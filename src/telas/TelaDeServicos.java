package telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ServicoController;
import util.ComponentesDeJFrame;
import util.Constantes;

public class TelaDeServicos extends JanelaPadrao {
	
	private DefaultTableModel modelo;
	private JTable tabela;
	
	private JButton jbVolta;
	private JButton jbEditar;
	private JButton jbRemover;
	private JButton jbNovo;


	public TelaDeServicos(String titulo) {
		super(titulo);
		adicionarTabela();
		adicionarJLabelTitulo();
		adicionarJButton();
		setVisible(true);

	}
	
	public DefaultTableModel getModelo() {
		return modelo;
	}

	public JTable getTabela() {
		return tabela;
	}

	public JButton getJbEditar() {
		return jbEditar;
	}

	public JButton getJbRemover() {
		return jbRemover;
	}

	public JButton getJbNovo() {
		return jbNovo;
	}

	public JButton getJbVolta() {
		return jbVolta;
	}

	private void adicionarJButton() {
		OuvinteBotaoVoltar ouvinteBotaoVoltar = new OuvinteBotaoVoltar();
		jbVolta = ComponentesDeJFrame.criarBotao("Voltar", 100, 460, 125, 50);
		jbVolta.addActionListener(ouvinteBotaoVoltar);
		add(jbVolta);

		
		OuvinteBotaoJbEditar ouvinteBotaoJbEditar = new OuvinteBotaoJbEditar(this);
		jbEditar = ComponentesDeJFrame.criarBotao("Editar", 250, 460, 125, 50);
		jbEditar.addActionListener(ouvinteBotaoJbEditar);
		add(jbEditar);
		
		
		OuvinteBotaoJbRemove ouvinteBotaoJbRemove = new OuvinteBotaoJbRemove(this);
		jbRemover = ComponentesDeJFrame.criarBotao("Remover", 400, 460, 125, 50);
		jbRemover.addActionListener(ouvinteBotaoJbRemove);
		add(jbRemover);
		
		
		OuvinteBotaoAdicionar ouvinteBotaoAdicionar = new OuvinteBotaoAdicionar(this);
		jbNovo = ComponentesDeJFrame.criarBotao("Adicionar", 550, 460, 125, 50);
		jbNovo.addActionListener(ouvinteBotaoAdicionar);
		add(jbNovo);

	}

	private void adicionarJLabelTitulo() {
		JLabel titulo = ComponentesDeJFrame.criaJLabel("Lista de Serviços", 270, 15, 400, 50, 30);
		add(titulo);
	}

	private void adicionarTabela() {
		modelo = new DefaultTableModel();
		modelo.addColumn("Serviços");

		Object[] todosOsServicos = ServicoController.getInstance().pegaServicos().toArray();

		for (Object t : todosOsServicos) {
			Object[] linha = { t };
			modelo.addRow(linha);

		}
		tabela = new JTable(modelo);
		JScrollPane painelTabela = new JScrollPane(tabela);
															
		painelTabela.setBounds(30, 90, 730, 350);
		add(painelTabela);
	}

	private class OuvinteBotaoVoltar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jbVolta) {
				dispose();
				TelaMenu telaMenu = new TelaMenu(Constantes.TITULO_MENU);
			}
		}

	}

	private class OuvinteBotaoJbEditar implements ActionListener{
		private TelaDeServicos janela;
		
		public OuvinteBotaoJbEditar(TelaDeServicos janela) {
			this.janela = janela;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int linhaSelecionada = tabela.getSelectedRow();
			
			if (linhaSelecionada == -1) {
				JOptionPane.showMessageDialog(janela, "Selecione uma linha");
			}else {
				String servicoEditado = JOptionPane.showInputDialog(janela, "Informe como deseja editar ");
				if(servicoEditado != null) {
					ServicoController.getInstance().salvarServicoEditado(linhaSelecionada, servicoEditado);
					JOptionPane.showMessageDialog(janela, "Serviço editado");
					getTabela().repaint();
					dispose();
					new TelaDeServicos(Constantes.TITULO_LISTA_SERVICOS); 
				}
		
			}
		}
		
	}
	
	private class OuvinteBotaoJbRemove implements ActionListener{
		private TelaDeServicos janela;
		
		public OuvinteBotaoJbRemove(TelaDeServicos janela) {
			this.janela = janela;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int linhaSelecionada = janela.getTabela().getSelectedRow();

			if (linhaSelecionada == -1) {
				
				JOptionPane.showMessageDialog(janela, "Selecione uma linha");
				
			}else {
				
				ServicoController.getInstance().removerServico(linhaSelecionada);
				
				janela.getModelo().removeRow(linhaSelecionada);
				janela.getTabela().repaint();
				
				JOptionPane.showMessageDialog(janela, "Removido");
			}
		}
		
	}
	
	private class OuvinteBotaoAdicionar implements ActionListener{
		private TelaDeServicos janela;
		
		public OuvinteBotaoAdicionar(TelaDeServicos janela) {
			this.janela = janela;
		}
		
		
		public void actionPerformed(ActionEvent e) {
			String novoServico = JOptionPane.showInputDialog(janela, "Informe o novo Serviço");
			Object[] novoServico2 = {novoServico};
			if(novoServico != null) {
				
				ServicoController.getInstance().adicionarServico(novoServico);
				JOptionPane.showMessageDialog(janela, "Adicionado");
				
				janela.getModelo().addRow(novoServico2);
				janela.getTabela().repaint();
			}
			
		}
		
	}
	

}

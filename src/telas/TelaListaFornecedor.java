package telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import baseDedados.CentralDeInformacoes;
import controller.FornecedorController;
import controller.ServicoController;
import model.FornecedorFisico;
import model.FornecedorJuridico;
import util.ComponentesDeJFrame;

public class TelaListaFornecedor extends JanelaPadrao {

	private JTable tabela;
	private JButton voltar;
	private JButton filtrar;
	private JButton detalhar;


	public TelaListaFornecedor(String titulo) {
		super(titulo);
		adicionaTituloJlabel();
		adicionarTabelaFornecedores();
		adicionarJButon();
		setVisible(true);

	}
	
	

	public JButton getVoltar() {
		return voltar;
	}

	public JButton getFiltrar() {
		return filtrar;
	}

	public JButton getDetalhar() {
		return detalhar;
	}

	public JTable getTabela() {
		return tabela;
	}

	private void adicionaTituloJlabel() {
		JLabel titulo = ComponentesDeJFrame.criaJLabel("Fornecedores", 285, 15, 400, 50, 30);
		add(titulo);
	}

	private void adicionarTabelaFornecedores() {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Nome");// adiciona colunas
		modelo.addColumn("Fisico/Juritico");
		modelo.addColumn("Quantidade de contratos");
		
		
		Object[] todosOsFornecedores =  CentralDeInformacoes.getInstance().getTodosOsFornecedores().toArray();

		for (Object t : todosOsFornecedores) {
			Object[] linha = new Object[3];
			
			if(t instanceof FornecedorFisico) {
				FornecedorFisico ff = (FornecedorFisico) t;
				linha[0] = ff.getNome();
				linha[1] = "Fisico";
				linha[2] = ff.getQuantContratosFisico();
			}else {
				FornecedorJuridico fj = (FornecedorJuridico) t;
				linha[0] = fj.getNome();
				linha[1] = "Jurico";
				linha[2] = fj.getQuantContratosJuridico();
			}
			
			modelo.addRow(linha);// adiciona alinha

		}
		tabela = new JTable(modelo);
		JScrollPane painelTabela = new JScrollPane(tabela);// esse JScrollPane serve para criar uma barra de rolagem na
															// tabela, mas se n quiser so n usar ele e no lugar que tem
															// a sua variavel de controle coloca a da tabela
		painelTabela.setBounds(30, 90, 730, 350);
		add(painelTabela);
	}
	
	public void adicionarJButon() {
		OuvinteBotaoVoltar ouvinteBotaoVoltar = new OuvinteBotaoVoltar();
		voltar = ComponentesDeJFrame.criarBotao("Voltar", 180, 460, 125, 50);
		voltar.addActionListener(ouvinteBotaoVoltar);
		add(voltar);

		
		OuvinteBotaoFiltrar ouvinteBotaoFiltrar = new OuvinteBotaoFiltrar(this);
		filtrar = ComponentesDeJFrame.criarBotao("Filtrar", 330, 460, 125, 50);
		filtrar.addActionListener(ouvinteBotaoFiltrar);
		add(filtrar);
		
		
		//OuvinteBotaoJbRemove ouvinteBotaoJbRemove = new OuvinteBotaoJbRemove(this);
		detalhar = ComponentesDeJFrame.criarBotao("Detalhar", 480, 460, 125, 50);
		//detalhar.addActionListener(ouvinteBotaoJbRemove);
		add(detalhar);
		
	}
	
	private class OuvinteBotaoVoltar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == voltar) {
				dispose();
				TelaMenu telaMenu = new TelaMenu("Tela Menu");
			}
		}

	}
	
	private class OuvinteBotaoFiltrar implements ActionListener{
		private TelaListaFornecedor janela;
		private JScrollPane tabela;
		
		public OuvinteBotaoFiltrar(TelaListaFornecedor janela) {
			this.janela = janela;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String tipoEscolhido = JOptionPane.showInputDialog(janela ,"Informe o tipo de fornecedor\n-Fisico   -Juridico");
			
			if(tipoEscolhido != null) {
				if(tipoEscolhido.equalsIgnoreCase("Fisico") || tipoEscolhido.equalsIgnoreCase("Juridico")) {
					tabela = FornecedorController.getInstance().filtrarFornecedores(tipoEscolhido);
					tabela.setBounds(30, 90, 730, 350);
					add(tabela);
					janela.repaint();
				}
			}
		
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

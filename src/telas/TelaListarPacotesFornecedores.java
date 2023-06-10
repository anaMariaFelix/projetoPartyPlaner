package telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.PacotesController;
import model.Pacote;
import util.ButtonEditor;
import util.ButtonRenderer;
import util.ComponentesDeJFrame;
import util.JLabelRenderer;

public class TelaListarPacotesFornecedores extends JanelaPadrao {
	private DefaultTableModel modelo;
	private JTable tabela;
	private JButton voltar;

	public TelaListarPacotesFornecedores(String titulo) {
		super(titulo);
		adicionarJLabel();
		adicinoarTabela();
		adicionarJButon();
		setVisible(true);

	}

	public DefaultTableModel getModelo() {
		return modelo;
	}

	public JTable getTabela() {
		return tabela;
	}


	private void adicionarJLabel() {
		JLabel titulo = ComponentesDeJFrame.criaJLabel("Lista de Pacotes", 285, 35, 400, 50, 30);
		add(titulo);

	}
	
	private void adicinoarTabela() {
		modelo = new DefaultTableModel();
		modelo.addColumn("Nome do Pacote");// adiciona colunas
		modelo.addColumn("Valor Do pacote");
		modelo.addColumn("Disponibilidade");
		modelo.addColumn("Serviços");
		modelo.addColumn("Detalhar");
		modelo.addColumn("Excluir");

		Object[] todosOsPacotes = PacotesController.getInstance().obterListaDePacote().toArray();

		tabela = new JTable(modelo);
		
		tabela.getColumn("Nome do Pacote").setCellRenderer(new JLabelRenderer()); 
		tabela.getColumn("Valor Do pacote").setCellRenderer(new JLabelRenderer()); 
		tabela.getColumn("Disponibilidade").setCellRenderer(new JLabelRenderer()); 
		
		
		tabela.getColumn("Excluir").setCellRenderer(new ButtonRenderer()); // Mostra um botao dentro da célula																// (linha/coluna)
		tabela.getColumn("Excluir").setCellEditor(new ButtonEditor(new JCheckBox()));// Para quando clica no botão, o
			
		
		// sistema entender que ele ta
		tabela.getColumn("Serviços").setCellRenderer(new ButtonRenderer()); 
		tabela.getColumn("Serviços").setCellEditor(new ButtonEditor(new JCheckBox())); 
		
		
		tabela.getColumn("Detalhar").setCellRenderer(new ButtonRenderer());
		tabela.getColumn("Detalhar").setCellEditor(new ButtonEditor(new JCheckBox()));

		JScrollPane painelTabela = new JScrollPane(tabela);
		painelTabela.setBounds(30, 135, 730, 350);
		add(painelTabela);
		preencherTabela(todosOsPacotes);
	}


	public void preencherTabela(Object[] pacotes) {
		for (Object p : pacotes) {
			Pacote pacote = (Pacote) p;
			Object[] linha = new Object[6];
			
			
			JLabel nomePacote = new JLabel(pacote.getNomeDoPacote());
			nomePacote.setToolTipText(pacote.getDescricao());
			linha[0] = nomePacote;
			
			JLabel valorPacote = new JLabel(pacote.getValorDoPacote());
			valorPacote.setToolTipText(pacote.getDescricao());
			linha[1] = valorPacote;
			
			JLabel disponivel = new JLabel(pacote.getDisponibilidade());
			
			if (PacotesController.getInstance().verificaSePacoteEstaDisponivel(pacote)) {
				disponivel.setText("Indisponivel");
				pacote.setDisponibilidade("Indisponivel");
				linha[2] = disponivel;
			}else {
				pacote.setDisponibilidade("Disponivel");
				disponivel.setText(pacote.getDisponibilidade());
				linha[2] = disponivel;
			}
			disponivel.setToolTipText(pacote.getDescricao());
			
			JButton servicos = new JButton("Serviços");
			servicos.setBackground(new Color(39, 228, 86));
			linha[3] = servicos;
			servicos.addActionListener(new OuvinteDeServicos(pacote));
			
			
			JButton btDetalhar = new JButton("Detalhar");
			linha[4] = btDetalhar;
			btDetalhar.setBackground(new Color(39, 228, 86));
			btDetalhar.addActionListener(new OuvinteDetalhar(pacote));

			
			JButton btExcluir = new JButton("Excluir");
			linha[5] = btExcluir;
			btExcluir.setBackground(new Color(191, 63, 63));
			btExcluir.addActionListener(new OuvinteBotaoExcluir(this, pacote));
			
			
			modelo.addRow(linha);// adiciona alinha

		}

	}

	public void adicionarJButon() {
		OuvinteBotaoVoltar ouvinteBotaoVoltar = new OuvinteBotaoVoltar();
		voltar = ComponentesDeJFrame.criarBotao("Voltar", 636, 490, 125, 35);
		voltar.addActionListener(ouvinteBotaoVoltar);
		add(voltar);
		
		JButton novoPacote = ComponentesDeJFrame.criarBotao("Novo", 636, 94, 125, 35);
		
		novoPacote.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaCadastrarPacotes("Cadastrar Pacote");
			}
		});
		
		add(novoPacote);

	}
	public class OuvinteDetalhar implements ActionListener{
		private Pacote pacote;
		
		public OuvinteDetalhar(Pacote pacote) {
			this.pacote = pacote;
		}
		
		public void actionPerformed(ActionEvent e) {
			AuxDetalharPacote auxDetalharPacote = new AuxDetalharPacote(pacote);
			auxDetalharPacote.detalharPacote(pacote);
			
		}
		
	}
	
	
	public class OuvinteDeServicos implements ActionListener{
		private Pacote pacote;
		
		public OuvinteDeServicos(Pacote pacote) {
			this.pacote = pacote;
		}
		
		public void actionPerformed(ActionEvent e) {
			dispose();
			new TelaListaServicoDoPacote("Serviços Do Pacote",pacote);
			
			
		}
		
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

	private class OuvinteBotaoExcluir implements ActionListener {
		private TelaListarPacotesFornecedores janela;
		private Pacote pacote;

		public OuvinteBotaoExcluir(TelaListarPacotesFornecedores janela, Pacote pacote) {
			this.janela = janela;
			this.pacote = pacote;

		}

		public void actionPerformed(ActionEvent e) {

			if (PacotesController.getInstance().removerPacote(pacote)) {
				JOptionPane.showMessageDialog(janela, "Pacote excluido");
				dispose();
				new TelaListarPacotesFornecedores("Lista De Pacotes");
			} else {
				JOptionPane.showMessageDialog(janela, "Não foi possivel excluir o pacote");
			}

		}

	}

}

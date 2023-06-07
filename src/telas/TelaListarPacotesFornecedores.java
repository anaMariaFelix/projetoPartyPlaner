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

	private void adicinoarTabela() {
		modelo = new DefaultTableModel();
		modelo.addColumn("Nome do Pacote");// adiciona colunas
		modelo.addColumn("Lista de Serviços");
		modelo.addColumn("Valor Do pacote");
		modelo.addColumn("Disponibilidade");
		modelo.addColumn("Detalhar");
		modelo.addColumn("Excluir");

		Object[] todosOsPacotes = PacotesController.getInstance().obterListaDePacote().toArray();

		tabela = new JTable(modelo);
		tabela.getColumn("Excluir").setCellRenderer(new ButtonRenderer()); // Mostra um botao dentro da célula
																			// (linha/coluna)
		tabela.getColumn("Excluir").setCellEditor(new ButtonEditor(new JCheckBox()));// Para quando clica no botão, o
																						// sistema entender que ele ta
																						// clicando no botão que está na
																						// linha
		tabela.getColumn("Detalhar").setCellRenderer(new ButtonRenderer());
		tabela.getColumn("Detalhar").setCellEditor(new ButtonEditor(new JCheckBox()));

		JScrollPane painelTabela = new JScrollPane(tabela);
		painelTabela.setBounds(30, 135, 730, 350);
		add(painelTabela);
		preencherTabela(todosOsPacotes);
	}

	private void adicionarJLabel() {
		JLabel titulo = ComponentesDeJFrame.criaJLabel("Lista de Pacotes", 285, 35, 400, 50, 30);
		add(titulo);

	}

	public void preencherTabela(Object[] pacotes) {
		for (Object p : pacotes) {
			Pacote pacote = (Pacote) p;
			Object[] linha = new Object[6];
			
			linha[0] = pacote.getNomeDoPacote();
			linha[1] = "tipo de serviços";
			linha[2] = pacote.getValorDoPacote();
			linha[3] = "disponivel";
			
			
			
			JButton btDetalhar = new JButton("Detalhar");
			linha[4] = btDetalhar;
			btDetalhar.setBackground(new Color(39, 228, 86));

			JButton btExcluir = new JButton("Excluir");
			linha[5] = btExcluir;
			btExcluir.setBackground(new Color(191,63,63));
			btExcluir.addActionListener(new OuvinteBotaoExcluir(this, pacote));
			
			
			modelo.addRow(linha);// adiciona alinha

		}

	}
	
	public void adicionarJButon() {
		OuvinteBotaoVoltar ouvinteBotaoVoltar = new OuvinteBotaoVoltar();
		voltar = ComponentesDeJFrame.criarBotao("Voltar", 636, 490, 125, 35);
		voltar.addActionListener(ouvinteBotaoVoltar);
		add(voltar);

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
	
	private class OuvinteBotaoExcluir implements ActionListener{
		private TelaListarPacotesFornecedores janela;
		private Pacote pacote;
		
		public OuvinteBotaoExcluir(TelaListarPacotesFornecedores janela, Pacote pacote) {
			this.janela = janela;
			this.pacote = pacote;
			
		}
		
		public void actionPerformed(ActionEvent e) {
			
			
			if(PacotesController.getInstance().removerPacote(pacote)) {
				JOptionPane.showMessageDialog(janela, "Pacote excluido");
				dispose();
				new TelaListarPacotesFornecedores("Lista De Pacotes");
			}else {
				JOptionPane.showMessageDialog(janela, "Não foi possivel excluir o pacote");
			}
			
			
		}
		
	}
	


}

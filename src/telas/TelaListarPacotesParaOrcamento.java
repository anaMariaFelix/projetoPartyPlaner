package telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.OrcamentoController;
import controller.PacotesController;
import model.Pacote;
import util.ButtonEditor;
import util.ButtonRenderer;
import util.ComponentesDeJFrame;
import util.JLabelRenderer;

public class TelaListarPacotesParaOrcamento extends JanelaPadrao{

	private DefaultTableModel modelo;
	private JTable tabela;
	
	private JLabel titulo;
	private JButton voltar;
	
	private ArrayList<Pacote> pacoteFornecedores = new ArrayList<>();
	
	private TelaCadastrarOrcamento janela;
	
	public TelaListarPacotesParaOrcamento(TelaCadastrarOrcamento janela,String titulo) {
		super(titulo);
		this.janela = janela;
		adicionarJLabel();
		adicionarJTable();
		adicionarJButton();
		setVisible(true);
	}
	

	public ArrayList<Pacote> getPacoteFornecedores() {
		return pacoteFornecedores;
	}


	private void adicionarJButton() {
		voltar = ComponentesDeJFrame.criarBotao("Voltar", 636, 490, 125, 35);
		voltar.addActionListener(new OuvinteBotaoVoltar());
		add(voltar);
	}


	protected void adicionarJLabel() {
		titulo = ComponentesDeJFrame.criaJLabel("Lista de Pacotes", 0, 40, 800, 50, 30);
		titulo.setHorizontalAlignment(JLabel.CENTER);
		add(titulo);
		
	}
	
	protected void adicionarJTable() {
		modelo = new DefaultTableModel();
		modelo.addColumn("Nome");
		modelo.addColumn("Valor");
		modelo.addColumn("Disponibilidade");
		modelo.addColumn("Adicionar");
		
		Object[] todosOsPacotes = PacotesController.getInstance().obterListaDePacote().toArray();
		
		tabela = new JTable(modelo);
		
		tabela.getColumn("Nome").setCellRenderer(new JLabelRenderer()); 
		tabela.getColumn("Valor").setCellRenderer(new JLabelRenderer()); 
		tabela.getColumn("Disponibilidade").setCellRenderer(new JLabelRenderer());
		
		tabela.getColumn("Adicionar").setCellRenderer(new ButtonRenderer()); 																// (linha/coluna)
		tabela.getColumn("Adicionar").setCellEditor(new ButtonEditor(new JCheckBox()));
		
		JScrollPane painelTabela = new JScrollPane(tabela);
		painelTabela.setBounds(30, 135, 730, 350);
		add(painelTabela);
		
		preencherTabela(todosOsPacotes);
		
	}

	public void preencherTabela(Object[] pacotes) {
		
		for (Object p : pacotes) {
			Pacote pacote = (Pacote) p;
			Object[] linha = new Object[4];
			
			JLabel nomePacote = new JLabel(pacote.getNomeDoPacote());
			nomePacote.setToolTipText(pacote.getDescricao());
			linha[0] = nomePacote;
			
			JLabel valorPacote = new JLabel(pacote.getValorDoPacote());
			valorPacote.setToolTipText(pacote.getDescricao());
			linha[1] = valorPacote;
			
			JLabel disponivel = new JLabel(pacote.getDisponibilidade());
			
			linha[2] = disponivel;
			
			if (PacotesController.getInstance().verificaSePacoteEstaDisponivel(pacote)) {
				disponivel.setText("Indisponivel");
				pacote.setDisponibilidade("Indisponivel");
				linha[2] = disponivel;
			}
			
			JButton adicionar = new JButton("Adicionar");
			adicionar.setBackground(new Color(39, 228, 86));
			adicionar.addActionListener(new OuvinteBotaoAdicionar(this,pacote));
			linha[3] = adicionar;
			
			modelo.addRow(linha);// adiciona alinha

		}

	}
	private class OuvinteBotaoVoltar implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			OrcamentoController.getInstance().populaArrayPacotes(pacoteFornecedores);
			dispose();
			janela.setVisible(true);
			
		}

	}
	
	public class OuvinteBotaoAdicionar implements ActionListener{
		private TelaListarPacotesParaOrcamento janela;
		private Pacote pacote;
		
		public OuvinteBotaoAdicionar(TelaListarPacotesParaOrcamento janela,Pacote pacote) {
			this.janela = janela;
			this.pacote = pacote;
			
		}
		
		public void actionPerformed(ActionEvent e) {
			if(!pacoteFornecedores.contains(pacote)) {
				pacoteFornecedores.add(pacote);
				JOptionPane.showMessageDialog(janela, "Pacote adicionado com sucesso");
			}else {
				JOptionPane.showMessageDialog(janela, "Pacote já adicionado");
			}
		}
		
	}
}

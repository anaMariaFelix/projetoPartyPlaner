package telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.OrcamentoController;
import model.OrcamentoOuContrato;
import util.ButtonEditor;
import util.ButtonRenderer;
import util.ComponentesDeJFrame;

public class TelaListarOrcamentosContratos extends JanelaPadrao{
	private JLabel titulo;
	
	private JRadioButton jrOrcamento;
	private JRadioButton jrContrato;
	private JRadioButton jrTodos;
	
	private JButton voltar;
	
	private DefaultTableModel modelo;
	private JTable tabela;

	public TelaListarOrcamentosContratos(String titulo) {
		super(titulo);
		adicionarJLabel();
		adicionarJTable();
		adicionarRadioButton();
		adicionarJButton();
		setVisible(true);
		
	}


	public JRadioButton getJrOrcamento() {
		return jrOrcamento;
	}

	public JRadioButton getJrContrato() {
		return jrContrato;
	}

	public JRadioButton getJrTodos() {
		return jrTodos;
	}

	public DefaultTableModel getModelo() {
		return modelo;
	}
	
	public JTable getTabela() {
		return tabela;
	}

	private void adicionarJLabel() {
		titulo = ComponentesDeJFrame.criaJLabel("Lista de Orçamentos/Contratos", 0, 36, 800, 50, 30);
		titulo.setHorizontalAlignment(JLabel.CENTER);
		add(titulo);
		
	}

	public void adicionarJButton() {
		OuvinteBotaoVoltar ouvinteBotaoVoltar = new OuvinteBotaoVoltar();
		voltar = ComponentesDeJFrame.criarBotao("Voltar", 636, 490, 125, 35);
		voltar.addActionListener(ouvinteBotaoVoltar);
		add(voltar);
		
		JButton botaoReunioes = ComponentesDeJFrame.criarBotao("Lista de Reuniões", 635, 98, 125, 35);
		botaoReunioes.addActionListener(new OuvinteReunioes());
		add(botaoReunioes);
	}

	protected void adicionarJTable() {
		modelo = new DefaultTableModel();
		modelo.addColumn("Nome");
		modelo.addColumn("Valor");
		modelo.addColumn("Status");
		modelo.addColumn("Cliente Associado");
		modelo.addColumn("Editar");
		modelo.addColumn("Detalhar");
		
		Object[] todosOsOrcamentosEContratos = OrcamentoController.getInstance().obterTodosOsOrcamentoEContratos().toArray();
		
		tabela = new JTable(modelo);
		
		tabela.getColumn("Editar").setCellRenderer(new ButtonRenderer()); 																
		tabela.getColumn("Editar").setCellEditor(new ButtonEditor(new JCheckBox()));
		
		tabela.getColumn("Detalhar").setCellRenderer(new ButtonRenderer()); 																
		tabela.getColumn("Detalhar").setCellEditor(new ButtonEditor(new JCheckBox()));
		
		JScrollPane painelTabela = new JScrollPane(tabela);
		painelTabela.setBounds(30, 135, 730, 350);
		add(painelTabela);
		
		preencherTabela(todosOsOrcamentosEContratos);
		
	}
	
	public void preencherTabela(Object[] orcamentosEContratos) {
		limparTabela();
		for (Object o : orcamentosEContratos) {
			OrcamentoOuContrato orcamentoOuContrato = (OrcamentoOuContrato) o;
			Object[] linha = new Object[6];
	
			linha[0] = orcamentoOuContrato.getNomeDoEvento() ;
			linha[1] = orcamentoOuContrato.getValor();
			linha[2] = "Orçamento";
				
			if (orcamentoOuContrato.isFoiContradoOuNao()) {
				linha[2] = "Contrato";			
			}
			
			linha[3] = orcamentoOuContrato.getClienteAssociado().getNome();
			
			JButton editar = new JButton("Editar");
			editar.setBackground(new Color(39, 228, 86));
			editar.addActionListener(new OuvinteBotaoEditar(orcamentoOuContrato));
			linha[4] = editar;
			
			JButton detalhar = new JButton("Detalhar");
			detalhar.setBackground(new Color(39, 228, 86));
			detalhar.addActionListener(new OuvinteBotaoDetalhar(orcamentoOuContrato));
			linha[5] = detalhar;
			
			modelo.addRow(linha);

		}

	}
	
	private void adicionarRadioButton() {

		OuvinteRadioButton ouvinteRadioButton = new OuvinteRadioButton(this);
		jrOrcamento = ComponentesDeJFrame.criarRadioButtons("Orçamentos", false, 30, 105, 110, 30, 15);
		jrOrcamento.setFont(new Font("Arial", Font.ITALIC, 15));
		jrOrcamento.addActionListener(ouvinteRadioButton);
		add(jrOrcamento);

		jrContrato = ComponentesDeJFrame.criarRadioButtons("Contratos", false, 165, 105, 100, 30, 15);
		jrContrato.setFont(new Font("Arial", Font.ITALIC, 15));
		jrContrato.addActionListener(ouvinteRadioButton);
		add(jrContrato);

		jrTodos = ComponentesDeJFrame.criarRadioButtons("Todos", true, 275, 105, 100, 30, 15);
		jrTodos.setFont(new Font("Arial", Font.ITALIC, 15));
		jrTodos.addActionListener(ouvinteRadioButton);
		add(jrTodos);

		ButtonGroup bg = new ButtonGroup();
		bg.add(jrTodos);
		bg.add(jrContrato);
		bg.add(jrOrcamento);

	}
	
	private class OuvinteRadioButton implements ActionListener {
		private TelaListarOrcamentosContratos janela;
		private JScrollPane tabelaFiltrada;

		public OuvinteRadioButton(TelaListarOrcamentosContratos janela) {
			this.janela = janela;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (jrTodos.isSelected()) {
				preencherTabela(OrcamentoController.getInstance().obterTodosOsOrcamentoEContratos().toArray());
			} else if (jrContrato.isSelected()) {
				preencherTabela(OrcamentoController.getInstance().filtrarPorTipo("Contrato").toArray());
			} else {
				preencherTabela(OrcamentoController.getInstance().filtrarPorTipo("Orcamento").toArray());
			}

		}

	}
	
	public void limparTabela() {
		int cont = modelo.getRowCount();
		for (int i = 0; i < cont; i++) {
			modelo.removeRow(0);
		}
		tabela.repaint();
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
	
	public class OuvinteBotaoDetalhar implements ActionListener{
		
		private OrcamentoOuContrato orcamentoContrato;
		
		public OuvinteBotaoDetalhar(OrcamentoOuContrato orcamentoContrato) {
			this.orcamentoContrato = orcamentoContrato;
		}
		
		public void actionPerformed(ActionEvent e) {
			dispose();
			new AuxDetalharOrcamentoContrato(orcamentoContrato);
			
			
			
		}
		
	}
	public class OuvinteBotaoEditar implements ActionListener{
		private OrcamentoOuContrato orcamentoContrato;
		
		public OuvinteBotaoEditar(OrcamentoOuContrato orcamentoContrato) {
			this.orcamentoContrato = orcamentoContrato;
		}
		
		public void actionPerformed(ActionEvent e) {
			dispose();
			new AuxTelaEditarOrcamento(orcamentoContrato,"Editar Orçamento/Contrato");
			
		}
		
	}
	
	public class OuvinteReunioes implements ActionListener{

		
		
		public void actionPerformed(ActionEvent e) {
			dispose();
			new TelaListaReunioes("Reuniões");
			
		}
		
	}
	
	
}

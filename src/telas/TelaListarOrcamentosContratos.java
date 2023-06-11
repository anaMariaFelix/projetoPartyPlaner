package telas;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
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
	
	private DefaultTableModel modelo;
	private JTable tabela;

	public TelaListarOrcamentosContratos(String titulo) {
		super(titulo);
		adicionarJLabel();
		adicionarJTable();
		setVisible(true);
		
	}


	public DefaultTableModel getModelo() {
		return modelo;
	}
	
	public JTable getTabela() {
		return tabela;
	}

	private void adicionarJLabel() {
		titulo = ComponentesDeJFrame.criaJLabel("Lista de Orçamentos/Contratos", 0, 70, 800, 50, 30);
		titulo.setHorizontalAlignment(JLabel.CENTER);
		add(titulo);
		
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
		
		for (Object o : orcamentosEContratos) {
			OrcamentoOuContrato orcamentoOuContrato = (OrcamentoOuContrato) o;
			Object[] linha = new Object[6];
	
			linha[0] = orcamentoOuContrato.getNomeDoEvento() ;
			linha[1] = orcamentoOuContrato.getValor();
			linha[2] = "Orçamento";
			//adicionar.addActionListener(new OuvinteBotaoAdicionar(this,fisico));
				
			if (orcamentoOuContrato.isFoiContradoOuNao()) {
				linha[2] = "Contrato";
			
			}
			linha[3] = orcamentoOuContrato.getClienteAssociado().getNome();
			
			JButton editar = new JButton("Editar");
			editar.setBackground(new Color(39, 228, 86));
			linha[4] = editar;
			
			JButton detalhar = new JButton("Detalhar");
			detalhar.setBackground(new Color(39, 228, 86));
			linha[5] = detalhar;
			
			modelo.addRow(linha);// adiciona alinha

		}

	}
}

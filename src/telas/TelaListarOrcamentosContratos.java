package telas;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.FornecedorController;
import model.FornecedorFisico;
import model.FornecedorJuridico;
import telas.TelaListaFornecedoresParaOrcamento.OuvinteBotaoAdicionar;
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
		modelo.addColumn("Disponibilidade");
		modelo.addColumn("Cliente Associado");
		
		Object[] todosOsForncedores = FornecedorController.getInstance().obterTodosOsFornecedores().toArray();
		
		tabela = new JTable(modelo);
		
		tabela.getColumn("Adicionar").setCellRenderer(new ButtonRenderer()); 																// (linha/coluna)
		tabela.getColumn("Adicionar").setCellEditor(new ButtonEditor(new JCheckBox()));
		
		JScrollPane painelTabela = new JScrollPane(tabela);
		painelTabela.setBounds(30, 135, 730, 350);
		add(painelTabela);
		
		preencherTabela(todosOsForncedores);
		
	}
	
	public void preencherTabela(Object[] fornecedores) {
		
		for (Object p : fornecedores) {
			Object[] linha = new Object[4];
			
			JButton adicionar = new JButton("Adicionar");
			adicionar.setBackground(new Color(39, 228, 86));
			
			linha[3] = adicionar;
			
			if(p instanceof FornecedorFisico ) {
				FornecedorFisico fisico = (FornecedorFisico) p;
				linha[0] = fisico.getNome();
				linha[1] = fisico.getCpfCnpj();
				linha[2] = "Indisponivel";
				//adicionar.addActionListener(new OuvinteBotaoAdicionar(this,fisico));
				
				if (fisico.isDisponibilidade()) {
					linha[2] = "Disponivel";
				}
			}else {
				FornecedorJuridico juridico = (FornecedorJuridico)p;
				linha[0] = juridico.getNome();
				linha[1] = juridico.getCnpj();
				linha[2] = "Indisponivel";
				//adicionar.addActionListener(new OuvinteBotaoAdicionar(this,juridico));
				
				if (juridico.isDisponibilidade()) {
					linha[2] = "Disponivel";
				}
			}

			modelo.addRow(linha);// adiciona alinha

		}

	}
}

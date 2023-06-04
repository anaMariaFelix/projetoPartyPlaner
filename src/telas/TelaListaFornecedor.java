package telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import baseDedados.CentralDeInformacoes;
import controller.FornecedorController;
import model.FornecedorFisico;
import model.FornecedorJuridico;
import model.Pessoa;
import util.ComponentesDeJFrame;

public class TelaListaFornecedor extends JanelaPadrao {

	private JTable tabela;
	private JButton voltar;
	private JButton novo;
	private DefaultTableModel modelo;
	private JRadioButton jrFisico;
	private JRadioButton jrJuridico;
	private JRadioButton jrTodos;

	public TelaListaFornecedor(String titulo) {
		super(titulo);
		adicionaTituloJlabel();
		adicionarTabelaFornecedores();
		adicionarJButon();
		adicionarRadioButton();
		setVisible(true);

	}
	
	

	public JButton getVoltar() {
		return voltar;
	}


	public JButton getDetalhar() {
		return novo;
	}

	public JTable getTabela() {
		return tabela;
	}

	private void adicionaTituloJlabel() {
		JLabel titulo = ComponentesDeJFrame.criaJLabel("Fornecedores", 285, 15, 400, 50, 30);
		add(titulo);
	}

	private void adicionarTabelaFornecedores() {
		modelo = new DefaultTableModel();
		modelo.addColumn("Nome");// adiciona colunas
		modelo.addColumn("Fisico/Juridico");
		modelo.addColumn("Quantidade de contratos");
		modelo.addColumn("Opções");

		Object[] todosOsFornecedores = FornecedorController.getInstance().obterTodosOsFornecedores().toArray();

		tabela = new JTable(modelo);
		JScrollPane painelTabela = new JScrollPane(tabela);// esse JScrollPane serve para criar uma barra de rolagem na
															// tabela, mas se n quiser so n usar ele e no lugar que tem
															// a sua variavel de controle coloca a da tabela
		painelTabela.setBounds(30, 135, 730, 350);
		add(painelTabela);
		preencherTabela(todosOsFornecedores);
	}

	public void preencherTabela(Object[] fornecedores) {
		limparTabela();
		for (Object t : fornecedores) {
			Object[] linha = new Object[4];

			if (t instanceof FornecedorFisico) {
				FornecedorFisico ff = (FornecedorFisico) t;
				linha[0] = ff.getNome();
				linha[1] = "Fisico";
				linha[2] = ff.getQuantContratosFisico();
	
			} else {
				FornecedorJuridico fj = (FornecedorJuridico) t;
				linha[0] = fj.getNome();
				linha[1] = "Juridico";
				linha[2] = fj.getQuantContratosJuridico();
			}
			linha[3] = new JButton("Editar");

			modelo.addRow(linha);// adiciona alinha

		}

	}

	public void limparTabela() {
		int cont = modelo.getRowCount();
		for (int i = 0; i < cont; i++) {
			modelo.removeRow(0);
		}
		tabela.repaint();
	}

	private void adicionarRadioButton() {

		OuvinteRadioButton ouvinteRadioButton = new OuvinteRadioButton(this);
		jrFisico = ComponentesDeJFrame.criarRadioButtons("Fisico", false, 30, 105, 80, 30, 15);
		jrFisico.setFont(new Font("Arial", Font.ITALIC, 15));
		jrFisico.addActionListener(ouvinteRadioButton);
		add(jrFisico);

		jrJuridico = ComponentesDeJFrame.criarRadioButtons("Juridico", false, 135, 105, 80, 30, 15);
		jrJuridico.setFont(new Font("Arial", Font.ITALIC, 15));
		jrJuridico.addActionListener(ouvinteRadioButton);
		add(jrJuridico);

		jrTodos = ComponentesDeJFrame.criarRadioButtons("Todos", true, 245, 105, 80, 30, 15);
		jrTodos.setFont(new Font("Arial", Font.ITALIC, 15));
		jrTodos.addActionListener(ouvinteRadioButton);
		add(jrTodos);

		ButtonGroup bg = new ButtonGroup();
		bg.add(jrTodos);
		bg.add(jrJuridico);
		bg.add(jrFisico);

	}

	public void adicionarJButon() {
		OuvinteBotaoVoltar ouvinteBotaoVoltar = new OuvinteBotaoVoltar();
		voltar = ComponentesDeJFrame.criarBotao("Voltar", 636, 490, 125, 35);
		voltar.addActionListener(ouvinteBotaoVoltar);
		add(voltar);

		
		novo = ComponentesDeJFrame.criarBotao("Novo", 636, 94, 125, 35);
		
		novo.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaCadastrarFornecedor("Cadastrar Fornecedor");
			}
		});
		add(novo);
		
		
		

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

	private class OuvinteRadioButton implements ActionListener {
		private TelaListaFornecedor janela;
		private JScrollPane tabelaFiltrada;

		public OuvinteRadioButton(TelaListaFornecedor janela) {
			this.janela = janela;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (jrTodos.isSelected()) {
				preencherTabela(FornecedorController.getInstance().obterTodosOsFornecedores().toArray());
			}else if(jrFisico.isSelected()) {
				preencherTabela(FornecedorController.getInstance().filtrarPorTipo("Fisico").toArray());
			}else {
				preencherTabela(FornecedorController.getInstance().filtrarPorTipo("Juridico").toArray());
			}

		}

	}

}

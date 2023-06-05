package telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.FornecedorController;
import model.FornecedorFisico;
import model.FornecedorJuridico;
import model.Pessoa;
import util.ButtonEditor;
import util.ButtonRenderer;
import util.ComponentesDeJFrame;
import util.ValidaEmail;
import util.ValidadorCPF;
import util.ValidarCNPJ;

public class TelaListaFornecedor extends JanelaPadrao {

	private JTable tabela;
	private JButton voltar;
	private JButton novo;
	private DefaultTableModel modelo;
	private JRadioButton jrFisico;
	private JRadioButton jrJuridico;
	private JRadioButton jrTodos;
	private JButton editar;
	private TelaCadastrarFornecedor editarDados;

	public TelaListaFornecedor(String titulo) {
		super(titulo);
		adicionaTituloJlabel();
		adicionarTabelaFornecedores();
		adicionarJButon();
		adicionarRadioButton();
		setVisible(true);

	}
	

	public TelaCadastrarFornecedor getEditarDados() {
		return editarDados;
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
		modelo.addColumn("Editar");
		modelo.addColumn("Detalhar");

		Object[] todosOsFornecedores = FornecedorController.getInstance().obterTodosOsFornecedores().toArray();

		tabela = new JTable(modelo);
		tabela.getColumn("Editar").setCellRenderer(new ButtonRenderer());
		tabela.getColumn("Editar").setCellEditor(new ButtonEditor(new JCheckBox()));
		tabela.getColumn("Detalhar").setCellRenderer(new ButtonRenderer());
		tabela.getColumn("Detalhar").setCellEditor(new ButtonEditor(new JCheckBox()));

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
			Object[] linha = new Object[5];

			JButton bt = new JButton("Editar");
			linha[3] = bt;
			bt.setBackground(new Color(39, 228, 86));

			if (t instanceof FornecedorFisico) {
				FornecedorFisico ff = (FornecedorFisico) t;
				linha[0] = ff.getNome();
				linha[1] = "Fisico";
				linha[2] = ff.getQuantContratosFisico();
				bt.addActionListener(new OuvinteButtonEditar(this ,ff.getCpf()));
			} else {
				FornecedorJuridico fj = (FornecedorJuridico) t;
				linha[0] = fj.getNome();
				linha[1] = "Juridico";
				linha[2] = fj.getQuantContratosJuridico();
				bt.addActionListener(new OuvinteButtonEditar(this, fj.getCnpj()));
			}

			linha[4] = new JButton("Detalhar");
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

//		OuvinteButtonEditar ouvinteButtonEditar = new OuvinteButtonEditar(this);
		editar = ComponentesDeJFrame.criarBotao("Editar", 300, 490, 125, 35);
//		editar.addActionListener(ouvinteButtonEditar);
		add(editar);

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
			} else if (jrFisico.isSelected()) {
				preencherTabela(FornecedorController.getInstance().filtrarPorTipo("Fisico").toArray());
			} else {
				preencherTabela(FornecedorController.getInstance().filtrarPorTipo("Juridico").toArray());
			}

		}

	}

	private class OuvinteButtonEditar implements ActionListener {
		private TelaListaFornecedor janela;
		private String cpfCnpj;

		public OuvinteButtonEditar() {
		}

		public OuvinteButtonEditar(TelaListaFornecedor janela,String cpfCnpj) {
			this.janela = janela;
			this.cpfCnpj = cpfCnpj;
		}

		public void actionPerformed(ActionEvent e) {
			editarDados = new TelaCadastrarFornecedor("Dados do fornecedor");
			editarDados.getBotaoSalvar().removeActionListener(editarDados.getOuvinteSalvarFornecedor());
			
			OuvinteBotaoSalvarFornecedorEditado ouvinteBotaoSalvarFornecedorEditado = new OuvinteBotaoSalvarFornecedorEditado(janela);
			editarDados.getBotaoSalvar().addActionListener(ouvinteBotaoSalvarFornecedorEditado);
			
			Pessoa pessoa = FornecedorController.getInstance().recuperarFornecedorPorCpfOuCnpj(cpfCnpj);
			
			editarDados.getCampoNomeCompleto().setText(pessoa.getNome());
			editarDados.getCampoEmail().setText(pessoa.getEmail());
			editarDados.getCampoTelefone().setText(pessoa.getTelefone());

			if (pessoa instanceof FornecedorFisico) {
				FornecedorFisico fisico = (FornecedorFisico) pessoa;
				editarDados.getCampoCPF().setText(fisico.getCpf());
				editarDados.setListaDeServicos(fisico.getServicos());
				editarDados.getCampoCPF().setEnabled(false);

			} else {
				FornecedorJuridico juridico = (FornecedorJuridico) pessoa;
				editarDados.getPessoaJuridica().doClick();
				editarDados.getCampoCNPJ().setText(juridico.getCnpj());
				editarDados.getPessoaJuridica().setBounds(280, 380, 200, 30);
				editarDados.setListaDeServicos(juridico.getServicos());
				editarDados.getCpfCnpj().setText("CNPJ");
				editarDados.getPessoaFisica().setVisible(false);
				editarDados.getCampoCNPJ().setEnabled(false);

			}

		}
		
	}
	private class OuvinteBotaoSalvarFornecedorEditado implements ActionListener {
		private TelaListaFornecedor janela;
		
		public OuvinteBotaoSalvarFornecedorEditado(TelaListaFornecedor janela) {
			this.janela = janela;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String nome = editarDados.getCampoNomeCompleto().getText();
			String telefone = editarDados.getCampoTelefone().getText().replace("(", "").replace(")", "").replace("-", "")
					.trim();
			String email = editarDados.getCampoEmail().getText();
			Pessoa fornecedor = null;

			if (e.getSource() == editarDados.getBotaoSalvar()) {
				
				if (editarDados.getPessoaFisica().isSelected()) {
					String cpf = editarDados.removerMacaraCampoCPF(editarDados.getCampoCPF());
					FornecedorController.getInstance().removerFornecedor(FornecedorController.getInstance().pegarIndeciDoFornecedor(cpf));

					if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty() || cpf.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos");

					}else if (!ValidaEmail.emailValidator(email)) {
						JOptionPane.showMessageDialog(janela, "Email inválido, informe novamente");

					} else if (!ValidadorCPF.isCPF(cpf)) {
						JOptionPane.showMessageDialog(janela, "O CPF não é válido, informe novamente");

					//} else if (editarDados.getListaDeServicos().isEmpty()) {
						//JOptionPane.showMessageDialog(janela, "Você deve fornecer ao menos um serviço");
					}else {
						fornecedor = new FornecedorFisico(nome, null, telefone, cpf, email,editarDados.getListaDeServicos());
						//editarDados.setListaDeServicos()  = new ArrayList<String>();
						if (FornecedorController.getInstance().adicionarFornecedor(fornecedor)) {
							JOptionPane.showMessageDialog(janela, "Fornecedor cadastrado com sucesso!");
							janela.dispose();
							new TelaListaFornecedor("Lista de Fornecedores");
						}
					}

				} else if (editarDados.getPessoaJuridica().isSelected()) {
					String cnpj = editarDados.removerMascaraCampoCNPJ(editarDados.getCampoCNPJ());
					FornecedorController.getInstance().removerFornecedor(FornecedorController.getInstance().pegarIndeciDoFornecedor(cnpj));


					if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty() || cnpj.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos");

					}else if (!ValidaEmail.emailValidator(email)) {
						JOptionPane.showMessageDialog(janela, "Email inválido, informe novamente");

					} else if (!ValidarCNPJ.isCNPJ(cnpj)) {
						JOptionPane.showMessageDialog(janela, "O CNPJ não é válido, informe novamente");

					//} else if (editarDados.getListaDeServicos().isEmpty()) {
						//JOptionPane.showMessageDialog(janela, "Você deve fornecer ao menos um serviço");
						
					} else {
						fornecedor = new FornecedorJuridico(nome, null, telefone, email, cnpj,editarDados.getListaDeServicos());
						//listaDeServicos = new ArrayList<String>();
						
						if (FornecedorController.getInstance().adicionarFornecedor(fornecedor)) {
							JOptionPane.showMessageDialog(janela, "Fornecedor cadastrado com sucesso!");
							janela.dispose();
							new TelaListaFornecedor("Lista de Fornecedores");

						}
					}
				}
			}

		}
	}

}

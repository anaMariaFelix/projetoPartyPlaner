package telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ClienteController;
import controller.OrcamentoController;
import model.FornecedorFisico;
import model.FornecedorJuridico;
import model.OrcamentoOuContrato;
import model.Pacote;
import model.Pessoa;
import util.ComponentesDeJFrame;

public class AuxTelaEditarOrcamento {

	private OrcamentoOuContrato orcamentoContrato;
	private TelaCadastrarOrcamento telaCadastrarOrcamento;

	private String titulo;

	private DefaultTableModel modelo;
	private JTable tabela;

	private Object[] todosOsFornecedoresDoOrcamento = null;
	private int tamanhoVetor;

	private JCheckBox contrato;
	private JButton incluirFornecedor;
	private JButton botaoExcluir;

	private TelaListaFornecedoresParaOrcamento telaListaFornecedoresParaOrcamento;

	public AuxTelaEditarOrcamento(OrcamentoOuContrato orcamentoContrato, String titulo) {
		this.orcamentoContrato = orcamentoContrato;
		this.titulo = titulo;
		configurarTela();
		adicionarTabelaFornecedores();
	}

	public JButton getBotaoExcluir() {
		return botaoExcluir;
	}

	public Object[] getTodosOsFornecedoresDoOrcamento() {
		return todosOsFornecedoresDoOrcamento;
	}

	public void setTodosOsFornecedoresDoOrcamento(Object[] totodosOsFornecedoresDoOrcamento){
		this.todosOsFornecedoresDoOrcamento = todosOsFornecedoresDoOrcamento;
	}

	public JTable getTabela() {
		return tabela;
	}

	public DefaultTableModel getModelo() {
		return modelo;
	}

	private void adicionarJCheckBox() {

		contrato = new JCheckBox("Promover para Contrato");
		contrato.setBounds(380, 450, 190, 100);
		contrato.setFont(new Font("Arial", Font.BOLD, 13));
		telaCadastrarOrcamento.add(contrato);

		if (orcamentoContrato.isFoiContradoOuNao()) {
			contrato.setText("Marcar como concluido");
		}
	}

	private void adicionarJButton() {

		incluirFornecedor = ComponentesDeJFrame.criarBotao("Incluir Fornecedor", 574, 90, 140, 30);
		incluirFornecedor.addActionListener(new OuvinteBotaoIncluirFornecedor(orcamentoContrato, this));
		telaCadastrarOrcamento.add(incluirFornecedor);

		botaoExcluir = ComponentesDeJFrame.criarBotao("Remover", 576, 450, 140, 30);
		botaoExcluir.addActionListener(new OuvinteBotaoExcluir(this, orcamentoContrato));
		telaCadastrarOrcamento.add(botaoExcluir);

	}

	private void configurarTela() {

		telaCadastrarOrcamento = new TelaCadastrarOrcamento(titulo);
		telaCadastrarOrcamento.getTitulo().setText(titulo);

		telaCadastrarOrcamento.getEmailClienteAssociado().setBounds(50, 100, 100, 30);
		telaCadastrarOrcamento.getInformacaoCliente().setBounds(115, 100, 225, 30);
		telaCadastrarOrcamento.getCampoEmailCliente().setBounds(50, 130, 280, 30);
		telaCadastrarOrcamento.getCampoEmailCliente().setText(orcamentoContrato.getClienteAssociado().getEmail());
		telaCadastrarOrcamento.getCampoEmailCliente().setEnabled(false);

		telaCadastrarOrcamento.getNomeEvento().setBounds(50, 170, 200, 30);
		telaCadastrarOrcamento.getCampoNomeEvento().setText(orcamentoContrato.getNomeDoEvento());
		telaCadastrarOrcamento.getCampoNomeEvento().setBounds(50, 200, 280, 30);

		telaCadastrarOrcamento.getDataEHoraEvento().setBounds(50, 240, 130, 30);
		telaCadastrarOrcamento.getCampoDataEHoraEvento()
				.setText(mudarDeLocalDateTimeParaString(orcamentoContrato.getDataEHoraDoEvento()));
		telaCadastrarOrcamento.getCampoDataEHoraEvento().setBounds(50, 270, 280, 30);

		telaCadastrarOrcamento.getLocalEvento().setBounds(50, 310, 100, 30);
		telaCadastrarOrcamento.getCampoLocalEvento().setText(orcamentoContrato.getLocalDoEvento());
		telaCadastrarOrcamento.getCampoLocalEvento().setBounds(50, 340, 280, 30);

		telaCadastrarOrcamento.getInformacao().setBounds(140, 385, 225, 20);
		telaCadastrarOrcamento.getTamanhoEvento().setBounds(50, 380, 90, 30);
		telaCadastrarOrcamento.getCampoTamanhoEvento().setText(orcamentoContrato.getTamanho());
		telaCadastrarOrcamento.getCampoTamanhoEvento().setBounds(50, 415, 280, 30);

		telaCadastrarOrcamento.getValor().setBounds(50, 450, 330, 35);
		if (!orcamentoContrato.getFornecedores().isEmpty()) {
			telaCadastrarOrcamento.getCampoValor().setEnabled(true);
		} else {
			telaCadastrarOrcamento.getCampoValor().setEnabled(false);
		}
		telaCadastrarOrcamento.getCampoValor().setText(orcamentoContrato.getValor());
		telaCadastrarOrcamento.getCampoValor().setBounds(50, 490, 280, 30);

		telaCadastrarOrcamento.getBotaoVoltar().removeActionListener(telaCadastrarOrcamento.getOuvinteVoltar());
		telaCadastrarOrcamento.getBotaoVoltar().setBounds(50, 535, 110, 30);
		telaCadastrarOrcamento.getBotaoVoltar().addActionListener(new OuvinteBotaoVoltar(telaCadastrarOrcamento));

		telaCadastrarOrcamento.getBotaoSalvar().removeActionListener(telaCadastrarOrcamento.getSalvar());
		telaCadastrarOrcamento.getBotaoSalvar().setBounds(220, 535, 110, 30);
		telaCadastrarOrcamento.getBotaoSalvar()
				.addActionListener(new OuvinteBotaoSalvar(telaCadastrarOrcamento, orcamentoContrato));

		telaCadastrarOrcamento.getBotaoAdicionarFornecedores().setVisible(false);

		adicionarJButton(); // adiciono o botao e o check e depois verifico se é um contrato concluido(16)
		adicionarJCheckBox(); 

		if (orcamentoContrato.isFoiConcluido()) {// Se nao for concluido deixa tudo como esta(podendo alterar), se nao
													// deixo os campos inacessiveis (16)

			telaCadastrarOrcamento.getCampoNomeEvento().setEnabled(false);
			telaCadastrarOrcamento.getCampoDataEHoraEvento().setEnabled(false);
			telaCadastrarOrcamento.getCampoLocalEvento().setEnabled(false);
			telaCadastrarOrcamento.getCampoTamanhoEvento().setEnabled(false);
			telaCadastrarOrcamento.getCampoValor().setEnabled(false);

			incluirFornecedor.setVisible(false);// deixei o botao falso para nao podermos colocar fornecedores se for um
			contrato.setVisible(false); // contrato concluido
			telaCadastrarOrcamento.getBotaoSalvar().setVisible(false);
			botaoExcluir.setVisible(false);
		}

	}

	private void adicionarTabelaFornecedores() {
		modelo = new DefaultTableModel();
		modelo.addColumn("Nome");

		tabela = new JTable(modelo);

		if (!orcamentoContrato.getFornecedores().isEmpty()) {
			todosOsFornecedoresDoOrcamento = orcamentoContrato.getFornecedores().toArray();

			if (orcamentoContrato.isFoiConcluido()) {
				botaoExcluir.setVisible(false);

			}
		} else {
			todosOsFornecedoresDoOrcamento = orcamentoContrato.getPacotesDeFornecedores().toArray();
		}

		JScrollPane painelTabela = new JScrollPane(tabela);
		painelTabela.setBounds(380, 128, 335, 318);
		telaCadastrarOrcamento.add(painelTabela);
		preencherTabela(todosOsFornecedoresDoOrcamento);
	}

	public void preencherTabela(Object[] fornecedoresOrcamento) {
		limparTabela();
		for (Object t : fornecedoresOrcamento) {
			Object[] linha = new Object[1];

			if (t instanceof Pessoa) {
				Pessoa fisicoOuJuridico = (Pessoa) t;
				linha[0] = fisicoOuJuridico.getNome();

			} else {
				Pacote pacote = (Pacote) t;
				linha[0] = pacote.getNomeDoPacote();
			}

			modelo.addRow(linha);
		}
		tabela.repaint();

	}

	public void limparTabela() {
		int cont = modelo.getRowCount();
		for (int i = 0; i < cont; i++) {
			modelo.removeRow(0);
		}
		tabela.repaint();
	}

	public static String mudarDeLocalDateTimeParaString(LocalDateTime dataEHora) {

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		String dataFormatada = dataEHora.format(dateTimeFormatter);

		return dataFormatada;

	}

	public class OuvinteBotaoVoltar implements ActionListener {
		private TelaCadastrarOrcamento janela;

		public OuvinteBotaoVoltar(TelaCadastrarOrcamento janela) {
			this.janela = janela;
		}

		public void actionPerformed(ActionEvent e) {
			janela.dispose();
			new TelaListarOrcamentosContratos("Lista de Orçamentos/Contratos");
		}

	}

	private class OuvinteBotaoExcluir implements ActionListener {

		private AuxTelaEditarOrcamento janelaEditar;
		private OrcamentoOuContrato orcamento;

		public OuvinteBotaoExcluir(AuxTelaEditarOrcamento janelaEditar, OrcamentoOuContrato orcamento) {
			this.janelaEditar = janelaEditar;
			this.orcamento = orcamento;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			int linhaSelecionada = janelaEditar.getTabela().getSelectedRow();

			if (linhaSelecionada == -1) {

				JOptionPane.showMessageDialog(null, "Selecione uma linha");

			} else {
				orcamento.getFornecedores().remove(linhaSelecionada);

				todosOsFornecedoresDoOrcamento = orcamento.getFornecedores().toArray();

				janelaEditar.getModelo().removeRow(linhaSelecionada);
				janelaEditar.getTabela().repaint();

			}

		}

	}

	public class OuvinteBotaoIncluirFornecedor implements ActionListener {

		private AuxTelaEditarOrcamento telaEditarOrcamento;
		private OrcamentoOuContrato orcamentoContrato;

		public OuvinteBotaoIncluirFornecedor(OrcamentoOuContrato orcamentoContrato,
				AuxTelaEditarOrcamento telaEditarOrcamento) {
			this.telaEditarOrcamento = telaEditarOrcamento;
			this.orcamentoContrato = orcamentoContrato;
		}

		public void actionPerformed(ActionEvent e) {

			telaListaFornecedoresParaOrcamento = new TelaListaFornecedoresParaOrcamento(true,
					guardaDadosDosCamposCopia(), telaCadastrarOrcamento, "Lista de Fornecedores");

			telaCadastrarOrcamento.dispose();

		}

	}

	private OrcamentoOuContrato guardaDadosDosCamposCopia() {

		OrcamentoOuContrato copiaOrcamento = new OrcamentoOuContrato(
				telaCadastrarOrcamento.getCampoNomeEvento().getText(),
				quebraDataEConverteEmLocalDateTime(telaCadastrarOrcamento.getCampoDataEHoraEvento().getText()),
				telaCadastrarOrcamento.getCampoLocalEvento().getText(),
				telaCadastrarOrcamento.getCampoTamanhoEvento().getText(), orcamentoContrato.getClienteAssociado(),
				orcamentoContrato.isFoiContradoOuNao(), telaCadastrarOrcamento.getCampoValor().getText(),
				orcamentoContrato.isFoiConcluido());

		copiaOrcamento.setFornecedores(orcamentoContrato.getFornecedores());

		return copiaOrcamento;
	}

	public LocalDateTime quebraDataEConverteEmLocalDateTime(String data) {
		String dataSemEspaco = data.replaceAll(" ", "");
		char[] novaData = new char[10];
		char[] hora = new char[5];
		int cont = 0;
		for (int i = 0; i < dataSemEspaco.length(); i++) {
			if (i < 10) {
				novaData[i] = dataSemEspaco.charAt(i);
			} else {
				hora[cont] = dataSemEspaco.charAt(i);
				cont++;
			}

		}
		String dataFinal = String.valueOf(novaData);
		String horaFinal = String.valueOf(hora);

		DateTimeFormatter forma = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(dataFinal + " " + horaFinal, forma);
		return dateTime;
	}

	public class OuvinteBotaoSalvar implements ActionListener {

		private TelaCadastrarOrcamento telaOrcamento;
		private OrcamentoOuContrato orcamentoContrato;

		public OuvinteBotaoSalvar(TelaCadastrarOrcamento telaOrcamento, OrcamentoOuContrato orcamentoContrato) {
			this.telaOrcamento = telaOrcamento;
			this.orcamentoContrato = orcamentoContrato;
		}

		public void actionPerformed(ActionEvent e) {

			String email = telaOrcamento.getCampoEmailCliente().getText();
			String nome = telaOrcamento.getCampoNomeEvento().getText();
			String local = telaOrcamento.getCampoLocalEvento().getText();
			String tamanho = telaOrcamento.getCampoTamanhoEvento().getText();
			String dataEHora = telaOrcamento.getCampoDataEHoraEvento().getText();
			String valor = telaOrcamento.getCampoValor().getText();
			OrcamentoOuContrato orcamento = null;

			LocalDateTime dataAntiga = orcamentoContrato.getDataEHoraDoEvento();
			String emailDoAssociado = orcamentoContrato.getClienteAssociado().getEmail();

			if (telaOrcamento.validaTodosOsCampos(telaOrcamento, email, nome, local, tamanho, dataEHora, valor)) {
				Pessoa clienteAssocidado = ClienteController.getInstance().recuperarClientePorEmail(email);

				LocalDateTime dataEHoraDoEvento = telaOrcamento.quebraDataEConverteEmLocalDateTime(dataEHora);

				ArrayList<String> comentariosFornecedores = new ArrayList();// criado para fazer os comentario dos
																			// fornecedores

				if (contrato.isSelected() && contrato.getText().equals("Promover para Contrato")) {
					orcamentoContrato.setFoiContradoOuNao(true);

					if (!orcamentoContrato.getFornecedores().isEmpty()) {
						
						for (int i = 0; i < orcamentoContrato.getFornecedores().size(); i++) {
							if (orcamentoContrato.getFornecedores().get(i) instanceof FornecedorFisico) {
								
								FornecedorFisico fisico = (FornecedorFisico) orcamentoContrato.getFornecedores().get(i);
								fisico.setQuantContratosFisico(fisico.getQuantContratosFisico() + 1);
							}else {
								FornecedorJuridico juridico = (FornecedorJuridico)orcamentoContrato.getFornecedores().get(i);
								juridico.setQuantContratosJuridico(juridico.getQuantContratosJuridico()+ 1);
							}
							
						}
					} else {					
						for (int i = 0; i < orcamentoContrato.getPacotesDeFornecedores().size(); i++) {							
							Pacote pacote = orcamentoContrato.getPacotesDeFornecedores().get(i);
							
							for (int j = 0; j < pacote.getTodosFornecedore().size();j++) {
								
								if (pacote.getTodosFornecedore().get(j) instanceof FornecedorFisico) {
									
									FornecedorFisico fisico = (FornecedorFisico) pacote.getTodosFornecedore().get(j);
									fisico.setQuantContratosFisico(fisico.getQuantContratosFisico() + 1);
								}else {
									FornecedorJuridico juridico = (FornecedorJuridico)pacote.getTodosFornecedore().get(j);
									juridico.setQuantContratosJuridico(juridico.getQuantContratosJuridico()+ 1);
								}
																
							}
							
						}

					}

				} else if (contrato.isSelected() && contrato.getText().equals("Marcar como concluido")) {
					orcamentoContrato.setFoiContradoOuNao(true);
					orcamentoContrato.setFoiConcluido(true);

					if (!orcamentoContrato.getFornecedores().isEmpty()) { // verifica se sao fornecedores para fazer os
																			// comentarios

						for (Pessoa p : orcamentoContrato.getFornecedores()) {

							String nomeFornecedor = p.getNome();
							String comentario = JOptionPane.showInputDialog(telaOrcamento,
									"Faca um comentario sobre o(a) " + nomeFornecedor);

							if (p instanceof FornecedorFisico) {
								FornecedorFisico fisico = (FornecedorFisico) p;
								fisico.getComentariosFornecedores().add(comentario);

							} else {
								FornecedorJuridico juridico = (FornecedorJuridico) p;
								juridico.getComentariosFornecedores().add(comentario);
							}

						}
					}

				}

				orcamento = new OrcamentoOuContrato(nome, dataEHoraDoEvento, local, tamanho, clienteAssocidado,
						orcamentoContrato.isFoiContradoOuNao(), valor, orcamentoContrato.isFoiConcluido());

				if (!orcamentoContrato.getPacotesDeFornecedores().isEmpty()) {

					orcamento.adicionaPacotesNaLista(orcamentoContrato.getPacotesDeFornecedores());

				} else if (!orcamentoContrato.getFornecedores().isEmpty()) {

					orcamento.adicionaFornecedoresNaLista(todosOsFornecedoresDoOrcamento);

				}

				if (OrcamentoController.getInstance().removerOrcamentoOuContrato(dataAntiga, emailDoAssociado)) {

					if (OrcamentoController.getInstance().adicionarOrcamento(orcamento)) {
						JOptionPane.showMessageDialog(telaOrcamento, "Orçamento editado com sucesso");
						telaOrcamento.dispose();
//						OrcamentoController.getInstance().getFornecedores().clear();
//						OrcamentoController.getInstance().getPacoteFornecedores().clear();
						new TelaMenu("Menu");

					}

				} else {
					JOptionPane.showMessageDialog(telaOrcamento, "Orçamento não encontrado");
				}
			}

		}

	}

}

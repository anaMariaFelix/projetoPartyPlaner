package telas;

import java.awt.Color;
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
import model.OrcamentoOuContrato;
import model.Pacote;
import model.Pessoa;
import telas.TelaListaFornecedoresParaOrcamento.OuvinteBotaoAdicionar;
import util.ButtonEditor;
import util.ButtonRenderer;
import util.ComponentesDeJFrame;

public class AuxTelaEditarOrcamento {

	private OrcamentoOuContrato orcamentoContrato;
	private TelaCadastrarOrcamento telaCadastrarOrcamento;
	private String titulo;

	private DefaultTableModel modelo;
	private JTable tabela;

	private Object[] todosOsFornecedoresDoOrçamento = null;
	private int tamanhoVetor;

	private JCheckBox contrato;
	private JButton incluirFornecedor; // deixei o botao de incluir fornecedores com escopo de instancia para acessa-lo
										// na hora de edita um orcamento
	
	private TelaListaFornecedoresParaOrcamento telaListaFornecedoresParaOrcamento;

	

	public AuxTelaEditarOrcamento(OrcamentoOuContrato orcamentoContrato, String titulo) {
		this.orcamentoContrato = orcamentoContrato;
		this.titulo = titulo;
		configurarTela();// Vou chamar o metodo de adicionar jbutton detro do metodo de configurar
		adicionarTabelaFornecedores();// Pq na hora de deixar o botao invisivel o codigo quebra por caus que a
										// variavel n recebe nada, tbm chamei adicionarCheckBox dentro de configurar pra ele nao quebrar
	}

	private void adicionarJCheckBox() {

		contrato = new JCheckBox("Promover para Contrato");
		contrato.setBounds(380, 430, 190, 100);
		contrato.setFont(new Font("Arial", Font.BOLD, 13));
		telaCadastrarOrcamento.add(contrato);

		// Altero checkboc para marcar como concluido caso seja um contrato, então
		// checkbox para promover não aparece
		if (orcamentoContrato.isFoiContradoOuNao()) {
			contrato.setText("Marcar como concluido");
		}
	}

	private void adicionarJButton() {

		incluirFornecedor = ComponentesDeJFrame.criarBotao("Incluir Fornecedor", 574, 90, 140, 30);
		incluirFornecedor.addActionListener(new OuvinteBotaoIncluirFornecedor());
		telaCadastrarOrcamento.add(incluirFornecedor);
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
		telaCadastrarOrcamento.getBotaoSalvar().addActionListener(new OuvinteBotaoSalvar(telaCadastrarOrcamento));

		telaCadastrarOrcamento.getBotaoAdicionarFornecedores().setVisible(false);

		adicionarJButton();						// adiciono o botao e o check e depois verifico se é um contrato concluido(16)
		adicionarJCheckBox();					// para setar os visible

		if (orcamentoContrato.isFoiConcluido()) {// Se nao for concluido deixa tudo como esta(podendo alterar), se nao
													// deixo os campos inacessiveis (16)

			telaCadastrarOrcamento.getCampoNomeEvento().setEnabled(false);
			telaCadastrarOrcamento.getCampoDataEHoraEvento().setEnabled(false);
			telaCadastrarOrcamento.getCampoLocalEvento().setEnabled(false);
			telaCadastrarOrcamento.getCampoTamanhoEvento().setEnabled(false);
			telaCadastrarOrcamento.getCampoValor().setEnabled(false);

			incluirFornecedor.setVisible(false);// deixei o botao falso para nao podermos colocar fornecedores se for um
			contrato.setVisible(false);									// contrato concluido
		}

	}

	private void adicionarTabelaFornecedores() {
		modelo = new DefaultTableModel();
		modelo.addColumn("Nome");

		tamanhoVetor = 1;

		tabela = new JTable(modelo);

		if (!orcamentoContrato.getFornecedores().isEmpty()) {
			todosOsFornecedoresDoOrçamento = orcamentoContrato.getFornecedores().toArray();
			modelo.addColumn("Excluir");
			tabela.getColumn("Excluir").setCellRenderer(new ButtonRenderer());
			tabela.getColumn("Excluir").setCellEditor(new ButtonEditor(new JCheckBox()));
			tamanhoVetor++;
			if (orcamentoContrato.isFoiConcluido()) {// Se for concluido vou tirar a segunda coluna da tabela, para não
														// aparecer mais os botoes de exluir

				tabela.removeColumn(tabela.getColumnModel().getColumn(1));// Metodo para remover coluna da tabela
				// remove so recebe tableColumn entao passei detro o getColumnModel com o
				// getColmun que pega a coluna especificada 

			}
		} else {
			todosOsFornecedoresDoOrçamento = orcamentoContrato.getPacotesDeFornecedores().toArray();
		}

		JScrollPane painelTabela = new JScrollPane(tabela);
		painelTabela.setBounds(380, 128, 335, 318);
		telaCadastrarOrcamento.add(painelTabela);
		preencherTabela(todosOsFornecedoresDoOrçamento, tamanhoVetor);
	}

	public void preencherTabela(Object[] fornecedoresOrcamento, int tamanho) {
		int indiceExcluir = 0;
		for (Object t : fornecedoresOrcamento) {
			Object[] linha = new Object[tamanho];

			if (t != null) {
				if (t instanceof Pessoa) {
					Pessoa fisicoOuJuridico = (Pessoa) t;
					linha[0] = fisicoOuJuridico.getNome();

					JButton btExcluir = new JButton("Excluir");
					linha[1] = btExcluir;
					btExcluir.setBackground(new Color(39, 228, 86));// !!!!!!!! Ta removendo da tabela so quando volta,
																	// quando salva a tabela volta com os fornecedores
																	// excluidos!!!!!!!!!!!!!!
					btExcluir.addActionListener(new OuvinteBotaoExcluir(fisicoOuJuridico, indiceExcluir));

				} else {
					Pacote pacote = (Pacote) t;
					linha[0] = pacote.getNomeDoPacote();
				}

				modelo.addRow(linha);
				indiceExcluir++;
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

	public String mudarDeLocalDateTimeParaString(LocalDateTime dataEHora) {

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

	public class OuvinteBotaoExcluir implements ActionListener {

		private Pessoa fornecedor;
		private int indiceDoFornecedor;

		public OuvinteBotaoExcluir(Pessoa fornecedor, int indice) {
			this.fornecedor = fornecedor;
			this.indiceDoFornecedor = indice;
		}

		public void actionPerformed(ActionEvent e) {
			todosOsFornecedoresDoOrçamento[indiceDoFornecedor] = null;
			OrcamentoController.getInstance().removerFornecedor(orcamentoContrato, fornecedor);// nao ta imediatamente
																								// removendo o
																								// fornecedor, e quando
																								// remove e volta e
																								// entra dnv ele aparece
																								// removido,
																								// salvar esta dando
																								// certo so nao atualiza
																								// imediatamente
			adicionarTabelaFornecedores();
		}

	}

	public class OuvinteBotaoIncluirFornecedor implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			telaCadastrarOrcamento.dispose();
			telaListaFornecedoresParaOrcamento = new TelaListaFornecedoresParaOrcamento(telaCadastrarOrcamento,
					"Lista de Fornecedores");

		}

	}

	public class OuvinteBotaoSalvar implements ActionListener {

		private TelaCadastrarOrcamento telaOrcamento;

		public OuvinteBotaoSalvar(TelaCadastrarOrcamento telaOrcamento) {
			this.telaOrcamento = telaOrcamento;
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

			ArrayList<Pacote> pacoteFornecedores = orcamentoContrato.getPacotesDeFornecedores(); // OrcamentoController.getInstance().getPacoteFornecedores();
			ArrayList<Pessoa> fornecedores = orcamentoContrato.getFornecedores(); // OrcamentoController.getInstance().getFornecedores();

			if (telaOrcamento.validaTodosOsCampos(telaOrcamento, email, nome, local, tamanho, dataEHora, valor)) {
				Pessoa clienteAssocidado = ClienteController.getInstance().recuperarClientePorEmail(email);

				LocalDateTime dataEHoraDoEvento = telaOrcamento.quebraDataEConverteEmLocalDateTime(dataEHora);

				boolean foiContratado = false;
				boolean foiConcluido = false; //flag criadapra passar no construtor
				ArrayList<String> comentariosFornecedores = new ArrayList();//criado para fazer os comentario dos fornecedores 
				
				if (contrato.isSelected() && contrato.getText().equals("Promover para Contrato")) {// verifica se o
																									// checkbox foi
																									// selecionado e
																									// qual era o nome
																									// dele
					foiContratado = true;
				} else {
					foiContratado = true; //repeti aqui dentro pq se nao linha 302 ficava false e editava de forma errada
					foiConcluido = true;
					
					if (!fornecedores.isEmpty()) { // verifica se sao fornecedores para fazer os comentarios

						for (Pessoa p : fornecedores) {// Onde os comentarios aparecem?? e devemos adicionar isso como
														// atrinuto??(acho que nao, pq ele pode ta em +1 contrato)
							String nomeFornecedor = p.getNome();
							String comentario = JOptionPane.showInputDialog(telaOrcamento,"Faca um comentario sobre o(a) " + nomeFornecedor);// mudar essa mensagem
							comentariosFornecedores.add(nomeFornecedor +" "+ comentario);
						} // Fazer showMessage para adm colocar comentarios nos fornecedores
					}
					// Se nao for atributo onde vai aparecer e pq pedir?
				}
				
			
				orcamento = new OrcamentoOuContrato(nome, dataEHoraDoEvento, local, tamanho, clienteAssocidado,
						foiContratado, valor,foiConcluido);
				
				
				if (!comentariosFornecedores.isEmpty()) {//verifico se lista ta cheia para adicionar os comentarios
					orcamento.setComentariosFornecedores(comentariosFornecedores);
				}
				

				if (!pacoteFornecedores.isEmpty()) { //retirei "fornecedores.isEmpty()" && pq tava sendo desnecessario
					orcamento.adicionaPacotesNaLista(pacoteFornecedores);


				} else if (!fornecedores.isEmpty()) { //retirei "pacoteFornecedores.isEmpty() &&" pq tava sendo desnecessario
					orcamento.adicionaFornecedoresNaLista(fornecedores);

				}

				if (OrcamentoController.getInstance().removerOrcamentoOuContrato(dataAntiga, emailDoAssociado)) {

					if (OrcamentoController.getInstance().adicionarOrcamento(orcamento)) {
						JOptionPane.showMessageDialog(telaOrcamento, "Orçamento editado com sucesso");
						telaOrcamento.dispose();
						new TelaMenu("Menu");
					}
				} else {
					JOptionPane.showMessageDialog(telaOrcamento, "Orçamento não encontrado");
				}
			}

		}

	}

}

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
	
	private TelaListaFornecedoresParaOrcamento telaListaFornecedoresParaOrcamento;

	public AuxTelaEditarOrcamento(OrcamentoOuContrato orcamentoContrato, String titulo) {
		this.orcamentoContrato = orcamentoContrato;
		this.titulo = titulo;
		configurarTela();
		adicionarTabelaFornecedores();
		adicionarJButton();
		adicionarJCheckBox();
	}

	private void adicionarJCheckBox() {
		
		contrato = new JCheckBox("Promover para Contrato");
		contrato.setBounds(380,430,190,100);
		contrato.setFont(new Font("Arial",Font.BOLD,13));
		telaCadastrarOrcamento.add(contrato);
	}

	private void adicionarJButton() {
		
		JButton incluirFornecedor = ComponentesDeJFrame.criarBotao("Incluir Fornecedor",574,90,140,30);
		incluirFornecedor.addActionListener(new OuvinteBotaoIncluirFornecedor());
		telaCadastrarOrcamento.add(incluirFornecedor);
	}

	private void configurarTela() {

		telaCadastrarOrcamento = new TelaCadastrarOrcamento(titulo);
		telaCadastrarOrcamento.getTitulo().setText(titulo);

		telaCadastrarOrcamento.getBotaoSalvar().removeActionListener(telaCadastrarOrcamento.getSalvar());

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

		telaCadastrarOrcamento.getBotaoAdicionarFornecedores().setVisible(false);
		
		telaCadastrarOrcamento.getBotaoVoltar().removeActionListener(telaCadastrarOrcamento.getOuvinteVoltar());
		telaCadastrarOrcamento.getBotaoVoltar().setBounds(50, 535, 110, 30);
		telaCadastrarOrcamento.getBotaoVoltar().addActionListener(new OuvinteBotaoVoltar(telaCadastrarOrcamento));
		
		telaCadastrarOrcamento.getBotaoSalvar().setBounds(220, 535, 110, 30);
		telaCadastrarOrcamento.getBotaoSalvar().addActionListener(new OuvinteBotaoSalvar(telaCadastrarOrcamento));

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
		} else {
			todosOsFornecedoresDoOrçamento = orcamentoContrato.getPacotesDeFornecedores().toArray();
		}

		JScrollPane painelTabela = new JScrollPane(tabela);
		painelTabela.setBounds(380, 128, 335, 318);
		telaCadastrarOrcamento.add(painelTabela);	
		preencherTabela(todosOsFornecedoresDoOrçamento,tamanhoVetor);
	}

	public void preencherTabela(Object[] fornecedoresOrcamento,int tamanho) {
		int indiceExcluir = 0;
		for (Object t : fornecedoresOrcamento) {
			Object[] linha = new Object[tamanho];

			if (t != null) {
				if (t instanceof Pessoa) {
				Pessoa fisicoOuJuridico = (Pessoa) t;
				linha[0] = fisicoOuJuridico.getNome();

				JButton btExcluir = new JButton("Excluir");
				linha[1] = btExcluir;
				btExcluir.setBackground(new Color(39, 228, 86));
				btExcluir.addActionListener(new OuvinteBotaoExcluir(fisicoOuJuridico,indiceExcluir));

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

	public class OuvinteBotaoVoltar implements ActionListener{
		private TelaCadastrarOrcamento janela;
		
		public OuvinteBotaoVoltar(TelaCadastrarOrcamento janela) {
			this.janela = janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			janela.dispose();
			new TelaListarOrcamentosContratos("Lista de Orçamentos/Contratos");
		}
		
		
	}
	
	public class OuvinteBotaoExcluir implements ActionListener{

		private Pessoa fornecedor;
		private int indiceDoFornecedor;
		
		public OuvinteBotaoExcluir(Pessoa fornecedor,int indice) {
			this.fornecedor = fornecedor;
			this.indiceDoFornecedor = indice;
		}
		
		public void actionPerformed(ActionEvent e) {
			todosOsFornecedoresDoOrçamento[indiceDoFornecedor] = null;
			limparTabela();
			preencherTabela(todosOsFornecedoresDoOrçamento,tamanhoVetor);
		}
		
	}
	
	public class OuvinteBotaoIncluirFornecedor implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {
			telaCadastrarOrcamento.dispose();
			telaListaFornecedoresParaOrcamento = new TelaListaFornecedoresParaOrcamento(telaCadastrarOrcamento,"Lista de Fornecedores");
			telaListaFornecedoresParaOrcamento.getFornecedores();
			
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
			
			ArrayList<Pacote> pacoteFornecedores = OrcamentoController.getInstance().getPacoteFornecedores();
			ArrayList<Pessoa> fornecedores = OrcamentoController.getInstance().getFornecedores();

			if (telaOrcamento.validaTodosOsCampos(telaOrcamento, email, nome, local, tamanho, dataEHora, valor)) {
				Pessoa clienteAssocidado = ClienteController.getInstance().recuperarClientePorEmail(email);

				LocalDateTime dataEHoraDoEvento = telaOrcamento.quebraDataEConverteEmLocalDateTime(dataEHora);
				
				boolean foiContratado = false;
				
				if (contrato.isSelected()) {
					foiContratado = true;
				}
				orcamento = new OrcamentoOuContrato(nome, dataEHoraDoEvento, local, tamanho, clienteAssocidado, foiContratado,valor);
				
				
				boolean listaCheia = false;

				if (fornecedores.isEmpty() && !pacoteFornecedores.isEmpty()) {
					orcamento.adicionaPacotesNaLista(pacoteFornecedores);
					listaCheia = true;

				} else if (pacoteFornecedores.isEmpty() && !fornecedores.isEmpty()) {
					orcamento.adicionaFornecedoresNaLista(fornecedores);
					listaCheia = true;
				}
				
				
				
				if (OrcamentoController.getInstance().removerOrcamentoOuContrato(dataAntiga, emailDoAssociado)) {
					
					if (OrcamentoController.getInstance().adicionarOrcamento(orcamento)) {
						JOptionPane.showMessageDialog(telaOrcamento, "Orçamento editado com sucesso");
						telaOrcamento.dispose();
						new TelaMenu("Menu");
					}			
				}else {
					JOptionPane.showMessageDialog(telaOrcamento, "Orçamento não encontrado");
				}
			}

		}

	}
	

}

package telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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

public class AuxTelaEditarOrcamento {

	private OrcamentoOuContrato orcamentoContrato;
	private TelaCadastrarOrcamento telaOrcamento;
	private String titulo;

	private DefaultTableModel modelo;
	private JTable tabela;

	public AuxTelaEditarOrcamento(OrcamentoOuContrato orcamentoContrato, String titulo) {
		this.orcamentoContrato = orcamentoContrato;
		this.titulo = titulo;
		configurarTela();
	}

	private void configurarTela() {

		TelaCadastrarOrcamento janela = new TelaCadastrarOrcamento(titulo);
		janela.getTitulo().setText(titulo);

		janela.getBotaoSalvar().removeActionListener(janela.getSalvar());

		janela.getEmailClienteAssociado().setBounds(50, 100, 100, 30);
		janela.getInformacaoCliente().setBounds(115, 100, 225, 30);
		janela.getCampoEmailCliente().setBounds(50, 130, 280, 30);

		janela.getCampoEmailCliente().setText(orcamentoContrato.getClienteAssociado().getEmail());
		janela.getCampoEmailCliente().setEnabled(false);

		janela.getNomeEvento().setBounds(50, 170, 200, 30);
		janela.getCampoNomeEvento().setText(orcamentoContrato.getNomeDoEvento());
		janela.getCampoNomeEvento().setBounds(50, 200, 280, 30);

		janela.getDataEHoraEvento().setBounds(50, 240, 130, 30);
		janela.getCampoDataEHoraEvento()
				.setText(mudarDeLocalDateTimeParaString(orcamentoContrato.getDataEHoraDoEvento()));
		janela.getCampoDataEHoraEvento().setBounds(50, 270, 280, 30);

		janela.getLocalEvento().setBounds(50, 310, 100, 30);
		janela.getCampoLocalEvento().setText(orcamentoContrato.getLocalDoEvento());
		janela.getCampoLocalEvento().setBounds(50, 340, 280, 30);

		janela.getInformacao().setBounds(140, 385, 225, 20);
		janela.getTamanhoEvento().setBounds(50, 380, 90, 30);
		janela.getCampoTamanhoEvento().setText(orcamentoContrato.getTamanho());
		janela.getCampoTamanhoEvento().setBounds(50, 415, 280, 30);

		janela.getValor().setBounds(50, 450, 330, 35);

		if (!orcamentoContrato.getFornecedores().isEmpty()) {
			janela.getCampoValor().setEnabled(true);
		} else {
			janela.getCampoValor().setEnabled(false);
		}
		janela.getCampoValor().setText(orcamentoContrato.getValor());
		janela.getCampoValor().setBounds(50, 490, 280, 30);

		janela.getBotaoAdicionarFornecedores().setVisible(false);
		janela.getBotaoVoltar().setBounds(50, 535, 110, 30);
		janela.getBotaoSalvar().setBounds(220, 535, 110, 30);

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

			if (telaOrcamento.validaTodosOsCampos(telaOrcamento, email, nome, local, tamanho, dataEHora, valor)) {
				Pessoa clienteAssocidado = ClienteController.getInstance().recuperarClientePorEmail(email);

				LocalDateTime dataEHoraDoEvento = telaOrcamento.quebraDataEConverteEmLocalDateTime(dataEHora);

				orcamento = new OrcamentoOuContrato(nome, dataEHoraDoEvento, local, tamanho, clienteAssocidado, false,
						valor);
			}

		}

	}

	private void adicionarTabelaFornecedores() {
		modelo = new DefaultTableModel();
		modelo.addColumn("Nome");
		modelo.addColumn("Excluir");
		
		Object[] todosOsFornecedoresDoOrçamento = null;
				
		
		if (!OrcamentoController.getInstance().getFornecedores().isEmpty()) {
			todosOsFornecedoresDoOrçamento = OrcamentoController.getInstance().getFornecedores().toArray();
		}else {
			todosOsFornecedoresDoOrçamento = OrcamentoController.getInstance().getPacoteFornecedores().toArray();
		}
		

		tabela = new JTable(modelo);
		tabela.getColumn("Excluir").setCellRenderer(new ButtonRenderer());
		tabela.getColumn("Excluir").setCellEditor(new ButtonEditor(new JCheckBox()));

		JScrollPane painelTabela = new JScrollPane(tabela);
		painelTabela.setBounds(30, 135, 730, 350);
		telaOrcamento.add(painelTabela);
		preencherTabela(todosOsFornecedoresDoOrçamento);
	}

	public void preencherTabela(Object[] fornecedoresOrcamento) {
		limparTabela();
		for (Object t : fornecedoresOrcamento) {
			Object[] linha = new Object[2];
			
			if (t instanceof Pessoa) {
				Pessoa fisicoOuJuridico = (Pessoa)t;
				linha[0] = fisicoOuJuridico.getNome();
			}else {
				Pacote pacote = (Pacote)t;
			} 

			JButton btExcluir = new JButton("Excluir");
			linha[1] = btExcluir;
			btExcluir.setBackground(new Color(39, 228, 86));

			
		
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

	public String mudarDeLocalDateTimeParaString(LocalDateTime dataEHora) {

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		String dataFormatada = dataEHora.format(dateTimeFormatter);

		return dataFormatada;

	}

}

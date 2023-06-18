package telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.OrcamentoOuContrato;
import relatorios.GeradorDeRelatorio;
import util.ComponentesDeJFrame;

public class TelaGerarPDF extends JanelaPadrao {

	private OrcamentoOuContrato orcamentoContrato;

	private JCheckBox checkBoxTodos;
	private JCheckBox checkBoxNome;
	private JCheckBox checkBoxEmail;
	private JCheckBox checBoxData;
	private JCheckBox checBoxTamanho;
	private JCheckBox checBoxValor;
	private JCheckBox checBoxFornecedorePacotes;

	boolean todos = false;
	boolean nome = false;
	boolean email = false;
	boolean data = false;
	boolean tamanho = false;
	boolean valor = false;
	boolean fornecedoresPacotes = false;

	public TelaGerarPDF(String titulo, OrcamentoOuContrato orcamentoContrato) {
		super(titulo);
		this.orcamentoContrato = orcamentoContrato;
		adicionarJlabel();
		adicionarJButton();
		adicionarComboBox();
		setVisible(true);
	}

	private void adicionarJButton() {
		JButton botaoVoltar = ComponentesDeJFrame.criarBotao("Voltar", 230, 545, 125, 30);
		botaoVoltar.addActionListener(new OuvinteBotaoVoltar(orcamentoContrato, this));
		add(botaoVoltar);

		JButton botaoGerarRelatorio = ComponentesDeJFrame.criarBotao("Gerar Relatorio", 425, 545, 125, 30);
		botaoGerarRelatorio.addActionListener(new OuvinteBotaoGerarRelatorio(orcamentoContrato));
		add(botaoGerarRelatorio);
	}

	private void adicionarJlabel() {
		JLabel titulo = ComponentesDeJFrame.criaJLabel("Dados do Relatorio", 0, 50, 800, 50, 30);
		titulo.setHorizontalAlignment(JLabel.CENTER);
		add(titulo);

	}

	private void adicionarComboBox() {

		OuvinteCheckBox ouvinte = new OuvinteCheckBox();
		checkBoxTodos = ComponentesDeJFrame.criaCheckBox("Selecionar Todos", 265, 140, 260, 20, 15);
		checkBoxTodos.addActionListener(ouvinte);
		add(checkBoxTodos);

		checkBoxNome = ComponentesDeJFrame.criaCheckBox("Nome do Orcamento/Contrato", 265, 190, 260, 20, 15);
		checkBoxNome.addActionListener(ouvinte);
		add(checkBoxNome);

		checkBoxEmail = ComponentesDeJFrame.criaCheckBox("Email do cliente", 265, 240, 260, 20, 15);
		checkBoxEmail.addActionListener(ouvinte);
		add(checkBoxEmail);

		checBoxData = ComponentesDeJFrame.criaCheckBox("Data do Orcamento/Contrato", 265, 290, 260, 20, 15);
		checBoxData.addActionListener(ouvinte);
		add(checBoxData);

		checBoxTamanho = ComponentesDeJFrame.criaCheckBox("Tamanho do Orcamento/Contrato", 265, 340, 280, 20, 15);
		checBoxTamanho.addActionListener(ouvinte);
		add(checBoxTamanho);

		checBoxValor = ComponentesDeJFrame.criaCheckBox("Valor do Orcamento/Contrato", 265, 390, 260, 20, 15);
		checBoxValor.addActionListener(ouvinte);
		add(checBoxValor);

		checBoxFornecedorePacotes = ComponentesDeJFrame.criaCheckBox("Fornecedores/Pacotes", 265, 440, 260, 20, 15);
		checBoxFornecedorePacotes.addActionListener(ouvinte);
		add(checBoxFornecedorePacotes);
	}

	public class OuvinteBotaoVoltar implements ActionListener {

		private OrcamentoOuContrato orcamentoOuContrato;
		private TelaGerarPDF janelaPDF;

		public OuvinteBotaoVoltar(OrcamentoOuContrato orcamentoOuContrato, TelaGerarPDF janelaPDF) {
			this.orcamentoOuContrato = orcamentoOuContrato;
			this.janelaPDF = janelaPDF;
		}

		public void actionPerformed(ActionEvent e) {
			janelaPDF.dispose();
			new AuxDetalharOrcamentoContrato(orcamentoOuContrato);

		}

	}

	public class OuvinteCheckBox implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			List<JCheckBox> botoes = Arrays.asList(checkBoxNome, checkBoxEmail, checBoxData, checBoxTamanho,
					checBoxValor, checBoxFornecedorePacotes);

			if (e.getSource() == checkBoxTodos) {
				todos = true;
				if (checkBoxTodos.isSelected()) {
					for (JCheckBox botao : botoes) {
						botao.setSelected(true);
					}
				}
			} else {
				todos = false;
				if (checkBoxNome.isSelected()) {
					nome = true;
				} else {
					nome = false;
				}

				if (checkBoxEmail.isSelected()) {
					email = true;
				} else {
					email = false;
				}

				if (checBoxData.isSelected()) {
					data = true;
				} else {
					data = false;
				}

				if (checBoxTamanho.isSelected()) {
					tamanho = true;
				} else {
					tamanho = false;
				}

				if (checBoxValor.isSelected()) {
					valor = true;
				} else {
					valor = false;
				}

				if (checBoxFornecedorePacotes.isSelected()) {
					fornecedoresPacotes = true;
				} else {
					fornecedoresPacotes = false;
				}
			}

		}

	}

	public class OuvinteBotaoGerarRelatorio implements ActionListener {

		private OrcamentoOuContrato orcamentoOuContrato;

		public OuvinteBotaoGerarRelatorio(OrcamentoOuContrato orcamentoOuContrato) {
			this.orcamentoOuContrato = orcamentoOuContrato;
		}

		public void actionPerformed(ActionEvent e) {

			GeradorDeRelatorio relatorio = new GeradorDeRelatorio();
			relatorio.gerarRelatorioOrcamento(orcamentoContrato, todos, nome, email, data, tamanho, valor,
					fornecedoresPacotes);
			JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso!");
			dispose();
			new AuxDetalharOrcamentoContrato(orcamentoOuContrato);

		}

	}

}

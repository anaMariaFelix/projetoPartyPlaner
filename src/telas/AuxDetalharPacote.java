package telas;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.FornecedorFisico;
import model.FornecedorJuridico;
import model.Pacote;
import model.Pessoa;
import util.ButtonEditor;
import util.ButtonRenderer;
import util.ComponentesDeJFrame;

public class AuxDetalharPacote {
	private Pacote pacote;
	private TelaCadastrarPacotes telaCadastrarPacotes;

	public AuxDetalharPacote(Pacote pacote) {
		this.pacote = pacote;
		detalharPacote(pacote);

	}

	public void detalharPacote(Pacote pacote) {
		telaCadastrarPacotes = new TelaCadastrarPacotes("Detalhar Pacote");
		telaCadastrarPacotes.getBotaoSalvar().setVisible(false);
		telaCadastrarPacotes.getJbFornecedores().setVisible(false);
		telaCadastrarPacotes.getBotaoVoltar().setVisible(false);
		telaCadastrarPacotes.getEscolhaFornecedores().setVisible(false);

		// Modificando/Atribuindo valores aos TextFields
		telaCadastrarPacotes.getCampoNomeDoPacote().setText(pacote.getNomeDoPacote());
		telaCadastrarPacotes.getCampoValorPacote().setText(pacote.getValorDoPacote());
		;
		telaCadastrarPacotes.getTfDescricao().setText(pacote.getDescricao());

		// Modificando os JtextField
		telaCadastrarPacotes.getCampoNomeDoPacote().setBounds(100, 190, 225, 30);
		telaCadastrarPacotes.getCampoValorPacote().setBounds(100, 260, 225, 30);
		telaCadastrarPacotes.getPainel().setBounds(100, 330, 225, 50);

		// Deixa campos inacessiveis
		telaCadastrarPacotes.getCampoNomeDoPacote().setEnabled(false);
		telaCadastrarPacotes.getCampoValorPacote().setEnabled(false);
		telaCadastrarPacotes.getTfDescricao().setEnabled(false);

		// Modificando os JLabel
		telaCadastrarPacotes.getLbTitulo().setBounds(0, 40, 800, 50);
		telaCadastrarPacotes.getLbTitulo().setHorizontalAlignment(JLabel.CENTER);
		
		telaCadastrarPacotes.getNomeDoPacote().setBounds(100, 160, 200, 30);
		telaCadastrarPacotes.getValorPacote().setBounds(100, 230, 200, 30);
		telaCadastrarPacotes.getCaracteristicas().setBounds(100, 300, 200, 30);

		JScrollPane tabela = adicionarTabela(pacote);
		tabela.setBounds(400, 187, 225, 196);
		telaCadastrarPacotes.add(tabela);

		JButton botaoVoltar = ComponentesDeJFrame.criarBotao("Voltar", 499, 400, 125, 35);
		botaoVoltar.addActionListener(new OuvinteBotaoVoltarTelaDeListar());
		telaCadastrarPacotes.add(botaoVoltar);
		
		
	}

	private class OuvinteBotaoVoltarTelaDeListar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			telaCadastrarPacotes.dispose();
			new TelaListarPacotesFornecedores("Lista de Pacotes");

		}

	}

	public JScrollPane adicionarTabela(Pacote pacote) {
		DefaultTableModel modelo = new DefaultTableModel();

		modelo.addColumn("Fornecedores");
		modelo.addColumn("Detalhar");

		JTable tabela = new JTable(modelo);
		tabela.getColumn("Detalhar").setCellRenderer(new ButtonRenderer());
		tabela.getColumn("Detalhar").setCellEditor(new ButtonEditor(new JCheckBox()));

		for (int i = 0; i < pacote.getTodosFornecedore().size(); i++) {
			Object[] linha = new Object[2];

			linha[0] = pacote.getTodosFornecedore().get(i).getNome();

			JButton detalhar = new JButton("Detalhar");
			detalhar.setBackground(new Color(39, 228, 86));
			linha[1] = detalhar;

			modelo.addRow(linha);

			detalhar.addActionListener(new OuvinteBotaoDetalhar(pacote.getTodosFornecedore().get(i)));

		}

		return new JScrollPane(tabela);

	}

	public class OuvinteBotaoDetalhar implements ActionListener {

		private Pessoa fornecedor;

		public OuvinteBotaoDetalhar(Pessoa fornecedor) {
			this.fornecedor = fornecedor;
		}

		public void actionPerformed(ActionEvent e) {
			telaCadastrarPacotes.dispose();
			AuxDetalhaFornecedor auxDetalhaFornecedor = new AuxDetalhaFornecedor();

			if (fornecedor instanceof FornecedorFisico) {
				FornecedorFisico fisico = (FornecedorFisico) fornecedor;
				auxDetalhaFornecedor.detalharFornecedor(fisico.getCpfCnpj());

			} else {
				FornecedorJuridico juridico = (FornecedorJuridico) fornecedor;
				auxDetalhaFornecedor.detalharFornecedor(juridico.getCnpj());
			}

		}

	}

}

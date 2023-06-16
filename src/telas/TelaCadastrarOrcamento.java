package telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controller.ClienteController;
import controller.OrcamentoController;
import model.OrcamentoOuContrato;
import model.Pacote;
import model.Pessoa;
import util.ComponentesDeJFrame;
import util.ValidaEmail;

public class TelaCadastrarOrcamento extends JanelaPadrao {

	private JLabel titulo;
	private JLabel emailClienteAssociado;
	private JLabel informacaoCliente;
	private JLabel nomeEvento;
	private JLabel dataEHoraEvento;
	private JLabel localEvento;
	private JLabel tamanhoEvento;
	private JLabel informacao;
	private JLabel valor;

	private JTextField campoEmailCliente;
	private JTextField campoNomeEvento;
	private JTextField campoDataEHoraEvento;
	private JTextField campoLocalEvento;
	private JTextField campoTamanhoEvento;
	private JTextField campoValor;

	private JButton botaoVoltar;
	private JButton botaoSalvar;
	private JButton botaoAdicionarFornecedores;
	
	private OuvinteBotaoSalvar salvar;
	private OuvinteBotaoVoltar ouvinteVoltar;

	private char[] novaData;

	private TelaListaFornecedoresParaOrcamento telaListaFornecedoresParaOrcamento;
	private TelaListarPacotesParaOrcamento telaListarPacotesParaOrcamento;

	public TelaCadastrarOrcamento(String titulo) {
		super(titulo);
		adicionarJLabel();
		adicionarJTextField();
		adicionarJButton();
		setVisible(true);
	}
	
	public OuvinteBotaoVoltar getOuvinteVoltar() {
		return ouvinteVoltar;
	}

	public OuvinteBotaoSalvar getSalvar() {
		return salvar;
	}

	public JTextField getCampoValor() {
		return campoValor;
	}

	public JTextField getCampoEmailCliente() {
		return campoEmailCliente;
	}

	public JLabel getInformacaoCliente() {
		return informacaoCliente;
	}

	public JLabel getEmailClienteAssociado() {
		return emailClienteAssociado;
	}

	public char[] getNovaData() {
		return novaData;
	}

	public JButton getBotaoVoltar() {
		return botaoVoltar;
	}

	public JButton getBotaoSalvar() {
		return botaoSalvar;
	}

	public JButton getBotaoAdicionarFornecedores() {
		return botaoAdicionarFornecedores;
	}

	public JTextField getCampoNomeEvento() {
		return campoNomeEvento;
	}

	public JTextField getCampoDataEHoraEvento() {
		return campoDataEHoraEvento;
	}

	public JTextField getCampoLocalEvento() {
		return campoLocalEvento;
	}

	public JTextField getCampoTamanhoEvento() {
		return campoTamanhoEvento;
	}

	public JLabel getTitulo() {
		return titulo;
	}

	public JLabel getNomeEvento() {
		return nomeEvento;
	}

	public JLabel getDataEHoraEvento() {
		return dataEHoraEvento;
	}

	public JLabel getLocalEvento() {
		return localEvento;
	}

	public JLabel getTamanhoEvento() {
		return tamanhoEvento;
	}

	public JLabel getInformacao() {
		return informacao;
	}

	public JLabel getValor() {
		return valor;
	}

	private void adicionarJLabel() {
		titulo = ComponentesDeJFrame.criaJLabel("Cadastrar Orçamento", 0, 20, 800, 50, 30);
		titulo.setHorizontalAlignment(JLabel.CENTER);
		add(titulo);

		emailClienteAssociado = ComponentesDeJFrame.criaJLabel("Email", 230, 100, 100, 30, 20);
		add(emailClienteAssociado);

		informacaoCliente = ComponentesDeJFrame.criaJLabel("(clinte associado)", 295, 100, 225, 30, 15);
		informacaoCliente.setFont(new Font("Arial", Font.ITALIC, 15));
		add(informacaoCliente);

		nomeEvento = ComponentesDeJFrame.criaJLabel("Nome Do Evendo", 230, 170, 200, 30, 20);
		add(nomeEvento);

		dataEHoraEvento = ComponentesDeJFrame.criaJLabel("Data e Hora", 230, 240, 130, 30, 20);
		add(dataEHoraEvento);

		localEvento = ComponentesDeJFrame.criaJLabel("Local", 230, 310, 100, 30, 20);
		add(localEvento);

		tamanhoEvento = ComponentesDeJFrame.criaJLabel("Tamanho", 230, 380, 90, 30, 20);
		add(tamanhoEvento);

		informacao = ComponentesDeJFrame.criaJLabel("(Quantidade de convidados)", 330, 380, 225, 30, 15);
		informacao.setFont(new Font("Arial", Font.ITALIC, 15));
		add(informacao);

		valor = ComponentesDeJFrame.criaJLabel("Valor", 230, 490, 225, 30, 20);
		add(valor);

	}

	private void adicionarJTextField() {

		campoEmailCliente = ComponentesDeJFrame.criaJTextField(230, 130, 330, 30);
		add(campoEmailCliente);

		campoNomeEvento = ComponentesDeJFrame.criaJTextField(230, 200, 330, 30);
		add(campoNomeEvento);

		MaskFormatter mascaraDeData;
		try {
			mascaraDeData = new MaskFormatter("##/##/#### ##:##");
			campoDataEHoraEvento = new JFormattedTextField(mascaraDeData);
			campoDataEHoraEvento.setBounds(230, 270, 330, 30);
			add(campoDataEHoraEvento);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		campoLocalEvento = ComponentesDeJFrame.criaJTextField(230, 340, 330, 30);
		add(campoLocalEvento);

		OuvinteCampoTamanhoEValor ouvinte = new OuvinteCampoTamanhoEValor();
		campoTamanhoEvento = ComponentesDeJFrame.criaJTextField(230, 415, 330, 30);
		campoTamanhoEvento.addKeyListener(ouvinte);
		add(campoTamanhoEvento);

		campoValor = ComponentesDeJFrame.criaJTextField(230, 520, 330, 30);
		campoValor.addKeyListener(ouvinte);
		campoValor.setEnabled(false);
		add(campoValor);

	}

	private void adicionarJButton() {
		
		ouvinteVoltar = new OuvinteBotaoVoltar();
		botaoVoltar = ComponentesDeJFrame.criarBotao("Voltar", 230, 560, 150, 30);
		botaoVoltar.addActionListener(ouvinteVoltar);
		add(botaoVoltar);

		salvar = new OuvinteBotaoSalvar(this);
		botaoSalvar = ComponentesDeJFrame.criarBotao("Salvar", 410, 560, 150, 30);
		botaoSalvar.addActionListener(salvar);
		add(botaoSalvar);

		botaoAdicionarFornecedores = ComponentesDeJFrame.criarBotao("Adicionar Fornecedores", 230, 450, 330, 35);
		botaoAdicionarFornecedores.addActionListener(new OuvinteBotaoFornecedores(this));
		add(botaoAdicionarFornecedores);

	}

	private class OuvinteBotaoVoltar implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			OrcamentoController.getInstance().getFornecedores().clear();
			OrcamentoController.getInstance().getPacoteFornecedores().clear();
			dispose();
			new TelaMenu("Tela Menu");
		}
	}

	public class OuvinteCampoTamanhoEValor implements KeyListener {

		public void keyTyped(KeyEvent e) {
			char c = e.getKeyChar();
			if (Character.isLetter(e.getKeyChar()) && c != ' ') {
				e.consume();
			}
		}

		public void keyPressed(KeyEvent e) {
			char c = e.getKeyChar();
			if (Character.isLetter(e.getKeyChar()) && c != ' ')
				;

		}

		public void keyReleased(KeyEvent e) {

		}
	}

	public class OuvinteBotaoFornecedores implements ActionListener {
		private TelaCadastrarOrcamento janela;

		public OuvinteBotaoFornecedores(TelaCadastrarOrcamento janela) {
			this.janela = janela;
		}

		public void actionPerformed(ActionEvent e) {
			
			Object[] opcoes = { "Fornecedores Individuais", "Pacote de Fornecedores" };
			int opcaoEscolhida = JOptionPane.showOptionDialog(null, "Escolha entre:", "Opções",
					JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

			if (opcaoEscolhida == 0) {
				if (OrcamentoController.getInstance().getPacoteFornecedores().isEmpty()) {
					janela.setVisible(false);
					telaListaFornecedoresParaOrcamento = new TelaListaFornecedoresParaOrcamento(false,null,janela,
							"Lista de Fornecedores");

				} else {
					JOptionPane.showMessageDialog(janela,
							"Você já adicionou um(s) pacote\nNão é possivel escolher as duas opções");

				}
			} else if (opcaoEscolhida == 1) {
				if (OrcamentoController.getInstance().getFornecedores().isEmpty()) {
					janela.setVisible(false);
					telaListarPacotesParaOrcamento = new TelaListarPacotesParaOrcamento(janela, "Lista de Pacotes");

				} else {
					JOptionPane.showMessageDialog(janela,
							"Você já adicionou um(s) Fornecedor\nNão é possivel escolher as duas opções");
				}
			}

		}
	}

	public class OuvinteBotaoSalvar implements ActionListener {
		private TelaCadastrarOrcamento janela;

		public OuvinteBotaoSalvar(TelaCadastrarOrcamento janela) {
			this.janela = janela;
		}

		public void actionPerformed(ActionEvent e) {
			
			String email = janela.getCampoEmailCliente().getText();
			String nome = janela.getCampoNomeEvento().getText();
			String local = janela.getCampoLocalEvento().getText();
			String tamanho = janela.getCampoTamanhoEvento().getText();
			String dataEHora = janela.getCampoDataEHoraEvento().getText();
			String valor = janela.getCampoValor().getText();

			OrcamentoOuContrato orcamento = null;

			ArrayList<Pacote> pacoteFornecedores = OrcamentoController.getInstance().getPacoteFornecedores();
			ArrayList<Pessoa> fornecedores = OrcamentoController.getInstance().getFornecedores();


			if (validaTodosOsCampos(janela, email, nome, local, tamanho, dataEHora, valor)) {
				Pessoa clienteAssocidado = ClienteController.getInstance().recuperarClientePorEmail(email);

				LocalDateTime dataEHoraDoEvento = quebraDataEConverteEmLocalDateTime(dataEHora);

				orcamento = new OrcamentoOuContrato(nome, dataEHoraDoEvento, local, tamanho, clienteAssocidado, false,
						valor,false);

				boolean listaCheia = false;

				if (fornecedores.isEmpty() && !pacoteFornecedores.isEmpty()) {
					orcamento.adicionaPacotesNaLista(pacoteFornecedores);
					listaCheia = true;

				} else if (pacoteFornecedores.isEmpty() && !fornecedores.isEmpty()) {
					orcamento.adicionaFornecedoresNaLista(fornecedores);
					listaCheia = true;
				}

				if (listaCheia) {
					if (OrcamentoController.getInstance().adicionarOrcamento(orcamento)) {
						JOptionPane.showMessageDialog(janela, "Orçamento cadastrado com sucesso.");
						dispose();
						OrcamentoController.getInstance().getFornecedores().clear();
						OrcamentoController.getInstance().getPacoteFornecedores().clear();
						new TelaMenu("Menu");
					} else {
						JOptionPane.showMessageDialog(janela, "Orçamento já existente ou Data indisponivel");
					}
				} else {
					JOptionPane.showMessageDialog(janela, "Você deve informar ao menos um Fornecedor/Pacote.");
				}

			}
		}

	}
	public boolean validaTodosOsCampos(TelaCadastrarOrcamento janela, String email, String nome, String local,
			String tamanho, String dataEHora, String valor) {

		String[] data = dataEHora.split(" ");

		if (nome.isEmpty() || local.isEmpty() || tamanho.isEmpty() || dataEHora.isEmpty() || email.isEmpty()
				|| valor.isEmpty()) {
			JOptionPane.showMessageDialog(janela, "Todos os campos devem ser preenchidos");

		} else if (!ValidaEmail.emailValidator(email)) {
			JOptionPane.showMessageDialog(janela, "Email inválido");

		} else if (!verificarSeDataEValida(data[0])) {
			JOptionPane.showMessageDialog(janela, "Data inválida\nInforme uma data válida");

		} else if (!verificarSeHoraEValida(data[1])) {
			JOptionPane.showMessageDialog(janela, "Hora inválida\nInforme uma data/Hora válida");

		} else if (!verificaSeStringContemApenasNumeros(tamanho)) {
			JOptionPane.showMessageDialog(janela, "Tamanho inválido\nInforme apenas números");

		} else if (!verificaSeStringContemApenasNumeros(valor)) {
			JOptionPane.showMessageDialog(janela, "Valor inválido\nInforme apenas números");

		} else if (!ClienteController.getInstance().existeCliente(email)) {
			JOptionPane.showMessageDialog(janela, "Não existe cliente com esse email");
		} else {
			return true;
		}
		return false;
	}
	public LocalDateTime quebraDataEConverteEmLocalDateTime(String data) {
		String dataSemEspaco = data.replaceAll(" ", "");
		novaData = new char[10];
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

	public boolean verificarSeDataEValida(String data) {
		try {
			LocalDate dataNew = LocalDate.parse(data,
					DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT));

			if (dataNew.isBefore(LocalDate.now())) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean verificaSeStringContemApenasNumeros(String tamanho) {
		try {
			float tamanhoF = Float.parseFloat(tamanho);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean verificarSeHoraEValida(String hora) {
		String[] vetor;
		vetor = hora.split(":");
		int[] vetorInt = new int[2];

		if (VerificaFormatoDaHora(hora)) {
			for (int i = 0; i < 2; i++) {
				vetorInt[i] = Integer.parseInt(vetor[i]);
			}

			if (vetorInt[0] >= 0 && vetorInt[0] <= 24) {
				if (vetorInt[1] >= 0 && vetorInt[1] <= 60) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean VerificaFormatoDaHora(String hora) {
		if (hora.charAt(2) == ':') {
			return true;
		}

		return false;
	}
	
	
}

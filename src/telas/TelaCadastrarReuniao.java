package telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controller.AdministradorController;
import controller.ReuniaoController;
import email.Mensageiro;
import model.OrcamentoOuContrato;
import model.Reuniao;
import util.ComponentesDeJFrame;

public class TelaCadastrarReuniao extends JanelaPadrao {

	private OrcamentoOuContrato orcamento;
	private JButton voltar;
	private JButton agendar;

	private JTextField campoDataEHoraEvento;
	private JTextArea descricao;

	public TelaCadastrarReuniao(String titulo, OrcamentoOuContrato orcamento) {
		super(titulo);
		this.orcamento = orcamento;
		adicionarTitulo();
		adicionarJlabel();
		adicionarCampo();
		adicionarBotao();
		setVisible(true);
	}

	private void adicionarBotao() {

		voltar = ComponentesDeJFrame.criarBotao("Voltar", 262, 360, 100, 35);
		voltar.addActionListener(new OuvinteBotaoVoltar(orcamento));
		add(voltar);

		agendar = ComponentesDeJFrame.criarBotao("Agendar", 414, 360, 100, 35);
		agendar.addActionListener(new OuvinteAgendar(orcamento));
		add(agendar);

	}

	private void adicionarTitulo() {
		JLabel titulo = ComponentesDeJFrame.criaJLabel("Cadastrar Reunião", 0, 36, 800, 50, 30);
		titulo.setHorizontalAlignment(JLabel.CENTER);
		add(titulo);

	}

	private void adicionarJlabel() {
		JLabel data = ComponentesDeJFrame.criaJLabel("Data e Hora", 262, 170, 250, 30, 25);
		add(data);

		JLabel descricao = ComponentesDeJFrame.criaJLabel("Detalhes da Reunião", 262, 260, 250, 30, 25);
		add(descricao);

	}

	private void adicionarCampo() {

		MaskFormatter mascaraDeData;
		try {
			mascaraDeData = new MaskFormatter("##/##/#### ##:##");
			campoDataEHoraEvento = new JFormattedTextField(mascaraDeData);
			campoDataEHoraEvento.setBounds(262, 210, 250, 30);
			add(campoDataEHoraEvento);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		descricao = new JTextArea();

		JScrollPane painel = new JScrollPane(descricao);
		painel.setBounds(262, 300, 250, 50);
		add(painel);

		descricao.setLineWrap(true);
		descricao.setWrapStyleWord(true);

	}

	public class OuvinteBotaoVoltar implements ActionListener {

		private OrcamentoOuContrato orcamentoContrato;

		public OuvinteBotaoVoltar(OrcamentoOuContrato orcamentoContrato) {
			this.orcamentoContrato = orcamentoContrato;
		}

		public void actionPerformed(ActionEvent e) {
			dispose();
			new AuxDetalharOrcamentoContrato(orcamentoContrato);

		}
	}

	public class OuvinteAgendar implements ActionListener {

		private OrcamentoOuContrato orcamento;

		public OuvinteAgendar(OrcamentoOuContrato orcamento) {
			this.orcamento = orcamento;
		}

		public void actionPerformed(ActionEvent e) {

			String desc = descricao.getText();
			String data = campoDataEHoraEvento.getText();

			String[] dataHoraSeparado = data.split(" ");

			if (desc.isEmpty() || data.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos");

			} else if (!verificarSeDataEValida(dataHoraSeparado[0])) {
				JOptionPane.showMessageDialog(null, "Data inválida, informe novamente");

			} else if (!verificarSeHoraEValida(dataHoraSeparado[1])) {
				JOptionPane.showMessageDialog(null, "Hora inválida, informe novamente");

			} else {

				Reuniao reuniao = new Reuniao(data, orcamento.getClienteAssociado());

				if (ReuniaoController.getInstance().adicionarReuniao(reuniao)) {
					JOptionPane.showMessageDialog(null, "Reunião agendada com sucesso");
					Mensageiro.enviarEmailParaCliente(AdministradorController.getInstance().obterAdministrador().getEmail(),reuniao , desc,orcamento);
					dispose();
					new AuxDetalharOrcamentoContrato(orcamento);

				} else {
					JOptionPane.showMessageDialog(null, "Horário indisponivel");

				}

			}

		}

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

	public boolean verificarSeHoraEValida(String hora) {
		String[] vetor;
		vetor = hora.split(":");
		int[] vetorInt = new int[2];

		for (int i = 0; i < 2; i++) {
			vetorInt[i] = Integer.parseInt(vetor[i]);
		}

		if (vetorInt[0] >= 0 && vetorInt[0] <= 24) {
			if (vetorInt[1] >= 0 && vetorInt[1] <= 60) {
				return true;
			}
		}

		return false;
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

}

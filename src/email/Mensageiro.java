package email;

import javax.swing.JOptionPane;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import controller.AdministradorController;
import model.OrcamentoOuContrato;
import model.Reuniao;

public class Mensageiro {

	public static void enviarEmailParaCliente(String emailAdministrador, Reuniao reuniao, String msg,
			OrcamentoOuContrato evento) {
		Email email = new SimpleEmail();

		try {
			email.setDebug(true);
			email.setHostName("smtp.gmail.com");
			email.setAuthentication("mensageiro811@gmail.com", "yazgcogoqjxgaxag");
			email.setSSL(true);
			email.addTo(reuniao.getCliente().getEmail());
			email.setFrom("mensageiro811@gmail.com");
			email.setSubject("Reunião sobre o evento " + evento.getNomeDoEvento());
			email.setMsg("Cerimonialista: " + AdministradorController.getInstance().obterAdministrador().getNome() + " "
					+ AdministradorController.getInstance().obterAdministrador().getSobrenome() + "\n" + "\n"
					+ "Data da reuniao: " + reuniao.getDataHora() + "\n" + msg);
			email.send();
			JOptionPane.showMessageDialog(null, "Email enviado com sucesso");

		} catch (EmailException e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel enviar o email");
			System.out.println(e.getMessage());

		}

	}

}

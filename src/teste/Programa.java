package teste;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

import baseDedados.CentralDeInformacoes;
import baseDedados.Persistencia;
import telas.CadastamentoAdministrador;
import telas.JanelaPadrao;
import telas.TelaCadastrarCliente;
import telas.TelaLogin;
import telas.TelaMenu;

public class Programa {

	public static void main(String[] args) {

		
		
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exceptionana 
		} catch (IllegalAccessException e) {
			// handle exception
		}


		CentralDeInformacoes centralDeInformacoes = Persistencia.getInstance().recupearCentral("bancoDeDados");
		
		if (CentralDeInformacoes.getInstance().getAdministrador() != null) {
			TelaLogin telaLogin = new TelaLogin("Tela de Login");
			
		}else {
			JanelaPadrao janela = new CadastamentoAdministrador();
		}
		//TelaCadastrarCliente cd = new TelaCadastrarCliente("Cadastro do Cliente");
		
	}

}

package teste;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import controller.AdministradorController;
import telas.CadastamentoAdministrador;
import telas.JanelaPadrao;
import telas.TelaLogin;
import telas.TelaMenu;
import util.Constantes;

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
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}

		if (AdministradorController.getInstance().obterAdministrador() != null) {
			TelaLogin telaLogin = new TelaLogin(Constantes.TITULO_MENU);

		} else {
			JanelaPadrao janela = new CadastamentoAdministrador();
		}
		
		//new TelaMenu("Menu");
		//new TelaListarOrcamentosContratos("titulo");

	}

}

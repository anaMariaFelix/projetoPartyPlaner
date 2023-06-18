package teste;

import java.awt.Desktop;
import java.io.FileOutputStream;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.UnsupportedLookAndFeelException;

import baseDedados.CentralDeInformacoes;
import baseDedados.Persistencia;
import telas.CadastamentoAdministrador;
import telas.JanelaPadrao;
import telas.TelaListarOrcamentosContratos;
import telas.TelaLogin;
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

		CentralDeInformacoes centralDeInformacoes = Persistencia.getInstance().recupearCentral(Constantes.NOME_ARQUIVO);

//		if (CentralDeInformacoes.getInstance().getAdministrador() != null) {
//			TelaLogin telaLogin = new TelaLogin("Tela de Login");
//
//		} else {
//			JanelaPadrao janela = new CadastamentoAdministrador();
//		}
		
		//new TelaMenu("Menu");
		new TelaListarOrcamentosContratos("titulo");

	}

}

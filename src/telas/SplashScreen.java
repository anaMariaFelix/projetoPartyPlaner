package telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingWorker;

@SuppressWarnings("serial")
public class SplashScreen extends JWindow {
	private static SplashScreen instance;

	private int duration = 10000;

	private SplashScreen() {
	
	}

// Este é um método simples para mostrar uma tela de apresentção

// no centro da tela durante a quantidade de tempo passada no construtor

	public static SplashScreen getInstance() {
		if(instance == null) {
			instance = new SplashScreen();
		}
		return instance;
	}

	public void showSplash() {
		JPanel content = (JPanel) getContentPane();
		content.setBackground(Color.white);

		// Configura a posição e o tamanho da janela
		int width = 700;
		int height = 19;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;
		setBounds(x, y, width, height);

		final JProgressBar pr = new JProgressBar();
		pr.setStringPainted(true);
		pr.setValue(0);
		pr.setSize(new Dimension(100, 23));

		content.add(pr, BorderLayout.NORTH);
		// Torna visível
		setVisible(true);

		final String[] msg = { "Caregando Arquivo", "Verificando Dados", "Examinando Diretorios", "Caregando Arquivo",
				"Verificando Dados", "Examinando Diretorios", "Caregando Arquivo", "Verificando Dados",
				"Examinando Diretorios", "Obrigado por Esperar", "Caregando Arquivo", "Verificando Dados",
				"Examinando Diretorios", "Caregando Arquivo", "Verificando Dados", "Examinando Diretorios",
				"Caregando Arquivo", "Verificando Dados", "Examinando Diretorios", "Caregando Arquivo",
				"Verificando Dados", "Examinando Diretorios", "Caregando Arquivo", "Verificando Dados",
				"Examinando Diretorios", "Caregando Arquivo", "Verificando Dados", "Examinando Diretorios",
				"Caregando Arquivo", "Verificando Dados", "Examinando Diretorios", "Caregando Arquivo",
				"Verificando Dados", "Examinando Diretorios", "Caregando Arquivo", "Verificando Dados",
				"Examinando Diretorios", "Obrigado por Esperar", "Caregando Arquivo", "Verificando Dados",
				"Examinando Diretorios", "Caregando Arquivo", "Verificando Dados", "Examinando Diretorios",
				"Caregando Arquivo", "Verificando Dados", "Examinando Diretorios", "Caregando Arquivo",
				"Verificando Dados", "Examinando Diretorios", "Caregando Arquivo", "Verificando Dados",
				"Examinando Diretorios", "Caregando Arquivo", "Verificando Dados", "Examinando Diretorios",
				"Caregando Arquivo", "Verificando Dados", "Examinando Diretorios", "Caregando Arquivo",
				"Verificando Dados", "Examinando Diretorios", "Caregando Arquivo", "Verificando Dados",
				"Examinando Diretorios", "Obrigado por Esperar", "Caregando Arquivo", "Verificando Dados",
				"Examinando Diretorios", "Caregando Arquivo", "Verificando Dados", "Examinando Diretorios",
				"Caregando Arquivo", "Verificando Dados", "Examinando Diretorios", "Caregando Arquivo",
				"Verificando Dados", "Examinando Diretorios", "Caregando Arquivo", "Verificando Dados",
				"Examinando Diretorios", "Caregando Arquivo", "Verificando Dados", "Examinando Diretorios",
				"Caregando Arquivo", "Verificando Dados", "Examinando Diretorios", "Caregando Arquivo",
				"Verificando Dados", "Examinando Diretorios", "Caregando Arquivo", "Verificando Dados",
				"Examinando Diretorios", "Caregando Arquivo", "Verificando Dados", "Examinando Diretorios",
				"Caregando Arquivo", "Verificando Dados", "Examinando Diretorios", "Obrigado por Esperar"

		};

		final SwingWorker w = new SwingWorker() {

			@Override
			protected Object doInBackground() throws Exception {
				for (int i = 1; i <= 100; i++) {
					try {
						pr.setValue(i);
						pr.setString(i + "% " + msg[i]);
						Thread.sleep(100);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
				return 0;
			}
		};
		// Espera ate que os recursos estejam carregados

		try {
			w.execute();
			Thread.sleep(duration);
		} catch (Exception e) {
		}

		setVisible(false);
	}

	public void showSplashAndExit() {
		showSplash();
	}

}

package telas;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import ouvintes.OuvinteBotaoSalvar;
import util.ComponentesDeJFrame;

public class CadastamentoAdministrador extends JanelaPadrao {
	private JButton botaoSalvar;
	
	private JTextField campoNome;
	private JTextField campoSobrenome;
	private JTextField campoEmail;
	
	private JPasswordField campoSenha;
	private JPasswordField campoConfirmaSenha;

	public CadastamentoAdministrador() {
		super("Cadastramento");
		adicionarTitulo();
		adicionarJLabelRotulos();
		adicionarCampoJTextField();
		adicionarJButton();

		setVisible(true);
	}

	public JButton getBotaoSalvar() {
		return botaoSalvar;
	}

	public void setBotaoSalvar(JButton botaoSalvar) {
		this.botaoSalvar = botaoSalvar;
	}

	public JTextField getCampoSobrenome() {
		return campoSobrenome;
	}

	public JTextField getCampoNome() {
		return campoNome;
	}

	public JTextField getCampoEmail() {
		return campoEmail;
	}

	public JPasswordField getCampoSenha() {
		return campoSenha;
	}

	public JPasswordField getCampoConfirmaSenha() {
		return campoConfirmaSenha;
	}

	private void adicionarTitulo() {

		JLabel lbTitulo = ComponentesDeJFrame.criaJLabel("Cadastro do Administrador",190, 50, 450, 50,30);
		lbTitulo.setHorizontalAlignment(JLabel.CENTER); // alinha o texto no centro
		lbTitulo.setForeground(Color.BLACK); // cor do texto
		add(lbTitulo);
	}

	private class OuvinteDosCampos implements KeyListener {

		public void keyPressed(KeyEvent e) {
			char c = e.getKeyChar();
			if (!Character.isLetter(c) && c != ' ') {
			}
		}

		public void keyReleased(KeyEvent e) {

		}

		public void keyTyped(KeyEvent e) {
			char c = e.getKeyChar();

			if (!Character.isLetter(c) && c != ' ') {
				e.consume();
			}
		}
	}

	private void adicionarJButton() {
		OuvinteBotaoSalvar ouvinte = new OuvinteBotaoSalvar(this);
		botaoSalvar = ComponentesDeJFrame.criarBotao("Salvar",510, 420, 190, 45);
		botaoSalvar.addActionListener(ouvinte);
		add(botaoSalvar);

	}

	private void adicionarJLabelRotulos() {
		JLabel jlNome = ComponentesDeJFrame.criaJLabel("Nome",70, 180, 80, 30, 20);
		add(jlNome);

		JLabel jlSobrenome = ComponentesDeJFrame.criaJLabel("Sobrenome",380, 180, 130, 30, 20);
		add(jlSobrenome);

		JLabel jlEmail = ComponentesDeJFrame.criaJLabel("Email",70, 250, 130, 30, 20);
		add(jlEmail);

		JLabel jlSenha = ComponentesDeJFrame.criaJLabel("Senha",70, 320, 130, 30, 20);
		add(jlSenha);

		JLabel jlConfirmaSenha = ComponentesDeJFrame.criaJLabel("Confirmar Senha",345, 320, 180, 30, 20);
		add(jlConfirmaSenha);

	}

	public void adicionarCampoJTextField() {
		OuvinteDosCampos ouvinte = new OuvinteDosCampos();
		campoNome = ComponentesDeJFrame.criaJTextField(150, 180, 180, 30);
		campoNome.addKeyListener(ouvinte);
		add(campoNome);

		campoSobrenome = ComponentesDeJFrame.criaJTextField(510, 180, 190, 30);
		campoSobrenome.addKeyListener(ouvinte);
		add(campoSobrenome);

		campoEmail = ComponentesDeJFrame.criaJTextField(150, 250, 548, 30);
		add(campoEmail);

		campoSenha = new JPasswordField();
		campoSenha.setBounds(150, 320, 180, 30);
		add(campoSenha);

		campoConfirmaSenha = new JPasswordField();
		campoConfirmaSenha.setBounds(510, 320, 190, 30);
		add(campoConfirmaSenha);
	}

}

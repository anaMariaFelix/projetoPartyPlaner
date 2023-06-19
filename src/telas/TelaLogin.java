package telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import baseDedados.CentralDeInformacoes;
import baseDedados.Persistencia;
import controller.AdministradorController;
import model.Administrador;
import util.ComponentesDeJFrame;
import util.Constantes;


public class TelaLogin extends JanelaPadrao {

	private JTextField campoEmail;
	private JPasswordField campoSenha;
	private JButton botaoEntrar;

	public TelaLogin(String titulo) {
		super(Constantes.TITULO_LOGIN);
		adicionarJLabel();
		adicionarCampoJTextField();
		adicionarJButton();
		setVisible(true);
	}


	public JTextField getCampoEmail() {
		return campoEmail;
	}

	public JPasswordField getCampoSenha() {
		return campoSenha;
	}

	public JButton getBotaoEntrar() {
		return botaoEntrar;
	}

	
	private void adicionarJLabel() {
		ImageIcon icone = new ImageIcon("icones/login.png");
		JLabel lbIcone = new JLabel(icone);
		lbIcone.setBounds(180,50, 150, 50); 
		add(lbIcone);
		
		
		JLabel lbTitulo = ComponentesDeJFrame.criaJLabel("Login Administrador",280, 50, 280, 50,30);
		lbTitulo.setHorizontalAlignment(JLabel.CENTER);
		lbTitulo.setForeground(Color.BLACK);
		lbTitulo.setFont(new Font("Arial", Font.ITALIC, 30));
		add(lbTitulo);
	
		JLabel jlNome =  ComponentesDeJFrame.criaJLabel("Email",200, 190, 160, 35,20);
		add(jlNome);

		JLabel jlSobrenome = ComponentesDeJFrame.criaJLabel("Senha",200, 290, 100, 35,20);
		add(jlSobrenome);

	}

	public void adicionarCampoJTextField() {

		campoEmail = ComponentesDeJFrame.criaJTextField(270, 180, 345, 40);
		add(campoEmail);

		campoSenha = new JPasswordField();
		campoSenha.setBounds(270, 280, 345, 40);
		add(campoSenha);
	}
	
	private void adicionarJButton() {
		OuvinteBotaoEntrar ouvinte = new OuvinteBotaoEntrar();
		botaoEntrar = ComponentesDeJFrame.criarBotao("Entrar",465, 350, 150, 40);
		botaoEntrar.addActionListener(ouvinte);
		add(botaoEntrar);

	}
	
	public class OuvinteBotaoEntrar implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			String campoEmail = getCampoEmail().getText();
			String campoSenha = new String(getCampoSenha().getPassword());
			Administrador adiministrador = (Administrador) AdministradorController.getInstance().obterAdministrador();
			if(campoEmail.isEmpty() || campoSenha.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos","ERRO", JOptionPane.ERROR_MESSAGE);
				
			}else if(!campoEmail.equals(adiministrador.getEmail()) || !campoSenha.equals(adiministrador.getSenha())) {
				JOptionPane.showMessageDialog(null, "Email ou senha inválidos","ERRO", JOptionPane.ERROR_MESSAGE);
			}else {
				dispose();
				TelaMenu telaMenu = new TelaMenu(Constantes.TITULO_MENU);
			}
		}
		
	}
}

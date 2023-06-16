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
import util.ComponentesDeJFrame;


public class TelaLogin extends JanelaPadrao {

	private JTextField campoEmail;
	private JPasswordField campoSenha;
	private JButton botaoEntrar;

	public TelaLogin(String titulo) {
		super("Tela de Login");
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

	
	// tudo para um unico titulo JLabel
	private void adicionarJLabel() {
		ImageIcon icone = new ImageIcon("icones/login.png"); //coloca um icone
		JLabel lbIcone = new JLabel(icone);
		lbIcone.setBounds(180,50, 150, 50); // local e tamanho do texto
		add(lbIcone);
		
		
		JLabel lbTitulo = ComponentesDeJFrame.criaJLabel("Login Administrador",280, 50, 280, 50,30);
		lbTitulo.setHorizontalAlignment(JLabel.CENTER); // alinha o texto no centro
		lbTitulo.setForeground(Color.BLACK); // cor do texto
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

		@Override
		public void actionPerformed(ActionEvent e) {
			
			CentralDeInformacoes  centralDeInformacao = Persistencia.getInstance().recupearCentral("bancoDeDados");
			String campoEmail = getCampoEmail().getText();
			String campoSenha = new String(getCampoSenha().getPassword());
			
			if(campoEmail.isEmpty() || campoSenha.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos","ERRO", JOptionPane.ERROR_MESSAGE);
				
			}else if(!campoEmail.equals(centralDeInformacao.getAdministrador().getEmail()) || !campoSenha.equals(centralDeInformacao.getAdministrador().getSenha())) {
				JOptionPane.showMessageDialog(null, "Email ou senha inválidos","ERRO", JOptionPane.ERROR_MESSAGE);
			}else {
				dispose();
				TelaMenu telaMenu = new TelaMenu("Tela Menu");
			}
		}
		
	}
}

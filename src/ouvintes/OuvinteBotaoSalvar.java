package ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import baseDedados.CentralDeInformacoes;
import baseDedados.Persistencia;
import model.Administrador;
import telas.CadastamentoAdministrador;
import telas.TelaLogin;
import util.ValidaEmail;

public class OuvinteBotaoSalvar implements ActionListener {
	private CadastamentoAdministrador janela;
	

	public OuvinteBotaoSalvar(CadastamentoAdministrador janela) {
		this.janela = janela;
		

	}

	public void actionPerformed(ActionEvent e) {
		String nome = janela.getCampoNome().getText();
		String sobrenome = janela.getCampoSobrenome().getText();
		String email = janela.getCampoEmail().getText();
		String senha = new String(janela.getCampoSenha().getPassword());
		String confirmacaoSenha = new String(janela.getCampoConfirmaSenha().getPassword());

		if (nome.isEmpty() || sobrenome.isEmpty() || senha.isEmpty() || confirmacaoSenha.isEmpty() || email.isEmpty()) {
			JOptionPane.showMessageDialog(janela, "Todos os campos devem ser preenchidos","ERRO", JOptionPane.ERROR_MESSAGE);

		} else if (!ValidaEmail.emailValidator(email)) {
			JOptionPane.showMessageDialog(janela, "Email inválido.\nInforme um email válido","ERRO", JOptionPane.ERROR_MESSAGE);

		} else if (!confirmacaoSenha.equals(senha)) {
			JOptionPane.showMessageDialog(janela, "Confirmação de senha inválida.\nAs senhas devem ser iguais","ERRO", JOptionPane.ERROR_MESSAGE);
			
		}else if(senha.length() < 8){
			JOptionPane.showMessageDialog(janela, "A Senha deve ter no minimo 8 caracteres","ERRO", JOptionPane.ERROR_MESSAGE);
			
		}else {
			Administrador administrador = new Administrador(nome, sobrenome, email, senha);
			
			CentralDeInformacoes.getInstance().setAdministrador(administrador);
			
			Persistencia.getInstance().salvarCentral(CentralDeInformacoes.getInstance(), "bancoDeDados");
			
			JOptionPane.showMessageDialog(janela, "Administrador cadastrado com sucesso","Cadastramento", JOptionPane.INFORMATION_MESSAGE);
			janela.dispose();
			TelaLogin telaLogin = new TelaLogin("Tela de Login");
			
		}
		

	}

}

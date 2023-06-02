package model;

public class Administrador extends Pessoa{
	
	private String senha;

	
	public Administrador(String nome,String sobrenome,String email, String senha) {
		super(nome, sobrenome,null,email);
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	

}

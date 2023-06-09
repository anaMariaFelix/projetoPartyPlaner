package model;

public class Pessoa implements Comparable<Pessoa>{

	private String nome;
	private String sobrenome;
	private String telefone;
	private String email;
	
	
	public Pessoa(String nome, String sobrenome, String telefone,String email) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.telefone = telefone;
		this.email = email;
	}
	
	
	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public int compareTo(Pessoa o) {
		return this.nome.compareTo(o.getNome());
	}
	
	
}

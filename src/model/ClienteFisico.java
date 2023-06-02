package model;

public class ClienteFisico extends Pessoa{
	
	private String CPF;

	public ClienteFisico(String nome, String sobrenome,String telefone, String email,String cpf) {
		super(nome, sobrenome,telefone, email);
		this.CPF = cpf;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}
	
	

}

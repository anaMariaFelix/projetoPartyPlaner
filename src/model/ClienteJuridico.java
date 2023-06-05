package model;

public class ClienteJuridico extends Pessoa{

	private String CNPJ;
	
	
	public ClienteJuridico(String nome, String sobrenome,String telefone, String email,String cnpj) {
		super(nome, sobrenome,telefone, email);
		this.CNPJ = cnpj;
	}
	
	


	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

	

}

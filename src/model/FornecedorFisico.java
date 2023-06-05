package model;

import java.util.ArrayList;

public class FornecedorFisico extends Pessoa{
	private String cpf;
	private ArrayList<String> servicos = new ArrayList();
	private int quantContratosFisico = 0;
	
	
	
	public FornecedorFisico(String nome, String sobrenome, String telefone, String cpf,String email,ArrayList<String> servicos) {
		super(nome, null, telefone, email);
		this.cpf = cpf;
		adicionaServicosNaLista(servicos);
		
	}
	
	

	public int getQuantContratosFisico() {
		return quantContratosFisico;
	}



	public void setQuantContratosFisico(int quantContratosFisico) {
		this.quantContratosFisico = quantContratosFisico;
	}



	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public ArrayList<String> getServicos() {
		return servicos;
	}

	public void setServicos(ArrayList<String> servicos) {
		this.servicos = servicos;
	}
	
	private void adicionaServicosNaLista(ArrayList<String> servicos) {
		for(String servico: servicos) {
			this.servicos.add(servico);
		}
	}
	
}

package model;

import java.util.ArrayList;

public class FornecedorJuridico extends Pessoa{
	private String cnpj;
	private ArrayList<String> servicos = new ArrayList();
	
	
	public FornecedorJuridico(String nome, String sobrenome, String telefone, String email,String cnpj,ArrayList<String> servicos) {
		super(nome, null, telefone, email);
		this.cnpj = cnpj;
		adicionaServicosNaLista(servicos);
	}
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCpf(String cnpj) {
		this.cnpj = cnpj;
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

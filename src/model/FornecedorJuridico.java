package model;

import java.util.ArrayList;

public class FornecedorJuridico extends Pessoa{
	private String cnpj;
	private ArrayList<String> servicos = new ArrayList();
	private int quantContratosJuridico = 0;
	private boolean disponibilidade ;
	private String motivoIndisponibilidade;
	public ArrayList<String> comentariosFornecedores = new ArrayList(); 
	
	
	public FornecedorJuridico(String nome, String sobrenome, String telefone, String email,String cnpj,ArrayList<String> servicos, boolean disponibilidade) {
		super(nome, null, telefone, email);
		this.cnpj = cnpj;
		this.disponibilidade = disponibilidade;
		adicionaServicosNaLista(servicos);
	}

	public ArrayList<String> getComentariosFornecedores() {
		return comentariosFornecedores;
	}

	public void setComentariosFornecedores(ArrayList<String> comentariosFornecedores) {
		this.comentariosFornecedores = comentariosFornecedores;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public boolean isDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}


	public String getMotivoIndisponibilidade() {
		return motivoIndisponibilidade;
	}




	public void setMotivoIndisponibilidade(String motivoIndisponibilidade) {
		this.motivoIndisponibilidade = motivoIndisponibilidade;
	}




	public int getQuantContratosJuridico() {
		return quantContratosJuridico;
	}

	public void setQuantContratosJuridico(int quantContratosJuridico) {
		this.quantContratosJuridico = quantContratosJuridico;
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
	
	public String toString() {
		return getNome() + " CNPJ " + getCnpj();
	}
	
}

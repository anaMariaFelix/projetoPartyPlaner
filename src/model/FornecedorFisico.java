package model;

import java.util.ArrayList;

public class FornecedorFisico extends Pessoa{
	private String cpfCnpj;
	private ArrayList<String> servicos = new ArrayList();
	private int quantContratosFisico = 0;
	private boolean disponibilidade ;
	private String motivoIndisponibilidade;
	
	
	public FornecedorFisico(String nome, String sobrenome, String telefone, String cpfCnpj,String email,ArrayList<String> servicos) {
		super(nome, null, telefone, email);
		this.cpfCnpj = cpfCnpj;
		this.disponibilidade = true;
		adicionaServicosNaLista(servicos);
		
	}
	
	
	public String getMotivoIndisponibilidade() {
		return motivoIndisponibilidade;
	}

	public void setMotivoIndisponibilidade(String motivoIndisponibilidade) {
		this.motivoIndisponibilidade = motivoIndisponibilidade;
	}

	public boolean isDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}



	public int getQuantContratosFisico() {
		return quantContratosFisico;
	}



	public void setQuantContratosFisico(int quantContratosFisico) {
		this.quantContratosFisico = quantContratosFisico;
	}



	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
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
		return getNome() + " CPF " + getCpfCnpj();
	}
	
	
}

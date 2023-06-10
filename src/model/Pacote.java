package model;

import java.util.ArrayList;

public class Pacote {
	private String nomeDoPacote;
	private String valorDoPacote;
	private String descricao;
	private String disponibilidade;
	private ArrayList<Pessoa> todosFornecedore = new ArrayList();
	
	public Pacote(String nomeDoPacote, String valorDoPacote, String descricao, ArrayList<Pessoa> todosFornecedore) {
		this.nomeDoPacote = nomeDoPacote;
		this.valorDoPacote = valorDoPacote;
		this.descricao = descricao;
		this.disponibilidade = "Disponivel";
		this.todosFornecedore = todosFornecedore;
	}

	public String getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(String disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public String getNomeDoPacote() {
		return nomeDoPacote;
	}

	public void setNomeDoPacote(String nomeDoPacote) {
		this.nomeDoPacote = nomeDoPacote;
	}

	public String getValorDoPacote() {
		return valorDoPacote;
	}

	public void setValorDoPacote(String valorDoPacote) {
		this.valorDoPacote = valorDoPacote;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ArrayList<Pessoa> getTodosFornecedore() {
		return todosFornecedore;
	}

	public void setTodosFornecedore(ArrayList<Pessoa> todosFornecedore) {
		this.todosFornecedore = todosFornecedore;
	}
	
	
	
	
	
	
	

}

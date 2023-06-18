package model;

import java.util.ArrayList;

public class Reuniao {

	private String dataHora;
	private ArrayList<String> ataReuniao = new ArrayList();
	private Pessoa cliente;
	private boolean concluido = false;
	
	
	public Reuniao(String dataHora, Pessoa cliente) {
		this.dataHora = dataHora;
		this.cliente = cliente;
	}
	
	public boolean isConcluido() {
		return concluido;
	}
	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}
	public Pessoa getCliente() {
		return cliente;
	}
	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}
	public String getDataHora() {
		return dataHora;
	}
	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	public ArrayList<String> getAtaReuniao() {
		return ataReuniao;
	}

	public void setAtaReuniao(ArrayList<String> ataReuniao) {
		this.ataReuniao = ataReuniao;
	}
	
	
	
	
	
}

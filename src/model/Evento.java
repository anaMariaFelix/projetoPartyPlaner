package model;

import java.time.LocalDateTime;

public class Evento {
	private long id;
	private String nomeDoEvento;
	private LocalDateTime dataEHoraDoEvento;
	private String localDoEvento;
	private Pessoa clienteAssociado;
	private boolean foiContradoOuNao;
	
	public Evento() {
		id = System.currentTimeMillis();
	}
	
	public Evento(String nome, LocalDateTime dataHora, String local, Pessoa clienteAssociado,
			boolean foiContradoOuNao) {
		this();
		this.nomeDoEvento = nome;
		this.dataEHoraDoEvento = dataHora;
		this.localDoEvento = local;
		this.clienteAssociado = clienteAssociado;
		this.foiContradoOuNao = foiContradoOuNao;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nomeDoEvento;
	}
	public void setNome(String nome) {
		this.nomeDoEvento = nome;
	}
	public LocalDateTime getDataHora() {
		return dataEHoraDoEvento;
	}
	public void setDataHora(LocalDateTime dataHora) {
		this.dataEHoraDoEvento = dataHora;
	}
	public String getLocal() {
		return localDoEvento;
	}
	public void setLocal(String local) {
		this.localDoEvento = local;
	}
	public Pessoa getClienteAssociado() {
		return clienteAssociado;
	}
	public void setClienteAssociado(Pessoa clienteAssociado) {
		this.clienteAssociado = clienteAssociado;
	}
	
	public boolean getFoiContradoOuNao() {
		return foiContradoOuNao;
	}
	
	public void setFoiContradoOuNao(boolean foiContradoOuNao) {
		this.foiContradoOuNao = foiContradoOuNao;
	}
	
	public boolean jaOcorreu() {
		LocalDateTime dataHoraAtual = LocalDateTime.now();
		if(dataHoraAtual.isAfter(dataEHoraDoEvento)) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		if(jaOcorreu()) {
			return clienteAssociado.getNome()+" convida para o seu "+nomeDoEvento+ 
					" Realizado em "+dataEHoraDoEvento+" em "+localDoEvento;
		}
		return  clienteAssociado.getNome()+" convida para o seu "+nomeDoEvento+ 
				" a ser Realizado em "+dataEHoraDoEvento+" em "+localDoEvento;
	}
	
	@Override	
	public boolean equals(Object obj) {
		Evento evento = (Evento) obj;
		if(id == evento.id) {
			return true;
		}
		return false;
		
	}

	
	

}

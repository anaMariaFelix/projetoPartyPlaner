package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrcamentoOuContrato {
	private Pessoa clienteAssociado;
	private String nomeDoEvento;
	private LocalDateTime dataEHoraDoEvento;
	private String localDoEvento;
	private String tamanho;
	private boolean foiContradoOuNao;
	private String valor;
	private ArrayList<Pessoa> fornecedores = new ArrayList<>();
	private ArrayList<Pacote> pacotesDeFornecedores = new ArrayList<>();
	
	
	public OrcamentoOuContrato(String nome, LocalDateTime dataHora, String local,String tamanho,Pessoa clienteAssociado,boolean foiContradoOuNao) {
		this.nomeDoEvento = nome;
		this.dataEHoraDoEvento = dataHora;
		this.localDoEvento = local;
		this.tamanho = tamanho;
		this.clienteAssociado = clienteAssociado;
		this.foiContradoOuNao = foiContradoOuNao;
	}
	
	public Pessoa getClienteAssociado() {
		return clienteAssociado;
	}

	public void setClienteAssociado(Pessoa clienteAssociado) {
		this.clienteAssociado = clienteAssociado;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public String getNomeDoEvento() {
		return nomeDoEvento;
	}

	public void setNomeDoEvento(String nomeDoEvento) {
		this.nomeDoEvento = nomeDoEvento;
	}

	public LocalDateTime getDataEHoraDoEvento() {
		return dataEHoraDoEvento;
	}
	public void setDataEHoraDoEvento(LocalDateTime dataEHoraDoEvento) {
		this.dataEHoraDoEvento = dataEHoraDoEvento;
	}

	public String getLocalDoEvento() {
		return localDoEvento;
	}
	public void setLocalDoEvento(String localDoEvento) {
		this.localDoEvento = localDoEvento;
	}
	public boolean isFoiContradoOuNao() {
		return foiContradoOuNao;
	}
	public void setFoiContradoOuNao(boolean foiContradoOuNao) {
		this.foiContradoOuNao = foiContradoOuNao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public ArrayList<Pessoa> getFornecedores() {
		return fornecedores;
	}
	
	public void setFornecedores(ArrayList<Pessoa> fornecedores) {
		this.fornecedores = fornecedores;
	}
	
	public ArrayList<Pacote> getPacotesDeFornecedores() {
		return pacotesDeFornecedores;
	}
	
	public void setPacotesDeFornecedores(ArrayList<Pacote> pacotesDeFornecedores) {
		this.pacotesDeFornecedores = pacotesDeFornecedores;
	}
	
	public void adicionaPacotesNaLista(ArrayList<Pacote> pacote) {
		for(Pacote p: pacote) {
			this.pacotesDeFornecedores.add(p);
		}
	}
	
	public void adicionaFornecedoresNaLista(ArrayList<Pessoa> fornecedores) {
		for(Pessoa p: fornecedores) {
			this.fornecedores.add(p);
		}
	}
	
	
	
	


	
	

}

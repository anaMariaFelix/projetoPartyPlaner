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
	public boolean foiConcluido; //Adicionei esse atributo pq precimaos saber se ele foi concluido para nao deixarmos alterar mais nada do contrato. 16
	public ArrayList<String> comentariosFornecedores = new ArrayList(); //Fiz esse atributo para os comentarios dos fornecedores ficarem no evento
	private ArrayList<Pessoa> fornecedores = new ArrayList<>();
	private ArrayList<Pacote> pacotesDeFornecedores = new ArrayList<>();
	
	//adicionei no construtor o foiConcluido para utilizar a mesma logida do foiContratado
	public OrcamentoOuContrato(String nome, LocalDateTime dataHora, String local,String tamanho,Pessoa clienteAssociado,boolean foiContradoOuNao,String valor,boolean foiConcluido) {
		this.nomeDoEvento = nome;
		this.dataEHoraDoEvento = dataHora;
		this.localDoEvento = local;
		this.tamanho = tamanho;
		this.clienteAssociado = clienteAssociado;
		this.foiContradoOuNao = foiContradoOuNao;
		this.valor = valor;
		this.foiConcluido = foiConcluido;
	}
	
	public ArrayList<String> getComentariosFornecedores() {
		return comentariosFornecedores;
	}

	public void setComentariosFornecedores(ArrayList<String> comentariosFornecedores) {
		this.comentariosFornecedores = comentariosFornecedores;
	}

	public boolean isFoiConcluido() {
		return foiConcluido;
	}

	public void setFoiConcluido(boolean foiConcluido) {
		this.foiConcluido = foiConcluido;
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
	
	public void adicionaFornecedoresNaLista(ArrayList<Pessoa> fornecedoresNovos) {
		for(Pessoa p: fornecedoresNovos) {
			if (!fornecedores.contains(p)) {
				this.fornecedores.add(p);
			}	
		}
	}
	public void adicionaFornecedoresNaLista(Object[] vetor) {
		for(Object p: vetor) {
			if (!fornecedores.contains(p)) {
				this.fornecedores.add((Pessoa)p);
			}	
		}
	}
	
	
	
	


	
	

}

package controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import baseDedados.CentralDeInformacoes;
import baseDedados.Persistencia;
import model.OrcamentoOuContrato;
import model.Pacote;
import model.Pessoa;
import util.Constantes;

public class OrcamentoController {
	private static OrcamentoController instance;
	
	private ArrayList<Pacote> pacoteFornecedores = new ArrayList();
	private ArrayList<Pessoa> fornecedores = new ArrayList();
	
	private OrcamentoController() {
		
	}

	public static OrcamentoController getInstance() {
		if(instance == null) {
			instance = new OrcamentoController();
		}
		return instance;
	}
	
	public ArrayList<Pacote> getPacoteFornecedores() {
		return pacoteFornecedores;
	}

	public void setPacoteFornecedores(ArrayList<Pacote> pacoteFornecedores) {
		this.pacoteFornecedores = pacoteFornecedores;
	}

	public ArrayList<Pessoa> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(ArrayList<Pessoa> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public boolean adicionarOrcamento(OrcamentoOuContrato orcamento) {
		LocalDateTime dataHoraAtual = LocalDateTime.now();
		if(!existeOrcamentoNessaData(orcamento.getDataEHoraDoEvento()) && orcamento.getDataEHoraDoEvento().isAfter(dataHoraAtual)) {
			CentralDeInformacoes.getInstance().getTodosEvento().add(orcamento);
			Persistencia.getInstance().salvarCentral(CentralDeInformacoes.getInstance(), Constantes.NOME_ARQUIVO);
			return true;
			
		}
		return false;
	}
	
	public boolean existeOrcamentoNessaData(LocalDateTime data) { 
		for(OrcamentoOuContrato o: CentralDeInformacoes.getInstance().getTodosEvento()) {
			if(o.getDataEHoraDoEvento().equals(data)){
				return true;
			}
		}
		return false;
	}
	
	public void populaArrayFornecedores(ArrayList<Pessoa> fornecedor) {
		for(Pessoa p: fornecedor) {
			fornecedores.add(p);
		}
		
	}
	
	public void populaArrayPacotes(ArrayList<Pacote> pacote) {
		for(Pacote p: pacote) {
			pacoteFornecedores.add(p);
		}
		
	}
		

//	public boolean jaOcorreu() {
//		LocalDateTime dataHoraAtual = LocalDateTime.now();
//		if(dataHoraAtual.isAfter(dataEHoraDoEvento)) {
//			return true;
//		}
//		return false;
//	}

	
	
	
}

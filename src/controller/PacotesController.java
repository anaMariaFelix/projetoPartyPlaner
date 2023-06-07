package controller;

import java.util.ArrayList;

import baseDedados.CentralDeInformacoes;
import baseDedados.Persistencia;
import model.Pacote;
import util.Constantes;

public class PacotesController {
	
	private static PacotesController instance;
	
	private PacotesController() {
		
	}
	
	public static PacotesController getInstance() {
		if(instance == null) {
			instance = new PacotesController();
		}
		return instance;
	}
	
	public static boolean adicionarPacote(Pacote pacote) {
		if(!existePacote(pacote.getNomeDoPacote())) {
			CentralDeInformacoes.getInstance().getTodosOsPacotes().add(pacote);
			Persistencia.getInstance().salvarCentral(CentralDeInformacoes.getInstance(), Constantes.NOME_ARQUIVO);
			return true;
		}
		return false;
	}
	
	public static boolean existePacote(String nome) { 
		for(Pacote c: CentralDeInformacoes.getInstance().getTodosOsPacotes()) {
			if(c.getNomeDoPacote().equals(nome)){
				return true;
			}
		}
		return false;
	}
	
	public static ArrayList<Pacote> obterListaDePacote(){
		return CentralDeInformacoes.getInstance().getTodosOsPacotes();
	}
	
	public boolean removerPacote(Pacote pacote) {
		if(existePacote(pacote.getNomeDoPacote())) {
			CentralDeInformacoes.getInstance().getTodosOsPacotes().remove(pacote);
			Persistencia.getInstance().salvarCentral(CentralDeInformacoes.getInstance(), Constantes.NOME_ARQUIVO);
			return true;
		}
		
		return false;
	}
	

}

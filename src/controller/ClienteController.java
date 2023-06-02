package controller;

import baseDedados.CentralDeInformacoes;
import baseDedados.Persistencia;
import model.Pessoa;
import util.Constantes;

public class ClienteController {
	
	private static ClienteController instance;
	
	
	private ClienteController() {
	
	}
	
	public static ClienteController getInstance() {
		if (instance == null) {
			instance = new ClienteController();
		}
		return instance;
	}
	
	public static boolean adicionarCliente(Pessoa pessoa) {
		if(! existeCliente(pessoa.getEmail())) {
			CentralDeInformacoes.getInstance().getTodosOsClientes().add(pessoa);
			Persistencia.getInstance().salvarCentral(CentralDeInformacoes.getInstance(), Constantes.NOME_ARQUIVO);
			return true;
		}
		return false;
	}
	
	public static Pessoa recuperarClientePorEmail(String email) {
		for(Pessoa cliente: CentralDeInformacoes.getInstance().getTodosOsClientes()) { 
			if(cliente.getEmail().equals(email)){
				return cliente;
			}
		}
		return null;
	}
	
	public static boolean existeCliente(String email) { 
		for(Pessoa c: CentralDeInformacoes.getInstance().getTodosOsClientes()) {
			if(c.getEmail().equals(email)){
				return true;
			}
		}
		return false;
	}
	
}

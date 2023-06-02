package controller;

import baseDedados.CentralDeInformacoes;
import baseDedados.Persistencia;
import model.Pessoa;
import util.Constantes;

public class FornecedorController {

	private static FornecedorController instance;
	
	
	private FornecedorController() {
	
	}
	
	public static FornecedorController getInstance() {
		if (instance == null) {
			instance = new FornecedorController();
		}
		return instance;
	}
	
	public static boolean adicionarFornecedor(Pessoa pessoa) {
		if(!existeFornecedor(pessoa.getEmail())) {
			CentralDeInformacoes.getInstance().getTodosOsFornecedores().add(pessoa);
			Persistencia.getInstance().salvarCentral(CentralDeInformacoes.getInstance(), Constantes.NOME_ARQUIVO);
			return true;
		}
		return false;
	}
	
	public static Pessoa recuperarFornecedorPorEmail(String email) {
		for(Pessoa fornecedor: CentralDeInformacoes.getInstance().getTodosOsFornecedores()) { 
			if(fornecedor.getEmail().equals(email)){
				return fornecedor;
			}
		}
		return null;
	}
	
	public static boolean existeFornecedor(String email) { 
		for(Pessoa c: CentralDeInformacoes.getInstance().getTodosOsFornecedores()) {
			if(c.getEmail().equals(email)){
				return true;
			}
		}
		return false;
	}
	
}

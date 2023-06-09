package controller;

import java.util.ArrayList;

import baseDedados.CentralDeInformacoes;
import baseDedados.Persistencia;
import model.FornecedorFisico;
import model.FornecedorJuridico;
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

	public  boolean adicionarFornecedor(Pessoa pessoa) {
		if(pessoa instanceof FornecedorFisico) {
			FornecedorFisico fisico = (FornecedorFisico) pessoa;
			if(!existeFornecedorCpfCnpj(fisico.getCpfCnpj())) {
				CentralDeInformacoes.getInstance().getTodosOsFornecedores().add(fisico);
				Persistencia.getInstance().salvarCentral(CentralDeInformacoes.getInstance(), Constantes.NOME_ARQUIVO);
				return true;
			}
			
		}else {
			FornecedorJuridico juridico = (FornecedorJuridico) pessoa;
			if(!existeFornecedorCpfCnpj(juridico.getCnpj())) {
				CentralDeInformacoes.getInstance().getTodosOsFornecedores().add(juridico);
				Persistencia.getInstance().salvarCentral(CentralDeInformacoes.getInstance(), Constantes.NOME_ARQUIVO);
				return true;
			}
			
		}

		return false;

	}

	public  Pessoa recuperarFornecedorPorCpfOuCnpj(String cpfCnpj) {
		for(Pessoa fornecedor: CentralDeInformacoes.getInstance().getTodosOsFornecedores()) { 
			if(fornecedor instanceof FornecedorFisico){
				FornecedorFisico fisico = (FornecedorFisico) fornecedor;
				if(fisico.getCpfCnpj().equals(cpfCnpj))
					return fisico;
			}else {
				FornecedorJuridico juridico = (FornecedorJuridico) fornecedor;
				if(juridico.getCnpj().equals(cpfCnpj)) {
					return juridico;
				}
			}
		}
		return null;
	}

	public  boolean existeFornecedorEmail(String email) { 
		for(Pessoa c: CentralDeInformacoes.getInstance().getTodosOsFornecedores()) {
			if(c.getEmail().equals(email)){
				return true;
			}
		}
		return false;
	}

	public  boolean existeFornecedorCpfCnpj(String cpfCnpj) { 
		for(Pessoa c: CentralDeInformacoes.getInstance().getTodosOsFornecedores()) {
			if(c instanceof FornecedorFisico) {
				FornecedorFisico fisico = (FornecedorFisico) c;
				if(fisico.getCpfCnpj().equals(cpfCnpj)){
					return true;
				}
			}else {
				FornecedorJuridico juridico = (FornecedorJuridico) c;
				if(juridico.getCnpj().equals(cpfCnpj)){
					return true;
				}
			}
			
		}
		return false;
	}

	public ArrayList<Pessoa> filtrarPorTipo(String tipo){
		ArrayList<Pessoa> TodosFornecedores = obterTodosOsFornecedores();
		ArrayList<Pessoa> filtrar = new ArrayList();
		for(Pessoa p: TodosFornecedores) {
			if (tipo.equalsIgnoreCase("Fisico")) {

				if (p instanceof FornecedorFisico) {
					filtrar.add(p);
				}
			} else {
				if (p instanceof FornecedorJuridico) {
					filtrar.add(p);
				}
			}
		}
		return filtrar;
		
	}

	public ArrayList<Pessoa> obterTodosOsFornecedores(){
		return CentralDeInformacoes.getInstance().getTodosOsFornecedores();
	
	}

	public int pegarIndeciDoFornecedor(String cpfCnpj) {
		int i = 0;
		for (Pessoa fornecedor : obterTodosOsFornecedores()) {
			if (fornecedor instanceof FornecedorFisico) {
				FornecedorFisico fisico = (FornecedorFisico) fornecedor;
				if (fisico.getCpfCnpj().equals(cpfCnpj))
					return i;
			} else {
				FornecedorJuridico juridico = (FornecedorJuridico) fornecedor;
				if (juridico.getCnpj().equals(cpfCnpj)) {
					return i;
				}
			}
			i++;
		}

		return i;

	}

	public void removerFornecedor(int indice) {
		CentralDeInformacoes.getInstance().getTodosOsFornecedores().remove(indice);
		Persistencia.getInstance().salvarCentral(CentralDeInformacoes.getInstance(), Constantes.NOME_ARQUIVO);
	}

}

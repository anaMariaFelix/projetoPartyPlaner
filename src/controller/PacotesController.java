package controller;

import java.util.ArrayList;

import baseDedados.CentralDeInformacoes;
import baseDedados.Persistencia;
import model.FornecedorFisico;
import model.FornecedorJuridico;
import model.Pacote;
import model.Pessoa;
import util.Constantes;

public class PacotesController {

	private static PacotesController instance;

	private PacotesController() {

	}

	public static PacotesController getInstance() {
		if (instance == null) {
			instance = new PacotesController();
		}
		return instance;
	}

	public boolean adicionarPacote(Pacote pacote) {
		if (!existePacote(pacote.getNomeDoPacote())) {
			CentralDeInformacoes.getInstance().getTodosOsPacotes().add(pacote);
			Persistencia.getInstance().salvarCentral(CentralDeInformacoes.getInstance(), Constantes.NOME_ARQUIVO);
			return true;
		}
		return false;
	}

	public boolean existePacote(String nome) {
		for (Pacote c : CentralDeInformacoes.getInstance().getTodosOsPacotes()) {
			if (c.getNomeDoPacote().equals(nome)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Pacote> obterListaDePacote() {
		return CentralDeInformacoes.getInstance().getTodosOsPacotes();
	}

	public boolean removerPacote(Pacote pacote) {
		if (existePacote(pacote.getNomeDoPacote())) {
			CentralDeInformacoes.getInstance().getTodosOsPacotes().remove(pacote);
			Persistencia.getInstance().salvarCentral(CentralDeInformacoes.getInstance(), Constantes.NOME_ARQUIVO);
			return true;
		}

		return false;
	}

	public boolean verificaSePacoteEstaDisponivel(Pacote pacote) {

		for (Pessoa p : pacote.getTodosFornecedore()) {
			if (p instanceof FornecedorFisico) {
				FornecedorFisico fisico = (FornecedorFisico) p;
				if (!fisico.isDisponibilidade()) {
					return true;
				}
			} else {
				FornecedorJuridico juridico = (FornecedorJuridico) p;
				if (!juridico.isDisponibilidade()) {
					return true;
				}
			}
		}
		return false;

	}

	public Pacote recuperarPacoteQueContemFornecedor(String cpfCnpj) {

		for (Pacote pacote : CentralDeInformacoes.getInstance().getTodosOsPacotes()) {
			for (Pessoa p : pacote.getTodosFornecedore()) {
				if (p instanceof FornecedorFisico) {
					FornecedorFisico fisico = (FornecedorFisico) p;
					if (fisico.getCpfCnpj().equals(cpfCnpj)) {
						return pacote;
					}

				} else {
					FornecedorJuridico juridico = (FornecedorJuridico) p;
					if (juridico.getCnpj().equals(cpfCnpj)) {
						return pacote;
					}
				}

			}

		}
		return null;
	}

	public int recuperarFornecedorNoPacote(String cpfCnpj, Pacote pacote) {

		ArrayList<Pessoa> fornecedores = pacote.getTodosFornecedore();

		for (int i = 0; i < fornecedores.size(); i++) {
			if (fornecedores.get(i) instanceof FornecedorFisico) {
				FornecedorFisico fisico = (FornecedorFisico) fornecedores.get(i);
				if (fisico.getCpfCnpj().equals(cpfCnpj)) {
					return i;
				}
			} else {
				FornecedorJuridico juridico = (FornecedorJuridico) fornecedores.get(i);
				if (juridico.equals(cpfCnpj)) {
					return i;
				}
			}

		}

		return -1;

	}

	public void removerEAdicionarPacoteAtualizado(String indentificador, Pessoa pessoa) {

		ArrayList<Pacote> listaDePacote = obterListaDePacote();
		int cont = 0;
		while (cont < listaDePacote.size()) {
			
			
			Pacote pacote = PacotesController.getInstance().recuperarPacoteQueContemFornecedor(indentificador);
			if(pacote != null) {
				int indiceFornecedor = PacotesController.getInstance().recuperarFornecedorNoPacote(indentificador, pacote);
				pacote.getTodosFornecedore().remove(indiceFornecedor);
				removerPacote(pacote);
				pacote.getTodosFornecedore().add(pessoa);
				adicionarPacote(pacote);
				
			}
			cont++;

		}

	}
	
	public ArrayList<String> obterServicosDoPacote(Pacote pacote){
		ArrayList<String> servicosDoPacote = new ArrayList();
		ArrayList<Pessoa> arrayFornecedor = pacote.getTodosFornecedore();
		
		for(Pessoa p: arrayFornecedor) {
			if(p instanceof FornecedorFisico) {
				FornecedorFisico fisico = (FornecedorFisico) p;
				for(int i = 0; i < fisico.getServicos().size();i++) {
					if(!servicosDoPacote.contains(fisico.getServicos().get(i))) {
						servicosDoPacote.add(fisico.getServicos().get(i));
					}
				}
			}else {
				FornecedorJuridico juridico = (FornecedorJuridico) p;
				for(int i = 0; i < juridico.getServicos().size();i++) {
					if(!servicosDoPacote.contains(juridico.getServicos().get(i))) {
						servicosDoPacote.add(juridico.getServicos().get(i));
					}
				}
			}
		}
		return servicosDoPacote;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

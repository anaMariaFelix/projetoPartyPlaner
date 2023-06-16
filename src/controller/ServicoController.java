package controller;

import java.util.ArrayList;

import baseDedados.CentralDeInformacoes;
import baseDedados.Persistencia;
import util.Constantes;

public class ServicoController {
	

	private static ServicoController instance;
	
	
	private ServicoController() {
		
	}
	
	public static ServicoController getInstance() {
		if (instance == null) {
			instance = new ServicoController();
		}
		return instance;
	}
	
	
	public ArrayList<String> pegaServicos(){
		return CentralDeInformacoes.getInstance().getTodosServicos();
	}
	
	
	public void salvarServicoEditado(int posicao, String servicoEditado) {
		ArrayList<String> servicos = pegaServicos();
		servicos.remove(posicao);
		servicos.add(posicao,servicoEditado);
		CentralDeInformacoes.getInstance().setTodosServicos(servicos);
		Persistencia.getInstance().salvarCentral(CentralDeInformacoes.getInstance(), Constantes.NOME_ARQUIVO);;
		
	}
	public void removerServico(int posicao) {
		ArrayList<String> servicos = pegaServicos();
		servicos.remove(posicao);
		CentralDeInformacoes.getInstance().setTodosServicos(servicos);
		Persistencia.getInstance().salvarCentral(CentralDeInformacoes.getInstance(), Constantes.NOME_ARQUIVO);;
		
	}
	
	public void adicionarServico(String servicoEditado) {
		ArrayList<String> servicos = pegaServicos();
		servicos.add(servicoEditado);
		CentralDeInformacoes.getInstance().setTodosServicos(servicos);
		Persistencia.getInstance().salvarCentral(CentralDeInformacoes.getInstance(), Constantes.NOME_ARQUIVO);;
		
	}
	
	
	
	
	
	
	
}

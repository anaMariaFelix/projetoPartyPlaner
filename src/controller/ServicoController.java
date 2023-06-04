package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import javax.swing.JOptionPane;

import baseDedados.CentralDeInformacoes;
import baseDedados.Persistencia;
import telas.TelaDeServicos;
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

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
	
	/**
	 * com o  construto privado evitando que essa classe seja instanciada fora dela.
	 * 
	 */
	
	private ServicoController() {
		
	}
	/**
	 * para conseguimos instancia ou acessar uma instancia dessa classe atreves desse metodo get.
	 * se a classe ja tiver sido estanciada ela nao retorna nulo. Ela retorna a propria instância.
	 *
	 * @return  retorna a instance.
	 */
	
	public static ServicoController getInstance() {
		if (instance == null) {
			instance = new ServicoController();
		}
		return instance;
	}
	
	
	/**
	 * Criamos um metodo para pegar os serviços.
	 * @return getTodosServiços();
	 */
	
	
	public ArrayList<String> pegaServicos(){
		return CentralDeInformacoes.getInstance().getTodosServicos();
	}
	
	
	/**
	 * Esse metodo, a gente salva o serviço editado, onde  vai pegar a posição e o serviço que a gente 
	 * que editar. Depois passamos a posição 
	 * que a pessoa que editar. Ai depois adicionamos ela na lista.  
	 * @param posicao
	 * @param servicoEditado
	 */
	
	public void salvarServicoEditado(int posicao, String servicoEditado) {
		ArrayList<String> servicos = pegaServicos();
		servicos.remove(posicao);
		servicos.add(posicao,servicoEditado);
		CentralDeInformacoes.getInstance().setTodosServicos(servicos);
		Persistencia.getInstance().salvarCentral(CentralDeInformacoes.getInstance(), Constantes.NOME_ARQUIVO);;
		
	}
	
	/**
	 * Esse metodo receber o indece que esta o servico que ele que remover.
	 * Depois que remover a gente seta o Array de servicos no array que está na central, que é o 
	 * setTodosServicos(), e depois chamamos a persistencia e salvamos na central com a lista atualizada.
	 * @param posicao
	 */
	
	public void removerServico(int posicao) {
		ArrayList<String> servicos = pegaServicos();
		servicos.remove(posicao);
		CentralDeInformacoes.getInstance().setTodosServicos(servicos);
		Persistencia.getInstance().salvarCentral(CentralDeInformacoes.getInstance(), Constantes.NOME_ARQUIVO);;
		
	}
	
	
	/**
	 * esse metodo adicionarServico vamos pegar o servico que ele quer adicionar e passamos central
	 * e depois salvamos na persistencia 
	 * @param servicoEditado
	 */
	
	public void adicionarServico(String servicoEditado) {
		ArrayList<String> servicos = pegaServicos();
		servicos.add(servicoEditado);
		CentralDeInformacoes.getInstance().setTodosServicos(servicos);
		Persistencia.getInstance().salvarCentral(CentralDeInformacoes.getInstance(), Constantes.NOME_ARQUIVO);;
		
	}
	
	
	
	
	
	
	
}

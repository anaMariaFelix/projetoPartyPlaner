package baseDedados;

import java.util.ArrayList;
import java.util.Arrays;

import model.Administrador;
import model.OrcamentoOuContrato;
import model.Pacote;
import model.Pessoa;
import model.Reuniao;
import util.Constantes;

public class CentralDeInformacoes {

	/**
	 * Atributos criados na central de informações para que sejam todos preenchidos
	 * de acordo com a quanttidade de coisas que iremos cadastrar durante a execução
	 * do sistema
	 */
	private ArrayList<Pessoa> todosOsClientes = new ArrayList();
	private ArrayList<Pessoa> todosOsFornecedores = new ArrayList();
	private ArrayList<OrcamentoOuContrato> todosEvento = new ArrayList();
	private ArrayList<String> todosServicos = new ArrayList();
	private ArrayList<Pacote> todosOsPacotes = new ArrayList();
	private ArrayList<Reuniao> todasAsReunioes = new ArrayList();
	private Administrador administrador;

	/**
	 * Atibuto da propria classe que esta privado para nao ser estanciado mais de
	 * uma vez, utilizamos o padrao singleton para termos apenas uma instancia dessa
	 * classe para nao perdermos as alterações que fizermos nela
	 */
	private static CentralDeInformacoes instance;

	private CentralDeInformacoes() {
		popularTodosServicos();

	}

	public static CentralDeInformacoes getInstance() {
		if (instance == null) {
			CentralDeInformacoes doArquivo = Persistencia.getInstance().recupearCentral(Constantes.NOME_ARQUIVO);
			if (doArquivo == null) {
				instance = new CentralDeInformacoes();
			} else {
				instance = doArquivo;
			}
		}
		return instance;
	}

	/**
	 * gets e sets dos atriutos para podermos acessalos em outros locais que a
	 * central de informação for estanciado
	 */
	
	public ArrayList<Pacote> getTodosOsPacotes() {
		return todosOsPacotes;
	}

	public ArrayList<Reuniao> getTodasAsReunioes() {
		return todasAsReunioes;
	}

	public void setTodasAsReunioes(ArrayList<Reuniao> todasAsReunioes) {
		this.todasAsReunioes = todasAsReunioes;
	}

	public void setTodosOsPacotes(ArrayList<Pacote> todosOsPacotes) {
		this.todosOsPacotes = todosOsPacotes;
	}

	public ArrayList<Pessoa> getTodosOsClientes() {
		return todosOsClientes;
	}

	public void setTodosOsClientes(ArrayList<Pessoa> todosOsClientes) {
		this.todosOsClientes = todosOsClientes;
	}

	public ArrayList<Pessoa> getTodosOsFornecedores() {
		return todosOsFornecedores;
	}

	public void setTodosOsFornecedores(ArrayList<Pessoa> todosOsFornecedores) {
		this.todosOsFornecedores = todosOsFornecedores;
	}

	public ArrayList<OrcamentoOuContrato> getTodosEvento() {
		return todosEvento;
	}

	public void setTodosEvento(ArrayList<OrcamentoOuContrato> todosEvento) {
		this.todosEvento = todosEvento;
	}

	public ArrayList<String> getTodosServicos() {
		return todosServicos;
	}

	public void setTodosServicos(ArrayList<String> todosServicos) {
		this.todosServicos = todosServicos;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	/**
	 * Metodo que utilizamos para iniciarmos com alguns serviços cadastrados, para o
	 * programa não iniciar sem nenhum serviço
	 */
	private void popularTodosServicos() {
		todosServicos.addAll(Arrays.asList("Buffet", "Musica", "Ornamentação", "Local", "Segurança", "Serviços Gerais",
				"Agente de publicidade", "Recepção"));
	}

}

package baseDedados;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import model.Administrador;
import model.ClienteFisico;
import model.ClienteJuridico;
import model.Pessoa;
import util.Constantes;
import model.OrcamentoOuContrato;
import model.FornecedorFisico;
import model.FornecedorJuridico;
import model.Pacote;

public class CentralDeInformacoes {

	private static CentralDeInformacoes instance;

	private ArrayList<Pessoa> todosOsClientes = new ArrayList();
	private ArrayList<Pessoa> todosOsFornecedores = new ArrayList();
	private ArrayList<OrcamentoOuContrato> todosEvento = new ArrayList();
	private ArrayList<String> todosServicos = new ArrayList();
	private ArrayList<Pacote> todosOsPacotes = new ArrayList();
	private Administrador administrador;

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

	public ArrayList<Pacote> getTodosOsPacotes() {
		return todosOsPacotes;
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

	private void popularTodosServicos() {
		todosServicos.addAll(Arrays.asList("Buffet", "Musica", "Ornamentação", "Local", "Segurança", "Serviços Gerais",
				"Agente de publicidade", "Recepção"));
	}

}

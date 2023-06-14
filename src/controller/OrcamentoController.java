package controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import baseDedados.CentralDeInformacoes;
import baseDedados.Persistencia;
import model.FornecedorFisico;
import model.FornecedorJuridico;
import model.OrcamentoOuContrato;
import model.Pacote;
import model.Pessoa;
import util.Constantes;

public class OrcamentoController {
	private static OrcamentoController instance;

	private ArrayList<Pacote> pacoteFornecedores = new ArrayList();
	private ArrayList<Pessoa> fornecedores = new ArrayList();

	private OrcamentoController() {

	}

	public static OrcamentoController getInstance() {
		if (instance == null) {
			instance = new OrcamentoController();
		}
		return instance;
	}

	public ArrayList<Pacote> getPacoteFornecedores() {
		return pacoteFornecedores;
	}

	public void setPacoteFornecedores(ArrayList<Pacote> pacoteFornecedores) {
		this.pacoteFornecedores = pacoteFornecedores;
	}

	public ArrayList<Pessoa> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(ArrayList<Pessoa> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public boolean adicionarOrcamento(OrcamentoOuContrato orcamento) {
		LocalDateTime dataHoraAtual = LocalDateTime.now();
		if (!existeOrcamentoNessaData(orcamento.getDataEHoraDoEvento())
				&& orcamento.getDataEHoraDoEvento().isAfter(dataHoraAtual)) {
			CentralDeInformacoes.getInstance().getTodosEvento().add(0, orcamento);// modifiquei para ele adicionar no
																					// indice 0 para quando ele listar
																					// os orcamentos
			Persistencia.getInstance().salvarCentral(CentralDeInformacoes.getInstance(), Constantes.NOME_ARQUIVO);// os modificados fiquem em cima,em ordem decrescente
			return true;// 14 - Eles são listados em ordem decrescente desde a última vez que sofreram
						// alguma modificação (os mais recentes primeiros).

		}
		return false;
	}

	public boolean existeOrcamentoNessaData(LocalDateTime data) {
		for (OrcamentoOuContrato o : CentralDeInformacoes.getInstance().getTodosEvento()) {
			if (o.getDataEHoraDoEvento().equals(data)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<OrcamentoOuContrato> obterTodosOsOrcamentoEContratos() {
		return CentralDeInformacoes.getInstance().getTodosEvento();// lembrar de mudar o nome desse array na central

	}

	public void populaArrayFornecedores(ArrayList<Pessoa> fornecedor) {
		for (Pessoa p : fornecedor) {
			fornecedores.add(p);
		}

	}

	public void populaArrayPacotes(ArrayList<Pacote> pacote) {
		for (Pacote p : pacote) {
			pacoteFornecedores.add(p);
		}

	}

	public ArrayList<OrcamentoOuContrato> filtrarPorTipo(String tipo) {
		ArrayList<OrcamentoOuContrato> TodosOrcamentos = obterTodosOsOrcamentoEContratos();
		ArrayList<OrcamentoOuContrato> filtrar = new ArrayList();
		for (OrcamentoOuContrato o : TodosOrcamentos) {
			if (tipo.equalsIgnoreCase("Orcamento")) {
				if (!o.isFoiContradoOuNao()) {
					filtrar.add(o); // orcamento
				}
			} else {
				if (o.isFoiContradoOuNao()) {
					filtrar.add(o); // contrato
				}
			}
		}
		return filtrar;

	}

	public void removerFornecedor(OrcamentoOuContrato orcamentoContrato, Pessoa fornecedor) {
		orcamentoContrato.getFornecedores().remove(fornecedor);
	}

	public boolean removerOrcamentoOuContrato(LocalDateTime data, String email) {
		ArrayList<OrcamentoOuContrato> todosOsOrcamentos = obterTodosOsOrcamentoEContratos();
		for (OrcamentoOuContrato o : todosOsOrcamentos) {
			if (o.getDataEHoraDoEvento() == data && o.getClienteAssociado().getEmail().equals(email)) {
				CentralDeInformacoes.getInstance().getTodosEvento().remove(o);
				Persistencia.getInstance().salvarCentral(CentralDeInformacoes.getInstance(), Constantes.NOME_ARQUIVO);
				return true;
			}
		}
		return false;

	}

}

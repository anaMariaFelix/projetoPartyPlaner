package controller;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
	
	
	public static JScrollPane filtrarFornecedores(String tipo) {
		
		JTable tabela = new JTable();
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Nome");// adiciona colunas
		modelo.addColumn("Fisico/Juridico");
		modelo.addColumn("Quantidade de contratos");
		
		Object[] todosOsFornecedores =  CentralDeInformacoes.getInstance().getTodosOsFornecedores().toArray();
		
		
		for (Object t : todosOsFornecedores) {
			Object[] linha = new Object[3];
			if(tipo.equalsIgnoreCase("Fisico")) {
			
				if(t instanceof FornecedorFisico) {
					FornecedorFisico ff = (FornecedorFisico) t;
					linha[0] = ff.getNome();
					linha[1] = "Fisico";
					linha[2] = ff.getQuantContratosFisico();
				}
			}else {
				if(t instanceof FornecedorJuridico) {
					FornecedorJuridico fj = (FornecedorJuridico) t;
					linha[0] = fj.getNome();
					linha[1] = "Juridico";
					linha[2] = fj.getQuantContratosJuridico();
				}
			}
			
			modelo.addRow(linha);

		}
		tabela = new JTable(modelo);
		JScrollPane painelTabela = new JScrollPane(tabela);										
		return painelTabela;
	}
	
	
	
	
	
	
	
	
	
	
}

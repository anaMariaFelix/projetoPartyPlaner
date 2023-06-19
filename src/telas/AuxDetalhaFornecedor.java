package telas;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import controller.FornecedorController;
import model.FornecedorFisico;
import model.FornecedorJuridico;
import model.Pessoa;
import util.ComponentesDeJFrame;
import util.Constantes;

public class AuxDetalhaFornecedor {
	
	private DefaultTableModel modelo;
	private DefaultTableModel modeloComentario;
	
	private JTable tabela;
	private JTable tabelaComentario;
	
	
	public  void detalharFornecedor(String cpfCnpj) {
		
		JScrollPane tabelaServicos;
		JScrollPane tabelaComentario;
		TelaCadastrarFornecedor telaCadastrarFornecedor = new TelaCadastrarFornecedor(Constantes.TITULO_DADOS_FORNECEDOR);
		
		telaCadastrarFornecedor.getLbTitulo().setBounds(250, 35, 300, 50);
		telaCadastrarFornecedor.getJlNomeCompleto().setBounds(100, 100, 150, 30);
		telaCadastrarFornecedor.getJlTelefone().setBounds(100, 165, 130, 30);
		telaCadastrarFornecedor.getJlEmail().setBounds(100, 225, 130, 30);
		telaCadastrarFornecedor.getCpfCnpj().setBounds(100, 285, 225, 30);

		// Campos TextField
		telaCadastrarFornecedor.getCampoNomeCompleto().setBounds(100, 130, 225, 30);
		telaCadastrarFornecedor.getCampoTelefone().setBounds(100, 193, 225, 30);
		telaCadastrarFornecedor.getCampoEmail().setBounds(100, 253, 225, 30);

		Pessoa pessoa = FornecedorController.getInstance().recuperarFornecedorPorCpfOuCnpj(cpfCnpj);

		telaCadastrarFornecedor.getCampoNomeCompleto().setText(pessoa.getNome());
		telaCadastrarFornecedor.getCampoNomeCompleto().setEnabled(false);

		telaCadastrarFornecedor.getCampoEmail().setText(pessoa.getEmail());
		telaCadastrarFornecedor.getCampoEmail().setEnabled(false);

		telaCadastrarFornecedor.getCampoTelefone().setText(pessoa.getTelefone());
		telaCadastrarFornecedor.getCampoTelefone().setEnabled(false);

		telaCadastrarFornecedor.getBotaoSalvar().setVisible(false);
		telaCadastrarFornecedor.getBotaoServicos().setVisible(false);
		telaCadastrarFornecedor.getJlServicos().setVisible(false);

		telaCadastrarFornecedor.getBotaoVoltar().setBounds(100, 442, 100, 30);

		if (pessoa instanceof FornecedorFisico) {
			FornecedorFisico fisico = (FornecedorFisico) pessoa;
			telaCadastrarFornecedor.getCampoCPF().setText(fisico.getCpfCnpj());
			telaCadastrarFornecedor.getCampoCPF().setBounds(100, 315, 225, 30);
			telaCadastrarFornecedor.getPessoaFisica().setBounds(100, 348, 200, 30);
			telaCadastrarFornecedor.setListaDeServicos(fisico.getServicos());
			telaCadastrarFornecedor.getCampoCPF().setEnabled(false);
			telaCadastrarFornecedor.getPessoaJuridica().setVisible(false);
			telaCadastrarFornecedor.getPessoaFisica().setEnabled(false);
			tabelaServicos = tabelaDetalharServicos(fisico);
			tabelaComentario = tabelaListaComentario(fisico);
			telaCadastrarFornecedor.add(exibeDisponibilidade(fisico));

		} else {
			FornecedorJuridico juridico = (FornecedorJuridico) pessoa;
			telaCadastrarFornecedor.getPessoaJuridica().doClick();
			telaCadastrarFornecedor.getCampoCNPJ().setText(juridico.getCnpj());
			telaCadastrarFornecedor.getCampoCNPJ().setBounds(100, 315, 225, 30);
			telaCadastrarFornecedor.getPessoaJuridica().setBounds(100, 348, 200, 30);
			telaCadastrarFornecedor.setListaDeServicos(juridico.getServicos());
			telaCadastrarFornecedor.getCpfCnpj().setText("CNPJ");
			telaCadastrarFornecedor.getPessoaFisica().setVisible(false);
			telaCadastrarFornecedor.getPessoaJuridica().setEnabled(false);
			telaCadastrarFornecedor.getCampoCNPJ().setEnabled(false);
			tabelaServicos = tabelaDetalharServicos(juridico);
			tabelaComentario = tabelaListaComentario(juridico);
			telaCadastrarFornecedor.add(exibeDisponibilidade(juridico));
		}
		

		JLabel disponibilidade = ComponentesDeJFrame.criaJLabel("Disponibilidade", 100,378,200,30,15);
		telaCadastrarFornecedor.add(disponibilidade);
		
		
		
		tabelaServicos.setBounds(400, 100, 225, 183);
		telaCadastrarFornecedor.add(tabelaServicos);
		
		tabelaComentario.setBounds(400,290,225,183);
		telaCadastrarFornecedor.add(tabelaComentario);
		
		
		
	}
	
	
	private JTextField exibeDisponibilidade(Pessoa pessoa) {
		
		JTextField disponibilidade = ComponentesDeJFrame.criaJTextField(100,408, 225, 30);
		if(pessoa instanceof FornecedorFisico) {
			FornecedorFisico fisico = (FornecedorFisico) pessoa;
			if(fisico.isDisponibilidade()) {
				disponibilidade.setText("Disponivel");
				disponibilidade.setEnabled(false);
			}else {
				disponibilidade.setText("Indisponivel");
				disponibilidade.setEnabled(false);
			}
			
		}else {
			FornecedorJuridico juridico = (FornecedorJuridico) pessoa;
			if(juridico.isDisponibilidade()) {
				disponibilidade.setText("Disponivel");
				disponibilidade.setEnabled(false);
			}else {
				disponibilidade.setText("Indisponivel");
				disponibilidade.setEnabled(false);
			}
			
		}
		return disponibilidade;
	}

	private JScrollPane tabelaDetalharServicos(Pessoa pessoa) {
		modelo = new DefaultTableModel();
		modelo.addColumn("Serviços Cadastrados");

		Object[] todosOsFornecedores;

		if (pessoa instanceof FornecedorFisico) {
			FornecedorFisico fisico = (FornecedorFisico) pessoa;
			todosOsFornecedores = fisico.getServicos().toArray();

		} else {
			FornecedorJuridico juridico = (FornecedorJuridico) pessoa;
			todosOsFornecedores = juridico.getServicos().toArray();
		}

		for (int i = 0; i < todosOsFornecedores.length; i++) {
			Object[] linha = new Object[1];
			linha[0] = todosOsFornecedores[i];
			modelo.addRow(linha);
		}

		tabela = new JTable(modelo);

		return new JScrollPane(tabela);

	}
	
	private JScrollPane tabelaListaComentario(Pessoa pessoa) {
		modeloComentario = new DefaultTableModel();
		modeloComentario.addColumn("Comentarios");

		Object[] todosOsFornecedores;

		if (pessoa instanceof FornecedorFisico) {
			FornecedorFisico fisico = (FornecedorFisico) pessoa;
			todosOsFornecedores = fisico.getComentariosFornecedores().toArray();

		} else {
			FornecedorJuridico juridico = (FornecedorJuridico) pessoa;
			todosOsFornecedores = juridico.getComentariosFornecedores().toArray();
		}

		for (int i = 0; i < todosOsFornecedores.length; i++) {
			Object[] linha = new Object[1];
			linha[0] = todosOsFornecedores[i];
			modeloComentario.addRow(linha);
		}

		tabelaComentario = new JTable(modeloComentario);

		return new JScrollPane(tabelaComentario);

	}
	
	

}

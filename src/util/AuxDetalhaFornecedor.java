package util;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.FornecedorController;
import model.FornecedorFisico;
import model.FornecedorJuridico;
import model.Pessoa;
import telas.TelaCadastrarFornecedor;

public class AuxDetalhaFornecedor {
	private DefaultTableModel modelo;
	private JTable tabela;
	
	
	public  void detalharFornecedor(String cpfCnpj) {
		
		JScrollPane tabelaServicos;
		TelaCadastrarFornecedor telaCadastrarFornecedor = new TelaCadastrarFornecedor("Dados Fornecedor");
		telaCadastrarFornecedor.getJlNomeCompleto().setBounds(100, 180, 150, 30);
		telaCadastrarFornecedor.getJlTelefone().setBounds(100, 250, 130, 30);
		telaCadastrarFornecedor.getJlEmail().setBounds(100, 320, 130, 30);
		telaCadastrarFornecedor.getCpfCnpj().setBounds(100, 415, 225, 30);

		// Campos TextField
		telaCadastrarFornecedor.getCampoNomeCompleto().setBounds(100, 210, 225, 30);
		telaCadastrarFornecedor.getCampoTelefone().setBounds(100, 280, 225, 30);
		telaCadastrarFornecedor.getCampoEmail().setBounds(100, 350, 225, 30);

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

		telaCadastrarFornecedor.getBotaoVoltar().setBounds(100, 500, 100, 30);

		if (pessoa instanceof FornecedorFisico) {
			FornecedorFisico fisico = (FornecedorFisico) pessoa;
			telaCadastrarFornecedor.getCampoCPF().setText(fisico.getCpfCnpj());
			telaCadastrarFornecedor.getCampoCPF().setBounds(100, 440, 225, 30);
			telaCadastrarFornecedor.getPessoaFisica().setBounds(100, 380, 200, 30);
			telaCadastrarFornecedor.setListaDeServicos(fisico.getServicos());
			telaCadastrarFornecedor.getCampoCPF().setEnabled(false);
			telaCadastrarFornecedor.getPessoaJuridica().setVisible(false);
			tabelaServicos = tabelaDetalharServicos(fisico);
			telaCadastrarFornecedor.add(exibeDisponibilidade(fisico));

		} else {
			FornecedorJuridico juridico = (FornecedorJuridico) pessoa;
			telaCadastrarFornecedor.getPessoaJuridica().doClick();
			telaCadastrarFornecedor.getCampoCNPJ().setText(juridico.getCnpj());
			telaCadastrarFornecedor.getCampoCNPJ().setBounds(100, 440, 225, 30);
			telaCadastrarFornecedor.getPessoaJuridica().setBounds(100, 380, 200, 30);
			telaCadastrarFornecedor.setListaDeServicos(juridico.getServicos());
			telaCadastrarFornecedor.getCpfCnpj().setText("CNPJ");
			telaCadastrarFornecedor.getPessoaFisica().setVisible(false);
			telaCadastrarFornecedor.getCampoCNPJ().setEnabled(false);
			tabelaServicos = tabelaDetalharServicos(juridico);
			telaCadastrarFornecedor.add(exibeDisponibilidade(juridico));
		}

		JLabel disponibilidade = ComponentesDeJFrame.criaJLabel("Disponibilidade", 400,415,200,30,15);
		telaCadastrarFornecedor.add(disponibilidade);
		tabelaServicos.setBounds(400, 184, 225, 196);
		telaCadastrarFornecedor.add(tabelaServicos);
	}
	
	private JTextField exibeDisponibilidade(Pessoa pessoa) {
		JTextField disponibilidade = ComponentesDeJFrame.criaJTextField(400,440, 225, 30);
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

}

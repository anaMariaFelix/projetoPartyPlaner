package telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import baseDedados.CentralDeInformacoes;
import ouvintes.OuvinteBotaoSalvar;
import util.ComponentesDeJFrame;

public class TelaMenu extends JanelaPadrao{
	private JButton botaoCadastrarCliente;
	private JButton botaoCadastrarFornecedor;
	private JButton botaoServicos;
	private JButton botaoListarFornecedores;
	private JButton botaoCadastrarPacotesDeFornecedores;
	private JButton botaoListarPacotesDeFornecedores;
	private JButton botaoCadastrarOrcamento;
	private JButton botaoListarOrcamento;

	
	
	
	
	public TelaMenu(String titulo) {
		super(titulo);
	
		adicionarTitulo();
		adicionarJButton();
		setVisible(true);
		
	}

	public JButton getBotaoCadastrarCliente() {
		return botaoCadastrarCliente;
	}

	public JButton getBotaoCadastrarFornecedor() {
		return botaoCadastrarFornecedor;
	}

	public JButton getBotaoServicos() {
		return botaoServicos;
	}

	public JButton getBotaoListarFornecedores() {
		return botaoListarFornecedores;
	}

	public JButton getBotaoCadastrarPacotesDeFornecedores() {
		return botaoCadastrarPacotesDeFornecedores;
	}

	public JButton getBotaoListarPacotesDeFornecedores() {
		return botaoListarPacotesDeFornecedores;
	}

	public JButton getBotaoCadastrarOrcamento() {
		return botaoCadastrarOrcamento;
	}

	public JButton getBotaoListarOrcamento() {
		return botaoListarOrcamento;
	}

	private void adicionarTitulo() {
		
		JLabel lbTitulo = ComponentesDeJFrame.criaJLabel("MENU",340, 40, 280, 80, 30);
		lbTitulo.setForeground(Color.BLACK); // cor do texto);
		add(lbTitulo);
	}

	private void adicionarJButton() {
		OuvinteBotoesDoMenu ouvinte = new OuvinteBotoesDoMenu();
		botaoCadastrarCliente = ComponentesDeJFrame.criarBotoesDoMenu("<html><center>Cadastrar<br>Cliente</center>",30, 150, 160, 170);
		botaoCadastrarCliente.addActionListener(ouvinte);
		add(botaoCadastrarCliente);
		
		botaoCadastrarFornecedor = ComponentesDeJFrame.criarBotoesDoMenu("<html><center>Cadastrar<br>Fornecedor</center>",210, 150, 160, 170);	
		botaoCadastrarFornecedor.addActionListener(ouvinte);
		add(botaoCadastrarFornecedor);
		
		botaoServicos =  ComponentesDeJFrame.criarBotoesDoMenu("Serviços",390, 150, 160, 170);
		botaoServicos.addActionListener(ouvinte);
		add(botaoServicos);
		
		botaoListarFornecedores = ComponentesDeJFrame.criarBotoesDoMenu("<html><center>Listar<br>Fornecedores</center>",570, 150, 160, 170);
		botaoListarFornecedores.addActionListener(ouvinte);
		add(botaoListarFornecedores);
		
		botaoCadastrarPacotesDeFornecedores = ComponentesDeJFrame.criarBotoesDoMenu("<html><center>Cadastrar Pacotes de<br>Fornecedores</center>",30, 350, 160, 170);
		botaoCadastrarPacotesDeFornecedores.addActionListener(ouvinte);
		add(botaoCadastrarPacotesDeFornecedores);
		

		botaoListarPacotesDeFornecedores = ComponentesDeJFrame.criarBotoesDoMenu("<html><center>Listar Pacotes de<br>Fornecedores</center>",210, 350, 160, 170);
		botaoListarPacotesDeFornecedores.addActionListener(ouvinte);
		add(botaoListarPacotesDeFornecedores);
		
		botaoCadastrarOrcamento = ComponentesDeJFrame.criarBotoesDoMenu("<html><center>Cadastrar<br>Orçamento</center>",390, 350, 160, 170);
		botaoCadastrarOrcamento.addActionListener(ouvinte);
		add(botaoCadastrarOrcamento);

		botaoListarOrcamento = ComponentesDeJFrame.criarBotoesDoMenu("<html><center>Listar<br>Orçamento</center>",570, 350, 160, 170);
		botaoListarOrcamento.addActionListener(ouvinte);
		add(botaoListarOrcamento);

	}
	
	private class OuvinteBotoesDoMenu implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == botaoCadastrarCliente) {
				dispose();
				new TelaCadastrarCliente("Cadastro do Cliente");
		
			}else if(e.getSource() == botaoCadastrarFornecedor) {
				dispose();
				new TelaCadastrarFornecedor("Cadastar Fornecedor");
				
			}else if(e.getSource() == botaoServicos) {
				JOptionPane.showMessageDialog(null, "dentro do botao serviço");
				
			}else if(e.getSource() == botaoListarFornecedores) {
				JOptionPane.showMessageDialog(null, "dentro do Listar Fornecedores");
				
			}else if(e.getSource() == botaoCadastrarPacotesDeFornecedores) {
				JOptionPane.showMessageDialog(null, "dentro do botao Cadastrar Pacotes De Fornecedores");
				
			}else if(e.getSource() == botaoListarPacotesDeFornecedores) {
				JOptionPane.showMessageDialog(null, "dentro do botao Listar Pacotes De Fornecedores");
				
			}else if(e.getSource() == botaoCadastrarOrcamento) {
				JOptionPane.showMessageDialog(null, "dentro do botao Cadastrar Orcamento");
				
			}else if(e.getSource() == botaoListarOrcamento) {
				JOptionPane.showMessageDialog(null, "dentro do botao Listar Orcamento");
			}
		}
		
	}

}

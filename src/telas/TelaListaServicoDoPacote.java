package telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.PacotesController;
import model.Pacote;
import util.ComponentesDeJFrame;

public class TelaListaServicoDoPacote extends JanelaPadrao{
	private Pacote pacote;
	
	public TelaListaServicoDoPacote(String titulo,Pacote pacote) {
		super(titulo);
		this.pacote = pacote;
		adicionarTabela();
		adicionarJLabel();
		adicionarJButton();
		setVisible(true);
	}

	private void adicionarJButton() {
		JButton voltar = ComponentesDeJFrame.criarBotao("Voltar", 636, 490, 125, 35);
		voltar.addActionListener(new OuvinteBotaoVoltar());
		add(voltar);
		
	}
	
	private class OuvinteBotaoVoltar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			new TelaListarPacotesFornecedores("Lista De Pacotes");
		}

	}

	private void adicionarJLabel() {
		JLabel titulo = ComponentesDeJFrame.criaJLabel("Serviços do Pacote",275, 15, 400, 50, 30 );
		add(titulo);
		
		
	}

	
	private void adicionarTabela() {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Serviços");
		
		Object[] servicos = PacotesController.getInstance().obterServicosDoPacote(pacote).toArray();
		
		for(Object o: servicos) {
			Object[] linha = new Object[1];
			linha[0] = o;
			
			modelo.addRow(linha);
		}
	
		JTable tabela = new JTable(modelo);
		
		
		JScrollPane painel = new JScrollPane(tabela);
		painel.setBounds(30, 135, 730, 350);
		
		add(painel);

		
	}
	

}

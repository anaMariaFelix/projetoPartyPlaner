package telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Reuniao;
import util.ComponentesDeJFrame;

public class TelaVisualizarAta extends JanelaPadrao {

	private DefaultTableModel modelo;
	private JTable tabela;

	private JButton voltar;

	private Reuniao reuniao;

	public TelaVisualizarAta(String titulo,Reuniao reuniao) {
		super(titulo);
		this.reuniao = reuniao;
		adicionarTitulo();
		adicionarTabela();
		adicionarJButton();
		setVisible(true);
	}

	private void adicionarTitulo() {
		JLabel titulo = ComponentesDeJFrame.criaJLabel("Ata da Reunião", 0, 36, 800, 50, 30);
		titulo.setHorizontalAlignment(JLabel.CENTER);
		add(titulo);

	}

	private void adicionarJButton() {
		voltar = ComponentesDeJFrame.criarBotao("Voltar", 636, 490, 125, 35);
		voltar.addActionListener(new OuvinteBotaoVoltar());
		add(voltar);

	}

	private void adicionarTabela() {

		modelo = new DefaultTableModel();
		modelo.addColumn("Comentarios");

		Object[] comentarios = reuniao.getAtaReuniao().toArray();

		tabela = new JTable(modelo);

		JScrollPane painelTabela = new JScrollPane(tabela);
		painelTabela.setBounds(30, 135, 730, 350);
		add(painelTabela);

		preencherTabela(comentarios);

	}

	public void preencherTabela(Object[] comentarios) {
		limparTabela();
		for (Object r : comentarios) {
			Object[] linha = new Object[5];

			linha[0] = r;

			modelo.addRow(linha);

		}

	}

	public void limparTabela() {
		int cont = modelo.getRowCount();
		for (int i = 0; i < cont; i++) {
			modelo.removeRow(0);
		}
		tabela.repaint();
	}

	private class OuvinteBotaoVoltar implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			dispose();
			new TelaListaReunioes("Lista de Reuniões");

		}

	}

}

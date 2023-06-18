package telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ReuniaoController;
import model.Reuniao;
import util.ButtonEditor;
import util.ButtonRenderer;
import util.ComponentesDeJFrame;

public class TelaListaReunioes extends JanelaPadrao {

	private JButton voltar;
	private DefaultTableModel modelo;
	private JTable tabela;

	public TelaListaReunioes(String titulo) {
		super(titulo);
		adicionarTitulo();
		adicionarTabela();
		adicionarJButton();
		setVisible(true);
	}

	private void adicionarTitulo() {
		JLabel titulo = ComponentesDeJFrame.criaJLabel("Lista de Reuniões", 0, 36, 800, 50, 30);
		titulo.setHorizontalAlignment(JLabel.CENTER);
		add(titulo);

	}

	private void adicionarJButton() {
		OuvinteBotaoVoltar ouvinteBotaoVoltar = new OuvinteBotaoVoltar();
		voltar = ComponentesDeJFrame.criarBotao("Voltar", 636, 490, 125, 35);
		voltar.addActionListener(ouvinteBotaoVoltar);
		add(voltar);

	}

	private void adicionarTabela() {

		modelo = new DefaultTableModel();
		modelo.addColumn("Data");
		modelo.addColumn("Nome do Cliente");
		modelo.addColumn("Email do Cliente");
		modelo.addColumn("Status");
		modelo.addColumn("Concluir/Ata");

		Object[] todosAsReunioes = ReuniaoController.getInstance().obterTodasReunioes().toArray();

		tabela = new JTable(modelo);

		tabela.getColumn("Concluir/Ata").setCellRenderer(new ButtonRenderer());
		tabela.getColumn("Concluir/Ata").setCellEditor(new ButtonEditor(new JCheckBox()));

		JScrollPane painelTabela = new JScrollPane(tabela);
		painelTabela.setBounds(30, 135, 730, 350);
		add(painelTabela);

		preencherTabela(todosAsReunioes);

	}

	public void preencherTabela(Object[] reunioes) {
		limparTabela();
		for (Object r : reunioes) {
			Reuniao reuniao = (Reuniao) r;
			Object[] linha = new Object[5];

			linha[0] = reuniao.getDataHora();
			linha[1] = reuniao.getCliente().getNome();
			linha[2] = reuniao.getCliente().getEmail();
			linha[3] = "Pendente";

			if (reuniao.isConcluido()) {
				linha[3] = "Concluido";
			}

			if (!reuniao.isConcluido()) {
				JButton concluir = new JButton("Concluir");
				concluir.setBackground(new Color(39, 228, 86));
				concluir.addActionListener(new OuvinteConcluir(reuniao));
				linha[4] = concluir;
			} else {
				JButton ata = new JButton("Vizualizar Ata");
				ata.setBackground(new Color(39, 228, 86));
				ata.addActionListener(new OuvinteVisualizarAta(reuniao));
				linha[4] = ata;
			}

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
			new TelaListarOrcamentosContratos("Lista de Orçamento/Contratos");

		}

	}

	public class OuvinteConcluir implements ActionListener {

		private Reuniao reuniao;

		public OuvinteConcluir(Reuniao reuniao) {
			this.reuniao = reuniao;
		}

		public void actionPerformed(ActionEvent e) {
			reuniao.setConcluido(true);
			boolean entrou = true;
			ArrayList<String> comentariosReuniao = new ArrayList();
			while (entrou) {
				String comentario = JOptionPane.showInputDialog(null, "Ata da reunião", "Ata",
						JOptionPane.PLAIN_MESSAGE);

				if (comentario != null) {
					comentariosReuniao.add(comentario);

					int opcao = JOptionPane.showConfirmDialog(null, "Deseja informar mais um comentario?",
							"Questionário", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null);
					if (opcao == JOptionPane.NO_OPTION || opcao == JOptionPane.DEFAULT_OPTION) {
						entrou = false;
					}
				}
			}
			reuniao.setAtaReuniao(comentariosReuniao);

			ReuniaoController.getInstance().atualizarCentral();
			dispose();
			new TelaListaReunioes("Lista de Reuniões");

		}

	}

	public class OuvinteVisualizarAta implements ActionListener {

		private Reuniao reuniao;
		
		public OuvinteVisualizarAta(Reuniao reuniao) {
			this.reuniao = reuniao;
		}
		
		
		public void actionPerformed(ActionEvent e) {
			dispose();
			new TelaVisualizarAta("Ata da reunião",reuniao);
			
		}

	}

}

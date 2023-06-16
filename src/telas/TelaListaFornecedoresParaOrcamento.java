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
import controller.FornecedorController;
import controller.OrcamentoController;
import model.FornecedorFisico;
import model.FornecedorJuridico;
import model.OrcamentoOuContrato;
import model.Pessoa;
import telas.TelaCadastrarOrcamento.OuvinteBotaoSalvar;
import util.ButtonEditor;
import util.ButtonRenderer;
import util.ComponentesDeJFrame;

public class TelaListaFornecedoresParaOrcamento extends JanelaPadrao{
	private DefaultTableModel modelo;
	private JTable tabela;
	
	private JLabel titulo;
	private JButton voltar;
	private JButton botaoNovo;
	
	private ArrayList<Pessoa> fornecedores = new ArrayList<>();
	
	private TelaCadastrarOrcamento janela;
	
	private OrcamentoOuContrato orcamento;
	
	private AuxTelaEditarOrcamento telaEditarOrcamento;
	
	private boolean telaEditar;
	
	public TelaListaFornecedoresParaOrcamento(boolean telaEditar,OrcamentoOuContrato orcamento,TelaCadastrarOrcamento janela,String titulo) {
		super(titulo);
		this.janela = janela;
		this.orcamento = orcamento;
		this.telaEditar = telaEditar;
		adicionarJLabel();
		adicionarJTable();
		adicionarJButton();
		setVisible(true);
	}
	
	public AuxTelaEditarOrcamento getTelaEditarOrcamento() {
		return telaEditarOrcamento;
	}

	public void setTelaEditarOrcamento(AuxTelaEditarOrcamento telaEditarOrcamento) {
		this.telaEditarOrcamento = telaEditarOrcamento;
	}

	public OrcamentoOuContrato getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(OrcamentoOuContrato orcamento) {
		this.orcamento = orcamento;
	}

	public JButton getBotaoNovo() {
		return botaoNovo;
	}
	public ArrayList<Pessoa> getFornecedores() {
		return fornecedores;
	}
	
	public DefaultTableModel getModelo() {
		return modelo;
	}

	public JTable getTabela() {
		return tabela;
	}

	private void adicionarJButton() {
		voltar = ComponentesDeJFrame.criarBotao("Voltar", 636, 490, 125, 35);
		voltar.addActionListener(new OuvinteBotaoVoltarIncluirFornecedor(telaEditar,orcamento,telaEditarOrcamento));
		add(voltar);
		
		botaoNovo = ComponentesDeJFrame.criarBotao("Novo",  636, 94, 125, 35);
		botaoNovo.addActionListener(new OuvinteBotaoNovo(orcamento));
		add(botaoNovo);
	}


	protected void adicionarJLabel() {
		titulo = ComponentesDeJFrame.criaJLabel("Lista de Fornecedores", 0, 30, 800, 50, 30);
		titulo.setHorizontalAlignment(JLabel.CENTER);
		add(titulo);
		
	}
	
	protected void adicionarJTable() {
		modelo = new DefaultTableModel();
		modelo.addColumn("Nome");
		modelo.addColumn("Identificador");
		modelo.addColumn("Disponibilidade");
		modelo.addColumn("Adicionar");
		
		Object[] todosOsForncedores = FornecedorController.getInstance().obterTodosOsFornecedores().toArray();
		
		tabela = new JTable(modelo);
		
		tabela.getColumn("Adicionar").setCellRenderer(new ButtonRenderer()); 																// (linha/coluna)
		tabela.getColumn("Adicionar").setCellEditor(new ButtonEditor(new JCheckBox()));
		
		JScrollPane painelTabela = new JScrollPane(tabela);
		painelTabela.setBounds(30, 135, 730, 350);
		add(painelTabela);
		
		preencherTabela(todosOsForncedores);
		
	}

	public void preencherTabela(Object[] fornecedores) {
		
		for (Object p : fornecedores) {
			Object[] linha = new Object[4];
			
			JButton adicionar = new JButton("Adicionar");
			adicionar.setBackground(new Color(39, 228, 86));
			
			linha[3] = adicionar;
			
			if(p instanceof FornecedorFisico ) {
				FornecedorFisico fisico = (FornecedorFisico) p;
				linha[0] = fisico.getNome();
				linha[1] = fisico.getCpfCnpj();
				linha[2] = "Indisponivel";
				adicionar.addActionListener(new OuvinteBotaoAdicionar(this,fisico));
				
				if (fisico.isDisponibilidade()) {
					linha[2] = "Disponivel";
				}
			}else {
				FornecedorJuridico juridico = (FornecedorJuridico)p;
				linha[0] = juridico.getNome();
				linha[1] = juridico.getCnpj();
				linha[2] = "Indisponivel";
				adicionar.addActionListener(new OuvinteBotaoAdicionar(this,juridico));
				
				if (juridico.isDisponibilidade()) {
					linha[2] = "Disponivel";
				}
			}

			modelo.addRow(linha);// adiciona alinha

		}

	}
	private class OuvinteBotaoVoltarIncluirFornecedor implements ActionListener {

		private OrcamentoOuContrato orcamentoComFornecedores;
		private AuxTelaEditarOrcamento telaEditarContratoOrcamento;
		private boolean editar;
		
		public OuvinteBotaoVoltarIncluirFornecedor(boolean editar,OrcamentoOuContrato orcamentoComFornecedores,AuxTelaEditarOrcamento telaEditarContratoOrcamento) {
			this.orcamentoComFornecedores = orcamentoComFornecedores;
			this.telaEditarContratoOrcamento = telaEditarContratoOrcamento;
			this.editar = editar;
			
		}
		
		public void actionPerformed(ActionEvent e) {
			OrcamentoController.getInstance().populaArrayFornecedores(fornecedores);
					
			if (!fornecedores.isEmpty()) {
				janela.getCampoValor().setEnabled(true);
			}
			
			if (editar) {
				orcamentoComFornecedores.adicionaFornecedoresNaLista(fornecedores);
				
				new AuxTelaEditarOrcamento(orcamentoComFornecedores,"Editar Orçamento/Contrato");
			}else {
				
				janela.setVisible(true);
				
			}
			
			
			
			dispose();
			
			
			
			
			
			
		}

	}
	
	public class OuvinteBotaoAdicionar implements ActionListener{
		private TelaListaFornecedoresParaOrcamento janela;
		private Pessoa fornecedor;
		
		public OuvinteBotaoAdicionar(TelaListaFornecedoresParaOrcamento janela,Pessoa fornecedor) {
			this.janela = janela;
			this.fornecedor = fornecedor;
			
		}
		
		public void actionPerformed(ActionEvent e) {
			
			if (orcamento != null) {
				preencheFornecedores();
			}
			
			if(!fornecedores.contains(fornecedor) && !OrcamentoController.getInstance().getFornecedores().contains(fornecedor)) {
				fornecedores.add(fornecedor);
				JOptionPane.showMessageDialog(janela, "Fornecedor adicionado com sucesso");
			}else {
				JOptionPane.showMessageDialog(janela, "Fornecedor já adicionado");
			}
		}
		
	}
	
	public class OuvinteBotaoNovo implements ActionListener{
		
		private OrcamentoOuContrato orcamentoComFornecedores;
		
		public OuvinteBotaoNovo(OrcamentoOuContrato orcamentoComFornecedores) {
			this.orcamentoComFornecedores = orcamentoComFornecedores;
		}
	
		public void actionPerformed(ActionEvent e) {
			dispose();
			TelaCadastrarFornecedor telaCadastrarFornecedor	= new TelaCadastrarFornecedor("Cadastro Fornecedor");
			telaCadastrarFornecedor.getBotaoVoltar().removeActionListener(telaCadastrarFornecedor.ouvinteVoltar);
			telaCadastrarFornecedor.getBotaoVoltar().addActionListener(new ouvinteBotaoVoltarTelaCadastrarFornecedor(orcamentoComFornecedores,telaCadastrarFornecedor));
			
		}
	}
	
	public class ouvinteBotaoVoltarTelaCadastrarFornecedor implements ActionListener{
		
		private TelaCadastrarFornecedor telaCadastrarFornecedor;
		private OrcamentoOuContrato orcamentoComFornecedores;
		
		public ouvinteBotaoVoltarTelaCadastrarFornecedor(OrcamentoOuContrato orcamentoComFornecedores,TelaCadastrarFornecedor telaCadastrarFornecedor) {
			this.telaCadastrarFornecedor = telaCadastrarFornecedor;
			this.orcamentoComFornecedores = orcamentoComFornecedores;
		}
		
		public void actionPerformed(ActionEvent e) {
			
			telaCadastrarFornecedor.dispose();
			new TelaListaFornecedoresParaOrcamento(false,orcamentoComFornecedores,janela,"Lista De Fornecedores");
			
		}
		
	}
	
	public void preencheFornecedores() {
		for (Pessoa p: orcamento.getFornecedores()) {
			fornecedores.add(p);
		}
		
	}
	
}

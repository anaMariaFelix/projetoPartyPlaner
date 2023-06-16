package telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.OrcamentoOuContrato;
import model.Pacote;
import model.Pessoa;
import util.ComponentesDeJFrame;

public class AuxDetalharOrcamentoContrato extends JanelaPadrao{
	
	private OrcamentoOuContrato orcamentoContrato;
	private TelaCadastrarOrcamento telaCadastrarOrcamento;
	private String titulo;
	
	private JTable tabela;
	private DefaultTableModel modelo;
	
	private Object[] todosOsOrcamentosEContratos = null;
	
	private JButton reuniao;
	private JButton gerarPDF;
	private JButton gerarPlanilha;

	public AuxDetalharOrcamentoContrato(OrcamentoOuContrato orcamentoContrato) {
		super("Dados");
		this.orcamentoContrato = orcamentoContrato;
		configurarTela();
		adicionarBotoes();
		
	}

	private void adicionarBotoes() {
		
		
		
		gerarPDF = ComponentesDeJFrame.criarBotao("GerarPDF", 380, 450, 100, 30);
		telaCadastrarOrcamento.add(gerarPDF);
		
		gerarPlanilha = ComponentesDeJFrame.criarBotao("GerarPlanilha", 499, 450, 100, 30);
		telaCadastrarOrcamento.add(gerarPlanilha);
		
		reuniao = ComponentesDeJFrame.criarBotao("Reunião", 616, 450, 100, 30); 
		if (orcamentoContrato.isFoiContradoOuNao()) {
			telaCadastrarOrcamento.add(reuniao);	
		}
	}

	private void configurarTela() {
		telaCadastrarOrcamento = new TelaCadastrarOrcamento("Dados");
		
		telaCadastrarOrcamento.getTitulo().setText("Dados do Orçamento/Contrato");
		
		telaCadastrarOrcamento.getEmailClienteAssociado().setBounds(50, 100, 100, 30);
		telaCadastrarOrcamento.getInformacaoCliente().setBounds(115, 100, 225, 30);
		telaCadastrarOrcamento.getCampoEmailCliente().setBounds(50, 130, 280, 30);
		telaCadastrarOrcamento.getCampoEmailCliente().setText(orcamentoContrato.getClienteAssociado().getEmail());
		telaCadastrarOrcamento.getCampoEmailCliente().setEnabled(false);

		telaCadastrarOrcamento.getNomeEvento().setBounds(50, 170, 200, 30);
		telaCadastrarOrcamento.getCampoNomeEvento().setText(orcamentoContrato.getNomeDoEvento());
		telaCadastrarOrcamento.getCampoNomeEvento().setBounds(50, 200, 280, 30);
		telaCadastrarOrcamento.getCampoNomeEvento().setEnabled(false);

		telaCadastrarOrcamento.getDataEHoraEvento().setBounds(50, 240, 130, 30);
		telaCadastrarOrcamento.getCampoDataEHoraEvento().setText(AuxTelaEditarOrcamento.mudarDeLocalDateTimeParaString(orcamentoContrato.getDataEHoraDoEvento()));
		telaCadastrarOrcamento.getCampoDataEHoraEvento().setBounds(50, 270, 280, 30);
		telaCadastrarOrcamento.getCampoDataEHoraEvento().setEnabled(false);
		
		telaCadastrarOrcamento.getLocalEvento().setBounds(50, 310, 100, 30);
		telaCadastrarOrcamento.getCampoLocalEvento().setText(orcamentoContrato.getLocalDoEvento());
		telaCadastrarOrcamento.getCampoLocalEvento().setBounds(50, 340, 280, 30);
		telaCadastrarOrcamento.getCampoLocalEvento().setEnabled(false);

		telaCadastrarOrcamento.getInformacao().setBounds(140, 385, 225, 20);
		telaCadastrarOrcamento.getTamanhoEvento().setBounds(50, 380, 90, 30);
		telaCadastrarOrcamento.getCampoTamanhoEvento().setText(orcamentoContrato.getTamanho());
		telaCadastrarOrcamento.getCampoTamanhoEvento().setBounds(50, 415, 280, 30);
		telaCadastrarOrcamento.getCampoTamanhoEvento().setEnabled(false);

		telaCadastrarOrcamento.getValor().setBounds(50, 450, 330, 35);	
		telaCadastrarOrcamento.getCampoValor().setText(orcamentoContrato.getValor());
		telaCadastrarOrcamento.getCampoValor().setBounds(50, 490, 280, 30);
		telaCadastrarOrcamento.getCampoValor().setEnabled(false);

		telaCadastrarOrcamento.getBotaoVoltar().removeActionListener(telaCadastrarOrcamento.getOuvinteVoltar());
		telaCadastrarOrcamento.getBotaoVoltar().setBounds(50, 535, 110, 30);
		telaCadastrarOrcamento.getBotaoVoltar().addActionListener(new OuvinteBotaoVoltar(telaCadastrarOrcamento));

		
		telaCadastrarOrcamento.getBotaoSalvar().removeActionListener(telaCadastrarOrcamento.getSalvar());
		telaCadastrarOrcamento.getBotaoSalvar().setBounds(220, 535, 110, 30);
		telaCadastrarOrcamento.getBotaoSalvar().setVisible(false);

		telaCadastrarOrcamento.getBotaoAdicionarFornecedores().setVisible(false);

		adicionarJTable();
	}
	
	protected void adicionarJTable() {
		modelo = new DefaultTableModel();
		modelo.addColumn("Nome");
		
		if (!orcamentoContrato.getFornecedores().isEmpty()) {
			todosOsOrcamentosEContratos = orcamentoContrato.getFornecedores().toArray();
		}else {
			todosOsOrcamentosEContratos = orcamentoContrato.getPacotesDeFornecedores().toArray();
		}
		
		
		tabela = new JTable(modelo);
		
		JScrollPane painelTabela = new JScrollPane(tabela);
		painelTabela.setBounds(380, 128, 335, 318);
		telaCadastrarOrcamento.add(painelTabela);
		
		preencherTabela(todosOsOrcamentosEContratos);
		
	}
	
	public void preencherTabela(Object[] orcamentosEContratos) {
		for (Object o : orcamentosEContratos) {
			Object[] linha = new Object[2];
			
			
			
			if (o instanceof Pessoa) {
				Pessoa fisicoOuJuridico = (Pessoa) o;
				linha[0] = fisicoOuJuridico.getNome();
				
				
			} else {
				Pacote pacote = (Pacote) o;
				linha[0] = pacote.getNomeDoPacote();
			}
			
			
			modelo.addRow(linha);

		}

	}
	
	public class OuvinteBotaoVoltar implements ActionListener {
		private TelaCadastrarOrcamento janela;

		public OuvinteBotaoVoltar(TelaCadastrarOrcamento janela) {
			this.janela = janela;
		}

		public void actionPerformed(ActionEvent e) {
			janela.dispose();
			new TelaListarOrcamentosContratos("Lista de Orçamentos/Contratos");
		}

	}
	public class OuvinteBotaoReuniao implements ActionListener {
		private TelaCadastrarOrcamento janela;
		
		public OuvinteBotaoReuniao(TelaCadastrarOrcamento janela) {
			this.janela = janela;
		}
		
		public void actionPerformed(ActionEvent e) {
			janela.dispose();
			
			
		}
		
	}
	

}

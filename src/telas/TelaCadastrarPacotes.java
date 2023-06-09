package telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.FornecedorController;
import controller.PacotesController;
import model.FornecedorFisico;
import model.FornecedorJuridico;
import model.Pacote;
import model.Pessoa;
import util.ComponentesDeJFrame;
import util.ValidaEmail;
import util.ValidadorCPF;

public class TelaCadastrarPacotes extends JanelaPadrao {
	private String titulo;
	private JButton botaoVoltar;
	private JButton jbFornecedores;
	private JButton botaoSalvar;
	private ArrayList<Pessoa> listaDeFornecedores = new ArrayList();
	private JTextField campoNomeDoPacote;
	private JTextField campoValorPacote;
	private JTextArea tfDescricao;
	private JLabel lbTitulo;
	private JLabel nomeDoPacote;
	private JLabel escolhaFornecedores;
	private JLabel valorPacote;
	private JLabel caracteristicas;
	private JScrollPane painel;


	public TelaCadastrarPacotes(String titulo) {
		super(titulo);
		this.titulo = titulo;
		adicionarJLabel();
		adicionarJTextField();
		adicionarJButton();

		setVisible(true);
	}

	public String getTitulo() {
		return titulo;
	}
	
	

	public JScrollPane getPainel() {
		return painel;
	}

	public JLabel getEscolhaFornecedores() {
		return escolhaFornecedores;
	}

	public JLabel getNomeDoPacote() {
		return nomeDoPacote;
	}

	public JLabel getValorPacote() {
		return valorPacote;
	}


	public JLabel getCaracteristicas() {
		return caracteristicas;
	}

	public JLabel getLbTitulo() {
		return lbTitulo;
	}

	public JTextField getCampoNomeDoPacote() {
		return campoNomeDoPacote;
	}

	public JTextField getCampoValorPacote() {
		return campoValorPacote;
	}

	public JTextArea getTfDescricao() {
		return tfDescricao;
	}

	public JButton getBotaoVoltar() {
		return botaoVoltar;
	}

	public JButton getJbFornecedores() {
		return jbFornecedores;
	}

	public JButton getBotaoSalvar() {
		return botaoSalvar;
	}

	public ArrayList<Pessoa> getListaDeFornecedores() {
		return listaDeFornecedores;
	}

	private void adicionarJButton() {
		
		
		OuvinteBotaoFornecedores ouvinteBotaoFornecedores = new OuvinteBotaoFornecedores(this);
		jbFornecedores = ComponentesDeJFrame.criarBotao("Fornecedores", 275, 270, 225, 40);
		jbFornecedores.setToolTipText("Você deve escolher no minimo dois fornecedores");
		jbFornecedores.addActionListener(ouvinteBotaoFornecedores);
		add(jbFornecedores);

		OuvinteBotaoVoltar ouvinteVoltar = new OuvinteBotaoVoltar();
		botaoVoltar = ComponentesDeJFrame.criarBotao("Voltar", 275, 505, 100, 30);
		botaoVoltar.addActionListener(ouvinteVoltar);
		add(botaoVoltar);

		OuvinteBotaoSalvar ouvinteSalvar = new OuvinteBotaoSalvar(this);
		botaoSalvar = ComponentesDeJFrame.criarBotao("Salvar", 400, 505, 100, 30);
		botaoSalvar.addActionListener(ouvinteSalvar);
		add(botaoSalvar);

	}

	private void adicionarJTextField() {
		campoNomeDoPacote = ComponentesDeJFrame.criaJTextField(275, 200, 225, 30);
		add(campoNomeDoPacote);

		OuvinteCampoValor ouvinteCampoValor = new OuvinteCampoValor();
		campoValorPacote = ComponentesDeJFrame.criaJTextField(275, 350, 225, 30);
		campoValorPacote.addKeyListener(ouvinteCampoValor);
		add(campoValorPacote);

		tfDescricao = new JTextArea();// e um campo que suporta multiplas linhas(testo)
		tfDescricao.setText("Descrição: ");// palavra que aparece dentro do campo

		painel = new JScrollPane(tfDescricao);// JScrollPane é uma barra de rolagem que aparece quando o
															// texto n cabe mas no campo
		painel.setBounds(275, 430, 225, 60);

		tfDescricao.setLineWrap(true);// quando o texto n cabe mas na linha ele quebra o texto para a proxima linha
		tfDescricao.setWrapStyleWord(true);// quebra o texto tbm para a proxima linha mas ele coloca a palavra completa
											// na proxima linha
		add(painel);

		// ouvinte anonimo para quando o campo receber foco o "descriçao " que esta
		// dentro dele saia
		tfDescricao.addFocusListener(new FocusListener() {// FocusListener verifica se o campo recebel foco

			@Override
			public void focusLost(FocusEvent e) {// quando perde o foco
				JTextArea tf = (JTextArea) e.getSource();
				String texto = tf.getText();

				if (texto.trim().equals("")) {// nesse metodo quando o campo perde o foco se(if) ele tiver vazio ele
												// volta a ter a palvra "descrição " dentro dele
					tf.setText("Descrição: ");
					tf.repaint();
				}

			}

			@Override
			public void focusGained(FocusEvent e) {// quando ganha foco
				JTextArea tf = (JTextArea) e.getSource();// pega o jtextArea
				String texto = tf.getText();

				if (texto.equals("Descrição: ")) {// se for igual quando ele receber foco ai ele atualiza para
													// barnco(sem nada)
					tf.setText("");// passa a ser vazio
					tf.repaint();// atualiza
				}
			}
		});

	}

	private void adicionarJLabel() {
		lbTitulo = ComponentesDeJFrame.criaJLabel(titulo, 265, 70, 300, 50, 30);
		lbTitulo.setForeground(Color.BLACK); // cor do texto
		lbTitulo.setFont(new Font("Arial", Font.ITALIC, 30));
		add(lbTitulo);

		nomeDoPacote = ComponentesDeJFrame.criaJLabel("Nome Do Pacote", 275, 165, 200, 30, 20);
		add(nomeDoPacote);

		escolhaFornecedores = ComponentesDeJFrame.criaJLabel("Escolha os Fornecedores ", 275, 235, 250, 30, 15);
		escolhaFornecedores.setFont(new Font("Arial", Font.ITALIC, 15));
		add(escolhaFornecedores);

		valorPacote = ComponentesDeJFrame.criaJLabel("Valor do Pacote", 275, 315, 200, 30, 20);
		add(valorPacote);

		caracteristicas = ComponentesDeJFrame.criaJLabel("Descrição do Pacote", 275, 390, 250, 30, 20);
		add(caracteristicas);

	}

	private class OuvinteBotaoVoltar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == botaoVoltar) {
				dispose();
				TelaMenu telaMenu = new TelaMenu("Tela Menu");
			}
		}

	}

	private class OuvinteCampoValor implements KeyListener {

		public void keyTyped(KeyEvent e) {
			char c = e.getKeyChar();
			
			
			
			if (Character.isLetter(c) && c != '.') {
				e.consume();
			}
		}

		public void keyPressed(KeyEvent e) {
			char c = e.getKeyChar();
			if (Character.isLetter(c) && c != '.') {

			}

		}

		public void keyReleased(KeyEvent e) {

		}

	}

	private class OuvinteBotaoFornecedores implements ActionListener {
		private TelaCadastrarPacotes janela;

		public OuvinteBotaoFornecedores(TelaCadastrarPacotes janela) {
			this.janela = janela;
		}

		public void actionPerformed(ActionEvent e) {
			Object[] todosFonecedores = FornecedorController.getInstance().obterTodosOsFornecedores().toArray();
			Pessoa fornecedorEscolhido = (Pessoa) JOptionPane.showInputDialog(janela, "Escolha um Fornecedor por vez",
					"fornecedores", JOptionPane.QUESTION_MESSAGE, null, todosFonecedores, todosFonecedores[0]);

			if (fornecedorEscolhido != null) {
				if (!getListaDeFornecedores().contains(fornecedorEscolhido)) {
					if (fornecedorEscolhido instanceof FornecedorFisico) {
						FornecedorFisico fisico = (FornecedorFisico) fornecedorEscolhido;
						janela.getListaDeFornecedores().add(fisico);
					} else {
						FornecedorJuridico juridico = (FornecedorJuridico) fornecedorEscolhido;
						janela.getListaDeFornecedores().add(juridico);
					}
					JOptionPane.showMessageDialog(janela, "Fornecedor adicionando com sucesso");

				} else {
					JOptionPane.showMessageDialog(janela, "Fornecedor já existente");
				}

			}

		}

	}

	private class OuvinteBotaoSalvar implements ActionListener {
		private TelaCadastrarPacotes janela;

		public OuvinteBotaoSalvar(TelaCadastrarPacotes janela) {
			this.janela = janela;
		}

		public void actionPerformed(ActionEvent e) {
			String nome = janela.getCampoNomeDoPacote().getText();
			String valor = janela.getCampoValorPacote().getText();
			String descricao = janela.getTfDescricao().getText();

			if (nome.isEmpty() || valor.isEmpty() || descricao.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos");

			} else if (janela.getListaDeFornecedores().isEmpty() || janela.getListaDeFornecedores().size() < 2) {
				JOptionPane.showMessageDialog(janela, "Você deve escolher no mínimo dois Fornecedores");

			} else {
				Pacote pacote = new Pacote(nome, valor, descricao, janela.getListaDeFornecedores());
				
				if (PacotesController.getInstance().adicionarPacote(pacote)) {
					JOptionPane.showMessageDialog(janela, "Pacote cadastrado com sucesso!");
					listaDeFornecedores = new ArrayList<Pessoa>();
					janela.dispose();
					TelaMenu telaMenu = new TelaMenu("Tela de Menu");

				} else {
					JOptionPane.showMessageDialog(janela, "Já existe Pacote com esse nome, informe novamente");
				}
			}

		}

	}

}

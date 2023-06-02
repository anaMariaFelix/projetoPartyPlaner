package telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import baseDedados.CentralDeInformacoes;
import baseDedados.Persistencia;
import controller.ClienteController;
import model.ClienteFisico;
import model.ClienteJuridico;
import model.Pessoa;
import ouvintes.OuvinteBotaoSalvar;
import util.ComponentesDeJFrame;
import util.ValidaEmail;
import util.ValidadorCPF;
import util.ValidarCNPJ;

public class TelaCadastrarCliente extends JanelaPadrao {
	private String titulo;
	private JLabel lbTitulo;
	private JTextField campoNomeCompleto;
	private JFormattedTextField campoTelefone;
	private JTextField campoEmail;
	private JFormattedTextField campoCPF;
	private JFormattedTextField campoCNPJ;
	private JRadioButton pessoaFisica;
	private JRadioButton pessoaJuridica;
	private JButton botaoSalvar;
	private JButton botaoVoltar;
	protected OuvinteBotaoSalvar ouvinteSalvar;
	

	public TelaCadastrarCliente(String titulo) {
		super(titulo);
		this.titulo = titulo;
		adicionarJLabel();
		adicionarCampoJTextField();
		adicionarRadioButtons();
		adicionarJButton();
		setVisible(true);
	}

	
	public JLabel getLbTitulo() {
		return lbTitulo;
	}



	public JButton getBotaoSalvar() {
		return botaoSalvar;
	}

	public JButton getBotaoVoltar() {
		return botaoVoltar;
	}

	public JRadioButton getPessoaFisica() {
		return pessoaFisica;
	}

	public JRadioButton getPessoaJuridica() {
		return pessoaJuridica;
	}

	public JTextField getCampoNomeCompleto() {
		return campoNomeCompleto;
	}

	public JTextField getCampoTelefone() {
		return campoTelefone;
	}

	public JTextField getCampoEmail() {
		return campoEmail;
	}

	public JFormattedTextField getCampoCPF() {
		return campoCPF;
	}

	public JFormattedTextField getCampoCNPJ() {
		return campoCNPJ;
	}

	private void adicionarJLabel() {

		JLabel lbTitulo = ComponentesDeJFrame.criaJLabel(titulo,0, 70, 800, 50,30);
		lbTitulo.setHorizontalAlignment(JLabel.CENTER); // alinha o texto no centro
		lbTitulo.setForeground(Color.BLACK); // cor do texto
		lbTitulo.setFont(new Font("Arial", Font.ITALIC, 30));
		add(lbTitulo);

		JLabel jlNomeCompleto = ComponentesDeJFrame.criaJLabel("Nome Completo",280, 180, 150, 30,20);
		add(jlNomeCompleto);

		JLabel jlTelefone = ComponentesDeJFrame.criaJLabel("Telefone",280, 250, 130, 30,20);
		add(jlTelefone);

		JLabel jlEmail = ComponentesDeJFrame.criaJLabel("Email",280, 320, 130, 30,20);
		add(jlEmail);

	}
	
	private class ouvinteBotaoVoltar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == botaoVoltar) {
				dispose();
				TelaMenu telaMenu = new TelaMenu("Tela Menu");
			}
		}
		
	}

	protected class OuvinteBotaoSalvar implements ActionListener {
		private TelaCadastrarCliente janela;

		public OuvinteBotaoSalvar(TelaCadastrarCliente janela) {
			this.janela = janela;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			String nome = janela.getCampoNomeCompleto().getText();
			String telefone = janela.getCampoTelefone().getText().replace("(", "").replace(")", "").replace("-", "").trim();
			String email = janela.getCampoEmail().getText();
			Pessoa cliente = null;

	
		
				if (pessoaFisica.isSelected()) {
					String cpf = removerMacaraCampoCPF(janela.getCampoCPF());
					
					if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty() || cpf.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos");
						
					}else if (!ValidaEmail.emailValidator(email)) {
						JOptionPane.showMessageDialog(janela, "Email inválido, informe novamente"); 
						
					}else if (!ValidadorCPF.isCPF(cpf)) {
						JOptionPane.showMessageDialog(janela, "O CPF não é válido, informe novamente");
						
					} else {
						cliente = new ClienteFisico(nome, null, telefone, email, cpf);
						
						if (ClienteController.adicionarCliente(cliente)) {
							JOptionPane.showMessageDialog(janela, "Cliente cadastrado com sucesso!");
							janela.dispose();
							TelaMenu telaMenu = new TelaMenu("Tela de Menu");
							
						} else {
							JOptionPane.showMessageDialog(janela,
									"Já existe cliente com esse email, informe novamente");
						}

					}

				} else if (pessoaJuridica.isSelected()) {
					String cnpj = removerMascaraCampoCNPJ(janela.getCampoCNPJ());
					
					if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty() || cnpj.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos");
						
					}else if (!ValidaEmail.emailValidator(email)) {
						JOptionPane.showMessageDialog(janela, "Email inválido, informe novamente"); 
						
					} else if (!ValidarCNPJ.isCNPJ(cnpj)) {
						JOptionPane.showMessageDialog(janela, "O CNPJ não é válido, informe novamente");
						
					} else {
						cliente = new ClienteJuridico(nome, null, telefone, email, cnpj);
						
						if (ClienteController.adicionarCliente(cliente)) {
							JOptionPane.showMessageDialog(janela, "Cliente cadastrado com sucesso!");

							janela.dispose();
							TelaMenu telaMenu = new TelaMenu("Tela de Menu");
							
						} else {
							JOptionPane.showMessageDialog(janela,"Já existe cliente com esse email, informe novamente");
						}
					}
				}
			
		}

	}

	public String removerMacaraCampoCPF(JTextField cpf) {
		return cpf.getText().replace(".", "").replace("-", "").trim();
	}

	public String removerMascaraCampoCNPJ(JTextField cnpj) {

		return cnpj.getText().replace(".", "").replace("-", "").replace("/", "").trim();
	}
	
		
	private void adicionarJButton() {

		
		ouvinteBotaoVoltar ouvinteVoltar = new ouvinteBotaoVoltar();
		botaoVoltar = ComponentesDeJFrame.criarBotao("Voltar",280, 500, 100, 30);
		botaoVoltar.addActionListener(ouvinteVoltar);
		add(botaoVoltar);

		
		ouvinteSalvar = new OuvinteBotaoSalvar(this);
		botaoSalvar = ComponentesDeJFrame.criarBotao("Salvar",405, 500, 100, 30);
		botaoSalvar.addActionListener(ouvinteSalvar);
		add(botaoSalvar);

	}

	private class OuvinteDoNome implements KeyListener {

		// esse metodo é chamado quando componente que o objeto tiver ouvindo dentro
		// dele o botao dentro do campo
		@Override
		public void keyPressed(KeyEvent e) {
			char c = e.getKeyChar();
			if (!Character.isLetter(c) && c != ' ') {
				// e.consume();//ignora a letra digitada
			}
		}

		// é chamado quando o botao que esta sento apertado for soltado
		public void keyReleased(KeyEvent e) {

		}

		// chamado quando o botao ja foi apertado e soltado, quando as coisas sao
		// digitadas
		public void keyTyped(KeyEvent e) {
			char c = e.getKeyChar(); // retorna o caracter da tecla que foi precionada

			if (!Character.isLetter(c) && c != ' ') {// Character.isLetter verifica se oq foi digitado é uma letra
				e.consume();// ignora a letra digitada
				// esse if é para que so seja posivel digitar letras dentro do campo
				// se o usuario digitar qualquer coisa que n seja letra ele n é aceito e nem
				// visivel no campo
			}
		}
	}

	public void adicionarCampoJTextField() {
		OuvinteDoNome ouvinte = new OuvinteDoNome();
		campoNomeCompleto = ComponentesDeJFrame.criaJTextField(280, 210, 225, 30);
		campoNomeCompleto.addKeyListener(ouvinte);
		add(campoNomeCompleto);

		try {
			MaskFormatter mascaraDeData = new MaskFormatter("(##)#####-####");
			campoTelefone = new JFormattedTextField(mascaraDeData);
			campoTelefone.setBounds(280, 280, 225, 30);
			// campoTelefone.addKeyListener(ouvinte);
			add(campoTelefone);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		campoEmail = ComponentesDeJFrame.criaJTextField(280, 350, 225, 30);
		add(campoEmail);

	}

	public void adicionarRadioButtons() {
		ouvinteRadioButton ouvinte = new ouvinteRadioButton();

		pessoaFisica = ComponentesDeJFrame.criarRadioButtons("Fisico", true,280, 380, 70, 30,15);
		pessoaFisica.addActionListener(ouvinte);

		pessoaJuridica = ComponentesDeJFrame.criarRadioButtons("Juridico", false,360, 380, 120, 30,15);
		pessoaJuridica.addActionListener(ouvinte);
		add(pessoaFisica);
		add(pessoaJuridica);

		ButtonGroup bg = new ButtonGroup();// nao deixa marca os dois raidios button ao msm tempo apenas um deles
		bg.add(pessoaFisica);
		bg.add(pessoaJuridica);

		pessoaFisica.doClick();// o focu começa no fisico e esse doclick ja deixa o campo visivel
	}

	private class ouvinteRadioButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			MaskFormatter mascaraDeCPF;
			MaskFormatter mascaraDeCNPJ;

			JLabel cpf = new JLabel("CPF/CNPJ");
			cpf.setBounds(280, 415, 225, 30);
			cpf.setFont(new Font("Arial", Font.ITALIC, 15));
			add(cpf);
			try {
				if (pessoaFisica.isSelected()) {
					if (campoCNPJ != null) {
						getContentPane().remove(campoCNPJ);

					}

					mascaraDeCPF = new MaskFormatter("###.###.###-##");
					campoCPF = new JFormattedTextField(mascaraDeCPF);
					campoCPF.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
					campoCPF.setBounds(280, 440, 225, 30);
					campoCPF.repaint();// atualiza
					add(campoCPF);
				} else {
					if (campoCPF != null) {
						getContentPane().remove(campoCPF);

					}
					mascaraDeCNPJ = new MaskFormatter("##.###.###/####-##");
					campoCNPJ = new JFormattedTextField(mascaraDeCNPJ);
					campoCNPJ.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
					campoCNPJ.setBounds(280, 440, 225, 30);
					campoCNPJ.repaint();// atualiza
					add(campoCNPJ);
				}

			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			repaint();
		}

	}

}
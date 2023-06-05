package telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.itextpdf.text.Font;

import baseDedados.CentralDeInformacoes;
import controller.ClienteController;
import controller.FornecedorController;
import controller.ServicoController;
import model.ClienteFisico;
import model.ClienteJuridico;
import model.FornecedorFisico;
import model.FornecedorJuridico;
import model.Pessoa;
import util.ComponentesDeJFrame;
import util.ValidaEmail;
import util.ValidadorCPF;
import util.ValidarCNPJ;

public class TelaCadastrarFornecedor extends TelaCadastrarCliente {

	private JButton botaoServicos;
	private JLabel jlServicos;
	private ArrayList<String> listaDeServicos = new ArrayList();
	private OuvinteBotaoSalvarFornecedor ouvinteSalvarFornecedor;

	public TelaCadastrarFornecedor(String titulo) {
		super(titulo);
		adicionarJButton();
		adicionarJLabel();
		getBotaoSalvar().removeActionListener(ouvinteSalvar);
	}

	public OuvinteBotaoSalvarFornecedor getOuvinteSalvarFornecedor() {
		return ouvinteSalvarFornecedor;
	}

	public ArrayList<String> getListaDeServicos() {
		return listaDeServicos;
	}
	
	public void setListaDeServicos(ArrayList<String> listaDeServicos) {
		this.listaDeServicos = listaDeServicos;
	}

	public JButton getBotaoServicos() {
		return botaoServicos;
	}

	public JLabel getJlServicos() {
		return jlServicos;
	}

	private void adicionarJButton() {
		OuvinteBotaoSalvarFornecedor ouvinteSalvarFornecedor = new OuvinteBotaoSalvarFornecedor(this);

		getBotaoVoltar().setBounds(280, 550, 100, 30);

		getBotaoSalvar().addActionListener(ouvinteSalvarFornecedor);
		getBotaoSalvar().setBounds(405, 550, 100, 30);

		OuvinteBotaoServicos ouvinteServico = new OuvinteBotaoServicos(this);
		botaoServicos = ComponentesDeJFrame.criarBotao("Serviços", 405, 500, 100, 30);
		botaoServicos.addActionListener(ouvinteServico);
		add(botaoServicos);

	}

	private void adicionarJLabel() {
		jlServicos = ComponentesDeJFrame.criaJLabel("Adicionar Serviços", 280, 500, 150, 30, 10);
		add(jlServicos);
	}

	private class OuvinteBotaoSalvarFornecedor implements ActionListener {
		private TelaCadastrarFornecedor janela;

		public OuvinteBotaoSalvarFornecedor(TelaCadastrarFornecedor janela) {
			this.janela = janela;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			String nome = janela.getCampoNomeCompleto().getText();
			String telefone = janela.getCampoTelefone().getText().replace("(", "").replace(")", "").replace("-", "")
					.trim();
			String email = janela.getCampoEmail().getText();
			Pessoa fornecedor = null;

			if (e.getSource() == janela.getBotaoSalvar()) {
				if (janela.getPessoaFisica().isSelected()) {
					String cpf = janela.removerMacaraCampoCPF(janela.getCampoCPF());

					if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty() || cpf.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos");

					}else if (!ValidaEmail.emailValidator(email)) {
						JOptionPane.showMessageDialog(janela, "Email inválido, informe novamente");

					} else if (!ValidadorCPF.isCPF(cpf)) {
						JOptionPane.showMessageDialog(janela, "O CPF não é válido, informe novamente");

					} else if (janela.getListaDeServicos().isEmpty()) {
						JOptionPane.showMessageDialog(janela, "Você deve fornecer ao menos um serviço");
					} else {
						fornecedor = new FornecedorFisico(nome, null, telefone, cpf, email,janela.getListaDeServicos());
						listaDeServicos = new ArrayList<String>();
						if (FornecedorController.getInstance().adicionarFornecedor(fornecedor)) {
							JOptionPane.showMessageDialog(janela, "Fornecedor cadastrado com sucesso!");
							janela.dispose();
							TelaMenu telaMenu = new TelaMenu("Tela de Menu");

						}else {
							JOptionPane.showMessageDialog(janela,
									"Já existe fornecedor com esse email, informe novamente");
						}
					}

				} else if (janela.getPessoaJuridica().isSelected()) {
					String cnpj = removerMascaraCampoCNPJ(janela.getCampoCNPJ());

					if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty() || cnpj.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos");

					}else if (!ValidaEmail.emailValidator(email)) {
						JOptionPane.showMessageDialog(janela, "Email inválido, informe novamente");

					} else if (!ValidarCNPJ.isCNPJ(cnpj)) {
						JOptionPane.showMessageDialog(janela, "O CNPJ não é válido, informe novamente");

					} else if (janela.getListaDeServicos().isEmpty()) {
						JOptionPane.showMessageDialog(janela, "Você deve fornecer ao menos um serviço");
						
					} else {
						fornecedor = new FornecedorJuridico(nome, null, telefone, email, cnpj,janela.getListaDeServicos());
						listaDeServicos = new ArrayList<String>();
						
						if (FornecedorController.getInstance().adicionarFornecedor(fornecedor)) {
							JOptionPane.showMessageDialog(janela, "Fornecedor cadastrado com sucesso!");
							janela.dispose();
							TelaMenu telaMenu = new TelaMenu("Tela de Menu");

						} else {
							JOptionPane.showMessageDialog(janela,
									"Já existe fornecedor com esse email, informe novamente");
						}
					}
				}
			}

		}
	}

	private class OuvinteBotaoServicos implements ActionListener {

		private TelaCadastrarFornecedor janela;

		public OuvinteBotaoServicos(TelaCadastrarFornecedor janela) {
			this.janela = janela;
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == botaoServicos) {
				Object[] servicos = ServicoController.getInstance().pegaServicos().toArray();
				String servicoEscolhido = (String) JOptionPane.showInputDialog(janela, "Escolha um servico por vez",
						"Servicos", JOptionPane.QUESTION_MESSAGE, null, servicos, servicos[0]);
				if(servicoEscolhido != null) {
					if(!getListaDeServicos().contains(servicoEscolhido)) {
						janela.getListaDeServicos().add(servicoEscolhido);
						JOptionPane.showMessageDialog(janela, "Serviço adicionando com sucesso");
					}else {
						JOptionPane.showMessageDialog(janela, "Serviço já existente");
					}
				}
				
			}

		}

	}
	
	

}

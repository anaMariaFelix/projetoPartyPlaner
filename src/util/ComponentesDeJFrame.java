package util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class ComponentesDeJFrame {
	
	public static JButton criarBotao(String titulo,int x,int y,int comprimento,int largura) {
		JButton botao = new JButton(titulo);
		botao.setBounds(x, y, comprimento, largura);
		return botao;
	}
	
	public static JButton criarBotoesDoMenu(String titulo,int x,int y,int comprimento,int largura) {
		JButton botao = new JButton(titulo);
		botao.setBackground(Color.LIGHT_GRAY);
		botao.setFont(new Font("Arial",Font.CENTER_BASELINE,20));
		botao.setBounds(x, y, comprimento, largura);
		return botao;
	}
	
	public static  JRadioButton criarRadioButtons(String nome,boolean marcado,int x,int y,int comprimento,int largura,int tamanho) {
		JRadioButton jradioButton = new JRadioButton(nome, marcado);
		jradioButton.setFont(new Font("Arial", Font.BOLD, tamanho));
		jradioButton.setBounds(x, y, comprimento, largura);
		return jradioButton;
		
	}
	
	public static JLabel criaJLabel(String titulo,int x,int y,int comprimento,int largura,int tamanho) {
		JLabel rotulo = new JLabel(titulo);
		rotulo.setBounds(x, y, comprimento, largura);
		rotulo.setFont(new Font("Arial", Font.BOLD, tamanho));
		return rotulo;
	}
	
	public static JTextField criaJTextField(int x,int y,int comprimento,int largura) {
		JTextField campo = new JTextField();
		campo.setBounds(x, y, comprimento, largura);
		return campo;
	}

}

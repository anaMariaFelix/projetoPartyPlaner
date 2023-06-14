package telas;

import java.awt.Color;

import javax.swing.JFrame;

public class JanelaPadrao extends JFrame{
	
	public JanelaPadrao(String titulo) {
		setTitle(titulo);
		setSize(800,650);
		setLocationRelativeTo(null);//centro
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(230, 230, 230));
		setResizable(false);

	}
	


}

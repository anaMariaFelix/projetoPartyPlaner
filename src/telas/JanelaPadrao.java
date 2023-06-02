package telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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

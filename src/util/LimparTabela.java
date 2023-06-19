package util;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class LimparTabela {

	
	public static void limparTabela(DefaultTableModel modelo, JTable tabela) {
		int cont = modelo.getRowCount();
		for (int i = 0; i < cont; i++) {
			modelo.removeRow(0);
		}
		tabela.repaint();
	}
	
	
}

package util;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class ButtonEditor extends DefaultCellEditor {
	private JButton button;
    private boolean isPushed;
    
    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
    	this.isPushed = true;
    	button = (JButton) value;
    	return button;

    }

    @Override
    public Object getCellEditorValue() {
    	this.isPushed = false;
        return button;
    }

    @Override
    public boolean stopCellEditing() {
    	this.isPushed = false;
        return super.stopCellEditing();
    }
}

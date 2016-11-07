import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class ChkBoxEditor extends DefaultCellEditor implements ItemListener {

	JTable desk;
	JCheckBox chkbox;
	SettingToField field;
	int col;
	public ChkBoxEditor(JCheckBox arg0, SettingToField field) {
		super(arg0);
		this.field = field;
	}
	@Override
	public Component getTableCellEditorComponent
	(JTable table, Object value, boolean isSelected, int row, int column)
	{
		if(value == null)
			return null;
		chkbox = (JCheckBox)value;
		chkbox.addItemListener(this);
		
		col = column;
		desk = table;
		return (Component)value;
		//return super.getTableCellEditorComponent(table, value, isSelected, row, column);
	}
	
	@Override
	public Object getCellEditorValue() {
		chkbox.removeItemListener(this);
		return chkbox;
		//return super.getCellEditorValue();
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		field.addColumn(col);
		field.setCellOption(desk);
	}

}

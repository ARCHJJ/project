import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class CmbBoxEditor extends DefaultCellEditor implements ItemListener {

	JTable desk;
	JComboBox combo;
	CmbBoxField field;
	int col;
	public CmbBoxEditor(JComboBox comboBox, CmbBoxField field) {
		super(comboBox);
		this.field = field;
	}
	public Component getTableCellEditorComponent
	(JTable table, Object value, boolean isSelected, int row, int column)
	{
		if(value == null)
			return null;
		
		combo = (JComboBox)value;
		combo.addItemListener(this);
		desk = table;
		col = column;
		return (Component)value;
	}
	@Override
	public Object getCellEditorValue() {
		combo.removeItemListener(this);
		return combo;
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if(field != null)
		{
			field.addColumn(col);
			field.setCellOption(desk);
		}
		
	}
}

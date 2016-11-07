import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class CmbBoxEditor extends DefaultCellEditor implements ItemListener {

	JComboBox combo;
	public CmbBoxEditor(JComboBox comboBox) {
		super(comboBox);
	}
	public Component getTableCellEditorComponent
	(JTable table, Object value, boolean isSelected, int row, int column)
	{
		if(value == null)
			return null;
		
		combo = (JComboBox)value;
		combo.addItemListener(this);
		
		return (Component)value;
	}
	@Override
	public Object getCellEditorValue() {
		combo.removeItemListener(this);
		return combo;
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		
	}
}

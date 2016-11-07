import java.awt.Component;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

class ChkBoxRenderer implements TableCellRenderer
{
	@Override
	public Component getTableCellRendererComponent
	(JTable table, Object value, boolean isSelected, boolean hasFocus,int row, int column)
	{
		
		if(value == null)
			return null;
		
		JCheckBox box = (JCheckBox)value;
		box.setHorizontalAlignment(JLabel.CENTER);
		
		return (Component)value;		
	}
}

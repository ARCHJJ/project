import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * @brief JTable���� �޺��ڽ��� ���̰� ���ִ� ��������
 */
class CmbBoxRenderer implements TableCellRenderer{

	/**
	 * @brief 
	 * @param JTable table
	 * @param Object value
	 * @param boolean isSelected
	 * @param boolean hasFocus
	 * @param int row
	 * @param int column
	 * @return
	 */
	@Override
	public Component getTableCellRendererComponent
	(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		if(value == null)
			return null;
		
		return (Component)value;
	}

}

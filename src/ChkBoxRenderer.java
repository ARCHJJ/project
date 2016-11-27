import java.awt.Component;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 * @brief JTable���� üũ�ڽ��� ���̰� ���ִ� ��������
 */
class ChkBoxRenderer implements TableCellRenderer
{
	/**
	 * @brief ���̺��������� ������Ʈ�� �����´�.
	 * @param JTable table
	 * @param Object value
	 * @param boolean isSelected : ���õǾ������� ��Ÿ����
	 * @param boolean hasFocus
	 * @param int row : ��
	 * @param int column : ��
	 * @return
	 */
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

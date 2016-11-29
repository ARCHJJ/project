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
	 * @brief ���̺��������� ������Ʈ�� ������
	 * @param JTable table		 : �������Ͱ� ����� JTable
	 * @param Object value		 : �������Ͱ� ����� JTable�� ��
	 * @param boolean isSelected : JTable�� ���õǾ����� �Ǵ�
	 * @param int row			 : �������Ͱ� ����� JTable�� ��
	 * @param int column 		 : �������Ͱ� ����� JTable�� ��
	 * @return value 			 : ������Ʈ value ���� �������ش�
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

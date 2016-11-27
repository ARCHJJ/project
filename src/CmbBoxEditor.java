import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 * @brief JTable���� �޺��ڽ��� ����� �� �ְ� �ϴ� ��������
 */
public class CmbBoxEditor extends DefaultCellEditor implements ItemListener {

	//!�޺��ڽ��� �̷���� ���̺� ���� ���۷����� ����
	JTable desk;
	//!���̺��� ������ ������Ʈ
	JComboBox combo;
	//!�޺��ڽ��� ����� �� �ְ� �������ֱ�����
	SettingToField field;
	//!�� 
	int col;
	
	/**
	 * @brief ������
	 * @param JComboBox comboBox
	 * @param SettingToField field
	 */
	public CmbBoxEditor(JComboBox comboBox, SettingToField field) {
		super(comboBox);
		this.field = field;
	}
	
	/**
	 * @brief ���̺��������� �ĺ���Ʈ�� ������
	 * @param JTable table
	 * @param Object value
	 * @param boolean isSelected
	 * @param int row : ��
	 * @param int column : ��
	 * @return value : ������Ʈ value ���� �������ش�
	 */
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
	
	/**
	 * @brief �� ������ ���� �˷��ش�.
	 * @return combo : ���̺��� �����ϴ� ������Ʈ
	 */
	@Override
	public Object getCellEditorValue() {
		combo.removeItemListener(this);
		return combo;
	}
	
	/**
	 * @brief ���� �࿡ �ִ� �޺��ڽ��� ���� ��ȭ�� ���� �� field�� ���� �ϳ� �߰��ȴ�.
	 * @param ItemEvent arg0 : Event�� �߻��ߴ����� �������ش�.
	 */
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if(field != null)
		{
			field.addColumn(col);
			field.setCellOption(desk);
		}
		
	}
}

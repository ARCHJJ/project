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

	//! �޺��ڽ��� �̷���� JTable�� ���۷����� ����
	JTable desk;
	
	//! JTable���� ��� �� �⺻ �޺��ڽ�
	JComboBox combo;
	
	//! �޺��ڽ��� ����� JTable�� TableModel
	SettingToField field;
	
	//! JTable�� Column 
	int col;
	
	/**@brief ������
	 * @param JComboBox arg0	: �޺��ڽ��� �̷���� �������͸� ����  
	 * @param ChkBoxField field : �޺��ڽ��� ����� JTable�� TableModel
	 */
	public CmbBoxEditor(JComboBox arg0, SettingToField field) {
		super(arg0);
		this.field = field;
	}
	
	/**
	 * @brief ���̺��������� ������Ʈ�� ������
	 * @param JTable table		 : �������Ͱ� ����� JTable
	 * @param Object value		 : �������Ͱ� ����� JTable�� ��
	 * @param boolean isSelected : JTable�� ���õǾ����� �Ǵ�
	 * @param int row			 : �������Ͱ� ����� JTable�� ��
	 * @param int column 		 : �������Ͱ� ����� JTable�� ��
	 * @return value 			 : ������Ʈ value ���� �������ش�
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
	 * @brief ������ ���� ���� �����Ѵ�.
	 * @return chkbox : ���� ����� �����۸����ʸ� ����� �����Ѵ�.
	 */
	@Override
	public Object getCellEditorValue() {
		combo.removeItemListener(this);
		return combo;
	}
	
	/**
	 * @brief ���� �࿡ �ִ� üũ�ڽ��� ���� ��ȭ�� ���� �� field�� ���� �ϳ� �߰��ȴ�.
	 * @param ItemEvent e : Event�� �߻��ߴ����� �������ش�.
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

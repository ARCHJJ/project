import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @brief JTable���� üũ�ڽ��� ����� �� �ְ� �ϴ� ��������
 */
class ChkBoxEditor extends DefaultCellEditor implements ItemListener {

	//! üũ�ڽ��� �̷���� JTable�� ���۷����� ����
	private JTable desk;
	
	//! JTable���� ��� �� �⺻ üũ�ڽ�
	private JCheckBox chkbox;
	
	//! üũ�ڽ��� ����� JTable�� TableModel
	private SettingToField field;
	
	//! JTable�� Column
	private int col;
	
	/**
	 * @brief ������
	 * @param JCheckBox arg0	: üũ�ڽ��� �̷���� �������͸� ����  
	 * @param ChkBoxField field : üũ�ڽ��� ����� JTable�� TableModel
	 */
	public ChkBoxEditor(JCheckBox arg0, SettingToField field) {
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
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column){
		if(value == null)
			return null;
		chkbox = (JCheckBox)value;
		chkbox.addItemListener(this);
		
		col = column;
		desk = table;
		return (Component)value;
		//return super.getTableCellEditorComponent(table, value, isSelected, row, column);
	}
	
	
	/**
	 * @brief ������ ���� ���� �����Ѵ�.
	 * @return chkbox : ���� ����� �����۸����ʸ� ����� �����Ѵ�.
	 */
	@Override
	public Object getCellEditorValue() {
		chkbox.removeItemListener(this);
		return chkbox;
		//return super.getCellEditorValue();
	}
	
	
	/**
	 * @brief ���� �࿡ �ִ� üũ�ڽ��� ���� ��ȭ�� ���� �� field�� ���� �ϳ� �߰��ȴ�.
	 * @param ItemEvent e : Event�� �߻��ߴ����� �������ش�.
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		field.addColumn(col);
		field.setCellOption(desk);
	}

}

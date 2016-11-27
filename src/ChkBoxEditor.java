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

	//!üũ�ڽ��� �̷���� ���̺� ���� ���۷����� ����
	JTable desk;
	//!���̺��� ������ ������Ʈ
	JCheckBox chkbox;
	//!üũ�ڽ��� ����� �� �ְ� �������ֱ�����
	ChkBoxField field;
	//!�÷�
	int col;
	
	/**
	 * @brief ������
	 * @param JCheckBox arg0
	 * @param ChkBoxField field
	 */
	public ChkBoxEditor(JCheckBox arg0, ChkBoxField field) {
		super(arg0);
		this.field = field;
	}
	
	/**
	 * @brief ���̺��������� ������Ʈ�� ������
	 * @param JTable table
	 * @param Object value
	 * @param boolean isSelected
	 * @param int row : ��
	 * @param int column : ��
	 * @return value : ������Ʈ value ���� �������ش�
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
	 * @brief �� ������ ���� �˷��ش�.
	 * @return chkbox : ���̺��� �����ϴ� ������Ʈ
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

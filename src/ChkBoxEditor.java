import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @brief JTable에서 체크박스를 사용할 수 있게 하는 셀에디터
 */
class ChkBoxEditor extends DefaultCellEditor implements ItemListener {

	//! 
	JTable desk;
	//!체크박스
	JCheckBox chkbox;
	//!
	ChkBoxField field;
	//!컬럼
	int col;
	
	/**
	 * @brief 생성자
	 * @param JCheckBox arg0
	 * @param ChkBoxField field
	 */
	public ChkBoxEditor(JCheckBox arg0, ChkBoxField field) {
		super(arg0);
		this.field = field;
	}
	
	/**
	 * @brief 
	 * @param JTable table
	 * @param Object value
	 * @param boolean isSelected
	 * @param int row
	 * @param int column
	 * @return 
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
	 * @brief 
	 */
	@Override
	public Object getCellEditorValue() {
		chkbox.removeItemListener(this);
		return chkbox;
		//return super.getCellEditorValue();
	}
	
	
	/**
	 * @brief 
	 * @param ItemEvent e
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		field.addColumn(col);
		field.setCellOption(desk);
	}

}

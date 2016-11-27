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

	//!체크박스로 이루어진 테이블에 대한 래퍼런스를 저장
	JTable desk;
	//!테이블을 구성할 컴포넌트
	JCheckBox chkbox;
	//!체크박스가 저장될 수 있게 세팅해주기위함
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
	 * @brief 테이블셀에디터의 컴포넌트를 가져옴
	 * @param JTable table
	 * @param Object value
	 * @param boolean isSelected
	 * @param int row : 행
	 * @param int column : 열
	 * @return value : 컴포넌트 value 값을 리턴해준다
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
	 * @brief 셀 에디터 값을 알려준다.
	 * @return chkbox : 테이블을 구성하는 컴포넌트
	 */
	@Override
	public Object getCellEditorValue() {
		chkbox.removeItemListener(this);
		return chkbox;
		//return super.getCellEditorValue();
	}
	
	
	/**
	 * @brief 현재 행에 있는 체크박스의 값에 변화가 있을 때 field의 열이 하나 추가된다.
	 * @param ItemEvent e : Event가 발생했는지를 전달해준다.
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		field.addColumn(col);
		field.setCellOption(desk);
	}

}

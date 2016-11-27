import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 * @brief JTable에서 콤보박스를 사용할 수 있게 하는 셀에디터
 */
public class CmbBoxEditor extends DefaultCellEditor implements ItemListener {

	//!콤보박스로 이루어진 테이블에 대한 레퍼런스를 저장
	JTable desk;
	//!테이블을 구성할 컴포넌트
	JComboBox combo;
	//!콤보박스가 저장될 수 있게 세팅해주기위함
	SettingToField field;
	//!열 
	int col;
	
	/**
	 * @brief 생성자
	 * @param JComboBox comboBox
	 * @param SettingToField field
	 */
	public CmbBoxEditor(JComboBox comboBox, SettingToField field) {
		super(comboBox);
		this.field = field;
	}
	
	/**
	 * @brief 테이블셀에디터의 컴보넌트를 가져옴
	 * @param JTable table
	 * @param Object value
	 * @param boolean isSelected
	 * @param int row : 행
	 * @param int column : 열
	 * @return value : 컴포넌트 value 값을 리턴해준다
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
	 * @brief 셀 에디터 값을 알려준다.
	 * @return combo : 테이블을 구성하는 컴포넌트
	 */
	@Override
	public Object getCellEditorValue() {
		combo.removeItemListener(this);
		return combo;
	}
	
	/**
	 * @brief 현재 행에 있는 콤보박스의 값에 변화가 있을 때 field의 열이 하나 추가된다.
	 * @param ItemEvent arg0 : Event가 발생했는지를 전달해준다.
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

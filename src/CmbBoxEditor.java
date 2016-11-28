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

	//! 콤보박스로 이루어진 JTable의 레퍼런스를 저장
	JTable desk;
	
	//! JTable에서 사용 될 기본 콤보박스
	JComboBox combo;
	
	//! 콤보박스가 적용될 JTable의 TableModel
	SettingToField field;
	
	//! JTable의 Column 
	int col;
	
	/**@brief 생성자
	 * @param JComboBox arg0	: 콤보박스로 이루어진 셀에디터를 설정  
	 * @param ChkBoxField field : 콤보박스가 적용될 JTable의 TableModel
	 */
	public CmbBoxEditor(JComboBox arg0, SettingToField field) {
		super(arg0);
		this.field = field;
	}
	
	/**
	 * @brief 테이블셀에디터의 컴포넌트를 가져옴
	 * @param JTable table		 : 셀에디터가 적용될 JTable
	 * @param Object value		 : 셀에디터가 적용될 JTable의 셀
	 * @param boolean isSelected : JTable이 선택되었는지 판단
	 * @param int row			 : 셀에디터가 적용될 JTable의 행
	 * @param int column 		 : 셀에디터가 적용될 JTable의 열
	 * @return value 			 : 컴포넌트 value 값을 리턴해준다
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
	 * @brief 선택한 셀의 값을 리턴한다.
	 * @return chkbox : 셀에 적용된 아이템리스너를 지우고 리턴한다.
	 */
	@Override
	public Object getCellEditorValue() {
		combo.removeItemListener(this);
		return combo;
	}
	
	/**
	 * @brief 현재 행에 있는 체크박스의 값에 변화가 있을 때 field의 열이 하나 추가된다.
	 * @param ItemEvent e : Event가 발생했는지를 전달해준다.
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

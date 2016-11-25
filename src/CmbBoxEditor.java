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

	//! 
	JTable desk;
	//! 
	JComboBox combo;
	//! 
	SettingToField field;
	//! 
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
	 * @brief 
	 * @param JTable table
	 * @param Object value
	 * @param boolean isSelected
	 * @param int row
	 * @param int column
	 * @return 
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
	 * @brief 
	 * @return 
	 */
	@Override
	public Object getCellEditorValue() {
		combo.removeItemListener(this);
		return combo;
	}
	
	/**
	 * @brief 
	 * @param ItemEvent arg0
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

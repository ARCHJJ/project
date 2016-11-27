import java.awt.Component;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 * @brief JTable에서 체크박스가 보이게 해주는 셀렌더러
 */
class ChkBoxRenderer implements TableCellRenderer
{
	/**
	 * @brief 테이블셀렌더러의 컴포넌트를 가져온다.
	 * @param JTable table
	 * @param Object value
	 * @param boolean isSelected : 선택되었는지를 나타내줌
	 * @param boolean hasFocus
	 * @param int row : 행
	 * @param int column : 열
	 * @return
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

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
	 * @brief 테이블셀에디터의 컴포넌트를 가져옴
	 * @param JTable table		 : 셀에디터가 적용될 JTable
	 * @param Object value		 : 셀에디터가 적용될 JTable의 셀
	 * @param boolean isSelected : JTable이 선택되었는지 판단
	 * @param int row			 : 셀에디터가 적용될 JTable의 행
	 * @param int column 		 : 셀에디터가 적용될 JTable의 열
	 * @return value 			 : 컴포넌트 value 값을 리턴해준다
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

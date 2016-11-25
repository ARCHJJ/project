import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @brief SettingToFeild와 SettingToKind의 부모클래스로써 두 자식클래스의 모델 역할을 하는 클래스이다. 
 */
abstract class Setting {
	//!
	protected DefaultTableModel tablemodel;
	//!
	protected Object[][] field;
	//!
	protected String[] header;
	
	/**
	 * @brief
	 */
	public Setting()
	{
		tablemodel = new DefaultTableModel();
	}
	
	/**
	 * @brief
	 * @return
	 */
	public DefaultTableModel getModel()
	{
		return tablemodel;
	}
			
}

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @brief SettingToFeild�� SettingToKind�� �θ�Ŭ�����ν� �� �ڽ�Ŭ������ �� ������ �ϴ� Ŭ�����̴�. 
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

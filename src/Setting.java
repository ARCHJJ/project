import javax.swing.table.DefaultTableModel;

/**
 * @brief SettingToField�� SettingToKind�� �θ�Ŭ�����ν� �� �ڽ�Ŭ������ �� ������ �ϴ� Ŭ�����̴�. 
 */
abstract class Setting {
	//!�⺻ ���̺� ��
	protected DefaultTableModel tablemodel;
	//!�ʵ�
	protected Object[][] field;
	//!���
	protected String[] header;
	
	/**
	 * @brief ������
	 * tablemodel �� �Ҵ��Ѵ�.
	 */
	public Setting()
	{
		tablemodel = new DefaultTableModel();
	}
	
	/**
	 * @brief tablemodel�� �����Ѵ�
	 * @return DefaultTableModel
	 */
	public DefaultTableModel getModel()
	{
		return tablemodel;
	}
			
}

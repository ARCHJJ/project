import javax.swing.table.DefaultTableModel;

/**
 * @brief SettingToField와 SettingToKind의 부모클래스로써 두 자식클래스의 모델 역할을 하는 클래스이다. 
 */
abstract class Setting {
	//!기본 테이블 모델
	protected DefaultTableModel tablemodel;
	//!필드
	protected Object[][] field;
	//!헤더
	protected String[] header;
	
	/**
	 * @brief 생성자
	 * tablemodel 을 할당한다.
	 */
	public Setting()
	{
		tablemodel = new DefaultTableModel();
	}
	
	/**
	 * @brief tablemodel을 리턴한다
	 * @return DefaultTableModel
	 */
	public DefaultTableModel getModel()
	{
		return tablemodel;
	}
			
}

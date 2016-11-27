import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @brief SettingToFeild와 SettingToKind의 부모클래스로써 두 자식클래스의 모델 역할을 하는 클래스이다. 
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
	 */
	public Setting()
	{
		tablemodel = new DefaultTableModel();
	}
	
	/**
	 * @brief 테이블 모델 호출
	 * @return tablemodel리턴
	 */
	public DefaultTableModel getModel()
	{
		return tablemodel;
	}
			
}

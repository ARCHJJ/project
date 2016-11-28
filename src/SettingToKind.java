/**
 * @brief Setting클래스를 상속받는 클래스. 프로그램 메인에서 특정악기를 선택했을 때 그 악기가 표현할 수 있는 음의 종류를 보여준다.
 */
class SettingToKind extends Setting{
	//! kinds  : 악기의 종류(개수)
	private int kinds;
	
	/**
	 * @brief 프로그램 메인에서 특정악기를 선택했을 때 그 악기가 표현할 수 있는 음의 종류를 불러옴
	 * @param String[] fileNames : 악기의 이름들이 저장되어 있는 String[]
	 */
	public SettingToKind(String[] fileNames)
	{
		super();
		kinds = fileNames.length;
		header = new String[]{"악기"};
		field = new Object[kinds][1];
		field[0][0] = fileNames[0];
		for(int i=0; i<kinds; i++)
		{
			field[i][0] = fileNames[i];   
		}
		tablemodel.setDataVector(field, header);
	}
}

/**
 * @brief SettingŬ������ ��ӹ޴� Ŭ����. ���α׷� ���ο��� Ư���Ǳ⸦ �������� �� �� �ǱⰡ ǥ���� �� �ִ� ���� ������ �����ش�.
 */
class SettingToKind extends Setting{
	//! kinds  : �Ǳ��� ����(����)
	private int kinds;
	
	/**
	 * @brief ���α׷� ���ο��� Ư���Ǳ⸦ �������� �� �� �ǱⰡ ǥ���� �� �ִ� ���� ������ �ҷ���
	 * @param String[] fileNames : �Ǳ��� �̸����� ����Ǿ� �ִ� String[]
	 */
	public SettingToKind(String[] fileNames)
	{
		super();
		kinds = fileNames.length;
		header = new String[]{"�Ǳ�"};
		field = new Object[kinds][1];
		field[0][0] = fileNames[0];
		for(int i=0; i<kinds; i++)
		{
			field[i][0] = fileNames[i];   
		}
		tablemodel.setDataVector(field, header);
	}
}

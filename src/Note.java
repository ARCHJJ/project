import java.util.LinkedList;

/**
 * @brief �۾�����(ChkBoxField, CmbBoxField)���� �� ���� ��Ÿ���� ����. ���õ� ��(row)�� �����ϴ� LinkedList�� ���½ð��� �����ϴ� int�� ������ �̷�����ִ�.
 */
class Note {
	LinkedList<Integer> fileidx;
	int rest;
	
	/**
	 * @brief ������
	 */
	Note()
	{
		fileidx = new LinkedList<Integer>();
		rest = 0;
	}
}

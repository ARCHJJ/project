import java.util.LinkedList;

/**
 * @brief �۾�����(ChkBoxField, CmbBoxField)���� �� ���� ��Ÿ���� ����.
 * ���õ� ��(row)�� �����ϴ� LinkedList�� ���½ð��� �����ϴ� int�� ������ �̷�����ִ�.
 */
class Note {
	public LinkedList<Integer> fileidx;
	public int rest;
	
	/**
	 * @brief ������
	 * fileidx�� �Ҵ��ϰ� rest�� �ʱⰪ�� 0���� �����Ѵ�.
	 */
	Note()
	{
		fileidx = new LinkedList<Integer>();
		rest = 0;
	}
}

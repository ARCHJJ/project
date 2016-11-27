import java.util.LinkedList;

/**
 * @brief ������� �۰��� ��� ������ ������ ���Ͽ� ����Ͽ� �����ϴ� ����� ������ Ŭ�����̴�. 
 */
abstract class SaveBank{
	//!�Ǳ�����
	protected int kind;
	
	/**
	 * @brief ���ϴ� ��ũ�� ȣ���ϴ� �޼ҵ�
	 * @param LinkedList<Integer> beatList �����Ǹ���� ������ LinkedList�� �ڷᱸ��
	 * @param int restTime ���� �κ��� ǥ��
	 */
	abstract public LinkedList<Note> getBank(LinkedList<Integer> beatList, int restTime);
	
	/**
	 * @brief ȣ��� �޼ҵ带 ȭ�鿡 ǥ���ϴ� �޼ҵ�
	 * @param LinkedList<Note> bank ��ũ�� ������ ������ LinkedList�� �ڷᱸ��
	 */
	abstract public void bankPrint(LinkedList<Note> bank);
}
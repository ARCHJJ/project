import java.util.LinkedList;

/**
 * @brief ������� �۰��� ��� ������ ������ ���Ͽ� ����Ͽ� �����ϴ� ����� ������ Ŭ�����̴�. 
 */
abstract class SaveBank{
	//!
	protected int kind;
	
	/**
	 * @brief 
	 * @param LinkedList<Integer> beatList
	 * @param int restTime
	 */
	abstract public LinkedList<Note> getBank(LinkedList<Integer> beatList, int restTime);
	
	/**
	 * @brief 
	 * @param LinkedList<Note> bank
	 */
	abstract public void bankPrint(LinkedList<Note> bank);
}
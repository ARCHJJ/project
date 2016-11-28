import java.util.LinkedList;

/**
 * @brief ������ ���� ���ڸ� ����(��ũ)������ �����ϴ� Ŭ���� 
 */
abstract class SaveBank{
	//! �Ǳ�����
	protected int kinds;
	
	//! ���� ���ڰ� �����Ǿ� �ִ� TableModel
	protected SettingToField field;
		
	/**
	 * @brief ���ϴ� ��ũ�� ȣ���ϴ� �޼ҵ�
	 * @param LinkedList<Integer> beatList : ����� ���� �����͸� ������ LinkedList
	 * @param int restTime				   : ���� �� ������ ���� �ð�
	 */
	abstract public LinkedList<Note> getBank(LinkedList<Integer> beatList, int restTime);
	
	/**
	 * @brief ��ũ�� ����� �����͸� ȭ�鿡 �ѷ��ִ� ������ �ϴ� �޼ҵ�. ����׿뵵�� ���ȴ�.
	 * @param LinkedList<Note> bank : ��ũ���� ������ ������ �ִ� LinkedList
	 */
	abstract public void bankPrint(LinkedList<Note> bank);
}
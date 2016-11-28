import java.util.LinkedList;

/**
 * @brief 설정한 음과 박자를 마디(뱅크)단위로 저장하는 클래스 
 */
abstract class SaveBank{
	//! 악기종류
	protected int kinds;
	
	//! 음과 박자가 설정되어 있는 TableModel
	protected SettingToField field;
		
	/**
	 * @brief 원하는 뱅크를 호출하는 메소드
	 * @param LinkedList<Integer> beatList : 저장된 박자 데이터를 가지는 LinkedList
	 * @param int restTime				   : 음과 음 사이의 쉬는 시간
	 */
	abstract public LinkedList<Note> getBank(LinkedList<Integer> beatList, int restTime);
	
	/**
	 * @brief 뱅크에 저장된 데이터를 화면에 뿌려주는 역할을 하는 메소드. 디버그용도로 사용된다.
	 * @param LinkedList<Note> bank : 뱅크들의 집합을 가지고 있는 LinkedList
	 */
	abstract public void bankPrint(LinkedList<Note> bank);
}
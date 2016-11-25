import java.util.LinkedList;

/**
 * @brief 현재까지 작곡한 모든 정보를 데이터 파일에 출력하여 저장하는 기능을 가지는 클래스이다. 
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
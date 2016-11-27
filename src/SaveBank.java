import java.util.LinkedList;

/**
 * @brief 현재까지 작곡한 모든 정보를 데이터 파일에 출력하여 저장하는 기능을 가지는 클래스이다. 
 */
abstract class SaveBank{
	//!악기종류
	protected int kind;
	
	/**
	 * @brief 원하는 뱅크를 호출하는 메소드
	 * @param LinkedList<Integer> beatList 박자의목록을 가지는 LinkedList형 자료구조
	 * @param int restTime 쉬는 부분을 표현
	 */
	abstract public LinkedList<Note> getBank(LinkedList<Integer> beatList, int restTime);
	
	/**
	 * @brief 호출된 메소드를 화면에 표현하는 메소드
	 * @param LinkedList<Note> bank 뱅크의 집합을 가지는 LinkedList형 자료구조
	 */
	abstract public void bankPrint(LinkedList<Note> bank);
}
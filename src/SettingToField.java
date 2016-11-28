import java.util.LinkedList;
import javax.swing.JTable;

/**
 * @brief Setting클래스를 상속받는 클래스. 
 * 체크박스 혹은 콤보박스가 저장될 수 있도록 Object[][]과 추가된 열이 첫 입력인지 아닌지 판단하는
 * LinkedList<Boolean>으로 이루어져 있다.
 */
abstract class SettingToField extends Setting{
	//! 추가된 열이 첫 입력인지 판단하는 데이터가 저장되어 있는 LinkedList
	protected LinkedList<Boolean> bulb;

	//! 뱅크를 저장하는 LinkedList
	protected LinkedList<LinkedList<Note>> BankList;
	
	//! kinds  : 악기의 종류(개수)
	//! colCnt : JTable의 열 
	protected int kinds, colCnt = 1;
	/**
	 * @brief 필드부분을 초기세팅해주는 생성자
	 */
	public SettingToField(){
		super();
		BankList = new LinkedList<LinkedList<Note>>();
		BankList.add(null);
		bulb = new LinkedList<Boolean>();
		bulb.add(true);
	}
	/**
	 * @brief BankList 를 초기화 하는 메소드
	 */
	public void BankListClear()
	{
		BankList.clear();
		BankList.add(null);
		bulb.clear();
		bulb.add(true);
	}
	/**
	 * @brief 컬럼 추가
	 * @param int idx 컬럼 인덱스
	 */
	abstract void addColumn(int idx);
	
	/**
	 * @brief 셀 옵션을 설정하는 메소드
	 * @param JTable Desk 작업표시줄의 라인
	 */ 
	abstract void setCellOption(JTable Desk);
	
	/**
	 * @brief 초기화 메소드
	 */
	abstract void Init();
	
	/**
	 * @brief 악기종류 얻는 메소드
	 */
	abstract int getKinds();

}

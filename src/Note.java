import java.util.LinkedList;

/**
 * @brief 작업공간(ChkBoxField, CmbBoxField)에서 한 행을 나타내는 단위.
 * 선택된 행(row)을 저장하는 LinkedList와 쉬는시간을 저장하는 int형 변수로 이루어져있다.
 */
class Note {
	public LinkedList<Integer> fileidx;
	public int rest;
	
	/**
	 * @brief 생성자
	 * fileidx를 할당하고 rest의 초기값을 0으로 설정한다.
	 */
	Note()
	{
		fileidx = new LinkedList<Integer>();
		rest = 0;
	}
}

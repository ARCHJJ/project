import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 * @brief 미리 만들어놓은 기타코드를 테이블에 삽입하는 클래스
 */
public class InputGuitarCode {
	//! 만들어 놓은 기타코드를 불러와서 저장하는 String[][]
	private String[][] Code;
	
	/**
	 * @brief 생성자
	 * @param String[][] Code_File : 만들어 놓은 기타코드 데이터를 인자로 전달 받는다.
	 */
	public InputGuitarCode(String[][] Code_File)
	{
		Code = Code_File;
	}
	
	/**
	 * @brief 선택한 코드를 JTable에 삽입한다.
	 * @param JTable Desk	: 코드가 추가될 JTable 
	 * @param int x			: Code의 행
	 * @param int y			: Code의 열
	 */
	public void addCode(JTable Desk, int x, int y)
	{
		String Note;
		JComboBox temp;
		Note = Code[x][y];
		char ch;
		for(int i=1; i<7; i++)
		{
			ch = Note.charAt(i-1);
			temp = (JComboBox)Desk.getModel().getValueAt(i, Desk.getModel().getColumnCount()-1);
			
			if(ch=='x')
				temp.setSelectedIndex(0);
			else
				temp.setSelectedIndex(((int)ch-48)+1);
			
			Desk.setValueAt(temp, i, Desk.getModel().getColumnCount()-1);
		}
	}
}

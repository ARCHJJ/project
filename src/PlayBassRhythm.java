import java.util.LinkedList;

public class PlayBassRhythm {
	
	//! 박자에 대한 정보가 들어있는 객체
	protected String[][] Bassrhytm;

	//! 박자당 시간
	protected int thirty_second_note;
	
	/**
	 * @brief 테이블에 선택한 리듬을 입력한다.
	 * @param String[][] Bassrhytm	: 박자에 대한 정보가 들어있는 객체
	 * @param int result			: 박자당 시간
	 */
	public void setRhythm(String[][] Bassrhytm, int result)
	{
		this.Bassrhytm = Bassrhytm;
		
		thirty_second_note = result;
	}
	
	/**
	 * @brief 테이블에 선택한 리듬을 재생한다.
	 * @param int y					: 선택한 리듬
	 */
	public LinkedList<Note> play_Rhythm(int y) 
	{
		LinkedList<Note> itbank = new LinkedList<Note>();
			
		for(int i=0; '$' != Bassrhytm[y][i].charAt(0); i++)
		{
			int result = (int) (thirty_second_note*(32/Math.pow(2 ,Integer.parseInt(Bassrhytm[y+4][i]))));
			
			Note itnote = new Note();
			
			itnote.rest = result;
			
			if('x' != Bassrhytm[y][i].charAt(0))
				itnote.fileidx.add(Integer.parseInt(Bassrhytm[y][i]));

			itbank.add(itnote);
		}
		
		return itbank;
	}

}

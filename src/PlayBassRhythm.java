import java.util.LinkedList;

public class PlayBassRhythm {
	
	//! ���ڿ� ���� ������ ����ִ� ��ü
	protected String[][] Bassrhytm;

	//! ���ڴ� �ð�
	protected int thirty_second_note;
	
	/**
	 * @brief ���̺� ������ ������ �Է��Ѵ�.
	 * @param String[][] Bassrhytm	: ���ڿ� ���� ������ ����ִ� ��ü
	 * @param int result			: ���ڴ� �ð�
	 */
	public void setRhythm(String[][] Bassrhytm, int result)
	{
		this.Bassrhytm = Bassrhytm;
		
		thirty_second_note = result;
	}
	
	/**
	 * @brief ���̺� ������ ������ ����Ѵ�.
	 * @param int y					: ������ ����
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

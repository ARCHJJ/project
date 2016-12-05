import java.util.LinkedList;

/**
 * @brief 드럼에 선택된 리듬을 재생하는 클래스이다. 
 */
public class PlayDrumRhythm {
	
	//! 박자 기준 음표
	protected int denominator;
			
	//! 박자 기준 길이 
	protected int numerator;
	
	//! 박자당 시간
	protected int thirty_second_note;
		
	/**
	 * @brief 박자를 셋팅한다
	 * @param int time_signature_numerator		: 기준음표
	 * @param int time_signature_denominator	: 기준 시간
	 * @param int result						: 박자당 시간
	 */
	public void setBeat(int time_signature_numerator, int time_signature_denominator, int result)
	{
		numerator = time_signature_numerator;
		
		denominator = time_signature_denominator;
		
		thirty_second_note = result;
		
	}
	
	/**
	 * @brief 테이블에 선택한 리듬을 재생한다.
	 * @param int x					: 선택한 박자
	 * @param int y					: 선택한 리듬
	 */
	public LinkedList<Note> play_Rhythm(int x, int y) 
	{
		LinkedList<Note> itbank = new LinkedList<Note>();

		switch(x)
		{
		case 0 :
		case 1 :
		case 2 :
		case 3 :
			SimpleTimeSignatures(itbank, y);
			break;
		case 5 :
		case 6 :
		case 7 :
			CompoundTimeSignatures(itbank, x, y);
			break;
		case 9 :
		case 10 :
		case 11 :
			ComplexTimeSignatures(itbank, x, y);
			break;
		}
		
		return itbank;
	}
	
	/**
	 * @brief 단순리듬.
	 * @param LinkedList<Note> itbank	: 드럼의 음이 저장될 뱅크
	 * @param int y					: 선택한 리듬
	 */
	public void SimpleTimeSignatures(LinkedList<Note> itbank, int y)
	{
		
		int result = (32/(denominator*y))*thirty_second_note;

		for(int i = 0; i < numerator*y; i++)
		{
			Note itnote = new Note();
			itnote.rest = result;
			
			if(i == 0)
			{
				itnote.fileidx.add(0);
			}
				
			else if(i%(2*y) == 0)
			{
				itnote.fileidx.add(2);
			}
			
			itnote.fileidx.add(1);

			itbank.add(itnote);
		}
		
	}
	
	/**
	 * @brief 복합리듬.
	 * @param LinkedList<Note> itbank	: 드럼의 음이 저장될 뱅크
	 * @param int y					: 선택한 리듬
	 */
	public void CompoundTimeSignatures(LinkedList<Note> itbank, int x, int y)
	{
		int result = (32/(denominator*y))*thirty_second_note;
		
		for(int i = 0; i < numerator*y; i++)
		{
			Note itnote = new Note();
			itnote.rest = result;
				
			if(i == 0)
			{
				itnote.fileidx.add(0);
			}
				
			else if(i%(3*y) == 0)
			{
				itnote.fileidx.add(2);
			}
				
			itnote.fileidx.add(1);
	
			itbank.add(itnote);
		}
	}
	
	/**
	 * @brief 혼합리듬.
	 * @param LinkedList<Note> itbank	: 드럼의 음이 저장될 뱅크
	 * @param int y					: 선택한 리듬
	 */
	public void ComplexTimeSignatures(LinkedList<Note> itbank, int x, int y)
	{
		int result = (32/(denominator*y))*thirty_second_note;
		
		for(int i = 0; i < numerator*y; i++)
		{
			Note itnote = new Note();
			itnote.rest = result;
			
			if(i == 0)
			{
				itnote.fileidx.add(0);
			}
				
			else if((i < (numerator*y)-1) && (i%(2*y) == 0))
			{
				itnote.fileidx.add(2);
			}
			
			itnote.fileidx.add(1);
			
			itbank.add(itnote);
		}

	}

}

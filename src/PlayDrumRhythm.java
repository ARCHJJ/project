import java.util.LinkedList;

/**
 * @brief �巳�� ���õ� ������ ����ϴ� Ŭ�����̴�. 
 */
public class PlayDrumRhythm {
	
	//! ���� ���� ��ǥ
	protected int denominator;
			
	//! ���� ���� ���� 
	protected int numerator;
	
	//! ���ڴ� �ð�
	protected int thirty_second_note;
		
	/**
	 * @brief ���ڸ� �����Ѵ�
	 * @param int time_signature_numerator		: ������ǥ
	 * @param int time_signature_denominator	: ���� �ð�
	 * @param int result						: ���ڴ� �ð�
	 */
	public void setBeat(int time_signature_numerator, int time_signature_denominator, int result)
	{
		numerator = time_signature_numerator;
		
		denominator = time_signature_denominator;
		
		thirty_second_note = result;
		
	}
	
	/**
	 * @brief ���̺� ������ ������ ����Ѵ�.
	 * @param int x					: ������ ����
	 * @param int y					: ������ ����
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
	 * @brief �ܼ�����.
	 * @param LinkedList<Note> itbank	: �巳�� ���� ����� ��ũ
	 * @param int y					: ������ ����
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
	 * @brief ���ո���.
	 * @param LinkedList<Note> itbank	: �巳�� ���� ����� ��ũ
	 * @param int y					: ������ ����
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
	 * @brief ȥ�ո���.
	 * @param LinkedList<Note> itbank	: �巳�� ���� ����� ��ũ
	 * @param int y					: ������ ����
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

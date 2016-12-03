import javax.sound.sampled.Clip;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
	 * @param SettingToField STF	: 드럼의 음이 저장된 객체
	 * @param int x					: 선택한 박자
	 * @param int y					: 선택한 리듬
	 */
	public void play_Rhythm(Clip Drum_Sound[][], int x, int y) 
	{
		switch(x)
		{
		case 0 :
		case 1 :
		case 2 :
		case 3 :
			SimpleTimeSignatures(Drum_Sound, y);
			break;
		case 5 :
		case 6 :
		case 7 :
			CompoundTimeSignatures(Drum_Sound, x, y);
			break;
		case 9 :
		case 10 :
		case 11 :
			ComplexTimeSignatures(Drum_Sound, x, y);
			break;
		}
	}
	
	/**
	 * @brief 단순리듬.
	 * @param SettingToField STF	: 드럼의 음이 저장된 객체
	 * @param int y					: 선택한 리듬
	 */
	public void SimpleTimeSignatures(Clip Drum_Sound[][], int y)
	{
		int result = (32/(denominator*y))*thirty_second_note;
		
		try
		{
			for(int i = 0; i < numerator*y; i++)
			{
				if(i == 0)
				{
					Drum_Sound[0][0].setFramePosition(0);
					Drum_Sound[0][0].start();
				}
				
				else if(i%(2*y) == 0)
				{
					Drum_Sound[0][2].setFramePosition(0);
					Drum_Sound[0][2].start();

				}
				
				Drum_Sound[0][1].setFramePosition(0);
				Drum_Sound[0][1].start();
				
				Thread.sleep(result-10);
				
				Drum_Sound[0][0].stop();

				Drum_Sound[0][2].stop();				

				Drum_Sound[0][1].stop();
				
				Thread.sleep(10);
	
			}
		}
		
		catch(InterruptedException ie) { ie.printStackTrace(); }
	}
	
	/**
	 * @brief 복합리듬.
	 * @param SettingToField STF	: 드럼의 음이 저장된 객체
	 * @param int y					: 선택한 리듬
	 */
	public void CompoundTimeSignatures(Clip Drum_Sound[][], int x, int y)
	{
		int result = (32/(denominator*y))*thirty_second_note;
		
		try
		{
			for(int i = 0; i < numerator*y; i++)
			{
				if(i == 0)
				{
					Drum_Sound[0][0].setFramePosition(0);
					Drum_Sound[0][0].start();
				}
				
				else if(i%(3*y) == 0)
				{
					Drum_Sound[0][2].setFramePosition(0);
					Drum_Sound[0][2].start();

				}
				
				Drum_Sound[0][1].setFramePosition(0);
				Drum_Sound[0][1].start();
				
				Thread.sleep(result-10);
				
				Drum_Sound[0][0].stop();

				Drum_Sound[0][2].stop();				

				Drum_Sound[0][1].stop();
				
				Thread.sleep(10);
	
			}
		}
		
		catch(InterruptedException ie) { ie.printStackTrace(); }
	}
	
	/**
	 * @brief 혼합리듬.
	 * @param SettingToField STF	: 드럼의 음이 저장된 객체
	 * @param int y					: 선택한 리듬
	 */
	public void ComplexTimeSignatures(Clip Drum_Sound[][], int x, int y)
	{
		int result = (32/(denominator*y))*thirty_second_note;
		
		try
		{
			for(int i = 0; i < numerator*y; i++)
			{
				if(i == 0)
				{
					Drum_Sound[0][0].setFramePosition(0);
					Drum_Sound[0][0].start();
				}
				
				else if((i < (numerator*y)-1) && (i%(2*y) == 0))
				{
					Drum_Sound[0][2].setFramePosition(0);
					Drum_Sound[0][2].start();

				}
				
				Drum_Sound[0][1].setFramePosition(0);
				Drum_Sound[0][1].start();
				
				Thread.sleep(result-10);
				
				Drum_Sound[0][0].stop();

				Drum_Sound[0][2].stop();				

				Drum_Sound[0][1].stop();
				
				Thread.sleep(10);
	
			}
		}
		
		catch(InterruptedException ie) { ie.printStackTrace(); }
	}

}

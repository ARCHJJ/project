import javax.sound.sampled.Clip;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
	 * @param SettingToField STF	: �巳�� ���� ����� ��ü
	 * @param int x					: ������ ����
	 * @param int y					: ������ ����
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
	 * @brief �ܼ�����.
	 * @param SettingToField STF	: �巳�� ���� ����� ��ü
	 * @param int y					: ������ ����
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
	 * @brief ���ո���.
	 * @param SettingToField STF	: �巳�� ���� ����� ��ü
	 * @param int y					: ������ ����
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
	 * @brief ȥ�ո���.
	 * @param SettingToField STF	: �巳�� ���� ����� ��ü
	 * @param int y					: ������ ����
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

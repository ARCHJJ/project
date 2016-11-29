import javax.sound.sampled.Clip;

/**
 * @brief ��Ʈ�γ��� �����ϴ� Ŭ���� �̴�. 
 */
class Metronome implements Runnable 
{
	//!	������� �����Ѵ�.
	private Thread thread;
	//!	���� UI
	private Orpheus ui;
	
	//!	�Ҹ������� ����ϱ� ���� ��ü
	private Clip MetronomeClip;

	//!	�Ҹ������� ����ϱ� ���� ��ü
	private Clip clip;

	//! ��Ʈ�γ��� ����� ����
	private int time;
	
	//! ��Ʈ�γ� �ݸ�Ƚ��
	private int music_score;
	
	//! �� ���ڴ� Ƚ��
	private int time_signature;
	
	/**
	 * @brief ������. �����带 �����Ѵ�.
	 * @param Orpheus ui
	 */
	public Metronome(Orpheus ui)
	{
		this.ui = ui;
		thread = new Thread(this);
	}
	
	/**
	 * @brief �����带 �����Ѵ�.
	 */
	public void ThreadStart()
	{
		thread.start();
	}
	
	/**
	 * @brief ���������带 �����Ѵ�.
	 * @param boolean bulb : ���������� ���ۿ����� ���ڷ� ���ȴ�.
	 */
	public void setDaemon(boolean bulb)
	{
		thread.setDaemon(bulb);
	}
	
	/**
	 * @brief ������ �����¸� �����Ѵ�.
	 */
	public synchronized void action()
	{
		notify();
	}
	
	/**
	 * @brief '�ַε��', '���ֽ���' ����� �����Ѵ�.
	 * @param int result							: ���ð�
	 * @param int time_signature_denominator		: ������ ����
	 * @param File metronome_Sound					: ����� �Ҹ��� ����Ǿ� �ִ� File
	 * @param int max								: �۾�������� ���� �� ��ũ ����
	 */
	public void metronomeSet(Clip metronome_Sound, int result, int time_signature_denominator, int time_signature_numerator, int max)
	{
		MetronomeClip = metronome_Sound;
		
		time = (result*(32/time_signature_denominator));
		
		music_score = max;
		
		time_signature = time_signature_numerator;
	}

	/**
	 * @brief ������ ���۸޼ҵ�
	 */
	public void run()
	{
		while(true)
		{
			try
			{
				synchronized (this)
				{
					wait();
				}

			 	for(int i = 0; i < music_score*time_signature; i++)
				{			
			 		clip = MetronomeClip;
			 		clip.setFramePosition(0);
					clip.start();
					
					Thread.sleep(time);	
				}
			}
			catch(InterruptedException ie) { ie.printStackTrace(); }
		}
	}
}

import java.io.File;
import java.util.LinkedList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 * @brief 메트로놈을 적용하는 클래스 이다. 
 */
class metronome implements Runnable 
{
	//!	스레드로 동작한다.
	private Thread thread;
	//!	메인 UI
	private Orpheus ui;
	
	//!	소리파일을 재생하기 위한 객체
	private Clip MetronomeClip;

	//!	소리파일을 재생하기 위한 객체
	private Clip clip;

	//! UI에서 버튼이 눌렸는지 판단하는 변수
	private boolean standby;
	//! 메트로놈이 실행될 박자
	private int time;
	//! 메트로놈 반목횟수
	private int music_score;
	//! 한 박자당 횟수
	private int time_signature;
	
	/**
	 * @brief 생성자. 스레드를 생성한다.
	 * @param Orpheus ui
	 */
	public metronome(Orpheus ui)
	{
		this.ui = ui;
		thread = new Thread(this);
		standby = true;
	}
	
	/**
	 * @brief 스레드를 시작한다.
	 */
	public void ThreadStart()
	{
		thread.start();
	}
	
	/**
	 * @brief 보조스레드를 설정한다.
	 * @param boolean bulb : 보조스레드 동작여부의 인자로 사용된다.
	 */
	public void setDaemon(boolean bulb)
	{
		thread.setDaemon(bulb);
	}
	
	/**
	 * @brief 스레드 대기상태를 해제한다.
	 */
	public synchronized void action()
	{
		standby = false;
		notify();
	}
	
	/**
	 * @brief 스레드를 대기상태로 만든다
	 */
	public void ready()
	{
		standby = true;
	}
	
	/**
	 * @brief '솔로듣기', '연주시작' 기능을 세팅한다.
	 * @param int result							: 대기시간
	 * @param int time_signature_denominator		: 설정된 박자
	 * @param File metronome_Sound					: 재생할 소리가 저장되어 있는 File
	 * @param int max								: 작업대기줄의 가장 긴 뱅크 길이
	 */
	public void metronomeSet(Clip metronome_Sound, int result, int time_signature_denominator, int time_signature_numerator, int max)
	{
		MetronomeClip = metronome_Sound;
		
		time = (result*(32/time_signature_denominator));
		
		music_score = max;
		
		time_signature = time_signature_numerator;
	}

	/**
	 * @brief 스레드 동작메소드
	 */
	public void run()
	{
		while(true)
		{
			try
			{
				synchronized (this)
				{
					if(standby)
						this.wait();
				}
			}
			catch(InterruptedException ie) {}
			
			try
			{
			 	for(int i = 0; i < music_score*time_signature; i++)
				{			
			 		clip = MetronomeClip;
			 		clip.setFramePosition(0);
					clip.start();
					
					Thread.sleep(time);	
				}
			 	
			}
			catch(Exception exp)
			{
				exp.printStackTrace();
			}	
			
			ready();
		}
	}
}

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 * @brief 작곡한 뱅크를 재생하는 기능을 담당하는 클래스이다. 
 */
class Play implements Runnable
{
	//! Play클래스와 UI는 스레드로 동작한다.
	private Thread thread;
	
	//! 작업대기줄 테이블
	private JTable table_Task;

	//! 뱅크가 저장되어 있는 LinkedList
	private LinkedList<LinkedList<Note>> BankList;
	
	//! 음과 쉬는시간이 저장되어 있는 LinkedList
	private LinkedList<Note> playlist;
	
	//! playlist의 Iterator
	private Iterator<Note> itNote;
	
	//! playlist.fileidx의 Iterator
	private Iterator<Integer> itPlay;
	
	//! 음소거 여부를 판단하기 위한 JCheckBox
	private JCheckBox mute;
	
	//! 재생할 소리가 저장되어 있는 File[][]
	private File[][] SoundFiles;
	
	//! 소리파일을 입력받기 위한 객체
	private AudioInputStream sound;
	
	//! 소리파일을 재생하기 위한 객체
	private Clip clip;
	
	//! 재생할 뱅크 번호
	private int idx;
	
	//! 메인 UI
	private Orpheus ui;
	
	//! UI에서 버튼이 눌렸는지 판단하는 변수
	private boolean standby;
	
	//! 뱅크듣기와 솔로듣기||연주시작을 구분하는 변수
	private boolean singleplay;
	
	/**
	 * @brief 생성자. 스레드를 생성한다.
	 * @param Orpheus ui
	 */
	public Play(Orpheus ui)
	{
		this.ui = ui;
		thread = new Thread(this);
		standby = true;
	}
	
	/**
	 * @brief 재생할 뱅크를 세팅한다.
	 * @param LinkedList<Note> playlist	: 음과 쉬는시간이 저장되어 있는 LinkedList
	 * @param File[][] SoundFiles		: 재생할 소리가 저장되어 있는 File[][]
	 */
	public void setBank(LinkedList<Note> playlist, File[][] SoundFiles)
	{
		this.playlist = playlist;
		this.SoundFiles = SoundFiles;
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
	 * @brief 뮤트기능을 사용하지 않을 때 호출한다.
	 */
	public void MuteDisable()
	{
		mute = new JCheckBox();
		mute.setSelected(false);
	}
	
	/**
	 * @brief '뱅크듣기' 기능만을 사용하는지 여부를 판단한다.
	 */
	public void singleSet()
	{
		singleplay = true;
	}
	
	/**
	 * @brief '솔로듣기', '연주시작' 기능을 세팅한다.
	 * @param JTable table_Task						: 작업대기줄 테이블
	 * @param LinkedList<LinkedList<Note>> BankList	: 뱅크가 저장되어 있는 LinkedList
	 * @param File[][] SoundFiles					: 재생할 소리가 저장되어 있는 File[][]
	 * @param JCheckBox mute						: 음소거 기능
	 */
	public void multySet(JTable table_Task, LinkedList<LinkedList<Note>> BankList, File[][] SoundFiles, JCheckBox mute)
	{
		singleplay = false;
		this.table_Task = table_Task;
		this.BankList = BankList;
		this.SoundFiles = SoundFiles;
		this.mute = mute;
	}
	
	
	/**
	 * @brief 하나의 뱅크만을 재생할 때 사용하는 메소드
	 */
	public void single()
	{
		try
		{		
			itNote = playlist.iterator();
			ui.getBankListenButton().setEnabled(false);
			
			while(itNote.hasNext())
			{
				Note temp = itNote.next();
				itPlay = temp.fileidx.iterator();
				while(itPlay.hasNext())
				{
					idx = itPlay.next();
					if(!mute.isSelected())
					{
						sound = AudioSystem.getAudioInputStream(SoundFiles[idx/100][idx%100]);
						clip = AudioSystem.getClip();
						clip.open(sound);
								
						//clip.setFramePosition(0);
						clip.start();
					}
							
				}
				Thread.sleep(temp.rest);
			}
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		ready();
		ui.getBankListenButton().setEnabled(true);
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
			
			
			if(singleplay)
				single();
				
			else
			{
				int bankidx = 0;
				JComboBox selectBank;
				for(int i=1; i<table_Task.getModel().getColumnCount(); i++)
				{
					selectBank = (JComboBox)table_Task.getValueAt(0, i);
					bankidx = selectBank.getSelectedIndex();
					if(bankidx!=0)
					{
						setBank(BankList.get(bankidx), SoundFiles);
						single();
					}
				}
			}
		}
	}
}
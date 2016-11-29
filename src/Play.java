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
	
	//! 부드러운 소리재생을 위해 잔향제거
	private LinkedList<Clip> noise;
	
	//! playlist의 Iterator
	private Iterator<Note> itNote;
	
	//! playlist.fileidx의 Iterator
	private Iterator<Integer> itPlay;
	
	//! 음소거 여부를 판단하기 위한 JCheckBox
	private JCheckBox mute;
	
	//! 재생할 소리가 저장되어 있는 Clip[][]
	private Clip[][] SoundClips;
	
	//! 소리파일을 재생하기 위한 객체
	private Clip clip;
	
	//! 재생할 뱅크 번호
	private int idx;
	
	//! 메인 UI
	private Orpheus ui;
	
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
		noise = new LinkedList<Clip>();
	}
	
	/**
	 * @brief 재생할 뱅크를 세팅한다.
	 * @param LinkedList<Note> playlist	: 음과 쉬는시간이 저장되어 있는 LinkedList
	 * @param Clip[][] SoundClips		: 재생할 소리가 저장되어 있는 Clip[][]
	 */
	public void setBank(LinkedList<Note> playlist, Clip[][] SoundClips)
	{
		this.playlist = playlist;
		this.SoundClips = SoundClips;
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
		notify();
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
	 * @param Clip[][] SoundClips					: 재생할 소리가 저장되어 있는 Clip[][]
	 * @param JCheckBox mute						: 음소거 기능
	 */
	public void multySet(JTable table_Task, LinkedList<LinkedList<Note>> BankList, Clip[][] SoundClips, JCheckBox mute)
	{
		singleplay = false;
		this.table_Task = table_Task;
		this.BankList = BankList;
		this.SoundClips = SoundClips;
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
						clip = SoundClips[idx/100][idx%100];
						noise.add(clip);
						clip.setFramePosition(0);
						clip.start();
					}
				}
				Thread.sleep(temp.rest);
				removeNoise();
			}
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		ui.getBankListenButton().setEnabled(true);
	}
	public void removeNoise()
	{
		Clip tmp;
		for(int i=0; i<noise.size(); i++)
		{
			tmp = noise.get(i);
			if(tmp.isRunning())
				tmp.stop();
		}
		noise.clear();
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
					wait();
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
						setBank(BankList.get(bankidx), SoundClips);
						single();
					}
				}
			}
		}
	}
}
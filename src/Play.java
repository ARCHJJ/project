import java.util.LinkedList;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;

@SuppressWarnings("rawtypes")
/**
 * @brief 작곡한 뱅크를 재생하는 기능을 담당하는 클래스이다. 
 */
class Play extends PlayComponents
{
	/**
	 * @brief 생성자. 스레드를 생성한다.
	 */
	public Play(JButton btn_play, Swtch swtch_play)
	{
		thread = new Thread(this);
		noise = new LinkedList<Clip>();
		
		standby = false;
		this.swtch_play = swtch_play;
		this.btn_play = btn_play;
		btnName = btn_play.getText();
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
		standby = false;
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
	 * @brief 재생을 정지한다.
	 */
	public void standby()
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
	
	public void setLongestTask(JButton btn_musicQ, Swtch swtch_musicQ)
	{
		this.btn_musicQ = btn_musicQ;
		this.swtch_musicQ = swtch_musicQ;
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
		standby = false;
		singleplay = false;
		this.table_Task = table_Task;
		this.BankList = BankList;
		this.SoundClips = SoundClips;
		this.mute = mute;
	}
	/**
	 * @brief 하나의 뱅크만을 재생할 때 사용하는 메소드
	 */
	public boolean single()
	{
		try
		{	
			itNote = playlist.iterator();
			while(itNote.hasNext())
			{
				if(standby)
					return false;
				Note temp = itNote.next();
				itPlay = temp.fileidx.iterator();
				while(itPlay.hasNext())
				{
					idx = itPlay.next();
					if(!mute.isSelected())
					{
						clip = SoundClips[idx/100][idx%100];
						noise.add(SoundClips[idx/100][idx%100]);
						clip.setFramePosition(0);
						clip.start();
					}
				}
				Thread.sleep(temp.rest);
				removeNoise();
			}
		}
		catch(InterruptedException ie) { ie.printStackTrace(); }
		
		return !standby;
	}
	public void removeNoise()
	{
		Clip tmp;
		for(int i=0; i<noise.size(); i++)
		{
			tmp = noise.get(i);
			if(tmp.isRunning())
				tmp.stop();
			try
			{
				Thread.sleep(1);
			}
			catch (InterruptedException ie) { ie.printStackTrace(); }
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
			
				if(singleplay)
					single();
					
				else
				{
					int bankidx = 0;
					boolean isStop = true;
					JComboBox selectBank;
					for(int i=1; i<table_Task.getModel().getColumnCount()&&isStop; i++)
					{
						selectBank = (JComboBox)table_Task.getValueAt(0, i);
						bankidx = selectBank.getSelectedIndex();
						if(bankidx!=0)
						{
							setBank(BankList.get(bankidx), SoundClips);
							isStop = single();
						}
					}
				}
				btn_play.setText(btnName);
				swtch_play.setFalse();
				
				btn_musicQ.setText("연주시작");
				swtch_musicQ.setFalse();
			}
			catch(NullPointerException ne) { continue; } 
			catch(InterruptedException ie) { ie.printStackTrace(); }
		}
	}
}
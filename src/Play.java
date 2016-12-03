import java.util.LinkedList;
import javax.sound.sampled.Clip;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;

@SuppressWarnings("rawtypes")
/**
 * @brief �۰��� ��ũ�� ����ϴ� ����� ����ϴ� Ŭ�����̴�. 
 */
class Play extends PlayComponents
{
	/**
	 * @brief ������. �����带 �����Ѵ�.
	 */
	
	public Play()
	{
		thread = new Thread(this);
		noise = new LinkedList<Clip>();
		standby = false;
	}
	
	/**
	 * @brief ����� ��ũ�� �����Ѵ�.
	 * @param LinkedList<Note> playlist	: ���� ���½ð��� ����Ǿ� �ִ� LinkedList
	 * @param Clip[][] SoundClips		: ����� �Ҹ��� ����Ǿ� �ִ� Clip[][]
	 */
	public void setBank(LinkedList<Note> playlist, Clip[][] SoundClips)
	{
		this.playlist = playlist;
		this.SoundClips = SoundClips;
		standby = false;
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
	 * @brief ����� �����Ѵ�.
	 */
	public void standby()
	{
		standby = true;
	}
	
	/**
	 * @brief ��Ʈ����� ������� ���� �� ȣ���Ѵ�.
	 */
	public void MuteDisable()
	{
		mute = new JCheckBox();
		mute.setSelected(false);
	}
	
	/**
	 * @brief '��ũ���' ��ɸ��� ����ϴ��� ���θ� �Ǵ��Ѵ�.
	 */
	public void singleSet()
	{
		singleplay = true;
	}
	
	/**
	 * @brief '�ַε��', '���ֽ���' ����� �����Ѵ�.
	 * @param JTable table_Task						: �۾������ ���̺�
	 * @param LinkedList<LinkedList<Note>> BankList	: ��ũ�� ����Ǿ� �ִ� LinkedList
	 * @param Clip[][] SoundClips					: ����� �Ҹ��� ����Ǿ� �ִ� Clip[][]
	 * @param JCheckBox mute						: ���Ұ� ���
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
	 * @brief �ϳ��� ��ũ���� ����� �� ����ϴ� �޼ҵ�
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
			}
			catch(InterruptedException ie) { ie.printStackTrace(); }
			
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
		}
	}
}
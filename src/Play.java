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
 * @brief �۰��� ��ũ�� ����ϴ� ����� ����ϴ� Ŭ�����̴�. 
 */
class Play implements Runnable
{
	//! PlayŬ������ UI�� ������� �����Ѵ�.
	private Thread thread;
	
	//! �۾������ ���̺�
	private JTable table_Task;

	//! ��ũ�� ����Ǿ� �ִ� LinkedList
	private LinkedList<LinkedList<Note>> BankList;
	
	//! ���� ���½ð��� ����Ǿ� �ִ� LinkedList
	private LinkedList<Note> playlist;
	
	//! �ε巯�� �Ҹ������ ���� ��������
	private LinkedList<Clip> noise;
	
	//! playlist�� Iterator
	private Iterator<Note> itNote;
	
	//! playlist.fileidx�� Iterator
	private Iterator<Integer> itPlay;
	
	//! ���Ұ� ���θ� �Ǵ��ϱ� ���� JCheckBox
	private JCheckBox mute;
	
	//! ����� �Ҹ��� ����Ǿ� �ִ� Clip[][]
	private Clip[][] SoundClips;
	
	//! �Ҹ������� ����ϱ� ���� ��ü
	private Clip clip;
	
	//! ����� ��ũ ��ȣ
	private int idx;
	
	//! ���� UI
	private Orpheus ui;
	
	//! ��ũ���� �ַε��||���ֽ����� �����ϴ� ����
	private boolean singleplay;
	
	/**
	 * @brief ������. �����带 �����Ѵ�.
	 * @param Orpheus ui
	 */
	public Play(Orpheus ui)
	{
		this.ui = ui;
		thread = new Thread(this);
		noise = new LinkedList<Clip>();
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
		singleplay = false;
		this.table_Task = table_Task;
		this.BankList = BankList;
		this.SoundClips = SoundClips;
		this.mute = mute;
	}
	/**
	 * @brief �ϳ��� ��ũ���� ����� �� ����ϴ� �޼ҵ�
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
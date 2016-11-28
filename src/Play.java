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
	
	//! playlist�� Iterator
	private Iterator<Note> itNote;
	
	//! playlist.fileidx�� Iterator
	private Iterator<Integer> itPlay;
	
	//! ���Ұ� ���θ� �Ǵ��ϱ� ���� JCheckBox
	private JCheckBox mute;
	
	//! ����� �Ҹ��� ����Ǿ� �ִ� File[][]
	private File[][] SoundFiles;
	
	//! �Ҹ������� �Է¹ޱ� ���� ��ü
	private AudioInputStream sound;
	
	//! �Ҹ������� ����ϱ� ���� ��ü
	private Clip clip;
	
	//! ����� ��ũ ��ȣ
	private int idx;
	
	//! ���� UI
	private Orpheus ui;
	
	//! UI���� ��ư�� ���ȴ��� �Ǵ��ϴ� ����
	private boolean standby;
	
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
		standby = true;
	}
	
	/**
	 * @brief ����� ��ũ�� �����Ѵ�.
	 * @param LinkedList<Note> playlist	: ���� ���½ð��� ����Ǿ� �ִ� LinkedList
	 * @param File[][] SoundFiles		: ����� �Ҹ��� ����Ǿ� �ִ� File[][]
	 */
	public void setBank(LinkedList<Note> playlist, File[][] SoundFiles)
	{
		this.playlist = playlist;
		this.SoundFiles = SoundFiles;
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
		standby = false;
		notify();
	}
	/**
	 * @brief �����带 �����·� �����
	 */
	public void ready()
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
	 * @param File[][] SoundFiles					: ����� �Ҹ��� ����Ǿ� �ִ� File[][]
	 * @param JCheckBox mute						: ���Ұ� ���
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
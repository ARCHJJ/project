import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;

class Play implements Runnable
{
	private Thread thread;
	
	//작업대기줄 재생 - MultyPlay
	private JTable table_Task;
	private LinkedList<LinkedList<Note>> BankList;
	
	//뱅크듣기 - SinglePlay
	private LinkedList<Note> playlist;
	private Iterator<Note> itNote;
	private Iterator<Integer> itPlay;
	private JCheckBox mute;
	
	private File[][] SoundFiles;
	private AudioInputStream sound;
	private Clip clip;
	private int idx;
	private Orpheus ui;
	
	private boolean standby;
	private boolean singleplay;
	public Play(Orpheus ui)
	{
		this.ui = ui;
		thread = new Thread(this);
		standby = true;
	}
	public void setBank(LinkedList<Note> playlist, File[][] SoundFiles)
	{
		this.playlist = playlist;
		this.SoundFiles = SoundFiles;
	}
	public void ThreadStart()
	{
		thread.start();
	}
	public void setDaemon(boolean bulb)
	{
		thread.setDaemon(bulb);
	}
	public void action()
	{
		standby = false;
	}
	public void ready()
	{
		standby = true;
	}
	public void MuteDisable()
	{
		mute = new JCheckBox();
		mute.setSelected(false);
	}
	public void singleSet()
	{
		singleplay = true;
	}
	public void multySet(JTable table_Task, LinkedList<LinkedList<Note>> BankList, File[][] SoundFiles, JCheckBox mute)
	{
		singleplay = false;
		this.table_Task = table_Task;
		this.BankList = BankList;
		this.SoundFiles = SoundFiles;
		this.mute = mute;
	}
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
	
	public void run()
	{
		while(true)
		{
			if(!standby)
			{
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
			else
				Thread.yield();
		}
	}
}
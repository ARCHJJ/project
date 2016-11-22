import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
	public void singleSet()
	{
		singleplay = true;
	}
	public void multySet(JTable table_Task, LinkedList<LinkedList<Note>> BankList, File[][] SoundFiles)
	{
		singleplay = false;
		this.table_Task = table_Task;
		this.BankList = BankList;
		this.SoundFiles = SoundFiles;
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
					sound = AudioSystem.getAudioInputStream(SoundFiles[idx/100][idx%100]);
					clip = AudioSystem.getClip();
					clip.open(sound);
							
					//clip.setFramePosition(0);
					clip.start();
							
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
				//ui.loopwaittoggle();
				if(singleplay)
					single();
				else
				{
					int bankidx = 0;
					JComboBox selectBank;
//					System.out.println(table_Task.getModel().getColumnCount());
					for(int i=1; i<table_Task.getModel().getColumnCount(); i++)
					{
						selectBank = (JComboBox)table_Task.getValueAt(0, i);
						bankidx = selectBank.getSelectedIndex();
						//System.out.println("BankIdx : "+bankidx);
						if(bankidx!=0)
						{
							//System.out.println(bankidx);
							setBank(BankList.get(bankidx), SoundFiles);
							single();
							
							
//							try {
//								System.out.println(RestTimeSetup.music_score * RestTimeSetup.result);
//								Thread.sleep(RestTimeSetup.music_score * RestTimeSetup.result);
//							} catch (InterruptedException e) {
//								e.printStackTrace();
//							}
//							try 
//							{
//								synchronized (this)
//								{
//									this.wait();
//								}
//							}
//							catch (InterruptedException e)
//							{
//									e.printStackTrace();
//							}
						}
					}
				}
//				loopwait = true;
//				ui.loopwaittoggle();
//				synchronized (this)
//				{
//					this.notify();
//				}

			}
			else
				Thread.yield();
		}
	}
	
	public void playtest(LinkedList<Note> playlist, File[][] SoundFiles)
	{
		//System.out.println(playlist);
		if(playlist == null)
			return;
		itNote = playlist.iterator();
		try
		{
			while(itNote.hasNext())
			{
				Note temp = itNote.next();
				itPlay = temp.fileidx.iterator();
				while(itPlay.hasNext())
				{
					idx = itPlay.next();
					sound = AudioSystem.getAudioInputStream(SoundFiles[idx/100][idx%100]);
					clip = AudioSystem.getClip();
					clip.open(sound);
					
					//clip.setFramePosition(0);
					clip.start();
					
				}
				Thread.sleep(temp.rest);
			}
		}
		catch(Exception exp)
		{}
	}
}
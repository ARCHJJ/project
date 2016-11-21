import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

class Play extends Thread
{
	private LinkedList<Note> playlist;
	private Iterator<Note> itNote;
	private Iterator<Integer> itPlay;
	
	private File[][] SoundFiles;
	private AudioInputStream sound;
	private Clip clip;
	private int idx;
	
	private Orpheus ui;
	public Play(Orpheus ui)
	{
		this.ui = ui;
	}
	public void setBank(LinkedList<Note> playlist, File[][] SoundFiles)
	{
		this.playlist = playlist;
		this.SoundFiles = SoundFiles;
	}
	public void run()
	{
		while(true)
		{
			try
			{
				ui.stopThread();
		
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
			ui.getBankListenButton().setEnabled(true);
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
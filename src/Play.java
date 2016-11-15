import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

class Play extends Thread
{
	private LinkedList<Note> playlist;
	private Iterator<Note> itNote;
	private Iterator<Integer> itPlay;
	private AudioInputStream sound;
	private Clip clip;
	private int idx;
	
	public Play()
	{}
	
	public void playtest(LinkedList<Note> playlist, File[][] SoundFiles)
	{
		System.out.println(playlist);
		itNote = playlist.iterator();
		try
		{
			while(itNote.hasNext())
			{
				Note temp = itNote.next();
				itPlay = temp.fileidx.iterator();
				while(itPlay.hasNext())
				{
					//System.out.println(itPlay.next());
					idx = itPlay.next();
					sound = AudioSystem.getAudioInputStream(SoundFiles[idx/100-1][idx%100-1]);
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
	public void run()
	{
		itNote = playlist.iterator();
		try
		{
			while(itNote.hasNext())
			{
				Thread.sleep(itNote.next().rest);
				System.out.println(itNote.next().fileidx);
				itPlay = itNote.next().fileidx.iterator();
				System.out.println(itPlay.next());
				if(!itPlay.hasNext())
					System.out.println("NO");
				while(itPlay.hasNext())
				{
					System.out.println(itPlay.next());
//					sound = AudioSystem.getAudioInputStream(SoundFiles[itPlay.next()]);
//					clip = AudioSystem.getClip();
//					clip.open(sound);
//					
//					//clip.setFramePosition(0);
//					clip.start();
//					
//					//Thread.sleep(it.next());
				}
			}
		}
		catch(Exception exp)
		{}
	}
}
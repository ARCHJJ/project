import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

class Playkb extends Thread
{
	LinkedList<Integer> playlist;
	Iterator<Integer> it;
	AudioInputStream sound;
	Clip clip;
	File[][] SoundFiles;
	
	Playkb(LinkedList<Integer> playlist, File[][] SoundFiles)
	{
		this.playlist = playlist;
		this.SoundFiles = SoundFiles;
	}
	
	public void run()
	{
		it = playlist.iterator();
		try
		{
			while(it.hasNext())
			{
				Thread.sleep(it.next());
				sound = AudioSystem.getAudioInputStream(SoundFiles[it.next()][it.next()]);
				clip = AudioSystem.getClip();
				clip.open(sound);
				
				//clip.setFramePosition(0);
				clip.start();
				
				//Thread.sleep(it.next());
			}
		}
		catch(Exception exp)
		{}
	}
}
import java.io.File;
import java.util.StringTokenizer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class PlayCode {
	private String[][] Code;
	private File[][] SoundFiles;
	
	private AudioInputStream sound;
	private Clip clip;
	
	public PlayCode(String[][] Code_File, File[][] Guitar)
	{
		Code = Code_File;
		SoundFiles = Guitar;
	}
	
	public void playCode(int x, int y)
	{
		String Note;
		
		StringTokenizer st = new StringTokenizer(Code[x][y] ,",");
		
		try
		{
			for(int i=0; i<6; i++)
			{
				Note = st.nextToken();
				
				if(Note.equals("x"))
					continue;
				
				sound = AudioSystem.getAudioInputStream(SoundFiles[i][Integer.parseInt(Note)]);
				clip = AudioSystem.getClip();
				clip.open(sound);
		
				clip.start();
			}
		}
		
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	
	}
}
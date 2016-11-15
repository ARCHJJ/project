import java.io.File;

class FileOpen {
	private File[][] PianoFiles, DrumFiles, GuitarFiles, BaseFiles;
	private String[][] PianoFnames, DrumFnames, GuitarFnames, BaseFnames;
	private String[] PianoFolder, DrumFolder, GuitarFolder, BaseFolder;
	private String[] PianoSoundNames, DrumSoundNames, GuitarSoundNames, BaseSoundNames;
	
	FileOpen()
	{
		PianoSoundNames = new String[]{"½°Ç¥", "µµ", "µµ#", "·¹", "·¹#", "¹Ì", "ÆÄ", "ÆÄ#", "¼Ö", "¼Ö#", "¶ó", "¶ó#", "½Ã"};
		DrumSoundNames = new String[]{"½°Ç¥", "K", "H", "S", "C"};
		GuitarSoundNames = new String[]{"½°Ç¥", "6", "5", "4", "3", "2", "1"};
		BaseSoundNames = new String[]{"½°Ç¥", "4", "3", "2", "1"};
		
		//PianoFolder= new String[3];
		PianoFolder = new String[]{
			  "C:\\workspace\\project\\piano\\0\\"	
			, "C:\\workspace\\project\\piano\\1\\"
			, "C:\\workspace\\project\\piano\\2\\"
		};
		PianoFiles = new File[3][12];
		PianoFnames = new String[][]{
			  {"C_4.wav", "C#_4.wav", "D_4.wav", "D#_4.wav", "E_4.wav", "F_4.wav", "F#_4.wav", "G_4.wav", "G#_4.wav", "A_4.wav", "A#_4.wav", "B_4.wav"}
			, {"C_5.wav", "C#_5.wav", "D_5.wav", "D#_5.wav", "E_5.wav", "F_5.wav", "F#_5.wav", "G_5.wav", "G#_5.wav", "A_5.wav", "A#_5.wav", "B_5.wav"}
			, {"C_6.wav", "C#_6.wav", "D_6.wav", "D#_6.wav", "E_6.wav", "F_6.wav", "F#_6.wav", "G_6.wav", "G#_6.wav", "A_6.wav", "A#_6.wav", "B_6.wav"}
		};
		for(int i=0; i<3; i++)
			for(int j=0; j<12; j++)
				PianoFiles[i][j] = new File(PianoFolder[i]+PianoFnames[i][j]);		
		
	}
	
	
	public File[][] getPianoFiles()
	{
		return PianoFiles;
	}
	public File[][] getDrumFiles()
	{
		return DrumFiles;
	}
	public File[][] getGuitarFiles()
	{
		return GuitarFiles;
	}
	public File[][] getBaseFiles()
	{
		return BaseFiles;
	}	
	public String[] getPianoSoundNames()
	{
		return PianoSoundNames;
	}
	public String[] getDrumSoundNames()
	{
		return DrumSoundNames;
	}
	public String[] getGuitarSoundNames()
	{
		return GuitarSoundNames;
	}
	public String[] getBaseSoundNames()
	{
		return BaseSoundNames;
	}
}

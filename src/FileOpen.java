import java.io.File;

class FileOpen {
	private File[][] PianoFiles, DrumFiles, GuitarFiles, BaseFiles;
	private String[][] PianoFnames, DrumFnames, GuitarFnames, BaseFnames;
	private String[] PianoFolder, DrumFolder, GuitarFolder, BaseFolder;
	private String[] PianoSoundNames, DrumSoundNames, GuitarSoundNames, BaseSoundNames;
	
	FileOpen()
	{
		PianoSoundNames = new String[]{"쉼표", "도", "도#", "레", "레#", "미", "파", "파#", "솔", "솔#", "라", "라#", "시"};
		DrumSoundNames = new String[]{"쉼표", "베이스", "하이헷", "스네어", "라이드", "크래시", "스몰탐", "하이탐"};
		GuitarSoundNames = new String[]{"쉼표", "6", "5", "4", "3", "2", "1"};
		BaseSoundNames = new String[]{"쉼표", "4", "3", "2", "1"};
		
		//PianoFolder= new String[3];
		PianoFolder = new String[]{
			  "C:\\workspace\\project\\piano\\0\\"	
			, "C:\\workspace\\project\\piano\\1\\"
			, "C:\\workspace\\project\\piano\\2\\"
		};
//		PianoFiles = new File[3][12];
//		PianoFnames = new String[][]{
//			  {"C_4.wav", "C#_4.wav", "D_4.wav", "D#_4.wav", "E_4.wav", "F_4.wav", "F#_4.wav", "G_4.wav", "G#_4.wav", "A_4.wav", "A#_4.wav", "B_4.wav"}
//			, {"C_5.wav", "C#_5.wav", "D_5.wav", "D#_5.wav", "E_5.wav", "F_5.wav", "F#_5.wav", "G_5.wav", "G#_5.wav", "A_5.wav", "A#_5.wav", "B_5.wav"}
//			, {"C_6.wav", "C#_6.wav", "D_6.wav", "D#_6.wav", "E_6.wav", "F_6.wav", "F#_6.wav", "G_6.wav", "G#_6.wav", "A_6.wav", "A#_6.wav", "B_6.wav"}
//		};
		PianoFiles = new File[12][3];
		PianoFnames = new String[][]{
				{"C_4.wav" , "C_5.wav" , "C_6.wav" }
			  , {"C#_4.wav", "C#_5.wav", "C#_6.wav"}
			  , {"D_4.wav" , "D_5.wav" , "D_6.wav" }
			  , {"D#_4.wav", "D#_5.wav", "D#_6.wav"}
			  , {"E_4.wav" , "E_5.wav" , "E_6.wav" }
			  , {"F_4.wav" , "F_5.wav" , "F_6.wav" }
			  , {"F#_4.wav", "F#_5.wav", "F#_6.wav"}
			  , {"G_4.wav" , "G_5.wav" , "G_6.wav" }
			  , {"G#_4.wav", "G#_5.wav", "G#_6.wav"}
			  , {"A_4.wav" , "A_5.wav" , "A_6.wav" }
			  , {"A#_4.wav", "A#_5.wav", "A#_6.wav"}
			  , {"B_4.wav" , "B_5.wav" , "B_6.wav" }
		};
		for(int i=0; i<12; i++)
			for(int j=0; j<3; j++)
				PianoFiles[i][j] = new File(PianoFolder[j]+PianoFnames[i][j]);	
		
		DrumFolder = new String[] {"C:\\workspace\\project\\drum\\"};
		DrumFiles = new File[1][7];
		DrumFnames = new String[][]{{"베이스.wav", "하이헷.wav", "스네어.wav", "라이드.wav", "크래시.wav", "스몰탐.wav", "하이탐.wav"}};
		for(int i=0; i<7; i++)
			DrumFiles[0][i] = new File(DrumFolder[0]+DrumFnames[0][i]);	
		
		GuitarFolder = new String[] {"C:\\workspace\\project\\guitar\\"};
		GuitarFiles = new File[6][25];
		GuitarFnames = new String[][]{
									{"0.wav", "1.wav", "2.wav", "3.wav", "4.wav", "5.wav", "6.wav", "7.wav", "8.wav", "9.wav", "10.wav", "11.wav", "12.wav"
										, "13.wav", "14.wav", "15.wav", "16.wav", "17.wav", "18.wav", "19.wav", "20.wav", "21.wav", "22.wav", "23.wav", "24.wav"}         
		
									, {"5.wav", "6.wav", "7.wav", "8.wav", "9.wav", "10.wav", "11.wav", "12.wav", "13.wav", "14.wav", "15.wav", "16.wav", "17.wav"
										, "18.wav", "19.wav", "20.wav", "21.wav", "22.wav", "23.wav", "24.wav", "25.wav", "26.wav", "27.wav", "28.wav", "29.wav"}
									
									, {"10.wav", "11.wav", "12.wav", "13.wav", "14.wav", "15.wav", "16.wav", "17.wav", "18.wav", "19.wav", "20.wav", "21.wav", "22.wav"
										, "23.wav", "24.wav", "25.wav", "26.wav", "27.wav", "28.wav", "29.wav", "30.wav", "31.wav", "32.wav", "33.wav", "34.wav"}
									
									, {"15.wav", "16.wav", "17.wav", "18.wav", "19.wav", "20.wav", "21.wav", "22.wav", "23.wav", "24.wav", "25.wav", "26.wav", "27.wav"
										, "28.wav", "29.wav", "30.wav", "31.wav", "32.wav", "33.wav", "34.wav", "35.wav", "36.wav", "37.wav", "38.wav", "39.wav"}
									
									, {"19.wav", "20.wav", "21.wav", "22.wav", "23.wav", "24.wav", "25.wav", "26.wav", "27.wav", "28.wav", "29.wav", "30.wav", "31.wav"
										, "32.wav", "33.wav", "34.wav", "35.wav", "36.wav", "37.wav", "38.wav", "39.wav", "40.wav", "41.wav", "42.wav", "43.wav"}
									
									, {"24.wav", "25.wav", "26.wav", "27.wav", "28.wav", "29.wav", "30.wav", "31.wav", "32.wav", "33.wav", "34.wav", "35.wav", "36.wav"
										, "37.wav", "38.wav", "39.wav", "40.wav", "41.wav", "42.wav", "43.wav", "44.wav", "45.wav", "46.wav", "47.wav", "48.wav"}
									};
										
		for(int i=0; i<6; i++)
			for(int j=0; j<25; j++)
				GuitarFiles[i][j] = new File(GuitarFolder[0]+GuitarFnames[i][j]);
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

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @brief 저장되어 음악파일을 오픈해준다.
 */
class FileOpen {
	
	//!피아노, 드럼 , 기타, 베이스 파일
	private File[][] PianoFiles, DrumFiles, GuitarFiles, BaseFiles;
	
	private AudioInputStream[][] PianoAIS, DrumAIS, GuitarAIS, BaseAIS;
	
	private Clip[][] PianoClip, DrumClip, GuitarClip, BaseClip;
	//!피아노, 드럼 , 기타, 베이스 폴더이름
	private String[] PianoFolder, DrumFolder, GuitarFolder, BaseFolder;
	
	//!피아노, 드럼 , 기타, 베이스 파일 이름
	private String[][] PianoFnames, DrumFnames, GuitarFnames, BaseFnames;
	
	//!피아노, 드럼 , 기타, 베이스 맵핑되는 음계명
	private String[] PianoSoundNames, DrumSoundNames, GuitarSoundNames, BaseSoundNames;
	
	//!기타 코드
	private String[][] GuitarCode;
	
	//!메트로놈
	private File MetronomeFile;
	private AudioInputStream MetronomeAIS;
	private Clip MetronomeClip;
	
	/**
	 * @brief 생성자
	 * 필요한 소리파일을 오픈한다.
	 */
	FileOpen()
	{

		PianoSoundNames = new String[]{"쉼표", "도", "도#", "레", "레#", "미", "파", "파#", "솔", "솔#", "라", "라#", "시"};
		DrumSoundNames = new String[]{"쉼표", "베이스", "하이헷", "스네어", "라이드", "크래시", "스몰탐", "하이탐"};
		GuitarSoundNames = new String[]{"쉼표", "6", "5", "4", "3", "2", "1"};
		BaseSoundNames = new String[]{"쉼표", "4", "3", "2", "1"};
		
		PianoFolder = new String[]{
			  "piano\\0\\"	
			, "piano\\1\\"
			, "piano\\2\\"
		};
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
	
		
		DrumFolder = new String[] {"drum\\"};
		DrumFnames = new String[][]{{"베이스.wav", "하이헷.wav", "스네어.wav", "라이드.wav", "크래시.wav", "스몰탐.wav", "하이탐.wav"}};

		GuitarFolder = new String[] {"guitar\\"};
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
	
		GuitarCode = new String[][] {  
			  {"x32010","335543", "x32310", "x32000", "335343", "x33011", "xx1212"}
			, {"xx0232", "x00231", "x00212", "x00222", "x00211", "x00233", "xx0101"}
			, {"022100", "022000", "020100", "021100", "020000", "022200", "012020"}
			, {"133211", "133111", "131211", "0x3210", "131111", "133311", "xx3434"}
			, {"320003", "355333", "320001", "320002", "353333", "355533", "xx2323"}
			, {"x02220", "002210", "002020", "002120", "002010", "002230", "x01212"}
			, {"224442", "224432", "x21202", "224342", "224232", "224452", "123131"}
			};
	
										
										
		PianoFiles = new File[12][3];
		PianoAIS = new AudioInputStream[12][3];
		PianoClip = new Clip[12][3];
		
		DrumFiles = new File[1][7];
		DrumAIS = new AudioInputStream[1][7];
		DrumClip = new Clip[1][7];
		
		GuitarFiles = new File[6][25];
		GuitarAIS = new AudioInputStream[6][25];
		GuitarClip = new Clip[6][25];
		
		/*
		 * BaseFiles = new File[x][y];
		 * BaseAIS = new AudioInputStream[x][y];
		 * BaseClip = new Clip[x][y];
		 */
		try
		{
			for(int i=0; i<12; i++)
				for(int j=0; j<3; j++)
				{
					PianoFiles[i][j] = new File(PianoFolder[j]+PianoFnames[i][j]);
					PianoAIS[i][j] = AudioSystem.getAudioInputStream(PianoFiles[i][j]);
					PianoClip[i][j] = AudioSystem.getClip();
					PianoClip[i][j].open(PianoAIS[i][j]);
				}
			for(int i=0; i<7; i++)
			{
				DrumFiles[0][i] = new File(DrumFolder[0]+DrumFnames[0][i]);
				DrumAIS[0][i] = AudioSystem.getAudioInputStream(DrumFiles[0][i]);
				DrumClip[0][i] = AudioSystem.getClip();
				DrumClip[0][i].open(DrumAIS[0][i]);
			}
			
			for(int i=0; i<6; i++)
				for(int j=0; j<25; j++)
				{
					GuitarFiles[i][j] = new File(GuitarFolder[0]+GuitarFnames[i][j]);
					GuitarAIS[i][j] = AudioSystem.getAudioInputStream(GuitarFiles[i][j]);
					GuitarClip[i][j] = AudioSystem.getClip();
					GuitarClip[i][j].open(GuitarAIS[i][j]);
				}
			/*
			 * Base작업 추가대기중
			 */
			
			MetronomeFile = new File("metronome.wav");
			MetronomeAIS = AudioSystem.getAudioInputStream(MetronomeFile);
			MetronomeClip = AudioSystem.getClip();
			MetronomeClip.open(MetronomeAIS);
		}
		catch(UnsupportedAudioFileException ue) { ue.printStackTrace(); }
		catch(LineUnavailableException le) { le.printStackTrace(); }
		catch(IOException ioe) { ioe.printStackTrace(); }
	}
	
	/**
	 * @brief 악기에 따른 사운드파일을 호출해 주는 메소드
	 * @param int type : 어떤 악기에 대한 것인지 분류. 0번 부터 3번 까지 차례대로 피아노, 드럼, 기타, 베이스
	 * return 각각의 악기별로 파일 배열명을 리턴
	 */
	public Clip[][] getSoundClips(int type)
	{
		switch(type)
		{
		case 0:
			return PianoClip;
		case 1:
			return DrumClip;
		case 2:
			return GuitarClip;
		case 3:
			return BaseClip;
		}
		return null;
	}
	
	/**
	 * @brief 메트로놈의 사운드 파일을 호출하는 메소드
	 * return 메트로놈 파일명을 리턴
	 */
	public Clip getMetronomeClips()
	{
		return MetronomeClip;
	}
	
	/**
	 * @brief 악기에 따른 사운드명을 호출하는 메소드
	 * @param int type : 어떤 악기에 대한 것인지 분류. 0번 부터 3번 까지 차례대로 피아노, 드럼, 기타, 베이스
	 * return 각각 악기의 사운드명을 리턴
	 */
	public String[] getSoundNames(int type)
	{
		switch(type)
		{
		case 0:
			return PianoSoundNames;
		case 1:
			return DrumSoundNames;			
		case 2:
			return GuitarSoundNames;
		case 3:
			return BaseSoundNames;
		}
		return null;
	}
	
	/**
	 * @brief 기타코드를 리턴해주는 메소드
	 * return 기타 코드 리턴
	 */
	public String[][] getGuitarCode()
	{
		return GuitarCode;
	}
}

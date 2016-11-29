import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @brief ����Ǿ� ���������� �������ش�.
 */
class FileOpen {
	
	//!�ǾƳ�, �巳 , ��Ÿ, ���̽� ����
	private File[][] PianoFiles, DrumFiles, GuitarFiles, BaseFiles;
	
	private AudioInputStream[][] PianoAIS, DrumAIS, GuitarAIS, BaseAIS;
	
	private Clip[][] PianoClip, DrumClip, GuitarClip, BaseClip;
	//!�ǾƳ�, �巳 , ��Ÿ, ���̽� �����̸�
	private String[] PianoFolder, DrumFolder, GuitarFolder, BaseFolder;
	
	//!�ǾƳ�, �巳 , ��Ÿ, ���̽� ���� �̸�
	private String[][] PianoFnames, DrumFnames, GuitarFnames, BaseFnames;
	
	//!�ǾƳ�, �巳 , ��Ÿ, ���̽� ���εǴ� �����
	private String[] PianoSoundNames, DrumSoundNames, GuitarSoundNames, BaseSoundNames;
	
	//!��Ÿ �ڵ�
	private String[][] GuitarCode;
	
	//!��Ʈ�γ�
	private File MetronomeFile;
	private AudioInputStream MetronomeAIS;
	private Clip MetronomeClip;
	
	/**
	 * @brief ������
	 * �ʿ��� �Ҹ������� �����Ѵ�.
	 */
	FileOpen()
	{

		PianoSoundNames = new String[]{"��ǥ", "��", "��#", "��", "��#", "��", "��", "��#", "��", "��#", "��", "��#", "��"};
		DrumSoundNames = new String[]{"��ǥ", "���̽�", "������", "���׾�", "���̵�", "ũ����", "����Ž", "����Ž"};
		GuitarSoundNames = new String[]{"��ǥ", "6", "5", "4", "3", "2", "1"};
		BaseSoundNames = new String[]{"��ǥ", "4", "3", "2", "1"};
		
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
		DrumFnames = new String[][]{{"���̽�.wav", "������.wav", "���׾�.wav", "���̵�.wav", "ũ����.wav", "����Ž.wav", "����Ž.wav"}};

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
			 * Base�۾� �߰������
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
	 * @brief �Ǳ⿡ ���� ���������� ȣ���� �ִ� �޼ҵ�
	 * @param int type : � �Ǳ⿡ ���� ������ �з�. 0�� ���� 3�� ���� ���ʴ�� �ǾƳ�, �巳, ��Ÿ, ���̽�
	 * return ������ �Ǳ⺰�� ���� �迭���� ����
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
	 * @brief ��Ʈ�γ��� ���� ������ ȣ���ϴ� �޼ҵ�
	 * return ��Ʈ�γ� ���ϸ��� ����
	 */
	public Clip getMetronomeClips()
	{
		return MetronomeClip;
	}
	
	/**
	 * @brief �Ǳ⿡ ���� ������� ȣ���ϴ� �޼ҵ�
	 * @param int type : � �Ǳ⿡ ���� ������ �з�. 0�� ���� 3�� ���� ���ʴ�� �ǾƳ�, �巳, ��Ÿ, ���̽�
	 * return ���� �Ǳ��� ������� ����
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
	 * @brief ��Ÿ�ڵ带 �������ִ� �޼ҵ�
	 * return ��Ÿ �ڵ� ����
	 */
	public String[][] getGuitarCode()
	{
		return GuitarCode;
	}
}

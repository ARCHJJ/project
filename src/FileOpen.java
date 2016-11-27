import java.io.File;

/**
 * @brief 저장되어 음악파일을 오픈해준다.
 */
class FileOpen {
	
	//!피아노, 드럼 , 기타, 베이스 파일들의 경로
	private File[][] PianoFiles, DrumFiles, GuitarFiles, BaseFiles;
	//!피아노, 드럼 , 기타, 베이스 파일 이름
	private String[][] PianoFnames, DrumFnames, GuitarFnames, BaseFnames;
	//!피아노, 드럼 , 기타, 베이스 폴더이름
	private String[] PianoFolder, DrumFolder, GuitarFolder, BaseFolder;
	//!피아노, 드럼 , 기타, 베이스 맵핑되는 음계명
	private String[] PianoSoundNames, DrumSoundNames, GuitarSoundNames, BaseSoundNames;
	//!기타 코드
	private String[][] GuitarCode;
	
	/**
	 * @brief 생성자
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
		
		DrumFolder = new String[] {"drum\\"};
		DrumFiles = new File[1][7];
		DrumFnames = new String[][]{{"베이스.wav", "하이헷.wav", "스네어.wav", "라이드.wav", "크래시.wav", "스몰탐.wav", "하이탐.wav"}};
		for(int i=0; i<7; i++)
			DrumFiles[0][i] = new File(DrumFolder[0]+DrumFnames[0][i]);	
		
		GuitarFolder = new String[] {"guitar\\"};
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
//		GuitarCode = new String[][] { {"x,3,2,0,1,0", "3,3,5,5,4,3", "x,3,2,3,1,0", "x,3,2,0,0,0", "3,3,5,3,4,3", "x,3,3,0,1,1", "x,x,1,2,1,2"}
//									, {"x,x,0,2,3,2", "x,0,0,2,3,1", "x,0,0,2,1,2", "x,0,0,2,2,2", "x,0,0,2,1,1", "x,0,0,2,3,3", "x,x,0,1,0,1"}
//									, {"0,2,2,1,0,0", "0,2,2,0,0,0", "0,2,0,1,0,0", "0,2,1,1,0,0", "0,2,0,0,0,0", "0,2,2,2,0,0", "0,1,2,0,2,0"}
//									, {"1,3,3,2,1,1", "1,3,3,1,1,1", "1,3,1,2,1,1", "0,x,3,2,1,0", "1,3,1,1,1,1", "1,3,3,3,1,1", "x,x,3,4,3,4"}
//									, {"3,2,0,0,0,3", "3,5,5,3,3,3", "3,2,0,0,0,1", "3,2,0,0,0,2", "3,5,3,3,3,3", "3,5,5,5,3,3", "x,x,2,3,2,3"}
//									, {"x,0,2,2,2,0", "0,0,2,2,1,0", "0,0,2,0,2,0", "0,0,2,1,2,0", "0,0,2,0,1,0", "0,0,2,2,3,0", "x,0,1,2,1,2"}
//									, {"x,0,2,2,2,0", "0,0,2,2,1,0", "0,0,2,0,2,0", "0,0,2,1,2,0", "0,0,2,0,1,0", "0,0,2,2,3,0", "x,0,1,2,1,2"}
//									, {"2,2,4,4,4,2", "2,2,4,4,3,2", "x,2,1,2,0,2", "2,2,4,3,4,2", "2,2,4,2,3,2", "2,2,4,4,5,2", "1,2,3,1,3,1"}
//									};
		GuitarCode = new String[][] {  {"x32010","335543", "x32310", "x32000", "335343", "x33011", "xx1212"}
									 , {"xx0232", "x00231", "x00212", "x00222", "x00211", "x00233", "xx0101"}
									 , {"022100", "022000", "020100", "021100", "020000", "022200", "012020"}
									 , {"133211", "133111", "131211", "0x3210", "131111", "133311", "xx3434"}
									 , {"320003", "355333", "320001", "320002", "353333", "355533", "xx2323"}
									 , {"x02220", "002210", "002020", "002120", "002010", "002230", "x01212"}
									 , {"224442", "224432", "x21202", "224342", "224232", "224452", "123131"}
									 };
	
	}
	
	/**
	 * @brief 악기에 따른 사운드파일을 호출해 주는 메소드
	 * @param int type 어떤 악기에 대한 것인지 분류
	 * return 각각의 악기별로 파일 배열명을 리턴
	 */
	public File[][] getSoundFiles(int type)
	{
		switch(type)
		{
		case 0:
			return PianoFiles;
		case 1:
			return DrumFiles;
		case 2:
			return GuitarFiles;
		case 3:
			return BaseFiles;
		}
		return null;
	}
	
	/**
	 * @brief 악기에 따른 사운드명을 호출하는 메소드
	 * @param int type
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

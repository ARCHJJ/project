import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
@SuppressWarnings({ "unchecked", "rawtypes" })
/**
 * @brief 메인프로그램의 GUI를 정의하는 클래스
 * 컴포넌트 집합체로서 Orpheus 클래스의 부모클래스가 된다.  
 */
abstract class OrpheusComponents {

	//! GUI에서 메인이 되는 Frame
	protected JFrame mainFrame;
	
	//! GUI에서 메인이 되는 Panel
	protected JPanel contentPane;
	
	//! 악기, 박자, 음높이 총 3가지를 설정하는 테이블
	protected JTable[] table_Field;

	//! 작업대기줄을 보여주는 테이블 차례대로 피아노, 드럼, 기타, 베이스
	protected JTable[] table_Task; //
	
	//! table_Field에 스크롤을 추가하는 ScrollPane 
	protected JScrollPane[] scrollPane_Field;
	
	//! table_Task에 스크롤을 추가하는 ScrollPane
	protected JScrollPane[] scrollPane_Task;
	
	//! 비트를 선택하는 콤보박스
	protected JComboBox BeatSet;
	
	//! 뱅크를 선택하는 콤보박스
	protected JComboBox BankChoice;
	
	//! 리듬을 선택하는 콤보박스
	protected JComboBox RhythmChoice1;
	
	//! 리듬을 선택하는 콤보박스
	protected JComboBox RhythmChoice2;
	
	//! 근음을 선택하는 콤보박스
	protected JComboBox RootChord;
	
	//! 근음에서 파생되는음을 선택하는 콤보박스
	protected JComboBox ChildChord;
	
	//! BPMSet : BPM을 입력하는 텍스트필드 
	protected JTextField BPMSet;
	
	//! 악기선택버튼을 안내해주는 레이블
	protected JLabel lbl_SelectInstrument;
	
	//! BPMSet을 안내해주는 레이블
	protected JLabel lbl_SelectBPM;
	
	//! BeatSet을 안내해주는 레이블
	protected JLabel lbl_SelectBeatSet;

	//! 작업대기줄에서 재생되는 악기를 뮤트시키는 체크박스. 차례대로 피아노, 드럼, 기타, 베이스
	protected JCheckBox[] Mute;
	
	//! 작업대기줄 실핼시 메트로놈의 활성화 여부를 묻는 체크박스
	protected JCheckBox metronome_Check;

	//! 연주시작버튼. 작업대기줄에서 설정한 뱅크를 차례로 재생한다.
	protected JButton btn_start;
	
	//! 초기화 버튼. table_Field의 내용을 모두 지운다. 
	protected JButton btn_init;
	
	//! 지우기버튼. table_Field의 열을 하나 지운다.
	protected JButton btn_erase;
	
	//! 피아노선택버튼. table_Field을 피아노로 셋팅한다.
	protected JButton btn_SelectToPiano;
	
	//! 드럼선택버튼. table_Field을 드럼으로 셋팅한다.
	protected JButton btn_SelectToDrum;
	
	//! 기타선택버튼. table_Field을 기타로 셋팅한다.
	protected JButton btn_SelectToGuitar;
	
	//! 베이스선택버튼. table_Field을 베이스로 셋팅한다.
	protected JButton btn_SelectToBase;
	
	//! 뱅크저장버튼. table_Field[1], table_Field[2]의 내용을 뱅크에 저장한다.
	protected JButton btn_BankSave;
	
	//! 뱅크재생버튼. 콤보박스로 선택한 뱅크를 재생한다.
	protected JButton btn_BankListen;
	
	//! 리듬삽입버튼. 미리 만들어져 있는 리듬을 table_Field[1], table_Field[2]에 삽입한다.
	protected JButton btn_RhythmInsert;
	
	//! 코드삽입버튼. 미리 만들어져 있는 코드를 table_Field[1], table_Field[2]에 삽입한다.
	protected JButton btn_RhythmListen;
	
	//! 리듬듣기버튼. 미리 만들어져 있는 리듬을 들어본다.
	protected JButton btn_ChordInsert;
	
	//! 코드듣기버튼. 미리 만들어져 있는 코드를 들어본다.
	protected JButton btn_ChordListen;
	
	//! 솔로버튼. 선택한 작업대기줄만 재생한다.
	protected JButton[] btn_Solo;
	
	//! 키보드연주버튼. 키보드 연주를 가능하게 한다. 
	protected JButton btn_KeyboardPlay;
	
	//! 파일 저장버튼. 작업 내용을 저장한다. 
	protected JButton btn_SaveScore;
	
	//! 파일 열람버튼. 작업 내용을 오픈한다. 
	protected JButton btn_OpenScore;
	
	//! table_Field[0]을 구성하는 클래스객체. 차례대로 피아노, 드럼, 기타, 베이스 
	protected SettingToKind[] STK;
	
	//! table_Field[1]을 구성하는 클래스객체. 차례대로 피아노, 드럼, 기타, 베이스
	protected BeatField[] STB;
	
	//! table_Field[2]을 구성하는 클래스객체. 차례대로 피아노, 드럼, 기타, 베이스
	protected SettingToField[] STF;
	
	//! table_Task를 구성하는 클래스객체. 차례대로 피아노, 드럼, 기타, 베이스
	protected TaskField[] STT;
	
	//! 뱅크저장을 위한 클래스객체. 차례대로 피아노, 드럼, 기타, 베이스
	protected SaveBank[] Bank;

	//! BeatSet을 구성하는 String 배열
	protected String[] BeatList = {"2/2", "2/4", "3/4", "4/4", "-----", "6/8", "9/8", "12/8", "----- ", "7/4", "11/4", "5/4"};

	//! RhythmChoice를 구성하는 String 배열
	protected String[] RhythmList1 = {"1", "2", "4"};
	
	//! RhythmChoice를 구성하는 String 배열
	protected String[] RhythmList2 = {"1", "2", "3", "4"};
	
	//! RootChord를 구성하는 String 배열
	protected String[] RootChordList = {"C", "D", "E", "F", "G", "A", "B"};
	
	//! ChildChord를 구성하는 String 배열
	protected String[] ChildChordList = {"M", "m", "7", "M7", "m7", "sus4", "dim"};
	
	//! 피아노의 음높이를 나타내는 String 배열. STF[0]의 생성자로 전달한다.
	protected String[] Piano_tones = {"", "1", "2", "3"};
	
	//! 기타의 음높이를 나타내는 String 배열. STF[2], STF[3]의 생성자로 전달한다.
	protected String[] Guitar_tones = {"", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
	
	//! 파일오픈클래스의 객체. 재생에 필요한 소리파일들을 오픈한다.
	protected FileOpen files;
	
	//! 키보드연주를 위한 클래스의 객체
	protected PlayToKeyboard keyboardPlay;
	
	//! 미리 만들어진 코드를 테이블에 삽입하기 위한 클래스의 객체
	protected InputGuitarCode Code;
	
	//! 미리 만들어진 드럼리듬을 테이블에 삽입하기 위한 클래스의 객체
	protected InputDrumRhythm DrumRhythm;
	
	//! 미리 만들어진 드럼리듬을 재생하기 위한 객체
	protected PlayDrumRhythm DrumPlay;
	
	//! 미리 만들어진 드럼리듬을 테이블에 삽입하기 위한 클래스의 객체
	protected InputBassRhythm BassRhythm;
	
	//! 미리 만들어진 드럼리듬을 실행하기 위한 클래스의 객체
	protected PlayBassRhythm BassPlay;
	
	//! 뱅크미리듣기를 위한 객체
	protected Play bankPlay;
	
	//! 솔로듣기를 위한 객체
	protected Play[] taskPlay;
	
	//! 메트로놈을 위한 객체
	protected Metronome metronome;
	
	//! 파일 저장을 위한 객체
	protected SaveScore save;
	
	//! 파일 열람을 위한 객체
	protected OpenScore open;

	//! 현재 보고 있는 화면이 어떤 악기인지 구분하기 위한 변수
	protected int IDX;
	
	//! 총 뱅크 갯수
	protected int totalBankCount;
	
	//! 예외처리 BPM
	protected IntegerDocument IdBPM;
	
	//! 저장여부를 묻는 창을 띄우는 조건으로 사용될 변수
	protected boolean isSave;
	
	//! 버튼의 중복클릭을 판단하기 위한 클래스
	protected Swtch[] swtch;
	
	//! 버튼에 이미지를 삽입하기 위한 icon[]
	private ImageIcon[] icon;
	
	/**
	 * @brief 생성자
	 * 사용할 컴포넌트를 할당하고 위치를 설정한다. 
	 */
	public OrpheusComponents() {
		mainFrame = new JFrame();
		mainFrame.setTitle("오르페우스 ver.1.0 (by. 디오니소스님)");
		mainFrame.setForeground(Color.WHITE);
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("images.png"));//icon
		mainFrame.setBounds(100, 20, 800, 768);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainFrame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//폰트
		Font HumanRoundHeadLine12 = new Font("휴먼둥근헤드라인", Font.PLAIN, 12);
		Font HumanRoundHeadLine20 = new Font("휴먼둥근헤드라인", Font.BOLD, 18);
		Font PureGothic12 = new Font("맑은 고딕", Font.PLAIN, 12);
		Font PureGothic14 = new Font("맑은 고딕", Font.BOLD, 14);
		Color bgcolor = new Color(255, 255, 255);
		Color fgcolor = new Color(0, 0, 0);
		lbl_SelectBeatSet = new JLabel("박자  : ");
		lbl_SelectBeatSet.setFont(PureGothic14);
		lbl_SelectBeatSet.setBounds(575, 20, 54, 23);
		contentPane.add(lbl_SelectBeatSet);

		BeatSet = new JComboBox(BeatList);
		BeatSet.setFont(PureGothic12);
		BeatSet.setSelectedItem("4/4");
		BeatSet.setBounds(625, 23, 53, 20);
		contentPane.add(BeatSet);		

		BankChoice = new JComboBox();
		BankChoice.setBounds(12, 83, 100, 25);
		contentPane.add(BankChoice);
		
		RhythmChoice1 = new JComboBox(RhythmList1);
		RhythmChoice1.setBounds(238, 83, 100, 25);
		contentPane.add(RhythmChoice1);
		
		RhythmChoice2 = new JComboBox(RhythmList2);
		RhythmChoice2.setBounds(238, 120, 100, 25);
		contentPane.add(RhythmChoice2);
		
		RootChord = new JComboBox(RootChordList);
		RootChord.setBounds(464, 83, 100, 25);
		contentPane.add(RootChord);

		ChildChord = new JComboBox(ChildChordList);
		ChildChord.setBounds(464, 120, 100, 25);
		contentPane.add(ChildChord);
	
		lbl_SelectBPM = new JLabel("BPM : ");
		lbl_SelectBPM.setFont(PureGothic14);
		lbl_SelectBPM.setBounds(576, 45, 57, 25);
		contentPane.add(lbl_SelectBPM);
		
		IdBPM = new IntegerDocument();
		
		BPMSet = new JTextField();
		BPMSet.setFont(PureGothic12);
		BPMSet.setBounds(627, 48, 50, 23);
		BPMSet.setDocument(IdBPM);
		BPMSet.setText("100");
		contentPane.add(BPMSet);
		
		metronome_Check = new JCheckBox("메트로놈");
		metronome_Check.setBounds(681, 219, 97, 23);
		metronome_Check.setBackground(SystemColor.window);
		metronome_Check.setFont(PureGothic14);
		contentPane.add(metronome_Check);
		
		//FileOpen
		files = new FileOpen();
		keyboardPlay = new PlayToKeyboard(files);
		keyboardPlay.setVisible(false);
		Code = new InputGuitarCode(files.getCode(2));
		DrumRhythm = new InputDrumRhythm();
		DrumPlay = new PlayDrumRhythm();
		
		BassRhythm =  new InputBassRhythm();
		BassPlay = new PlayBassRhythm();
		
		save = new SaveScore();
		open = new OpenScore();
		
		//0번부터 3번까지 차례로 피아노, 드럼, 기타, 베이스
		STB = new BeatField[4];
		STT = new TaskField[4];
		STK = new SettingToKind[4];
		Mute = new JCheckBox[4];
		table_Task = new JTable[4];
		scrollPane_Task = new JScrollPane[4];
		btn_Solo = new JButton[4];
		table_Field = new JTable[3]; //0 : table_Kind, 1 : table_Beat, 2: table_Field
		scrollPane_Field = new JScrollPane[3];
		String[] instruments = {"피아노", "드럼", "기타", "베이스"};
		
		for(int i=0, TaskY=468, MuteY = 496; i<4; i++)
		{
			if(i<3)
			{
				table_Field[i] = new JTable();
				table_Field[i].setBackground(Color.WHITE);
				table_Field[i].setForeground(fgcolor);
				table_Field[i].setFont(PureGothic12);
				table_Field[i].setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				scrollPane_Field[i] = new JScrollPane(table_Field[i]);
				scrollPane_Field[i].getViewport().setBackground(Color.WHITE);
				
				contentPane.add(scrollPane_Field[i]);
			}
			STB[i] = new BeatField();
			STK[i] = new SettingToKind(files.getSoundNames(i));
			STT[i] = new TaskField(instruments[i]);			
						
			table_Task[i] = new JTable(STT[i].getModel());
			table_Task[i].setFont(PureGothic12);
			table_Task[i].setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			scrollPane_Task[i] = new JScrollPane(table_Task[i]);
			//scrollPane_Task[i].getHorizontalScrollBar().setValue(scrollPane_Task[i].getHorizontalScrollBar().getMaximum());
			scrollPane_Task[i].getViewport().setBackground(Color.WHITE);
			scrollPane_Task[i].setBounds(12, TaskY, 666, 65);
			
			STT[i].setCellOption(table_Task[i]);
			
			btn_Solo[i] = new JButton(instruments[i]+"솔로");
			btn_Solo[i].setBackground(SystemColor.window);
			btn_Solo[i].setFont(PureGothic12);
			btn_Solo[i].setBounds(681, TaskY, 97, 23);
			
			Mute[i] = new JCheckBox(instruments[i]+"뮤트");
			Mute[i].setBackground(SystemColor.window);
			Mute[i].setFont(PureGothic12);
			Mute[i].setBounds(681, MuteY, 97, 23);
			
			contentPane.add(scrollPane_Task[i]);
			contentPane.add(btn_Solo[i]);
			contentPane.add(Mute[i]);
		
			TaskY+=62;
			MuteY+=62;
		}
		scrollPane_Field[0].setBounds(12, 216, 66, 255);
		scrollPane_Field[1].setBounds(12, 155, 666, 65);
		scrollPane_Field[2].setBounds(75, 216, 603, 255);
		
		//0번부터 3번까지 차례로 피아노, 드럼, 기타, 베이스
		STF = new SettingToField[4];
		STF[0] = new CmbBoxField(STB[0], table_Field[1], files.getSoundNames(0).length, Piano_tones);
		STF[1] = new ChkBoxField(STB[1], table_Field[1], files.getSoundNames(1).length);
		STF[2] = new CmbBoxField(STB[2], table_Field[1], files.getSoundNames(2).length, Guitar_tones);
		STF[3] = new CmbBoxField(STB[3], table_Field[1], files.getSoundNames(3).length, Guitar_tones);		

		Bank = new SaveBank[4];
		Bank[0] = new SaveCmbBoxBank(STF[0]);
		Bank[1] = new SaveChkBoxBank(STF[1]);
		Bank[2] = new SaveCmbBoxBank(STF[2]);
		Bank[3] = new SaveCmbBoxBank(STF[3]);
		
		icon = new ImageIcon[4];
		for(int i=0; i<4; i++)
			icon[i] = new ImageIcon("image\\button"+Integer.toString(i+1)+".jpg");


		lbl_SelectInstrument = new JLabel("악기선택 :");
		lbl_SelectInstrument.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_SelectInstrument.setFont(HumanRoundHeadLine20);
		lbl_SelectInstrument.setBounds(12, 24, 100, 50);
		contentPane.add(lbl_SelectInstrument);
		
		btn_SelectToPiano = new JButton(icon[0]);
		btn_SelectToPiano.setText("피아노");
		btn_SelectToPiano.setContentAreaFilled(false);
		btn_SelectToPiano.setFont(PureGothic12);
		btn_SelectToPiano.setForeground(Color.BLACK);
		btn_SelectToPiano.setBounds(125, 20, 100, 50);
		contentPane.add(btn_SelectToPiano);
		
		btn_SelectToDrum = new JButton(icon[1]);
		btn_SelectToDrum.setText("드럼");
		btn_SelectToDrum.setContentAreaFilled(false);
		btn_SelectToDrum.setFont(PureGothic12);
		btn_SelectToDrum.setBounds(238, 20, 100, 50);
		contentPane.add(btn_SelectToDrum);
		
		btn_SelectToGuitar = new JButton(icon[2]);
		btn_SelectToGuitar.setText("기타");
		btn_SelectToGuitar.setContentAreaFilled(false);
		btn_SelectToGuitar.setFont(PureGothic12);
		btn_SelectToGuitar.setBounds(351, 20, 100, 50);
		contentPane.add(btn_SelectToGuitar);
		
		btn_SelectToBase = new JButton(icon[3]);
		btn_SelectToBase.setText("베이스");
		btn_SelectToBase.setContentAreaFilled(false);
		btn_SelectToBase.setFont(PureGothic12);
		btn_SelectToBase.setBounds(464, 20, 100, 50);
		contentPane.add(btn_SelectToBase);
		
		btn_start = new JButton("연주시작");
		btn_start.setBackground(bgcolor);
		btn_start.setFont(HumanRoundHeadLine12);
		btn_start.setBounds(681, 249, 97, 33);
		contentPane.add(btn_start);
		
		btn_init = new JButton("초기화");
		btn_init.setBackground(bgcolor);
		btn_init.setFont(HumanRoundHeadLine12);
		btn_init.setBounds(681, 299, 97, 33);
		contentPane.add(btn_init);
	
		btn_erase = new JButton("열지우기");
		btn_erase.setBackground(bgcolor);
		btn_erase.setFont(HumanRoundHeadLine12);
		btn_erase.setBounds(681, 349, 97, 33);
		contentPane.add(btn_erase);
		
		btn_KeyboardPlay = new JButton("키보드연주");
		btn_KeyboardPlay.setBackground(bgcolor);
		btn_KeyboardPlay.setFont(HumanRoundHeadLine12);
		btn_KeyboardPlay.setBounds(681, 399, 97, 33);
		contentPane.add(btn_KeyboardPlay);

		btn_BankSave = new JButton("뱅크 저장");
		btn_BankSave.setBackground(Color.WHITE);
		btn_BankSave.setFont(PureGothic12);
		btn_BankSave.setBounds(125, 83, 100, 25);
		contentPane.add(btn_BankSave);
		
		btn_BankListen = new JButton("뱅크 듣기");
		btn_BankListen.setBackground(Color.WHITE);
		btn_BankListen.setFont(PureGothic12);
		btn_BankListen.setBounds(125, 120, 100, 25);
		contentPane.add(btn_BankListen);
		
		btn_RhythmListen = new JButton("리듬 듣기");
		btn_RhythmListen.setBackground(Color.WHITE);
		btn_RhythmListen.setFont(PureGothic12);
		btn_RhythmListen.setBounds(351, 83, 100, 25);
		contentPane.add(btn_RhythmListen);
		
		btn_RhythmInsert = new JButton("리듬 입력");
		btn_RhythmInsert.setBackground(Color.WHITE);
		btn_RhythmInsert.setFont(PureGothic12);
		btn_RhythmInsert.setBounds(351, 120, 100, 25);
		contentPane.add(btn_RhythmInsert);
		
		btn_ChordListen = new JButton("코드 듣기");
		btn_ChordListen.setBackground(Color.WHITE);
		btn_ChordListen.setFont(PureGothic12);
		btn_ChordListen.setBounds(577, 83, 100, 25);
		contentPane.add(btn_ChordListen);
		
		btn_ChordInsert = new JButton("코드 입력");
		btn_ChordInsert.setBackground(Color.WHITE);
		btn_ChordInsert.setFont(PureGothic12);
		btn_ChordInsert.setBounds(577, 120, 100, 25);
		contentPane.add(btn_ChordInsert);
		
		btn_SaveScore = new JButton("파일 저장");
		btn_SaveScore.setBackground(SystemColor.window);
		btn_SaveScore.setFont(PureGothic12);
		btn_SaveScore.setBounds(681, 155, 97, 23);
		contentPane.add(btn_SaveScore);
		
		btn_OpenScore = new JButton("파일 열람");
		btn_OpenScore.setBackground(SystemColor.window);
		btn_OpenScore.setFont(PureGothic12);
		btn_OpenScore.setBounds(681, 188, 97, 23);
		contentPane.add(btn_OpenScore);

		swtch = new Swtch[6];
		for(int i=0; i<6; i++)
			swtch[i] = new Swtch();
		//swtch[0] = 피아노솔로
		//swtch[1] = 드럼솔로
		//swtch[2] = 기타솔로
		//swtch[3] = 베이스솔로
		//swtch[4] = 뱅크듣기
		//swtch[5] = 연주시작
	}
}

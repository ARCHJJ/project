import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.util.ArrayList;

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
	protected JComboBox RhythmChoice;
	
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
	
	//! 지우기버튼. table_Field의 내용을 모두 지운다.
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
	
	//! 피아노솔로버튼. 피아노작업대기줄만 재생한다.
	protected JButton btn_PianoSolo;
	
	//! 드럼솔로버튼. 드럼작업대기줄만 재생한다.
	protected JButton btn_DrumSolo;
	
	//! 기타솔로버튼. 기타작업대기줄만 재생한다.
	protected JButton btn_GuitarSolo;

	//! 베이스솔로버튼. 베이스작업대기줄만 재생한다.
	protected JButton btn_BaseSolo;
	
	//! 키보드연주버튼. 키보드 연주를 가능하게 한다. 
	protected JButton btn_KeyboardPlay;
	
	//! 파일 저장버튼. 작업 내용을 저장한다. 
	protected JButton btn_SaveScore;
	
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
	protected String[] BeatList = {"2/2", "2/4", "3/4", "4/4", "-----", "6/8", "9/8", "12/8", "-----", "7/4", "11/4", "5/4"};

	//! RhythmChoice를 구성하는 String 배열
	protected String[] RhythmList = {"1","2","3","4"};
	
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
	
	//! 뱅크미리듣기를 위한 객체
	protected Play bankPlay;
	
	//! 솔로듣기를 위한 객체
	protected Play[] taskPlay;
	
	//! 듣기버튼들의 중복클릭을 방지하기 위해 여러개의 버튼을 비활성화 시키기 위해 ArrayList에 저장
	protected ArrayList<JButton> ListenButtons;
	
	//! 메트로놈을 위한 객체
	protected Metronome metronome;
	
	//! 파일 저장을 위한 객체
	protected SaveScore save;

	//! 현재 보고 있는 화면이 어떤 악기인지 구분하기 위한 변수
	protected int IDX;
	
	//! 총 뱅크 갯수
	protected int totalBankCount;
	
	//! 저장여부를 묻는 창을 띄우는 조건으로 사용될 변수
	protected boolean isSave;
	
	private ImageIcon[] icon;
	/**
	 * @brief 생성자
	 * 사용할 컴포넌트를 할당하고 위치를 설정한다. 
	 */
	public OrpheusComponents() {
		mainFrame = new JFrame();
		mainFrame.setTitle("\uD504\uB85C\uC81D\uD2B8 \uC624\uB974\uD398\uC6B0\uC2A4 ver.1.0 (by. \uB514\uC624\uB2C8\uC18C\uC2A4\uB2D8\u2606)");
		mainFrame.setForeground(Color.WHITE);
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("images.png"));//icon
		//mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(100, 20, 840, 880);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("/Images/about.png")))));
		mainFrame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//폰트
		Font HumanRoundHeadLine = new Font("휴먼둥근헤드라인", Font.PLAIN, 12);
		Font PureGothic = new Font("맑은 고딕", Font.PLAIN, 12);
		Color bgcolor = new Color(255, 255, 255);
		Color fgcolor = new Color(0, 0, 0);
		lbl_SelectBeatSet = new JLabel("박자 : ");
		lbl_SelectBeatSet.setFont(PureGothic);
		lbl_SelectBeatSet.setBounds(605, 14, 57, 15);
		contentPane.add(lbl_SelectBeatSet);

		BeatSet = new JComboBox(BeatList);
		BeatSet.setFont(PureGothic);
		BeatSet.setSelectedItem("4/4");
		BeatSet.setBounds(644, 10, 55, 23);
		contentPane.add(BeatSet);		

		BankChoice = new JComboBox();
		BankChoice.setBounds(12, 152, 100, 23);
		contentPane.add(BankChoice);
		
		RhythmChoice = new JComboBox(RhythmList);
		RhythmChoice.setBounds(234, 152, 100, 23);
		contentPane.add(RhythmChoice);
		
		RootChord = new JComboBox(RootChordList);
		RootChord.setBounds(480, 152, 100, 23);
		contentPane.add(RootChord);

		ChildChord = new JComboBox(ChildChordList);
		ChildChord.setBounds(480, 189, 100, 23);
		contentPane.add(ChildChord);
	
		lbl_SelectBPM = new JLabel("BPM : ");
		lbl_SelectBPM.setFont(PureGothic);
		lbl_SelectBPM.setBounds(711, 14, 57, 15);
		contentPane.add(lbl_SelectBPM);
		
		BPMSet = new JTextField("100");
		BPMSet.setFont(PureGothic);
		BPMSet.setBounds(752, 10, 60, 23);
		contentPane.add(BPMSet);
		
		metronome_Check = new JCheckBox("메트로놈");
		metronome_Check.setBounds(12, 804, 97, 23);
		metronome_Check.setBackground(SystemColor.window);
		metronome_Check.setFont(PureGothic);
		contentPane.add(metronome_Check);
		
		//FileOpen
		files = new FileOpen();
		keyboardPlay = new PlayToKeyboard(files);
		keyboardPlay.setVisible(false);
		Code = new InputGuitarCode(files.getGuitarCode());
		
		//0번부터 3번까지 차례로 피아노, 드럼, 기타, 베이스
		STB = new BeatField[4];
		STT = new TaskField[4];
		STK = new SettingToKind[4];
		Mute = new JCheckBox[4];
		table_Task = new JTable[4];
		scrollPane_Task = new JScrollPane[4];
		
		//0 : table_Kind, 1 : table_Beat, 2: table_Field
		table_Field = new JTable[3];
		scrollPane_Field = new JScrollPane[3];
		String[] instruments = {"피아노", "드럼", "기타", "베이스"};
		for(int i=0, TaskY=469, MuteY = 497; i<4; i++)
		{
			if(i<3)
			{
				table_Field[i] = new JTable();
				table_Field[i].setBackground(Color.WHITE);
				table_Field[i].setForeground(fgcolor);
				table_Field[i].setFont(PureGothic);
				table_Field[i].setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				scrollPane_Field[i] = new JScrollPane(table_Field[i]);
				scrollPane_Field[i].getViewport().setBackground(Color.WHITE);
				
				contentPane.add(scrollPane_Field[i]);
			}
			STB[i] = new BeatField();
			STK[i] = new SettingToKind(files.getSoundNames(i));
			STT[i] = new TaskField(instruments[i]);			
						
			table_Task[i] = new JTable(STT[i].getModel());
			table_Task[i].setFont(PureGothic);
			table_Task[i].setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			scrollPane_Task[i] = new JScrollPane(table_Task[i]);
			scrollPane_Task[i].getViewport().setBackground(Color.WHITE);
			scrollPane_Task[i].setBounds(12, TaskY, 703, 65);
			
			STT[i].setCellOption(table_Task[i]);
			
			Mute[i] = new JCheckBox(instruments[i]+"뮤트");
			Mute[i].setBackground(SystemColor.window);
			Mute[i].setFont(PureGothic);
			Mute[i].setBounds(718, MuteY, 97, 23);
			
			contentPane.add(scrollPane_Task[i]);
			contentPane.add(Mute[i]);
			
			TaskY+=64;
			MuteY+=64;
		}
		scrollPane_Field[0].setBounds(12, 220, 63, 251);
		scrollPane_Field[1].setBounds(12, 155, 703, 65);
		scrollPane_Field[2].setBounds(75, 220, 640, 251);
		
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
		
		icon=new ImageIcon[4];
		for(int i=0; i<4; i++)
			icon[i] = new ImageIcon("image\\button"+Integer.toString(i+1)+".png");

		lbl_SelectInstrument = new JLabel("악기선택");
		lbl_SelectInstrument.setFont(HumanRoundHeadLine);
		lbl_SelectInstrument.setBounds(12, 12, 57, 15);
		contentPane.add(lbl_SelectInstrument);
		
		btn_SelectToPiano = new JButton(icon[0]);
		btn_SelectToPiano.setText("피아노");
		btn_SelectToPiano.setBackground(Color.WHITE);
		btn_SelectToPiano.setFont(PureGothic);
		btn_SelectToPiano.setForeground(Color.BLACK);
		btn_SelectToPiano.setBounds(12, 40, 200, 100);
		contentPane.add(btn_SelectToPiano);
		
		btn_SelectToDrum = new JButton(icon[1]);
		btn_SelectToDrum.setText("드럼");
		btn_SelectToDrum.setBackground(Color.WHITE);
		btn_SelectToDrum.setFont(PureGothic);
		btn_SelectToDrum.setBounds(212, 40, 200, 100);
		contentPane.add(btn_SelectToDrum);
		
		btn_SelectToGuitar = new JButton(icon[2]);
		btn_SelectToGuitar.setText("기타");
		btn_SelectToGuitar.setBackground(Color.WHITE);
		btn_SelectToGuitar.setFont(PureGothic);
		btn_SelectToGuitar.setBounds(412, 40, 200, 100);
		contentPane.add(btn_SelectToGuitar);
		
		btn_SelectToBase = new JButton(icon[3]);
		btn_SelectToBase.setText("베이스");
		btn_SelectToBase.setBackground(Color.WHITE);
		btn_SelectToBase.setFont(PureGothic);
		btn_SelectToBase.setBounds(612, 40, 200, 100);
		contentPane.add(btn_SelectToBase);
		
		btn_start = new JButton("연주시작");
		btn_start.setBackground(bgcolor);
		btn_start.setFont(HumanRoundHeadLine);
		btn_start.setBounds(393, 804, 99, 33);
		contentPane.add(btn_start);
		
		btn_erase = new JButton("지우기");
		btn_erase.setBackground(bgcolor);
		btn_erase.setFont(HumanRoundHeadLine);
		btn_erase.setBounds(504, 804, 99, 33);
		contentPane.add(btn_erase);
	
		btn_KeyboardPlay = new JButton("키보드연주");
		btn_KeyboardPlay.setBackground(bgcolor);
		btn_KeyboardPlay.setFont(HumanRoundHeadLine);
		btn_KeyboardPlay.setBounds(615,804,100,33);
		contentPane.add(btn_KeyboardPlay);

		btn_BankSave = new JButton("뱅크 저장");
		btn_BankSave.setBackground(Color.WHITE);
		btn_BankSave.setFont(PureGothic);
		btn_BankSave.setBounds(124, 150, 100, 25);
		contentPane.add(btn_BankSave);
		
		btn_BankListen = new JButton("뱅크 듣기");
		btn_BankListen.setBackground(Color.WHITE);
		btn_BankListen.setFont(PureGothic);
		btn_BankListen.setBounds(123, 187, 100, 25);
		contentPane.add(btn_BankListen);
		
		btn_RhythmListen = new JButton("리듬 듣기");
		btn_RhythmListen.setBackground(Color.WHITE);
		btn_RhythmListen.setFont(PureGothic);
		btn_RhythmListen.setBounds(345, 151, 120, 25);
		contentPane.add(btn_RhythmListen);
		
		btn_RhythmInsert = new JButton("리듬 입력");
		btn_RhythmInsert.setBackground(Color.WHITE);
		btn_RhythmInsert.setFont(PureGothic);
		btn_RhythmInsert.setBounds(345, 188, 120, 25);
		contentPane.add(btn_RhythmInsert);
		
		btn_ChordListen = new JButton("코드 듣기");
		btn_ChordListen.setBackground(Color.WHITE);
		btn_ChordListen.setFont(PureGothic);
		btn_ChordListen.setBounds(595, 151, 120, 25);
		contentPane.add(btn_ChordListen);
		
		btn_ChordInsert = new JButton("코드 입력");
		btn_ChordInsert.setBackground(Color.WHITE);
		btn_ChordInsert.setFont(PureGothic);
		btn_ChordInsert.setBounds(595, 188, 120, 25);
		contentPane.add(btn_ChordInsert);
		
		btn_PianoSolo = new JButton("피아노솔로");
		btn_PianoSolo.setBackground(SystemColor.window);
		btn_PianoSolo.setFont(PureGothic);
		btn_PianoSolo.setBounds(718, 537, 97, 23);
		contentPane.add(btn_PianoSolo);
		
		btn_DrumSolo = new JButton("드럼솔로");
		btn_DrumSolo.setBackground(SystemColor.window);
		btn_DrumSolo.setFont(PureGothic);
		btn_DrumSolo.setBounds(718, 600, 97, 23);
		contentPane.add(btn_DrumSolo);
		
		btn_GuitarSolo = new JButton("기타솔로");
		btn_GuitarSolo.setBackground(SystemColor.window);
		btn_GuitarSolo.setFont(PureGothic);
		btn_GuitarSolo.setBounds(718, 667, 97, 23);
		contentPane.add(btn_GuitarSolo);
		
		btn_BaseSolo = new JButton("베이스솔로");
		btn_BaseSolo.setBackground(SystemColor.window);
		btn_BaseSolo.setFont(PureGothic);
		btn_BaseSolo.setBounds(718, 730, 97, 23);
		contentPane.add(btn_BaseSolo);
		
		btn_SaveScore = new JButton("파일 저장");
		btn_SaveScore.setBackground(SystemColor.window);
		btn_SaveScore.setFont(PureGothic);
		btn_SaveScore.setBounds(718, 222, 97, 23);
		contentPane.add(btn_SaveScore);
		
		ListenButtons = new ArrayList<JButton>(8);
		ListenButtons.add(btn_BankListen);
		ListenButtons.add(btn_RhythmListen);
		ListenButtons.add(btn_ChordListen);
		ListenButtons.add(btn_start);
		ListenButtons.add(btn_PianoSolo);
		ListenButtons.add(btn_DrumSolo);
		ListenButtons.add(btn_GuitarSolo);
		ListenButtons.add(btn_BaseSolo);
	}
}

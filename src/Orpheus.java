import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
@SuppressWarnings({ "unchecked", "serial","rawtypes" })

/**
 * @brief main 함수가 실행되는 클래스. GUI 인터페이스가 정의되는 곳이다.
 */
public class Orpheus extends JFrame implements ActionListener{

	//! GUI에서 메인이 되는 Panel
	private JPanel contentPane;
	
	//! 악기, 박자, 음높이 총 3가지를 설정하는 테이블
	private JTable[] table_Field;

	//! 작업대기줄을 보여주는 테이블 차례대로 피아노, 드럼, 기타, 베이스
	private JTable[] table_Task; //
	
	//! table_Field에 스크롤을 추가하는 ScrollPane 
	private JScrollPane[] scrollPane_Field;
	
	//! table_Task에 스크롤을 추가하는 ScrollPane
	private JScrollPane[] scrollPane_Task;
	
	//! BeatSet			: 비트를 선택하는 콤보박스
	//! BankChoice		: 뱅크를 선택하는 콤보박스
	//! RhythmChoice	: 리듬을 선택하는 콤보박스
	//! RootChord		: 코드의 근음을 선택하는 콤보박스
	//! ChildChord		: 근음에서 파생되는음을 선택하는 콤보박스
	private JComboBox BeatSet, BankChoice, RhythmChoice, RootChord, ChildChord;
	
	//! BPMSet : BPM을 입력하는 텍스트필드 
	private JTextField BPMSet;
	
	//! lbl_SelectInstrument : 악기선택버튼을 안내해주는 레이블
	//! lbl_SelectBPM		 : BPMSet을 안내해주는 레이블
	//! lbl_SelectBeatSet	 : BeatSet을 안내해주는 레이블
	private JLabel lbl_SelectInstrument, lbl_SelectBPM, lbl_SelectBeatSet;

	//! 작업대기줄에서 재생되는 악기를 뮤트시키는 체크박스. 차례대로 피아노, 드럼, 기타, 베이스
	private JCheckBox[] Mute;

	//! btn_start			: 연주시작버튼. 작업대기줄에서 설정한 뱅크를 차례로 재생한다.
	//! btn_erase			: 지우기버튼. table_Field의 내용을 모두 지운다.
	//! btn_SelectToPiano	: 피아노선택버튼. table_Field을 피아노로 셋팅한다.
	//! btn_SelectToDrum	: 드럼선택버튼. table_Field을 드럼으로 셋팅한다.
	//! btn_SelectToGuitar	: 기타선택버튼. table_Field을 기타로 셋팅한다.
	//! btn_SelectToBase	: 베이스선택버튼. table_Field을 베이스로 셋팅한다.
	private JButton btn_start, btn_erase, btn_SelectToPiano, btn_SelectToDrum, btn_SelectToGuitar, btn_SelectToBase;
	
	//! btn_BankSave		: 뱅크저장버튼. table_Field[1], table_Field[2]의 내용을 뱅크에 저장한다.
	//! btn_BankListen		: 뱅크재생버튼. 콤보박스로 선택한 뱅크를 재생한다.
	//! btn_RhythmInsert	: 리듬삽입버튼. 미리 만들어져 있는 리듬을 table_Field[1], table_Field[2]에 삽입한다.
	//! btn_RhythmListen	: 리듬듣기버튼. 미리 만들어져 있는 리듬을 들어본다.
	//! btn_ChordInsert		: 코드삽입버튼. 미리 만들어져 있는 코드를 table_Field[1], table_Field[2]에 삽입한다.
	//! btn_ChordListen		: 코드듣기버튼. 미리 만들어져 있는 코드를 들어본다.
	private JButton btn_BankSave, btn_BankListen, btn_RhythmInsert, btn_RhythmListen, btn_ChordInsert, btn_ChordListen;
	
	//! btn_PianoSolo	 : 피아노솔로버튼. 피아노작업대기줄만 재생한다.
	//! btn_DrumSolo	 : 드럼솔로버튼. 드럼작업대기줄만 재생한다.
	//! btn_GuitarSolo	 : 기타솔로버튼. 기타작업대기줄만 재생한다.
	//! btn_BaseSolo	 : 베이스솔로버튼. 베이스작업대기줄만 재생한다.
	//! btn_KeyBoardPlay : 키보드연주버튼. 키보드 연주를 가능하게 한다. 
	private JButton btn_PianoSolo, btn_DrumSolo, btn_GuitarSolo, btn_BaseSolo, btn_KeyboardPlay;
	
	//! table_Field[0]을 구성하는 클래스객체. 차례대로 피아노, 드럼, 기타, 베이스 
	private SettingToKind[] STK;
	
	//! table_Field[1]을 구성하는 클래스객체. 차례대로 피아노, 드럼, 기타, 베이스
	private BeatField[] STB;
	
	//! table_Field[2]을 구성하는 클래스객체. 차례대로 피아노, 드럼, 기타, 베이스
	private SettingToField[] STF;
	
	//! table_Task를 구성하는 클래스객체. 차례대로 피아노, 드럼, 기타, 베이스
	private TaskField[] STT;
	
	//! 뱅크저장을 위한 클래스객체. 차례대로 피아노, 드럼, 기타, 베이스
	private SaveBank[] Bank;

	//! BeatSet을 구성하는 String 배열
	private String[] BeatList = {"2/2", "2/4", "3/4", "4/4", "-----", "6/8", "9/8", "12/8", "-----", "7/4", "11/4", "5/4"};

	//! RhythmChoice를 구성하는 String 배열
	private String[] RhythmList = {"1","2","3","4"};
	
	//! RootChord를 구성하는 String 배열
	private String[] RootChordList = {"C", "D", "E", "F", "G", "A", "B"};
	
	//! ChildChord를 구성하는 String 배열
	private String[] ChildChordList = {"M", "m", "7", "M7", "m7", "sus4", "dim"};
	
	//! 피아노의 음높이를 나타내는 String 배열. STF[0]의 생성자로 전달한다.
	private String[] Piano_tones = {"", "1", "2", "3"};
	
	//! 기타의 음높이를 나타내는 String 배열. STF[2], STF[3]의 생성자로 전달한다.
	private String[] Guitar_tones = {"", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
	
	//! 파일오픈클래스의 객체. 재생에 필요한 소리파일들을 오픈한다.
	private FileOpen files;
	
	//! 키보드연주를 위한 클래스의 객체
	private PlayToKeyboard keyboardPlay;
	
	//! 미리 만들어진 코드를 테이블에 삽입하기 위한 클래스의 객체
	private Input_GuitarCode Code;
	
	//!	삭제예정
	//private PlayCode CodePlay;
	
	//! 소리를 재생할 때 UI와의 스레드를 구현하기 위한 메인클래스의 객체.  
	private static Orpheus ui;
	
	//! 뱅크미리듣기를 위한 객체
	private static Play bankPlay;
	
	//! 솔로듣기를 위한 객체
	private static Play[] taskPlay;

	//! 현재 보고 있는 화면이 어떤 악기인지 구분하기 위한 변수
	private int IDX;
	
	/**
	 * @brief main 함수
	 * 뱅크듣기, 작업대기줄 솔로듣기, 연주시작과 UI의 스레드를 설정한다.
	 */
	public static void main(String[] args) {
		ui = new Orpheus();
		
		//뱅크듣기
		bankPlay = new Play(ui);
		bankPlay.MuteDisable();
		bankPlay.singleSet();
		bankPlay.setDaemon(true);
		bankPlay.ThreadStart();
		
		taskPlay = new Play[4];
		//for(int i=0; i<4; i++)
		for(int i=0; i<3; i++)	//아직 베이스가 완성전이라서 반복은 3까지
		{
			taskPlay[i] = new Play(ui);
			taskPlay[i].setDaemon(true);
			taskPlay[i].ThreadStart();
		}
	}

	/**
	 * @brief 생성자
	 * 사용할 컴포넌트를 할당하고 위치를 지정한다.
	 */
	public Orpheus() {
		setTitle("\uD504\uB85C\uC81D\uD2B8 \uC624\uB974\uD398\uC6B0\uC2A4 ver.1.0 (by. \uB514\uC624\uB2C8\uC18C\uC2A4\uB2D8\u2606)");
		setForeground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("images.png"));//icon
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 835, 819);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("/Images/about.png")))));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//폰트
		Font HumanRoundHeadLine = new Font("휴먼둥근헤드라인", Font.PLAIN, 12);
		Font PureGothic = new Font("맑은 고딕", Font.PLAIN, 12);
		Color bgcolor = new Color(255, 255, 255);
		Color fgcolor = new Color(0, 0, 0);
		lbl_SelectBeatSet = new JLabel("박자 : ");
		lbl_SelectBeatSet.setFont(PureGothic);
		lbl_SelectBeatSet.setBounds(595, 10, 57, 15);
		contentPane.add(lbl_SelectBeatSet);

		BeatSet = new JComboBox(BeatList);
		BeatSet.setFont(PureGothic);
		BeatSet.setSelectedItem("4/4");
		BeatSet.setBounds(634, 6, 55, 23);
		contentPane.add(BeatSet);		

		BankChoice = new JComboBox();
		BankChoice.setBounds(12, 85, 100, 23);
		contentPane.add(BankChoice);
		
		RhythmChoice = new JComboBox(RhythmList);
		RhythmChoice.setBounds(234, 85, 100, 23);
		contentPane.add(RhythmChoice);
		
		RootChord = new JComboBox(RootChordList);
		RootChord.setBounds(480, 85, 100, 23);
		contentPane.add(RootChord);

		ChildChord = new JComboBox(ChildChordList);
		ChildChord.setBounds(480, 122, 100, 23);
		contentPane.add(ChildChord);
	
		lbl_SelectBPM = new JLabel("BPM : ");
		lbl_SelectBPM.setFont(PureGothic);
		lbl_SelectBPM.setBounds(701, 10, 57, 15);
		contentPane.add(lbl_SelectBPM);
		
		BPMSet = new JTextField("100");
		BPMSet.setFont(PureGothic);
		BPMSet.setBounds(742, 6, 60, 23);
		contentPane.add(BPMSet);
		
		//FileOpen
		files = new FileOpen();
		keyboardPlay = new PlayToKeyboard(files);
		keyboardPlay.setVisible(false);
		Code = new Input_GuitarCode(files.getGuitarCode());
		//CodePlay = new PlayCode(files.getGuitarCode(), files.getSoundFiles(2));
		
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

		lbl_SelectInstrument = new JLabel("악기선택");
		lbl_SelectInstrument.setFont(HumanRoundHeadLine);
		lbl_SelectInstrument.setBounds(12, 12, 57, 15);
		contentPane.add(lbl_SelectInstrument);
		
		btn_SelectToPiano = new JButton("피아노");
		btn_SelectToPiano.setBackground(Color.WHITE);
		btn_SelectToPiano.setFont(PureGothic);
		btn_SelectToPiano.setForeground(Color.BLACK);
		btn_SelectToPiano.addActionListener(this);
		btn_SelectToPiano.setBounds(12, 40, 99, 25);
		contentPane.add(btn_SelectToPiano);
		
		btn_SelectToDrum = new JButton("드럼");
		btn_SelectToDrum.setBackground(Color.WHITE);
		btn_SelectToDrum.setFont(PureGothic);
		btn_SelectToDrum.addActionListener(this);
		btn_SelectToDrum.setBounds(123, 40, 99, 25);
		contentPane.add(btn_SelectToDrum);
		
		btn_SelectToGuitar = new JButton("기타");
		btn_SelectToGuitar.setBackground(Color.WHITE);
		btn_SelectToGuitar.setFont(PureGothic);
		btn_SelectToGuitar.addActionListener(this);
		btn_SelectToGuitar.setBounds(234, 40, 99, 25);
		contentPane.add(btn_SelectToGuitar);
		
		btn_SelectToBase = new JButton("베이스");
		btn_SelectToBase.setBackground(Color.WHITE);
		btn_SelectToBase.setFont(PureGothic);
		btn_SelectToBase.addActionListener(this);
		btn_SelectToBase.setBounds(345, 40, 99, 25);
		contentPane.add(btn_SelectToBase);
		
		btn_start = new JButton("연주시작");
		btn_start.setBackground(bgcolor);
		btn_start.setFont(HumanRoundHeadLine);
		btn_start.addActionListener(this);
		btn_start.setBounds(393, 737, 99, 33);
		contentPane.add(btn_start);
		
		btn_erase = new JButton("지우기");
		btn_erase.setBackground(bgcolor);
		btn_erase.setFont(HumanRoundHeadLine);
		btn_erase.addActionListener(this);
		btn_erase.setBounds(504, 737, 99, 33);
		contentPane.add(btn_erase);
	
		btn_KeyboardPlay = new JButton("키보드연주");
		btn_KeyboardPlay.setBackground(bgcolor);
		btn_KeyboardPlay.setFont(HumanRoundHeadLine);
		btn_KeyboardPlay.addActionListener(this);
		btn_KeyboardPlay.setBounds(615,737,100,33);
		contentPane.add(btn_KeyboardPlay);

		btn_BankSave = new JButton("뱅크 저장");
		btn_BankSave.setBackground(Color.WHITE);
		btn_BankSave.setFont(PureGothic);
		btn_BankSave.addActionListener(this);
		btn_BankSave.setBounds(124, 83, 100, 25);
		contentPane.add(btn_BankSave);
		
		btn_BankListen = new JButton("뱅크 듣기");
		btn_BankListen.setBackground(Color.WHITE);
		btn_BankListen.setFont(PureGothic);
		btn_BankListen.addActionListener(this);
		btn_BankListen.setBounds(123, 120, 100, 25);
		contentPane.add(btn_BankListen);
		
		btn_RhythmListen = new JButton("리듬 듣기");
		btn_RhythmListen.setBackground(Color.WHITE);
		btn_RhythmListen.setFont(PureGothic);
		btn_RhythmListen.setBounds(345, 84, 120, 25);
		contentPane.add(btn_RhythmListen);
		
		btn_RhythmInsert = new JButton("리듬 입력");
		btn_RhythmInsert.setBackground(Color.WHITE);
		btn_RhythmInsert.setFont(PureGothic);
		btn_RhythmInsert.setBounds(345, 121, 120, 25);
		contentPane.add(btn_RhythmInsert);
		
		btn_ChordListen = new JButton("코드 듣기");
		btn_ChordListen.setBackground(Color.WHITE);
		btn_ChordListen.setFont(PureGothic);
		btn_ChordListen.addActionListener(this);
		btn_ChordListen.setBounds(595, 84, 120, 25);
		contentPane.add(btn_ChordListen);
		
		btn_ChordInsert = new JButton("코드 입력");
		btn_ChordInsert.setBackground(Color.WHITE);
		btn_ChordInsert.setFont(PureGothic);
		btn_ChordInsert.addActionListener(this);
		btn_ChordInsert.setBounds(595, 121, 120, 25);
		contentPane.add(btn_ChordInsert);
		
		btn_PianoSolo = new JButton("피아노솔로");
		btn_PianoSolo.setBackground(SystemColor.window);
		btn_PianoSolo.setFont(PureGothic);
		btn_PianoSolo.addActionListener(this);
		btn_PianoSolo.setBounds(718, 470, 97, 23);
		contentPane.add(btn_PianoSolo);
		
		btn_DrumSolo = new JButton("드럼솔로");
		btn_DrumSolo.setBackground(SystemColor.window);
		btn_DrumSolo.setFont(PureGothic);
		btn_DrumSolo.addActionListener(this);
		btn_DrumSolo.setBounds(718, 533, 97, 23);
		contentPane.add(btn_DrumSolo);
		
		btn_GuitarSolo = new JButton("기타솔로");
		btn_GuitarSolo.setBackground(SystemColor.window);
		btn_GuitarSolo.setFont(PureGothic);
		btn_GuitarSolo.addActionListener(this);
		btn_GuitarSolo.setBounds(718, 600, 97, 23);
		contentPane.add(btn_GuitarSolo);
		
		btn_BaseSolo = new JButton("베이스솔로");
		btn_BaseSolo.setBackground(SystemColor.window);
		btn_BaseSolo.setFont(PureGothic);
		btn_BaseSolo.addActionListener(this);
		btn_BaseSolo.setBounds(718, 663, 97, 23);
		contentPane.add(btn_BaseSolo);

		setField(0);
		
		setVisible(true);
	}

	/**
	 * @brief 버튼액션리스너
	 * @param ActionEvent e
	 * 버튼이 눌렸을 때 해당 버튼의 이름에 따라 필요한 메소드를 호출한다.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		
		switch(source.getText())
		{
		case "피아노" :
			setField(0);
			break;
			
		case "드럼" :
			setField(1);
			break;
			
		case "기타" :
			setField(2);
			break;
			
		case "베이스" :
			setField(3);
			break;
			
		case "뱅크 저장":
			saveBank();
			break;
		
		case "뱅크 듣기":
			ListenBank();
			break;
		
		case "피아노솔로":
			ListenSolo(0);
			break;
			
		case "드럼솔로":
			ListenSolo(1);
			break;
			
		case "기타솔로":
			ListenSolo(2);
			break;
			
		case "베이스솔로":
			ListenSolo(3);
			break;
			
		case "연주시작":
			musicQ();
			break;
			
		case "지우기":
			init();
			break;
			
		case "코드 입력":
			inputCode();
			break;
			
		case "코드 듣기":
			playCode();
			break;
			
		case "키보드연주":
			keyboardPlay.setVisible(true);
			System.gc();
			break;
		}
	}
	
	/**
	 * @brief 현재 보고있는 JTable에 미리 만들어진 코드를 삽입한다.
	 */
	public void inputCode()
	{
		Code.addCode(table_Field[2], RootChord.getSelectedIndex(), ChildChord.getSelectedIndex());
		STF[2].addColumn(table_Field[2].getModel().getColumnCount()-1);
		STF[2].setCellOption(table_Field[2]);
	}
	
	
	/**
	 * @brief table_Field를 악기에 따라 다르게 세팅한다.
	 * @param int idx : 0번부터 3번까지 차례대로 피아노, 드럼, 기타, 베이스
	 */
	public void setField(int idx)
	{
		table_Field[0].setModel(STK[idx].getModel());
		table_Field[1].setModel(STB[idx].getModel());
		table_Field[2].setModel(STF[idx].getModel());
		
		table_Field[0].getColumnModel().getColumn(0).setPreferredWidth(60);
		STB[idx].setCellOption(table_Field[1]);
		STF[idx].setCellOption(table_Field[2]);
		
		setBankList(STT[idx].getItemList().length-1);
		
		switch(idx)
		{
		case 0:
			RootChord.setEnabled(false);
			ChildChord.setEnabled(false);
			
			RhythmChoice.setEnabled(false);
			btn_RhythmInsert.setEnabled(false);
			btn_RhythmListen.setEnabled(false);
			
			btn_ChordInsert.setEnabled(false);
			btn_ChordListen.setEnabled(false);
		break;

		case 2:
			RootChord.setEnabled(true);
			ChildChord.setEnabled(true);
			
			RhythmChoice.setEnabled(false);
			btn_RhythmInsert.setEnabled(false);
			btn_RhythmListen.setEnabled(false);
			
			btn_ChordInsert.setEnabled(true);
			btn_ChordListen.setEnabled(true);
		break;
	
		case 1:
		case 3:
			RootChord.setEnabled(false);
			ChildChord.setEnabled(false);
			
			RhythmChoice.setEnabled(true);
			btn_RhythmInsert.setEnabled(true);
			btn_RhythmListen.setEnabled(true);
			
			btn_ChordInsert.setEnabled(false);
			btn_ChordListen.setEnabled(false);
		}
		
		IDX = idx;
	}
	
	/**
	 * @brief 현재 보고 있는 table_Field[1], table_Field[2]를 지우고 초기상태로 되돌린다.
	 */
	public void init()
	{
		STB[IDX].Init();
		STF[IDX].Init();
		
		STB[IDX].setCellOption(table_Field[1]);
		STF[IDX].setCellOption(table_Field[2]);
	}
	
	/**
	 * @brief 뱅크가 추가되면 BankChoice에서 선택할 수 있도록 추가한다.
	 * @param int size : BankChoice의 크기
	 */
	public void setBankList(int size)
	{
		BankChoice.removeAllItems();
		BankChoice.addItem("");
		for(int i=1; i<=size; i++)
			BankChoice.addItem(Integer.toString(i));
	}
	
	/**
	 * @brief 뱅크를 만들 때 박자규칙을 어겼는지 확인하는 메소드
	 * @param BeatField BeatField : 박자정보를 가지고 있는 클래스의 객체
	 * @return
	 */
	public boolean OutofBeat(BeatField BeatField)
	{
		if(BeatField.out_max(table_Field[1]) < RestTimeSetup.music_score)
		{
			JOptionPane.showMessageDialog(null, "설정한 박자가 부족합니다.");
			return false;
		}
		if(BeatField.out_max(table_Field[1]) > RestTimeSetup.music_score)
		{
			JOptionPane.showMessageDialog(null, "설정한 박자가 초과하였습니다.");
			return false;
		}
		return true;
	}
	
	/**
	 * @brief table_Field[1], table_Field[2]의 내용을 뱅크에 저장한다.
	 */
	public void saveBank()
	{
		RestTimeSetup.getRestTime(BPMSet.getText(), (String)BeatSet.getSelectedItem());
		if(!OutofBeat(STB[IDX]))
			return;
		
		STF[IDX].BankList.add(Bank[IDX].getBank(STB[IDX].getBeatResult(), RestTimeSetup.result));
		int num = STF[IDX].BankList.size()-1;
		STT[IDX].reflash(num);
		setBankList(num);
		JOptionPane.showMessageDialog(null, num+"번 뱅크 저장완료.");
	}
	
	/**
	 * @brief BankChoice를 통해 선택된 뱅크를 재생한다.
	 * UI와 스레드로 동작한다.
	 */
	public void ListenBank()
	{
		int BankNum = BankChoice.getSelectedIndex();
		bankPlay.setBank(STF[IDX].BankList.get(BankNum), files.getSoundFiles(IDX));
		bankPlay.action();
	}

	/**
	 * @brief RootChord, ChildChord 를 통해 선택된 코드를 재생한다.
	 */
	public void playCode()
	{
		//CodePlay.playCode(RootChord.getSelectedIndex(), ChildChord.getSelectedIndex());
		AudioInputStream sound;
		Clip clip;
		String code = files.getGuitarCode()[RootChord.getSelectedIndex()][ChildChord.getSelectedIndex()];
		char ch;
		try{
			for(int i=0; i<6; i++)
			{
				ch = code.charAt(i);
				if(ch!='x')
				{
					sound = AudioSystem.getAudioInputStream(files.getSoundFiles(2)[i][(int)ch-48]);
					clip = AudioSystem.getClip();
					clip.open(sound);
					clip.start();
				}
				
			}
		}
		catch(Exception e) {}
		
	}
	/**
	 * @brief 작업대기줄에서 특정 악기대기줄만 재생한다.
	 * UI와 스레드로 동작한다.
	 * @param int idx : 0번부터 3번까지 차례대로 피아노, 드럼, 기타, 베이스
	 */
	public void ListenSolo(int idx)
	{
		taskPlay[idx].multySet(table_Task[idx], STF[idx].BankList, files.getSoundFiles(idx), Mute[idx]);
		taskPlay[idx].action();
	}
	
	/**
	 * @brief 작업대기줄에 있는 모든 내용을 재생한다.
	 * taskPlay[0], taskPlay[1], taskPlay[2], taskPlay[3], UI가 스레드로 동작한다.
	 */
	public void musicQ()
	{
		//연주시작 메소드
		//for(int i=0; i<4; i++)
		for(int i=0; i<3; i++)
		{
			taskPlay[i].multySet(table_Task[i], STF[i].BankList, files.getSoundFiles(i), Mute[i]);
			taskPlay[i].action();
		}
	}
	
	/**
	 * @brief 뱅크듣기 버튼을 리턴한다.
	 * 솔로듣기, 연주시작을 눌렀을 때 중복클릭을 방지하기 위해 해당 클래스에서 btn_BankListen을 Enable(false)시킨다. 
	 * @return btn_BankListen
	 */
	public JButton getBankListenButton()
	{
		return btn_BankListen;
	}
}

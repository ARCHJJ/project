import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

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
	
	//! BeatSet			: 비트를 선택하는 콤보박스
	//! BankChoice		: 뱅크를 선택하는 콤보박스
	//! RhythmChoice	: 리듬을 선택하는 콤보박스
	//! RootChord		: 코드의 근음을 선택하는 콤보박스
	//! ChildChord		: 근음에서 파생되는음을 선택하는 콤보박스
	protected JComboBox BeatSet, BankChoice, RhythmChoice, RootChord, ChildChord;
	
	//! BPMSet : BPM을 입력하는 텍스트필드 
	protected JTextField BPMSet;
	
	//! lbl_SelectInstrument : 악기선택버튼을 안내해주는 레이블
	//! lbl_SelectBPM		 : BPMSet을 안내해주는 레이블
	//! lbl_SelectBeatSet	 : BeatSet을 안내해주는 레이블
	protected JLabel lbl_SelectInstrument, lbl_SelectBPM, lbl_SelectBeatSet;

	//! 작업대기줄에서 재생되는 악기를 뮤트시키는 체크박스. 차례대로 피아노, 드럼, 기타, 베이스
	protected JCheckBox[] Mute;

	//! btn_start			: 연주시작버튼. 작업대기줄에서 설정한 뱅크를 차례로 재생한다.
	//! btn_erase			: 지우기버튼. table_Field의 내용을 모두 지운다.
	//! btn_SelectToPiano	: 피아노선택버튼. table_Field을 피아노로 셋팅한다.
	//! btn_SelectToDrum	: 드럼선택버튼. table_Field을 드럼으로 셋팅한다.
	//! btn_SelectToGuitar	: 기타선택버튼. table_Field을 기타로 셋팅한다.
	//! btn_SelectToBase	: 베이스선택버튼. table_Field을 베이스로 셋팅한다.
	protected JButton btn_start, btn_erase, btn_SelectToPiano, btn_SelectToDrum, btn_SelectToGuitar, btn_SelectToBase;
	
	//! btn_BankSave		: 뱅크저장버튼. table_Field[1], table_Field[2]의 내용을 뱅크에 저장한다.
	//! btn_BankListen		: 뱅크재생버튼. 콤보박스로 선택한 뱅크를 재생한다.
	//! btn_RhythmInsert	: 리듬삽입버튼. 미리 만들어져 있는 리듬을 table_Field[1], table_Field[2]에 삽입한다.
	//! btn_RhythmListen	: 리듬듣기버튼. 미리 만들어져 있는 리듬을 들어본다.
	//! btn_ChordInsert		: 코드삽입버튼. 미리 만들어져 있는 코드를 table_Field[1], table_Field[2]에 삽입한다.
	//! btn_ChordListen		: 코드듣기버튼. 미리 만들어져 있는 코드를 들어본다.
	protected JButton btn_BankSave, btn_BankListen, btn_RhythmInsert, btn_RhythmListen, btn_ChordInsert, btn_ChordListen;
	
	//! btn_PianoSolo	 : 피아노솔로버튼. 피아노작업대기줄만 재생한다.
	//! btn_DrumSolo	 : 드럼솔로버튼. 드럼작업대기줄만 재생한다.
	//! btn_GuitarSolo	 : 기타솔로버튼. 기타작업대기줄만 재생한다.
	//! btn_BaseSolo	 : 베이스솔로버튼. 베이스작업대기줄만 재생한다.
	//! btn_KeyBoardPlay : 키보드연주버튼. 키보드 연주를 가능하게 한다. 
	protected JButton btn_PianoSolo, btn_DrumSolo, btn_GuitarSolo, btn_BaseSolo, btn_KeyboardPlay;
	
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
	
	//! 소리를 재생할 때 UI와의 스레드를 구현하기 위한 메인클래스의 객체.  
	protected Orpheus ui;
	
	//! 뱅크미리듣기를 위한 객체
	protected Play bankPlay;
	
	//! 솔로듣기를 위한 객체
	protected Play[] taskPlay;
	
	//! 메트로놈을 위한 객체
	protected Metronome metronome;

	//! 현재 보고 있는 화면이 어떤 악기인지 구분하기 위한 변수
	protected int IDX;
}

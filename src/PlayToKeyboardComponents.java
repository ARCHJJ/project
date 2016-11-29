import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

abstract class PlayToKeyboardComponents {

	//! GUI에서 메인이 되는 Freme
	protected JFrame mainFrame;
	
	//! GUI에서 메인이 되는 Panel
	protected JPanel contentPane;
	
	//! table_RootChord  : 근음을 안내해주는 테이블
	//! table_ChildChord : 파생음을 안내해주는 테이블
	protected JTable table_RootChord, table_ChildChord;
	
	//! tablemodel_RootChord	: table_RootChord의 모델
	//! tablemodel_ChildChord	: table_ChildChord의 모델
	protected DefaultTableModel tablemodel_RootChord, tablemodel_ChildChord;
	
	//! centerSort	: 가운데 정렬을 위한 셀렌더러
	protected DefaultTableCellRenderer centerSort;
	
	//! scrollPane_RootChord	: table_RootChord에 스크롤을 추가하는 ScrollPane
	//! scrollPane_ChildChord	: table_ChildChord에 스크롤을 추가하는 ScrollPane
	protected JScrollPane scrollPane_RootChord, scrollPane_ChildChord;
	
	//! btn_SelectToPiano	: 피아노선택버튼. table_Field을 피아노로 셋팅한다.
	//! btn_SelectToDrum	: 드럼선택버튼. table_Field을 드럼으로 셋팅한다.
	//! btn_SelectToGuitar	: 기타선택버튼. table_Field을 기타로 셋팅한다.
	//! btn_SelectToBase	: 베이스선택버튼. table_Field을 베이스로 셋팅한다.
	protected JButton btn_SelectToPiano, btn_SelectToDrum, btn_SelectToGuitar, btn_SelectToBase;
	
	//! lbl_showOctave	: table_RootChord 을 안내해주는 레이블
	//! lbl_showTone	: table_ChildChord 을 안내해주는 레이블
	protected JLabel lbl_showOctave, lbl_showTone;

	//! RootField	: tablemodel_RootChord의 구성이 되는 Object[][]의 집합
	//! ChildField	: tablemodel_ChildChord의 구성이 되는 Object[][]의 집합
	protected Object[][][] RootField, ChildField;
	
	//! RootHeader	: tablemodel_RootChord의 헤더가 되는 String[]의 집합
	//! ChildHeader	: tablemodel_ChildChord의 헤더가 되는 String[]의 집합
	protected String[][] RootHeader, ChildHeader;	
	
	//! 파일오픈클래스의 객체. 재생에 필요한 소리파일들을 오픈한다.
	protected FileOpen files;

	//! 소리파일을 재생하기 위한 객체
	protected Clip clip;
	
	//! IDX			: 현재 보고 있는 화면이 어떤 악기인지 구분하기 위한 변수
	//! pianoRoot	: 현재 피아노의 옥타브
	//! guitarRoot	: 현재 기타의 근음
	protected int IDX, pianoRoot = 0, guitarRoot = 0;
	
	//! pianoOctave : 피아노의 옥타브 이름을 가지고 있는 String[]
	protected String[] pianoOctave = {"4옥타브", "5옥타브", "6옥타브"};
	
	//! pianoOctave : 기타의 근음 이름을 가지고 있는 String[]
	protected String[] guitarOctave= {"C","D","E","F","G","A","B"};
	
}

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

/**
 * @brief 사용자가 컴퓨터 키보드를 눌렀을 때 맵핑된 음을 재생시키는 역할을 하는 클래스이다. 
 */
class PlayToKeyboard extends JFrame implements ActionListener, KeyListener{

	//! GUI에서 메인이 되는 Panel
	private JPanel contentPane;
	
	//! 근음과 파생음을 안내해주는 테이블
	private JTable table_RootChord, table_ChildChord;
	
	//! tablemodel_RootChord	: table_RootChord의 모델
	//! tablemodel_ChildChord	: table_ChildChord의 모델
	private DefaultTableModel tablemodel_RootChord, tablemodel_ChildChord;
	
	//! centerSort	: 가운데 정렬을 위한 셀렌더러
	private DefaultTableCellRenderer centerSort;
	
	//! table_RootChord, table_ChildChord에 스크롤을 추가하는 ScrollPane
	private JScrollPane scrollPane_RootChord, scrollPane_ChildChord;
	
	//! btn_SelectToPiano	: 피아노선택버튼. table_Field을 피아노로 셋팅한다.
	//! btn_SelectToDrum	: 드럼선택버튼. table_Field을 드럼으로 셋팅한다.
	//! btn_SelectToGuitar	: 기타선택버튼. table_Field을 기타로 셋팅한다.
	//! btn_SelectToBase	: 베이스선택버튼. table_Field을 베이스로 셋팅한다.
	private JButton btn_SelectToPiano, btn_SelectToDrum, btn_SelectToGuitar, btn_SelectToBase;
	
	//! lbl_showOctave	: table_RootChord 을 안내해주는 레이블
	//! lbl_showTone	: table_ChildChord 을 안내해주는 레이블
	private JLabel lbl_showOctave, lbl_showTone;

	//! RootField	: tablemodel_RootChord의 구성이 되는 Object[][]의 집합
	//! ChildField	: tablemodel_ChildChord의 구성이 되는 Object[][]의 집합
	private Object[][][] RootField, ChildField;
	
	//! RootHeader	: tablemodel_RootChord의 헤더가 되는 String[]의 집합
	//! ChildHeader	: tablemodel_ChildChord의 헤더가 되는 String[]의 집합
	private String[][] RootHeader, ChildHeader;	
	
	//! 파일오픈클래스의 객체. 재생에 필요한 소리파일들을 오픈한다.
	private FileOpen files;
	
	//! 소리파일을 입력받기 위한 객체
	private AudioInputStream sound;
	
	//! 소리파일을 재생하기 위한 객체
	private Clip clip;
	
	//! 키보드입력이 발생했을 때 어떤 키가 입력되었는지 저장하는 변수  
	private char keyPress;
	
	//! IDX			: 현재 보고 있는 화면이 어떤 악기인지 구분하기 위한 변수
	//! pianoRoot	: 현재 피아노의 옥타브
	//! guitarRoot	: 현재 기타의 근음
	private int IDX, pianoRoot = 0, guitarRoot = 0;
	
	//! pianoOctave : 피아노의 옥타브 이름을 가지고 있는 String[]
	private String[] pianoOctave = {"4옥타브", "5옥타브", "6옥타브"};
	
	//! pianoOctave : 기타의 근음 이름을 가지고 있는 String[]
	private String[] guitarOctave= {"C","D","E","F","G","A","B"};
	
	/**
	 * @brief 생성자. 초기상태를 설정해준다.
	 * @param FileOpen files :  파일오픈클래스의 객체. 소리재생에 필요한 데이터를 가지고 있다.
	 */
	PlayToKeyboard(FileOpen files)
	{
		this.files = files;
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setBounds(935, 20, 462, 200);
		addKeyListener(this);
		setFocusable(true);
		MakeModel();
		
		table_RootChord = new JTable();
		table_RootChord.setEnabled(false);
		scrollPane_RootChord = new JScrollPane(table_RootChord);
		scrollPane_RootChord.setBounds(12, 83, 432, 41);
		contentPane.add(scrollPane_RootChord);
		
		table_ChildChord = new JTable();
		table_ChildChord.setEnabled(false);
		scrollPane_ChildChord = new JScrollPane(table_ChildChord);
		scrollPane_ChildChord.setBounds(12, 35, 432, 41);
		contentPane.add(scrollPane_ChildChord);
		
		btn_SelectToPiano = new JButton("피아노");
		btn_SelectToPiano.addActionListener(this);
		btn_SelectToPiano.setBounds(12, 5, 99, 25);
		contentPane.add(btn_SelectToPiano);
		
		btn_SelectToDrum = new JButton("드럼");
		btn_SelectToDrum.addActionListener(this);
		btn_SelectToDrum.setBounds(123, 5, 99, 25);
		contentPane.add(btn_SelectToDrum);
		
		btn_SelectToGuitar = new JButton("기타");
		btn_SelectToGuitar.addActionListener(this);
		btn_SelectToGuitar.setBounds(234, 5, 99, 25);
		contentPane.add(btn_SelectToGuitar);
		
		btn_SelectToBase = new JButton("베이스");
		//btn_SelectToBase.addActionListener(this);
		btn_SelectToBase.setBounds(345, 5, 99, 25);
		contentPane.add(btn_SelectToBase);
				
		lbl_showOctave = new JLabel("");
		lbl_showOctave.setBounds(12, 134, 57, 15);
		contentPane.add(lbl_showOctave);
		
		lbl_showTone = new JLabel("");
		lbl_showTone.setBounds(123, 134, 57, 15);
		contentPane.add(lbl_showTone);
		
		setModel(0);
	}
	/**
	 * @brief table_RootChord, table_ChildChord 구성에 필요한 객체를 할당한다.
	 */
	public void MakeModel()
	{
		tablemodel_RootChord = new DefaultTableModel();
		tablemodel_ChildChord = new DefaultTableModel();
		centerSort = new DefaultTableCellRenderer();
		centerSort.setHorizontalAlignment(SwingConstants.CENTER);
		RootHeader = new String[][]
				{ {"입력", "1", "2", "3"}
				 ,{"입력"}
				 ,{"입력", "1", "2", "3", "4", "5", "6" ,"7"}
				 ,{"입력"}
				};
		
		RootField = new Object[4][][];
		RootField[0] = new Object[][]{{"근음", pianoOctave[0], pianoOctave[1], pianoOctave[2]}};
		RootField[1] = new Object[][]{{"근음"}};
		RootField[2] = new Object[][]{{"근음", guitarOctave[0], guitarOctave[1], guitarOctave[2], guitarOctave[3], guitarOctave[4], guitarOctave[5], guitarOctave[6]}};
		RootField[3] = new Object[][]{{"근음"}};
		
		ChildHeader = new String[][]
				{ {"입력", "A", "W", "S", "E", "D", "F", "T", "H", "U", "J", "I", "K", "L"}
				 ,{"입력", "A", "S", "D", "F", "H", "J", "K"}
				 ,{"입력", "A", "S", "D", "F", "H", "J", "K"}
				 ,{"입력"}
				};
		
		
		ChildField = new Object[4][][];
		ChildField[0] = new Object[][]{{"연주", "도", "도#", "레", "레#", "미", "파", "파#", "솔", "솔#", "라", "라#", "시", "도"}};
		ChildField[1] = new Object[][]{{"연주", "베이스", "하이헷", "스네어", "라이드", "크래시", "스몰탐", "하이탐"}};
		ChildField[2] = new Object[][]{{"연주", "M", "m", "7", "M7", "m7", "sus4", "dim"}};
		ChildField[3] = new Object[][]{{"연주"}};		
	}
	/**
	 * @brief 버튼에 따라  table_RootChord, table_ChildChord를 다르게 세팅한다.
	 * @param int idx : 0번부터 3번까지 차례대로 피아노, 드럼, 기타, 베이스
	 */
	
	public void setModel(int idx)
	{
		tablemodel_RootChord.setDataVector(RootField[idx], RootHeader[idx]);
		tablemodel_ChildChord.setDataVector(ChildField[idx], ChildHeader[idx]);
		table_RootChord.setModel(tablemodel_RootChord);
		table_ChildChord.setModel(tablemodel_ChildChord);
		
		for(int i=0; i<tablemodel_RootChord.getColumnCount(); i++)
			table_RootChord.getColumnModel().getColumn(i).setCellRenderer(centerSort);
		for(int i=0; i<tablemodel_ChildChord.getColumnCount(); i++)
			table_ChildChord.getColumnModel().getColumn(i).setCellRenderer(centerSort);
		
		IDX = idx;
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
		case "피아노":
			setModel(0);
			scrollPane_RootChord.setVisible(true);
			requestFocus();
			break;
		case "드럼":
			setModel(1);
			scrollPane_RootChord.setVisible(false);
			lbl_showOctave.setText("");
			requestFocus();
			break;
		case "기타":
			setModel(2);
			scrollPane_RootChord.setVisible(true);
			requestFocus();
			break;
		case "베이스":
			setModel(3);
			scrollPane_RootChord.setVisible(false);
			requestFocus();
			break;
		}
	}
	
	/**
	 * @brief 키보드를 눌렀을 때 피아노 소리가 재생되도록 세팅한다.
	 */
	public void Piano()
	{
		int idx = 0;
		try
		{
			switch(keyPress)
			{
			case '1':
				pianoRoot = 0;
				return;
			case '2':
				pianoRoot = 1;
				return;
			case '3':
				pianoRoot = 2;
				return;
				
			case 'a':
				sound = AudioSystem.getAudioInputStream(files.getSoundFiles(0)[0][pianoRoot]);
				lbl_showTone.setText("도");
				break;
			case 'w':
				sound = AudioSystem.getAudioInputStream(files.getSoundFiles(0)[1][pianoRoot]);
				lbl_showTone.setText("도#");
				break;
			case 's':
				sound = AudioSystem.getAudioInputStream(files.getSoundFiles(0)[2][pianoRoot]);
				lbl_showTone.setText("레");
				break;
			case 'e':
				sound = AudioSystem.getAudioInputStream(files.getSoundFiles(0)[3][pianoRoot]);
				lbl_showTone.setText("레#");
				break;
			case 'd':
				sound = AudioSystem.getAudioInputStream(files.getSoundFiles(0)[4][pianoRoot]);
				lbl_showTone.setText("미");
				break;
			case 'f':
				sound = AudioSystem.getAudioInputStream(files.getSoundFiles(0)[5][pianoRoot]);
				lbl_showTone.setText("파");
				break;
			case 't':
				sound = AudioSystem.getAudioInputStream(files.getSoundFiles(0)[6][pianoRoot]);
				lbl_showTone.setText("파#");
				break;
			case 'h':
				sound = AudioSystem.getAudioInputStream(files.getSoundFiles(0)[7][pianoRoot]);
				lbl_showTone.setText("솔");
				break;
			case 'u':
				sound = AudioSystem.getAudioInputStream(files.getSoundFiles(0)[8][pianoRoot]);
				lbl_showTone.setText("솔#");
				break;
			case 'j':
				sound = AudioSystem.getAudioInputStream(files.getSoundFiles(0)[9][pianoRoot]);
				lbl_showTone.setText("라");
				break;
			case 'i':
				sound = AudioSystem.getAudioInputStream(files.getSoundFiles(0)[10][pianoRoot]);
				lbl_showTone.setText("라#");
				break;
			case 'k':
				sound = AudioSystem.getAudioInputStream(files.getSoundFiles(0)[11][pianoRoot]);
				lbl_showTone.setText("시");
				break;
			case 'l':
				sound = AudioSystem.getAudioInputStream(files.getSoundFiles(0)[0][(pianoRoot+1)%3]);
				lbl_showTone.setText("도");
			}
			clip = AudioSystem.getClip();
			clip.open(sound);
			clip.start();
		}
		catch(Exception e) {}	
	}
	/**
	 * @brief 키보드를 눌렀을 때 드럼 소리가 재생되도록 세팅한다.
	 */
	public void Drum()
	{
		int idx = -1;
		try
		{
			switch(keyPress)
			{
			case 'a':
				idx = 0;
				lbl_showTone.setText("베이스");
				break;
			case 's':
				idx = 1;
				lbl_showTone.setText("하이헷");
				break;
			case 'd':
				idx = 2;
				lbl_showTone.setText("스네어");
				break;
			case 'f':
				idx = 3;
				lbl_showTone.setText("라이드");
				break;
			case 'h':
				idx = 4;
				lbl_showTone.setText("크래시");
				break;
			case 'j':
				idx = 5;
				lbl_showTone.setText("스몰탐");
				break;
			case 'k':
				idx = 6;
				lbl_showTone.setText("하이탐");
				break;
			default:
				return;
			}
			sound = AudioSystem.getAudioInputStream(files.getSoundFiles(1)[0][idx]);
			clip = AudioSystem.getClip();
			clip.open(sound);
			clip.start();
		}
		catch(Exception e) {}
		
	}
	/**
	 * @brief 키보드를 눌렀을 때 기타 소리가 재생되도록 세팅한다.
	 */
	public void Guitar()
	{
		String tones;		
		try
		{
			switch(keyPress)
			{	
			case '1':
				guitarRoot = 0;
				return;
			case '2':
				guitarRoot = 1;
				return;
			case '3':
				guitarRoot = 2;
				return;
			case '4':
				guitarRoot = 3;
				return;
			case '5':
				guitarRoot = 4;
				return;
			case '6':
				guitarRoot = 5;
				return;
			case '7':
				guitarRoot = 6;
				return;
				
			case 'a':
				tones = files.getGuitarCode()[guitarRoot][0];
				lbl_showTone.setText("M");
				break;
			case 's':
				tones = files.getGuitarCode()[guitarRoot][1];
				lbl_showTone.setText("m");
				break;
			case 'd':
				tones = files.getGuitarCode()[guitarRoot][2];
				lbl_showTone.setText("7");
				break;
			case 'f':
				tones = files.getGuitarCode()[guitarRoot][3];
				lbl_showTone.setText("M7");
				break;
			case 'h':
				tones = files.getGuitarCode()[guitarRoot][4];
				lbl_showTone.setText("m7");
				break;
			case 'j':
				tones = files.getGuitarCode()[guitarRoot][5];
				lbl_showTone.setText("sus4");
				break;
			case 'k':
				tones = files.getGuitarCode()[guitarRoot][6];
				lbl_showTone.setText("dim");
				break;
			default:
				return;
			}
			
			char ch;
			for(int i=0; i<6; i++)
			{
				ch = tones.charAt(i);
				if(ch!='x')
				{
					sound = AudioSystem.getAudioInputStream(files.getSoundFiles(2)[i][(int)ch-48]);
					clip = AudioSystem.getClip();
					clip.open(sound);
					clip.start();
				}
			}
		}
		catch(Exception e){}
	}
	/**
	 * @brief 키보드액션리스너
	 * 키보드가 눌렸을 때 데이터를 가져와서 처리한다.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		keyPress = e.getKeyChar();
		switch(IDX)
		{
		case 0:
			Piano();
			lbl_showOctave.setText(pianoOctave[pianoRoot]);
			break;
		case 1:
			Drum();
			break;
		case 2:
			Guitar();
			lbl_showOctave.setText(guitarOctave[guitarRoot]);
			break;
		}
	}
	
	/**
	 * @brief 키보드를 땔 때 데이터를 가져와서 처리한다.
	 * PlayToKeyboard 클래스에서는 사용하지 않는다.
	 */
	@Override
	public void keyReleased(KeyEvent e) {}
	
	/**
	 * @brief 키보드를 땔 때 데이터를 가져와서 처리한다.
	 * PlayToKeyboard 클래스에서는 사용하지 않는다.
	 */
	@Override
	public void keyTyped(KeyEvent e) {}
}

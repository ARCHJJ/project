import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * @brief 사용자가 컴퓨터 키보드를 눌렀을 때 맵핑된 음을 재생시키는 역할을 하는 클래스이다. 
 */
class PlayToKeyboard extends PlayToKeyboardComponents implements ActionListener, KeyListener{

	//! 키보드입력이 발생했을 때 어떤 키가 입력되었는지 저장하는 변수  
	private char keyPress;
	
	/**
	 * @brief 생성자. 초기상태를 설정해준다.
	 * @param FileOpen files :  파일오픈클래스의 객체. 소리재생에 필요한 데이터를 가지고 있다.
	 */
	PlayToKeyboard(FileOpen files)
	{
		this.files = files;
		mainFrame.addKeyListener(this);
		mainFrame.setFocusable(true);
		
		btn_SelectToPiano.addActionListener(this);
		btn_SelectToDrum.addActionListener(this);
		btn_SelectToGuitar.addActionListener(this);
		btn_SelectToBase.addActionListener(this);
		
		MakeModel();
		setModel(0);
	}
	/**
	 * @brief 메인프레임의 Visible을 설정하는 메소드
	 */
	public void setVisible(boolean bulb)
	{
		mainFrame.setVisible(bulb);
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
				 ,{"입력", "A", "S", "D", "F", "H", "J", "K"}
				};
		
		
		ChildField = new Object[4][][];
		ChildField[0] = new Object[][]{{"연주", "도", "도#", "레", "레#", "미", "파", "파#", "솔", "솔#", "라", "라#", "시", "도"}};
		ChildField[1] = new Object[][]{{"연주", "베이스", "하이헷", "스네어", "라이드", "크래시", "스몰탐", "하이탐"}};
		ChildField[2] = new Object[][]{{"연주", "M", "m", "7", "M7", "m7", "sus4", "dim"}};
		ChildField[3] = new Object[][]{{"연주", "C", "D", "E", "F", "G", "A", "B"}};		
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
			mainFrame.requestFocus();
			break;
		case "드럼":
			setModel(1);
			scrollPane_RootChord.setVisible(false);
			lbl_showOctave.setText("");
			mainFrame.requestFocus();
			break;
		case "기타":
			setModel(2);
			scrollPane_RootChord.setVisible(true);
			mainFrame.requestFocus();
			break;
		case "베이스":
			setModel(3);
			scrollPane_RootChord.setVisible(false);
			mainFrame.requestFocus();
			break;
		}
	}
	/**
	 * @brief 키보드를 눌렀을 때 피아노 소리가 재생되도록 세팅한다.
	 */
	public void Piano()
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
			clip = files.getSoundClips(0)[0][pianoRoot];
			lbl_showTone.setText("도");
			break;
		case 'w':
			clip = files.getSoundClips(0)[1][pianoRoot];
			lbl_showTone.setText("도#");
			break;
		case 's':
			clip = files.getSoundClips(0)[2][pianoRoot];
			lbl_showTone.setText("레");
			break;
		case 'e':
			clip = files.getSoundClips(0)[3][pianoRoot];
			lbl_showTone.setText("레#");
			break;
		case 'd':
			clip = files.getSoundClips(0)[4][pianoRoot];
			lbl_showTone.setText("미");
			break;
		case 'f':
			clip = files.getSoundClips(0)[5][pianoRoot];
			lbl_showTone.setText("파");
			break;
		case 't':
			clip = files.getSoundClips(0)[6][pianoRoot];
			lbl_showTone.setText("파#");
			break;
		case 'h':
			clip = files.getSoundClips(0)[7][pianoRoot];
			lbl_showTone.setText("솔");
			break;
		case 'u':
			clip = files.getSoundClips(0)[8][pianoRoot];
			lbl_showTone.setText("솔#");
			break;
		case 'j':
			clip = files.getSoundClips(0)[9][pianoRoot];
			lbl_showTone.setText("라");
			break;
		case 'i':
			clip = files.getSoundClips(0)[10][pianoRoot];
			lbl_showTone.setText("라#");
			break;
		case 'k':
			clip = files.getSoundClips(0)[11][pianoRoot];
			lbl_showTone.setText("시");
			break;
		case 'l':
			clip = files.getSoundClips(0)[0][(pianoRoot+1)%3];
			lbl_showTone.setText("도");
			break;
			
		default:
			return;
		}
		clip.setFramePosition(0);
		clip.start();
	}
	/**
	 * @brief 키보드를 눌렀을 때 드럼 소리가 재생되도록 세팅한다.
	 */
	public void Drum()
	{
		int idx = -1;
	
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
		clip = files.getSoundClips(1)[0][idx];
		clip.setFramePosition(0);
		clip.start();
	
	}
	/**
	 * @brief 키보드를 눌렀을 때 기타 소리가 재생되도록 세팅한다.
	 */
	public void Guitar()
	{
		String tones;		
	
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
			tones = files.getCode(2)[guitarRoot][0];
			lbl_showTone.setText("M");
			break;
		case 's':
			tones = files.getCode(2)[guitarRoot][1];
			lbl_showTone.setText("m");
			break;
		case 'd':
			tones = files.getCode(2)[guitarRoot][2];
			lbl_showTone.setText("7");
			break;
		case 'f':
			tones = files.getCode(2)[guitarRoot][3];
			lbl_showTone.setText("M7");
			break;
		case 'h':
			tones = files.getCode(2)[guitarRoot][4];
			lbl_showTone.setText("m7");
			break;
		case 'j':
			tones = files.getCode(2)[guitarRoot][5];
			lbl_showTone.setText("sus4");
			break;
		case 'k':
			tones = files.getCode(2)[guitarRoot][6];
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
				clip = files.getSoundClips(2)[i][(int)ch-48];
				clip.setFramePosition(0);
				clip.start();
			}
		}
	}
	
	public void Bass()
	{
		String tones;		
	
		switch(keyPress)
		{	
		case 'a':
			tones = files.getCode(3)[0][0];
			lbl_showTone.setText("C");
			break;
		case 's':
			tones = files.getCode(3)[1][0];
			lbl_showTone.setText("D");
			break;
		case 'd':
			tones = files.getCode(3)[2][0];
			lbl_showTone.setText("E");
			break;
		case 'f':
			tones = files.getCode(3)[3][0];
			lbl_showTone.setText("F");
			break;
		case 'h':
			tones = files.getCode(3)[4][0];
			lbl_showTone.setText("G");
			break;
		case 'j':
			tones = files.getCode(3)[5][0];
			lbl_showTone.setText("A");
			break;
		case 'k':
			tones = files.getCode(3)[6][0];
			lbl_showTone.setText("B");
			break;
		default:
			return;
		}
		
		char ch;
		for(int i=0; i<4; i++)
		{
			ch = tones.charAt(i);
			if(ch!='x')
			{
				clip = files.getSoundClips(3)[i][(int)ch-48];
				clip.setFramePosition(0);
				clip.start();
			}
		}
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
		case 3:
			Bass();
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

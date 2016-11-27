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

//import javax.swing.JTable;

class PlayToKeyboard extends JFrame implements ActionListener, KeyListener{

	private JPanel contentPane;
	private JTable table_RootChord, table_ChildChord;
	private JScrollPane scrollPane_RootChord, scrollPane_ChildChord;
	private JButton btn_SelectToPiano, btn_SelectToDrum, btn_SelectToGuitar, btn_SelectToBase;
	private JLabel lbl_showOctave, lbl_showTone;
	private DefaultTableModel tablemodel_RootChord, tablemodel_ChildChord;
	private DefaultTableCellRenderer centerSort;
	private Object[][][] RootField, ChildField;
	private String[][] RootHeader, ChildHeader;	
	private FileOpen files;
	private AudioInputStream sound;
	private Clip clip;
	private int IDX, pianoRoot = 0, guitarRoot = 0;
	private char keyPress;

	PlayToKeyboard(FileOpen files)
	{
		this.files = files;
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setBounds(935, 20, 464, 400);
		addKeyListener(this);
		setFocusable(true);
		MakeModel();
		
		table_RootChord = new JTable();
		table_RootChord.setEnabled(false);
		scrollPane_RootChord = new JScrollPane(table_RootChord);
		scrollPane_RootChord.setBounds(12, 108, 432, 63);
		contentPane.add(scrollPane_RootChord);
		
		table_ChildChord = new JTable();
		table_ChildChord.setEnabled(false);
		scrollPane_ChildChord = new JScrollPane(table_ChildChord);
		scrollPane_ChildChord.setBounds(12, 35, 432, 63);
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
		lbl_showOctave.setBounds(12, 181, 57, 15);
		contentPane.add(lbl_showOctave);
		
		lbl_showTone = new JLabel("");
		lbl_showTone.setBounds(123, 181, 57, 15);
		contentPane.add(lbl_showTone);
		
		setModel(0);
	}
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
		RootField[0] = new Object[][]{{"근음", "4옥타브", "5옥타브", "6옥타브"}};
		RootField[1] = new Object[][]{{"근음"}};
		RootField[2] = new Object[][]{{"근음", "C","D","E","F","G","A","B"}};
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
	
	public void Piano()
	{
		try
		{
			switch(keyPress)
			{
			case '1':
				pianoRoot = 0;
				lbl_showOctave.setText("4옥타브");
				return;
			case '2':
				pianoRoot = 1;
				lbl_showOctave.setText("5옥타브");
				return;
			case '3':
				pianoRoot = 2;
				lbl_showOctave.setText("6옥타브");
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
	public void Guitar()
	{
		String tones;		
		try
		{
			switch(keyPress)
			{	
			case '1':
				guitarRoot = 0;
				lbl_showOctave.setText("C");
				return;
			case '2':
				guitarRoot = 1;
				lbl_showOctave.setText("D");
				return;
			case '3':
				guitarRoot = 2;
				lbl_showOctave.setText("E");
				return;
			case '4':
				guitarRoot = 3;
				lbl_showOctave.setText("F");
				return;
			case '5':
				guitarRoot = 4;
				lbl_showOctave.setText("G");
				return;
			case '6':
				guitarRoot = 5;
				lbl_showOctave.setText("A");
				return;
			case '7':
				guitarRoot = 6;
				lbl_showOctave.setText("B");
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
			for(int i=0, flat=5; i<6; i++, flat--)
			{
				ch = tones.charAt(i);
				if(ch!='x')
				{
					sound = AudioSystem.getAudioInputStream(files.getSoundFiles(2)[flat][(int)ch-48]);
					clip = AudioSystem.getClip();
					clip.open(sound);
					clip.start();
				}
			}
		}
		catch(Exception e){}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		keyPress = e.getKeyChar();
		switch(IDX)
		{
		case 0:
			Piano();
			break;
		case 1:
			Drum();
			break;
		case 2:
			Guitar();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

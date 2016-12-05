import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * @brief ����ڰ� ��ǻ�� Ű���带 ������ �� ���ε� ���� �����Ű�� ������ �ϴ� Ŭ�����̴�. 
 */
class PlayToKeyboard extends PlayToKeyboardComponents implements ActionListener, KeyListener{

	//! Ű�����Է��� �߻����� �� � Ű�� �ԷµǾ����� �����ϴ� ����  
	private char keyPress;
	
	/**
	 * @brief ������. �ʱ���¸� �������ش�.
	 * @param FileOpen files :  ���Ͽ���Ŭ������ ��ü. �Ҹ������ �ʿ��� �����͸� ������ �ִ�.
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
	 * @brief ������������ Visible�� �����ϴ� �޼ҵ�
	 */
	public void setVisible(boolean bulb)
	{
		mainFrame.setVisible(bulb);
	}
	/**
	 * @brief table_RootChord, table_ChildChord ������ �ʿ��� ��ü�� �Ҵ��Ѵ�.
	 */
	public void MakeModel()
	{
		tablemodel_RootChord = new DefaultTableModel();
		tablemodel_ChildChord = new DefaultTableModel();
		centerSort = new DefaultTableCellRenderer();
		centerSort.setHorizontalAlignment(SwingConstants.CENTER);
		RootHeader = new String[][]
				{ {"�Է�", "1", "2", "3"}
				 ,{"�Է�"}
				 ,{"�Է�", "1", "2", "3", "4", "5", "6" ,"7"}
				 ,{"�Է�"}
				};
		
		RootField = new Object[4][][];
		RootField[0] = new Object[][]{{"����", pianoOctave[0], pianoOctave[1], pianoOctave[2]}};
		RootField[1] = new Object[][]{{"����"}};
		RootField[2] = new Object[][]{{"����", guitarOctave[0], guitarOctave[1], guitarOctave[2], guitarOctave[3], guitarOctave[4], guitarOctave[5], guitarOctave[6]}};
		RootField[3] = new Object[][]{{"����"}};
		
		ChildHeader = new String[][]
				{ {"�Է�", "A", "W", "S", "E", "D", "F", "T", "H", "U", "J", "I", "K", "L"}
				 ,{"�Է�", "A", "S", "D", "F", "H", "J", "K"}
				 ,{"�Է�", "A", "S", "D", "F", "H", "J", "K"}
				 ,{"�Է�", "A", "S", "D", "F", "H", "J", "K"}
				};
		
		
		ChildField = new Object[4][][];
		ChildField[0] = new Object[][]{{"����", "��", "��#", "��", "��#", "��", "��", "��#", "��", "��#", "��", "��#", "��", "��"}};
		ChildField[1] = new Object[][]{{"����", "���̽�", "������", "���׾�", "���̵�", "ũ����", "����Ž", "����Ž"}};
		ChildField[2] = new Object[][]{{"����", "M", "m", "7", "M7", "m7", "sus4", "dim"}};
		ChildField[3] = new Object[][]{{"����", "C", "D", "E", "F", "G", "A", "B"}};		
	}
	/**
	 * @brief ��ư�� ����  table_RootChord, table_ChildChord�� �ٸ��� �����Ѵ�.
	 * @param int idx : 0������ 3������ ���ʴ�� �ǾƳ�, �巳, ��Ÿ, ���̽�
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
	 * @brief ��ư�׼Ǹ�����
	 * @param ActionEvent e
	 * ��ư�� ������ �� �ش� ��ư�� �̸��� ���� �ʿ��� �޼ҵ带 ȣ���Ѵ�.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		
		switch(source.getText())
		{
		case "�ǾƳ�":
			setModel(0);
			scrollPane_RootChord.setVisible(true);
			mainFrame.requestFocus();
			break;
		case "�巳":
			setModel(1);
			scrollPane_RootChord.setVisible(false);
			lbl_showOctave.setText("");
			mainFrame.requestFocus();
			break;
		case "��Ÿ":
			setModel(2);
			scrollPane_RootChord.setVisible(true);
			mainFrame.requestFocus();
			break;
		case "���̽�":
			setModel(3);
			scrollPane_RootChord.setVisible(false);
			mainFrame.requestFocus();
			break;
		}
	}
	/**
	 * @brief Ű���带 ������ �� �ǾƳ� �Ҹ��� ����ǵ��� �����Ѵ�.
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
			lbl_showTone.setText("��");
			break;
		case 'w':
			clip = files.getSoundClips(0)[1][pianoRoot];
			lbl_showTone.setText("��#");
			break;
		case 's':
			clip = files.getSoundClips(0)[2][pianoRoot];
			lbl_showTone.setText("��");
			break;
		case 'e':
			clip = files.getSoundClips(0)[3][pianoRoot];
			lbl_showTone.setText("��#");
			break;
		case 'd':
			clip = files.getSoundClips(0)[4][pianoRoot];
			lbl_showTone.setText("��");
			break;
		case 'f':
			clip = files.getSoundClips(0)[5][pianoRoot];
			lbl_showTone.setText("��");
			break;
		case 't':
			clip = files.getSoundClips(0)[6][pianoRoot];
			lbl_showTone.setText("��#");
			break;
		case 'h':
			clip = files.getSoundClips(0)[7][pianoRoot];
			lbl_showTone.setText("��");
			break;
		case 'u':
			clip = files.getSoundClips(0)[8][pianoRoot];
			lbl_showTone.setText("��#");
			break;
		case 'j':
			clip = files.getSoundClips(0)[9][pianoRoot];
			lbl_showTone.setText("��");
			break;
		case 'i':
			clip = files.getSoundClips(0)[10][pianoRoot];
			lbl_showTone.setText("��#");
			break;
		case 'k':
			clip = files.getSoundClips(0)[11][pianoRoot];
			lbl_showTone.setText("��");
			break;
		case 'l':
			clip = files.getSoundClips(0)[0][(pianoRoot+1)%3];
			lbl_showTone.setText("��");
			break;
			
		default:
			return;
		}
		clip.setFramePosition(0);
		clip.start();
	}
	/**
	 * @brief Ű���带 ������ �� �巳 �Ҹ��� ����ǵ��� �����Ѵ�.
	 */
	public void Drum()
	{
		int idx = -1;
	
		switch(keyPress)
		{
		case 'a':
			idx = 0;
			lbl_showTone.setText("���̽�");
			break;
		case 's':
			idx = 1;
			lbl_showTone.setText("������");
			break;
		case 'd':
			idx = 2;
			lbl_showTone.setText("���׾�");
			break;
		case 'f':
			idx = 3;
			lbl_showTone.setText("���̵�");
			break;
		case 'h':
			idx = 4;
			lbl_showTone.setText("ũ����");
			break;
		case 'j':
			idx = 5;
			lbl_showTone.setText("����Ž");
			break;
		case 'k':
			idx = 6;
			lbl_showTone.setText("����Ž");
			break;
		default:
			return;
		}
		clip = files.getSoundClips(1)[0][idx];
		clip.setFramePosition(0);
		clip.start();
	
	}
	/**
	 * @brief Ű���带 ������ �� ��Ÿ �Ҹ��� ����ǵ��� �����Ѵ�.
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
	 * @brief Ű����׼Ǹ�����
	 * Ű���尡 ������ �� �����͸� �����ͼ� ó���Ѵ�.
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
	 * @brief Ű���带 �� �� �����͸� �����ͼ� ó���Ѵ�.
	 * PlayToKeyboard Ŭ���������� ������� �ʴ´�.
	 */
	@Override
	public void keyReleased(KeyEvent e) {}
	
	/**
	 * @brief Ű���带 �� �� �����͸� �����ͼ� ó���Ѵ�.
	 * PlayToKeyboard Ŭ���������� ������� �ʴ´�.
	 */
	@Override
	public void keyTyped(KeyEvent e) {}
}

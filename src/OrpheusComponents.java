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
 * @brief �������α׷��� GUI�� �����ϴ� Ŭ����
 * ������Ʈ ����ü�μ� Orpheus Ŭ������ �θ�Ŭ������ �ȴ�.  
 */
abstract class OrpheusComponents {

	//! GUI���� ������ �Ǵ� Frame
	protected JFrame mainFrame;
	
	//! GUI���� ������ �Ǵ� Panel
	protected JPanel contentPane;
	
	//! �Ǳ�, ����, ������ �� 3������ �����ϴ� ���̺�
	protected JTable[] table_Field;

	//! �۾�������� �����ִ� ���̺� ���ʴ�� �ǾƳ�, �巳, ��Ÿ, ���̽�
	protected JTable[] table_Task; //
	
	//! table_Field�� ��ũ���� �߰��ϴ� ScrollPane 
	protected JScrollPane[] scrollPane_Field;
	
	//! table_Task�� ��ũ���� �߰��ϴ� ScrollPane
	protected JScrollPane[] scrollPane_Task;
	
	//! ��Ʈ�� �����ϴ� �޺��ڽ�
	protected JComboBox BeatSet;
	
	//! ��ũ�� �����ϴ� �޺��ڽ�
	protected JComboBox BankChoice;
	
	//! ������ �����ϴ� �޺��ڽ�
	protected JComboBox RhythmChoice;
	
	//! ������ �����ϴ� �޺��ڽ�
	protected JComboBox RootChord;
	
	//! �������� �Ļ��Ǵ����� �����ϴ� �޺��ڽ�
	protected JComboBox ChildChord;
	
	//! BPMSet : BPM�� �Է��ϴ� �ؽ�Ʈ�ʵ� 
	protected JTextField BPMSet;
	
	//! �Ǳ⼱�ù�ư�� �ȳ����ִ� ���̺�
	protected JLabel lbl_SelectInstrument;
	
	//! BPMSet�� �ȳ����ִ� ���̺�
	protected JLabel lbl_SelectBPM;
	
	//! BeatSet�� �ȳ����ִ� ���̺�
	protected JLabel lbl_SelectBeatSet;

	//! �۾�����ٿ��� ����Ǵ� �Ǳ⸦ ��Ʈ��Ű�� üũ�ڽ�. ���ʴ�� �ǾƳ�, �巳, ��Ÿ, ���̽�
	protected JCheckBox[] Mute;
	
	//! �۾������ ���۽� ��Ʈ�γ��� Ȱ��ȭ ���θ� ���� üũ�ڽ�
	protected JCheckBox metronome_Check;

	//! ���ֽ��۹�ư. �۾�����ٿ��� ������ ��ũ�� ���ʷ� ����Ѵ�.
	protected JButton btn_start;
	
	//! ������ư. table_Field�� ������ ��� �����.
	protected JButton btn_erase;
	
	//! �ǾƳ뼱�ù�ư. table_Field�� �ǾƳ�� �����Ѵ�.
	protected JButton btn_SelectToPiano;
	
	//! �巳���ù�ư. table_Field�� �巳���� �����Ѵ�.
	protected JButton btn_SelectToDrum;
	
	//! ��Ÿ���ù�ư. table_Field�� ��Ÿ�� �����Ѵ�.
	protected JButton btn_SelectToGuitar;
	
	//! ���̽����ù�ư. table_Field�� ���̽��� �����Ѵ�.
	protected JButton btn_SelectToBase;
	
	//! ��ũ�����ư. table_Field[1], table_Field[2]�� ������ ��ũ�� �����Ѵ�.
	protected JButton btn_BankSave;
	
	//! ��ũ�����ư. �޺��ڽ��� ������ ��ũ�� ����Ѵ�.
	protected JButton btn_BankListen;
	
	//! ������Թ�ư. �̸� ������� �ִ� ������ table_Field[1], table_Field[2]�� �����Ѵ�.
	protected JButton btn_RhythmInsert;
	
	//! �ڵ���Թ�ư. �̸� ������� �ִ� �ڵ带 table_Field[1], table_Field[2]�� �����Ѵ�.
	protected JButton btn_RhythmListen;
	
	//! �������ư. �̸� ������� �ִ� ������ ����.
	protected JButton btn_ChordInsert;
	
	//! �ڵ����ư. �̸� ������� �ִ� �ڵ带 ����.
	protected JButton btn_ChordListen;
	
	//! �ǾƳ�ַι�ư. �ǾƳ��۾�����ٸ� ����Ѵ�.
	protected JButton btn_PianoSolo;
	
	//! �巳�ַι�ư. �巳�۾�����ٸ� ����Ѵ�.
	protected JButton btn_DrumSolo;
	
	//! ��Ÿ�ַι�ư. ��Ÿ�۾�����ٸ� ����Ѵ�.
	protected JButton btn_GuitarSolo;

	//! ���̽��ַι�ư. ���̽��۾�����ٸ� ����Ѵ�.
	protected JButton btn_BaseSolo;
	
	//! Ű���忬�ֹ�ư. Ű���� ���ָ� �����ϰ� �Ѵ�. 
	protected JButton btn_KeyboardPlay;
	
	//! ���� �����ư. �۾� ������ �����Ѵ�. 
	protected JButton btn_SaveScore;
	
	//! table_Field[0]�� �����ϴ� Ŭ������ü. ���ʴ�� �ǾƳ�, �巳, ��Ÿ, ���̽� 
	protected SettingToKind[] STK;
	
	//! table_Field[1]�� �����ϴ� Ŭ������ü. ���ʴ�� �ǾƳ�, �巳, ��Ÿ, ���̽�
	protected BeatField[] STB;
	
	//! table_Field[2]�� �����ϴ� Ŭ������ü. ���ʴ�� �ǾƳ�, �巳, ��Ÿ, ���̽�
	protected SettingToField[] STF;
	
	//! table_Task�� �����ϴ� Ŭ������ü. ���ʴ�� �ǾƳ�, �巳, ��Ÿ, ���̽�
	protected TaskField[] STT;
	
	//! ��ũ������ ���� Ŭ������ü. ���ʴ�� �ǾƳ�, �巳, ��Ÿ, ���̽�
	protected SaveBank[] Bank;

	//! BeatSet�� �����ϴ� String �迭
	protected String[] BeatList = {"2/2", "2/4", "3/4", "4/4", "-----", "6/8", "9/8", "12/8", "-----", "7/4", "11/4", "5/4"};

	//! RhythmChoice�� �����ϴ� String �迭
	protected String[] RhythmList = {"1","2","3","4"};
	
	//! RootChord�� �����ϴ� String �迭
	protected String[] RootChordList = {"C", "D", "E", "F", "G", "A", "B"};
	
	//! ChildChord�� �����ϴ� String �迭
	protected String[] ChildChordList = {"M", "m", "7", "M7", "m7", "sus4", "dim"};
	
	//! �ǾƳ��� �����̸� ��Ÿ���� String �迭. STF[0]�� �����ڷ� �����Ѵ�.
	protected String[] Piano_tones = {"", "1", "2", "3"};
	
	//! ��Ÿ�� �����̸� ��Ÿ���� String �迭. STF[2], STF[3]�� �����ڷ� �����Ѵ�.
	protected String[] Guitar_tones = {"", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
	
	//! ���Ͽ���Ŭ������ ��ü. ����� �ʿ��� �Ҹ����ϵ��� �����Ѵ�.
	protected FileOpen files;
	
	//! Ű���忬�ָ� ���� Ŭ������ ��ü
	protected PlayToKeyboard keyboardPlay;
	
	//! �̸� ������� �ڵ带 ���̺� �����ϱ� ���� Ŭ������ ��ü
	protected InputGuitarCode Code;
	
	//! ��ũ�̸���⸦ ���� ��ü
	protected Play bankPlay;
	
	//! �ַε�⸦ ���� ��ü
	protected Play[] taskPlay;
	
	//! ����ư���� �ߺ�Ŭ���� �����ϱ� ���� �������� ��ư�� ��Ȱ��ȭ ��Ű�� ���� ArrayList�� ����
	protected ArrayList<JButton> ListenButtons;
	
	//! ��Ʈ�γ��� ���� ��ü
	protected Metronome metronome;
	
	//! ���� ������ ���� ��ü
	protected SaveScore save;

	//! ���� ���� �ִ� ȭ���� � �Ǳ����� �����ϱ� ���� ����
	protected int IDX;
	
	//! �� ��ũ ����
	protected int totalBankCount;
	
	//! ���忩�θ� ���� â�� ���� �������� ���� ����
	protected boolean isSave;
	
	private ImageIcon[] icon;
	/**
	 * @brief ������
	 * ����� ������Ʈ�� �Ҵ��ϰ� ��ġ�� �����Ѵ�. 
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
		
		//��Ʈ
		Font HumanRoundHeadLine = new Font("�޸յձ�������", Font.PLAIN, 12);
		Font PureGothic = new Font("���� ���", Font.PLAIN, 12);
		Color bgcolor = new Color(255, 255, 255);
		Color fgcolor = new Color(0, 0, 0);
		lbl_SelectBeatSet = new JLabel("���� : ");
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
		
		metronome_Check = new JCheckBox("��Ʈ�γ�");
		metronome_Check.setBounds(12, 804, 97, 23);
		metronome_Check.setBackground(SystemColor.window);
		metronome_Check.setFont(PureGothic);
		contentPane.add(metronome_Check);
		
		//FileOpen
		files = new FileOpen();
		keyboardPlay = new PlayToKeyboard(files);
		keyboardPlay.setVisible(false);
		Code = new InputGuitarCode(files.getGuitarCode());
		
		//0������ 3������ ���ʷ� �ǾƳ�, �巳, ��Ÿ, ���̽�
		STB = new BeatField[4];
		STT = new TaskField[4];
		STK = new SettingToKind[4];
		Mute = new JCheckBox[4];
		table_Task = new JTable[4];
		scrollPane_Task = new JScrollPane[4];
		
		//0 : table_Kind, 1 : table_Beat, 2: table_Field
		table_Field = new JTable[3];
		scrollPane_Field = new JScrollPane[3];
		String[] instruments = {"�ǾƳ�", "�巳", "��Ÿ", "���̽�"};
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
			
			Mute[i] = new JCheckBox(instruments[i]+"��Ʈ");
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
		
		//0������ 3������ ���ʷ� �ǾƳ�, �巳, ��Ÿ, ���̽�
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

		lbl_SelectInstrument = new JLabel("�Ǳ⼱��");
		lbl_SelectInstrument.setFont(HumanRoundHeadLine);
		lbl_SelectInstrument.setBounds(12, 12, 57, 15);
		contentPane.add(lbl_SelectInstrument);
		
		btn_SelectToPiano = new JButton(icon[0]);
		btn_SelectToPiano.setText("�ǾƳ�");
		btn_SelectToPiano.setBackground(Color.WHITE);
		btn_SelectToPiano.setFont(PureGothic);
		btn_SelectToPiano.setForeground(Color.BLACK);
		btn_SelectToPiano.setBounds(12, 40, 200, 100);
		contentPane.add(btn_SelectToPiano);
		
		btn_SelectToDrum = new JButton(icon[1]);
		btn_SelectToDrum.setText("�巳");
		btn_SelectToDrum.setBackground(Color.WHITE);
		btn_SelectToDrum.setFont(PureGothic);
		btn_SelectToDrum.setBounds(212, 40, 200, 100);
		contentPane.add(btn_SelectToDrum);
		
		btn_SelectToGuitar = new JButton(icon[2]);
		btn_SelectToGuitar.setText("��Ÿ");
		btn_SelectToGuitar.setBackground(Color.WHITE);
		btn_SelectToGuitar.setFont(PureGothic);
		btn_SelectToGuitar.setBounds(412, 40, 200, 100);
		contentPane.add(btn_SelectToGuitar);
		
		btn_SelectToBase = new JButton(icon[3]);
		btn_SelectToBase.setText("���̽�");
		btn_SelectToBase.setBackground(Color.WHITE);
		btn_SelectToBase.setFont(PureGothic);
		btn_SelectToBase.setBounds(612, 40, 200, 100);
		contentPane.add(btn_SelectToBase);
		
		btn_start = new JButton("���ֽ���");
		btn_start.setBackground(bgcolor);
		btn_start.setFont(HumanRoundHeadLine);
		btn_start.setBounds(393, 804, 99, 33);
		contentPane.add(btn_start);
		
		btn_erase = new JButton("�����");
		btn_erase.setBackground(bgcolor);
		btn_erase.setFont(HumanRoundHeadLine);
		btn_erase.setBounds(504, 804, 99, 33);
		contentPane.add(btn_erase);
	
		btn_KeyboardPlay = new JButton("Ű���忬��");
		btn_KeyboardPlay.setBackground(bgcolor);
		btn_KeyboardPlay.setFont(HumanRoundHeadLine);
		btn_KeyboardPlay.setBounds(615,804,100,33);
		contentPane.add(btn_KeyboardPlay);

		btn_BankSave = new JButton("��ũ ����");
		btn_BankSave.setBackground(Color.WHITE);
		btn_BankSave.setFont(PureGothic);
		btn_BankSave.setBounds(124, 150, 100, 25);
		contentPane.add(btn_BankSave);
		
		btn_BankListen = new JButton("��ũ ���");
		btn_BankListen.setBackground(Color.WHITE);
		btn_BankListen.setFont(PureGothic);
		btn_BankListen.setBounds(123, 187, 100, 25);
		contentPane.add(btn_BankListen);
		
		btn_RhythmListen = new JButton("���� ���");
		btn_RhythmListen.setBackground(Color.WHITE);
		btn_RhythmListen.setFont(PureGothic);
		btn_RhythmListen.setBounds(345, 151, 120, 25);
		contentPane.add(btn_RhythmListen);
		
		btn_RhythmInsert = new JButton("���� �Է�");
		btn_RhythmInsert.setBackground(Color.WHITE);
		btn_RhythmInsert.setFont(PureGothic);
		btn_RhythmInsert.setBounds(345, 188, 120, 25);
		contentPane.add(btn_RhythmInsert);
		
		btn_ChordListen = new JButton("�ڵ� ���");
		btn_ChordListen.setBackground(Color.WHITE);
		btn_ChordListen.setFont(PureGothic);
		btn_ChordListen.setBounds(595, 151, 120, 25);
		contentPane.add(btn_ChordListen);
		
		btn_ChordInsert = new JButton("�ڵ� �Է�");
		btn_ChordInsert.setBackground(Color.WHITE);
		btn_ChordInsert.setFont(PureGothic);
		btn_ChordInsert.setBounds(595, 188, 120, 25);
		contentPane.add(btn_ChordInsert);
		
		btn_PianoSolo = new JButton("�ǾƳ�ַ�");
		btn_PianoSolo.setBackground(SystemColor.window);
		btn_PianoSolo.setFont(PureGothic);
		btn_PianoSolo.setBounds(718, 537, 97, 23);
		contentPane.add(btn_PianoSolo);
		
		btn_DrumSolo = new JButton("�巳�ַ�");
		btn_DrumSolo.setBackground(SystemColor.window);
		btn_DrumSolo.setFont(PureGothic);
		btn_DrumSolo.setBounds(718, 600, 97, 23);
		contentPane.add(btn_DrumSolo);
		
		btn_GuitarSolo = new JButton("��Ÿ�ַ�");
		btn_GuitarSolo.setBackground(SystemColor.window);
		btn_GuitarSolo.setFont(PureGothic);
		btn_GuitarSolo.setBounds(718, 667, 97, 23);
		contentPane.add(btn_GuitarSolo);
		
		btn_BaseSolo = new JButton("���̽��ַ�");
		btn_BaseSolo.setBackground(SystemColor.window);
		btn_BaseSolo.setFont(PureGothic);
		btn_BaseSolo.setBounds(718, 730, 97, 23);
		contentPane.add(btn_BaseSolo);
		
		btn_SaveScore = new JButton("���� ����");
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

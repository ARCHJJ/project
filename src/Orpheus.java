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
 * @brief main �Լ��� ����Ǵ� Ŭ����. GUI �������̽��� ���ǵǴ� ���̴�.
 */
public class Orpheus extends JFrame implements ActionListener{

	//! GUI���� ������ �Ǵ� Panel
	private JPanel contentPane;
	
	//! �Ǳ�, ����, ������ �� 3������ �����ϴ� ���̺�
	private JTable[] table_Field;

	//! �۾�������� �����ִ� ���̺� ���ʴ�� �ǾƳ�, �巳, ��Ÿ, ���̽�
	private JTable[] table_Task; //
	
	//! table_Field�� ��ũ���� �߰��ϴ� ScrollPane 
	private JScrollPane[] scrollPane_Field;
	
	//! table_Task�� ��ũ���� �߰��ϴ� ScrollPane
	private JScrollPane[] scrollPane_Task;
	
	//! BeatSet			: ��Ʈ�� �����ϴ� �޺��ڽ�
	//! BankChoice		: ��ũ�� �����ϴ� �޺��ڽ�
	//! RhythmChoice	: ������ �����ϴ� �޺��ڽ�
	//! RootChord		: �ڵ��� ������ �����ϴ� �޺��ڽ�
	//! ChildChord		: �������� �Ļ��Ǵ����� �����ϴ� �޺��ڽ�
	private JComboBox BeatSet, BankChoice, RhythmChoice, RootChord, ChildChord;
	
	//! BPMSet : BPM�� �Է��ϴ� �ؽ�Ʈ�ʵ� 
	private JTextField BPMSet;
	
	//! lbl_SelectInstrument : �Ǳ⼱�ù�ư�� �ȳ����ִ� ���̺�
	//! lbl_SelectBPM		 : BPMSet�� �ȳ����ִ� ���̺�
	//! lbl_SelectBeatSet	 : BeatSet�� �ȳ����ִ� ���̺�
	private JLabel lbl_SelectInstrument, lbl_SelectBPM, lbl_SelectBeatSet;

	//! �۾�����ٿ��� ����Ǵ� �Ǳ⸦ ��Ʈ��Ű�� üũ�ڽ�. ���ʴ�� �ǾƳ�, �巳, ��Ÿ, ���̽�
	private JCheckBox[] Mute;

	//! btn_start			: ���ֽ��۹�ư. �۾�����ٿ��� ������ ��ũ�� ���ʷ� ����Ѵ�.
	//! btn_erase			: ������ư. table_Field�� ������ ��� �����.
	//! btn_SelectToPiano	: �ǾƳ뼱�ù�ư. table_Field�� �ǾƳ�� �����Ѵ�.
	//! btn_SelectToDrum	: �巳���ù�ư. table_Field�� �巳���� �����Ѵ�.
	//! btn_SelectToGuitar	: ��Ÿ���ù�ư. table_Field�� ��Ÿ�� �����Ѵ�.
	//! btn_SelectToBase	: ���̽����ù�ư. table_Field�� ���̽��� �����Ѵ�.
	private JButton btn_start, btn_erase, btn_SelectToPiano, btn_SelectToDrum, btn_SelectToGuitar, btn_SelectToBase;
	
	//! btn_BankSave		: ��ũ�����ư. table_Field[1], table_Field[2]�� ������ ��ũ�� �����Ѵ�.
	//! btn_BankListen		: ��ũ�����ư. �޺��ڽ��� ������ ��ũ�� ����Ѵ�.
	//! btn_RhythmInsert	: ������Թ�ư. �̸� ������� �ִ� ������ table_Field[1], table_Field[2]�� �����Ѵ�.
	//! btn_RhythmListen	: �������ư. �̸� ������� �ִ� ������ ����.
	//! btn_ChordInsert		: �ڵ���Թ�ư. �̸� ������� �ִ� �ڵ带 table_Field[1], table_Field[2]�� �����Ѵ�.
	//! btn_ChordListen		: �ڵ����ư. �̸� ������� �ִ� �ڵ带 ����.
	private JButton btn_BankSave, btn_BankListen, btn_RhythmInsert, btn_RhythmListen, btn_ChordInsert, btn_ChordListen;
	
	//! btn_PianoSolo	 : �ǾƳ�ַι�ư. �ǾƳ��۾�����ٸ� ����Ѵ�.
	//! btn_DrumSolo	 : �巳�ַι�ư. �巳�۾�����ٸ� ����Ѵ�.
	//! btn_GuitarSolo	 : ��Ÿ�ַι�ư. ��Ÿ�۾�����ٸ� ����Ѵ�.
	//! btn_BaseSolo	 : ���̽��ַι�ư. ���̽��۾�����ٸ� ����Ѵ�.
	//! btn_KeyBoardPlay : Ű���忬�ֹ�ư. Ű���� ���ָ� �����ϰ� �Ѵ�. 
	private JButton btn_PianoSolo, btn_DrumSolo, btn_GuitarSolo, btn_BaseSolo, btn_KeyboardPlay;
	
	//! table_Field[0]�� �����ϴ� Ŭ������ü. ���ʴ�� �ǾƳ�, �巳, ��Ÿ, ���̽� 
	private SettingToKind[] STK;
	
	//! table_Field[1]�� �����ϴ� Ŭ������ü. ���ʴ�� �ǾƳ�, �巳, ��Ÿ, ���̽�
	private BeatField[] STB;
	
	//! table_Field[2]�� �����ϴ� Ŭ������ü. ���ʴ�� �ǾƳ�, �巳, ��Ÿ, ���̽�
	private SettingToField[] STF;
	
	//! table_Task�� �����ϴ� Ŭ������ü. ���ʴ�� �ǾƳ�, �巳, ��Ÿ, ���̽�
	private TaskField[] STT;
	
	//! ��ũ������ ���� Ŭ������ü. ���ʴ�� �ǾƳ�, �巳, ��Ÿ, ���̽�
	private SaveBank[] Bank;

	//! BeatSet�� �����ϴ� String �迭
	private String[] BeatList = {"2/2", "2/4", "3/4", "4/4", "-----", "6/8", "9/8", "12/8", "-----", "7/4", "11/4", "5/4"};

	//! RhythmChoice�� �����ϴ� String �迭
	private String[] RhythmList = {"1","2","3","4"};
	
	//! RootChord�� �����ϴ� String �迭
	private String[] RootChordList = {"C", "D", "E", "F", "G", "A", "B"};
	
	//! ChildChord�� �����ϴ� String �迭
	private String[] ChildChordList = {"M", "m", "7", "M7", "m7", "sus4", "dim"};
	
	//! �ǾƳ��� �����̸� ��Ÿ���� String �迭. STF[0]�� �����ڷ� �����Ѵ�.
	private String[] Piano_tones = {"", "1", "2", "3"};
	
	//! ��Ÿ�� �����̸� ��Ÿ���� String �迭. STF[2], STF[3]�� �����ڷ� �����Ѵ�.
	private String[] Guitar_tones = {"", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
	
	//! ���Ͽ���Ŭ������ ��ü. ����� �ʿ��� �Ҹ����ϵ��� �����Ѵ�.
	private FileOpen files;
	
	//! Ű���忬�ָ� ���� Ŭ������ ��ü
	private PlayToKeyboard keyboardPlay;
	
	//! �̸� ������� �ڵ带 ���̺� �����ϱ� ���� Ŭ������ ��ü
	private Input_GuitarCode Code;
	
	//!	��������
	//private PlayCode CodePlay;
	
	//! �Ҹ��� ����� �� UI���� �����带 �����ϱ� ���� ����Ŭ������ ��ü.  
	private static Orpheus ui;
	
	//! ��ũ�̸���⸦ ���� ��ü
	private static Play bankPlay;
	
	//! �ַε�⸦ ���� ��ü
	private static Play[] taskPlay;

	//! ���� ���� �ִ� ȭ���� � �Ǳ����� �����ϱ� ���� ����
	private int IDX;
	
	/**
	 * @brief main �Լ�
	 * ��ũ���, �۾������ �ַε��, ���ֽ��۰� UI�� �����带 �����Ѵ�.
	 */
	public static void main(String[] args) {
		ui = new Orpheus();
		
		//��ũ���
		bankPlay = new Play(ui);
		bankPlay.MuteDisable();
		bankPlay.singleSet();
		bankPlay.setDaemon(true);
		bankPlay.ThreadStart();
		
		taskPlay = new Play[4];
		//for(int i=0; i<4; i++)
		for(int i=0; i<3; i++)	//���� ���̽��� �ϼ����̶� �ݺ��� 3����
		{
			taskPlay[i] = new Play(ui);
			taskPlay[i].setDaemon(true);
			taskPlay[i].ThreadStart();
		}
	}

	/**
	 * @brief ������
	 * ����� ������Ʈ�� �Ҵ��ϰ� ��ġ�� �����Ѵ�.
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
		
		//��Ʈ
		Font HumanRoundHeadLine = new Font("�޸յձ�������", Font.PLAIN, 12);
		Font PureGothic = new Font("���� ���", Font.PLAIN, 12);
		Color bgcolor = new Color(255, 255, 255);
		Color fgcolor = new Color(0, 0, 0);
		lbl_SelectBeatSet = new JLabel("���� : ");
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

		lbl_SelectInstrument = new JLabel("�Ǳ⼱��");
		lbl_SelectInstrument.setFont(HumanRoundHeadLine);
		lbl_SelectInstrument.setBounds(12, 12, 57, 15);
		contentPane.add(lbl_SelectInstrument);
		
		btn_SelectToPiano = new JButton("�ǾƳ�");
		btn_SelectToPiano.setBackground(Color.WHITE);
		btn_SelectToPiano.setFont(PureGothic);
		btn_SelectToPiano.setForeground(Color.BLACK);
		btn_SelectToPiano.addActionListener(this);
		btn_SelectToPiano.setBounds(12, 40, 99, 25);
		contentPane.add(btn_SelectToPiano);
		
		btn_SelectToDrum = new JButton("�巳");
		btn_SelectToDrum.setBackground(Color.WHITE);
		btn_SelectToDrum.setFont(PureGothic);
		btn_SelectToDrum.addActionListener(this);
		btn_SelectToDrum.setBounds(123, 40, 99, 25);
		contentPane.add(btn_SelectToDrum);
		
		btn_SelectToGuitar = new JButton("��Ÿ");
		btn_SelectToGuitar.setBackground(Color.WHITE);
		btn_SelectToGuitar.setFont(PureGothic);
		btn_SelectToGuitar.addActionListener(this);
		btn_SelectToGuitar.setBounds(234, 40, 99, 25);
		contentPane.add(btn_SelectToGuitar);
		
		btn_SelectToBase = new JButton("���̽�");
		btn_SelectToBase.setBackground(Color.WHITE);
		btn_SelectToBase.setFont(PureGothic);
		btn_SelectToBase.addActionListener(this);
		btn_SelectToBase.setBounds(345, 40, 99, 25);
		contentPane.add(btn_SelectToBase);
		
		btn_start = new JButton("���ֽ���");
		btn_start.setBackground(bgcolor);
		btn_start.setFont(HumanRoundHeadLine);
		btn_start.addActionListener(this);
		btn_start.setBounds(393, 737, 99, 33);
		contentPane.add(btn_start);
		
		btn_erase = new JButton("�����");
		btn_erase.setBackground(bgcolor);
		btn_erase.setFont(HumanRoundHeadLine);
		btn_erase.addActionListener(this);
		btn_erase.setBounds(504, 737, 99, 33);
		contentPane.add(btn_erase);
	
		btn_KeyboardPlay = new JButton("Ű���忬��");
		btn_KeyboardPlay.setBackground(bgcolor);
		btn_KeyboardPlay.setFont(HumanRoundHeadLine);
		btn_KeyboardPlay.addActionListener(this);
		btn_KeyboardPlay.setBounds(615,737,100,33);
		contentPane.add(btn_KeyboardPlay);

		btn_BankSave = new JButton("��ũ ����");
		btn_BankSave.setBackground(Color.WHITE);
		btn_BankSave.setFont(PureGothic);
		btn_BankSave.addActionListener(this);
		btn_BankSave.setBounds(124, 83, 100, 25);
		contentPane.add(btn_BankSave);
		
		btn_BankListen = new JButton("��ũ ���");
		btn_BankListen.setBackground(Color.WHITE);
		btn_BankListen.setFont(PureGothic);
		btn_BankListen.addActionListener(this);
		btn_BankListen.setBounds(123, 120, 100, 25);
		contentPane.add(btn_BankListen);
		
		btn_RhythmListen = new JButton("���� ���");
		btn_RhythmListen.setBackground(Color.WHITE);
		btn_RhythmListen.setFont(PureGothic);
		btn_RhythmListen.setBounds(345, 84, 120, 25);
		contentPane.add(btn_RhythmListen);
		
		btn_RhythmInsert = new JButton("���� �Է�");
		btn_RhythmInsert.setBackground(Color.WHITE);
		btn_RhythmInsert.setFont(PureGothic);
		btn_RhythmInsert.setBounds(345, 121, 120, 25);
		contentPane.add(btn_RhythmInsert);
		
		btn_ChordListen = new JButton("�ڵ� ���");
		btn_ChordListen.setBackground(Color.WHITE);
		btn_ChordListen.setFont(PureGothic);
		btn_ChordListen.addActionListener(this);
		btn_ChordListen.setBounds(595, 84, 120, 25);
		contentPane.add(btn_ChordListen);
		
		btn_ChordInsert = new JButton("�ڵ� �Է�");
		btn_ChordInsert.setBackground(Color.WHITE);
		btn_ChordInsert.setFont(PureGothic);
		btn_ChordInsert.addActionListener(this);
		btn_ChordInsert.setBounds(595, 121, 120, 25);
		contentPane.add(btn_ChordInsert);
		
		btn_PianoSolo = new JButton("�ǾƳ�ַ�");
		btn_PianoSolo.setBackground(SystemColor.window);
		btn_PianoSolo.setFont(PureGothic);
		btn_PianoSolo.addActionListener(this);
		btn_PianoSolo.setBounds(718, 470, 97, 23);
		contentPane.add(btn_PianoSolo);
		
		btn_DrumSolo = new JButton("�巳�ַ�");
		btn_DrumSolo.setBackground(SystemColor.window);
		btn_DrumSolo.setFont(PureGothic);
		btn_DrumSolo.addActionListener(this);
		btn_DrumSolo.setBounds(718, 533, 97, 23);
		contentPane.add(btn_DrumSolo);
		
		btn_GuitarSolo = new JButton("��Ÿ�ַ�");
		btn_GuitarSolo.setBackground(SystemColor.window);
		btn_GuitarSolo.setFont(PureGothic);
		btn_GuitarSolo.addActionListener(this);
		btn_GuitarSolo.setBounds(718, 600, 97, 23);
		contentPane.add(btn_GuitarSolo);
		
		btn_BaseSolo = new JButton("���̽��ַ�");
		btn_BaseSolo.setBackground(SystemColor.window);
		btn_BaseSolo.setFont(PureGothic);
		btn_BaseSolo.addActionListener(this);
		btn_BaseSolo.setBounds(718, 663, 97, 23);
		contentPane.add(btn_BaseSolo);

		setField(0);
		
		setVisible(true);
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
		case "�ǾƳ�" :
			setField(0);
			break;
			
		case "�巳" :
			setField(1);
			break;
			
		case "��Ÿ" :
			setField(2);
			break;
			
		case "���̽�" :
			setField(3);
			break;
			
		case "��ũ ����":
			saveBank();
			break;
		
		case "��ũ ���":
			ListenBank();
			break;
		
		case "�ǾƳ�ַ�":
			ListenSolo(0);
			break;
			
		case "�巳�ַ�":
			ListenSolo(1);
			break;
			
		case "��Ÿ�ַ�":
			ListenSolo(2);
			break;
			
		case "���̽��ַ�":
			ListenSolo(3);
			break;
			
		case "���ֽ���":
			musicQ();
			break;
			
		case "�����":
			init();
			break;
			
		case "�ڵ� �Է�":
			inputCode();
			break;
			
		case "�ڵ� ���":
			playCode();
			break;
			
		case "Ű���忬��":
			keyboardPlay.setVisible(true);
			System.gc();
			break;
		}
	}
	
	/**
	 * @brief ���� �����ִ� JTable�� �̸� ������� �ڵ带 �����Ѵ�.
	 */
	public void inputCode()
	{
		Code.addCode(table_Field[2], RootChord.getSelectedIndex(), ChildChord.getSelectedIndex());
		STF[2].addColumn(table_Field[2].getModel().getColumnCount()-1);
		STF[2].setCellOption(table_Field[2]);
	}
	
	
	/**
	 * @brief table_Field�� �Ǳ⿡ ���� �ٸ��� �����Ѵ�.
	 * @param int idx : 0������ 3������ ���ʴ�� �ǾƳ�, �巳, ��Ÿ, ���̽�
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
	 * @brief ���� ���� �ִ� table_Field[1], table_Field[2]�� ����� �ʱ���·� �ǵ�����.
	 */
	public void init()
	{
		STB[IDX].Init();
		STF[IDX].Init();
		
		STB[IDX].setCellOption(table_Field[1]);
		STF[IDX].setCellOption(table_Field[2]);
	}
	
	/**
	 * @brief ��ũ�� �߰��Ǹ� BankChoice���� ������ �� �ֵ��� �߰��Ѵ�.
	 * @param int size : BankChoice�� ũ��
	 */
	public void setBankList(int size)
	{
		BankChoice.removeAllItems();
		BankChoice.addItem("");
		for(int i=1; i<=size; i++)
			BankChoice.addItem(Integer.toString(i));
	}
	
	/**
	 * @brief ��ũ�� ���� �� ���ڱ�Ģ�� ������ Ȯ���ϴ� �޼ҵ�
	 * @param BeatField BeatField : ���������� ������ �ִ� Ŭ������ ��ü
	 * @return
	 */
	public boolean OutofBeat(BeatField BeatField)
	{
		if(BeatField.out_max(table_Field[1]) < RestTimeSetup.music_score)
		{
			JOptionPane.showMessageDialog(null, "������ ���ڰ� �����մϴ�.");
			return false;
		}
		if(BeatField.out_max(table_Field[1]) > RestTimeSetup.music_score)
		{
			JOptionPane.showMessageDialog(null, "������ ���ڰ� �ʰ��Ͽ����ϴ�.");
			return false;
		}
		return true;
	}
	
	/**
	 * @brief table_Field[1], table_Field[2]�� ������ ��ũ�� �����Ѵ�.
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
		JOptionPane.showMessageDialog(null, num+"�� ��ũ ����Ϸ�.");
	}
	
	/**
	 * @brief BankChoice�� ���� ���õ� ��ũ�� ����Ѵ�.
	 * UI�� ������� �����Ѵ�.
	 */
	public void ListenBank()
	{
		int BankNum = BankChoice.getSelectedIndex();
		bankPlay.setBank(STF[IDX].BankList.get(BankNum), files.getSoundFiles(IDX));
		bankPlay.action();
	}

	/**
	 * @brief RootChord, ChildChord �� ���� ���õ� �ڵ带 ����Ѵ�.
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
	 * @brief �۾�����ٿ��� Ư�� �Ǳ����ٸ� ����Ѵ�.
	 * UI�� ������� �����Ѵ�.
	 * @param int idx : 0������ 3������ ���ʴ�� �ǾƳ�, �巳, ��Ÿ, ���̽�
	 */
	public void ListenSolo(int idx)
	{
		taskPlay[idx].multySet(table_Task[idx], STF[idx].BankList, files.getSoundFiles(idx), Mute[idx]);
		taskPlay[idx].action();
	}
	
	/**
	 * @brief �۾�����ٿ� �ִ� ��� ������ ����Ѵ�.
	 * taskPlay[0], taskPlay[1], taskPlay[2], taskPlay[3], UI�� ������� �����Ѵ�.
	 */
	public void musicQ()
	{
		//���ֽ��� �޼ҵ�
		//for(int i=0; i<4; i++)
		for(int i=0; i<3; i++)
		{
			taskPlay[i].multySet(table_Task[i], STF[i].BankList, files.getSoundFiles(i), Mute[i]);
			taskPlay[i].action();
		}
	}
	
	/**
	 * @brief ��ũ��� ��ư�� �����Ѵ�.
	 * �ַε��, ���ֽ����� ������ �� �ߺ�Ŭ���� �����ϱ� ���� �ش� Ŭ�������� btn_BankListen�� Enable(false)��Ų��. 
	 * @return btn_BankListen
	 */
	public JButton getBankListenButton()
	{
		return btn_BankListen;
	}
}

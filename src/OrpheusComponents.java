import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;

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
import javax.swing.SwingConstants;
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
	protected JComboBox RhythmChoice1;
	
	//! ������ �����ϴ� �޺��ڽ�
	protected JComboBox RhythmChoice2;
	
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
	
	//! �ʱ�ȭ ��ư. table_Field�� ������ ��� �����. 
	protected JButton btn_init;
	
	//! ������ư. table_Field�� ���� �ϳ� �����.
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
	
	//! �ַι�ư. ������ �۾�����ٸ� ����Ѵ�.
	protected JButton[] btn_Solo;
	
	//! Ű���忬�ֹ�ư. Ű���� ���ָ� �����ϰ� �Ѵ�. 
	protected JButton btn_KeyboardPlay;
	
	//! ���� �����ư. �۾� ������ �����Ѵ�. 
	protected JButton btn_SaveScore;
	
	//! ���� ������ư. �۾� ������ �����Ѵ�. 
	protected JButton btn_OpenScore;
	
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
	protected String[] BeatList = {"2/2", "2/4", "3/4", "4/4", "-----", "6/8", "9/8", "12/8", "----- ", "7/4", "11/4", "5/4"};

	//! RhythmChoice�� �����ϴ� String �迭
	protected String[] RhythmList1 = {"1", "2", "4"};
	
	//! RhythmChoice�� �����ϴ� String �迭
	protected String[] RhythmList2 = {"1", "2", "3", "4"};
	
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
	
	//! �̸� ������� �巳������ ���̺� �����ϱ� ���� Ŭ������ ��ü
	protected InputDrumRhythm DrumRhythm;
	
	//! �̸� ������� �巳������ ����ϱ� ���� ��ü
	protected PlayDrumRhythm DrumPlay;
	
	//! �̸� ������� �巳������ ���̺� �����ϱ� ���� Ŭ������ ��ü
	protected InputBassRhythm BassRhythm;
	
	//! �̸� ������� �巳������ �����ϱ� ���� Ŭ������ ��ü
	protected PlayBassRhythm BassPlay;
	
	//! ��ũ�̸���⸦ ���� ��ü
	protected Play bankPlay;
	
	//! �ַε�⸦ ���� ��ü
	protected Play[] taskPlay;
	
	//! ��Ʈ�γ��� ���� ��ü
	protected Metronome metronome;
	
	//! ���� ������ ���� ��ü
	protected SaveScore save;
	
	//! ���� ������ ���� ��ü
	protected OpenScore open;

	//! ���� ���� �ִ� ȭ���� � �Ǳ����� �����ϱ� ���� ����
	protected int IDX;
	
	//! �� ��ũ ����
	protected int totalBankCount;
	
	//! ����ó�� BPM
	protected IntegerDocument IdBPM;
	
	//! ���忩�θ� ���� â�� ���� �������� ���� ����
	protected boolean isSave;
	
	//! ��ư�� �ߺ�Ŭ���� �Ǵ��ϱ� ���� Ŭ����
	protected Swtch[] swtch;
	
	//! ��ư�� �̹����� �����ϱ� ���� icon[]
	private ImageIcon[] icon;
	
	/**
	 * @brief ������
	 * ����� ������Ʈ�� �Ҵ��ϰ� ��ġ�� �����Ѵ�. 
	 */
	public OrpheusComponents() {
		mainFrame = new JFrame();
		mainFrame.setTitle("������콺 ver.1.0 (by. ����ϼҽ���)");
		mainFrame.setForeground(Color.WHITE);
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("images.png"));//icon
		mainFrame.setBounds(100, 20, 800, 768);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainFrame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//��Ʈ
		Font HumanRoundHeadLine12 = new Font("�޸յձ�������", Font.PLAIN, 12);
		Font HumanRoundHeadLine20 = new Font("�޸յձ�������", Font.BOLD, 18);
		Font PureGothic12 = new Font("���� ���", Font.PLAIN, 12);
		Font PureGothic14 = new Font("���� ���", Font.BOLD, 14);
		Color bgcolor = new Color(255, 255, 255);
		Color fgcolor = new Color(0, 0, 0);
		lbl_SelectBeatSet = new JLabel("����  : ");
		lbl_SelectBeatSet.setFont(PureGothic14);
		lbl_SelectBeatSet.setBounds(575, 20, 54, 23);
		contentPane.add(lbl_SelectBeatSet);

		BeatSet = new JComboBox(BeatList);
		BeatSet.setFont(PureGothic12);
		BeatSet.setSelectedItem("4/4");
		BeatSet.setBounds(625, 23, 53, 20);
		contentPane.add(BeatSet);		

		BankChoice = new JComboBox();
		BankChoice.setBounds(12, 83, 100, 25);
		contentPane.add(BankChoice);
		
		RhythmChoice1 = new JComboBox(RhythmList1);
		RhythmChoice1.setBounds(238, 83, 100, 25);
		contentPane.add(RhythmChoice1);
		
		RhythmChoice2 = new JComboBox(RhythmList2);
		RhythmChoice2.setBounds(238, 120, 100, 25);
		contentPane.add(RhythmChoice2);
		
		RootChord = new JComboBox(RootChordList);
		RootChord.setBounds(464, 83, 100, 25);
		contentPane.add(RootChord);

		ChildChord = new JComboBox(ChildChordList);
		ChildChord.setBounds(464, 120, 100, 25);
		contentPane.add(ChildChord);
	
		lbl_SelectBPM = new JLabel("BPM : ");
		lbl_SelectBPM.setFont(PureGothic14);
		lbl_SelectBPM.setBounds(576, 45, 57, 25);
		contentPane.add(lbl_SelectBPM);
		
		IdBPM = new IntegerDocument();
		
		BPMSet = new JTextField();
		BPMSet.setFont(PureGothic12);
		BPMSet.setBounds(627, 48, 50, 23);
		BPMSet.setDocument(IdBPM);
		BPMSet.setText("100");
		contentPane.add(BPMSet);
		
		metronome_Check = new JCheckBox("��Ʈ�γ�");
		metronome_Check.setBounds(681, 219, 97, 23);
		metronome_Check.setBackground(SystemColor.window);
		metronome_Check.setFont(PureGothic14);
		contentPane.add(metronome_Check);
		
		//FileOpen
		files = new FileOpen();
		keyboardPlay = new PlayToKeyboard(files);
		keyboardPlay.setVisible(false);
		Code = new InputGuitarCode(files.getCode(2));
		DrumRhythm = new InputDrumRhythm();
		DrumPlay = new PlayDrumRhythm();
		
		BassRhythm =  new InputBassRhythm();
		BassPlay = new PlayBassRhythm();
		
		save = new SaveScore();
		open = new OpenScore();
		
		//0������ 3������ ���ʷ� �ǾƳ�, �巳, ��Ÿ, ���̽�
		STB = new BeatField[4];
		STT = new TaskField[4];
		STK = new SettingToKind[4];
		Mute = new JCheckBox[4];
		table_Task = new JTable[4];
		scrollPane_Task = new JScrollPane[4];
		btn_Solo = new JButton[4];
		table_Field = new JTable[3]; //0 : table_Kind, 1 : table_Beat, 2: table_Field
		scrollPane_Field = new JScrollPane[3];
		String[] instruments = {"�ǾƳ�", "�巳", "��Ÿ", "���̽�"};
		
		for(int i=0, TaskY=468, MuteY = 496; i<4; i++)
		{
			if(i<3)
			{
				table_Field[i] = new JTable();
				table_Field[i].setBackground(Color.WHITE);
				table_Field[i].setForeground(fgcolor);
				table_Field[i].setFont(PureGothic12);
				table_Field[i].setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				scrollPane_Field[i] = new JScrollPane(table_Field[i]);
				scrollPane_Field[i].getViewport().setBackground(Color.WHITE);
				
				contentPane.add(scrollPane_Field[i]);
			}
			STB[i] = new BeatField();
			STK[i] = new SettingToKind(files.getSoundNames(i));
			STT[i] = new TaskField(instruments[i]);			
						
			table_Task[i] = new JTable(STT[i].getModel());
			table_Task[i].setFont(PureGothic12);
			table_Task[i].setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			scrollPane_Task[i] = new JScrollPane(table_Task[i]);
			//scrollPane_Task[i].getHorizontalScrollBar().setValue(scrollPane_Task[i].getHorizontalScrollBar().getMaximum());
			scrollPane_Task[i].getViewport().setBackground(Color.WHITE);
			scrollPane_Task[i].setBounds(12, TaskY, 666, 65);
			
			STT[i].setCellOption(table_Task[i]);
			
			btn_Solo[i] = new JButton(instruments[i]+"�ַ�");
			btn_Solo[i].setBackground(SystemColor.window);
			btn_Solo[i].setFont(PureGothic12);
			btn_Solo[i].setBounds(681, TaskY, 97, 23);
			
			Mute[i] = new JCheckBox(instruments[i]+"��Ʈ");
			Mute[i].setBackground(SystemColor.window);
			Mute[i].setFont(PureGothic12);
			Mute[i].setBounds(681, MuteY, 97, 23);
			
			contentPane.add(scrollPane_Task[i]);
			contentPane.add(btn_Solo[i]);
			contentPane.add(Mute[i]);
		
			TaskY+=62;
			MuteY+=62;
		}
		scrollPane_Field[0].setBounds(12, 216, 66, 255);
		scrollPane_Field[1].setBounds(12, 155, 666, 65);
		scrollPane_Field[2].setBounds(75, 216, 603, 255);
		
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
		
		icon = new ImageIcon[4];
		for(int i=0; i<4; i++)
			icon[i] = new ImageIcon("image\\button"+Integer.toString(i+1)+".jpg");


		lbl_SelectInstrument = new JLabel("�Ǳ⼱�� :");
		lbl_SelectInstrument.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_SelectInstrument.setFont(HumanRoundHeadLine20);
		lbl_SelectInstrument.setBounds(12, 24, 100, 50);
		contentPane.add(lbl_SelectInstrument);
		
		btn_SelectToPiano = new JButton(icon[0]);
		btn_SelectToPiano.setText("�ǾƳ�");
		btn_SelectToPiano.setContentAreaFilled(false);
		btn_SelectToPiano.setFont(PureGothic12);
		btn_SelectToPiano.setForeground(Color.BLACK);
		btn_SelectToPiano.setBounds(125, 20, 100, 50);
		contentPane.add(btn_SelectToPiano);
		
		btn_SelectToDrum = new JButton(icon[1]);
		btn_SelectToDrum.setText("�巳");
		btn_SelectToDrum.setContentAreaFilled(false);
		btn_SelectToDrum.setFont(PureGothic12);
		btn_SelectToDrum.setBounds(238, 20, 100, 50);
		contentPane.add(btn_SelectToDrum);
		
		btn_SelectToGuitar = new JButton(icon[2]);
		btn_SelectToGuitar.setText("��Ÿ");
		btn_SelectToGuitar.setContentAreaFilled(false);
		btn_SelectToGuitar.setFont(PureGothic12);
		btn_SelectToGuitar.setBounds(351, 20, 100, 50);
		contentPane.add(btn_SelectToGuitar);
		
		btn_SelectToBase = new JButton(icon[3]);
		btn_SelectToBase.setText("���̽�");
		btn_SelectToBase.setContentAreaFilled(false);
		btn_SelectToBase.setFont(PureGothic12);
		btn_SelectToBase.setBounds(464, 20, 100, 50);
		contentPane.add(btn_SelectToBase);
		
		btn_start = new JButton("���ֽ���");
		btn_start.setBackground(bgcolor);
		btn_start.setFont(HumanRoundHeadLine12);
		btn_start.setBounds(681, 249, 97, 33);
		contentPane.add(btn_start);
		
		btn_init = new JButton("�ʱ�ȭ");
		btn_init.setBackground(bgcolor);
		btn_init.setFont(HumanRoundHeadLine12);
		btn_init.setBounds(681, 299, 97, 33);
		contentPane.add(btn_init);
	
		btn_erase = new JButton("�������");
		btn_erase.setBackground(bgcolor);
		btn_erase.setFont(HumanRoundHeadLine12);
		btn_erase.setBounds(681, 349, 97, 33);
		contentPane.add(btn_erase);
		
		btn_KeyboardPlay = new JButton("Ű���忬��");
		btn_KeyboardPlay.setBackground(bgcolor);
		btn_KeyboardPlay.setFont(HumanRoundHeadLine12);
		btn_KeyboardPlay.setBounds(681, 399, 97, 33);
		contentPane.add(btn_KeyboardPlay);

		btn_BankSave = new JButton("��ũ ����");
		btn_BankSave.setBackground(Color.WHITE);
		btn_BankSave.setFont(PureGothic12);
		btn_BankSave.setBounds(125, 83, 100, 25);
		contentPane.add(btn_BankSave);
		
		btn_BankListen = new JButton("��ũ ���");
		btn_BankListen.setBackground(Color.WHITE);
		btn_BankListen.setFont(PureGothic12);
		btn_BankListen.setBounds(125, 120, 100, 25);
		contentPane.add(btn_BankListen);
		
		btn_RhythmListen = new JButton("���� ���");
		btn_RhythmListen.setBackground(Color.WHITE);
		btn_RhythmListen.setFont(PureGothic12);
		btn_RhythmListen.setBounds(351, 83, 100, 25);
		contentPane.add(btn_RhythmListen);
		
		btn_RhythmInsert = new JButton("���� �Է�");
		btn_RhythmInsert.setBackground(Color.WHITE);
		btn_RhythmInsert.setFont(PureGothic12);
		btn_RhythmInsert.setBounds(351, 120, 100, 25);
		contentPane.add(btn_RhythmInsert);
		
		btn_ChordListen = new JButton("�ڵ� ���");
		btn_ChordListen.setBackground(Color.WHITE);
		btn_ChordListen.setFont(PureGothic12);
		btn_ChordListen.setBounds(577, 83, 100, 25);
		contentPane.add(btn_ChordListen);
		
		btn_ChordInsert = new JButton("�ڵ� �Է�");
		btn_ChordInsert.setBackground(Color.WHITE);
		btn_ChordInsert.setFont(PureGothic12);
		btn_ChordInsert.setBounds(577, 120, 100, 25);
		contentPane.add(btn_ChordInsert);
		
		btn_SaveScore = new JButton("���� ����");
		btn_SaveScore.setBackground(SystemColor.window);
		btn_SaveScore.setFont(PureGothic12);
		btn_SaveScore.setBounds(681, 155, 97, 23);
		contentPane.add(btn_SaveScore);
		
		btn_OpenScore = new JButton("���� ����");
		btn_OpenScore.setBackground(SystemColor.window);
		btn_OpenScore.setFont(PureGothic12);
		btn_OpenScore.setBounds(681, 188, 97, 23);
		contentPane.add(btn_OpenScore);

		swtch = new Swtch[6];
		for(int i=0; i<6; i++)
			swtch[i] = new Swtch();
		//swtch[0] = �ǾƳ�ַ�
		//swtch[1] = �巳�ַ�
		//swtch[2] = ��Ÿ�ַ�
		//swtch[3] = ���̽��ַ�
		//swtch[4] = ��ũ���
		//swtch[5] = ���ֽ���
	}
}

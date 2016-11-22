import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
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
public class Orpheus extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTable table_Field, table_Kind, table_Beat;
	private JTable table_TaskPiano, table_TaskDrum, table_TaskGuitar, table_TaskBase;
	private JScrollPane scrollPane_Field, scrollPane_Kind, scrollPane_Beat;
	private JScrollPane scrollPane_TaskPiano, scrollPane_TaskKeyDrum, scrollPane_TaskGuitar, scrollPane_TaskBase;
	private JComboBox BeatSet, BankChoice, RhythmChoice, RootChord, ChildChord;
	private JTextField BPMSet;
	
	private JLabel lbl_SelectInstrument, lbl_SelectBPM, lbl_SelectBeatSet;
	private JCheckBox Mute1, Mute2, Mute3, Mute4;
	private JButton btn_start, btn_erase, btn_SelectToPiano, btn_SelectToDrum, btn_SelectToGuitar, btn_SelectToBase;
	private JButton btn_BankSave, btn_BankListen, btn_RhythmInsert, btn_RhythmListen, btn_ChordInsert, btn_ChordListen;
	private JButton btn_PianoSolo, btn_DrumSolo, btn_GuitarSolo, btn_BaseSolo;
	private JButton Keyboard;

	private SettingToKind STK_Piano, STK_Drum, STK_Guitar, STK_Base;
	private SettingToField STF_Piano, STF_Drum, STF_Guitar, STF_Base;
	//private CmbBoxField STF_Piano, STF_Guitar, STF_Base;
	//private ChkBoxField STF_Drum;
	private BeatField STB_Piano, STB_Drum, STB_Guitar, STB_Base;
	private TaskField STT_Piano, STT_Drum, STT_Guitar, STT_Base; 
	
	private SaveBank Bank_Piano, Bank_Drum, Bank_Guitar, Bank_Base;
	//private CmbBoxBank Bank_Piano, Bank_Guitar, Bank_Base;
	//private ChkBoxBank Bank_Drum;
	
	private String[] BeatList = {"2/2", "2/4", "3/4", "4/4", "-----", "6/8", "9/8", "12/8", "-----", "7/4", "11/4", "5/4"};
	private String[] RhythmList = {"1","2","3","4"};
	private String[] RootChordList = {"C", "D", "E", "F", "G", "A", "B"};
	private String[] ChildChordList = {"M", "m", "7", "M7", "m7", "sus4", "dim"};
	
	private String[] Piano_tones = {"", "1", "2", "3"};
	private String[] Guitar_tones = {"", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
	
	private FileOpen files;
	
	private Input_GuitarCode Code;
	private PlayCode CodePlay;
	private int direction;
	private static Orpheus ui;
	private static Play bankPlay;
	private static Play[] taskPlay;

	public static void main(String[] args) {
		ui = new Orpheus();
		
		//뱅크듣기
		bankPlay = new Play(ui);
		bankPlay.MuteDisable();
		bankPlay.singleSet();
		
		taskPlay = new Play[4];
		for(int i=0; i<4; i++)
			taskPlay[i] = new Play(ui);
		
		bankPlay.setDaemon(true);
		bankPlay.ThreadStart();
		
		taskPlay[0].setDaemon(true);
		taskPlay[0].ThreadStart();
		
		taskPlay[1].setDaemon(true);
		taskPlay[1].ThreadStart();
		
		taskPlay[2].setDaemon(true);
		taskPlay[2].ThreadStart();
		
//		taskPlay[3].setDaemon(true);
//		taskPlay[3].ThreadStart();
	}

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
		
		Code = new Input_GuitarCode(files.getGuitarCode());
		CodePlay = new PlayCode(files.getGuitarCode(), files.getGuitarFiles());
		
		//STB = Setting to Beat
		STB_Piano = new BeatField();
		STB_Drum = new BeatField();
		STB_Guitar = new BeatField();
		STB_Base = new BeatField();
		
		STT_Piano = new TaskField("피아노");
		STT_Drum = new TaskField("드럼");
		STT_Guitar = new TaskField("기타");
		STT_Base = new TaskField("베이스");
		
		table_Beat = new JTable();
		table_Beat.setBackground(Color.WHITE);
		table_Beat.setForeground(new Color(0, 0, 0));
		table_Beat.setFont(PureGothic);
		table_Beat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_Beat = new JScrollPane(table_Beat);
		scrollPane_Beat.setBounds(12, 155, 703, 65);
		scrollPane_Beat.getViewport().setBackground(Color.WHITE);
		contentPane.add(scrollPane_Beat);
		
		//STF = Setting to Field
		STF_Piano = new CmbBoxField(STB_Piano, table_Beat, files.getPianoSoundNames().length, Piano_tones);
		STF_Drum = new ChkBoxField(STB_Drum, table_Beat, files.getDrumSoundNames().length);
		STF_Guitar = new CmbBoxField(STB_Guitar, table_Beat, files.getGuitarSoundNames().length, Guitar_tones);
		STF_Base = new CmbBoxField(STB_Base, table_Beat, files.getBaseSoundNames().length, Guitar_tones);
		
		table_Field = new JTable();
		table_Field.setFont(PureGothic);
		table_Field.setForeground(new Color(0, 0, 0));
		table_Field.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_Field = new JScrollPane(table_Field);
		scrollPane_Field.setBounds(75, 220, 640, 251);
		scrollPane_Field.getViewport().setBackground(Color.WHITE);
		contentPane.add(scrollPane_Field);
		
		//STK = Setting to Title
		STK_Piano = new SettingToKind(files.getPianoSoundNames());
		STK_Drum = new SettingToKind(files.getDrumSoundNames());
		STK_Guitar = new SettingToKind(files.getGuitarSoundNames());
		STK_Base = new SettingToKind(files.getBaseSoundNames());
		
		table_Kind = new JTable();
		table_Kind.setFont(PureGothic);
		table_Kind.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
		scrollPane_Kind = new JScrollPane(table_Kind);
		scrollPane_Kind.setBounds(12, 220, 63, 251);
		scrollPane_Kind.getViewport().setBackground(Color.WHITE);
		contentPane.add(scrollPane_Kind);

		//뱅크저장영역
		Bank_Piano = new SaveCmbBoxBank(STF_Piano);
		Bank_Drum = new SaveChkBoxBank(STF_Drum);
		Bank_Guitar = new SaveCmbBoxBank(STF_Guitar);
		Bank_Base = new SaveCmbBoxBank(STF_Base);

		//작업대기줄 영역
		table_TaskPiano = new JTable(STT_Piano.getModel());
		table_TaskPiano.setFont(PureGothic);
		table_TaskPiano.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		STT_Piano.setCellOption(table_TaskPiano);
		scrollPane_TaskPiano = new JScrollPane(table_TaskPiano);
		scrollPane_TaskPiano.setBounds(12, 469, 703, 65);
		scrollPane_TaskPiano.getViewport().setBackground(Color.WHITE);
		contentPane.add(scrollPane_TaskPiano);
		
		table_TaskDrum = new JTable(STT_Drum.getModel());
		table_TaskDrum.setFont(PureGothic);
		table_TaskDrum.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		STT_Drum.setCellOption(table_TaskDrum);
		scrollPane_TaskKeyDrum = new JScrollPane(table_TaskDrum);
		scrollPane_TaskKeyDrum.setBounds(12, 533, 703, 65);
		scrollPane_TaskKeyDrum.getViewport().setBackground(Color.WHITE);
		contentPane.add(scrollPane_TaskKeyDrum);
		
		table_TaskGuitar = new JTable(STT_Guitar.getModel());
		table_TaskGuitar.setFont(PureGothic);
		table_TaskGuitar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		STT_Guitar.setCellOption(table_TaskGuitar);
		scrollPane_TaskGuitar = new JScrollPane(table_TaskGuitar);
		scrollPane_TaskGuitar.setBounds(12, 598, 703, 65);
		scrollPane_TaskGuitar.getViewport().setBackground(Color.WHITE);
		contentPane.add(scrollPane_TaskGuitar);
		
		table_TaskBase = new JTable(STT_Base.getModel());
		table_TaskBase.setFont(PureGothic);
		table_TaskBase.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		STT_Base.setCellOption(table_TaskBase);
		scrollPane_TaskBase = new JScrollPane(table_TaskBase);
		scrollPane_TaskBase.setBounds(12, 662, 703, 65);
		scrollPane_TaskBase.getViewport().setBackground(Color.WHITE);
		contentPane.add(scrollPane_TaskBase);
		
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
		btn_start.setBounds(505, 737, 99, 33);
		contentPane.add(btn_start);
		
		btn_erase = new JButton("지우기");
		btn_erase.setBackground(bgcolor);
		btn_erase.setFont(HumanRoundHeadLine);
		btn_erase.addActionListener(this);
		btn_erase.setBounds(616, 737, 99, 33);
		contentPane.add(btn_erase);
	
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
		
		Mute1 = new JCheckBox("키보드뮤트");
		Mute1.setBackground(SystemColor.window);
		Mute1.setFont(PureGothic);
		Mute1.setBounds(718, 497, 97, 23);
		contentPane.add(Mute1);
		
		Mute2 = new JCheckBox("드럼뮤트");
		Mute2.setBackground(SystemColor.window);
		Mute2.setFont(PureGothic);
		Mute2.setBounds(718, 561, 97, 23);
		contentPane.add(Mute2);
		
		Mute3 = new JCheckBox("기타뮤트");
		Mute3.setBackground(SystemColor.window);
		Mute3.setFont(PureGothic);
		Mute3.setBounds(718, 629, 97, 23);
		contentPane.add(Mute3);
		
		Mute4 = new JCheckBox("베이스뮤트");
		Mute4.setBackground(SystemColor.window);
		Mute4.setFont(PureGothic);
		Mute4.setBounds(718, 692, 97, 23);
		contentPane.add(Mute4);
	
		
		/*
		Keyboard = new JButton("키보드");
		Keyboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Keyboard kb = new Keyboard();
				Keyboard.addActionListener(kb);
			}
		});
		Keyboard.setBackground(SystemColor.window);
		Keyboard.setFont(godic);
		Keyboard.setBounds(718,100,48,42);
		contentPane.add(Keyboard);
*/
		setPiano();
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		
		switch(source.getText())
		{
		case "피아노" :
			setPiano();
			break;
			
		case "드럼" :
			setDrum();
			break;
			
		case "기타" :
			setGuitar();
			break;
			
		case "베이스" :
			setBase();
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
		}
	}
	
	public void inputCode()
	{
		Code.addCode(table_Field, RootChord.getSelectedIndex(), ChildChord.getSelectedIndex());
		STF_Guitar.addColumn(table_Field.getModel().getColumnCount()-1);
		STF_Guitar.setCellOption(table_Field);
	}
	
	public void playCode()
	{
		CodePlay.playCode(RootChord.getSelectedIndex(), ChildChord.getSelectedIndex());
	}
	
	public void setPiano()
	{
		table_Kind.setModel(STK_Piano.getModel());
		table_Kind.getColumnModel().getColumn(0).setPreferredWidth(60);
		table_Field.setModel(STF_Piano.getModel());
		STF_Piano.setCellOption(table_Field);
		
		table_Beat.setModel(STB_Piano.getModel());
		STB_Piano.setCellOption(table_Beat);
		
		setBankList(STT_Piano.getItemList().length-1);
		
		RootChord.setEnabled(false);
		ChildChord.setEnabled(false);
		
		RhythmChoice.setEnabled(false);
		btn_RhythmInsert.setEnabled(false);
		btn_RhythmListen.setEnabled(false);
		
		btn_ChordInsert.setEnabled(false);
		btn_ChordListen.setEnabled(false);
		
		direction = 1;
	}
	public void setDrum()
	{
		table_Kind.setModel(STK_Drum.getModel());
		table_Kind.getColumnModel().getColumn(0).setPreferredWidth(60);
		table_Field.setModel(STF_Drum.getModel());
		STF_Drum.setCellOption(table_Field);
		
		table_Beat.setModel(STB_Drum.getModel());
		STB_Drum.setCellOption(table_Beat);
		
		setBankList(STT_Drum.getItemList().length-1);
		
		RootChord.setEnabled(false);
		ChildChord.setEnabled(false);
		
		RhythmChoice.setEnabled(true);
		btn_RhythmInsert.setEnabled(true);
		btn_RhythmListen.setEnabled(true);
		
		btn_ChordInsert.setEnabled(false);
		btn_ChordListen.setEnabled(false);
		
		direction = 2;
	}
	public void setGuitar()
	{
		table_Kind.setModel(STK_Guitar.getModel());
		table_Kind.getColumnModel().getColumn(0).setPreferredWidth(60);
		table_Field.setModel(STF_Guitar.getModel());
		STF_Guitar.setCellOption(table_Field);
		
		table_Beat.setModel(STB_Guitar.getModel());
		STB_Guitar.setCellOption(table_Beat);
		
		setBankList(STT_Guitar.getItemList().length-1);
		
		RootChord.setEnabled(true);
		ChildChord.setEnabled(true);
		
		RhythmChoice.setEnabled(false);
		btn_RhythmInsert.setEnabled(false);
		btn_RhythmListen.setEnabled(false);
		
		btn_ChordInsert.setEnabled(true);
		btn_ChordListen.setEnabled(true);
		
		direction = 3;
	}
	public void setBase()
	{
		table_Kind.setModel(STK_Base.getModel());
		table_Kind.getColumnModel().getColumn(0).setPreferredWidth(60);
		table_Field.setModel(STF_Base.getModel());
		STF_Base.setCellOption(table_Field);
		
		table_Beat.setModel(STB_Base.getModel());
		STB_Base.setCellOption(table_Beat);
		
		setBankList(STT_Base.getItemList().length-1);
		
		
		RootChord.setEnabled(false);
		ChildChord.setEnabled(false);
		
		RhythmChoice.setEnabled(true);
		btn_RhythmInsert.setEnabled(true);
		btn_RhythmListen.setEnabled(true);
		
		btn_ChordInsert.setEnabled(false);
		btn_ChordListen.setEnabled(false);
		
		direction = 4;
	}
	public void initPiano()
	{
		STF_Piano.Init();
		STF_Piano.setCellOption(table_Field);
		
		STB_Piano.Init();
		STB_Piano.setCellOption(table_Beat);
	}
	public void initDrum()
	{
		STF_Drum.Init();
		STF_Drum.setCellOption(table_Field);
		
		STB_Drum.Init();
		STB_Drum.setCellOption(table_Beat);
	}
	public void initGuitar()
	{
		STF_Guitar.Init();
		STF_Guitar.setCellOption(table_Field);
		
		STB_Guitar.Init();
		STB_Guitar.setCellOption(table_Beat);
	}
	public void initBase()
	{
		STF_Base.Init();
		STF_Base.setCellOption(table_Field);
		
		STF_Base.Init();
		STF_Base.setCellOption(table_Beat);
	}
	
	public void setBankList(int size)
	{
		BankChoice.removeAllItems();
		BankChoice.addItem("");
		for(int i=1; i<=size; i++)
			BankChoice.addItem(Integer.toString(i));
	}
	public boolean OutofBeat(BeatField BeatField)
	{
		if(BeatField.out_max(table_Beat) < RestTimeSetup.music_score)
		{
			JOptionPane.showMessageDialog(null, "설정한 박자가 부족합니다.");
			return false;
		}
		if(BeatField.out_max(table_Beat) > RestTimeSetup.music_score)
		{
			JOptionPane.showMessageDialog(null, "설정한 박자가 초과하였습니다.");
			return false;
		}
		return true;
	}
	public void saveBank()
	{
		RestTimeSetup.getRestTime(BPMSet.getText(), (String)BeatSet.getSelectedItem());
		switch(direction)
		{
		case 1:
			if(!OutofBeat(STB_Piano))
				return;
			
			STF_Piano.BankList.add(Bank_Piano.getBank(STB_Piano.getBeatResult(), RestTimeSetup.result));
			//Bank_Piano.bankPrint(STF_Piano.BankList.getLast());
				
			initPiano();
			setBankList(STF_Piano.BankList.size()-1);
			STT_Piano.reflash(STF_Piano.BankList.size()-1);	
			break;
			
		case 2:
			if(!OutofBeat(STB_Drum))
				return;
			
			STF_Drum.BankList.add(Bank_Drum.getBank(STB_Drum.getBeatResult(), RestTimeSetup.result));
			//Bank_Drum.bankPrint(STF_Drum.BankList.getLast());
				
			initDrum();
			setBankList(STF_Drum.BankList.size()-1);
			STT_Drum.reflash(STF_Drum.BankList.size()-1);

			break;
			
		case 3:
			if(!OutofBeat(STB_Guitar))
				return;
			
			STF_Guitar.BankList.add(Bank_Guitar.getBank(STB_Guitar.getBeatResult(), RestTimeSetup.result));
			//Bank_Guitar.bankPrint(STF_Guitar.BankList.getLast());
			
			initGuitar();
			setBankList(STF_Guitar.BankList.size()-1);
			STT_Guitar.reflash(STF_Guitar.BankList.size()-1);
			break;
			
		case 4:
			if(!OutofBeat(STB_Base))
				return;
			
			STF_Base.BankList.add(Bank_Base.getBank(STB_Base.getBeatResult(), RestTimeSetup.result));
			//Bank_Piano.bankPrint();
			
			initBase();
			setBankList(STF_Base.BankList.size()-1);
			STT_Base.reflash(STF_Base.BankList.size()-1);
			break;
		}
	}
	public void ListenBank()
	{
		int BankNum = BankChoice.getSelectedIndex();
		switch(direction)
		{
		case 1:
			bankPlay.setBank(STF_Piano.BankList.get(BankNum), files.getPianoFiles());
			break;
			
		case 2:
			bankPlay.setBank(STF_Drum.BankList.get(BankNum), files.getDrumFiles());
			break;
			
		case 3:
			bankPlay.setBank(STF_Guitar.BankList.get(BankNum), files.getGuitarFiles());
			break;
			
		case 4:
			//베이스 들어갈 곳
			break;
		}
		bankPlay.action();
	}
	public void ListenSolo(int idx)
	{
		switch(idx)
		{
		case 0:
			taskPlay[0].multySet(table_TaskPiano, STF_Piano.BankList, files.getPianoFiles(), Mute1);
			break;
		
		case 1:
			taskPlay[1].multySet(table_TaskDrum, STF_Drum.BankList, files.getDrumFiles(), Mute2);
			break;
		
		case 2:
			taskPlay[2].multySet(table_TaskGuitar, STF_Guitar.BankList, files.getGuitarFiles(), Mute3);
			break;
			
		case 3:
			//taskPlay[3].multySet(table_TaskBase, STF_Base.BankList, files.getBaseFiles());
			break;
		}
		taskPlay[idx].action();
	}
	
	public void musicQ()
	{
		//연주시작 메소드
		taskPlay[0].multySet(table_TaskPiano, STF_Piano.BankList, files.getPianoFiles(), Mute1);
		taskPlay[1].multySet(table_TaskDrum, STF_Drum.BankList, files.getDrumFiles(), Mute2);
		taskPlay[2].multySet(table_TaskGuitar, STF_Guitar.BankList, files.getGuitarFiles(), Mute3);
		//taskPlay[3].multySet(table_TaskBase, STF_Base.BankList, files.getBaseFiles());
		taskPlay[0].action();
		taskPlay[1].action();
		taskPlay[2].action();
		//taskPlay[3].action();
	}
	public void init()
	{
		switch(direction)
		{
		case 1:
			initPiano();
			break;
			
		case 2:
			initDrum();
			break;
			
		case 3:
			initGuitar();
			break;
			
		case 4:
			initBase();
			break;
		}
	}
	public JButton getBankListenButton()
	{
		return btn_BankListen;
	}
}

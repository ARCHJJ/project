import java.awt.Color;
import java.awt.Component;
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
	private JButton btn_start, btn_erase, btn_SelectToPiano, btn_SelectToDrum, btn_SelectToGuitar, btn_SelectToBase;
	private JButton BankSave, BankListen, RhythmInsert, RhythmListen, ChordInsert, ChordListen;
	private JButton Mute1, Mute2, Mute3, Mute4;
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
	
	private input_GuitarCode Code;
	private PlayCode CodePlay;
	private int direction;
	private boolean wait = true;
	private static Orpheus ui;
	private static Play bankPlay;
	private static Play[] taskPlay;
	public static void main(String[] args) {
		ui = new Orpheus();
		
		//뱅크듣기
		bankPlay = new Play(ui);
		taskPlay = new Play[4];
		for(int i=0; i<4; i++)
			taskPlay[i] = new Play(ui);
		
		bankPlay.start();
//		taskPlay[0].start();
//		taskPlay[1].start();
//		taskPlay[2].start();
//		taskPlay[3].start();
		
	}
	public synchronized Play stopThread() throws InterruptedException
	{
		while(wait)
			wait();
	
		wait = true;
		return Orpheus.bankPlay;
	}
	
	public synchronized void goThread()
	{
		wait = false;
		notify();
	}
	public JButton getBankListenButton()
	{
		return BankListen;
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
			
		lbl_SelectBeatSet = new JLabel("박자 : ");
		lbl_SelectBeatSet.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbl_SelectBeatSet.setBounds(595, 10, 57, 15);
		contentPane.add(lbl_SelectBeatSet);

		BeatSet = new JComboBox(BeatList);
		BeatSet.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
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
		lbl_SelectBPM.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbl_SelectBPM.setBounds(701, 10, 57, 15);
		contentPane.add(lbl_SelectBPM);
		
		BPMSet = new JTextField("100");
		BPMSet.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		BPMSet.setBounds(742, 6, 60, 23);
		contentPane.add(BPMSet);
		
		//FileOpen
		files = new FileOpen();
		
		Code = new input_GuitarCode(files.getGuitarCode());
		
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
		table_Beat.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
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
		table_Field.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
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
		table_Kind.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
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
		table_TaskPiano.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		table_TaskPiano.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		STT_Piano.setCellOption(table_TaskPiano);
		scrollPane_TaskPiano = new JScrollPane(table_TaskPiano);
		scrollPane_TaskPiano.setBounds(12, 469, 703, 65);
		scrollPane_TaskPiano.getViewport().setBackground(Color.WHITE);
		contentPane.add(scrollPane_TaskPiano);
		
		table_TaskDrum = new JTable(STT_Drum.getModel());
		table_TaskDrum.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		table_TaskDrum.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		STT_Drum.setCellOption(table_TaskDrum);
		scrollPane_TaskKeyDrum = new JScrollPane(table_TaskDrum);
		scrollPane_TaskKeyDrum.setBounds(12, 533, 703, 65);
		scrollPane_TaskKeyDrum.getViewport().setBackground(Color.WHITE);
		contentPane.add(scrollPane_TaskKeyDrum);
		
		table_TaskGuitar = new JTable(STT_Guitar.getModel());
		table_TaskGuitar.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		table_TaskGuitar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		STT_Guitar.setCellOption(table_TaskGuitar);
		scrollPane_TaskGuitar = new JScrollPane(table_TaskGuitar);
		scrollPane_TaskGuitar.setBounds(12, 598, 703, 65);
		scrollPane_TaskGuitar.getViewport().setBackground(Color.WHITE);
		contentPane.add(scrollPane_TaskGuitar);
		
		table_TaskBase = new JTable(STT_Base.getModel());
		table_TaskBase.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		table_TaskBase.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		STT_Base.setCellOption(table_TaskBase);
		scrollPane_TaskBase = new JScrollPane(table_TaskBase);
		scrollPane_TaskBase.setBounds(12, 662, 703, 65);
		scrollPane_TaskBase.getViewport().setBackground(Color.WHITE);
		contentPane.add(scrollPane_TaskBase);
		
		lbl_SelectInstrument = new JLabel("악기선택");
		lbl_SelectInstrument.setFont(new Font("휴먼옛체", Font.PLAIN, 12));
		lbl_SelectInstrument.setBounds(12, 12, 57, 15);
		contentPane.add(lbl_SelectInstrument);
		
		btn_SelectToPiano = new JButton("피아노");
		btn_SelectToPiano.setBackground(Color.WHITE);
		btn_SelectToPiano.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		btn_SelectToPiano.setForeground(Color.BLACK);
		btn_SelectToPiano.addActionListener(this);
		btn_SelectToPiano.setBounds(12, 40, 99, 25);
		contentPane.add(btn_SelectToPiano);
		
		btn_SelectToDrum = new JButton("드럼");
		btn_SelectToDrum.setBackground(Color.WHITE);
		btn_SelectToDrum.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		btn_SelectToDrum.addActionListener(this);
		btn_SelectToDrum.setBounds(123, 40, 99, 25);
		contentPane.add(btn_SelectToDrum);
		
		btn_SelectToGuitar = new JButton("기타");
		btn_SelectToGuitar.setBackground(Color.WHITE);
		btn_SelectToGuitar.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		btn_SelectToGuitar.addActionListener(this);
		btn_SelectToGuitar.setBounds(234, 40, 99, 25);
		contentPane.add(btn_SelectToGuitar);
		
		btn_SelectToBase = new JButton("베이스");
		btn_SelectToBase.setBackground(Color.WHITE);
		btn_SelectToBase.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		btn_SelectToBase.addActionListener(this);
		btn_SelectToBase.setBounds(345, 40, 99, 25);
		contentPane.add(btn_SelectToBase);
		
		//폰트
		Font headline = new Font("휴먼둥근헤드라인", Font.PLAIN, 12);
		Font godic = new Font("맑은 고딕", Font.PLAIN, 12);
		Color bgcolor = new Color(255, 255, 255);
		
		btn_start = new JButton("연주시작");
		btn_start.setBackground(bgcolor);
		btn_start.setFont(headline);
		btn_start.addActionListener(this);
		btn_start.setBounds(505, 737, 99, 33);
		contentPane.add(btn_start);
		
		btn_erase = new JButton("지우기");
		btn_erase.setBackground(bgcolor);
		btn_erase.setFont(headline);
		btn_erase.addActionListener(this);
		btn_erase.setBounds(616, 737, 99, 33);
		contentPane.add(btn_erase);
	
		BankSave = new JButton("뱅크 저장");
		BankSave.setBackground(Color.WHITE);
		BankSave.setFont(godic);
		BankSave.addActionListener(this);
		BankSave.setBounds(124, 83, 100, 25);
		contentPane.add(BankSave);
		
		BankListen = new JButton("뱅크 듣기");
		BankListen.setBackground(Color.WHITE);
		BankListen.setFont(godic);
		BankListen.addActionListener(this);
		BankListen.setBounds(123, 120, 100, 25);
		contentPane.add(BankListen);
		
		RhythmListen = new JButton("리듬 듣기");
		RhythmListen.setBackground(Color.WHITE);
		RhythmListen.setFont(godic);
		RhythmListen.setBounds(345, 84, 120, 25);
		contentPane.add(RhythmListen);
		
		RhythmInsert = new JButton("리듬 입력");
		RhythmInsert.setBackground(Color.WHITE);
		RhythmInsert.setFont(godic);
		RhythmInsert.setBounds(345, 121, 120, 25);
		contentPane.add(RhythmInsert);
		
		ChordListen = new JButton("코드 듣기");
		ChordListen.setBackground(Color.WHITE);
		ChordListen.setFont(godic);
		ChordListen.addActionListener(this);
		ChordListen.setBounds(595, 84, 120, 25);
		contentPane.add(ChordListen);
		
		ChordInsert = new JButton("코드 입력");
		ChordInsert.setBackground(Color.WHITE);
		ChordInsert.setFont(godic);
		ChordInsert.addActionListener(this);
		ChordInsert.setBounds(595, 121, 120, 25);
		contentPane.add(ChordInsert);
		
		Mute1 = new JButton("M");
		Mute1.setBackground(SystemColor.window);
		Mute1.setFont(godic);
		Mute1.setBounds(718, 481, 48, 42);
		contentPane.add(Mute1);
		
		Mute2 = new JButton("M");
		Mute2.setBackground(SystemColor.window);
		Mute2.setFont(godic);
		Mute2.setBounds(718, 546, 48, 42);
		contentPane.add(Mute2);
		
		Mute3 = new JButton("M");
		Mute3.setBackground(SystemColor.window);
		Mute3.setFont(godic);
		Mute3.setBounds(718, 608, 48, 42);
		contentPane.add(Mute3);
		
		Mute4 = new JButton("M");
		Mute4.setBackground(SystemColor.window);
		Mute4.setFont(godic);
		Mute4.setBounds(718, 673, 48, 42);
		contentPane.add(Mute4);
		
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
		RhythmInsert.setEnabled(false);
		RhythmListen.setEnabled(false);
		
		ChordInsert.setEnabled(false);
		ChordListen.setEnabled(false);
		
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
		RhythmInsert.setEnabled(true);
		RhythmListen.setEnabled(true);
		
		ChordInsert.setEnabled(false);
		ChordListen.setEnabled(false);
		
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
		RhythmInsert.setEnabled(false);
		RhythmListen.setEnabled(false);
		
		ChordInsert.setEnabled(true);
		ChordListen.setEnabled(true);
		
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
		RhythmInsert.setEnabled(true);
		RhythmListen.setEnabled(true);
		
		ChordInsert.setEnabled(false);
		ChordListen.setEnabled(false);
		
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
	public void saveBank()
	{
		RestTimeSetup.getRestTime(BPMSet.getText(), (String)BeatSet.getSelectedItem());
		switch(direction)
		{
		case 1:
			
			if(STB_Piano.out_max(table_Beat) < RestTimeSetup.music_score)
			{
				JOptionPane.showMessageDialog(null, "설정한 박자가 부족합니다.");
			}
			
			else if(STB_Piano.out_max(table_Beat) > RestTimeSetup.music_score)
			{
				JOptionPane.showMessageDialog(null, "설정한 박자가 초과하였습니다.");
			}
			
			else
			{
				STF_Piano.BankList.add(Bank_Piano.getBank(STB_Piano.getBeatResult(), RestTimeSetup.result));
				//Bank_Piano.bankPrint(STF_Piano.BankList.getLast());
				
				initPiano();
				setBankList(STF_Piano.BankList.size()-1);
				STT_Piano.reflash(STF_Piano.BankList.size()-1);
			}
			
			break;
			
		case 2:
			
			if(STB_Drum.out_max(table_Beat) < RestTimeSetup.music_score)
			{
				JOptionPane.showMessageDialog(null, "설정한 박자가 부족합니다.");
			}
			
			else if(STB_Drum.out_max(table_Beat) > RestTimeSetup.music_score)
			{
				JOptionPane.showMessageDialog(null, "설정한 박자가 초과하였습니다.");
			}
			
			else
			{
				STF_Drum.BankList.add(Bank_Drum.getBank(STB_Drum.getBeatResult(), RestTimeSetup.result));
				//Bank_Drum.bankPrint(STF_Drum.BankList.getLast());
				
				initDrum();
				setBankList(STF_Drum.BankList.size()-1);
				STT_Drum.reflash(STF_Drum.BankList.size()-1);
			}
			
			break;
			
		case 3:
			
			if(STB_Guitar.out_max(table_Beat) < RestTimeSetup.music_score)
			{
				JOptionPane.showMessageDialog(null, "설정한 박자가 부족합니다.");
			}
			
			else if(STB_Guitar.out_max(table_Beat) > RestTimeSetup.music_score)
			{
				JOptionPane.showMessageDialog(null, "설정한 박자가 초과하였습니다.");
			}
			
			else
			{
				STF_Guitar.BankList.add(Bank_Guitar.getBank(STB_Guitar.getBeatResult(), RestTimeSetup.result));
				//Bank_Guitar.bankPrint(STF_Guitar.BankList.getLast());
				
				initGuitar();
				setBankList(STF_Guitar.BankList.size()-1);
				STT_Guitar.reflash(STF_Guitar.BankList.size()-1);
			}
			
			break;
			
		case 4:
			if(STB_Base.out_max(table_Beat) < RestTimeSetup.music_score)
			{
				JOptionPane.showMessageDialog(null, "설정한 박자가 부족합니다.");
			}
			
			else if(STB_Base.out_max(table_Beat) > RestTimeSetup.music_score)
			{
				JOptionPane.showMessageDialog(null, "설정한 박자가 초과하였습니다.");
			}
			
			else
			{
				STF_Base.BankList.add(Bank_Base.getBank(STB_Base.getBeatResult(), RestTimeSetup.result));
				//Bank_Piano.bankPrint();
				
				initBase();
				setBankList(STF_Base.BankList.size()-1);
				STT_Base.reflash(STF_Base.BankList.size()-1);
			}
			
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
		goThread();
	}
	public void musicQ()
	{
		//연주시작 메소드
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

}

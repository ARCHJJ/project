import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
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
	private String[] ChildChordList = {"X", "M", "m", "7", "M7", "m7", "sus4", "dim"};
	
	private String[] Piano_tones = {"", "1", "2", "3"};
	private String[] Guitar_tones = {"", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
	
	private FileOpen files;
	private Play play;
	private int direction;
	public Orpheus() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 1024, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		lbl_SelectBeatSet = new JLabel("박자 : ");
		lbl_SelectBeatSet.setBounds(694, 10, 57, 15);
		contentPane.add(lbl_SelectBeatSet);

		BeatSet = new JComboBox(BeatList);
		BeatSet.setSelectedItem("4/4");
		BeatSet.setBounds(735, 6, 55, 23);
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
		lbl_SelectBPM.setBounds(829, 10, 57, 15);
		contentPane.add(lbl_SelectBPM);
		
		BPMSet = new JTextField("100");
		BPMSet.setBounds(873, 6, 60, 23);
		contentPane.add(BPMSet);
		
		//FileOpen
		files = new FileOpen();
		
		//STB = Setting to Beat
		STB_Piano = new BeatField();
		STB_Drum = new BeatField();
		STB_Guitar = new BeatField();
		STB_Base = new BeatField();
		
		STT_Piano = new TaskField("키보드");
		STT_Drum = new TaskField("드럼");
		STT_Guitar = new TaskField("기타");
		STT_Base = new TaskField("베이스");
		
		table_Beat = new JTable();
		table_Beat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_Beat = new JScrollPane(table_Beat);
		scrollPane_Beat.setBounds(12, 155, 700, 65);
		contentPane.add(scrollPane_Beat);
		
		//STF = Setting to Field
		STF_Piano = new CmbBoxField(STB_Piano, table_Beat, files.getPianoSoundNames().length, Piano_tones);
		STF_Drum = new ChkBoxField(STB_Drum, table_Beat, files.getDrumSoundNames().length);
		STF_Guitar = new CmbBoxField(STB_Guitar, table_Beat, files.getGuitarSoundNames().length, Guitar_tones);
		STF_Base = new CmbBoxField(STB_Base, table_Beat, files.getBaseSoundNames().length, Guitar_tones);
		
		table_Field = new JTable();
		table_Field.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_Field = new JScrollPane(table_Field);
		scrollPane_Field.setBounds(72, 220, 640, 251);
		contentPane.add(scrollPane_Field);
		
		//STK = Setting to Title
		STK_Piano = new SettingToKind(files.getPianoSoundNames());
		STK_Drum = new SettingToKind(files.getDrumSoundNames());
		STK_Guitar = new SettingToKind(files.getGuitarSoundNames());
		STK_Base = new SettingToKind(files.getBaseSoundNames());
		
		table_Kind = new JTable();
		scrollPane_Kind = new JScrollPane(table_Kind);
		scrollPane_Kind.setBounds(12, 220, 66, 251);
		contentPane.add(scrollPane_Kind);

		//뱅크저장영역
		int restTime = RestTimeSetup.getRestTime(BPMSet.getText(), (String)BeatSet.getSelectedItem());
		Bank_Piano = new SaveCmbBoxBank(STF_Piano, restTime);
		Bank_Drum = new SaveChkBoxBank(STF_Drum, restTime);
		Bank_Guitar = new SaveCmbBoxBank(STF_Guitar, restTime);
		Bank_Base = new SaveCmbBoxBank(STF_Base, restTime);

		//뱅크듣기
		play = new Play();
		
		//작업대기줄 영역
		table_TaskPiano = new JTable(STT_Piano.getModel());
		table_TaskPiano.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		STT_Piano.setCellOption(table_TaskPiano);
		scrollPane_TaskPiano = new JScrollPane(table_TaskPiano);
		scrollPane_TaskPiano.setBounds(12, 469, 640, 65);
		contentPane.add(scrollPane_TaskPiano);
		
		table_TaskDrum = new JTable(STT_Drum.getModel());
		table_TaskDrum.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		STT_Drum.setCellOption(table_TaskDrum);
		scrollPane_TaskKeyDrum = new JScrollPane(table_TaskDrum);
		scrollPane_TaskKeyDrum.setBounds(12, 533, 640, 65);
		contentPane.add(scrollPane_TaskKeyDrum);
		
		table_TaskGuitar = new JTable(STT_Guitar.getModel());
		table_TaskGuitar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		STT_Guitar.setCellOption(table_TaskGuitar);
		scrollPane_TaskGuitar = new JScrollPane(table_TaskGuitar);
		scrollPane_TaskGuitar.setBounds(12, 598, 640, 65);
		contentPane.add(scrollPane_TaskGuitar);
		
		table_TaskBase = new JTable(STT_Base.getModel());
		table_TaskBase.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		STT_Base.setCellOption(table_TaskBase);
		scrollPane_TaskBase = new JScrollPane(table_TaskBase);
		scrollPane_TaskBase.setBounds(12, 662, 640, 65);
		contentPane.add(scrollPane_TaskBase);
		
		lbl_SelectInstrument = new JLabel("악기선택");
		lbl_SelectInstrument.setBounds(12, 10, 57, 15);
		contentPane.add(lbl_SelectInstrument);
		
		btn_SelectToPiano = new JButton("키보드");
		btn_SelectToPiano.addActionListener(this);
		btn_SelectToPiano.setBounds(12, 40, 99, 25);
		contentPane.add(btn_SelectToPiano);
		
		btn_SelectToDrum = new JButton("드럼");
		btn_SelectToDrum.addActionListener(this);
		btn_SelectToDrum.setBounds(123, 40, 99, 25);
		contentPane.add(btn_SelectToDrum);
		
		btn_SelectToGuitar = new JButton("기타");
		btn_SelectToGuitar.addActionListener(this);
		btn_SelectToGuitar.setBounds(234, 40, 99, 25);
		contentPane.add(btn_SelectToGuitar);
		
		btn_SelectToBase = new JButton("베이스");
		btn_SelectToBase.addActionListener(this);
		btn_SelectToBase.setBounds(345, 40, 99, 25);
		contentPane.add(btn_SelectToBase);
		

		btn_start = new JButton("연주시작");
		btn_start.setBounds(12, 738, 99, 25);
		contentPane.add(btn_start);
		
		btn_erase = new JButton("지우기");
		btn_erase.addActionListener(this);
		btn_erase.setBounds(123, 738, 99, 25);
		contentPane.add(btn_erase);
	
		BankSave = new JButton("뱅크 저장");
		BankSave.addActionListener(this);
		BankSave.setBounds(123, 85, 100, 23);
		contentPane.add(BankSave);
		
		BankListen = new JButton("뱅크 듣기");
		BankListen.addActionListener(this);
		BankListen.setBounds(123, 120, 100, 25);
		contentPane.add(BankListen);
		
		RhythmListen = new JButton("리듬 듣기");
		RhythmListen.setBounds(345, 84, 120, 25);
		contentPane.add(RhythmListen);
		
		RhythmInsert = new JButton("리듬 입력");
		RhythmInsert.setBounds(345, 121, 120, 25);
		contentPane.add(RhythmInsert);
		
		ChordListen = new JButton("코드 듣기");
		ChordListen.setBounds(592, 84, 120, 25);
		contentPane.add(ChordListen);
		
		ChordInsert = new JButton("코드 입력");
		ChordInsert.setBounds(592, 121, 120, 25);
		contentPane.add(ChordInsert);
		
		Mute1 = new JButton("M");
		Mute1.setBounds(657, 496, 55, 25);
		contentPane.add(Mute1);
		
		Mute2 = new JButton("M");
		Mute2.setBounds(657, 559, 55, 25);
		contentPane.add(Mute2);
		
		Mute3 = new JButton("M");
		Mute3.setBounds(657, 620, 55, 25);
		contentPane.add(Mute3);
		
		Mute4 = new JButton("M");
		Mute4.setBounds(657, 688, 55, 25);
		contentPane.add(Mute4);
		
//		PlayBank1 = new LinkedList<Note>();
//		PlayBank2 = new LinkedList<Note>();
//
//		Play test = new Play(PlayBank1, Pianofile);
//		Play test2 = new Play(PlayBank2, Drumfile);

		setPiano();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		
		switch(source.getText())
		{
		case "키보드" :
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
		case "지우기":
			init();
			break;
		}
	}
	public void setPiano()
	{
		table_Kind.setModel(STK_Piano.getModel());
		table_Field.setModel(STF_Piano.getModel());
		STF_Piano.setCellOption(table_Field);
		
		table_Beat.setModel(STB_Piano.getModel());
		STB_Piano.setCellOption(table_Beat);
		
		setBankList(STT_Piano.getItemList().length-1);
		
		RootChord.setEnabled(false);
		ChildChord.setEnabled(false);
		
		RhythmInsert.setEnabled(false);
		RhythmListen.setEnabled(false);
		
		ChordInsert.setEnabled(false);
		ChordListen.setEnabled(false);
		
		direction = 1;
	}
	public void setDrum()
	{
		table_Kind.setModel(STK_Drum.getModel());
		table_Field.setModel(STF_Drum.getModel());
		STF_Drum.setCellOption(table_Field);
		
		table_Beat.setModel(STB_Drum.getModel());
		STB_Drum.setCellOption(table_Beat);
		
		setBankList(STT_Drum.getItemList().length-1);
		
		RootChord.setEnabled(false);
		ChildChord.setEnabled(false);
		
		RhythmInsert.setEnabled(true);
		RhythmListen.setEnabled(true);
		
		ChordInsert.setEnabled(false);
		ChordListen.setEnabled(false);
		
		direction = 2;
	}
	public void setGuitar()
	{
		table_Kind.setModel(STK_Guitar.getModel());
		table_Field.setModel(STF_Guitar.getModel());
		STF_Guitar.setCellOption(table_Field);
		
		table_Beat.setModel(STB_Guitar.getModel());
		STB_Guitar.setCellOption(table_Beat);
		
		setBankList(STT_Guitar.getItemList().length-1);
		
		RootChord.setEnabled(true);
		ChildChord.setEnabled(true);
		
		RhythmInsert.setEnabled(false);
		RhythmListen.setEnabled(false);
		
		ChordInsert.setEnabled(true);
		ChordListen.setEnabled(true);
		
		direction = 3;
	}
	public void setBase()
	{
		table_Kind.setModel(STK_Base.getModel());
		table_Field.setModel(STF_Base.getModel());
		STF_Base.setCellOption(table_Field);
		
		table_Beat.setModel(STB_Base.getModel());
		STB_Base.setCellOption(table_Beat);
		
		setBankList(STT_Base.getItemList().length-1);
		
		RootChord.setEnabled(false);
		ChildChord.setEnabled(false);
		
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
		switch(direction)
		{
		case 1:
			STF_Piano.BankList.add(Bank_Piano.getBank(STB_Piano.getBeatResult()));
			//((SaveCmbBoxBank) Bank_Piano).bankPrint(STF_Piano.BankList.getLast());
			
			initPiano();
			setBankList(STF_Piano.BankList.size()-1);
			STT_Piano.reflash(STF_Piano.BankList.size()-1);
			break;
			
		case 2:
			STF_Drum.BankList.add(Bank_Drum.getBank(STB_Drum.getBeatResult()));
			//Bank_Drum.bankPrint();
			
			initDrum();
			setBankList(STF_Drum.BankList.size()-1);
			STT_Drum.reflash(STF_Drum.BankList.size()-1);
			break;
			
		case 3:
			STF_Guitar.BankList.add(Bank_Guitar.getBank(STB_Guitar.getBeatResult()));
			//Bank_Piano.bankPrint();
			
			initGuitar();
			setBankList(STF_Guitar.BankList.size()-1);
			STT_Guitar.reflash(STF_Guitar.BankList.size()-1);
			break;
			
		case 4:
			STF_Base.BankList.add(Bank_Base.getBank(STB_Base.getBeatResult()));
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
		System.out.println(BankNum);
		switch(direction)
		{
		case 1:
			play.playtest(STF_Piano.BankList.get(BankNum), files.getPianoFiles());
			break;
			
		case 2:
			
			break;
			
		case 3:
		
			break;
			
		case 4:
	
			break;
		}		
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
	
	public static void main(String[] args) {
		Orpheus frame = new Orpheus();
		frame.setVisible(true);
	}
}

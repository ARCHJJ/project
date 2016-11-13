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
	private JTable table_TaskKeyboard, table_TaskDrum, table_TaskGuitar, table_TaskBase;
	private JScrollPane scrollPane_Field, scrollPane_Kind, scrollPane_Beat;
	private JScrollPane scrollPane_TaskKeyboard, scrollPane_TaskKeyDrum, scrollPane_TaskGuitar, scrollPane_TaskBase;
	private JComboBox BeatSet, BankChoice, RhythmChoice, RootChord, ChildChord;
	private JTextField BPMSet;
	
	private JLabel lbl_SelectInstrument, lbl_SelectBPM, lbl_SelectBeatSet;
	private JButton btn_start, btn_erase, btn_SelectTokeyboard, btn_SelectTodrum, btn_SelectToguitar, btn_SelectTobase;
	private JButton BankSave, BankListen, RhythmInsert, RhythmListen, ChordInsert, ChordListen;
	private JButton Mute1, Mute2, Mute3, Mute4;

	private SettingToKind STK_keyboard, STK_drum, STK_guitar, STK_base;
	private SettingToField STF_keyboard, STF_drum, STF_guitar, STF_base;
	//private CmbBoxField STF_keyboard, STF_guitar, STF_base;
	//private ChkBoxField STF_drum;
	private BeatField STB_keyboard, STB_drum, STB_guitar, STB_base;
	private TaskField STT_keyboard, STT_drum, STT_guitar, STT_base; 
	
	private CmbBoxBank Bank_keyboard, Bank_guitar, Bank_base;
	private ChkBoxBank Bank_drum;
	
	private String[] BeatList = {"2/2", "2/4", "3/4", "4/4", "-----", "6/8", "9/8", "12/8", "-----", "7/4", "11/4", "5/4"};
	private String[] RhythmList = {"1","2","3","4"};
	private String[] RootChordList = {"C", "D", "E", "F", "G", "A", "B"};
	private String[] ChildChordList = {"X", "M", "m", "7", "M7", "m7", "sus4", "dim"};
	
	private String[] keyboard_fname = {"쉼표", "도.wav","레.wav", "미.wav", "파.wav", "솔.wav","라.wav", "시.wav", "도(높은).wav"};
	private String[] drum_fname = {"쉼표", "K.wav", "H.wav", "S.wav", "HH.wav"};
	private String[] guitar_fname = {"쉼표", "6", "5", "4", "3", "2", "1"};
	private String[] base_fname = {"쉼표", "4", "3", "2", "1"};
	private String[] keyboard_tones = {"", "1", "2", "3"};
	private String[] guitar_tones = {"", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
	
	private File keyboardfile[], drumfile[];
	
	private int direction;
	public Orpheus() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		try{
			keyboardfile = new File[8];
			for(int i=0; i<8; i++)
				keyboardfile[i] = new File(keyboard_fname[i]);
			
			drumfile = new File[5];
			for(int i=0; i<5; i++)
				drumfile[i] = new File(drum_fname[i]);
		}
		catch(Exception e ) {}
			
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
		
		//STB = Setting to Beat
		STB_keyboard = new BeatField();
		STB_drum = new BeatField();
		STB_guitar = new BeatField();
		STB_base = new BeatField();
		
		STT_keyboard = new TaskField("키보드");
		STT_drum = new TaskField("드럼");
		STT_guitar = new TaskField("기타");
		STT_base = new TaskField("베이스");
		
		table_Beat = new JTable();
		table_Beat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_Beat = new JScrollPane(table_Beat);
		scrollPane_Beat.setBounds(12, 155, 700, 65);
		contentPane.add(scrollPane_Beat);
		
		//STF = Setting to Field
		STF_keyboard = new CmbBoxField(STB_keyboard, table_Beat, keyboard_fname.length, keyboard_tones);
		STF_drum = new ChkBoxField(STB_drum, table_Beat, drum_fname.length);
		STF_guitar = new CmbBoxField(STB_guitar, table_Beat, guitar_fname.length, guitar_tones);
		STF_base = new CmbBoxField(STB_base, table_Beat, base_fname.length, guitar_tones);
		
		table_Field = new JTable();
		table_Field.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_Field = new JScrollPane(table_Field);
		scrollPane_Field.setBounds(72, 220, 640, 200);
		contentPane.add(scrollPane_Field);
		
		//STK = Setting to Title
		STK_keyboard = new SettingToKind(keyboard_fname);
		STK_drum = new SettingToKind(drum_fname);
		STK_guitar = new SettingToKind(guitar_fname);
		STK_base = new SettingToKind(base_fname);
		
		table_Kind = new JTable();
		scrollPane_Kind = new JScrollPane(table_Kind);
		scrollPane_Kind.setBounds(12, 220, 60, 200);
		contentPane.add(scrollPane_Kind);

		
		//뱅크저장영역
		int restTime = RestTimeSetup.getRestTime(BPMSet.getText(), (String)BeatSet.getSelectedItem());
		Bank_keyboard = new CmbBoxBank(STF_keyboard, restTime);
		Bank_drum = new ChkBoxBank(STF_drum, restTime);
		Bank_guitar = new CmbBoxBank(STF_guitar, restTime);
		Bank_base = new CmbBoxBank(STF_base, restTime);

		
		
		//작업대기줄 영역
		table_TaskKeyboard = new JTable(STT_keyboard.getModel());
		table_TaskKeyboard.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		STT_keyboard.setCellOption(table_TaskKeyboard);
		scrollPane_TaskKeyboard = new JScrollPane(table_TaskKeyboard);
		scrollPane_TaskKeyboard.setBounds(12, 419, 640, 65);
		contentPane.add(scrollPane_TaskKeyboard);
		
		table_TaskDrum = new JTable(STT_drum.getModel());
		table_TaskDrum.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		STT_drum.setCellOption(table_TaskDrum);
		scrollPane_TaskKeyDrum = new JScrollPane(table_TaskDrum);
		scrollPane_TaskKeyDrum.setBounds(12, 483, 640, 65);
		contentPane.add(scrollPane_TaskKeyDrum);
		
		table_TaskGuitar = new JTable(STT_guitar.getModel());
		table_TaskGuitar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		STT_guitar.setCellOption(table_TaskGuitar);
		scrollPane_TaskGuitar = new JScrollPane(table_TaskGuitar);
		scrollPane_TaskGuitar.setBounds(12, 546, 640, 65);
		contentPane.add(scrollPane_TaskGuitar);
		
		table_TaskBase = new JTable(STT_base.getModel());
		table_TaskBase.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		STT_base.setCellOption(table_TaskBase);
		scrollPane_TaskBase = new JScrollPane(table_TaskBase);
		scrollPane_TaskBase.setBounds(12, 612, 640, 65);
		contentPane.add(scrollPane_TaskBase);
		
		lbl_SelectInstrument = new JLabel("악기선택");
		lbl_SelectInstrument.setBounds(12, 10, 57, 15);
		contentPane.add(lbl_SelectInstrument);
		
		btn_SelectTokeyboard = new JButton("키보드");
		btn_SelectTokeyboard.addActionListener(this);
		btn_SelectTokeyboard.setBounds(12, 40, 99, 25);
		contentPane.add(btn_SelectTokeyboard);
		
		btn_SelectTodrum = new JButton("드럼");
		btn_SelectTodrum.addActionListener(this);
		btn_SelectTodrum.setBounds(123, 40, 99, 25);
		contentPane.add(btn_SelectTodrum);
		
		btn_SelectToguitar = new JButton("기타");
		btn_SelectToguitar.addActionListener(this);
		btn_SelectToguitar.setBounds(234, 40, 99, 25);
		contentPane.add(btn_SelectToguitar);
		
		btn_SelectTobase = new JButton("베이스");
		btn_SelectTobase.addActionListener(this);
		btn_SelectTobase.setBounds(345, 40, 99, 25);
		contentPane.add(btn_SelectTobase);
		

		btn_start = new JButton("연주시작");
		btn_start.setBounds(12, 706, 99, 25);
		contentPane.add(btn_start);
		
		btn_erase = new JButton("지우기");
		btn_erase.setBounds(123, 706, 99, 25);
		contentPane.add(btn_erase);
	
		BankSave = new JButton("뱅크 저장");
		BankSave.addActionListener(this);
		BankSave.setBounds(123, 85, 100, 23);
		contentPane.add(BankSave);
		
		BankListen = new JButton("뱅크 듣기");
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
		Mute1.setBounds(657, 441, 55, 25);
		contentPane.add(Mute1);
		
		Mute2 = new JButton("M");
		Mute2.setBounds(657, 504, 55, 25);
		contentPane.add(Mute2);
		
		Mute3 = new JButton("M");
		Mute3.setBounds(657, 565, 55, 25);
		contentPane.add(Mute3);
		
		Mute4 = new JButton("M");
		Mute4.setBounds(657, 633, 55, 25);
		contentPane.add(Mute4);
		
//		PlayBank1 = new LinkedList<Note>();
//		PlayBank2 = new LinkedList<Note>();
//
//		Play test = new Play(PlayBank1, keyboardfile);
//		Play test2 = new Play(PlayBank2, drumfile);

		setKeyboard();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		
		switch(source.getText())
		{
		case "키보드" :
			setKeyboard();
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
		}
	}
	public void setKeyboard()
	{
		table_Kind.setModel(STK_keyboard.getModel());
		table_Field.setModel(STF_keyboard.getModel());
		STF_keyboard.setCellOption(table_Field);
		
		table_Beat.setModel(STB_keyboard.getModel());
		STB_keyboard.setCellOption(table_Beat);
		
		setBankList(STT_keyboard.getItemList().length-1);
		
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
		table_Kind.setModel(STK_drum.getModel());
		table_Field.setModel(STF_drum.getModel());
		STF_drum.setCellOption(table_Field);
		
		table_Beat.setModel(STB_drum.getModel());
		STB_drum.setCellOption(table_Beat);
		
		setBankList(STT_drum.getItemList().length-1);
		
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
		table_Kind.setModel(STK_guitar.getModel());
		table_Field.setModel(STF_guitar.getModel());
		STF_guitar.setCellOption(table_Field);
		
		table_Beat.setModel(STB_guitar.getModel());
		STB_guitar.setCellOption(table_Beat);
		
		setBankList(STT_guitar.getItemList().length-1);
		
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
		table_Kind.setModel(STK_base.getModel());
		table_Field.setModel(STF_base.getModel());
		STF_base.setCellOption(table_Field);
		
		table_Beat.setModel(STB_base.getModel());
		STB_base.setCellOption(table_Beat);
		
		setBankList(STT_base.getItemList().length-1);
		
		RootChord.setEnabled(false);
		ChildChord.setEnabled(false);
		
		RhythmInsert.setEnabled(true);
		RhythmListen.setEnabled(true);
		
		ChordInsert.setEnabled(false);
		ChordListen.setEnabled(false);
		
		direction = 4;
	}
	public void initKeyboard()
	{
		STF_keyboard.Init();
		STF_keyboard.setCellOption(table_Field);
		
		STB_keyboard.Init();
		STB_keyboard.setCellOption(table_Beat);
	}
	public void initDrum()
	{
		STF_drum.Init();
		STF_drum.setCellOption(table_Field);
		
		STB_drum.Init();
		STB_drum.setCellOption(table_Beat);
	}
	public void initGuitar()
	{
		STF_guitar.Init();
		STF_guitar.setCellOption(table_Field);
		
		STB_guitar.Init();
		STB_guitar.setCellOption(table_Beat);
	}
	public void initBase()
	{
		STF_base.Init();
		STF_base.setCellOption(table_Field);
		
		STF_base.Init();
		STF_base.setCellOption(table_Beat);
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
			STF_keyboard.PlayList.add(Bank_keyboard.getBank(STB_keyboard.getBeatResult()));
			//Bank_keyboard.bankPrint();
			
			initKeyboard();
			setBankList(STF_keyboard.PlayList.size()-1);
			STT_keyboard.reflash(STF_keyboard.PlayList.size()-1);
			break;
			
		case 2:
			STF_drum.PlayList.add(Bank_drum.getBank(STB_drum.getBeatResult()));
			//Bank_drum.bankPrint();
			
			initDrum();
			setBankList(STF_drum.PlayList.size()-1);
			STT_drum.reflash(STF_drum.PlayList.size()-1);
			break;
			
		case 3:
			STF_guitar.PlayList.add(Bank_guitar.getBank(STB_guitar.getBeatResult()));
			//Bank_keyboard.bankPrint();
			
			initGuitar();
			setBankList(STF_guitar.PlayList.size()-1);
			STT_guitar.reflash(STF_guitar.PlayList.size()-1);
			break;
			
		case 4:
			STF_base.PlayList.add(Bank_base.getBank(STB_base.getBeatResult()));
			//Bank_keyboard.bankPrint();
			
			initBase();
			setBankList(STF_base.PlayList.size()-1);
			STT_base.reflash(STF_base.PlayList.size()-1);
			break;
		}
		
	}
	
	public static void main(String[] args) {
		Orpheus frame = new Orpheus();
		frame.setVisible(true);
	}
}

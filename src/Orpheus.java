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

/**
 * @brief main 함수가 실행되는 클래스. GUI 인터페이스가 정의되는 곳이다.
 */
public class Orpheus extends JFrame implements ActionListener{

	//!
	private JPanel contentPane;
	//private JTable table_Field, table_Kind, table_Beat;
	//!
	private JTable[] table_Field;
	//private JTable table_TaskPiano, table_TaskDrum, table_TaskGuitar, table_TaskBase;
	//!
	private JTable[] table_Task; //
	//private JScrollPane scrollPane_Field, scrollPane_Kind, scrollPane_Beat;
	//!
	private JScrollPane[] scrollPane_Field;
	//private JScrollPane scrollPane_TaskPiano, scrollPane_TaskKeyDrum, scrollPane_TaskGuitar, scrollPane_TaskBase;
	//!
	private JScrollPane[] scrollPane_Task; //
	//!
	private JComboBox BeatSet, BankChoice, RhythmChoice, RootChord, ChildChord;
	//!
	private JTextField BPMSet;
	
	//!
	private JLabel lbl_SelectInstrument, lbl_SelectBPM, lbl_SelectBeatSet;
	//private JCheckBox Mute1, Mute2, Mute3, Mute4;
	//!
	private JCheckBox[] Mute;
	//!
	private JButton btn_start, btn_erase, btn_SelectToPiano, btn_SelectToDrum, btn_SelectToGuitar, btn_SelectToBase;
	//!
	private JButton btn_BankSave, btn_BankListen, btn_RhythmInsert, btn_RhythmListen, btn_ChordInsert, btn_ChordListen;
	//!
	private JButton btn_PianoSolo, btn_DrumSolo, btn_GuitarSolo, btn_BaseSolo;
	//!
	private JButton Keyboard;

	//private SettingToKind STK_Piano, STK_Drum, STK_Guitar, STK_Base;
	//!
	private SettingToKind[] STK;
	//private SettingToField STF_Piano, STF_Drum, STF_Guitar, STF_Base;
	//private CmbBoxField STF_Piano, STF_Guitar, STF_Base;
	//private ChkBoxField STF_Drum;
	
	//!
	private SettingToField[] STF;
	//private BeatField STB_Piano, STB_Drum, STB_Guitar, STB_Base;
	//!
	private BeatField[] STB;
	//private TaskField STT_Piano, STT_Drum, STT_Guitar, STT_Base;
	//!
	private TaskField[] STT;
	
	//private SaveBank Bank_Piano, Bank_Drum, Bank_Guitar, Bank_Base;
	//!
	private SaveBank[] Bank;
	//private CmbBoxBank Bank_Piano, Bank_Guitar, Bank_Base;
	//private ChkBoxBank Bank_Drum;
	
	//!
	private String[] BeatList = {"2/2", "2/4", "3/4", "4/4", "-----", "6/8", "9/8", "12/8", "-----", "7/4", "11/4", "5/4"};
	//!
	private String[] RhythmList = {"1","2","3","4"};
	//!
	private String[] RootChordList = {"C", "D", "E", "F", "G", "A", "B"};
	//!
	private String[] ChildChordList = {"M", "m", "7", "M7", "m7", "sus4", "dim"};
	
	//!
	private String[] Piano_tones = {"", "1", "2", "3"};
	//!
	private String[] Guitar_tones = {"", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
	
	//!
	private FileOpen files;
	
	//!
	private Input_GuitarCode Code;
	//!
	private PlayCode CodePlay;
	//!
	private static Orpheus ui;
	//!
	private static Play bankPlay;
	//!
	private static Play[] taskPlay;

	//!
	private int IDX;
	
	/**
	 * @brief main 함수
	 */
	public static void main(String[] args) {
		ui = new Orpheus();
		
		//뱅크듣기
		bankPlay = new Play(ui);
		bankPlay.MuteDisable();
		bankPlay.singleSet();
		bankPlay.setDaemon(true);
		bankPlay.ThreadStart();
		
		taskPlay = new Play[4];
		//for(int i=0; i<4; i++)
		for(int i=0; i<3; i++)	//아직 베이스가 완성전이라서 반복은 3까지
		{
			taskPlay[i] = new Play(ui);
			taskPlay[i].setDaemon(true);
			taskPlay[i].ThreadStart();
		}
	}

	/**
	 * @brief 생성자
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
		
		//폰트
		Font HumanRoundHeadLine = new Font("휴먼둥근헤드라인", Font.PLAIN, 12);
		Font PureGothic = new Font("맑은 고딕", Font.PLAIN, 12);
		Color bgcolor = new Color(255, 255, 255);
		Color fgcolor = new Color(0, 0, 0);
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
		CodePlay = new PlayCode(files.getGuitarCode(), files.getSoundFiles(2));
		
		//0번부터 3번까지 차례로 피아노, 드럼, 기타, 베이스
		STB = new BeatField[4];
		STT = new TaskField[4];
		STK = new SettingToKind[4];
		Mute = new JCheckBox[4];
		table_Task = new JTable[4];
		scrollPane_Task = new JScrollPane[4];
		
		//0 : table_Kind, 1 : table_Beat, 2: table_Field
		table_Field = new JTable[3];
		scrollPane_Field = new JScrollPane[3];
		String[] instruments = {"피아노", "드럼", "기타", "베이스"};
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
			
			Mute[i] = new JCheckBox(instruments[i]+"뮤트");
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
		
		//0번부터 3번까지 차례로 피아노, 드럼, 기타, 베이스
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

		
		Keyboard = new JButton("키보드");
		Keyboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Keyboard kb = new Keyboard();
				Keyboard.addActionListener(kb);
			}
		});
		Keyboard.setBackground(SystemColor.window);
		Keyboard.setBounds(718,100,48,42);
		contentPane.add(Keyboard);

		setField(0);
		
		setVisible(true);
	}

	/**
	 * @brief 
	 * @param ActionEvent e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		
		switch(source.getText())
		{
		case "피아노" :
			setField(0);
			break;
			
		case "드럼" :
			setField(1);
			break;
			
		case "기타" :
			setField(2);
			break;
			
		case "베이스" :
			setField(3);
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
	
	/**
	 * @brief 
	 */
	public void inputCode()
	{
		Code.addCode(table_Field[2], RootChord.getSelectedIndex(), ChildChord.getSelectedIndex());
		STF[2].addColumn(table_Field[2].getModel().getColumnCount()-1);
		STF[2].setCellOption(table_Field[2]);
	}
	
	/**
	 * @brief 
	 */
	public void playCode()
	{
		CodePlay.playCode(RootChord.getSelectedIndex(), ChildChord.getSelectedIndex());
	}
	
	/**
	 * @brief 
	 * @param int idx
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
	 * @brief 
	 */
	public void init()
	{
		STB[IDX].Init();
		STF[IDX].Init();
		
		STB[IDX].setCellOption(table_Field[1]);
		STF[IDX].setCellOption(table_Field[2]);
	}
	
	/**
	 * @brief 
	 * @param int size
	 */
	public void setBankList(int size)
	{
		BankChoice.removeAllItems();
		BankChoice.addItem("");
		for(int i=1; i<=size; i++)
			BankChoice.addItem(Integer.toString(i));
	}
	
	/**
	 * @brief 
	 * @param BeatField BeatField
	 * @return
	 */
	public boolean OutofBeat(BeatField BeatField)
	{
		if(BeatField.out_max(table_Field[1]) < RestTimeSetup.music_score)
		{
			JOptionPane.showMessageDialog(null, "설정한 박자가 부족합니다.");
			return false;
		}
		if(BeatField.out_max(table_Field[1]) > RestTimeSetup.music_score)
		{
			JOptionPane.showMessageDialog(null, "설정한 박자가 초과하였습니다.");
			return false;
		}
		return true;
	}
	
	/**
	 * @brief 
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
		JOptionPane.showMessageDialog(null, num+"번 뱅크 저장완료.");
	}
	
	/**
	 * @brief 
	 */
	public void ListenBank()
	{
		int BankNum = BankChoice.getSelectedIndex();
		bankPlay.setBank(STF[IDX].BankList.get(BankNum), files.getSoundFiles(IDX));
		bankPlay.action();
	}
	
	/**
	 * @brief 
	 * @param int idx
	 */
	public void ListenSolo(int idx)
	{
		taskPlay[idx].multySet(table_Task[idx], STF[idx].BankList, files.getSoundFiles(idx), Mute[idx]);
		taskPlay[idx].action();
	}
	
	/**
	 * @brief 
	 */
	public void musicQ()
	{
		//연주시작 메소드
		//for(int i=0; i<4; i++)
		for(int i=0; i<3; i++)
		{
			taskPlay[i].multySet(table_Task[i], STF[i].BankList, files.getSoundFiles(i), Mute[i]);
			taskPlay[i].action();
		}
	}
	
	/**
	 * @brief 
	 * @return
	 */
	public JButton getBankListenButton()
	{
		return btn_BankListen;
	}
}

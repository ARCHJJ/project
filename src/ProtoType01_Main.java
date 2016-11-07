import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView.TableRow;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.DefaultCellEditor;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ProtoType01_Main extends JFrame{

	private JPanel contentPane;
	private JTable table_Desk, table_Kind;
	private JScrollPane scrollPane_Desk, scrollPane_kind, scrollPane_beat;
	private JComboBox BeatSet;
	private JTextField BPMSet;
	
	private JLabel lbl_SelectInstrument, lbl_SelectBPM, lbl_SelectBeatSet;
	private JButton btn_start, btn_erase, btn_SelectTokeyboard, btn_SelectTodrum;
	private JButton btn_playBank1, btn_playBank2, Bank1, Bank2;
	
	private LinkedList<Note> PlayBank1, PlayBank2;
	private SaveBank SaveBank1, SaveBank2;
	
	private SettingToKind STK_keyboard, STK_drum;
	private SettingToField STF_keyboard, STF_drum;
	private SettingToBeat STB;
	private String[] BeatList = {"4/4"};	//박자는 더 추가될 예정
	
	private String keyboard_fname[] = {"쉼표", "도.wav","레.wav", "미.wav", "파.wav", "솔.wav","라.wav", "시.wav", "도(높은).wav"};
	private String drum_fname[] = {"쉼표", "K.wav", "H.wav", "S.wav", "HH.wav"};
	
	private File keyboardfile[], drumfile[];
	
	private AudioInputStream audioin;
	private Clip clip;
	
	
	public ProtoType01_Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//파일오픈 시켜주는 메소드 하나 만들면 좋을거같아.
		try{
			keyboardfile = new File[9];
			for(int i=0; i<9; i++)
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
		BeatSet.setBounds(735, 6, 60, 23);
		contentPane.add(BeatSet);		
		
		lbl_SelectBPM = new JLabel("BPM : ");
		lbl_SelectBPM.setBounds(829, 10, 57, 15);
		contentPane.add(lbl_SelectBPM);
		
		BPMSet = new JTextField("100");
		BPMSet.setBounds(873, 6, 60, 23);
		contentPane.add(BPMSet);
		
		
		//STB = Setting to Beat
		STB = new SettingToBeat();

		//table_beat = new JTable(STB.getModel());
		STB.getTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//table_beat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//table_beat.getColumnModel().getColumn(0).setPreferredWidth(60);
		STB.getTable().getColumnModel().getColumn(0).setPreferredWidth(60);
		//STB.setCellOption(table_beat);
		contentPane.add(STB.getTable());
		scrollPane_beat = new JScrollPane(STB.getTable());
		scrollPane_beat.setBounds(12, 135, 700, 65);
		contentPane.add(scrollPane_beat);
		
		//STF = Setting to Field
		STF_keyboard = new SettingToField(keyboard_fname.length, STB);
		STF_drum = new SettingToField(drum_fname.length, STB);
	
		table_Desk = new JTable(STF_keyboard.getModel());
		table_Desk.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		STF_keyboard.setCellOption(table_Desk);
		contentPane.add(table_Desk);
		scrollPane_Desk = new JScrollPane(table_Desk);
		scrollPane_Desk.setBounds(72, 200, 640, 200);
		contentPane.add(scrollPane_Desk);
		
		//STK = Setting to Title
		STK_keyboard = new SettingToKind(keyboard_fname);
		STK_drum = new SettingToKind(drum_fname);
		
		table_Kind = new JTable(STK_keyboard.getModel());
		contentPane.add(table_Kind);
		scrollPane_kind = new JScrollPane(table_Kind);
		scrollPane_kind.setBounds(12, 200, 60, 200);
		contentPane.add(scrollPane_kind);

				
		btn_start = new JButton("연주시작");
		btn_start.setBounds(12, 410, 99, 25);
		contentPane.add(btn_start);
		
		
		btn_erase = new JButton("지우기");
		btn_erase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dm = (DefaultTableModel)table_Desk.getModel();
				JCheckBox chk;
				System.out.println(dm.getRowCount());
				System.out.println(dm.getColumnCount());
				for(int i=0; i<dm.getRowCount(); i++)
					for(int j=1; j<dm.getColumnCount(); j++)
					{
						chk = (JCheckBox)dm.getValueAt(i, j);
						chk.setSelected(false);
//						if(chk.isSelected())
//						{
//							//chk.setSelected(false);
//							System.out.println(i +", "+ j);
//						}
//							//System.out.println(i +", "+ j);
					}
				
			}
		});
		btn_erase.setBounds(123, 410, 99, 25);
		contentPane.add(btn_erase);
		
		
		JButton CheckTest = new JButton("Check Test");
		CheckTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JCheckBox chk = new JCheckBox();
				for(int i=0; i<8; i++)
				{
					for(int j=1; j<33; j++)
					{
						chk = (JCheckBox)table_Desk.getValueAt(i, j);
						if(chk.isSelected())
							System.out.println("Select : "+ i +", " + j);
					}
				}
			}
		});
		CheckTest.setBounds(237, 410, 99, 25);
		contentPane.add(CheckTest);
		

		lbl_SelectInstrument = new JLabel("악기선택");
		lbl_SelectInstrument.setBounds(12, 10, 57, 15);
		contentPane.add(lbl_SelectInstrument);
		
		btn_SelectTokeyboard = new JButton("키보드");
		btn_SelectTokeyboard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				table_Kind.setModel(STK_keyboard.getModel());
				table_Desk.setModel(STF_keyboard.getModel());
				STF_keyboard.setCellOption(table_Desk);
			}
		});
		btn_SelectTokeyboard.setBounds(10, 40, 99, 25);
		contentPane.add(btn_SelectTokeyboard);
		
		btn_SelectTodrum = new JButton("드럼");
		btn_SelectTodrum.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				table_Kind.setModel(STK_drum.getModel());
				table_Desk.setModel(STF_drum.getModel());
				STF_drum.setCellOption(table_Desk);
				
			}
		});
		btn_SelectTodrum.setBounds(123, 40, 99, 25);
		contentPane.add(btn_SelectTodrum);
		
		
		PlayBank1 = new LinkedList<Note>();
		PlayBank2 = new LinkedList<Note>();
		
		int restTime = setup();
		SaveBank1 = new SaveBank(PlayBank1, table_Desk, STB.getTable(), keyboard_fname.length, restTime);
		SaveBank2 = new SaveBank(PlayBank2, table_Desk, STB.getTable(), drum_fname.length, restTime);
		
		Bank1 = new JButton("1번 뱅크(비었음)");
		Bank1.addActionListener(SaveBank1);
		Bank1.setBounds(12, 75, 150, 25);
		contentPane.add(Bank1);
		
		Bank2 = new JButton("2번 뱅크(비었음)");
		Bank2.addActionListener(SaveBank2);
		Bank2.setBounds(174, 75, 150, 25);
		contentPane.add(Bank2);
		
		Play test = new Play(PlayBank1, keyboardfile);
		Play test2 = new Play(PlayBank2, drumfile);
				
		btn_playBank1 = new JButton("1번 뱅크 재생");
		btn_playBank1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!PlayBank1.isEmpty())
					test.playtest();
			}
		});
		btn_playBank1.setBounds(12, 103, 150, 25);
		contentPane.add(btn_playBank1);
		
		btn_playBank2 = new JButton("2번 뱅크 재생");
		btn_playBank2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!PlayBank2.isEmpty())
					test2.playtest();
			}
		});
		btn_playBank2.setBounds(174, 103, 150, 25);
		contentPane.add(btn_playBank2);	

	}

	public int setup()
	{
		int BPM = Integer.parseInt(BPMSet.getText()); 
        int time_signature_denominator, time_signature_numerator, result; 
        
        double BPN;
        double thirty_second_note;
        
        
        
        StringTokenizer st = new StringTokenizer((String)BeatSet.getSelectedItem(),"/");
        
        time_signature_numerator = Integer.parseInt(st.nextToken());
        time_signature_denominator = Integer.parseInt(st.nextToken());
        
        BPN = 60.0/BPM;
        
        thirty_second_note = BPN/(32/time_signature_denominator);
        result = (int)thirty_second_note*1000;
        return result; 
    }
	public static void main(String[] args) {
		ProtoType01_Main frame = new ProtoType01_Main();
		frame.setVisible(true);
		System.out.println("Hello world");
	}
}

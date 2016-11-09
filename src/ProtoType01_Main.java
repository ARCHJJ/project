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
	private JTable table_Field, table_Kind, table_Beat;
	private JScrollPane scrollPane_Field, scrollPane_Kind, scrollPane_Beat;
	private JComboBox BeatSet;
	private JTextField BPMSet;
	
	private JLabel lbl_SelectInstrument, lbl_SelectBPM, lbl_SelectBeatSet;
	private JButton btn_start, btn_erase, btn_SelectTokeyboard, btn_SelectTodrum, btn_SelectToguitar, btn_SelectTobase;
	private JButton btn_playBank1, btn_playBank2, Bank1, Bank2;
	
	private LinkedList<Note> PlayBank1, PlayBank2;
	private SaveBank SaveBank1, SaveBank2;

	private SettingToKind STK_keyboard, STK_drum, STK_guitar, STK_base;
	private SettingToField STF_keyboard, STF_drum, STF_guitar, STF_base;
	private BeatField STB_keyboard, STB_drum, STB_guitar, STB_base;
	
	private String[] BeatList = {"4/4"};	//박자는 더 추가될 예정
	
	private String[] keyboard_fname = {"쉼표", "도.wav","레.wav", "미.wav", "파.wav", "솔.wav","라.wav", "시.wav", "도(높은).wav"};
	private String[] drum_fname = {"쉼표", "K.wav", "H.wav", "S.wav", "HH.wav"};
	private String[] guitar_fname = {"쉼표", "6", "5", "4", "3", "2", "1"};
	private String[] base_fname = {"쉼표", "4", "3", "2", "1"};
	private String[] keyboard_tones = {"", "1", "2", "3"};
	private String[] guitar_tones = {"", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
	
	private File keyboardfile[], drumfile[];
	
	public ProtoType01_Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 480);
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
		BeatSet.setBounds(735, 6, 60, 23);
		contentPane.add(BeatSet);		
		
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
		
		table_Beat = new JTable(STB_keyboard.getModel());
		table_Beat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_Beat.getColumnModel().getColumn(0).setPreferredWidth(60);
		STB_keyboard.setCellOption(table_Beat);
		contentPane.add(table_Beat);
		scrollPane_Beat = new JScrollPane(table_Beat);
		scrollPane_Beat.setBounds(12, 135, 700, 65);
		contentPane.add(scrollPane_Beat);
		
		//STF = Setting to Field
		STF_keyboard = new CmbBoxField(STB_keyboard, table_Beat, keyboard_fname.length, keyboard_tones);
		STF_drum = new ChkBoxField(STB_drum, table_Beat, drum_fname.length);
		STF_guitar = new CmbBoxField(STB_guitar, table_Beat, guitar_fname.length, guitar_tones);
		STF_base = new CmbBoxField(STB_base, table_Beat, base_fname.length, guitar_tones);
		
		table_Field = new JTable(STF_keyboard.getModel());
		table_Field.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		STF_keyboard.setCellOption(table_Field);
		contentPane.add(table_Field);
		scrollPane_Field = new JScrollPane(table_Field);
		scrollPane_Field.setBounds(72, 200, 640, 200);
		contentPane.add(scrollPane_Field);
		
		//STK = Setting to Title
		STK_keyboard = new SettingToKind(keyboard_fname);
		STK_drum = new SettingToKind(drum_fname);
		STK_guitar = new SettingToKind(guitar_fname);
		STK_base = new SettingToKind(base_fname);
		
		table_Kind = new JTable(STK_keyboard.getModel());
		contentPane.add(table_Kind);
		scrollPane_Kind = new JScrollPane(table_Kind);
		scrollPane_Kind.setBounds(12, 200, 60, 200);
		contentPane.add(scrollPane_Kind);

				
		btn_start = new JButton("연주시작");
		btn_start.setBounds(12, 410, 99, 25);
		contentPane.add(btn_start);
		
		
		btn_erase = new JButton("지우기");
		btn_erase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dm = (DefaultTableModel)table_Field.getModel();
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
						chk = (JCheckBox)table_Field.getValueAt(i, j);
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
				table_Kind.setModel(STK_keyboard.getModel());
				table_Field.setModel(STF_keyboard.getModel());
				STF_keyboard.setCellOption(table_Field);
				
				table_Beat.setModel(STB_keyboard.getModel());
				STB_keyboard.setCellOption(table_Beat);
			}
		});
		btn_SelectTokeyboard.setBounds(10, 40, 99, 25);
		contentPane.add(btn_SelectTokeyboard);
		
		btn_SelectTodrum = new JButton("드럼");
		btn_SelectTodrum.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				table_Kind.setModel(STK_drum.getModel());
				table_Field.setModel(STF_drum.getModel());
				STF_drum.setCellOption(table_Field);
				
				table_Beat.setModel(STB_drum.getModel());
				STB_drum.setCellOption(table_Beat);
			}
		});
		btn_SelectTodrum.setBounds(123, 40, 99, 25);
		contentPane.add(btn_SelectTodrum);
		
		btn_SelectToguitar = new JButton("기타");
		btn_SelectToguitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table_Kind.setModel(STK_guitar.getModel());
				table_Field.setModel(STF_guitar.getModel());
				STF_guitar.setCellOption(table_Field);
				
				table_Beat.setModel(STB_guitar.getModel());
				STB_guitar.setCellOption(table_Beat);
			}
		});
		btn_SelectToguitar.setBounds(237, 40, 99, 25);
		contentPane.add(btn_SelectToguitar);
		
		btn_SelectTobase = new JButton("베이스");
		btn_SelectTobase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_Kind.setModel(STK_base.getModel());
				table_Field.setModel(STF_base.getModel());
				STF_base.setCellOption(table_Field);
				
				table_Beat.setModel(STB_base.getModel());
				STB_base.setCellOption(table_Beat);
			}
		});
		btn_SelectTobase.setBounds(348, 40, 99, 25);
		contentPane.add(btn_SelectTobase);
		
		
		PlayBank1 = new LinkedList<Note>();
		PlayBank2 = new LinkedList<Note>();
		
		int restTime = setup();
		//SaveBank1 = new SaveBank(PlayBank1, table_Field, STB.getTable(), keyboard_fname.length, restTime);
	//	SaveBank2 = new SaveBank(PlayBank2, table_Field, STB.getTable(), drum_fname.length, restTime);
		
		Bank1 = new JButton("1번 뱅크(비었음)");
		//Bank1.addActionListener(SaveBank1);
		Bank1.setBounds(12, 75, 150, 25);
		contentPane.add(Bank1);
		
		Bank2 = new JButton("2번 뱅크(비었음)");
		//Bank2.addActionListener(SaveBank2);
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
//
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
        result = (int)(thirty_second_note*1000.0);
        return result; 
    }
	public static void main(String[] args) {
		ProtoType01_Main frame = new ProtoType01_Main();
		frame.setVisible(true);
	}
}

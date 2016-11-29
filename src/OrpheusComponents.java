import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

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
	
	//! BeatSet			: ��Ʈ�� �����ϴ� �޺��ڽ�
	//! BankChoice		: ��ũ�� �����ϴ� �޺��ڽ�
	//! RhythmChoice	: ������ �����ϴ� �޺��ڽ�
	//! RootChord		: �ڵ��� ������ �����ϴ� �޺��ڽ�
	//! ChildChord		: �������� �Ļ��Ǵ����� �����ϴ� �޺��ڽ�
	protected JComboBox BeatSet, BankChoice, RhythmChoice, RootChord, ChildChord;
	
	//! BPMSet : BPM�� �Է��ϴ� �ؽ�Ʈ�ʵ� 
	protected JTextField BPMSet;
	
	//! lbl_SelectInstrument : �Ǳ⼱�ù�ư�� �ȳ����ִ� ���̺�
	//! lbl_SelectBPM		 : BPMSet�� �ȳ����ִ� ���̺�
	//! lbl_SelectBeatSet	 : BeatSet�� �ȳ����ִ� ���̺�
	protected JLabel lbl_SelectInstrument, lbl_SelectBPM, lbl_SelectBeatSet;

	//! �۾�����ٿ��� ����Ǵ� �Ǳ⸦ ��Ʈ��Ű�� üũ�ڽ�. ���ʴ�� �ǾƳ�, �巳, ��Ÿ, ���̽�
	protected JCheckBox[] Mute;

	//! btn_start			: ���ֽ��۹�ư. �۾�����ٿ��� ������ ��ũ�� ���ʷ� ����Ѵ�.
	//! btn_erase			: ������ư. table_Field�� ������ ��� �����.
	//! btn_SelectToPiano	: �ǾƳ뼱�ù�ư. table_Field�� �ǾƳ�� �����Ѵ�.
	//! btn_SelectToDrum	: �巳���ù�ư. table_Field�� �巳���� �����Ѵ�.
	//! btn_SelectToGuitar	: ��Ÿ���ù�ư. table_Field�� ��Ÿ�� �����Ѵ�.
	//! btn_SelectToBase	: ���̽����ù�ư. table_Field�� ���̽��� �����Ѵ�.
	protected JButton btn_start, btn_erase, btn_SelectToPiano, btn_SelectToDrum, btn_SelectToGuitar, btn_SelectToBase;
	
	//! btn_BankSave		: ��ũ�����ư. table_Field[1], table_Field[2]�� ������ ��ũ�� �����Ѵ�.
	//! btn_BankListen		: ��ũ�����ư. �޺��ڽ��� ������ ��ũ�� ����Ѵ�.
	//! btn_RhythmInsert	: ������Թ�ư. �̸� ������� �ִ� ������ table_Field[1], table_Field[2]�� �����Ѵ�.
	//! btn_RhythmListen	: �������ư. �̸� ������� �ִ� ������ ����.
	//! btn_ChordInsert		: �ڵ���Թ�ư. �̸� ������� �ִ� �ڵ带 table_Field[1], table_Field[2]�� �����Ѵ�.
	//! btn_ChordListen		: �ڵ����ư. �̸� ������� �ִ� �ڵ带 ����.
	protected JButton btn_BankSave, btn_BankListen, btn_RhythmInsert, btn_RhythmListen, btn_ChordInsert, btn_ChordListen;
	
	//! btn_PianoSolo	 : �ǾƳ�ַι�ư. �ǾƳ��۾�����ٸ� ����Ѵ�.
	//! btn_DrumSolo	 : �巳�ַι�ư. �巳�۾�����ٸ� ����Ѵ�.
	//! btn_GuitarSolo	 : ��Ÿ�ַι�ư. ��Ÿ�۾�����ٸ� ����Ѵ�.
	//! btn_BaseSolo	 : ���̽��ַι�ư. ���̽��۾�����ٸ� ����Ѵ�.
	//! btn_KeyBoardPlay : Ű���忬�ֹ�ư. Ű���� ���ָ� �����ϰ� �Ѵ�. 
	protected JButton btn_PianoSolo, btn_DrumSolo, btn_GuitarSolo, btn_BaseSolo, btn_KeyboardPlay;
	
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
	
	//! �Ҹ��� ����� �� UI���� �����带 �����ϱ� ���� ����Ŭ������ ��ü.  
	protected Orpheus ui;
	
	//! ��ũ�̸���⸦ ���� ��ü
	protected Play bankPlay;
	
	//! �ַε�⸦ ���� ��ü
	protected Play[] taskPlay;
	
	//! ��Ʈ�γ��� ���� ��ü
	protected Metronome metronome;

	//! ���� ���� �ִ� ȭ���� � �Ǳ����� �����ϱ� ���� ����
	protected int IDX;
}

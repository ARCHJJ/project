import java.util.LinkedList;

import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * @brief Ű���忬�ֱ���� GUI�� �����ϴ� Ŭ����
 * ������Ʈ ����ü�μ� PlayToKeyboard Ŭ������ �θ�Ŭ������ �ȴ�.  
 */
abstract class PlayToKeyboardComponents {

	//! GUI���� ������ �Ǵ� Freme
	protected JFrame mainFrame;
	
	//! GUI���� ������ �Ǵ� Panel
	protected JPanel contentPane;
	
	//! table_RootChord  : ������ �ȳ����ִ� ���̺�
	//! table_ChildChord : �Ļ����� �ȳ����ִ� ���̺�
	protected JTable table_RootChord, table_ChildChord;
	
	//! tablemodel_RootChord	: table_RootChord�� ��
	//! tablemodel_ChildChord	: table_ChildChord�� ��
	protected DefaultTableModel tablemodel_RootChord, tablemodel_ChildChord;
	
	//! centerSort	: ��� ������ ���� ��������
	protected DefaultTableCellRenderer centerSort;
	
	//! scrollPane_RootChord	: table_RootChord�� ��ũ���� �߰��ϴ� ScrollPane
	//! scrollPane_ChildChord	: table_ChildChord�� ��ũ���� �߰��ϴ� ScrollPane
	protected JScrollPane scrollPane_RootChord, scrollPane_ChildChord;
	
	//! btn_SelectToPiano	: �ǾƳ뼱�ù�ư. table_Field�� �ǾƳ�� �����Ѵ�.
	//! btn_SelectToDrum	: �巳���ù�ư. table_Field�� �巳���� �����Ѵ�.
	//! btn_SelectToGuitar	: ��Ÿ���ù�ư. table_Field�� ��Ÿ�� �����Ѵ�.
	//! btn_SelectToBase	: ���̽����ù�ư. table_Field�� ���̽��� �����Ѵ�.
	protected JButton btn_SelectToPiano, btn_SelectToDrum, btn_SelectToGuitar, btn_SelectToBase;
	
	//! lbl_showOctave	: table_RootChord �� �ȳ����ִ� ���̺�
	//! lbl_showTone	: table_ChildChord �� �ȳ����ִ� ���̺�
	protected JLabel lbl_showOctave, lbl_showTone;

	//! RootField	: tablemodel_RootChord�� ������ �Ǵ� Object[][]�� ����
	//! ChildField	: tablemodel_ChildChord�� ������ �Ǵ� Object[][]�� ����
	protected Object[][][] RootField, ChildField;
	
	//! RootHeader	: tablemodel_RootChord�� ����� �Ǵ� String[]�� ����
	//! ChildHeader	: tablemodel_ChildChord�� ����� �Ǵ� String[]�� ����
	protected String[][] RootHeader, ChildHeader;	
	
	//! ���Ͽ���Ŭ������ ��ü. ����� �ʿ��� �Ҹ����ϵ��� �����Ѵ�.
	protected FileOpen files;

	//! �Ҹ������� ����ϱ� ���� ��ü
	protected Clip clip;
	
	//! �ε巯�� �Ҹ������ ���� ��������
	//protected LinkedList<Clip> noise;
	
	//! IDX			: ���� ���� �ִ� ȭ���� � �Ǳ����� �����ϱ� ���� ����
	//! pianoRoot	: ���� �ǾƳ��� ��Ÿ��
	//! guitarRoot	: ���� ��Ÿ�� ����
	protected int IDX, pianoRoot = 0, guitarRoot = 0;
	
	//! pianoOctave : �ǾƳ��� ��Ÿ�� �̸��� ������ �ִ� String[]
	protected String[] pianoOctave = {"4��Ÿ��", "5��Ÿ��", "6��Ÿ��"};
	
	//! pianoOctave : ��Ÿ�� ���� �̸��� ������ �ִ� String[]
	protected String[] guitarOctave= {"C","D","E","F","G","A","B"};
	
	public PlayToKeyboardComponents() {
		mainFrame = new JFrame();
		contentPane = new JPanel();
		mainFrame.setContentPane(contentPane);
		contentPane.setLayout(null);
		mainFrame.setBounds(935, 20, 462, 200);
		
		//noise = new LinkedList<Clip>();
		table_RootChord = new JTable();
		table_RootChord.setEnabled(false);
		scrollPane_RootChord = new JScrollPane(table_RootChord);
		scrollPane_RootChord.setBounds(12, 83, 432, 41);
		contentPane.add(scrollPane_RootChord);
		
		table_ChildChord = new JTable();
		table_ChildChord.setEnabled(false);
		scrollPane_ChildChord = new JScrollPane(table_ChildChord);
		scrollPane_ChildChord.setBounds(12, 35, 432, 41);
		contentPane.add(scrollPane_ChildChord);
		
		btn_SelectToPiano = new JButton("�ǾƳ�");
		btn_SelectToPiano.setBounds(12, 5, 99, 25);
		contentPane.add(btn_SelectToPiano);
		
		btn_SelectToDrum = new JButton("�巳");
		btn_SelectToDrum.setBounds(123, 5, 99, 25);
		contentPane.add(btn_SelectToDrum);
		
		btn_SelectToGuitar = new JButton("��Ÿ");
		btn_SelectToGuitar.setBounds(234, 5, 99, 25);
		contentPane.add(btn_SelectToGuitar);
		
		btn_SelectToBase = new JButton("���̽�");
		btn_SelectToBase.setBounds(345, 5, 99, 25);
		contentPane.add(btn_SelectToBase);
				
		lbl_showOctave = new JLabel("");
		lbl_showOctave.setBounds(12, 134, 57, 15);
		contentPane.add(lbl_showOctave);
		
		lbl_showTone = new JLabel("");
		lbl_showTone.setBounds(123, 134, 57, 15);
		contentPane.add(lbl_showTone);
	}
}

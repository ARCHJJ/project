import java.awt.Toolkit;

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

	//! GUI���� ������ �Ǵ� Frame
	protected JFrame mainFrame;
	
	//! GUI���� ������ �Ǵ� Panel
	protected JPanel contentPane;
	
	//! ������ �ȳ����ִ� ���̺�
	protected JTable table_RootChord;

	//! �Ļ����� �ȳ����ִ� ���̺�
	protected JTable table_ChildChord;
	
	//! table_RootChord�� ��
	protected DefaultTableModel tablemodel_RootChord;

	//! table_ChildChord�� ��
	protected DefaultTableModel tablemodel_ChildChord;
	
	//! centerSort	: ��� ������ ���� ��������
	protected DefaultTableCellRenderer centerSort;
	
	//! table_RootChord�� ��ũ���� �߰��ϴ� ScrollPane
	protected JScrollPane scrollPane_RootChord;
	
	//! table_ChildChord�� ��ũ���� �߰��ϴ� ScrollPane
	protected JScrollPane scrollPane_ChildChord;
	
	//! �ǾƳ뼱�ù�ư. table_Field�� �ǾƳ�� �����Ѵ�.
	protected JButton btn_SelectToPiano;
	
	//! �巳���ù�ư. table_Field�� �巳���� �����Ѵ�.
	protected JButton btn_SelectToDrum;
	
	//! ��Ÿ���ù�ư. table_Field�� ��Ÿ�� �����Ѵ�.
	protected JButton btn_SelectToGuitar;
	
	//! ���̽����ù�ư. table_Field�� ���̽��� �����Ѵ�.
	protected JButton btn_SelectToBase;
	
	//! table_RootChord �� �ȳ����ִ� ���̺�
	protected JLabel lbl_showOctave;
	
	//! table_ChildChord �� �ȳ����ִ� ���̺�
	protected JLabel lbl_showTone;

	//! tablemodel_RootChord�� ������ �Ǵ� Object[][]�� ����
	protected Object[][][] RootField;
	
	//! tablemodel_ChildChord�� ������ �Ǵ� Object[][]�� ����
	protected Object[][][] ChildField;
	
	//! tablemodel_RootChord�� ����� �Ǵ� String[]�� ����
	protected String[][] RootHeader;
	
	//! tablemodel_ChildChord�� ����� �Ǵ� String[]�� ����
	protected String[][] ChildHeader;	
	
	//! ���Ͽ���Ŭ������ ��ü. ����� �ʿ��� �Ҹ����ϵ��� �����Ѵ�.
	protected FileOpen files;

	//! �Ҹ������� ����ϱ� ���� ��ü
	protected Clip clip;
	
	//! ���� ���� �ִ� ȭ���� � �Ǳ����� �����ϱ� ���� ����
	protected int IDX;
	
	//! �ǾƳ��� ���� ��Ÿ��
	protected int pianoRoot = 0;
	
	//! ��Ÿ�� ���� ����
	protected int guitarRoot = 0;
	
	//! �ǾƳ��� ��Ÿ�� �̸��� ������ �ִ� String[]
	protected String[] pianoOctave = {"4��Ÿ��", "5��Ÿ��", "6��Ÿ��"};
	
	//! ��Ÿ�� ���� �̸��� ������ �ִ� String[]
	protected String[] guitarOctave= {"C","D","E","F","G","A","B"};
	

	/**
	 * @brief ������
	 * ����� ������Ʈ�� �Ҵ��ϰ� ��ġ�� �����Ѵ�. 
	 */
	public PlayToKeyboardComponents() {
		mainFrame = new JFrame();
		contentPane = new JPanel();
		mainFrame.setContentPane(contentPane);
		contentPane.setLayout(null);
		mainFrame.setBounds(935, 20, 510, 200);
		mainFrame.setTitle("������콺�� ����");
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("images.png"));//icon
		
		table_RootChord = new JTable();
		table_RootChord.setEnabled(false);
		scrollPane_RootChord = new JScrollPane(table_RootChord);
		scrollPane_RootChord.setBounds(12, 88, 477, 47);
		contentPane.add(scrollPane_RootChord);
		
		table_ChildChord = new JTable();
		table_ChildChord.setEnabled(false);
		scrollPane_ChildChord = new JScrollPane(table_ChildChord);
		scrollPane_ChildChord.setBounds(12, 35, 477, 47);
		contentPane.add(scrollPane_ChildChord);
		
		btn_SelectToPiano = new JButton("�ǾƳ�");
		btn_SelectToPiano.setBounds(12, 5, 110, 25);
		contentPane.add(btn_SelectToPiano);
		
		btn_SelectToDrum = new JButton("�巳");
		btn_SelectToDrum.setBounds(134, 5, 110, 25);
		contentPane.add(btn_SelectToDrum);
		
		btn_SelectToGuitar = new JButton("��Ÿ");
		btn_SelectToGuitar.setBounds(256, 5, 110, 25);
		contentPane.add(btn_SelectToGuitar);
		
		btn_SelectToBase = new JButton("���̽�");
		btn_SelectToBase.setBounds(378, 5, 110, 25);
		contentPane.add(btn_SelectToBase);
				
		lbl_showOctave = new JLabel("");
		lbl_showOctave.setBounds(12, 142, 57, 15);
		contentPane.add(lbl_showOctave);
		
		lbl_showTone = new JLabel("");
		lbl_showTone.setBounds(123, 142, 57, 15);
		contentPane.add(lbl_showTone);
	}
}

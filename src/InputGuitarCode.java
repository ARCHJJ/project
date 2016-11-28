import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 * @brief �̸� �������� ��Ÿ�ڵ带 ���̺� �����ϴ� Ŭ����
 */
public class InputGuitarCode {
	//! ����� ���� ��Ÿ�ڵ带 �ҷ��ͼ� �����ϴ� String[][]
	private String[][] Code;
	
	/**
	 * @brief ������
	 * @param String[][] Code_File : ����� ���� ��Ÿ�ڵ� �����͸� ���ڷ� ���� �޴´�.
	 */
	public InputGuitarCode(String[][] Code_File)
	{
		Code = Code_File;
	}
	
	/**
	 * @brief ������ �ڵ带 JTable�� �����Ѵ�.
	 * @param JTable Desk	: �ڵ尡 �߰��� JTable 
	 * @param int x			: Code�� ��
	 * @param int y			: Code�� ��
	 */
	public void addCode(JTable Desk, int x, int y)
	{
		String Note;
		JComboBox temp;
		Note = Code[x][y];
		char ch;
		for(int i=1; i<7; i++)
		{
			ch = Note.charAt(i-1);
			temp = (JComboBox)Desk.getModel().getValueAt(i, Desk.getModel().getColumnCount()-1);
			
			if(ch=='x')
				temp.setSelectedIndex(0);
			else
				temp.setSelectedIndex(((int)ch-48)+1);
			
			Desk.setValueAt(temp, i, Desk.getModel().getColumnCount()-1);
		}
	}
}

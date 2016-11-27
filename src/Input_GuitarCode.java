import java.util.StringTokenizer;

import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 * @brief 
 */
public class Input_GuitarCode {
	//!
	private String[][] Code;
	
	/**
	 * @brief 
	 * @param String[][] Code_File
	 */
	public Input_GuitarCode(String[][] Code_File)
	{
		Code = Code_File;
	}
	
	/**
	 * @brief 
	 * @param JTable Desk
	 * @param int x
	 * @param int y
	 */
	public void addCode(JTable Desk, int x, int y)
	{
		String Note;
		JComboBox temp;
		//StringTokenizer st = new StringTokenizer(Code[x][y] ,",");
		Note = Code[x][y];
		char ch;
		for(int i=1; i<7; i++)
		{
			//Note = st.nextToken();
			ch = Note.charAt(i-1);
			temp = (JComboBox)Desk.getModel().getValueAt(i, Desk.getModel().getColumnCount()-1);
			//if(Note.equals("x"))
			if(ch=='x')
				temp.setSelectedIndex(0);
			else
				temp.setSelectedIndex(((int)ch-48)+1);
				//temp.setSelectedIndex(Integer.parseInt(Note)+1);
			
			Desk.setValueAt(temp, i, Desk.getModel().getColumnCount()-1);
			
		}
	}
}

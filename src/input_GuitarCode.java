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
		StringTokenizer st = new StringTokenizer(Code[x][y] ,",");
		
		for(int i=1; i<7; i++)
		{
			Note = st.nextToken();
			
			temp = (JComboBox)Desk.getModel().getValueAt(i, Desk.getModel().getColumnCount()-1);
			
			if(Note.equals("x"))
				temp.setSelectedIndex(0);
			else
				temp.setSelectedIndex(Integer.parseInt(Note)+1);
			
			Desk.setValueAt(temp, i, Desk.getModel().getColumnCount()-1);
			
		}
	}
}

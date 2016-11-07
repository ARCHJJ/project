import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class SaveBank implements ActionListener{
	private LinkedList<Note> PlayList;
	private JTable Desk, Beat;
	private int kind, restTime;
	SaveBank(LinkedList<Note> PlayList, JTable Desk, JTable Beat, int kind, int restTime)
	{
		this.PlayList = PlayList;
		this.Desk = Desk;
		this.Beat = Beat;
		this.kind = kind;
		this.restTime = restTime;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		boolean isrest = false;
		int i, j;
		JCheckBox chk;
		JComboBox com; 
		PlayList.clear();
		
		Note temp = null;
		for(i=0; i<Desk.getColumnCount(); i++)
		{
			temp = null;
			for(j=0; j<kind; j++)
			{
				chk = (JCheckBox)Desk.getValueAt(j, i);
				if(chk.isSelected() && !isrest)
				{
					temp = new Note();
					if(j!=0)
						temp.fileidx.add(j);
					com = (JComboBox) Beat.getValueAt(0, i+1);
					//System.out.println((String)com.getSelectedItem());
					//temp.rest = 32/Integer.parseInt((String)com.getSelectedItem())*restTime;
					//동현이가 만든 쉬는시간 메소드 안먹힘
					temp.rest = 350;
					isrest = true;
					//System.out.println(j +", " + i);
				}
				else if(chk.isSelected())
				{
					temp.fileidx.add(j);
					//System.out.println(j +", " + i);
				}
					
			}
			if(temp!=null)
			{
				PlayList.add(temp);
				System.out.println(temp.fileidx);
			}
			isrest = false;
		}
		
//		if(!PlayBank1.isEmpty())
//			Bank1.setText("1번 뱅크(저장)");
	}
}



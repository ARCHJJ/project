import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.LinkedList;

import javax.sound.sampled.AudioSystem;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class SaveChkBoxBank extends SaveBank{

	SettingToField field;
	SaveChkBoxBank(SettingToField field) {
		this.field = field;
		this.kind = field.getKinds();
	}

	public LinkedList<Note> getBank(LinkedList<Integer> beatList, int restTime)
	{
		boolean isfirst = false;
		int i, j;
		JCheckBox chk;
		
		LinkedList<Note> newBank = new LinkedList<Note>();
		Note temp = null;
		DefaultTableModel tablemodel = field.getModel();
		for(i=0; i<tablemodel.getColumnCount(); i++)
		{
			temp = null;
			for(j=0; j<kind; j++)
			{
				chk = (JCheckBox)tablemodel.getValueAt(j, i);
				if(chk.isSelected() && !isfirst)
				{
					temp = new Note();
					if(j!=0)
						temp.fileidx.add(j);
					
					temp.rest = (32/beatList.get(i))*restTime;
					isfirst = true;
				}
				else if(chk.isSelected())
					temp.fileidx.add(j);
					
			}
			if(temp!=null)
				newBank.add(temp);
	
			isfirst = false;
		}
		return newBank;
	}
	
	public void bankPrint(LinkedList<Note> bank)	//테스트용 메소드
	{
		Iterator<Note> itNote = bank.iterator();
		Iterator<Integer> itPlay;
		try
		{
			while(itNote.hasNext())
			{
				Note temp = itNote.next();
				System.out.println(temp.rest);
				itPlay = temp.fileidx.iterator();
				System.out.print("[");
				while(itPlay.hasNext())
				{
					System.out.print(itPlay.next());
					System.out.print(", ");
				}
				System.out.print("]" + "\t");
				System.out.println("rest :" + temp.rest);
				//Thread.sleep(temp.rest);
			}
		}
		catch(Exception exp)
		{}
	}
	
}


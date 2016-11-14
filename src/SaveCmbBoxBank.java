import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

class SaveCmbBoxBank extends SaveBank{
	SettingToField field;
	SaveCmbBoxBank(SettingToField field, int restTime) {
		super();
		this.field = field;
		this.kind = field.getKinds();
		this.restTime = restTime;
	}

	public LinkedList<Note> getBank(LinkedList<Integer> beatList)
	{
		boolean isfirst = false;
		int i, j;
		JComboBox combo;
		String tmp;
		bank.clear();
		
		Note temp = null;
		DefaultTableModel tablemodel = field.getModel();
		for(i=0; i<tablemodel.getColumnCount(); i++)
		{
			temp = null;
			for(j=0; j<kind; j++)
			{
				combo = (JComboBox)tablemodel.getValueAt(j, i);
				tmp = (String)combo.getSelectedItem();
				if(!tmp.isEmpty() && !isfirst)
				{
					temp = new Note();
					if(j!=0)
						temp.fileidx.add((Integer.parseInt(tmp)*10)+j);
					
					temp.rest = (32/beatList.get(i))*restTime;
					isfirst = true;
				}
				else if(!tmp.isEmpty())
					temp.fileidx.add((Integer.parseInt(tmp)*10)+j);
					
			}
			if(temp!=null)
				bank.add(temp);
	
			isfirst = false;
		}
		return bank;
	}
	
	public void bankPrint()	//테스트용 메소드
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

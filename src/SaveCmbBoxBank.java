import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 * @brief 콤보박스로 이루어진 뱅크를 저장
 */
class SaveCmbBoxBank extends SaveBank{
	//!필드
	SettingToField field;
	
	/**
	 * @brief 생성자
	 * @param field
	 */
	SaveCmbBoxBank(SettingToField field) {
		this.field = field;
		this.kind = field.getKinds();
	}

	
	/**
	 * @brief 조건에 맞는 특정한 뱅크를 호출하는 메소드
	 * @param LinkedList<Integer> beatList 박자의 리스트들을 가진 자료구조
	 * @param int restTime 쉬는부분
	 * @return 조건에 맞는 arraylist 리턴
	 */
	public LinkedList<Note> getBank(LinkedList<Integer> beatList, int restTime)
	{
		boolean isfirst = false;
		int i, j;
		JComboBox combo;
		int tmp;
		
		LinkedList<Note> newBank = new LinkedList<Note>();
		Note newNote = null;
		DefaultTableModel tablemodel = field.getModel();
		for(i=0; i<tablemodel.getColumnCount(); i++)
		{
			newNote = null;
			for(j=0; j<kind; j++)
			{
				combo = (JComboBox)tablemodel.getValueAt(j, i);
				tmp = combo.getSelectedIndex();
				if(tmp!=0 && !isfirst)
				{
					newNote = new Note();
					if(j!=0)
						newNote.fileidx.add(((j-1)*100)+(tmp-1));
					
					newNote.rest = (32/beatList.get(i))*restTime;
					isfirst = true;
				}
				else if(tmp!=0)
					newNote.fileidx.add(((j-1)*100)+(tmp-1));
					
			}
			if(newNote!=null)
				newBank.add(newNote);
	
			isfirst = false;
		}
		return newBank;
	}
	
	/**
	 * @brief 뱅크를 화면에 뿌려주는 메소드
	 * @param LinkedList<Note> bank 뱅크들의 집합을 가지는 linkedList 자료구조
	 */
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

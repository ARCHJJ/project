import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.LinkedList;

import javax.sound.sampled.AudioSystem;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @brief 체크박스형 뱅크를 저장하는 역할을 담당하는 클래스
 */
class SaveChkBoxBank extends SaveBank{

	//!필드
	SettingToField field;
	
	/**
	 * @brief 생성자
	 * @param SettingToField field 필드
	 */
	SaveChkBoxBank(SettingToField field) {
		this.field = field;
		this.kind = field.getKinds();
	}

	/**
	 * @brief 사용자가 원하는 특정 조건의 뱅크를 호출하는 메소드
	 * @param LinkedList<Integer> beatList 박자의 목록을 가지고 있는 LinkedList형 자료구조
	 * @param int restTime 쉬는 부분을 표현
	 * @return
	 */
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
						temp.fileidx.add(j-1);
					
					temp.rest = (32/beatList.get(i))*restTime;
					isfirst = true;
				}
				else if(chk.isSelected())
					temp.fileidx.add(j-1);
					
			}
			if(temp!=null)
				newBank.add(temp);
	
			isfirst = false;
		}
		return newBank;
	}
	
	/**
	 * @brief 뱅크를 화면에 뿌려주는 역할을 하는 메소드
	 * @param LinkedList<Note> bank 뱅크들의 집합을 가지고 있는 Linkedlist형 자료구조
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


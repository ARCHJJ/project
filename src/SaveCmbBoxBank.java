import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
@SuppressWarnings("rawtypes")
/**
 * @brief 콤보박스로 이루어진 테이블을 뱅크에 저장하는 클래스
 */
class SaveCmbBoxBank extends SaveBank{
	/**
	 * @brief 생성자
	 * @param SettingToField field : 음과 박자가 설정되어 있는 TableModel
	 */
	SaveCmbBoxBank(SettingToField field) {
		this.field = field;
		this.kinds = field.getKinds();
	}
	
	/**
	 * @brief 사용자가 원하는 뱅크를 리턴하는 메소드
	 * @param LinkedList<Integer> beatList : 저장된 박자 데이터를 가지는 LinkedList
	 * @param int restTime				   : 음과 음 사이의 쉬는 시간
	 * @return LinkedList<Note>
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
			for(j=0; j<kinds; j++)
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
	 * @brief 뱅크에 저장된 데이터를 화면에 뿌려주는 역할을 하는 메소드. 디버그용도로 사용된다.
	 * @param LinkedList<Note> bank : 뱅크들의 집합을 가지고 있는 LinkedList
	 */
	public void bankPrint(LinkedList<Note> bank)	//테스트용 메소드
	{
		Iterator<Note> itNote = bank.iterator();
		Iterator<Integer> itPlay;
		
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
		}
	}
}

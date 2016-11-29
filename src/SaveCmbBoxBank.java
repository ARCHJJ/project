import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
@SuppressWarnings("rawtypes")
/**
 * @brief �޺��ڽ��� �̷���� ���̺��� ��ũ�� �����ϴ� Ŭ����
 */
class SaveCmbBoxBank extends SaveBank{
	/**
	 * @brief ������
	 * @param SettingToField field : ���� ���ڰ� �����Ǿ� �ִ� TableModel
	 */
	SaveCmbBoxBank(SettingToField field) {
		this.field = field;
		this.kinds = field.getKinds();
	}
	
	/**
	 * @brief ����ڰ� ���ϴ� ��ũ�� �����ϴ� �޼ҵ�
	 * @param LinkedList<Integer> beatList : ����� ���� �����͸� ������ LinkedList
	 * @param int restTime				   : ���� �� ������ ���� �ð�
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
	 * @brief ��ũ�� ����� �����͸� ȭ�鿡 �ѷ��ִ� ������ �ϴ� �޼ҵ�. ����׿뵵�� ���ȴ�.
	 * @param LinkedList<Note> bank : ��ũ���� ������ ������ �ִ� LinkedList
	 */
	public void bankPrint(LinkedList<Note> bank)	//�׽�Ʈ�� �޼ҵ�
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

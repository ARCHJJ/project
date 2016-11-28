import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;

/**
 * @brief üũ�ڽ��� �̷���� ���̺��� ��ũ�� �����ϴ� Ŭ����
 */
class SaveChkBoxBank extends SaveBank{
	/**
	 * @brief ������
	 * @param SettingToField field : ���� ���ڰ� �����Ǿ� �ִ� TableModel
	 */
	SaveChkBoxBank(SettingToField field) {
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
		JCheckBox chk;
		
		LinkedList<Note> newBank = new LinkedList<Note>();
		Note temp = null;
		DefaultTableModel tablemodel = field.getModel();
		for(i=0; i<tablemodel.getColumnCount(); i++)
		{
			temp = null;
			for(j=0; j<kinds; j++)
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
	 * @brief ��ũ�� ����� �����͸� ȭ�鿡 �ѷ��ִ� ������ �ϴ� �޼ҵ�. ����׿뵵�� ���ȴ�.
	 * @param LinkedList<Note> bank : ��ũ���� ������ ������ �ִ� LinkedList
	 */
	public void bankPrint(LinkedList<Note> bank)	//�׽�Ʈ�� �޼ҵ�
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
			}
		}
		catch(Exception exp)
		{}
	}
	
}


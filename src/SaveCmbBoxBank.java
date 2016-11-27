import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 * @brief �޺��ڽ��� �̷���� ��ũ�� ����
 */
class SaveCmbBoxBank extends SaveBank{
	//!�ʵ�
	SettingToField field;
	
	/**
	 * @brief ������
	 * @param field
	 */
	SaveCmbBoxBank(SettingToField field) {
		this.field = field;
		this.kind = field.getKinds();
	}

	
	/**
	 * @brief ���ǿ� �´� Ư���� ��ũ�� ȣ���ϴ� �޼ҵ�
	 * @param LinkedList<Integer> beatList ������ ����Ʈ���� ���� �ڷᱸ��
	 * @param int restTime ���ºκ�
	 * @return ���ǿ� �´� arraylist ����
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
	 * @brief ��ũ�� ȭ�鿡 �ѷ��ִ� �޼ҵ�
	 * @param LinkedList<Note> bank ��ũ���� ������ ������ linkedList �ڷᱸ��
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
				//Thread.sleep(temp.rest);
			}
		}
		catch(Exception exp)
		{}
	}
	
}

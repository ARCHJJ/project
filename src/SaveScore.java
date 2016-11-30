import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;

import javax.sound.sampled.Clip;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 * @brief ���� ������ ���Ϸ� �����ϴ� Ŭ�����̴�. 
 */
public class SaveScore {
	//! ���� ����� �ϱ����� ��ü
	protected BufferedWriter out;
	
	//! ��ũ ����Ʈ���� �޾ƿ��� ���� ��ü
	protected LinkedList<LinkedList<Note>> BankList;
	
	//! ���� ���½ð��� ����Ǿ� �ִ� LinkedList
	protected LinkedList<Note> savelist;
	
	//! savelist�� Iterator
	protected Iterator<Note> itNote;
	
	//! savelist.fileidx�� Iterator
	protected Iterator<Integer> itSave;
	
	//! �۾�������� ���̺� ���� �޾ƿ��� ���� ��ü
	protected JComboBox selectBank;
	
	//! ������ ���ڸ� �޾ƿ��� ���� ��ü
	protected String time_signature_Index;
	
	//! ������BPM�� �޾ƿ��� ���� ��ü
	protected String BPM;
	
	//! Save ���Ͽ� ����ϱ� ���� ���� �迭
	protected String[] BankKind = {"Keyboard", "Drum", "Guitar", "Base"};
	
	/**
	 * @brief ���� ������ ���ڿ� �ӵ��� �����Ѵ�.
	 * @param String BPMSet		: ������ �ӵ�
	 * @param int BeatSet		: ������ ����
	 */
	public void setBeat(String BPMSet, int BeatSet)
	{
		time_signature_Index = Integer.toString(BeatSet);
		
		BPM = BPMSet;
	}
	
	/**
	 * @brief ��ũ�� �۾�������� ���¸� ���Ϸ� ����Ѵ�.
	 * @param SettingToField STF[]		: ������ ��ũ�� ����ִ� SettingToField
	 * @param JTable table_Task[]		: �۾�������� ���¸� �������ִ� JTable 
	 */
	public void save_Score(SettingToField STF[], JTable table_Task[])
	{
		try 
		{	
			
			out = new BufferedWriter(new FileWriter("out.save"));
		      
		    //System.out.println("���� Ȯ��");
		    
		    out.write("TimeSignature=");
		    out.write(time_signature_Index); 
		    out.newLine();
		    
		    out.write("BPM=");
		    out.write(BPM); 
		    out.newLine();
		    
		    for(int i = 0; i < 4 ; i++)
			{
				BankList = STF[i].BankList;
				
				out.write(BankKind[i]+"BankCount=");
				out.write(Integer.toString(BankList.size()-1)); 
			    out.newLine();
			    
			    for(int j = 1; j <= BankList.size()-1; j++)
			    {
				    savelist = BankList.get(j);
				    
				    itNote = savelist.iterator();
					
					out.write(Integer.toString(savelist.size()));
					out.newLine();
				    
					while(itNote.hasNext())
					{
						Note temp = itNote.next();
						
						out.write(Integer.toString(temp.rest));
						
						itSave = temp.fileidx.iterator();
						
						while(itSave.hasNext())
						{
							out.write("$");
							out.write(Integer.toString(itSave.next()));
						}
						out.newLine();
					}
			    }   	    
			}
		    
		    for(int i = 0; i < 4 ; i++)
			{
		    	out.write(BankKind[i]+"Taskbar=");
				out.write(Integer.toString(table_Task[i].getModel().getColumnCount()-2)); 
			    
			    for(int j = 1; j < table_Task[i].getModel().getColumnCount()-1 ; j++)
				{
			    	selectBank = (JComboBox)table_Task[i].getModel().getValueAt(0, j);
			    	
			    	out.write(Integer.toString(selectBank.getSelectedIndex())); 
			    	out.write("$"); 
				}
			    
			    out.newLine(); 
				    	
			}
		    
		    out.close();

		} 
		
		catch (IOException e) 
		{
			System.err.println(e); 
		    System.exit(1);
		}
	}


}

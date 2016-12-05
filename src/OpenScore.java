import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

@SuppressWarnings("rawtypes")
/**
 * @brief ����� ������ �ҷ����� Ŭ�����̴�. 
 */
public class OpenScore {
	//! ���� �Է��� ���� ��ü
	protected BufferedReader in;

	//! ���̺������� ���پ� �о���� ���� ��ü
	protected String line;
	
	//! ���̺� ������ �������� �ڸ��� ���� ��ü
	protected StringTokenizer st;
	
	//! ������ ��ũ�� �ֱ����� ��ü
	protected JComboBox selectBank;
	
	//! ���� �Ǳ��� ��ũ ��
	protected int BankCount;
	
	//! ���� �Ǳ� ��ũ�� ��Ʈ ��
	protected int NoteCount;
	
	//! ���� �Ǳ��� �۾���� ��
	protected int TaskCount;
	
	/**
	 * @brief ����� ������ �ҷ��´�.
	 * @param JComboBox BeatSet		: ���ڸ� �����ϴ� �޺��ڽ�
	 * @param JTextField BPMSet		: BPM�� �����ϴ� ���̺� �ʵ�
	 * @param SettingToField STF[]	: ��ũ�� ������ �Ǳ��� �ʵ�
	 * @param TaskField STT[]		: �۾�ǥ������ ���¸� ������ �ʵ�
	 * @param JTable table_Task[]	: �۾�ǥ������ �� ���̺�
	 */
	public void open_Score(JComboBox BeatSet, JTextField BPMSet, BeatField STB[], SettingToField STF[], TaskField STT[], JTable table_Task[])
	{
		
		final JFileChooser fc = new JFileChooser();   
		
	    File file;
	    
	    if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
	    	file = fc.getSelectedFile();
	    
	    else  
	    {
	    	JOptionPane.showMessageDialog(null, "������ �����ϼ���.", "����", JOptionPane.ERROR_MESSAGE);
	    	return;
	    }
	    
		for(int i=0; i<4; i++)
		{
			STF[i].BankListClear();
			STT[i].Init();
			STF[i].Init();
			STB[i].Init();
		}
		
		try
		{
			in = new BufferedReader(new FileReader(file));
			
			for(int i = 0; i < 2 ; i++)
			{
				line = in.readLine();
				
				st = new StringTokenizer(line ,"=");
				
				st.nextToken();
				
				if(i == 0)
					BeatSet.setSelectedIndex(Integer.parseInt(st.nextToken()));
				else
					BPMSet.setText(st.nextToken());
			}
			
			for(int i = 0; i < 4 ; i++)
			{
				line = in.readLine();

				st = new StringTokenizer(line ,"=");
				
				st.nextToken();
				BankCount = Integer.parseInt(st.nextToken());	
				

				if(BankCount == 0)
					continue;

				for(int j = 0; j < BankCount; j++)
				{
					LinkedList<Note> itbank = new LinkedList<Note>();
					
					line = in.readLine();
					
					st = new StringTokenizer(line ,"$");
					
					NoteCount = Integer.parseInt(st.nextToken());

					for(int z = 0; z < NoteCount; z++)
					{
						Note itnote = new Note();

						line = in.readLine();
						
						st = new StringTokenizer(line ,"$");
						
						itnote.rest = Integer.parseInt(st.nextToken());
						
						while(st.hasMoreTokens())
						{
							itnote.fileidx.add(Integer.parseInt(st.nextToken()));
						}
						
						itbank.add(itnote);
					}
					
					STF[i].BankList.add(itbank);
					STT[i].reflash(j+1);
					//STT[i].addColumn(1);table_Task
				}
				
				for(int j=0; j<4; j++)
					STT[j].setCellOption(table_Task[j]);
				
			}
			
			for(int i = 0; i < 4 ; i++)
			{
				line = in.readLine();

				st = new StringTokenizer(line ,"=");
				
				st.nextToken();
				
				line = st.nextToken();
				
				if(line.charAt(0) == '0')
					continue;
				
				st = new StringTokenizer(line ,"$");
				
				TaskCount = Integer.parseInt(st.nextToken());
				
				for(int j = 1; j <= TaskCount ; j++)
				{			
					selectBank = (JComboBox)STT[i].getModel().getValueAt(0, j);		
					selectBank.setSelectedIndex(Integer.parseInt(st.nextToken()));
					STT[i].getModel().setValueAt(selectBank, 0, j);
					
					STT[i].addColumn(j);
					
				}
				STT[i].setCellOption(table_Task[i]);
			}			
		    
		    in.close();
		}
		
	        
	        catch (IOException e) 
			{
				System.err.println(e); 
			    System.exit(1);
			}
	}
}

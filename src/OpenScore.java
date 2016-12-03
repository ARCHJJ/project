import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;


public class OpenScore {
	
	protected BufferedReader in;

	protected String line;
	
	protected StringTokenizer st;
	
	protected JComboBox selectBank;
	
	protected int BankCount;
	
	protected int NoteCount;
	
	protected int TaskCount;
	
	public void open_Score(JComboBox BeatSet, JTextField BPMSet, SettingToField STF[], TaskField STT[], JTable table_Task[])
	{
		
		try
		{
			in = new BufferedReader(new FileReader("out.save"));
			
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
					selectBank = (JComboBox)table_Task[i].getModel().getValueAt(0, j);		
					selectBank.setSelectedIndex(Integer.parseInt(st.nextToken()));
					table_Task[i].getModel().setValueAt(selectBank, 0, j);
					
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

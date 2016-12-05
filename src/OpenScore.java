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
 * @brief 저장된 내용을 불러오는 클래스이다. 
 */
public class OpenScore {
	//! 파일 입력을 위한 객체
	protected BufferedReader in;

	//! 세이브파일을 한줄씩 읽어오기 위한 객체
	protected String line;
	
	//! 세이브 파일을 정보별로 자르기 위한 객체
	protected StringTokenizer st;
	
	//! 정보를 뱅크에 넣기위한 객체
	protected JComboBox selectBank;
	
	//! 현재 악기의 뱅크 수
	protected int BankCount;
	
	//! 현재 악기 뱅크의 노트 수
	protected int NoteCount;
	
	//! 현재 악기의 작업대기 수
	protected int TaskCount;
	
	/**
	 * @brief 저장된 파일을 불러온다.
	 * @param JComboBox BeatSet		: 박자를 설정하는 콤보박스
	 * @param JTextField BPMSet		: BPM을 설정하는 테이블 필드
	 * @param SettingToField STF[]	: 뱅크를 가지는 악기의 필드
	 * @param TaskField STT[]		: 작업표시줄의 상태를 가지는 필드
	 * @param JTable table_Task[]	: 작업표시줄이 들어갈 테이블
	 */
	public void open_Score(JComboBox BeatSet, JTextField BPMSet, BeatField STB[], SettingToField STF[], TaskField STT[], JTable table_Task[])
	{
		
		final JFileChooser fc = new JFileChooser();   
		
	    File file;
	    
	    if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
	    	file = fc.getSelectedFile();
	    
	    else  
	    {
	    	JOptionPane.showMessageDialog(null, "파일을 선택하세요.", "오류", JOptionPane.ERROR_MESSAGE);
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

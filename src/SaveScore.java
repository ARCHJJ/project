import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;

import javax.sound.sampled.Clip;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 * @brief 진행 내용을 파일로 저장하는 클래스이다. 
 */
public class SaveScore {
	//! 파일 출력을 하기위한 객체
	protected BufferedWriter out;
	
	//! 뱅크 리스트들을 받아오기 위한 객체
	protected LinkedList<LinkedList<Note>> BankList;
	
	//! 음과 쉬는시간이 저장되어 있는 LinkedList
	protected LinkedList<Note> savelist;
	
	//! savelist의 Iterator
	protected Iterator<Note> itNote;
	
	//! savelist.fileidx의 Iterator
	protected Iterator<Integer> itSave;
	
	//! 작업대기줄의 테이블 모델을 받아오기 위한 객체
	protected JComboBox selectBank;
	
	//! 설정된 박자를 받아오기 위한 객체
	protected String time_signature_Index;
	
	//! 설정된BPM을 받아오기 위한 객체
	protected String BPM;
	
	//! Save 파일에 출력하기 위한 문자 배열
	protected String[] BankKind = {"Keyboard", "Drum", "Guitar", "Base"};
	
	/**
	 * @brief 현재 설정된 박자와 속도를 셋팅한다.
	 * @param String BPMSet		: 설정된 속도
	 * @param int BeatSet		: 설정된 박자
	 */
	public void setBeat(String BPMSet, int BeatSet)
	{
		time_signature_Index = Integer.toString(BeatSet);
		
		BPM = BPMSet;
	}
	
	/**
	 * @brief 뱅크와 작업대기줄의 상태를 파일로 출력한다.
	 * @param SettingToField STF[]		: 설정된 뱅크가 담겨있는 SettingToField
	 * @param JTable table_Task[]		: 작업대기줄의 상태를 가지고있는 JTable 
	 */
	public void save_Score(SettingToField STF[], JTable table_Task[])
	{
		try 
		{	
			
			out = new BufferedWriter(new FileWriter("out.save"));
		      
		    //System.out.println("동작 확인");
		    
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

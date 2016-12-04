import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class InputBassRhythm {

		//! 테이블에 박자를 넣기위한 객체
		protected JComboBox selectBeat;
		
		//! 박자에 대한 정보가 들어있는 객체
		protected String[][] Bassrhytm;
		
		/**
		 * @brief 테이블에 선택한 리듬을 입력한다.
		 * @param String[][] Bassrhytm	: 박자에 대한 정보가 들어있는 객체
		 */
		public void setRhythm(String[][] Bassrhytm)
		{
			this.Bassrhytm = Bassrhytm;
		}
		
		/**
		 * @brief 테이블에 선택한 리듬을 입력한다.
		 * @param int y					: 선택한 리듬
		 * @param SettingToField STF	: 베이스가 셋팅 될 필드
		 * @param BeatField STB			: 노트 당 박자가 셋팅 될 필드
		 * @param JTable Desk			: 셋팅된 필드가 들어갈 테이블
		 */
		public void inputBass(int y, SettingToField STF, BeatField STB, JTable Desk)
		{
			JComboBox temp;
			
			int num;
				
			for(int i=0; '$' != Bassrhytm[y][i].charAt(0); i++)
			{
				if('x' == Bassrhytm[y][i].charAt(0))
				{
					temp = (JComboBox)STF.getModel().getValueAt(0, STF.getModel().getColumnCount()-1);
					temp.setSelectedIndex(1);
					STF.getModel().setValueAt(temp, 0, STF.getModel().getColumnCount()-1);
					
					temp = (JComboBox)STB.getModel().getValueAt(0, STB.getModel().getColumnCount()-1);
					temp.setSelectedIndex(Integer.parseInt(Bassrhytm[y+4][i]));
					STB.getModel().setValueAt(temp, 0, STB.getModel().getColumnCount()-1);
					
					STF.addColumn(STF.getModel().getColumnCount()-1);
					
					continue;
				}

				num = Integer.parseInt(Bassrhytm[y][i]);
				
				temp = (JComboBox)STF.getModel().getValueAt((num/100)+1, STF.getModel().getColumnCount()-1);
				temp.setSelectedIndex((num%100)+1);
				STF.getModel().setValueAt(temp, num/100+1, STF.getModel().getColumnCount()-1);
				
				temp = (JComboBox)STB.getModel().getValueAt(0, STB.getModel().getColumnCount()-1);
				temp.setSelectedIndex(Integer.parseInt(Bassrhytm[y+4][i]));
				STB.getModel().setValueAt(temp, 0, STB.getModel().getColumnCount()-1);
				
				STF.addColumn(STF.getModel().getColumnCount()-1);
				
			}
			
			STF.setCellOption(Desk);
			
		}
		

}

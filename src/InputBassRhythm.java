import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class InputBassRhythm {

		//! ���̺� ���ڸ� �ֱ����� ��ü
		protected JComboBox selectBeat;
		
		//! ���ڿ� ���� ������ ����ִ� ��ü
		protected String[][] Bassrhytm;
		
		/**
		 * @brief ���̺� ������ ������ �Է��Ѵ�.
		 * @param String[][] Bassrhytm	: ���ڿ� ���� ������ ����ִ� ��ü
		 */
		public void setRhythm(String[][] Bassrhytm)
		{
			this.Bassrhytm = Bassrhytm;
		}
		
		/**
		 * @brief ���̺� ������ ������ �Է��Ѵ�.
		 * @param int y					: ������ ����
		 * @param SettingToField STF	: ���̽��� ���� �� �ʵ�
		 * @param BeatField STB			: ��Ʈ �� ���ڰ� ���� �� �ʵ�
		 * @param JTable Desk			: ���õ� �ʵ尡 �� ���̺�
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

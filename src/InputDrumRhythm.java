import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
@SuppressWarnings("rawtypes")

/**
 * @brief �巳�� ���õ� ������ �Է��ϴ� Ŭ�����̴�. 
 */
public class InputDrumRhythm {
	//!	���̺� �巳 ���ڸ� �����ϱ� ���� ��ü
	protected JCheckBox selectNote;
	
	//! ���̺� ���ڸ� �ֱ����� ��ü
	protected JComboBox selectBeat;
	
	//! ���� ���� ��ǥ
	protected int denominator;
		
	//! ���� ���� ���� 
	protected int numerator;
	
	/**
	 * @brief ���ڸ� �����Ѵ�
	 * @param int time_signature_numerator		: ������ǥ
	 * @param int time_signature_denominator	: ���� �ð�
	 */
	public void setBeat(int time_signature_numerator, int time_signature_denominator)
	{
		numerator = time_signature_numerator;
		
		denominator = time_signature_denominator;
		
	}
	
	/**
	 * @brief ���̺� ������ ������ �Է��Ѵ�.
	 * @param int x					: ������ ����
	 * @param int y					: ������ ����
	 * @param SettingToField STF	: �巳�� ���� �� �ʵ�
	 * @param BeatField STB			: ��Ʈ �� ���ڰ� ���� �� �ʵ�
	 * @param JTable Desk			: ���õ� �ʵ尡 �� ���̺�
	 */
	public void inputDrum(int x, int y, SettingToField STF, BeatField STB, JTable Desk)
	{
		switch(x)
		{
		case 0 :
		case 1 :
		case 2 :
		case 3 :
			SimpleTimeSignatures(y, STF, STB);
			break;
		case 5 :
		case 6 :
		case 7 :
			CompoundTimeSignatures(y, STF, STB);
			break;
		case 9 :
		case 10 :
		case 11 :
			ComplexTimeSignatures(y, STF, STB);
			break;
		}
			
		
		
		STF.setCellOption(Desk);
	}
	
	/**
	 * @brief �ܼ�����.
	 * @param int y					: ������ ����
	 * @param SettingToField STF	: �巳�� ���� �� �ʵ�
	 * @param BeatField STB			: ��Ʈ �� ���ڰ� ���� �� �ʵ�
	 */
	public void SimpleTimeSignatures(int y, SettingToField STF, BeatField STB)
	{
		for(int i = 0; i < numerator*y; i++)
		{
			if(i == 0)
			{
				selectNote = (JCheckBox)STF.getModel().getValueAt(1, i);
				selectNote.setSelected(true);
				STF.getModel().setValueAt(selectNote, 1, i);
			}
			
			else if(i%(2*y) == 0)
			{
				selectNote = (JCheckBox)STF.getModel().getValueAt(3, i);
				selectNote.setSelected(true);
				STF.getModel().setValueAt(selectNote, 3, i);
			}
			
			selectNote = (JCheckBox)STF.getModel().getValueAt(2, i);
			selectNote.setSelected(true);
			STF.getModel().setValueAt(selectNote, 2, i);
			
			selectBeat = (JComboBox)STB.getModel().getValueAt(0, i+1);
			selectBeat.setSelectedIndex((denominator/2)+(y/2));
			STB.getModel().setValueAt(selectBeat, 0, i+1);
			
			STF.addColumn(STF.getModel().getColumnCount()-1);
		}
	}
	
	/**
	 * @brief ���ո���.
	 * @param int y					: ������ ����
	 * @param SettingToField STF	: �巳�� ���� �� �ʵ�
	 * @param BeatField STB			: ��Ʈ �� ���ڰ� ���� �� �ʵ�
	 */
	public void CompoundTimeSignatures(int y, SettingToField STF, BeatField STB)
	{
		for(int i = 0; i < numerator*y; i++)
		{
			if(i == 0)
			{
				selectNote = (JCheckBox)STF.getModel().getValueAt(1, i);
				selectNote.setSelected(true);
				STF.getModel().setValueAt(selectNote, 1, i);
			}
			
			else if(i%(3*y) == 0)
			{
				selectNote = (JCheckBox)STF.getModel().getValueAt(3, i);
				selectNote.setSelected(true);
				STF.getModel().setValueAt(selectNote, 3, i);
			}
			
			selectNote = (JCheckBox)STF.getModel().getValueAt(2, i);
			selectNote.setSelected(true);
			STF.getModel().setValueAt(selectNote, 2, i);
			
			selectBeat = (JComboBox)STB.getModel().getValueAt(0, i+1);
			selectBeat.setSelectedIndex((denominator/2)-1+(y/2));
			STB.getModel().setValueAt(selectBeat, 0, i+1);
			
			STF.addColumn(STF.getModel().getColumnCount()-1);
		}
	}
	
	/**
	 * @brief ȥ�ո���.
	 * @param int y					: ������ ����
	 * @param SettingToField STF	: �巳�� ���� �� �ʵ�
	 * @param BeatField STB			: ��Ʈ �� ���ڰ� ���� �� �ʵ�
	 */
	public void ComplexTimeSignatures(int y, SettingToField STF, BeatField STB)
	{
		for(int i = 0; i < numerator*y; i++)
		{
			if(i == 0)
			{
				selectNote = (JCheckBox)STF.getModel().getValueAt(1, i);
				selectNote.setSelected(true);
				STF.getModel().setValueAt(selectNote, 1, i);
			}
			
			else if((i < (numerator*y)-1) && (i%(2*y) == 0))
			{
				selectNote = (JCheckBox)STF.getModel().getValueAt(3, i);
				selectNote.setSelected(true);
				STF.getModel().setValueAt(selectNote, 3, i);
			}
			
			selectNote = (JCheckBox)STF.getModel().getValueAt(2, i);
			selectNote.setSelected(true);
			STF.getModel().setValueAt(selectNote, 2, i);
			
			selectBeat = (JComboBox)STB.getModel().getValueAt(0, i+1);
			selectBeat.setSelectedIndex((denominator/2)+(y/2));
			STB.getModel().setValueAt(selectBeat, 0, i+1);
			
			STF.addColumn(STF.getModel().getColumnCount()-1);
		}
	}
}

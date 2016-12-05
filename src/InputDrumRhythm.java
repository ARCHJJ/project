import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
@SuppressWarnings("rawtypes")

/**
 * @brief 드럼에 선택된 리듬을 입력하는 클래스이다. 
 */
public class InputDrumRhythm {
	//!	테이블에 드럼 박자를 저장하기 위한 객체
	protected JCheckBox selectNote;
	
	//! 테이블에 박자를 넣기위한 객체
	protected JComboBox selectBeat;
	
	//! 박자 기준 음표
	protected int denominator;
		
	//! 박자 기준 길이 
	protected int numerator;
	
	/**
	 * @brief 박자를 셋팅한다
	 * @param int time_signature_numerator		: 기준음표
	 * @param int time_signature_denominator	: 기준 시간
	 */
	public void setBeat(int time_signature_numerator, int time_signature_denominator)
	{
		numerator = time_signature_numerator;
		
		denominator = time_signature_denominator;
		
	}
	
	/**
	 * @brief 테이블에 선택한 리듬을 입력한다.
	 * @param int x					: 선택한 박자
	 * @param int y					: 선택한 리듬
	 * @param SettingToField STF	: 드럼이 셋팅 될 필드
	 * @param BeatField STB			: 노트 당 박자가 셋팅 될 필드
	 * @param JTable Desk			: 셋팅된 필드가 들어갈 테이블
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
	 * @brief 단순리듬.
	 * @param int y					: 선택한 리듬
	 * @param SettingToField STF	: 드럼이 셋팅 될 필드
	 * @param BeatField STB			: 노트 당 박자가 셋팅 될 필드
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
	 * @brief 복합리듬.
	 * @param int y					: 선택한 리듬
	 * @param SettingToField STF	: 드럼이 셋팅 될 필드
	 * @param BeatField STB			: 노트 당 박자가 셋팅 될 필드
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
	 * @brief 혼합리듬.
	 * @param int y					: 선택한 리듬
	 * @param SettingToField STF	: 드럼이 셋팅 될 필드
	 * @param BeatField STB			: 노트 당 박자가 셋팅 될 필드
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

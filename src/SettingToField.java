import java.util.LinkedList;

import javax.swing.JCheckBox;
import javax.swing.JTable;

abstract class SettingToField extends Setting{
	protected Object[][] field;
	protected LinkedList<Boolean> bulb;
	public SettingToField(){
		super();
		bulb = new LinkedList<Boolean>();
		bulb.add(true);
	}
	abstract void addColumn(int idx);
	abstract void setCellOption(JTable Desk);

}

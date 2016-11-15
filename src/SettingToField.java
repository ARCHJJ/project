import java.util.LinkedList;

import javax.swing.JCheckBox;
import javax.swing.JTable;

abstract class SettingToField extends Setting{
	protected LinkedList<Boolean> bulb;
	protected LinkedList<LinkedList<Note>> BankList;
	public SettingToField(){
		super();
		BankList = new LinkedList<LinkedList<Note>>();
		BankList.add(null);
		bulb = new LinkedList<Boolean>();
		bulb.add(true);
	}
	
	abstract void addColumn(int idx);
	abstract void setCellOption(JTable Desk);
	abstract void Init();
	abstract int getKinds();

}

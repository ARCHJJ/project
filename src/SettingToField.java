import java.util.LinkedList;

import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 * @brief Setting클래스를 상속받는 클래스. 체크박스 혹은 콤보박스가 저장될 수 있도록 Object[][]과 추가된 행이 첫 입력인지 아닌지 판단하는 LinkedList<Boolean>으로 이루어져 있다.
 */
abstract class SettingToField extends Setting{
	//!
	protected LinkedList<Boolean> bulb;
	//!
	protected LinkedList<LinkedList<Note>> BankList;
	
	/**
	 * @brief
	 */
	public SettingToField(){
		super();
		BankList = new LinkedList<LinkedList<Note>>();
		BankList.add(null);
		bulb = new LinkedList<Boolean>();
		bulb.add(true);
	}
	
	/**
	 * @brief
	 * @param int idx
	 */
	abstract void addColumn(int idx);
	
	/**
	 * @brief
	 * @param JTable Desk
	 */
	abstract void setCellOption(JTable Desk);
	
	/**
	 * @brief
	 */
	abstract void Init();
	
	/**
	 * @brief
	 */
	abstract int getKinds();

}

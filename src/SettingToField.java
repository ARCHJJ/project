import java.util.LinkedList;

import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 * @brief SettingŬ������ ��ӹ޴� Ŭ����. üũ�ڽ� Ȥ�� �޺��ڽ��� ����� �� �ֵ��� Object[][]�� �߰��� ���� ù �Է����� �ƴ��� �Ǵ��ϴ� LinkedList<Boolean>���� �̷���� �ִ�.
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

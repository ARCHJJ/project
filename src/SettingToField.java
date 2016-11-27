import java.util.LinkedList;

import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 * @brief SettingŬ������ ��ӹ޴� Ŭ����. 
 * üũ�ڽ� Ȥ�� �޺��ڽ��� ����� �� �ֵ��� Object[][]�� �߰��� ���� ù �Է����� �ƴ��� �Ǵ��ϴ�
 * LinkedList<Boolean>���� �̷���� �ִ�.
 */
abstract class SettingToField extends Setting{
	//!
	protected LinkedList<Boolean> bulb;
	//!��ũ�� ���
	protected LinkedList<LinkedList<Note>> BankList;
	
	/**
	 * @brief �ʵ�κ��� �ʱ⼼�����ִ� ������
	 */
	public SettingToField(){
		super();
		BankList = new LinkedList<LinkedList<Note>>();
		BankList.add(null);
		bulb = new LinkedList<Boolean>();
		bulb.add(true);
	}
	
	/**
	 * @brief �÷� �߰�
	 * @param int idx �÷� �ε���
	 */
	abstract void addColumn(int idx);
	
	/**
	 * @brief �� �ɼ��� �����ϴ� �޼ҵ�
	 * @param JTable Desk �۾�ǥ������ ����
	 */ 
	abstract void setCellOption(JTable Desk);
	
	/**
	 * @brief �ʱ�ȭ �޼ҵ�
	 */
	abstract void Init();
	
	/**
	 * @brief �Ǳ����� ��� �޼ҵ�
	 */
	abstract int getKinds();

}

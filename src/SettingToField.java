import java.util.LinkedList;
import javax.swing.JTable;

/**
 * @brief SettingŬ������ ��ӹ޴� Ŭ����. 
 * üũ�ڽ� Ȥ�� �޺��ڽ��� ����� �� �ֵ��� Object[][]�� �߰��� ���� ù �Է����� �ƴ��� �Ǵ��ϴ�
 * LinkedList<Boolean>���� �̷���� �ִ�.
 */
abstract class SettingToField extends Setting{
	//! �߰��� ���� ù �Է����� �Ǵ��ϴ� �����Ͱ� ����Ǿ� �ִ� LinkedList
	protected LinkedList<Boolean> bulb;

	//! ��ũ�� �����ϴ� LinkedList
	protected LinkedList<LinkedList<Note>> BankList;
	
	//! kinds  : �Ǳ��� ����(����)
	//! colCnt : JTable�� �� 
	protected int kinds, colCnt = 1;
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
	 * @brief BankList �� �ʱ�ȭ �ϴ� �޼ҵ�
	 */
	public void BankListClear()
	{
		BankList.clear();
		BankList.add(null);
		bulb.clear();
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

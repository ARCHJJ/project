import java.util.LinkedList;
import javax.swing.JComboBox;
import javax.swing.JTable;
@SuppressWarnings({ "rawtypes", "unchecked" })
/**
 * @brief GUI���� �۾�������� �����ϴ� Ŭ����
 * SettingToField�� ��ӹ޾Ƽ� DefaultTableModel�� Object[][]�� ������ �ִ�. 
 * �޺��ڽ��� �̷���� DefaultTableModel�� ������ش�.
 */
class TaskField extends SettingToField{
	//! JTable���� �޺��ڽ��� �̿��� ���� �ְ��� �� �� �ʿ��� ������
	private CmbBoxEditor cmbboxEditor;
	
	//! JTable���� �޺��ڽ��� ���̰Բ� ���ִ� ������
	private CmbBoxRenderer cmbboxRenderer;
	
	//! DefaultTableModel�� �� �޺��ڽ�
	private JComboBox BankList;
	
	//! �޺��ڽ��� �� ������
	private Object[] itemList;
	
	//! �޺��ڽ��� �� �����Ͱ� �߰��� ��� LinkedList<String>�� ������ �� �迭ȭ ��Ų��.
	private LinkedList<String> items;
	
	//! �Ǳ� �̸�
	private String instrument;
	/**
	 * @brief ������. �ʱ���¸� �������ش�.
	 * @param String instrument : �Ǳ��̸��� �������ش�.
	 */
	public TaskField(String instrument)
	{
		this.instrument = instrument;
		items = new LinkedList<String>();
		items.add("");
		itemList = items.toArray();
		BankList = new JComboBox(itemList);
		
		cmbboxEditor = new CmbBoxEditor(BankList, this);
		cmbboxRenderer = new CmbBoxRenderer();
		
		header = new String[]{"�Ǳ�", "1"};
		field = new Object[1][2];
		field[0][0] = instrument;
		field[0][1] = BankList;
		
		tablemodel.setDataVector(field, header);
	}
	
	/**
	 * @brief �޺��ڽ��� ���� �����Ͱ� �ԷµǸ� ���� �����͸� �Է¹ޱ� ���� ���ο� ���� �߰��ϴ� �޼ҵ�
	 * @param int idx : �� ��ȣ
	 */
	public void addColumn(int idx) {
		idx--;
		if(bulb.get(idx))
		{
			Object[] newData = new Object[1];
			newData[0] = new JComboBox(itemList);
			tablemodel.addColumn(Integer.toString(++colCnt), newData);
			
			bulb.set(idx, false);
			bulb.add(true);
		}
	}
	
	/**
	 * @brief ���� �߰��ϰ� ���� �ٽ� �������� �����͸� ������� �ִ� �޼ҵ�
	 * @param JTable Desk : �������� �����Ͱ� ����� JTable
	 */
	public void setCellOption(JTable Desk) {
		Desk.getColumnModel().getColumn(0).setPreferredWidth(63);	
		for(int i=1; i<Desk.getModel().getColumnCount(); i++)
		{
			Desk.getColumnModel().getColumn(i).setPreferredWidth(50);			
			Desk.getColumnModel().getColumn(i).setCellRenderer(cmbboxRenderer);
			Desk.getColumnModel().getColumn(i).setCellEditor(cmbboxEditor);
		}
	}
	
	/**
	 * @brief �θ�Ŭ������ abstract �޼ҵ�. TaskField Ŭ���������� ������� �ʴ´�.
	 */
	@Override
	public void removeColumn() {}
	
	/**
	 * @brief ���� ���̺��� �ʱ�ȭ�ϴ� �޼ҵ� 
	 */
	@Override
	public void Init() {
		items.clear();
		items.add("");
		BankList.removeAllItems();
		BankList.addItem("");
		
		bulb.clear();
		bulb.add(true);
		colCnt = 1;

		header = new String[]{"�Ǳ�", "1"};
		field = new Object[1][2];
		field[0][0] = instrument;
		field[0][1] = BankList;
		tablemodel.setDataVector(field, header);
	}
	
	/**
	 * @brief ��ũ������ ���� ���ο� ��ũ�� �߰��Ǹ� �޺��ڽ��� �����͸� �߰���Ű�� �޼ҵ�
	 * @param int size �޺��ڽ��� ������ �ִ� ������ ����
	 */
	public void reflash(int size)
	{
		JComboBox temp;
		for(int i=1; i<tablemodel.getColumnCount(); i++)
		{
			temp = (JComboBox)tablemodel.getValueAt(0, i);
			temp.addItem(Integer.toString(size));
		}
		items.add(Integer.toString(size));
		itemList = items.toArray();
	}
	
	/**
	 * @brief �޺��ڽ��� �̷�� �ִ� �����͸� �����ϴ� �޼ҵ�
	 * @return Object[] itemList : �޺��ڽ��� �̷�� �ִ� �����͹迭
	 */
	public Object[] getItemList()
	{
		return itemList;
	}

}

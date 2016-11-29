import java.util.LinkedList;

import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 * @brief SettingToField�� ��ӹ޴� Ŭ���� üũ�ڽ��� ������ TableModel�μ� Ÿ�Ǳ⸦ ������ �� �ִ�.
 */
class ChkBoxField extends SettingToField{

	//! JTable�� üũ�ڽ��� �߰���Ű�� ���� ��������
	private ChkBoxRenderer chkboxRenderer;

	//! JTable�� üũ�ڽ��� �߰���Ű�� ���� ��������
	private ChkBoxEditor chkboxEditor;
	
	//! ���� ���̺� �𵨿� ���� �߰��Ǹ� BeatField�� �Բ� �߰��ǰ� �ϱ� ���� ���������ڷ� ���޹���
	private BeatField BeatField;
	
	//! BeatField�� ���� �߰��� �� �� �����Ϳ� �������� �ٽ� �ѹ� �����Ű�� ���� ���������ڷ� ���޹���
	private JTable table_Beat;
	
	/**
	 * @brief ������
	 * @param BeatField BeatField : ���� ���̺� �𵨿� ���� �߰��Ǹ� BeatField�� �Բ� �߰��� �� �ֵ��� �޾ƿ�
	 * @param JTable table_Beat	  : BeatField�� ����� JTable
	 * @param int kinds			  : �ش��ϴ� �ǱⰡ �� �� �ִ� ���� ����
	 */
	public ChkBoxField(BeatField BeatField, JTable table_Beat, int kinds)
	{
		this.BeatField = BeatField;
		this.table_Beat = table_Beat;
		this.kinds = kinds;
		
		chkboxRenderer = new ChkBoxRenderer();
		chkboxEditor = new ChkBoxEditor(new JCheckBox(), this);
		
		header = new String[]{"1"};
		field = new Object[kinds][colCnt];
		for(int i=0; i<kinds; i++)
			field[i][0] = new JCheckBox();
		
		tablemodel.setDataVector(field, header);
	}
	
	/**
	 * @brief �÷��� ���� �߰��ϴ� �޼ҵ�
	 * @param int idx ���� ���° �÷������� ����Ű�� �ε���
	 */
	@Override
	public void addColumn(int idx) {
		if(bulb.get(idx))
		{
			Object[] newData = new Object[kinds];
			for(int i=0; i<kinds; i++)
				newData[i] = new JCheckBox(); 
			tablemodel.addColumn(Integer.toString(++colCnt), newData);
			
			bulb.set(idx, false);
			bulb.add(true);
			
			BeatField.addColumn(idx);
			BeatField.setCellOption(table_Beat);
		}
	}
	
	/**
	 * @brief ���� �߰��ϰ� ���� �ٽ� �������� �����͸� ������� �ִ� �޼ҵ�
	 * @param JTable Desk : �������� �����Ͱ� ����� JTable
	 */
	@Override
	public void setCellOption(JTable Desk) {
		for(int i=0; i<Desk.getModel().getColumnCount(); i++)
		{
			Desk.getColumnModel().getColumn(i).setPreferredWidth(50);
			Desk.getColumnModel().getColumn(i).setCellRenderer(chkboxRenderer);
			Desk.getColumnModel().getColumn(i).setCellEditor(chkboxEditor);
		}
	}
	
	/**
	 * @brief ���� ���̺��� �ʱ�ȭ�ϴ� �޼ҵ�
	 */
	@Override
	public void Init() {
		bulb.clear();
		bulb.add(true);
		colCnt = 1;
		header = new String[]{"1"};
		field = new Object[kinds][colCnt];
		for(int i=0; i<kinds; i++)
			field[i][0] = new JCheckBox();
		
		tablemodel.setDataVector(field, header);
	}
	
	/**
	 * @brief �Ǳ������� ���� �ϴ� �޼ҵ�
	 * @return �Ǳ����� ����
	 */
	public int getKinds()
	{
		return kinds;
	}
}

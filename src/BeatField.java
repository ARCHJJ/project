import java.util.LinkedList;

import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 * @brief SettingToFeild�� ��ӹ޴� Ŭ�����ν� N����ǥ�� �����ϰ� �Ҽ� �ִ� �κ��� ǥ���ϴ� JTable�� ����� Ŭ�����̴�. 
 */
class BeatField extends SettingToField{
	
	//! JTable���� �޺��ڽ��� ����� �� �ְԲ� ���ִ� ��������
	private CmbBoxEditor cmbboxEditor;
	
	//! JTable���� �޺��ڽ��� �� �� �ְ� ���ִ� ��������
	private CmbBoxRenderer cmbboxRenderer;
	
	//! �޺��ڽ��� �����۸���Ʈ
	private String[] beat = {"1", "2", "4", "8", "16", "32"};
	
	//! JTable���� ��� �� �⺻ �޺��ڽ�
	private JComboBox beatList;
	
	//! ���� ���̺� �ִ� �޺��ڽ����� ���� �����ϴ� ��ũ�帮��Ʈ
	private LinkedList<Integer> beatResult;
	
	//! ���� ���̺� �ִ� �޺��ڽ����� ���� �� ��
	private int max = 0;
	
	/**
	 * @brief ������
	 * ��ü�� �����Ǹ� �⺻ ��� ���� �����Ѵ�.
	 */
	public BeatField()
	{
		super();
		beatList = new JComboBox(beat);
		beatList.setSelectedIndex(2);
		
		beatResult = new LinkedList<Integer>();
		cmbboxEditor = new CmbBoxEditor(beatList, null);
		cmbboxRenderer = new CmbBoxRenderer();
		
		header = new String[]{"", "1"};
		field = new Object[1][2];
		field[0][0] = "�����ǥ";
		field[0][1] = beatList;

		tablemodel.setDataVector(field, header);
	}
	
	/**
	 * @brief ������ �Է½� ���� �÷��� �߰��ϴ� �޼ҵ�
	 * @param int idx : �ε��� ��ȣ
	 */
	@Override
	public void addColumn(int idx) {
		if(bulb.get(idx))
		{
			Object[] newData = new Object[1];
			newData[0] = new JComboBox(beat);
			((JComboBox) newData[0]).setSelectedIndex(2);
			tablemodel.addColumn(Integer.toString(++colCnt), newData);
			
			bulb.set(idx, false);
			bulb.add(true);
		}
	}
	
	/**
	 * @brief ���� �߰��ϰ� ���� �ٽ� �������� �����͸� ������� �ִ� �޼ҵ�
	 * @param JTable Desk : �������� �����Ͱ� ����� JTable
	 */
	@Override
	public void setCellOption(JTable Desk) 
	{
		Desk.getColumnModel().getColumn(0).setPreferredWidth(63);	
		for(int i=1; i<Desk.getModel().getColumnCount(); i++)
		{
			Desk.getColumnModel().getColumn(i).setPreferredWidth(50);			
			Desk.getColumnModel().getColumn(i).setCellRenderer(cmbboxRenderer);
			Desk.getColumnModel().getColumn(i).setCellEditor(cmbboxEditor);
		}
		
	}
	
	/**
	 * @brief ���� ���̺� �ִ� �޺��ڽ����� ���� �� ���� �����ϴ� �޼ҵ�
	 * @param JTable Desk 
	 * @return int max 
	 */
	public int out_max(JTable Desk)
	{
		JComboBox temp;
		String item = null;
		max = 0;
		
		for(int i=1; i<Desk.getModel().getColumnCount()-1; i++)
		{
			temp = (JComboBox)Desk.getModel().getValueAt(0, i);
			item = (String)temp.getSelectedItem();
			max += 32/(Integer.parseInt(item));
		}
		
		return max;
	}
	
	/**
	 * @brief ���� ���̺� �ִ� �޺��ڽ����� ���� ������ ��ũ�帮��Ʈ�� �����ϴ� �޼ҵ�
	 * @return LinkedList<Integer> beatResult
	 */
	public LinkedList<Integer> getBeatResult()
	{
		JComboBox temp;
		String item;
		beatResult.clear();
		for(int i=1; i<tablemodel.getColumnCount(); i++)
		{
			temp = (JComboBox)tablemodel.getValueAt(0, i);
			item = (String)temp.getSelectedItem();
			beatResult.add(Integer.parseInt(item));
		}
		return beatResult;
	}
	
	/**
	 * @brief ���� ���̺��� �ʱ�ȭ�ϴ� �޼ҵ� 
	 */
	@Override
	public void Init() {
		bulb.clear();
		bulb.add(true);
		colCnt = 1;
		field = new Object[1][2];
		field[0][0] = "�����ǥ";
		field[0][1] = beatList;

		tablemodel.setDataVector(field, new String[]{" ", "1"});
	}
	
	/**
	 * @brief �θ�Ŭ������ abstract �޼ҵ�. TaskField Ŭ���������� ������� �ʴ´�.
	 * @return -1 : ������� �ʱ� ������ -1�� �����Ѵ�.
	 */
	@Override
	public int getKinds() {
		return -1;
	}
}

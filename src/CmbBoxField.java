import javax.swing.JComboBox;
import javax.swing.JTable;
@SuppressWarnings({"unchecked", "rawtypes"})
/**
 * @brief SettingToField�� ��ӹ޴� Ŭ����. �޺��ڽ��� ������ TableModel�μ� ��Ÿ, ���̽�, �ǾƳ� ���� �ǱⰡ ǥ���� �� �ִ� ���� �޺��ڽ��� �̿��Ͽ� �����Ѵ�.
 */
class CmbBoxField extends SettingToField {

	//! JTable���� �޺��ڽ��� ����� �� �ְԲ� ���ִ� ��������
	private CmbBoxEditor cmbboxEditor;
	
	//! JTable���� �޺��ڽ��� �� �� �ְ� ���ִ� ��������
	private CmbBoxRenderer cmbboxRenderer;
	
	//!BeatField�� ���� �߰��� �� �������Ϳ� �������� �ٽ� �ѹ� �����Ű�� ���� ���������ڷ� ���޹���
	private String[] tones;
	
	//!���� ���̺� �𵨿� ���� �߰��Ǹ� BeatField�� �Բ� �߰��ǰ� �ϱ����� ���������ڷ� ���޹���
	private BeatField BeatField;
	
	//!BeatField�� ���� �߰��� �� �������Ϳ� �������� �ٽ� �ѹ� �����Ű�� ���� ���������ڷ� ���޹���
	private JTable table_Beat;
	
	/**
	 * @brief �޺��ڽ��� �̷���� ���� �ϳ� �߰��Ͽ� �ʱⰪ�� �����Ѵ�.
	 * @param BeatField BeatField : ���� ���̺� �𵨿� ���� �߰��Ǹ� BeatField�� �Բ� �߰��� �� �ֵ��� �޾ƿ�
	 * @param JTable table_Beat	  : BeatField�� ����� JTable
	 * @param int kinds			  : �ش��ϴ� �ǱⰡ �� �� �ִ� ���� ����
	 * @param String[] tones 	  : �޺��ڽ��� ��Ÿ�� �����۸���Ʈ
	 */
	public CmbBoxField(BeatField BeatField, JTable table_Beat, int kinds, String[] tones)
	{
		this.BeatField = BeatField;
		this.table_Beat = table_Beat;
		this.kinds = kinds;
		this.tones = tones;
		
		JComboBox toneList = new JComboBox(tones);
		cmbboxEditor = new CmbBoxEditor(toneList, this);
		cmbboxRenderer = new CmbBoxRenderer();
		
		header = new String[]{"1"};
		field = new Object[kinds][colCnt];
		for(int i=0; i<kinds; i++)
			field[i][0] = new JComboBox(tones);
		
		tablemodel.setDataVector(field, header);
	}
	
	/**
	 * @brief �÷��� ���� �߰��ϴ� �޼ҵ�
	 * @param int idx : ���� �� ��° �÷������� ����Ű�� �ε���
	 */
	@Override
	public void addColumn(int idx) {
		if(bulb.get(idx))
		{
			Object[] newData = new Object[kinds];
			for(int i=0; i<kinds; i++)
				newData[i] = new JComboBox(tones);
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
			Desk.getColumnModel().getColumn(i).setCellRenderer(cmbboxRenderer);
			Desk.getColumnModel().getColumn(i).setCellEditor(cmbboxEditor);
		}
	}
	
	/**
	 * @brief J���̺��� �ʱ�ȭ�ϴ� �޼ҵ�
	 */
	@Override
	public void Init() {
		bulb.clear();
		bulb.add(true);
		colCnt = 1;
		header = new String[]{"1"};
		field = new Object[kinds][colCnt];
		for(int i=0; i<kinds; i++)
			field[i][0] = new JComboBox(tones);
		
		tablemodel.setDataVector(field, header);
	}
	
	/**
	 * @brief �Ǳ������� ���� �ϴ� �޼ҵ�
	 * @return int kinds : �Ǳ����� ����
	 */
	public int getKinds()
	{
		return kinds;
	}
}

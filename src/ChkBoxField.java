import java.util.LinkedList;

import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 * @brief SettingToField�� ��ӹ޴� Ŭ���� üũ�ڽ��� ������ TableModel�μ� Ÿ�Ǳ⸦ ������ �� �ִ�.
 */
class ChkBoxField extends SettingToField{

	//! 
	private ChkBoxRenderer chkboxRenderer;
	//! 
	private ChkBoxEditor chkboxEditor;
	//! 
	private BeatField BeatField;
	//! 
	private JTable table_Beat;
	//! 
	private int kinds, rowcnt = 1;
	
	/**
	 * @brief ������
	 * @param BeatField BeatField
	 * @param JTable table_Beat
	 * @param int kinds
	 */
	public ChkBoxField(BeatField BeatField, JTable table_Beat, int kinds)
	{
		super();
		this.BeatField = BeatField;
		this.table_Beat = table_Beat;
		this.kinds = kinds;
		
		chkboxRenderer = new ChkBoxRenderer();
		chkboxEditor = new ChkBoxEditor(new JCheckBox(), this);
		
		header = new String[]{"1"};
		field = new Object[kinds][rowcnt];
		for(int i=0; i<kinds; i++)
			field[i][0] = new JCheckBox();
		
		tablemodel.setDataVector(field, header);
	}
	
	/**
	 * @brief 
	 * @param int idx
	 */
	@Override
	public void addColumn(int idx) {
		if(bulb.get(idx))
		{
			Object[] newData = new Object[kinds];
			for(int i=0; i<kinds; i++)
				newData[i] = new JCheckBox(); 
			tablemodel.addColumn(Integer.toString(++rowcnt), newData);
			
			bulb.set(idx, false);
			bulb.add(true);
			
			BeatField.addColumn(idx);
			BeatField.setCellOption(table_Beat);
		}
	}
	
	/**
	 * @brief 
	 * @param JTable Desk
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
	 * @brief 
	 */
	@Override
	public void Init() {
		bulb.clear();
		bulb.add(true);
		rowcnt = 1;
		header = new String[]{"1"};
		field = new Object[kinds][rowcnt];
		for(int i=0; i<kinds; i++)
			field[i][0] = new JCheckBox();
		
		tablemodel.setDataVector(field, header);
	}
	
	/**
	 * @brief 
	 * @return
	 */
	public int getKinds()
	{
		return kinds;
	}
}

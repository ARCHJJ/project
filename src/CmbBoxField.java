import java.util.LinkedList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 * @brief SettingToField를 상속받는 클래스. 콤보박스로 구성된 TableModel로서 기타, 베이스, 피아노 등의 악기가 표현할 수 있는 음을 콤보박스를 이용하여 선택한다.
 */
class CmbBoxField extends SettingToField {

	//! 
	private CmbBoxEditor cmbboxEditor;
	//! 
	private CmbBoxRenderer cmbboxRenderer;
	//! 
	private String[] tones;
	//! 
	private BeatField BeatField;
	//! 
	private JTable table_Beat;
	//! 
	private int kinds, rowcnt = 1;
	
	/**
	 * @brief 
	 * @param BeatField BeatField
	 * @param JTable table_Beat
	 * @param int kinds
	 * @param String[] tones
	 */
	public CmbBoxField(BeatField BeatField, JTable table_Beat, int kinds, String[] tones)
	{
		super();
		this.BeatField = BeatField;
		this.table_Beat = table_Beat;
		this.kinds = kinds;
		this.tones = tones;
		
		JComboBox toneList = new JComboBox(tones);
		cmbboxEditor = new CmbBoxEditor(toneList, this);
		cmbboxRenderer = new CmbBoxRenderer();
		
		header = new String[]{"1"};
		field = new Object[kinds][rowcnt];
		for(int i=0; i<kinds; i++)
			field[i][0] = new JComboBox(tones);
		
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
				newData[i] = new JComboBox(tones);
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
			Desk.getColumnModel().getColumn(i).setCellRenderer(cmbboxRenderer);
			Desk.getColumnModel().getColumn(i).setCellEditor(cmbboxEditor);
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
			field[i][0] = new JComboBox(tones);
		
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

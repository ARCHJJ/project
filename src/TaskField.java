import java.util.LinkedList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
@SuppressWarnings({ "rawtypes", "unchecked" })
/**
 * @brief 
 */
class TaskField extends SettingToField{
	//!
	private CmbBoxEditor cmbboxEditor;
	//!
	private CmbBoxRenderer cmbboxRenderer;
	//!
	private JComboBox BankList;
	//!
	private Object[] itemList;
	//!
	private LinkedList<String> items;
	//!
	private int rowcnt = 1;
	
	/**
	 * @brief 
	 * @param String instrument
	 */
	public TaskField(String instrument)
	{
		super();
		items = new LinkedList<String>();
		items.add("");
		itemList = items.toArray();
		BankList = new JComboBox(itemList);
		
		cmbboxEditor = new CmbBoxEditor(BankList, this);
		cmbboxRenderer = new CmbBoxRenderer();
		
		header = new String[]{"¾Ç±â", "1"};
		field = new Object[1][2];
		field[0][0] = instrument;
		field[0][1] = BankList;
		
		tablemodel.setDataVector(field, header);
	}
	
	/**
	 * @brief 
	 * @param int idx
	 */
	public void addColumn(int idx) {
		idx--;
		if(bulb.get(idx))
		{
			Object[] newData = new Object[1];
			newData[0] = new JComboBox(itemList);
			tablemodel.addColumn(Integer.toString(++rowcnt), newData);
			
			bulb.set(idx, false);
			bulb.add(true);
		}
	}
	
	/**
	 * @brief 
	 * @param JTable Desk
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
	 * @brief 
	 */
	@Override
	public void Init() {
				
	}
	
	/**
	 * @brief 
	 * @return
	 */
	@Override
	public int getKinds() {
		return -1;
	}
	
	/**
	 * @brief 
	 * @param int size
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
	 * @brief 
	 * @return
	 */
	public Object[] getItemList()
	{
		return itemList;
	}
}

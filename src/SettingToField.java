import java.util.LinkedList;

import javax.swing.JCheckBox;
import javax.swing.JTable;

class SettingToField extends Setting{
	private Object[][] field;
	private ChkBoxRenderer chkboxRenderer;
	private ChkBoxEditor chkboxEditor;
	private LinkedList<Boolean> bulb;
	private SettingToBeat beat;
	private int kinds, rowcnt = 1;
	
	public SettingToField(int kinds, SettingToBeat beat)
	{
		super();
		this.kinds = kinds;
		this.beat = beat;
		chkboxRenderer = new ChkBoxRenderer();
		chkboxEditor = new ChkBoxEditor(new JCheckBox(), this);
		bulb = new LinkedList<Boolean>();
		bulb.add(true);
		field = new Object[kinds][rowcnt];
		for(int i=0; i<kinds; i++)
			field[i][0] = new JCheckBox();
		tablemodel.setDataVector(field, new String[]{"1"});
	}
	public void addColumn(int idx)
	{
		if(bulb.get(idx))
		{
			Object[] newData = new Object[kinds];
			for(int i=0; i<kinds; i++)
				newData[i] = new JCheckBox(); 
			tablemodel.addColumn(Integer.toString(++rowcnt), newData);
			
			bulb.set(idx, false);
			bulb.add(true);
			
			beat.addColumn(idx);
		}
	}
	public void setCellOption(JTable Desk)
	{
		for(int i=0; i<Desk.getModel().getColumnCount(); i++)
		{
			Desk.getColumnModel().getColumn(i).setPreferredWidth(40);
			Desk.getColumnModel().getColumn(i).setCellRenderer(chkboxRenderer);
			Desk.getColumnModel().getColumn(i).setCellEditor(chkboxEditor);
		}
		
	}
	public int getKinds()
	{
		return kinds;
	}
}

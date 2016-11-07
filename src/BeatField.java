import java.util.LinkedList;

import javax.swing.JComboBox;
import javax.swing.JTable;

class BeatField extends SettingToField{

	private CmbBoxEditor cmbboxEditor;
	private CmbBoxRenderer cmbboxRenderer;
	private String[] beat = {"1", "2", "4", "8", "16", "32"};
	private int rowcnt = 1;
	BeatField()
	{
		super();
		JComboBox beatList = new JComboBox(beat);
		beatList.setSelectedIndex(2);
		
		cmbboxEditor = new CmbBoxEditor(beatList, null);
		cmbboxRenderer = new CmbBoxRenderer();
		
		field = new Object[1][2];
		field[0][0] = "¡àºÐÀ½Ç¥";
		field[0][1] = beatList;

		tablemodel.setDataVector(field, new String[]{" ", "1"});
	}
	@Override
	void addColumn(int idx) {
		if(bulb.get(idx))
		{
			Object[] newData = new Object[1];
			newData[0] = new JComboBox(beat);
			((JComboBox) newData[0]).setSelectedIndex(2);
			tablemodel.addColumn(Integer.toString(++rowcnt), newData);
			
			bulb.set(idx, false);
			bulb.add(true);
		}
	}
	@Override
	void setCellOption(JTable Desk) {
		Desk.getColumnModel().getColumn(0).setPreferredWidth(60);	
		for(int i=1; i<Desk.getModel().getColumnCount(); i++)
		{
			Desk.getColumnModel().getColumn(i).setPreferredWidth(50);			
			Desk.getColumnModel().getColumn(i).setCellRenderer(cmbboxRenderer);
			Desk.getColumnModel().getColumn(i).setCellEditor(cmbboxEditor);
		}
	}
}

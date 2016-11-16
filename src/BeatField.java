import java.util.LinkedList;

import javax.swing.JComboBox;
import javax.swing.JTable;

class BeatField extends SettingToField{
	private CmbBoxEditor cmbboxEditor;
	private CmbBoxRenderer cmbboxRenderer;
	private JComboBox beatList;
	private String[] beat = {"1", "2", "4", "8", "16", "32"};
	private LinkedList<Integer> beatResult;
	private int rowcnt = 1, max = 0;
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
		field[0][0] = "□분음표";
		field[0][1] = beatList;

		tablemodel.setDataVector(field, header);
	}
	@Override
	public void addColumn(int idx) {
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
	public void setCellOption(JTable Desk) {
		JComboBox temp;
		String item;
		max = 0;
		Desk.getColumnModel().getColumn(0).setPreferredWidth(63);	
		for(int i=1; i<Desk.getModel().getColumnCount(); i++)
		{
			Desk.getColumnModel().getColumn(i).setPreferredWidth(50);			
			Desk.getColumnModel().getColumn(i).setCellRenderer(cmbboxRenderer);
			Desk.getColumnModel().getColumn(i).setCellEditor(cmbboxEditor);
			
			temp = (JComboBox)Desk.getModel().getValueAt(0, i);
			item = (String)temp.getSelectedItem();
			max += (32/Integer.parseInt(item));
		}
	}
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
	@Override
	public void Init() {
		bulb.clear();
		bulb.add(true);
		rowcnt = 1;
		field = new Object[1][2];
		field[0][0] = "□분음표";
		field[0][1] = beatList;

		tablemodel.setDataVector(field, new String[]{" ", "1"});
	}
	@Override
	public int getKinds() {
		return -1;
	}
}

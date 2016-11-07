//import java.util.LinkedList;
//import javax.swing.DefaultCellEditor;
//import javax.swing.JCheckBox;
//import javax.swing.JComboBox;
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableCellRenderer;
//
//class SettingToBeat extends Setting{
//
//	private CmbBoxEditor cmbboxEditor;
//	private CmbBoxRenderer cmbboxRenderer;
//	private Object[][] field;
//	private String[] beat = {"1", "2", "4", "8", "16", "32"};
//	private JComboBox beatList;
//	private LinkedList<Boolean> bulb;
//	private int rowcnt = 1;
//	SettingToBeat()
//	{
//		beatList = new JComboBox(beat);
//		beatList.setSelectedIndex(2);
//		
//		cmbboxEditor = new CmbBoxEditor(beatList);
//		cmbboxRenderer = new CmbBoxRenderer();
//		bulb = new LinkedList<Boolean>();
//		bulb.add(true);
//		
//		field = new Object[1][2];
//		field[0][0] = "¡àºÐÀ½Ç¥";
//		field[0][1] = beatList;
//
//		tablemodel.setDataVector(field, new String[]{" ", "1"});
//	}
//	public void addColumn(int idx)
//	{
//		if(bulb.get(idx))
//		{
//			Object[] newData = new Object[1];
//			newData[0] = new JComboBox(beat);
//			((JComboBox) newData[0]).setSelectedIndex(2);
//			tablemodel.addColumn(Integer.toString(++rowcnt), newData);
//			
//			bulb.set(idx, false);
//			bulb.add(true);
//			
//		}
//	}
//	public void setCellOption(JTable table)
//	{
//		table.getColumnModel().getColumn(0).setPreferredWidth(60);	
//		for(int i=1; i<table.getModel().getColumnCount(); i++)
//		{
//			table.getColumnModel().getColumn(i).setPreferredWidth(40);			
//			table.getColumnModel().getColumn(i).setCellRenderer(cmbboxRenderer);
//			table.getColumnModel().getColumn(i).setCellEditor(cmbboxEditor);
//		}
//	}
//}

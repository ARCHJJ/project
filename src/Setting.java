import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

abstract class Setting {
	protected DefaultTableModel tablemodel;
	protected Object[][] field;
	protected String[] header;
	public Setting()
	{
		tablemodel = new DefaultTableModel();
	}
	public DefaultTableModel getModel()
	{
		return tablemodel;
	}
			
}

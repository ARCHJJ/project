import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

abstract class Setting {
	protected DefaultTableModel tablemodel;
	public Setting()
	{
		tablemodel = new DefaultTableModel();
	}
	public DefaultTableModel getModel()
	{
		return tablemodel;
	}
			
}

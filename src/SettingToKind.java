import javax.swing.JTable;

class SettingToKind extends Setting{
	private int kinds;
	public SettingToKind(String[] fileNames)
	{
		super();
		kinds = fileNames.length;
		String[] blank = {"¾Ç±â"};				
		Object[][] data = new Object[kinds][1];
		data[0][0] = fileNames[0];
		for(int i=1; i<kinds; i++)
		{
			String str = fileNames[i].substring(0, fileNames[i].indexOf("."));
			data[i][0] = str;
		}
		tablemodel.setDataVector(data, blank);
	}
}

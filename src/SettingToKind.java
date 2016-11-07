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
		for(int i=0; i<kinds; i++)
		{
			String str;
			if(fileNames[i].indexOf(".") != -1)
				str = fileNames[i].substring(0, fileNames[i].indexOf("."));
			else
				str = fileNames[i];
			
			data[i][0] = str;
		}
		tablemodel.setDataVector(data, blank);
	}
}

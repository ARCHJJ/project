import java.util.LinkedList;

import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 * @brief SettingToFeild를 상속받는 클래스로써 N분음표를 선택하게 할수 있는 부분을 표시하는 JTable을 만드는 클래스이다. 
 */
class BeatField extends SettingToField{
	private CmbBoxEditor cmbboxEditor;
	private CmbBoxRenderer cmbboxRenderer;
	private JComboBox beatList;
	private String[] beat = {"1", "2", "4", "8", "16", "32"};
	private LinkedList<Integer> beatResult;
	private int rowcnt = 1, max = 0;
	
	/**
	 * @brief 생성자
	 */
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
	
	/**
	 * @brief 데이터 입력시 다음 컬럼을 추가하는 메소드
	 * @param int idx 인덱스 번호
	 */
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
	
	/**
	 * @brief 열을 추가하고 나서 다시 렌더러와 에디터를 적용시켜 주는 메소드
	 * @param JTable Desk : 렌더러와 에디터가 적용될 JTable
	 */
	@Override
	public void setCellOption(JTable Desk) 
	{
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
	 * @param JTable Desk
	 * @return 
	 */
	public int out_max(JTable Desk)
	{
		JComboBox temp;
		String item = null;
		max = 0;
		
		for(int i=1; i<Desk.getModel().getColumnCount()-1; i++)
		{
			temp = (JComboBox)Desk.getModel().getValueAt(0, i);
			item = (String)temp.getSelectedItem();
			max += 32/(Integer.parseInt(item));
		}
		
		return max;
	}
	
	/**
	 * @brief 
	 * @return 
	 */
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
	
	/**
	 * @brief 
	 */
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
	
	/**
	 * @brief 
	 * @return -1 리턴
	 */
	@Override
	public int getKinds() {
		return -1;
	}
}

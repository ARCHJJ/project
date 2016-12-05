import java.util.LinkedList;
import javax.swing.JComboBox;
import javax.swing.JTable;
@SuppressWarnings({ "rawtypes", "unchecked" })
/**
 * @brief GUI에서 작업대기줄을 구성하는 클래스
 * SettingToField를 상속받아서 DefaultTableModel과 Object[][]를 가지고 있다. 
 * 콤보박스로 이루어진 DefaultTableModel을 만들어준다.
 */
class TaskField extends SettingToField{
	//! JTable에서 콤보박스를 이용해 값을 주고자 할 때 필요한 에디터
	private CmbBoxEditor cmbboxEditor;
	
	//! JTable에서 콤보박스가 보이게끔 해주는 렌더러
	private CmbBoxRenderer cmbboxRenderer;
	
	//! DefaultTableModel에 들어갈 콤보박스
	private JComboBox BankList;
	
	//! 콤보박스에 들어갈 데이터
	private Object[] itemList;
	
	//! 콤보박스에 들어갈 데이터가 추가될 경우 LinkedList<String>에 저장한 후 배열화 시킨다.
	private LinkedList<String> items;
	
	//! 악기 이름
	private String instrument;
	/**
	 * @brief 생성자. 초기상태를 설정해준다.
	 * @param String instrument : 악기이름을 설정해준다.
	 */
	public TaskField(String instrument)
	{
		this.instrument = instrument;
		items = new LinkedList<String>();
		items.add("");
		itemList = items.toArray();
		BankList = new JComboBox(itemList);
		
		cmbboxEditor = new CmbBoxEditor(BankList, this);
		cmbboxRenderer = new CmbBoxRenderer();
		
		header = new String[]{"악기", "1"};
		field = new Object[1][2];
		field[0][0] = instrument;
		field[0][1] = BankList;
		
		tablemodel.setDataVector(field, header);
	}
	
	/**
	 * @brief 콤보박스를 통해 데이터가 입력되면 다음 데이터를 입력받기 위해 새로운 열을 추가하는 메소드
	 * @param int idx : 열 번호
	 */
	public void addColumn(int idx) {
		idx--;
		if(bulb.get(idx))
		{
			Object[] newData = new Object[1];
			newData[0] = new JComboBox(itemList);
			tablemodel.addColumn(Integer.toString(++colCnt), newData);
			
			bulb.set(idx, false);
			bulb.add(true);
		}
	}
	
	/**
	 * @brief 열을 추가하고 나서 다시 렌더러와 에디터를 적용시켜 주는 메소드
	 * @param JTable Desk : 렌더러와 에디터가 적용될 JTable
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
	 * @brief 부모클래스의 abstract 메소드. TaskField 클래스에서는 사용하지 않는다.
	 */
	@Override
	public void removeColumn() {}
	
	/**
	 * @brief 현재 테이블을 초기화하는 메소드 
	 */
	@Override
	public void Init() {
		items.clear();
		items.add("");
		BankList.removeAllItems();
		BankList.addItem("");
		
		bulb.clear();
		bulb.add(true);
		colCnt = 1;

		header = new String[]{"악기", "1"};
		field = new Object[1][2];
		field[0][0] = instrument;
		field[0][1] = BankList;
		tablemodel.setDataVector(field, header);
	}
	
	/**
	 * @brief 뱅크저장을 통해 새로운 뱅크가 추가되면 콤보박스에 데이터를 추가시키는 메소드
	 * @param int size 콤보박스가 가지고 있는 아이템 개수
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
	 * @brief 콤보박스를 이루고 있는 데이터를 리턴하는 메소드
	 * @return Object[] itemList : 콤보박스를 이루고 있는 데이터배열
	 */
	public Object[] getItemList()
	{
		return itemList;
	}

}

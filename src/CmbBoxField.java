import javax.swing.JComboBox;
import javax.swing.JTable;
@SuppressWarnings({"unchecked", "rawtypes"})
/**
 * @brief SettingToField를 상속받는 클래스. 콤보박스로 구성된 TableModel로서 기타, 베이스, 피아노 등의 악기가 표현할 수 있는 음을 콤보박스를 이용하여 선택한다.
 */
class CmbBoxField extends SettingToField {

	//! JTable에서 콤보박스를 사용할 수 있게끔 해주는 셀에디터
	private CmbBoxEditor cmbboxEditor;
	
	//! JTable에서 콤보박스를 볼 수 있게 해주는 셀렌더러
	private CmbBoxRenderer cmbboxRenderer;
	
	//!BeatField의 행이 추가될 때 셀에디터와 렌더러를 다시 한번 적용시키기 위해 생성자인자로 전달받음
	private String[] tones;
	
	//!현재 테이블 모델에 행이 추가되면 BeatField도 함께 추가되게 하기위해 생성자인자로 전달받음
	private BeatField BeatField;
	
	//!BeatField의 행이 추가될 때 셀에디터와 렌더러를 다시 한번 적용시키기 위해 생성자인자로 전달받음
	private JTable table_Beat;
	
	/**
	 * @brief 콤보박스로 이루어진 행을 하나 추가하여 초기값을 세팅한다.
	 * @param BeatField BeatField : 현재 테이블 모델에 행이 추가되면 BeatField도 함께 추가될 수 있도록 받아옴
	 * @param JTable table_Beat	  : BeatField가 적용될 JTable
	 * @param int kinds			  : 해당하는 악기가 낼 수 있는 음의 개수
	 * @param String[] tones 	  : 콤보박스가 나타낼 아이템리스트
	 */
	public CmbBoxField(BeatField BeatField, JTable table_Beat, int kinds, String[] tones)
	{
		this.BeatField = BeatField;
		this.table_Beat = table_Beat;
		this.kinds = kinds;
		this.tones = tones;
		
		JComboBox toneList = new JComboBox(tones);
		cmbboxEditor = new CmbBoxEditor(toneList, this);
		cmbboxRenderer = new CmbBoxRenderer();
		
		header = new String[]{"1"};
		field = new Object[kinds][colCnt];
		for(int i=0; i<kinds; i++)
			field[i][0] = new JComboBox(tones);
		
		tablemodel.setDataVector(field, header);
	}
	
	/**
	 * @brief 컬럼을 한줄 추가하는 메소드
	 * @param int idx : 현재 몇 번째 컬럼인지를 가리키는 인덱스
	 */
	@Override
	public void addColumn(int idx) {
		if(bulb.get(idx))
		{
			Object[] newData = new Object[kinds];
			for(int i=0; i<kinds; i++)
				newData[i] = new JComboBox(tones);
			tablemodel.addColumn(Integer.toString(++colCnt), newData);
			
			bulb.set(idx, false);
			bulb.add(true);
			
			BeatField.addColumn(idx);
			BeatField.setCellOption(table_Beat);
		}
	}

	/**
	 * @brief 열을 추가하고 나서 다시 렌더러와 에디터를 적용시켜 주는 메소드
	 * @param JTable Desk : 렌더러와 에디터가 적용될 JTable
	 */
	@Override
	public void setCellOption(JTable Desk) {
		for(int i=0; i<Desk.getModel().getColumnCount(); i++)
		{
			Desk.getColumnModel().getColumn(i).setPreferredWidth(50);			
			Desk.getColumnModel().getColumn(i).setCellRenderer(cmbboxRenderer);
			Desk.getColumnModel().getColumn(i).setCellEditor(cmbboxEditor);
		}
	}
	
	/**
	 * @brief J테이블을 초기화하는 메소드
	 */
	@Override
	public void Init() {
		bulb.clear();
		bulb.add(true);
		colCnt = 1;
		header = new String[]{"1"};
		field = new Object[kinds][colCnt];
		for(int i=0; i<kinds; i++)
			field[i][0] = new JComboBox(tones);
		
		tablemodel.setDataVector(field, header);
	}
	
	/**
	 * @brief 악기종류를 리턴 하는 메소드
	 * @return int kinds : 악기종류 리턴
	 */
	public int getKinds()
	{
		return kinds;
	}
}

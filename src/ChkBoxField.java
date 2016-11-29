import java.util.LinkedList;

import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 * @brief SettingToField를 상속받는 클래스 체크박스로 구성된 TableModel로서 타악기를 선택할 수 있다.
 */
class ChkBoxField extends SettingToField{

	//! JTable에 체크박스를 추가시키기 위한 셀렌더러
	private ChkBoxRenderer chkboxRenderer;

	//! JTable에 체크박스를 추가시키기 위한 셀에디터
	private ChkBoxEditor chkboxEditor;
	
	//! 현재 테이블 모델에 행이 추가되면 BeatField도 함께 추가되게 하기 위해 생성자인자로 전달받음
	private BeatField BeatField;
	
	//! BeatField의 행이 추가될 때 셀 에디터와 렌더러를 다시 한번 적용시키기 위해 생성자인자로 전달받음
	private JTable table_Beat;
	
	/**
	 * @brief 생성자
	 * @param BeatField BeatField : 현재 테이블 모델에 행이 추가되면 BeatField도 함께 추가될 수 있도록 받아옴
	 * @param JTable table_Beat	  : BeatField가 적용될 JTable
	 * @param int kinds			  : 해당하는 악기가 낼 수 있는 음의 개수
	 */
	public ChkBoxField(BeatField BeatField, JTable table_Beat, int kinds)
	{
		this.BeatField = BeatField;
		this.table_Beat = table_Beat;
		this.kinds = kinds;
		
		chkboxRenderer = new ChkBoxRenderer();
		chkboxEditor = new ChkBoxEditor(new JCheckBox(), this);
		
		header = new String[]{"1"};
		field = new Object[kinds][colCnt];
		for(int i=0; i<kinds; i++)
			field[i][0] = new JCheckBox();
		
		tablemodel.setDataVector(field, header);
	}
	
	/**
	 * @brief 컬럼을 한줄 추가하는 메소드
	 * @param int idx 현재 몇번째 컬럼인지를 가리키는 인덱스
	 */
	@Override
	public void addColumn(int idx) {
		if(bulb.get(idx))
		{
			Object[] newData = new Object[kinds];
			for(int i=0; i<kinds; i++)
				newData[i] = new JCheckBox(); 
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
			Desk.getColumnModel().getColumn(i).setCellRenderer(chkboxRenderer);
			Desk.getColumnModel().getColumn(i).setCellEditor(chkboxEditor);
		}
	}
	
	/**
	 * @brief 현재 테이블을 초기화하는 메소드
	 */
	@Override
	public void Init() {
		bulb.clear();
		bulb.add(true);
		colCnt = 1;
		header = new String[]{"1"};
		field = new Object[kinds][colCnt];
		for(int i=0; i<kinds; i++)
			field[i][0] = new JCheckBox();
		
		tablemodel.setDataVector(field, header);
	}
	
	/**
	 * @brief 악기종류를 리턴 하는 메소드
	 * @return 악기종류 리턴
	 */
	public int getKinds()
	{
		return kinds;
	}
}

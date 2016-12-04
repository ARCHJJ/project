
/**
 * @brief 소리를 재생하는 버튼의 중복클릭을 방지하기 위한 클래스
 * boolean 변수를 call by reference로 인자전달한다.  
 */
class Swtch {
	//! 중복여부를 판단하는 변수
	private boolean set;
	
	/**
	 * @brief 생성자. 초기값을 false로 설정한다.
	 */
	public Swtch()
	{
		set = false;
	}
	
	/**
	 * @brief set 값을 true로 설정한다.  
	 */
	public void setTrue()
	{
		set = true;
	}
	
	/**
	 * @brief set 값을 false로 설정한다.  
	 */
	public void setFalse()
	{
		set = false;
	}
	
	/**
	 * @brief 현재 상태를 리턴하는 메소드
	 * @return boolean set : 현재 상태
	 */
	public boolean getSwtch()
	{
		return set;
	}
}

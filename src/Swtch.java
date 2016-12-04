
/**
 * @brief �Ҹ��� ����ϴ� ��ư�� �ߺ�Ŭ���� �����ϱ� ���� Ŭ����
 * boolean ������ call by reference�� ���������Ѵ�.  
 */
class Swtch {
	//! �ߺ����θ� �Ǵ��ϴ� ����
	private boolean set;
	
	/**
	 * @brief ������. �ʱⰪ�� false�� �����Ѵ�.
	 */
	public Swtch()
	{
		set = false;
	}
	
	/**
	 * @brief set ���� true�� �����Ѵ�.  
	 */
	public void setTrue()
	{
		set = true;
	}
	
	/**
	 * @brief set ���� false�� �����Ѵ�.  
	 */
	public void setFalse()
	{
		set = false;
	}
	
	/**
	 * @brief ���� ���¸� �����ϴ� �޼ҵ�
	 * @return boolean set : ���� ����
	 */
	public boolean getSwtch()
	{
		return set;
	}
}

import javax.swing.text.*;

/**
 * @brief BPM�� ����ó���� ���� Ŭ�����̴�.
 */
public class IntegerDocument extends PlainDocument 
{
	//! ���� ��ȯ�� ��
    int currentValue = 0;

    
    /**
	 * @brief ���� ��ȯ�Ѵ�.
	 */
    public int getValue() 
    {
    	return currentValue;
    }
    
    /**
	 * @brief �Է��� ���� �о�´�.
	 * @param int offset				: �Էµ� ������ ���� ��ġ
	 * @param String string				: �Էµ� ����
	 * @param AttributeSet attributes	: �Է��� �޾ƿ� ��ü�� ����
	 */
    public void insertString(int offset, String string, AttributeSet attributes) throws BadLocationException 
    {
    	if (string == null) 
    	{
         return;
        } 
    	
    	else 
    	{
         String newValue;
         
         int length = getLength();
         
         if (length == 0) 
         {
        	 newValue = string;
         } 
         
         else 
         {
        	 String currentContent = getText(0, length);
             StringBuffer currentBuffer = new StringBuffer(currentContent);
             currentBuffer.insert(offset, string);
             newValue = currentBuffer.toString();
         }
         
         currentValue = checkInput(newValue, offset);
         
         super.insertString(offset, string, attributes);
       }
     }
    
    /**
	 * @brief �Է��� ���� �����.
	 * @param int offset				: �Էµ� ������ ���� ��ġ
	 * @param int length				: �Էµ� ������ ����
	 */
     public void remove(int offset, int length) throws BadLocationException 
     {
    	 int currentLength = getLength();
    	 
         String currentContent = getText(0, currentLength);
         
         String before = currentContent.substring(0, offset);
         
         String after = currentContent.substring(length+offset, currentLength);
         
         String newValue = before + after;
         
         currentValue = checkInput(newValue, offset);
         
         super.remove(offset, length);
     }
     
     /**
 	 * @brief �Է� ���θ� Ȯ���Ѵ�.
 	 * @param String proposedValue		: ���� �ؽ�Ʈ �ʵ��� ���ڿ�
 	 * @param int offset				: �Էµ� ������ ���� ��ġ
 	 */
     public int checkInput(String proposedValue, int offset) throws BadLocationException 
     {
         if (proposedValue.length() > 0) 
         {
	         try 
	         {
	           int newValue = Integer.parseInt(proposedValue);
	           
	           return newValue;
	         } 
	         
	         catch (NumberFormatException e) 
	         {
	           throw new BadLocationException(proposedValue, offset);
	         }
         
         } 
         
         else 
         {
         return 0;
         }
     }
}
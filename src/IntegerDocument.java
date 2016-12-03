import javax.swing.text.*;

/**
 * @brief BPM의 예외처리를 위한 클래스이다.
 */
public class IntegerDocument extends PlainDocument 
{
	//! 현재 반환될 값
    int currentValue = 0;

    
    /**
	 * @brief 값을 반환한다.
	 */
    public int getValue() 
    {
    	return currentValue;
    }
    
    /**
	 * @brief 입력한 값을 읽어온다.
	 * @param int offset				: 입력된 글자의 시작 위치
	 * @param String string				: 입력된 글자
	 * @param AttributeSet attributes	: 입력을 받아온 객체의 설정
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
	 * @brief 입력한 값을 지운다.
	 * @param int offset				: 입력된 글자의 시작 위치
	 * @param int length				: 입력된 글자의 길이
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
 	 * @brief 입력 려부를 확인한다.
 	 * @param String proposedValue		: 현제 텍스트 필드의 문자열
 	 * @param int offset				: 입력된 글자의 시작 위치
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
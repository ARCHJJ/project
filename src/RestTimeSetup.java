import java.util.StringTokenizer;

/**
 * @brief 박자에 따른 음과 음 사이의 시간과 총 연주시간을 계산하는 클래스
 */
class RestTimeSetup {
	//! 음이 연주되는 총 시간	
	public static int music_score = 32;
	
	//! 음과 음 사이의 시간
	public static int result = 1000;
	
	//! 박자 기준 음표
	public static int time_signature_denominator = 4;
	
	//! 박자 기준 길이 
	public static int time_signature_numerator = 4;
	
	/**
	 * @brief 박자와 비트에 맞춰 쉬는시간과 연주시간을 계산하는 메소드
	 * @param String BPMSet	 : 박자
	 * @param String BeatSet : 비트
	 */
	public static void getRestTime(String BPMSet, String BeatSet)
	{
		int BPM = Integer.parseInt(BPMSet); 
        
        double BPN, thirty_second_note;
        
        StringTokenizer st = new StringTokenizer(BeatSet ,"/");
        
        time_signature_numerator = Integer.parseInt(st.nextToken());
        time_signature_denominator = Integer.parseInt(st.nextToken());
        
        BPN = 60.0/BPM;
        
        music_score = (32/time_signature_denominator)*time_signature_numerator;
        
        thirty_second_note = BPN/8;
        result = (int)(thirty_second_note*1000.0);
    }
}

import java.util.StringTokenizer;

class RestTimeSetup {
	
	static int music_score, result;

	RestTimeSetup()
	{}
	
	public static void getRestTime(String BPMSet, String BeatSet)
	{
		int BPM = Integer.parseInt(BPMSet); 
        int time_signature_denominator, time_signature_numerator; 
        
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

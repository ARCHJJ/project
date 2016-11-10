import java.util.StringTokenizer;

class RestTimeSetup {

	RestTimeSetup()
	{}
	
	public static int getRestTime(String BPMSet, String BeatSet)
	{
		int BPM = Integer.parseInt(BPMSet); 
        int time_signature_denominator, time_signature_numerator, result; 
        
        double BPN, thirty_second_note;
        
        StringTokenizer st = new StringTokenizer(BeatSet ,"/");
        
        time_signature_numerator = Integer.parseInt(st.nextToken());
        time_signature_denominator = Integer.parseInt(st.nextToken());
        
        BPN = 60.0/BPM;
        
        thirty_second_note = BPN/(32/time_signature_denominator);
        result = (int)(thirty_second_note*1000.0);
        
        return result; 
    }
}

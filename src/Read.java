import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.JavaClipAudioPlayer;
import com.sun.speech.freetts.en.us.CMUDiphoneVoice;
import com.sun.speech.freetts.en.us.CMULexicon;

public class Read {
	private static VoiceManager voiceManager; 
    private static Voice syntheticVoice;
    
    public static Voice getVoice()
    {
    	try
    	{
    		voiceManager = VoiceManager.getInstance();
    		syntheticVoice = voiceManager.getVoice("kevin16");
            syntheticVoice.setRate(130f);
            syntheticVoice.setVolume(50);
    		syntheticVoice.allocate();
    		System.out.println("Allocated synthetic voice: " + syntheticVoice);
    	}
    	catch(Exception e)
    	{
    		System.out.println("Error in the voice instantiation: ");
    		e.printStackTrace();
    	}
    	return syntheticVoice;
    }
    
    public static void main(String[] args) {
    	cmdTrial();
/*    	System.out.println("Started...");
    	getVoice().speak("This is working fine. you can proceed with whatever you want to do.");
    	getVoice().deallocate();
    	System.out.println("Finished...");
*/
    }
    
    public static void cmdTrial() {
    	System.out.println("Entered...");
    	Voice voice = new CMUDiphoneVoice();
    	voice.setLexicon(new CMULexicon());
//    	voice.setAudioPlayer(new JavaClipAudioPlayer());
    	voice.allocate();
    	voice.speak("I can talk forever without getting tired!");
    	System.out.println("Exiting...");
    }
}
import java.util.TimerTask;

import com.sun.speech.freetts.Voice;

public class Warning extends TimerTask{
	private static Voice voice;
	
	public Warning(Voice voice)
	{
		Warning.voice = voice;
	}
	public void run() {
		String message = "The test is going to finish in five minutes";
		voice.speak(message);
	}
	
	public static void main(String[] args) {
		Warning w = new Warning(Read.getVoice());
		w.run();
	}
}

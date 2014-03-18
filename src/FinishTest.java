import java.util.TimerTask;

import com.sun.speech.freetts.Voice;

public class FinishTest extends TimerTask {
	private static EScribe test;
	private Voice voice;
	
	public FinishTest(Voice voice, EScribe test) {
		this.voice = voice;
		this.test = test;
	}
	public void run() {
		String message = "Your alloted time is over";
		voice.speak(message);
		test.complete();
	}
}

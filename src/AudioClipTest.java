import java.applet.Applet;
import java.applet.AudioClip;


public class AudioClipTest {
	
	private static AudioClip clip;
	
	public static void main(String[] args) {
		try {
			String filename = "/home/user/Desktop/ESCRIBE/Option D.wav";
			System.out.println(AudioClipTest.class.getResource(filename));
			clip = Applet.newAudioClip(AudioClipTest.class.getResource(filename));
			clip.play();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

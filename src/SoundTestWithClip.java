import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class SoundTestWithClip {
	public static void main(String[] args) {
		try {
			// Give the full path of the .wav file here
			File soundFile = new File( "/home/user/Desktop/ESCRIBE/Option D.wav" );
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream( soundFile );
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch(Exception e) {
			System.out.println("Unable to play clip: ");
			e.printStackTrace();
		}
	}
}

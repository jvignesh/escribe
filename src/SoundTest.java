import java.io.File;

public class SoundTest {
	// Should give the full path (absolute path) of the sound file (.wav) here
	public static final String filepath = "/home/user/Desktop/ESCRIBE/Option D.wav";
	public static void main(String[] args) {
		try {
			File file = new File(filepath);
			System.out.println("File: " + file);
			byte[] b = FileToByteArray.convert(file);
			PlayMedia.speak(b);
		} catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
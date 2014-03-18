import java.awt.Component;
import java.io.ByteArrayInputStream;
import java.io.File;
import javax.swing.JFileChooser;
import sun.audio.AudioPlayer;

public class PlayMedia {
	private static final AudioPlayer player = AudioPlayer.player;
	private static ByteArrayInputStream bais;
	
	public static void speak(byte[] audioData) {
		try {
			bais = new ByteArrayInputStream(audioData);
        	player.start(bais);
        	while(bais.available() > 0);
        	bais.close();
		} catch (Exception e) {
			System.out.println("Error in playing the audio from ByteArrayInputStream: ");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			JFileChooser jfc = new JFileChooser();
			Component c = null;
			jfc.showOpenDialog(c);
			File file = jfc.getSelectedFile();
			byte[] b = FileToByteArray.convert(file);
			speak(b);
		} catch(Exception e) {
			System.out.println("Error inside main function of PlayMedia: ");
			e.printStackTrace();
		}
	}
}
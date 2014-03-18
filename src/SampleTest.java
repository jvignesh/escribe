import java.io.File;
import java.io.FileInputStream;
import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
//import javafx.embed.swing.JFXPanel;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;

public class SampleTest {
    private static Clip clip;
    private static final String fileName = "/home/vignesh/Desktop/ESCRIBE/Option A.wav";
    
    public static void main(String[] a) {
        try {
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file);
/*        	JFXPanel jp = new JFXPanel();
            Media hit = new Media(fileName);
            MediaPlayer mediaPlayer = new MediaPlayer(hit);
            jp.setVisible(true);
            mediaPlayer.play();
*/
            if (file.exists()) {
            	//AudioInputStream s = new AudioInputStream(fis, null, 0);
            	AudioInputStream sound = AudioSystem.getAudioInputStream(file);
             // load the sound into memory (a Clip)
            	System.out.println("Sound: " + sound);
            	System.out.println("Format: " + sound.getFormat());
            	System.out.println("Lenght: " + sound.getFrameLength());
                clip = AudioSystem.getClip();
                Type[] type = AudioSystem.getAudioFileTypes();
                for(Type t:type) {
                	System.out.println(t);
                }
                clip.open(sound);
                play(); 
            }
            else {
                throw new RuntimeException("Sound: file not found: " + fileName);
            } 
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    // play, stop, loop the sound clip
    }
    public static void play(){
        clip.setFramePosition(0);  // Must always rewind!
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
            clip.stop();
        } 
}
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;


public class PlayClip {
	public static void play(String filename)
	{
	    try
	    {
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File(filename)));
	        clip.start();
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
	}
	
	public static void main(String[] args) throws FileNotFoundException {
//        AePlayWave aw = new AePlayWave( "/home/user/workspace/EScribe/src/ans2.wav" );
        AePlayWave.speak(("/home/user/workspace/EScribe/src/ans2.wav")); 
		//function3(new File("/home/user/workspace/EScribe/src/ans2.wav"));
		//function2();
	}
	
	public static void function2 () {
		try {
		    File yourFile = new File("/home/user/workspace/EScribe/src/ans2.wav");
		    AudioInputStream stream;
		    AudioFormat format;
		    DataLine.Info info;
		    Clip clip;

		    stream = AudioSystem.getAudioInputStream(yourFile);
		    format = stream.getFormat();
		    System.out.println("Format: " + format);
		    info = new DataLine.Info(Clip.class, format);
		    System.out.println("Info: " + info);
		    clip = (Clip) AudioSystem.getLine(info);
		    System.out.println("Clip: " + clip);
		    clip.open(stream);
		    System.out.println("Open done...");
		    clip.start();
		}
		catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public static void function3(File file) 
	{
	    try
	    {
	        final Clip clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));

	        clip.open(AudioSystem.getAudioInputStream(file));
	        clip.start();
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
	}
}

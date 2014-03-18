import java.sql.*;
import java.util.Timer;
import javax.swing.*;

public class EScribe extends Thread{
	public final static Statement s = DBConnection.getStatement();
	private static String message;
	protected static String user;
	public static final int mcqMaxTime = 7 * 60 * 1000;
	public static final int vedestMaxTime = 60 * 60 * 1000;
	public static final int FIVE_MIN = 5 * 60 * 1000;
	public static final JFrame jf = new JFrame();
	private static final JLabel label1 = new JLabel();
	public static final byte[] audioData[] = RetrieveAudio.getAudioData();
	
	public static void main(String[] a)
	{
		if(null == s)
		{
			message = "Database connection failed: Could not log in.";
			System.out.println(message);
		}
		else
		{
			user = Login.getUser();
			//Change this to JFrame
			//Login l = new Login();

			//user = Login.getUser();
			System.out.println("Login successful with: " + user);
			if(null == user)
			{
				message = "Login failed. Enter correct username and password";
			//	voice.speak(message);
			}
			else
			{
				JTextArea label2 = new JTextArea(10, 100);
				label2.setEditable(false);
				JScrollPane jsp1 = new JScrollPane(label2);

				jf.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		       label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(jf.getContentPane());
		        jf.getContentPane().setLayout(layout);
		        layout.setHorizontalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(152, 152, 152)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addComponent(jsp1)
		                    .addComponent(label1))
		                .addContainerGap(214, Short.MAX_VALUE))
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(25, 25, 25)
		                .addComponent(label1)
		                .addGap(35, 35, 35)
		                .addComponent(jsp1)
		                .addGap(61, 61, 61)
		                .addContainerGap(137, Short.MAX_VALUE))
		        );
		        
				jf.addKeyListener(new java.awt.event.KeyAdapter() {
		            public void keyPressed(java.awt.event.KeyEvent evt) {
		            	if(evt.getKeyCode() == 112)
		            	{
		            		PlayMedia.speak(audioData[1]);
		            		System.out.println("Reading instructions once again.");
		            		//voice.speak(message);
		            	}
		            	else if(evt.getKeyCode() == 113)
		            	{
		            		//voice.speak("Your time for module starts now.");
		    				Timer t = new Timer();
		    				final RunMCQ mcq = new RunMCQ(user);
		    				
		    				//Add appropriate Warning classes here or remove this feature
		    				//t.schedule(new Warning(voice), mcqMaxTime - FIVE_MIN);
		    				//t.schedule(new FinishTest(voice, mcq), mcqMaxTime);
		    				jf.dispose();
		            	}
		            }
		        });

		        jf.pack(); 
				jf.setVisible(true);
				message = "Welcome to E-Scribe, An Assessment Aid";
				label1.setText(message);
				PlayMedia.speak(audioData[0]);
				
				message = "Please listen to the insructions carefully. This test has two modules."; 
				label2.append("\n" + message + "\n");
				PlayMedia.speak(audioData[1]);
				//voice.speak(message);
				message = "The first module has multiple choice questions and the permitted time is "+ mcqMaxTime/60000 +" minutes. The second module consists of descriptive answers and its permitted time is " +vedestMaxTime/60000+ " minutes.";
				label2.append("\n" + message + "\n");
				//voice.speak(message);

				message = "Instructions for module one. Press F1 key to play the question, F2 to play the Answers and the keys F5 through F8 are for selecting the options. Press F11 for help, F12 for time alert and space bar to complete the test.";
				label2.append("\n" + message + "\n");
				//voice.speak(message);	

				//voice.speak("Now, press F1 to listen to the instructions once again or press F2 to proceed with the module one.");			

	
				//VEDEST vedest = new VEDEST(con, voice, user);
				
				//message = "Your answers have been saved. Thank you for taking up the test.";
				//voice.speak(message);
			}
		}
	}
	
	public void complete() {
		
	}
}
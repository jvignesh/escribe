import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RetrieveAudio {
	public static final Statement s = DBConnection.getStatement();
	public static final String query = "select voice_inst from voice_instructions;";
	public static ResultSet rs;
	private static final int numOfIns = InstructionsInsert.ins.length;
	public static byte[] audioData[] = new byte[numOfIns][];
	
	public static void main(String[] args) {
		for(String t:InstructionsInsert.ins)
			PlayMedia.speak(getAudioData(t));
		//PlayMedia.speak(getAudioData("Welcome"));
	}

	public static byte[][] getAudioData() {
		try {
			int i=0;
			System.out.println("S Value: " + s);
			rs = s.executeQuery(query);
			while(rs.next()) {
				audioData[i++] = rs.getBytes(1);
			}
		}
		catch(SQLException e) {
			System.out.println("SQL Exception occured:");
			e.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("Error occured while retrieving audio data:");
			e.printStackTrace();
		}
		return audioData;
	}
	
	public static byte[] getAudioData(String ins) {
		byte[] b = null;
		try {
			String q = "select voice_inst from voice_instructions where instruction_name='"+ins+"';";
			rs = s.executeQuery(q);
			while(rs.next()) {
				b = rs.getBytes(1);
			}
		}
		catch(SQLException e) {
			System.out.println("SQL Exception");
			e.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("Error occured while retrieving audio data: ");
			e.printStackTrace();
		}
		return b;
	}
}
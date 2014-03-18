import java.sql.ResultSet;
import java.sql.Statement;

public class RetriveQA {
	public static final Statement s = EScribe.s;
	public static final String queryQues = "select voice_ques from voice_test;";
	public static final String queryAns = "select voice_ans from voice_test;";
	public static ResultSet rs;
	private static final int numOfQues = 10;
	public static byte[] quesAudioData[] = new byte[numOfQues][];
	public static byte[] ansAudioData[] = new byte[numOfQues][];
	
	public static void main(String[] args) {
		//for(byte[] t: getQuesData())
		//	PlayMedia.speak(t);
		for(byte[] t: getAnsData())
			PlayMedia.speak(t);
	}

	public static byte[][] getQuesData() {
		try {
			int i=0;
			rs = s.executeQuery(queryQues);

			while(rs.next())
				quesAudioData[i++] = rs.getBytes(1);
		}
		catch(Exception e) {
			System.out.println("Error occured while retrieving audio question data:");
			e.printStackTrace();
		}
		return quesAudioData;
	}
	
	public static byte[][] getAnsData() {
		try {
			int i=0;
			rs = s.executeQuery(queryAns);

			while(rs.next())
				ansAudioData[i++] = rs.getBytes(1);
		}
		catch(Exception e) {
			System.out.println("Error occured while retrieving audio answer data:");
			e.printStackTrace();
		}
		return ansAudioData;
	}	
}
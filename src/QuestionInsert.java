import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuestionInsert {
	private static final Connection con = DBConnection.getConnection(); 
	private static PreparedStatement pstmt;
	private static final String path = "/home/user/Desktop/ESCRIBE/";
	private static final String quesFile = path + "quesFile.txt";
	private static BufferedReader br;
	private static final int numQ = 10;
	private static String ques, aans, bans, cans, dans;
	private static File qFile, ansFile;
	private static InputStream qIn, ansIn;
	private static String query;
	
	public static void main(String[] args) {
		try {
			br = new BufferedReader(new FileReader(quesFile));

			for(int i=0; i < numQ;i++) {
				ques = br.readLine();
				aans = br.readLine();
				bans = br.readLine();
				cans = br.readLine();
				dans = br.readLine();
				qFile = new File(path + "ques"+(i+1)+".wav");
				ansFile = new File(path + "ans"+(i+1)+".wav");
				qIn = new FileInputStream(qFile);
				ansIn = new FileInputStream(ansFile);
				query = "insert into voice_test VALUES(?,?,?,?,?,?,?);";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, ques);
				pstmt.setBinaryStream(2, qIn, qFile.length());
				pstmt.setString(3, aans);
				pstmt.setString(4, bans);
				pstmt.setString(5, cans);
				pstmt.setString(6, dans);
				pstmt.setBinaryStream(7, ansIn, ansFile.length());
				pstmt.execute();
				System.out.println("Completed " + i);
			}
			pstmt.close();
			con.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("Question file not found: ");
			e.printStackTrace();
		} 
		catch (IOException e) {
			System.out.println("IOException occured: ");
			e.printStackTrace();
		} 
		catch (SQLException e) {
			System.out.println("SQL Exception occured: ");
			e.printStackTrace();
		}
	}
}
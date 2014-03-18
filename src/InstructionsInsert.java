import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InstructionsInsert {
	public static final Connection con = DBConnection.getConnection(); 
	private static PreparedStatement pstmt;
	public static final String path = "/home/user/Desktop/ESCRIBE/";
	private static File insFile;
	private static InputStream insIn;
	private static String query;
	public static final String[] ins = {/*0*/	"Welcome", 
										/*1*/	"Module one instructions", 
										/*2*/	"Module one start", 
										/*3*/	"Module one complete", 
										/*4*/	"Module two instructions", 
										/*5*/	"Module two start", 
										/*6*/	"Module two complete", 
										/*7*/	"Thank you", 
										/*8*/	"Time starts now", 
										/*9*/	"Option A", 
										/*10*/	"Option B", 
										/*11*/	"Option C", 
										/*12*/	"Option D", 
										/*13*/	"Five minutes",
										/*14*/	"Time ended"};

	public static int getIndexOf(String instruction) {
		int i;
		for(i=0;i<ins.length;i++)
			if(instruction.equalsIgnoreCase(ins[i]))
				return i;
		return -1;
	}
	
	public static void main(String[] args) {
		try {
			for(int i=0;i<ins.length;i++) {
				insFile = new File(path + ins[i] + ".wav");
				insIn = new FileInputStream(insFile);
				
				query = "INSERT INTO voice_instructions VALUES(?,?);";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, ins[i]);
				pstmt.setBinaryStream(2, insIn, insFile.length());
				pstmt.execute();
				System.out.println("Completed: " + ins[i]);
			}
			pstmt.close();
			con.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Mentioned file is not found: ");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("An SQL Exception occured: ");
			e.printStackTrace();
		}
	}
}

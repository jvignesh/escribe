import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.Statement;


public class Login {
	public static final Statement s = EScribe.s;
	public static final String query = "select pass from users where user = ";
	public static ResultSet rs;
	public static final InputStreamReader isr = new InputStreamReader(System.in);
	public static final BufferedReader br = new BufferedReader(isr);

	public static String getUser() {
		System.out.println("Enter username and password: ");
		try {
			String usr, pwd, password = null;
			usr = br.readLine();
			pwd = br.readLine();
			rs = s.executeQuery(query + "'" + usr + "';");
			
			while(rs.next()) 
				password = rs.getString(1);	
				
			if(password.equals(pwd))
				return usr;
			else
				System.out.println("Authentication failed.");
		}
		catch (Exception e) {
			System.out.println("Error while getting username and password.");
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println("User name is got as: " + getUser());
	}
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

		private static final String username = "root";
		private static final String password = "123456";
		private static Connection con;
		private static Statement s;
		
		public static Connection getConnection() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/escribe", username, password);
			}
			catch(Exception e) {
				System.out.println("Database connection failed:");
				e.printStackTrace();
			}
			return con;
		}
		
		public static Statement getStatement() {
			try {
				s = getConnection().createStatement();
			} catch (SQLException e) {
				System.out.println("Statement creation failed:");
				e.printStackTrace();
			}
			return s;
		}
		
		public static void main(String[] args) {
			System.out.println("Connection: " + getConnection());
			System.out.println("Statement: " + getStatement());
		}
}
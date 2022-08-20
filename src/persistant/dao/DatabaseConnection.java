package persistant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	static Connection con=null;
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/studentregister","root","saisithu112");
			System.out.println("Connecting...");
		}catch(ClassNotFoundException e) {
			System.out.println("Driver class not found");
		}catch(SQLException e) {
			System.out.println("Database not found");
		}
		
		return con;
	}
}

package db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {
	private static String user = "weibo";
	private static String password = "weibo";
	private static String url="jdbc:postgresql://localhost/checkin";
	
	
	public static Connection getConnection(){
		
		Properties props = new Properties();
		props.setProperty("user", user);
		props.setProperty("password", password);
		//props.setProperty("ssl", "true");
		
		Connection conn;
		try {
			conn = DriverManager.getConnection(url, props);
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

}

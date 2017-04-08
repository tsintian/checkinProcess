package db.daoimps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import db.connection.DBManager;

public class UserInfoImp {
	Connection conn;
	private final static Logger logger = LogManager.getLogger(UserInfoImp.class);
	//private final String tableName="pois_with_code";
	
	public UserInfoImp() {
		this.conn = DBManager.getConnection();
	}
	
	public ArrayList<String> getAllUserId(){
		String sql = "SELECT uid FROM USERINFO limit 500";
		ArrayList<String> results = new ArrayList<String>();
		PreparedStatement ps;
		try {
			ps = this.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				results.add(rs.getString("uid"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return results;
	}
	
	
	public int colseConn(){
		if(this.conn != null)
			try {
				this.conn.close();
				return 0;
			} catch (SQLException e) {
				logger.error("Error while closing the connection.");
				e.printStackTrace();
			}
		return -1;
	}

}

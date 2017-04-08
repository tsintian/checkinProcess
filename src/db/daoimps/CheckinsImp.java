package db.daoimps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import db.connection.DBManager;
import db.datamodels.POI;
import process.CheckinChain;
import process.CheckinElem;
import tools.ParseCategoryCode;

public class CheckinsImp {
	Connection conn;
	private final static Logger logger = LogManager.getLogger(CheckinsImp.class);
	
	public CheckinsImp(){
		this.conn = DBManager.getConnection();
	}
	
	
	/**
	 * Update time fields, such as year,month,day,hour,week etc..
	 * @param updateAll Boolean, Whether update all the records, if false, only update the records where ckyear column is null; 
	 * otherwise, update all records.
	 * @return 0 if updated successfully; otherwise, return non-zero numbers.
	 */
	public int updateTimeInfoFields(boolean updateAll){
		//Select records which contains null ckyear.
		
		String selNullCkyear = "SELECT id,checkintime FROM checkins WHERE ckyear is null";
		if(updateAll)
			selNullCkyear = "SELECT id,checkintime FROM checkins";
		String updateCKyear = "UPDATE checkins SET ckyear = ?,ckmonth=?,ckhour=?,ckweek=? WHERE id = ?";
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(selNullCkyear);
			while(rs.next()){
				PreparedStatement pst = conn.prepareStatement(updateCKyear);
				Timestamp ts = rs.getTimestamp("checkintime");
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(ts.getTime());
				pst.setInt(1, cal.get(Calendar.YEAR));
				pst.setInt(2, cal.get(Calendar.MONTH)+1);
				pst.setInt(3, cal.get(Calendar.HOUR_OF_DAY));
				pst.setInt(4, cal.get(Calendar.DAY_OF_WEEK));
				pst.setLong(5, rs.getLong("id"));
				
				logger.debug("UPDATE checkins SET ckyear =  "+cal.get(Calendar.YEAR)+", ckmonth= "+ cal.get(Calendar.MONTH)+", ckhour= "+cal.get(Calendar.HOUR_OF_DAY)
						+", ckweek= "+cal.get(Calendar.DAY_OF_WEEK) +" WHERE id = " +rs.getLong("id"));
				pst.executeUpdate();
				pst.close();
			}
			rs.close();
			st.close();
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
	//TODO:  To be implemented. Very important !!!!.
	/**
	 * Get someone's all Checkins according to its user id.
	 * @param userId
	 * @return
	 */
	public CheckinChain getCheckinsByUserId(String userId){
		CheckinChain chkChain = new CheckinChain();
		
		//We can get checkins according to the userId, then we can find information about the poi, such as location, semantics, by related poi id which
		// can be got from each check-in record.
		String selSql = "SELECT poiid, checkintime FROM checkins WHERE uid = ? ORDER BY checkintime ";
		try {
			PreparedStatement ps = this.conn.prepareStatement(selSql);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();
			POIImp poiImp = new POIImp();
			while(rs.next()){
				String poiid = rs.getString("poiid");
				Timestamp chktime = rs.getTimestamp("checkintime");
				
				//Get poi information according to the poi id.
				POI poi = poiImp.getPOIbyPoiId(poiid);
				if (poi != null) {
					String[] cates = ParseCategoryCode.parseCateCode(poi.getCode());
					chkChain.addCheckinElem(new CheckinElem(poiid, poi.getWgs_lon(), poi.getWgs_lan(), poi.getCityid(),
							poi.getProvinceid(), poi.getCountryid(), cates[0], cates[1], cates[2], chktime));
				}
				}
			poiImp.colseConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return chkChain;
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

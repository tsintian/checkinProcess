package db.daoimps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import db.connection.DBManager;
import db.datamodels.POI;
import process.CheckinElem;
import tools.GeoPoint;
import tools.SpaRefConvert;

public class POIImp {

	Connection conn;
	private final static Logger logger = LogManager.getLogger(POIImp.class);
	private final String tableName="pois_with_code";

	/**
	 * Transform the coordinates to WGS84 as the current coordinates system is GCJ-02.
	 * @return
	 */
	public int geoconv_gcj2wgs() {
		String SelOriginCoords = "SELECT id,longitude,latitude FROM "+tableName;
		String updateCoords = "UPDATE "+tableName+" SET wgs_lon = ?,wgs_lat=? WHERE id = ?";
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(SelOriginCoords);
			while(rs.next()){
				PreparedStatement pst = conn.prepareStatement(updateCoords);
				double lon = rs.getDouble("longitude");
				double lat = rs.getDouble("latitude");
				GeoPoint point = SpaRefConvert.gcj_To_Gps84(lat, lon);
				pst.setDouble(1,point.getLon() );
				pst.setDouble(2, point.getLat());				
				pst.setLong(3, rs.getLong("id"));
				
				logger.debug("UPDATE pois SET wgs_lon =  "+point.getLon()+", wgs_lat= "+ point.getLat()+" WHERE id = " +rs.getLong("id"));
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

	public POIImp() {
		this.conn = DBManager.getConnection();
	}
	
	
	


	public POI getPOIbyPoiId(String poiId){
		String selSql = "SELECT cityid, province, country,wgs_lon, wgs_lat, code FROM  "+ this.tableName+ " WHERE poiid = ?";
		
		try {
			PreparedStatement ps = this.conn.prepareStatement(selSql);
			ps.setString(1, poiId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				if(rs.getString("code") !=  null){
				return new POI(poiId, rs.getString("cityid"), rs.getString("province"), rs.getString("country"), rs.getString("code"),
						rs.getDouble("wgs_lon"), rs.getDouble("wgs_lat"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
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

/**
 * Checkin table.
 */
package db.datamodels;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author tianqin
 *
 */
public class Checkins implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String uid;
	private String poiid;
	private Timestamp checkintime;
	private int ckyear;
	private int ckmonth;
	private int ckhour;
	private int ckweek;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPoiid() {
		return poiid;
	}
	public void setPoiid(String poiid) {
		this.poiid = poiid;
	}
	public Timestamp getCheckintime() {
		return checkintime;
	}
	public void setCheckintime(Timestamp checkintime) {
		this.checkintime = checkintime;
	}
	public int getCkyear() {
		return ckyear;
	}
	public void setCkyear(int ckyear) {
		this.ckyear = ckyear;
	}
	public int getCkmonth() {
		return ckmonth;
	}
	public void setCkmonth(int ckmonth) {
		this.ckmonth = ckmonth;
	}
	public int getCkhour() {
		return ckhour;
	}
	public void setCkhour(int ckhour) {
		this.ckhour = ckhour;
	}
	public int getCkweek() {
		return ckweek;
	}
	public void setCkweek(int ckweek) {
		this.ckweek = ckweek;
	}
	
	
	
	

}

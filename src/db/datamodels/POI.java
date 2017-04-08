package db.datamodels;

import java.io.Serializable;

/**
 * The class for modeling POI element.
 * @author tianqin
 *
 */
public class POI implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String poiid;
	private String cityid;
	private String provinceid;
	private String countryid;
	
	private String code;
	
	private double wgs_lon;
	private double wgs_lan;
	
	

	public POI() {
		
	}


	

	public POI(String poiid, String cityid, String provinceid, String countryid, String code, double wgs_lon,
			double wgs_lan) {
		super();
		this.poiid = poiid;
		this.cityid = cityid;
		this.provinceid = provinceid;
		this.countryid = countryid;
		this.code = code;
		this.wgs_lon = wgs_lon;
		this.wgs_lan = wgs_lan;
	}




	public String getPoiid() {
		return poiid;
	}



	public void setPoiid(String poiid) {
		this.poiid = poiid;
	}



	public String getCityid() {
		return cityid;
	}



	public void setCityid(String cityid) {
		this.cityid = cityid;
	}



	public String getProvinceid() {
		return provinceid;
	}



	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}



	public String getCountryid() {
		return countryid;
	}



	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public double getWgs_lon() {
		return wgs_lon;
	}



	public void setWgs_lon(double wgs_lon) {
		this.wgs_lon = wgs_lon;
	}



	public double getWgs_lan() {
		return wgs_lan;
	}



	public void setWgs_lan(double wgs_lan) {
		this.wgs_lan = wgs_lan;
	}




	@Override
	public String toString() {
		return "Poiid: " + this.poiid + " ,cityid: "+this.cityid +" ,provinceid: "
	+this.provinceid+", countryid: "+this.countryid+", code: "+this.code;
	}
	
	
	
	

}

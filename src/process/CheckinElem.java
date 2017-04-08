/**
 * 
 */
package process;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author tianqin
 * The Check in element which makes up Check-in chain.
 */
public class CheckinElem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String poiid;
	
	//Spatial hierachy
	private double wgs_lon;
	private double wgs_lat;
	private String cityId;
	private String provinceId;
	private String countryId;
	
	
	//Semantic hierachy
	private String largeCat; //class
	private String mediumCat; //division
	private String smallCat;  //group

	//Check in time.
	private Timestamp time; 
	
	/**
	 * 
	 */
	public CheckinElem() {
		// TODO Auto-generated constructor stub
	}
	
	

	public CheckinElem(String poiid, double wgs_lon, double wgs_lat, String cityId, String provinceId, String countryId,
			String largeCat, String mediumCat, String smallCat, Timestamp time) {
		super();
		this.poiid = poiid;
		this.wgs_lon = wgs_lon;
		this.wgs_lat = wgs_lat;
		this.cityId = cityId;
		this.provinceId = provinceId;
		this.countryId = countryId;
		this.largeCat = largeCat;
		this.mediumCat = mediumCat;
		this.smallCat = smallCat;
		this.time = time;
	}



	public String getPoiid() {
		return poiid;
	}

	public void setPoiid(String poiid) {
		this.poiid = poiid;
	}

	public double getWgs_lon() {
		return wgs_lon;
	}

	public void setWgs_lon(double wgs_lon) {
		this.wgs_lon = wgs_lon;
	}

	public double getWgs_lat() {
		return wgs_lat;
	}

	public void setWgs_lat(double wgs_lat) {
		this.wgs_lat = wgs_lat;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getLargeCat() {
		return largeCat;
	}

	public void setLargeCat(String largeCat) {
		this.largeCat = largeCat;
	}

	public String getMediumCat() {
		return mediumCat;
	}

	public void setMediumCat(String mediumCat) {
		this.mediumCat = mediumCat;
	}

	public String getSmallCat() {
		return smallCat;
	}

	public void setSmallCat(String smallCat) {
		this.smallCat = smallCat;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	
	
	@Override
	public String toString(){
		return "poiid: " +this.poiid +", cityid: " +this.cityId +", province id: " +this.provinceId + ", countryid: " + this.countryId+
				", larget cateCode: " + this.largeCat +" , medium cateCode: " + this.mediumCat + ", small CateCode: " + this.smallCat;		
		
	}
	
	

}

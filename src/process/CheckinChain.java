package process;

import java.io.Serializable;
import java.util.ArrayList;

public class CheckinChain implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String POI_LVEL="poi";
	public static final String CITY_LVEL="city";
	public static final String PROVINCE_LVEL="province";
	public static final String COUNTRY_LVEL="country";
	
	public static final String HIGH_LVEL="group";
	public static final String MEDIUM_LVEL="division";
	public static final String LOW_LVEL="class";
	
	private ArrayList<CheckinElem> checkinChain;
	private String uid;

	public CheckinChain() {
		this.checkinChain = new ArrayList<CheckinElem>();
	}
	
	
	
	
	public String getUid() {
		return uid;
	}




	public void setUid(String uid) {
		this.uid = uid;
	}




	public int lengh(){
		return this.checkinChain.size();
	}
	
	public void addCheckinElem(CheckinElem chk){
		this.checkinChain.add(chk);
	}
	
	public ArrayList<CheckinElem> getElems(){
		return this.checkinChain;
	}
	
	/**
	 * Calculate Spatial-Semantic Similarities here.
	 * @param chkChain
	 * @return
	 */
	public float calcSim(CheckinChain chkChain,String scale, String semanticLevel,float alpha){
		
		float spatSim = this.calcSpatSim(chkChain, scale);
		if(scale.equals(POI_LVEL)){
			return spatSim;
		}
		float semanSim = this.calcSemanSim(chkChain, semanticLevel);
		return spatSim*alpha +(1-alpha)*semanSim;
		
	}
	
	
	
	
	public float calcSpatSim(CheckinChain chkChain, String level){
		ArrayList<String> chain1 = new ArrayList<String>();
		ArrayList<String> chain2 = new ArrayList<String>();
		DamerauLevensheinDistance dlDis = new DamerauLevensheinDistance(1.0f, 1.0f, 1.0f, 1f);
		switch(level){
			case "poi":  // poi level.
				for(CheckinElem elem:this.checkinChain){
					chain1.add(elem.getPoiid());
				}
				for(CheckinElem elem:chkChain.getElems()){
					chain2.add(elem.getPoiid());
				}
				break;
				//return dlDis.executeTerms(chain1, chain2);
			case "city": //City level
				for(CheckinElem elem:this.checkinChain){
					chain1.add(elem.getCityId());
				}
				for(CheckinElem elem:chkChain.getElems()){
					chain2.add(elem.getCityId());
				}
				break;
				//return dlDis.executeTerms(chain1, chain2);
			case "province":
				for(CheckinElem elem:this.checkinChain){
					chain1.add(elem.getProvinceId());
				}
				for(CheckinElem elem:chkChain.getElems()){
					chain2.add(elem.getProvinceId());
				}
				break;
			case "country":
				for(CheckinElem elem:this.checkinChain){
					chain1.add(elem.getCountryId());
				}
				for(CheckinElem elem:chkChain.getElems()){
					chain2.add(elem.getCountryId());
				}
				break;
				default:
					return 0.0f;
		}
		
		return dlDis.executeTermsScore(chain1, chain2);
	}
	
	
	/**
	 * Calculate the semantic Similarity.
	 * @param chkChain
	 * @param level  ""
	 * @return
	 */
	public float calcSemanSim(CheckinChain chkChain, String level){
		ArrayList<String> chain1 = new ArrayList<String>();
		ArrayList<String> chain2 = new ArrayList<String>();
		DamerauLevensheinDistance dlDis = new DamerauLevensheinDistance(1, 1, 1, 1);
		switch(level){
			case "class": //class level.
				for(CheckinElem elem:this.checkinChain){
					chain1.add(elem.getSmallCat());
				}
				for(CheckinElem elem:chkChain.getElems()){
					chain2.add(elem.getSmallCat());
				}
				break;
			case "division":  //division level/
				for(CheckinElem elem:this.checkinChain){
					chain1.add(elem.getMediumCat());
				}
				for(CheckinElem elem:chkChain.getElems()){
					chain2.add(elem.getMediumCat());
				}
				break;
			case "group":   //group level.
				for(CheckinElem elem:this.checkinChain){
					chain1.add(elem.getLargeCat());
				}
				for(CheckinElem elem:chkChain.getElems()){
					chain2.add(elem.getLargeCat());
				}
				break;
			default:
				return 0.0f;
		}
		return dlDis.executeTermsScore(chain1, chain2);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.uid+" \n");
		for(CheckinElem elem: this.checkinChain){
			sb.append(elem.toString() + "\n");
		}
		return sb.toString();
	}
	
	
	

}

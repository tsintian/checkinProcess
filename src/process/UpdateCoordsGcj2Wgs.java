package process;

import db.daoimps.POIImp;

public class UpdateCoordsGcj2Wgs {
	public static void main(String[] args){
		POIImp poiImp = new POIImp();
		poiImp.geoconv_gcj2wgs();
		poiImp.colseConn();
		System.out.println("game over!");
	}
}

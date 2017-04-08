package test;

import org.junit.Before;
import org.junit.Test;

import db.daoimps.POIImp;

public class TestPOIImp {
	
	POIImp poiImp = new POIImp();
	String poiid = "B2094757DB6EA6FC439D";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetPOIbyPoiId() {
		System.out.println("poi: "+poiImp.getPOIbyPoiId(poiid));
		poiImp.colseConn();
	}

}

package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import chk.dataSerialization.ChkDataObject;
import chk.dataSerialization.ChkDataSerialization;
import cluster.chkDbscan.ChkCluster;
import cluster.chkDbscan.ChkDBSCANClusterer;
import cluster.chkDbscan.ChkDLDistance;
import cluster.chkDbscan.ChkUserPoint;
import db.daoimps.CheckinsImp;
import db.daoimps.UserInfoImp;
import process.CheckinChain;

public class TestChkCluster {
	
	ArrayList<ChkUserPoint> points = new ArrayList<>();
	
	
	
	public void init1() throws Exception {
		UserInfoImp uii = new UserInfoImp();
		CheckinsImp chkimp = new CheckinsImp();
		ArrayList<String> uids = uii.getAllUserId();
		int i=0;
		for(String u: uids){
			CheckinChain chain = chkimp.getCheckinsByUserId(u);
			System.out.println(i++ +" user : " + u+ " chain size: " + chain.lengh());
			points.add(new ChkUserPoint(u,chain));
		}
		uii.colseConn();
		chkimp.colseConn();
	}
	
	@Before
	public void setUp() throws ClassNotFoundException, IOException{
		ChkDataSerialization chkS = new ChkDataSerialization();
		ChkDataObject data = chkS.readData("./data200.ser");
		for(CheckinChain chain:data.getData()){
			points.add(new ChkUserPoint(chain.getUid(),chain));
		}
	}
	

	@Test
	public void TestChkClusterer() {
		ChkDLDistance dlm = new ChkDLDistance(CheckinChain.CITY_LVEL, CheckinChain.MEDIUM_LVEL, 0.5f);
		ChkDBSCANClusterer<ChkUserPoint> dbscan = new ChkDBSCANClusterer<ChkUserPoint>(0.5, 20, dlm);
		List<ChkCluster<ChkUserPoint>> clusters = dbscan.cluster(points);
		int i=1;
		for(ChkCluster<ChkUserPoint> c:clusters){
			System.out.print(i + ":  size: " + c.getPoints().size()+". ");
			for(ChkUserPoint up:c.getPoints()){
				System.out.print(up.getUserId()+", ");
			}
			System.out.println();
		}
	}

	

}

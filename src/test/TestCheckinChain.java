package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import db.daoimps.CheckinsImp;
import process.CheckinChain;

public class TestCheckinChain {
	private CheckinChain  chkChain1 = null;
	private CheckinChain chkChain2 = null;
	private String user1id="2708788183"; //2708788183, 5202707928
	private String user2id = "5202707928"; //1987233495

	@Before
	public void setUp() throws Exception {
		CheckinsImp chksImp = new CheckinsImp();
		chkChain1 =  chksImp.getCheckinsByUserId(user1id);
		chkChain2 =  chksImp.getCheckinsByUserId(user2id);
	}

	@Test
	public void testCalcSim() {
		float sim = this.chkChain1.calcSim(chkChain2, CheckinChain.COUNTRY_LVEL, CheckinChain.HIGH_LVEL,0.1f);
		float spatSim = this.chkChain1.calcSpatSim(chkChain2, CheckinChain.COUNTRY_LVEL);
		float semanSim = this.chkChain1.calcSemanSim(chkChain2, CheckinChain.HIGH_LVEL);
		System.out.println("Spatial Similarity: "+spatSim);
		System.out.println("Semantic Similarity: " + semanSim);
		System.out.println("similarity: " + sim);
	}

	@Test
	public void testCalcSpatSim() {
		float sim = this.chkChain1.calcSpatSim(chkChain2, CheckinChain.POI_LVEL);
		System.out.println("spatial similarity: " + sim);
	}

	@Test
	public void testCalcSemanSim() {
		float sim = this.chkChain1.calcSemanSim(chkChain2, CheckinChain.LOW_LVEL);
		System.out.println("semantic similarity: " + sim);
	}

}

package test;

import org.junit.Before;
import org.junit.Test;

import db.daoimps.CheckinsImp;
import process.CheckinChain;

public class TestCheckinsImp {
	private CheckinsImp chksImp = null;
	private String userId = null;

	@Before
	public void setUp() throws Exception {
		this.chksImp = new CheckinsImp();
		this.userId = "2786818682"; //1633950591, 2708788183
	}

	@Test
	public void testGetCheckinsByUserId() {
		CheckinChain chkChain = chksImp.getCheckinsByUserId(userId);
		chksImp.colseConn();
		System.out.println("Checkins : " + chkChain.toString());
	}

}

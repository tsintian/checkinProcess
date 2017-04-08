package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cluster.chkDbscan.ChkUserPoint;
import db.daoimps.CheckinsImp;

public class TestChkUserPointEquals {
	
	private String uid;
	ChkUserPoint user1 = null;
	ChkUserPoint user2 = null;

	@Before
	public void setUp() throws Exception {
		uid = "2710516841";
		CheckinsImp chkImp = new CheckinsImp();
		user1 = new ChkUserPoint(uid, chkImp.getCheckinsByUserId(uid));
		user2 = new ChkUserPoint(uid, chkImp.getCheckinsByUserId(uid));
		
	}

	@Test
	public void test() {
		System.out.println("user equals: " + user1.equals(user2));
		System.out.println("hash code equals: " + (user1.hashCode() == user2.hashCode()));
		System.out.println("== : " + (user1 == user2));
	}
	
	
	@Test
	public void testListContains(){
		List<ChkUserPoint> list = new ArrayList<ChkUserPoint>();
		list.add(user1);
		System.out.println("if list contains user1: " + list.contains(user1));
	}

}

package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import chk.dataSerialization.ChkDataObject;
import chk.dataSerialization.ChkDataSerialization;
import process.CheckinChain;

public class TestChkSerialization {
	
	ChkDataSerialization chkS = new ChkDataSerialization();
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSaveData() {
		try {
			chkS.saveData("./data500.ser");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testReadData() {
		try {
			ChkDataObject data = chkS.readData("./data200.ser");
			System.out.println("number of uid: " + data.getData().size());
			int i=0;
			for(CheckinChain chain: data.getData()){
				System.out.println(++i +"user :"  + chain.getUid() + ", data: "+ chain);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

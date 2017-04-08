package chk.dataSerialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import db.daoimps.CheckinsImp;
import db.daoimps.UserInfoImp;
import process.CheckinChain;

public class ChkDataSerialization {
	
	
	
	public ChkDataSerialization() {
	
	}
	
	
	public void saveData(String filePath) throws IOException{
		ChkDataObject data = new ChkDataObject();
		UserInfoImp uiip = new UserInfoImp();
		CheckinsImp chkImp = new CheckinsImp();
		ArrayList<String> uids = uiip.getAllUserId();
		int i=0;
		for(String u: uids){
			CheckinChain chain = chkImp.getCheckinsByUserId(u);
			if(chain != null && chain.lengh() >1){
				
				chain.setUid(u);
				System.out.println(++i +"saving chain: " + chain);
				data.addCheckChain(chain);
			}
		}
		uiip.colseConn();
		chkImp.colseConn();
		
		FileOutputStream fileOut =  new FileOutputStream(filePath);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(data);
		out.close();
		fileOut.close();
		System.out.println("Check in data is saved in "+ filePath);
	}

	
	public ChkDataObject readData(String filePath) throws IOException, ClassNotFoundException{
		ChkDataObject data = null;
		FileInputStream fileIn = new FileInputStream(filePath);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		data = (ChkDataObject) in.readObject();
		fileIn.close();
		return data;
	}

}

package chk.dataSerialization;

import java.io.Serializable;
import java.util.ArrayList;

import process.CheckinChain;

public class ChkDataObject implements Serializable {

	/**
	 * 
	 */
		
	private static final long serialVersionUID = 1L;
	
	ArrayList<CheckinChain> data = new ArrayList<CheckinChain>();
	

	public ChkDataObject() {
		
	}

	public void addCheckChain(CheckinChain chain){
		this.data.add(chain);
	}

	public ArrayList<CheckinChain> getData() {
		return data;
	}


	public void setData(ArrayList<CheckinChain> data) {
		this.data = data;
	}
	
	

}

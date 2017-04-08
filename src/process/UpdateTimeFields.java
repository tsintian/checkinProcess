package process;

import db.daoimps.CheckinsImp;

public class UpdateTimeFields {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CheckinsImp ckImp = new CheckinsImp();
		ckImp.updateTimeInfoFields(true);
		ckImp.colseConn();
		System.out.println("Game Over");
	}

}

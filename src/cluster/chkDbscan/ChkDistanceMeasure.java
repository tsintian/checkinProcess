package cluster.chkDbscan;

import java.io.Serializable;

import process.CheckinChain;

public interface ChkDistanceMeasure extends Serializable {
	
	/**
	 * Compute the similarity of the two check-in Chain; 
	 * @param a Check-in Chain a
	 * @param b Check-in Chain b
	 * @return
	 */
	double compute(CheckinChain a, CheckinChain b);

}

package cluster.chkDbscan;

import process.CheckinChain;

public class ChkDLDistance implements ChkDistanceMeasure {
	private String scale;
	private String semanticLevel;
	private float  spatWeight;

	public ChkDLDistance(String scale, String semanticLevel, float spatWeight) {
		this.scale = scale;
		this.semanticLevel = semanticLevel;
		this.spatWeight = spatWeight;
	}

	@Override
	public double compute(CheckinChain a, CheckinChain b) {
		//TODO:  to be deleted here.
		double sim =  a.calcSim(b, scale, semanticLevel, spatWeight);
		System.out.println("sim: " + sim);
		return a.calcSim(b, scale, semanticLevel, spatWeight);
	}

}

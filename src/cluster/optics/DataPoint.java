package cluster.optics;

public interface DataPoint {
	public double distance(DataPoint datapoint);
	
	public void setCluster(int id);
	
	public int getCluster();
	
	public int getX();
	
	public int getY();

}

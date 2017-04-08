package cluster.optics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OPTICS {
	
	public List points;
	private List order;
	
	public double max_distance;
	public int min_points;
	
	public boolean[] processed;
	private double[] reachability;
	private double[] core_distance;
	
	private double[][] distances;
	
	

	public OPTICS(double max_distance, int min_points) {
		this.points  = new ArrayList<>();
		this.order = new ArrayList<>();
		
		this.max_distance = max_distance;
		this.min_points = min_points;
	}
	
	
	public void cluster() {
		calculateDistance();

		Iterator it = points.iterator();
		int n = 0;

		while (it.hasNext()) {
			DataPoint point = (DataPoint) it.next();
			if (!processed[n]) {
				processed[n] = true;
				List neighbors = getNeighors(point, n);
				order.add(point);
				if (core_distance[n] != -1) {
					List<Pair<Integer, Double>> seeds = new ArrayList<Pair<Integer, Double>>();
					update(point, seeds, neighbors, n);

					for (Pair<Integer, Double> seed : seeds) {
						int node = seed.getFirst();
						processed[node] = true;
						DataPoint seedPoint = (DataPoint) points.get(node);
						List seedNeighbors = getNeighors(seedPoint, node);
						order.add(seedPoint);

						if (core_distance[node] != -1) {
							update(seedPoint, seeds, seedNeighbors, node);
						}
					}
				}
			}
		}
	}
	
	private void update(DataPoint point, List<Pair<Integer, Double>> seeds, List neighbors, int node) {

		for (int i = 0; i < neighbors.size(); i++) {
			int index = (int) neighbors.get(i);
			if (!processed[index]) {
				double max = Math.max(core_distance[node], distances[node][index]);

				if (reachability[index] == -1) {
					reachability[index] = max;
					insertSeed(seeds, new Pair<Integer, Double>(index, max), false);
				} else if (max < reachability[index]) {
					reachability[index] = max;
					insertSeed(seeds, new Pair<Integer, Double>(index, max), true);
				}
			}
		}
	}
	
	public void insertSeed(List<Pair<Integer,Double>> seeds, Pair<Integer,Double> seed, boolean remove) {
		int index = seeds.size() +1;
		
		int node = seed.getFirst();
		double distance = seed.getSecond();
		
		boolean done = false;
		
		for(int i = 0; i < seeds.size(); i++) {
			Pair<Integer,Double> aux = seeds.get(i);
			double aux_distance = aux.getSecond();
			if(distance < aux_distance) {
				seeds.add(i,seed);
				done = true;
				if(remove) {
					index = i + 1;
				}
				break;
			}
		}
		for(int i = index; i < seeds.size() ; i++) {
			Pair<Integer,Double> aux = seeds.get(i);
			int aux_index = aux.getFirst();
			if(node == aux_index) {
				seeds.remove(i);
				break;
			}
		}
		
		if(!done) {
			seeds.add(seeds.size(),seed);
		}
	}
	
	private List getNeighors(DataPoint d, int index){
		List neighbors = new ArrayList();
		int i=0;
		for(Iterator iterator= points.iterator();iterator.hasNext();){
			iterator.next();
			double distance = distances[index][i];
			
			if(distance<=max_distance){
				neighbors.add(i);
				if(i == min_points-1){
					core_distance[index] = distance;
				}
			}
			i++;
		}
		return neighbors;
	}
	
	public void setPoints(List points){
		this.points = points;
		this.processed = new boolean[points.size()];
		this.reachability = new double[points.size()];
		this.core_distance = new double[points.size()];
		
		for( int i=0; i< reachability.length;i++){
			reachability[i] = -1;
			core_distance[i] = -1;
		}
	}
	
	private void calculateDistance(){
		System.out.println("Calculation distance between: " + points.size() + " points");
		int size = points.size();
		this.distances = new double[size][size];
		
		for( int i=0; i< size;i++){
			DataPoint p1 = (DataPoint) points.get(i);
			for(int j=i+1; j < size; j++){
				DataPoint p2 = (DataPoint) points.get(j);
				double distance = p1.distance(p2);
				
				distances[i][j] = distance;
				distances[j][i] = distance;
			}
		}
	}

}

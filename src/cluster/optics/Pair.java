package cluster.optics;

import java.util.Objects;

public class Pair<P,S> {
	
	private P first;
	private S second;

	public Pair(P first, S second) {
		this.first = first;
		this.second = second;
	}

	public P getFirst() {
		return first;
	}

	public void setFirst(P first) {
		this.first = first;
	}

	public S getSecond() {
		return second;
	}

	public void setSecond(S second) {
		this.second = second;
	}

	@Override
	public int hashCode() {
		return (first == null ? 0 : first.hashCode())^( second == null ? 0 : second.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Pair)){
			return false;
		}
		
		Pair p = (Pair)obj;
		return Objects.equals(p.first, first) && Objects.equals(p.second, second);
	}

	@Override
	public String toString() {
		return "< " + this.first +" , " + second +">";
	}
	
	
	

}

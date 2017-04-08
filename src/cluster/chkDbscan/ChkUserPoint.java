package cluster.chkDbscan;

import process.CheckinChain;

public class ChkUserPoint implements ChkClusterable {
	
	

	private String userId;
	private CheckinChain chkChain;
	
	public ChkUserPoint(String userId, CheckinChain chkChain) {
		this.userId = userId;
		this.chkChain = chkChain;
	}
	

	@Override
	public CheckinChain getPoint() {
		return this.chkChain;
	}
	
	public String getUserId(){
		return userId;
	}


	@Override
	public String toString() {
		return "user id: "+ userId + " chain: " + chkChain.toString();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChkUserPoint other = (ChkUserPoint) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
	
	
	
	

}

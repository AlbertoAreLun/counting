package es.us.agoraus.counting.dto;

public class AgeMayorityVote extends Vote {
	
	private int minorsCount;
	private int mayorityCount;
	
	public AgeMayorityVote(){
		super();
	}
	public AgeMayorityVote(int id, int mayorityCount, int minorsCount){
		super(id);
		this.minorsCount=minorsCount;
		this.mayorityCount=mayorityCount;
	}
	
	public int getMinorsCount() {
		return minorsCount;
	}
	public void setCount(int minorsCount) {
		this.minorsCount = minorsCount;
	}
	public void increaseMinorsCount(int numberToIncrease) {
		this.minorsCount += numberToIncrease;
	}
	
	public int getMayorityCount() {
		return mayorityCount;
	}
	public void setMayorityCount(int mayorityCount) {
		this.mayorityCount = mayorityCount;
	}
	public void increaseMayorityCount(int numberToIncrease) {
		this.mayorityCount += numberToIncrease;
	}

}

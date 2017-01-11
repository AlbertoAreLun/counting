package es.us.agoraus.counting.dto;

public class GenderVote extends Vote {
	
	private int femaleCount;
	private int maleCount;
	
	public GenderVote(){
		super();
	}
	public GenderVote(int id, int femaleCount, int maleCount){
		super(id);
		this.femaleCount=femaleCount;
		this.maleCount=maleCount;
	}
	
	public int getFemaleCount() {
		return femaleCount;
	}
	public void setFemaleCount(int femaleCount) {
		this.femaleCount = femaleCount;
	}
	
	public int getMaleCount() {
		return maleCount;
	}
	public void setMaleCount(int maleCount) {
		this.maleCount = maleCount;
	}
	
	public void increaseFemaleCount(int numberToIncrease) {
		this.femaleCount += numberToIncrease;
	}
	
	public void increaseMaleCount(int numberToIncrease) {
		this.maleCount += numberToIncrease;
	}

}

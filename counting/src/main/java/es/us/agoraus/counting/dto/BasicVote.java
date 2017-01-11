package es.us.agoraus.counting.dto;

public class BasicVote extends Vote {
	
	private int count;
	
	public BasicVote(){
		super();
	}
	public BasicVote(int id, int count){
		super(id);
		this.count=count;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void increaseCount(int numberToIncrease) {
		this.count += numberToIncrease;
	}

}

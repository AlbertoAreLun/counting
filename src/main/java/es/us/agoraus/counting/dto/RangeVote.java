package es.us.agoraus.counting.dto;

public class RangeVote extends Vote {
	
	private int count;
	private int nocount;
	
	public RangeVote(){
		super();
	}
	public RangeVote(int id, int count, int nocount){
		super(id);
		this.count=count;
		this.nocount=nocount;
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
	public int getNocount() {
		return nocount;
	}
	public void setNocount(int nocount) {
		this.nocount = nocount;
	}
	public void increaseNoCount(int numberToIncrease) {
		this.nocount += numberToIncrease;
	}

}

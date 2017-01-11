package es.us.agoraus.counting.dto;

public class UsualAgeRangesVote extends Vote {
	
	private int countUnder30;
	
	private int count30To60;
	
	private int countOver60;

	
	public UsualAgeRangesVote(){
		super();
	}
	public UsualAgeRangesVote(int id, int countUnder30, int count30To60, int countOver60){
		super(id);
		this.countUnder30=countUnder30;
		this.count30To60=count30To60;
		this.countOver60=countOver60;
	}
	
	public int getCountUnder30() {
		return countUnder30;
	}
	public void setCountUnder30(int countUnder30) {
		this.countUnder30 = countUnder30;
	}
	public void increaseCountUnder30(int numberToIncrease) {
		this.countUnder30 += numberToIncrease;
	}
	
	public int getCount30To60() {
		return count30To60;
	}
	public void setCount30To60(int count30To60) {
		this.count30To60 = count30To60;
	}
	public void increaseCount30To60(int numberToIncrease) {
		this.count30To60 += numberToIncrease;
	}
	
	public int getCountOver60() {
		return countOver60;
	}
	public void setCountOver60(int countOver60) {
		this.countOver60 = countOver60;
	}
	public void increaseCountOver60(int numberToIncrease) {
		this.countOver60 += numberToIncrease;
	}

}

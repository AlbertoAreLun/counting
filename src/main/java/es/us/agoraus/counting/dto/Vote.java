package es.us.agoraus.counting.dto;

public abstract class Vote {
	
	private int id;
	
	public Vote(){
		
	}
	public Vote(int id){
		this.id=id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}

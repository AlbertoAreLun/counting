package es.us.agoraus.counting.dto;

public class StoredVote {

	private int id;
	private int age;
	private String gender;
	private String community;
	
	public StoredVote(){
		
	}
	public StoredVote(int id, int age, String gender, String community){
		this.id=id;
		this.age=age;
		this.gender=gender;
		this.community=community;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCommunity() {
		return community;
	}
	public void setCommunity(String community) {
		this.community = community;
	}

}
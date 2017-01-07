package es.us.agoraus.counting.dto;

import org.springframework.util.StringUtils;

public class Vote {

	private String age;
	private String community;
	private String gender;
	private String id;
	
	public Vote(){
		
	}
	public Vote(String id, String age, String gender, String community){
		this.age=age;
		this.community=community;
		this.gender=gender;
		this.id=id;
	}
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isValid() {
		return StringUtils.hasText(age) && StringUtils.hasText(community) && StringUtils.hasText(gender)
				&& StringUtils.hasText(id);
	}

	@Override
	public String toString() {
		return "Vote [age=" + age + ",community=" + community + ", gender=" + gender + ", id=" + id + "]";
	}

}
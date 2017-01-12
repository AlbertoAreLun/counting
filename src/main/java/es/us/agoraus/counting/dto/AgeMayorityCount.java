package es.us.agoraus.counting.dto;

import java.util.List;

public class AgeMayorityCount {
	
	private String status;
	private int pollId;
	private List<AgeMayorityVote> votes;
	
	public AgeMayorityCount(){
		
	}
	public AgeMayorityCount(String status, int pollId, List<AgeMayorityVote> votes){
		this.status=status;
		this.votes=votes;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getPollId() {
		return pollId;
	}
	public void setPollId(int pollId) {
		this.pollId = pollId;
	}
	
	
	public List<AgeMayorityVote> getVotes() {
		return votes;
	}
	
	public void setVotes(List<AgeMayorityVote> votes) {
		this.votes = votes;
	}
	
	public void addVote(AgeMayorityVote vote) {
		this.votes.add(vote);
	}

}

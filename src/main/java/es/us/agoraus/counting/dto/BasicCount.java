package es.us.agoraus.counting.dto;

import java.util.List;

public class BasicCount {
	
	private String status;
	private int pollId;
	private List<BasicVote> votes;
	
	public BasicCount(){
		
	}
	public BasicCount(String status, int pollId, List<BasicVote> votes){
		this.status=status;
		this.pollId=pollId;
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
	
	public List<BasicVote> getVotes() {
		return votes;
	}
	public void setVotes(List<BasicVote> votes) {
		this.votes = votes;
	}
	public void addVote(BasicVote vote) {
		this.votes.add(vote);
	}

}

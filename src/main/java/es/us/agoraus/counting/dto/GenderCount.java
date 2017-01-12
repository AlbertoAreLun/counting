package es.us.agoraus.counting.dto;

import java.util.List;

public class GenderCount {
	
	private String status;
	private int pollId;
	private List<GenderVote> votes;
	
	public GenderCount(){
		
	}
	public GenderCount(String status, int pollId, List<GenderVote> votes){
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
	
	public List<GenderVote> getVotes() {
		return votes;
	}
	public void setVotes(List<GenderVote> votes) {
		this.votes = votes;
	}
	public void addVote(GenderVote vote) {
		this.votes.add(vote);
	}

}

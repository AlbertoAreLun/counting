package es.us.agoraus.counting.dto;

import java.util.List;

public class RangeCount {
	
	private String status;
	private int pollId;
	private List<RangeVote> votes;
	
	public RangeCount(){
		
	}
	public RangeCount(String status, int pollId, List<RangeVote> votes){
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
	
	public List<RangeVote> getVotes() {
		return votes;
	}
	public void setVotes(List<RangeVote> votes) {
		this.votes = votes;
	}
	public void addVote(RangeVote vote) {
		this.votes.add(vote);
	}

}

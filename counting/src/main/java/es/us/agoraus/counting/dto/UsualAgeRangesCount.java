package es.us.agoraus.counting.dto;

import java.util.List;

public class UsualAgeRangesCount {
	
	private String status;
	private int pollId;
	private List<UsualAgeRangesVote> votes;
	
	public UsualAgeRangesCount(){
		
	}
	public UsualAgeRangesCount(String status, int pollId, List<UsualAgeRangesVote> votes){
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
	
	
	public List<UsualAgeRangesVote> getUsualAgeRangesVotes() {
		return votes;
	}
	
	public void setUsualAgeRangesVotes(List<UsualAgeRangesVote> votes) {
		this.votes = votes;
	}
	
	public void addMUsualAgeRangesVote(UsualAgeRangesVote vote) {
		this.votes.add(vote);
	}

}

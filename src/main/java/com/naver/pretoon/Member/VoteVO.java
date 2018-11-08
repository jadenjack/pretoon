package com.naver.pretoon.Member;

public class VoteVO {
	private String name;
	private int vote;
	
	public VoteVO(String name, int vote) {
		this.name = name;
		this.vote = vote;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getVote() {
		return vote;
	}
	public void setVote(int vote) {
		this.vote = vote;
	}
}

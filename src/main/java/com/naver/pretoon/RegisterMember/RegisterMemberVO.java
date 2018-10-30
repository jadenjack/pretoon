package com.naver.pretoon.RegisterMember;

public class RegisterMemberVO {
	private String name;
	private String description;
	private int vote;
	private String image;
	
	public RegisterMemberVO(String name, String description, int vote, String image) {
		this.name = name;
		this.description = description;
		this.vote = vote;
		this.image = image;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getVote() {
		return vote;
	}
	public void setVote(int vote) {
		this.vote = vote;
	}
	public String getImage() {
		return this.image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}

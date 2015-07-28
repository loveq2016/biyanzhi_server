package com.biyanzhi.bean;

//pkͶƱ
public class PKVote {
	private int pk_id;
	private int vote_user_id;

	public PKVote() {
	}

	public PKVote(int pk_id, int vote_user_id) {
		this.pk_id = pk_id;
		this.vote_user_id = vote_user_id;
	}

	public int getPk_id() {
		return pk_id;
	}

	public void setPk_id(int pk_id) {
		this.pk_id = pk_id;
	}

	public int getVote_user_id() {
		return vote_user_id;
	}

	public void setVote_user_id(int vote_user_id) {
		this.vote_user_id = vote_user_id;
	}

}

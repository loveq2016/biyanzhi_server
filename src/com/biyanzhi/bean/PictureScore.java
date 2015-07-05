package com.biyanzhi.bean;

public class PictureScore {
	private int id;
	private int user_id;
	private int picture_id;
	private int picture_score;
	private String play_score_time;
	private User user;

	public String getPlay_score_time() {
		return play_score_time;
	}

	public void setPlay_score_time(String play_score_time) {
		this.play_score_time = play_score_time;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getPicture_id() {
		return picture_id;
	}

	public void setPicture_id(int picture_id) {
		this.picture_id = picture_id;
	}

	public int getPicture_score() {
		return picture_score;
	}

	public void setPicture_score(int picture_score) {
		this.picture_score = picture_score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}

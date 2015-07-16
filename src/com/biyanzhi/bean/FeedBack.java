package com.biyanzhi.bean;

public class FeedBack {
	private int user_id;
	private String feedback_content = "";
	private String feedback_time = "";

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFeedback_content() {
		return feedback_content;
	}

	public void setFeedback_content(String feedback_content) {
		this.feedback_content = feedback_content;
	}

	public String getFeedback_time() {
		return feedback_time;
	}

	public void setFeedback_time(String feedback_time) {
		this.feedback_time = feedback_time;
	}

}

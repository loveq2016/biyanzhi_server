package com.biyanzhi.bean;

import java.util.ArrayList;
import java.util.List;

public class Picture {
	private int picture_id;
	private int publisher_id = 0;
	private String publish_time = "";
	// private String publish_time_last_update = "";
	private String content = "";
	private String publisher_name = "";
	private String publisher_avatar = "";
	private String picture_image_url = "";
	private int average_score;
	private boolean is_play_score;

	private int score_number;// �������
	private List<Comment> comments = new ArrayList<Comment>();

	public boolean isIs_play_score() {
		return is_play_score;
	}

	public void setIs_play_score(boolean is_play_score) {
		this.is_play_score = is_play_score;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public int getScore_number() {
		return score_number;
	}

	public void setScore_number(int score_number) {
		this.score_number = score_number;
	}

	public int getAverage_score() {
		return average_score;
	}

	public void setAverage_score(int average_score) {
		this.average_score = average_score;
	}

	public int getPicture_id() {
		return picture_id;
	}

	public void setPicture_id(int picture_id) {
		this.picture_id = picture_id;
	}

	public int getPublisher_id() {
		return publisher_id;
	}

	public void setPublisher_id(int publisher_id) {
		this.publisher_id = publisher_id;
	}

	public String getPublish_time() {
		return publish_time;
	}

	public void setPublish_time(String publish_time) {
		this.publish_time = publish_time;
	}

	// public String getPublish_time_last_update() {
	// return publish_time_last_update;
	// }
	//
	// public void setPublish_time_last_update(String publish_time_last_update)
	// {
	// this.publish_time_last_update = publish_time_last_update;
	// }

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPublisher_name() {
		return publisher_name;
	}

	public void setPublisher_name(String publisher_name) {
		this.publisher_name = publisher_name;
	}

	public String getPublisher_avatar() {
		return publisher_avatar;
	}

	public void setPublisher_avatar(String publisher_avatar) {
		this.publisher_avatar = publisher_avatar;
	}

	public String getPicture_image_url() {
		return picture_image_url;
	}

	public void setPicture_image_url(String picture_image_url) {
		this.picture_image_url = picture_image_url;
	}

}

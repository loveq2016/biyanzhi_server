package com.biyanzhi.bean;

import java.util.ArrayList;
import java.util.List;

public class Picture {
	private int picture_id;
	private int publisher_id = 0;
	private String publish_time = "";
	private String content = "";
	private String publisher_name = "";
	private String publisher_avatar = "";
	// private List<PictureImage> images = new ArrayList<PictureImage>();
	private String picture_image_url = "";
	private int average_score;

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

	// public List<PictureImage> getImages() {
	// return images;
	// }
	//
	// public void setImages(List<PictureImage> images) {
	// this.images = images;
	// }

}

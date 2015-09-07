package com.biyanzhi.bean;

import java.util.ArrayList;
import java.util.List;

public class ShuoShuo {
	private int shuoshuo_id = 0;
	private int publisher_id = 0;
	private String time = "";
	private String content = "";
	private List<ShuoShuoImage> images = new ArrayList<ShuoShuoImage>();
	private List<ShuoShuoComment> comments = new ArrayList<ShuoShuoComment>();
	private String publisher_name = "";
	private String publisher_avatar = "";
	private boolean isPraise;// 1 ÔŞ 0Î´ÔŞ
	private List<ShuoShuoPraise> praises = new ArrayList<ShuoShuoPraise>();
	private int state;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public List<ShuoShuoPraise> getPraises() {
		return praises;
	}

	public void setPraises(List<ShuoShuoPraise> praises) {
		this.praises = praises;
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

	public int getPublisher_id() {
		return publisher_id;
	}

	public void setPublisher_id(int publisher_id) {
		this.publisher_id = publisher_id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<ShuoShuoImage> getImages() {
		return images;
	}

	public void setImages(List<ShuoShuoImage> images) {
		this.images = images;
	}

	public List<ShuoShuoComment> getComments() {
		return comments;
	}

	public void setComments(List<ShuoShuoComment> comments) {
		this.comments = comments;
	}

	public int getShuoshuo_id() {
		return shuoshuo_id;
	}

	public void setShuoshuo_id(int shuoshuo_id) {
		this.shuoshuo_id = shuoshuo_id;
	}

	public boolean isPraise() {
		return isPraise;
	}

	public void setPraise(boolean isPraise) {
		this.isPraise = isPraise;
	}

	@Override
	public String toString() {
		return "shuoshuo_id:" + this.shuoshuo_id + "  content:" + this.content
				+ "  images:" + this.images;
	}
}

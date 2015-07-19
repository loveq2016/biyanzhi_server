package com.biyanzhi.bean;

public class GuanZhu {
	private int user_id;
	private int guanzhu_user_id;
	private String guanzhu_time = "";

	public String getGuanzhu_time() {
		return guanzhu_time;
	}

	public void setGuanzhu_time(String guanzhu_time) {
		this.guanzhu_time = guanzhu_time;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getGuanzhu_user_id() {
		return guanzhu_user_id;
	}

	public void setGuanzhu_user_id(int guanzhu_user_id) {
		this.guanzhu_user_id = guanzhu_user_id;
	}

}

package com.biyanzhi.bean;

public class PK {
	private int pk_id;
	private int pk1_user_id;
	private String pk1_user_gender = "";
	private String pk1_user_picture = "";
	private int pk1_ticket_count;
	private int pk2_user_id;
	private String pk2_user_picture = "";
	private int pk2_ticket_count;
	private String pk_time = "";

	public int getPk_id() {
		return pk_id;
	}

	public void setPk_id(int pk_id) {
		this.pk_id = pk_id;
	}

	public String getPk1_user_gender() {
		return pk1_user_gender;
	}

	public void setPk1_user_gender(String pk1_user_gender) {
		this.pk1_user_gender = pk1_user_gender;
	}

	public int getPk1_user_id() {
		return pk1_user_id;
	}

	public void setPk1_user_id(int pk1_user_id) {
		this.pk1_user_id = pk1_user_id;
	}

	public String getPk1_user_picture() {
		return pk1_user_picture;
	}

	public void setPk1_user_picture(String pk1_user_picture) {
		this.pk1_user_picture = pk1_user_picture;
	}

	public int getPk2_user_id() {
		return pk2_user_id;
	}

	public void setPk2_user_id(int pk2_user_id) {
		this.pk2_user_id = pk2_user_id;
	}

	public String getPk2_user_picture() {
		return pk2_user_picture;
	}

	public void setPk2_user_picture(String pk2_user_picture) {
		this.pk2_user_picture = pk2_user_picture;
	}

	public int getPk1_ticket_count() {
		return pk1_ticket_count;
	}

	public void setPk1_ticket_count(int pk1_ticket_count) {
		this.pk1_ticket_count = pk1_ticket_count;
	}

	public int getPk2_ticket_count() {
		return pk2_ticket_count;
	}

	public void setPk2_ticket_count(int pk2_ticket_count) {
		this.pk2_ticket_count = pk2_ticket_count;
	}

	public String getPk_time() {
		return pk_time;
	}

	public void setPk_time(String pk_time) {
		this.pk_time = pk_time;
	}

}

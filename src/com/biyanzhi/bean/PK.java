package com.biyanzhi.bean;

import java.util.ArrayList;
import java.util.List;

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
	private boolean is_voted;// 是否已经投票
	private int pk_state;// 0 PK中 ，1 PK结束
	private int pk_winer_user_id;
	private List<PKVote> pkVotes = new ArrayList<PKVote>();

	private int pk1_win_count;
	private int pk1_fail_count;
	private int pk2_win_count;
	private int pk2_fail_count;

	public int getPk1_win_count() {
		return pk1_win_count;
	}

	public void setPk1_win_count(int pk1_win_count) {
		this.pk1_win_count = pk1_win_count;
	}

	public int getPk1_fail_count() {
		return pk1_fail_count;
	}

	public void setPk1_fail_count(int pk1_fail_count) {
		this.pk1_fail_count = pk1_fail_count;
	}

	public int getPk2_win_count() {
		return pk2_win_count;
	}

	public void setPk2_win_count(int pk2_win_count) {
		this.pk2_win_count = pk2_win_count;
	}

	public int getPk2_fail_count() {
		return pk2_fail_count;
	}

	public void setPk2_fail_count(int pk2_fail_count) {
		this.pk2_fail_count = pk2_fail_count;
	}

	public int getPk_state() {
		return pk_state;
	}

	public void setPk_state(int pk_state) {
		this.pk_state = pk_state;
	}

	public int getPk_winer_user_id() {
		return pk_winer_user_id;
	}

	public void setPk_winer_user_id(int pk_winer_user_id) {
		this.pk_winer_user_id = pk_winer_user_id;
	}

	public boolean isIs_voted() {
		return is_voted;
	}

	public void setIs_voted(boolean is_voted) {
		this.is_voted = is_voted;
	}

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

	public List<PKVote> getPkVotes() {
		return pkVotes;
	}

	public void setPkVotes(List<PKVote> pkVotes) {
		this.pkVotes = pkVotes;
	}
}

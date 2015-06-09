package com.biyanzhi.dao;

import java.util.List;

import com.biyanzhi.bean.GuanZhu;

public interface GuanZhuDao {
	int addGuanZhu(GuanZhu guanzhu);

	List<GuanZhu> getGuanZhuCountByUserID(int user_id);
}

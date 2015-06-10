package com.biyanzhi.dao;

import com.biyanzhi.bean.GuanZhu;

public interface GuanZhuDao {
	int addGuanZhu(GuanZhu guanzhu);

	int getGuanZhuCountByUserID(int user_id);
}

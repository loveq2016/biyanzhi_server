package com.biyanzhi.dao;

import org.apache.ibatis.annotations.Param;

import com.biyanzhi.bean.GuanZhu;

public interface GuanZhuDao {
	int addGuanZhu(GuanZhu guanzhu);

	int getGuanZhuCountByUserID(int user_id);

	int isGuanZhuByUserIDAndGuanZhuUserID(@Param("user_id") int user_id,
			@Param("guanzhu_user_id") int guanzhu_user_id);
}

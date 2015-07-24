package com.biyanzhi.dao;

import org.apache.ibatis.annotations.Param;

import com.biyanzhi.bean.GuanZhu;

public interface GuanZhuDao {
	int addGuanZhu(GuanZhu guanzhu);

	int getGuanZhuCountByUserID(int user_id);// 关注我的用户数量

	int getMyGuanZhuCountByUserID(int user_id);// 我关注的用户数量

	int isGuanZhuByUserIDAndGuanZhuUserID(@Param("user_id") int user_id,
			@Param("guanzhu_user_id") int guanzhu_user_id);

	int cancleGuanZhu(GuanZhu guanzhu);
}

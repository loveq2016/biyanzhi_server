package com.biyanzhi.dao;

import org.apache.ibatis.annotations.Param;

import com.biyanzhi.bean.PKResult;

public interface PKResultDao {
	int insert(PKResult result);

	int getResultByUserIDAndPictureID(@Param("user_id") int user_id,
			@Param("picture_id") int picture_id);

	int updateWinCount(@Param("user_id") int user_id,
			@Param("picture_id") int picture_id);

	int updateFailCount(@Param("user_id") int user_id,
			@Param("picture_id") int picture_id);
}

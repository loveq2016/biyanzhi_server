package com.biyanzhi.dao;

import org.apache.ibatis.annotations.Param;

import com.biyanzhi.bean.PKResult;

public interface PKResultDao {
	int insert(PKResult result);

	int getResultByUserIDAndPictureID(@Param("user_id") int user_id,
			@Param("picture_id") String picture_id);

	int updateWinCount(@Param("user_id") int user_id,
			@Param("picture_id") String picture_id,
			@Param("user_win_count") int user_win_count);

	int updateFailCount(@Param("user_id") int user_id,
			@Param("picture_id") String picture_id,
			@Param("user_fail_count") int user_fail_count);

	Integer getWinCount(@Param("user_id") int user_id,
			@Param("picture_id") String picture_id);

	Integer getFailCount(@Param("user_id") int user_id,
			@Param("picture_id") String picture_id);
}

package com.biyanzhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biyanzhi.bean.PictureScore;

public interface PictureScoreDao {
	int getPictureScores(int picture_id);

	Integer getPictureAvgScore(int picture_id);

	int addPictureScore(PictureScore pictureScore);

	List<PictureScore> getPlayScoreUserListByPictureID(@Param("page") int page,
			@Param("picture_id") int picture_id);

	// int getPlayScoreByUserID(int user_id);
}

package com.biyanzhi.dao;

import com.biyanzhi.bean.PictureScore;

public interface PictureScoreDao {
	int getPictureScores(int picture_id);

	Integer getPictureAvgScore(int picture_id);

	int addPictureScore(PictureScore pictureScore);
}

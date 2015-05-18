package com.biyanzhi.dao;

import java.util.List;

import com.biyanzhi.bean.PictureScore;

public interface PictureScoreDao {
	List<PictureScore> getPictureScores(int picture_id);

	Integer getPictureAvgScore(int picture_id);

	int addPictureScore(PictureScore pictureScore);
}

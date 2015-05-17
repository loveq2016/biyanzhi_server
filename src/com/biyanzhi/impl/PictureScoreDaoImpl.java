package com.biyanzhi.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.biyanzhi.bean.PictureScore;
import com.biyanzhi.dao.PictureScoreDao;
import com.biyanzhi.factory.MySqlSession;

@Repository
public class PictureScoreDaoImpl implements PictureScoreDao {

	public List<PictureScore> getPictureScores(int picture_id) {
		SqlSession sqlSession = MySqlSession.getSessionFactory().openSession();
		PictureScoreDao dao = sqlSession.getMapper(PictureScoreDao.class);
		return dao.getPictureScores(picture_id);
	}

	public int addPictureScore(PictureScore pictureScore) {
		try {
			SqlSession sqlSession = MySqlSession.getSessionFactory()
					.openSession();
			PictureScoreDao dao = sqlSession.getMapper(PictureScoreDao.class);
			dao.addPictureScore(pictureScore);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public int getPictureAvgScore(int picture_id) {
		int score;
		try {
			SqlSession sqlSession = MySqlSession.getSessionFactory()
					.openSession();
			PictureScoreDao dao = sqlSession.getMapper(PictureScoreDao.class);
			score = dao.getPictureAvgScore(picture_id);
			return score;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}

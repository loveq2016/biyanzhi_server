package com.biyanzhi.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.biyanzhi.bean.PictureScore;
import com.biyanzhi.dao.PictureScoreDao;
import com.biyanzhi.factory.MySqlSession;

@Repository
public class PictureScoreDaoImpl implements PictureScoreDao {

	public int getPictureScores(int picture_id) {
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

	public Integer getPictureAvgScore(int picture_id) {
		Integer score;
		try {
			SqlSession sqlSession = MySqlSession.getSessionFactory()
					.openSession();
			PictureScoreDao dao = sqlSession.getMapper(PictureScoreDao.class);
			score = dao.getPictureAvgScore(picture_id);
			if (null == score) {
				return 0;
			}
			return score;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}

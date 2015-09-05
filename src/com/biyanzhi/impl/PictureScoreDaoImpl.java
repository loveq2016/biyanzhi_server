package com.biyanzhi.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.biyanzhi.bean.PictureScore;
import com.biyanzhi.bean.User;
import com.biyanzhi.dao.PictureScoreDao;

@Repository
public class PictureScoreDaoImpl implements PictureScoreDao {
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	@Resource(name = "sqlSession")
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int getPictureScores(int picture_id) {
		PictureScoreDao dao = sqlSession.getMapper(PictureScoreDao.class);
		return dao.getPictureScores(picture_id);
	}

	public int addPictureScore(PictureScore pictureScore) {
		try {
			PictureScoreDao dao = sqlSession.getMapper(PictureScoreDao.class);
			dao.addPictureScore(pictureScore);
			// sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public Integer getPictureAvgScore(int picture_id) {
		Integer score;
		try {
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

	public List<PictureScore> getPlayScoreUserListByPictureID(int page,
			int picture_id) {
		PictureScoreDao dao = sqlSession.getMapper(PictureScoreDao.class);
		return dao.getPlayScoreUserListByPictureID((page - 1) * 10, picture_id);
	}

	// public int getPlayScoreByUserID(int user_id) {
	// PictureScoreDao dao = sqlSession.getMapper(PictureScoreDao.class);
	// return dao.getPlayScoreByUserID(user_id);
	// }

}

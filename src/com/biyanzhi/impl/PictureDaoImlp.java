package com.biyanzhi.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.biyanzhi.bean.Picture;
import com.biyanzhi.dao.PictureDao;

@Repository
public class PictureDaoImlp implements PictureDao {
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	@Resource(name = "sqlSession")
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int insertPicture(Picture picture) {
		try {
			PictureDao dao = sqlSession.getMapper(PictureDao.class);
			dao.insertPicture(picture);
			// sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return picture.getPicture_id();

	}

	public List<Picture> getPictureList(String publish_time) {
		PictureDao dao = sqlSession.getMapper(PictureDao.class);
		return dao.getPictureList(publish_time);
	}

	public List<Picture> getPictureListByUserID(int publisher_id) {
		PictureDao dao = sqlSession.getMapper(PictureDao.class);
		return dao.getPictureListByUserID(publisher_id);
	}

	public List<Picture> loadMorePictureList(String publish_time) {
		PictureDao dao = sqlSession.getMapper(PictureDao.class);
		return dao.loadMorePictureList(publish_time);
	}
}

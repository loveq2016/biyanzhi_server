package com.biyanzhi.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.biyanzhi.bean.Picture;
import com.biyanzhi.dao.PictureDao;
import com.biyanzhi.factory.MySqlSession;

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
			SqlSession sqlSession = MySqlSession.getSessionFactory()
					.openSession();
			PictureDao dao = sqlSession.getMapper(PictureDao.class);
			dao.insertPicture(picture);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return picture.getPicture_id();

	}

	public List<Picture> getPictureList() {
		SqlSession sqlSession = MySqlSession.getSessionFactory().openSession();
		PictureDao dao = sqlSession.getMapper(PictureDao.class);
		return dao.getPictureList();
	}

	public List<Picture> getPictureListByUserID(int publisher_id) {
		SqlSession sqlSession = MySqlSession.getSessionFactory().openSession();
		PictureDao dao = sqlSession.getMapper(PictureDao.class);
		return dao.getPictureListByUserID(publisher_id);
	}
}

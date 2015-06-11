package com.biyanzhi.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.biyanzhi.bean.PictureImage;
import com.biyanzhi.dao.PictureImageDao;

@Repository
public class PictureImageDaoImpl implements PictureImageDao {
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	@Resource(name = "sqlSession")
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int insertPictureImage(List<PictureImage> imageLists) {
		int id = -1;
		try {
			PictureImageDao dao = sqlSession.getMapper(PictureImageDao.class);
			id = dao.insertPictureImage(imageLists);
			// sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public List<PictureImage> getPictureImageListsByPictureID(int picture_id) {
		PictureImageDao dao = sqlSession.getMapper(PictureImageDao.class);
		return dao.getPictureImageListsByPictureID(picture_id);
	}

}

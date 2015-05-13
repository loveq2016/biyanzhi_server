package com.biyanzhi.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.biyanzhi.bean.Picture;
import com.biyanzhi.dao.PictureDao;
import com.biyanzhi.factory.MySqlSession;

@Repository
public class PictureDaoImlp implements PictureDao {

	public int insertPicture(Picture picture) {
		try {
			SqlSession sqlSession = MySqlSession.getSessionFactory().openSession();
			PictureDao dao = sqlSession.getMapper(PictureDao.class);
			dao.insertPicture(picture);
			sqlSession.commit();
		} catch (Exception e) {
 			e.printStackTrace();
		}
		return picture.getPicture_id();

	}
}

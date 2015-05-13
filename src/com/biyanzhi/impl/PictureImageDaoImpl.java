package com.biyanzhi.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.biyanzhi.bean.PictureImage;
import com.biyanzhi.dao.PictureImageDao;
import com.biyanzhi.factory.MySqlSession;

public class PictureImageDaoImpl implements PictureImageDao {

	public int insertPictureImage(List<PictureImage> imageLists) {
		int id = -1;
		try {
			SqlSession sqlSession = MySqlSession.getSessionFactory()
					.openSession();
			PictureImageDao dao = sqlSession.getMapper(PictureImageDao.class);
			id = dao.insertPictureImage(imageLists);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

}

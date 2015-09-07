package com.biyanzhi.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.biyanzhi.bean.ShuoShuoImage;
import com.biyanzhi.dao.ShuoShuoImageDao;

@Repository
public class ShuoShuoImageDaoImpl implements ShuoShuoImageDao {
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	@Resource(name = "sqlSession")
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int insert(List<ShuoShuoImage> images) {
		ShuoShuoImageDao dao = sqlSession.getMapper(ShuoShuoImageDao.class);
		return dao.insert(images);
	}

	public List<ShuoShuoImage> getImageByShuoShuoID(int shuoshuo_id) {
		ShuoShuoImageDao dao = sqlSession.getMapper(ShuoShuoImageDao.class);
		return dao.getImageByShuoShuoID(shuoshuo_id);
	}

}

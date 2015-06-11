package com.biyanzhi.impl;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.biyanzhi.bean.GuanZhu;
import com.biyanzhi.dao.GuanZhuDao;

@Repository
public class GuanZhuImpl implements GuanZhuDao {
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	@Resource(name = "sqlSession")
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int addGuanZhu(GuanZhu guanzhu) {
		try {

			GuanZhuDao dao = sqlSession.getMapper(GuanZhuDao.class);
			int result = dao.addGuanZhu(guanzhu);
			// sqlSession.commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getGuanZhuCountByUserID(int user_id) {
		try {
			GuanZhuDao dao = sqlSession.getMapper(GuanZhuDao.class);
			return dao.getGuanZhuCountByUserID(user_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}

package com.biyanzhi.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.biyanzhi.bean.GuanZhu;
import com.biyanzhi.dao.GuanZhuDao;
import com.biyanzhi.factory.MySqlSession;

@Repository
public class GuanZhuImpl implements GuanZhuDao {

	public int addGuanZhu(GuanZhu guanzhu) {
		try {
			SqlSession sqlSession = MySqlSession.getSessionFactory()
					.openSession();
			GuanZhuDao dao = sqlSession.getMapper(GuanZhuDao.class);
			int result = dao.addGuanZhu(guanzhu);
			sqlSession.commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<GuanZhu> getGuanZhuCountByUserID(int user_id) {
		try {
			SqlSession sqlSession = MySqlSession.getSessionFactory()
					.openSession();
			GuanZhuDao dao = sqlSession.getMapper(GuanZhuDao.class);
			return dao.getGuanZhuCountByUserID(user_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

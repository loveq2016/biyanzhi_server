package com.biyanzhi.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.biyanzhi.bean.User;
import com.biyanzhi.dao.UserDao;
import com.biyanzhi.factory.MySqlSession;

@Repository
public class UserDaoImpl implements UserDao {

	public int insertUserToDB(User user) {
		SqlSession sqlSession = MySqlSession.getSessionFactory().openSession();
		UserDao dao = sqlSession.getMapper(UserDao.class);
		int result = dao.insertUserToDB(user);
		sqlSession.commit();
		return result;
	}

	public String verifyCellphone(String cellPhone) {
		SqlSession sqlSession = MySqlSession.getSessionFactory().openSession();
		UserDao dao = sqlSession.getMapper(UserDao.class);
		String result = dao.verifyCellphone(cellPhone);
		return result;
	}

	public User findUserByUserCellPhoneAndPassword(String cell_phone,
			String password) {
		SqlSession sqlSession = MySqlSession.getSessionFactory().openSession();
		UserDao dao = sqlSession.getMapper(UserDao.class);
		return dao.findUserByUserCellPhoneAndPassword(cell_phone, password);
	}

	public User findUserByUserID(int user_id) {
		SqlSession sqlSession = MySqlSession.getSessionFactory().openSession();
		UserDao dao = sqlSession.getMapper(UserDao.class);
		return dao.findUserByUserID(user_id);
	}
}

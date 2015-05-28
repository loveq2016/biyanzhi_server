package com.biyanzhi.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.biyanzhi.bean.SMSCode;
import com.biyanzhi.dao.SMSCodeDao;
import com.biyanzhi.factory.MySqlSession;

@Repository
public class SMSCodeDaoImpl implements SMSCodeDao {

	public int insertToDB(SMSCode code) {
		SqlSession sqlSession = MySqlSession.getSessionFactory().openSession();
		SMSCodeDao dao = sqlSession.getMapper(SMSCodeDao.class);
		int result = dao.insertToDB(code);
		sqlSession.commit();
		return result;
	}

	public String findCodeByCellphone(String user_cellphone) {
		SqlSession sqlSession = MySqlSession.getSessionFactory().openSession();
		SMSCodeDao dao = sqlSession.getMapper(SMSCodeDao.class);
		return dao.findCodeByCellphone(user_cellphone);
	}

	public int delCodeByUserCellPhone(String user_cellphone) {
		SqlSession sqlSession = MySqlSession.getSessionFactory().openSession();
		SMSCodeDao dao = sqlSession.getMapper(SMSCodeDao.class);
		int result = dao.delCodeByUserCellPhone(user_cellphone);
		sqlSession.commit();
		return result;
	}

}

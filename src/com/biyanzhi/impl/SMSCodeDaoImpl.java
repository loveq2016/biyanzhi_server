package com.biyanzhi.impl;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.biyanzhi.bean.SMSCode;
import com.biyanzhi.dao.SMSCodeDao;

@Repository
public class SMSCodeDaoImpl implements SMSCodeDao {
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	@Resource(name = "sqlSession")
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int insertToDB(SMSCode code) {
		SMSCodeDao dao = sqlSession.getMapper(SMSCodeDao.class);
		int result = dao.insertToDB(code);
		// sqlSession.commit();
		return result;
	}

	public String findCodeByCellphone(String user_cellphone) {
		SMSCodeDao dao = sqlSession.getMapper(SMSCodeDao.class);
		return dao.findCodeByCellphone(user_cellphone);
	}

	public int delCodeByUserCellPhone(String user_cellphone) {
		SMSCodeDao dao = sqlSession.getMapper(SMSCodeDao.class);
		int result = dao.delCodeByUserCellPhone(user_cellphone);
		// sqlSession.commit();
		return result;
	}

}

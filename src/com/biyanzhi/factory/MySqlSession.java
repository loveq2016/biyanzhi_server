package com.biyanzhi.factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.biyanzhi.bean.PictureImage;
import com.biyanzhi.bean.SMSCode;
import com.biyanzhi.dao.PictureImageDao;
import com.biyanzhi.dao.PictureScoreDao;
import com.biyanzhi.dao.SMSCodeDao;
import com.biyanzhi.dao.UserDao;

public class MySqlSession {
	/**
	 * ���MyBatis SqlSessionFactory
	 * SqlSessionFactory���𴴽�SqlSession��һ�������ɹ����Ϳ�����SqlSessionʵ����ִ��ӳ�����
	 * ��commit��rollback��close�ȷ�����
	 * 
	 * @return
	 */
	public static SqlSessionFactory sessionFactory = null;

	static {
		String resource = "configuration.xml";
		try {
			sessionFactory = new SqlSessionFactoryBuilder().build(Resources
					.getResourceAsReader(resource));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void main(String[] args) {

		SqlSession sqlSession = MySqlSession.getSessionFactory().openSession();
		UserDao dao = sqlSession.getMapper(UserDao.class);
		System.out.println(dao.findUserByUserCellPhoneAndPassword("18560133195", "123456"));

	}
}

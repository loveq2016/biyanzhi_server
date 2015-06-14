package com.biyanzhi.factory;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MySqlSession {
	/**
	 * 获得MyBatis SqlSessionFactory
	 * SqlSessionFactory负责创建SqlSession，一旦创建成功，就可以用SqlSession实例来执行映射语句
	 * ，commit，rollback，close等方法。
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

	}
}

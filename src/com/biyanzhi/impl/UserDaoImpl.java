package com.biyanzhi.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.biyanzhi.bean.User;
import com.biyanzhi.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	@Resource(name = "sqlSession")
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int insertUserToDB(User user) {
		UserDao dao = sqlSession.getMapper(UserDao.class);
		int result = dao.insertUserToDB(user);
		// sqlSession.commit();
		return result;
	}

	public String verifyCellphone(String cellPhone) {
		// SqlSession sqlSession =
		// MySqlSession.getSessionFactory().openSession();
		UserDao dao = sqlSession.getMapper(UserDao.class);
		String result = dao.verifyCellphone(cellPhone);
		return result;
	}

	public User findUserByUserCellPhoneAndPassword(String cell_phone,
			String password) {
		UserDao dao = sqlSession.getMapper(UserDao.class);
		return dao.findUserByUserCellPhoneAndPassword(cell_phone, password);
	}

	public User findUserByUserID(int user_id) {
		UserDao dao = sqlSession.getMapper(UserDao.class);
		return dao.findUserByUserID(user_id);
	}

	public int upDateUserAvatar(String user_avatar, int user_id) {
		UserDao dao = sqlSession.getMapper(UserDao.class);
		return dao.upDateUserAvatar(user_avatar, user_id);
	}

	public int upDateUserName(String user_name, int user_id) {
		UserDao dao = sqlSession.getMapper(UserDao.class);
		return dao.upDateUserName(user_name, user_id);
	}

	public int upDateUserAddress(String user_address, int user_id) {
		UserDao dao = sqlSession.getMapper(UserDao.class);
		return dao.upDateUserAddress(user_address, user_id);
	}

	public List<User> getGuanZhuUsersByUserID(int guanzhu_user_id) {
		UserDao dao = sqlSession.getMapper(UserDao.class);
		return dao.getGuanZhuUsersByUserID(guanzhu_user_id);
	}

	public int changeUserPassword(String cell_phone, String password) {
		UserDao dao = sqlSession.getMapper(UserDao.class);
		return dao.changeUserPassword(cell_phone, password);
	}

	public String getUserChatIDByPictureID(int picture_id,
			int picture_publisher_id) {
		UserDao dao = sqlSession.getMapper(UserDao.class);
		return dao.getUserChatIDByPictureID(picture_id, picture_publisher_id);
	}

	public String getUserChatIDByCommentID(int comment_id) {
		UserDao dao = sqlSession.getMapper(UserDao.class);
		return dao.getUserChatIDByCommentID(comment_id);
	}
}

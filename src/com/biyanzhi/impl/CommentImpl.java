package com.biyanzhi.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.biyanzhi.bean.Comment;
import com.biyanzhi.dao.CommentDao;
import com.biyanzhi.factory.MySqlSession;

@Repository
public class CommentImpl implements CommentDao {

	public int insertComment(Comment comment) {
		try {
			SqlSession sqlSession = MySqlSession.getSessionFactory()
					.openSession();
			CommentDao dao = sqlSession.getMapper(CommentDao.class);
			dao.insertComment(comment);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comment.getComment_id();
	}

	public List<Comment> getCommentByPictureID(int picture_id) {
		return null;
	}

	public boolean deleteCommentByID(int comment_id) {
		return false;
	}

}

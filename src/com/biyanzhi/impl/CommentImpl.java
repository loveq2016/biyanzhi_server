package com.biyanzhi.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.biyanzhi.bean.Comment;
import com.biyanzhi.dao.CommentDao;

@Repository
public class CommentImpl implements CommentDao {
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	@Resource(name = "sqlSession")
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int insertComment(Comment comment) {
		try {
			CommentDao dao = sqlSession.getMapper(CommentDao.class);
			dao.insertComment(comment);
			// sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comment.getComment_id();
	}

	public List<Comment> getCommentByPictureID(int picture_id) {
		try {
			CommentDao dao = sqlSession.getMapper(CommentDao.class);
			return dao.getCommentByPictureID(picture_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean deleteCommentByID(int comment_id) {
		return false;
	}

}

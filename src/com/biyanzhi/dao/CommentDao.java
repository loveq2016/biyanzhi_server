package com.biyanzhi.dao;

import java.util.List;

import com.biyanzhi.bean.Comment;

public interface CommentDao {
	int insertComment(Comment comment);// ·µ»Øid

	List<Comment> getCommentByPictureID(int picture_id);

	boolean deleteCommentByID(int comment_id);
}

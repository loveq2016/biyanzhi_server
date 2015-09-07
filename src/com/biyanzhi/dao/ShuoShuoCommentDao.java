package com.biyanzhi.dao;

import java.util.List;

import com.biyanzhi.bean.ShuoShuoComment;

public interface ShuoShuoCommentDao {
	int insertComment(ShuoShuoComment comment);// ·µ»Øid

	List<ShuoShuoComment> getCommentByShuoShuoID(int shuoshuo_id);
}

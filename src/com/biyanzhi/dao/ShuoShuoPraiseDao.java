package com.biyanzhi.dao;

import java.util.List;

import com.biyanzhi.bean.ShuoShuoPraise;

public interface ShuoShuoPraiseDao {
	int insert(ShuoShuoPraise praise);

	List<ShuoShuoPraise> getPariseByShuoShuoID(int shuoshuo_id);
}

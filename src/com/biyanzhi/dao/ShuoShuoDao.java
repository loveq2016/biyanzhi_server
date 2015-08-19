package com.biyanzhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biyanzhi.bean.ShuoShuo;

public interface ShuoShuoDao {
	int insert(ShuoShuo shuoshuo);

	List<ShuoShuo> getShuoShuoList(@Param("page") int page);
}

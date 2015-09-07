package com.biyanzhi.dao;

import java.util.List;

import com.biyanzhi.bean.ShuoShuoImage;

public interface ShuoShuoImageDao {
	int insert(List<ShuoShuoImage> images);

	List<ShuoShuoImage> getImageByShuoShuoID(int shuoshuo_id);
}

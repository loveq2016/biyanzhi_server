package com.biyanzhi.dao;

import java.util.List;

import com.biyanzhi.bean.Picture;

public interface PictureDao {
	int insertPicture(Picture picture);

	List<Picture> getPictureList();
}

package com.biyanzhi.dao;

import java.util.List;

import com.biyanzhi.bean.PictureImage;

public interface PictureImageDao {
	int insertPictureImage(List<PictureImage> imageLists);

	List<PictureImage> getPictureImageListsByPictureID(int picture_id);
}

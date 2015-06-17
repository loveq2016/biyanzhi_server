package com.biyanzhi.dao;

import java.util.List;

import com.biyanzhi.bean.Picture;

public interface PictureDao {
	int insertPicture(Picture picture);

	List<Picture> getPictureList(String publish_time);

	List<Picture> loadMorePictureList(String publish_time);// 上拉加载更多

	List<Picture> getPictureListByUserID(int publisher_id);

	List<Picture> getGirlBangPictureList();// 美女榜

	List<Picture> getBoyBangPictureList();// 帅哥榜

}

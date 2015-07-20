package com.biyanzhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biyanzhi.bean.Picture;

public interface PictureDao {
	int insertPicture(Picture picture);

	Picture getPictureByPictureID(int picture_id);

	List<Picture> getPictureList(String publish_time);

	List<Picture> loadMorePictureList(String publish_time);// 上拉加载更多

	List<Picture> getPictureListByUserID(int publisher_id);

	List<Picture> getGirlBangPictureList();// 美女榜

	List<Picture> getBoyBangPictureList();// 帅哥榜

	int updatePictureUpdateTime(@Param("picture_id") int picture_id,
			@Param("publish_time_last_update") String publish_time_last_update);

	int delPicture(int picture_id);

}

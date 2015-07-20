package com.biyanzhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biyanzhi.bean.Picture;

public interface PictureDao {
	int insertPicture(Picture picture);

	Picture getPictureByPictureID(int picture_id);

	List<Picture> getPictureList(String publish_time);

	List<Picture> loadMorePictureList(String publish_time);// �������ظ���

	List<Picture> getPictureListByUserID(int publisher_id);

	List<Picture> getGirlBangPictureList();// ��Ů��

	List<Picture> getBoyBangPictureList();// ˧���

	int updatePictureUpdateTime(@Param("picture_id") int picture_id,
			@Param("publish_time_last_update") String publish_time_last_update);

	int delPicture(int picture_id);

}

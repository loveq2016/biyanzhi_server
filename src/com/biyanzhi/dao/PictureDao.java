package com.biyanzhi.dao;

import java.util.List;

import com.biyanzhi.bean.Picture;

public interface PictureDao {
	int insertPicture(Picture picture);

	Picture getPictureByPictureID(int picture_id);

	List<Picture> getPictureList(String publish_time);

	List<Picture> loadMorePictureList(String publish_time);// �������ظ���

	List<Picture> getPictureListByUserID(int publisher_id);

	List<Picture> getGirlBangPictureList();// ��Ů��

	List<Picture> getBoyBangPictureList();// ˧���

}

package com.biyanzhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biyanzhi.bean.User;

public interface UserDao {
	int insertUserToDB(User user);// �����ݿ������һ���û�����

	String verifyCellphone(String cellPhone);// ��֤�ֻ����Ƿ����

	User findUserByUserCellPhoneAndPassword(
			@Param("user_cellphone") String cell_phone,
			@Param("user_password") String password);// Mybatis ���������ѯ

	User findUserByUserID(int user_id);

	int upDateUserAvatar(@Param("user_avatar") String user_avatar,
			@Param("user_id") int user_id);

	int upDateUserName(@Param("user_name") String user_name,
			@Param("user_id") int user_id);

	int upDateUserAddress(@Param("user_address") String user_address,
			@Param("user_id") int user_id);

	List<User> getGuanZhuUsersByUserID(int user_id);// ��ȡ��ע�û��б�
}

package com.biyanzhi.dao;

import org.apache.ibatis.annotations.Param;

import com.biyanzhi.bean.User;

public interface UserDao {
	int insertUserToDB(User user);// �����ݿ������һ���û�����

	String verifyCellphone(String cellPhone);// ��֤�ֻ����Ƿ����

	User findUserByUserCellPhoneAndPassword(
			@Param("user_cellphone") String cell_phone,
			@Param("user_password") String password);// Mybatis ���������ѯ

	User findUserByUserID(int user_id);
}

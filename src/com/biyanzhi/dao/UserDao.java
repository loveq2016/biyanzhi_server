package com.biyanzhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biyanzhi.bean.User;

public interface UserDao {
	int insertUserToDB(User user);// 向数据库里插入一条用户数据

	String verifyCellphone(String cellPhone);// 验证手机号是否存在

	User findUserByUserCellPhoneAndPassword(
			@Param("user_cellphone") String cell_phone,
			@Param("user_password") String password);// Mybatis 多个参数查询

	User findUserByUserID(int user_id);

	int upDateUserAvatar(@Param("user_avatar") String user_avatar,
			@Param("user_id") int user_id);

	int upDateUserName(@Param("user_name") String user_name,
			@Param("user_id") int user_id);

	int upDateUserAddress(@Param("user_address") String user_address,
			@Param("user_id") int user_id);

	List<User> getGuanZhuUsersByUserID(int user_id);// 获取关注用户列表
}

package com.biyanzhi.dao;

import org.apache.ibatis.annotations.Param;

import com.biyanzhi.bean.User;

public interface UserDao {
	int insertUserToDB(User user);// 向数据库里插入一条用户数据

	String verifyCellphone(String cellPhone);// 验证手机号是否存在

	User findUserByUserCellPhoneAndPassword(
			@Param("user_cellphone") String cell_phone,
			@Param("user_password") String password);// Mybatis 多个参数查询
}

package com.biyanzhi.dao;

import com.biyanzhi.bean.User;

public interface UserDao {
	int insertUserToDB(User user);// 向数据库里插入一条用户数据

	String verifyCellphone(String cellPhone);// 验证手机号是否存在

}

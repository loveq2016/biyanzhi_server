package com.biyanzhi.dao;

import com.biyanzhi.bean.User;

public interface UserDao {
	int insertUserToDB(User user);// �����ݿ������һ���û�����

	String verifyCellphone(String cellPhone);// ��֤�ֻ����Ƿ����

}

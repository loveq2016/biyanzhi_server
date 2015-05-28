package com.biyanzhi.dao;

import com.biyanzhi.bean.SMSCode;

public interface SMSCodeDao {
	int insertToDB(SMSCode code);

	String findCodeByCellphone(String user_cellphone);

	int delCodeByUserCellPhone(String user_cellphone);// É¾³ýÑéÖ¤Âë
}

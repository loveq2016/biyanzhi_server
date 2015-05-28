package com.biyanzhi.util;

import java.util.Date;
import java.util.Random;

public class Utils {

	/**
	 * 生成验证码
	 * 
	 * @return
	 */
	public static String getSMSCode() {
		Date d = new Date();
		long lseed = d.getTime();
		Random r = new Random(lseed); // 设置随机种子
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < 6; i++) {
			str.append(r.nextInt(9)); // 生成随机数字
		}
		return str.toString();
	}
}

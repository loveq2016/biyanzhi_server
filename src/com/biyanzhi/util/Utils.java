package com.biyanzhi.util;

import java.util.Date;
import java.util.Random;

public class Utils {

	/**
	 * ������֤��
	 * 
	 * @return
	 */
	public static String getSMSCode() {
		Date d = new Date();
		long lseed = d.getTime();
		Random r = new Random(lseed); // �����������
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < 6; i++) {
			str.append(r.nextInt(9)); // �����������
		}
		return str.toString();
	}
}

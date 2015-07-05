package com.biyanzhi.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 */
public class DateUtils {

	private static DateFormat fileNameDF = new SimpleDateFormat(
			"yyyy-MM-dd-HH-mm-ss");// �����ļ��ϴ��ļ���

	public static String getUpLoadFileName() {
		return fileNameDF.format(new Date());

	}
 
	public static String getPicturePublishTime() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}
}
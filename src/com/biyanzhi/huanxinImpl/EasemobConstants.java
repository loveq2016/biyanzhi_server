package com.biyanzhi.huanxinImpl;

/**
 * Constants
 * 
 * @author Lynch 2014-09-15
 * 
 */
public interface EasemobConstants {
	public static final String USER_NAME = "binbin";
	public static final String PASSWORD = "binbin521";
	public static final String APP_KEY = "774663576#biyanzhi";
	public static final String HOST = "a1.easemob.com";

	public static final String JOIN_CIRCLE_USER_ID = "joincircle";
	public static final String RECEIVE_JOIN_CIRCLE_USER_ID = "receivejoincircle";
	public static final String REFUSE_JON_CIRCLE_USER_ID = "refusejoincircle";
	public static final String DISSOLVE_CIRCLE_USER_ID = "dissolvecircle";
	public static final String PRAISE_USER_ID = "praise";
	public static final String COMMENT_USER_ID = "comment";
	public static final String KICK_OUT_USER_ID = "kickout";
	public static final String JOIN_NOTIFY_USER_ID = "joinnotify";
	// API_HTTP_SCHEMA
	public static String API_HTTP_SCHEMA = "https";
	// API_SERVER_HOST
	public static String API_SERVER_HOST = PropertiesUtils.getProperties()
			.getProperty("API_SERVER_HOST");
	// APPKEY
	public static String APPKEY = PropertiesUtils.getProperties().getProperty(
			"APPKEY");
	// APP_CLIENT_ID
	public static String APP_CLIENT_ID = PropertiesUtils.getProperties()
			.getProperty("APP_CLIENT_ID");
	// APP_CLIENT_SECRET
	public static String APP_CLIENT_SECRET = PropertiesUtils.getProperties()
			.getProperty("APP_CLIENT_SECRET");
	// ORG_ADMIN_USERNAME
	public static String ORG_ADMIN_USERNAME = PropertiesUtils.getProperties()
			.getProperty("ORG_ADMIN_USERNAME");
	// ORG_ADMIN_PASSWORD
	public static String ORG_ADMIN_PASSWORD = PropertiesUtils.getProperties()
			.getProperty("ORG_ADMIN_PASSWORD");
	// DEFAULT_PASSWORD
	public static String DEFAULT_PASSWORD = "1234456";
}

package com.biyanzhi.huanxinImpl;

public class EasemobConstans {
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
	public static final String PLAY_SCORE_USER_ID = "playscore";

	public static final String KICK_OUT_USER_ID = "kickout";
	public static final String ADD_USER_FRIEND_INVITE = "adduserfriendinvite";
	public static final String XINQING_PRAISE_AND_COMMENT_USER_ID = "xinqing_praise_and_comment";

	// API_SERVER_HOST
	public static String API_SERVER_HOST = PropertiesUtils.getProperties()
			.getProperty("API_SERVER_HOST");
	// API_HTTP_SCHEMA
	public static String API_HTTP_SCHEMA = "https";
	// APPKEY
	public static String APPKEY = PropertiesUtils.getProperties().getProperty(
			"APPKEY");
	// APP_CLIENT_ID
	public static String APP_CLIENT_ID = PropertiesUtils.getProperties()
			.getProperty("APP_CLIENT_ID");
	// APP_CLIENT_SECRET
	public static String APP_CLIENT_SECRET = PropertiesUtils.getProperties()
			.getProperty("APP_CLIENT_SECRET");
}

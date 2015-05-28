/**
 * @author Glan.duanyj
 * @date 2014-05-12
 * @project rest_demo
 */
package com.biyanzhi.smscode;

public class RestSMSCode {
	public static final String ACCOUNTSID = "cea526c74f8f77af93613464d94da1e7";
	public static final String TOKEN = "390b46431da96b0a0452f936701fc054";
	// public static final String APPID = "f500ab6d729a490a9feae574a7c7a58c";
	public static final String APPID = "b19dff0d1e3e42f485c7eba2b007937b";
	public static final String TEMPLATEID = "7281";

	static AbsRestClient InstantiationRestAPI(boolean enable) {
		return new JsonReqClient();

	}

	public static void testTemplateSMS(boolean json, String accountSid,
			String authToken, String appId, String templateId, String to,
			String param) {
		try {
			String result = InstantiationRestAPI(json).templateSMS(accountSid,
					authToken, appId, templateId, to, param);
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendCode(String code, String cellphone) {
		testTemplateSMS(true, ACCOUNTSID, TOKEN, APPID, TEMPLATEID, cellphone,
				code);

	}

}

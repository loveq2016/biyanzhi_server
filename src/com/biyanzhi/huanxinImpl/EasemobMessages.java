package com.biyanzhi.huanxinImpl;

import java.io.File;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biyanzhi.util.Constants;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * REST API Demo: 发送消息 REST API HttpClient4.3实现
 * 
 * Doc URL: http://www.easemob.com/docs/rest/sendmessage/
 * 
 * @author Lynch 2014-09-15
 * 
 */
public class EasemobMessages {

	private static Logger LOGGER = LoggerFactory
			.getLogger(EasemobMessages.class);
	private static final String APPKEY = EasemobConstants.APPKEY;
	private static JsonNodeFactory factory = new JsonNodeFactory(false);

	// 通过app的client_id和client_secret来获取app管理员token
	private static Credential credential = new ClientSecretCredential(
			EasemobConstants.APP_CLIENT_ID, EasemobConstants.APP_CLIENT_SECRET,
			Roles.USER_ROLE_APPADMIN);

	public static void main(String[] args) {
		// 检测用户是否在线
		String targetuserPrimaryKey = "8f849c995d0e27a546971bd6c0fe6e98";
		ObjectNode usernode = getUserStatus(targetuserPrimaryKey);
		if (null != usernode) {
			LOGGER.info("检测用户是否在线: " + usernode.toString());
		}

		// 给用户发一条文本消息
		String from = "8f849c995d0e27a546971bd6c0fe6e98";
		String targetTypeus = "users";
		ObjectNode ext = factory.objectNode();
		ArrayNode targetusers = factory.arrayNode();
		targetusers.add("kenshinnuser001");
		targetusers.add("kenshinnuser002");
		ObjectNode txtmsg = factory.objectNode();
		txtmsg.put("msg", "Hello Easemob!");
		txtmsg.put("type", "txt");
		ObjectNode sendTxtMessageusernode = sendMessages(targetTypeus,
				targetusers, txtmsg, from, ext);
		if (null != sendTxtMessageusernode) {
			LOGGER.info("给用户发一条文本消息: " + sendTxtMessageusernode.toString());
		}
		// 给一个群组发文本消息
		String targetTypegr = "chatgroups";
		ArrayNode chatgroupidsNode = (ArrayNode) EasemobChatGroups
				.getAllChatgroupids().path("data");
		ArrayNode targetgroup = factory.arrayNode();
		targetgroup.add(chatgroupidsNode.get(0).path("groupid").asText());
		ObjectNode sendTxtMessagegroupnode = sendMessages(targetTypegr,
				targetgroup, txtmsg, from, ext);
		if (null != sendTxtMessagegroupnode) {
			LOGGER.info("给一个群组发文本消息: " + sendTxtMessagegroupnode.toString());
		}

		// 给用户发一条图片消息
		File uploadImgFile = new File("/home/lynch/Pictures/24849.jpg");
		ObjectNode imgDataNode = EasemobFiles.mediaUpload(uploadImgFile);
		String imgFileUUID = imgDataNode.path("entities").get(0).path("uuid")
				.asText();
		String shareSecret = imgDataNode.path("entities").get(0)
				.path("share-secret").asText();
		if (null != imgDataNode) {
			LOGGER.info("上传图片文件: " + imgDataNode.toString());
		}
		ObjectNode imgmsg = factory.objectNode();
		imgmsg.put("type", "img");
		imgmsg.put(
				"url",
				HTTPClientUtils.getURL(
						EasemobConstants.APPKEY.replace("#", "/")
								+ "/chatfiles/" + imgFileUUID).toString());
		imgmsg.put("filename", "24849.jpg");
		imgmsg.put("length", 10);
		imgmsg.put("secret", shareSecret);
		ObjectNode sendimgMessageusernode = sendMessages(targetTypeus,
				targetusers, imgmsg, from, ext);
		if (null != sendimgMessageusernode) {
			LOGGER.info("给一个群组发文本消息: " + sendimgMessageusernode.toString());
		}
		// 给一个群组发图片消息
		ObjectNode sendimgMessagegroupnode = sendMessages(targetTypegr,
				targetgroup, imgmsg, from, ext);
		if (null != sendimgMessagegroupnode) {
			LOGGER.info("给一个群组发文本消息: " + sendimgMessagegroupnode.toString());
		}

		// 给用户发一条语音消息
		File uploadAudioFile = new File("/home/lynch/Music/music.MP3");
		ObjectNode audioDataNode = EasemobFiles.mediaUpload(uploadAudioFile);
		String audioFileUUID = audioDataNode.path("entities").get(0)
				.path("uuid").asText();
		String audioFileShareSecret = audioDataNode.path("entities").get(0)
				.path("share-secret").asText();
		if (null != audioDataNode) {
			LOGGER.info("上传语音文件: " + audioDataNode.toString());
		}
		ObjectNode audiomsg = factory.objectNode();
		audiomsg.put("type", "audio");
		audiomsg.put(
				"url",
				HTTPClientUtils.getURL(
						EasemobConstants.APPKEY.replace("#", "/")
								+ "/chatfiles/" + audioFileUUID).toString());
		audiomsg.put("filename", "music.MP3");
		audiomsg.put("length", 10);
		audiomsg.put("secret", audioFileShareSecret);
		ObjectNode sendaudioMessageusernode = sendMessages(targetTypeus,
				targetusers, audiomsg, from, ext);
		if (null != sendaudioMessageusernode) {
			LOGGER.info("给用户发一条语音消息: " + sendaudioMessageusernode.toString());
		}

		// 给一个群组发语音消息
		ObjectNode sendaudioMessagegroupnode = sendMessages(targetTypegr,
				targetgroup, audiomsg, from, ext);
		if (null != sendaudioMessagegroupnode) {
			LOGGER.info("给一个群组发语音消息: " + sendaudioMessagegroupnode.toString());
		}

		// 给用户发一条透传消息
		ObjectNode cmdmsg = factory.objectNode();
		cmdmsg.put("action", "gogogo");
		cmdmsg.put("type", "cmd");
		ObjectNode sendcmdMessageusernode = sendMessages(targetTypeus,
				targetusers, cmdmsg, from, ext);
		if (null != sendcmdMessageusernode) {
			LOGGER.info("给用户发一条透传消息: " + sendcmdMessageusernode.toString());
		}
	}

	/**
	 * 检测用户是否在线
	 * 
	 * @param username
	 * 
	 * @return
	 */
	public static ObjectNode getUserStatus(String username) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!HTTPClientUtils
				.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}

		// check properties that must be provided
		if (StringUtils.isEmpty(username)) {
			LOGGER.error("You must provided a targetUserPrimaryKey .");
			objectNode.put("message",
					"You must provided a targetUserPrimaryKey .");
			return objectNode;
		}

		try {
			URL userStatusUrl = HTTPClientUtils.getURL(EasemobConstants.APPKEY
					.replace("#", "/") + "/users/" + username + "/status");
			objectNode = HTTPClientUtils.sendHTTPRequest(userStatusUrl,
					credential, null, HTTPMethod.METHOD_GET);
			String userStatus = objectNode.get("data").path(username).asText();
			if ("online".equals(userStatus)) {
				LOGGER.error(String.format(
						"The status of user[%s] is : [%s] .", username,
						userStatus));
			} else if ("offline".equals(userStatus)) {
				LOGGER.error(String.format(
						"The status of user[%s] is : [%s] .", username,
						userStatus));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public static void sendGroupMessage(String group_id, String from_user,
			String content, ObjectNode ext) {
		String targetTypeus = "chatgroups";
		ObjectNode txtmsg = factory.objectNode();
		txtmsg.put("msg", content);
		txtmsg.put("type", "txt");
		ArrayNode targetgroup = factory.arrayNode();
		targetgroup.add(group_id);
		ObjectNode sendTxtMessageusernode = sendMessages(targetTypeus,
				targetgroup, txtmsg, from_user, ext);
		System.out.println("group_message::"
				+ sendTxtMessageusernode.toString());
	}

	public static void sendNotifyForDongTai(String group_id, String publisher_id) {
		ObjectNode ext = factory.objectNode();
		ext.put("publisher_id", publisher_id);
		ext.put("user_name", "趣友");
		ext.put("user_avatar", Constants.APP_AVATAR);
		ext.put("user_id", -1);
		sendGroupMessage(group_id, "growth", "有人更新了动态,快去看看吧", ext);
	}

	/**
	 * 加入圈子通知
	 * 
	 * @param group_id
	 * @param user_id
	 * @param user_name
	 */
	public static void sendNotifyForSomeOneJoinCircle(String group_id,
			int user_id, String user_name) {
		ObjectNode ext = factory.objectNode();
		ext.put("join_circle_user_id", user_id + "");
		ext.put("user_name", "趣友");
		ext.put("user_avatar", Constants.APP_AVATAR);
		ext.put("user_id", -1);
		sendGroupMessage(group_id, "joinnotify", "欢迎 [ " + user_name
				+ " ] 加入圈子,点击查看TA的资料吧", ext);
	}

	/**
	 * 解散圈子提醒
	 * 
	 * 
	 */
	public static void sendTextMessageForDissolve(String group_id,
			String message_content, String circle_id, int user_id) {
		ObjectNode ext = factory.objectNode();
		ext.put("user_name", "趣友");
		ext.put("user_avatar", Constants.APP_AVATAR);
		ext.put("circle_id", circle_id);
		ext.put("user_id", user_id + "");
		sendGroupMessage(group_id, EasemobConstans.DISSOLVE_CIRCLE_USER_ID,
				message_content, ext);
	}

	/**
	 * 踢出圈子提醒
	 * 
	 * @param textContent
	 * @param to_user_id
	 */
	public static void sendMessageForKickOutCircle(String textContent,
			String to_user_id, int circle_id, int kickout_user_id) {
		ObjectNode ext = factory.objectNode();
		ext.put("user_name", "趣友");
		ext.put("user_avatar", Constants.APP_AVATAR);
		ext.put("circle_id", circle_id + "");
		ext.put("kickout_user_id", kickout_user_id + "");
		sendUserMessage(to_user_id, EasemobConstans.KICK_OUT_USER_ID,
				textContent, ext);
	}

	public static void sendTextMessageForpXinQingRraiseAndComment(
			int xinqing_id, String to_user_id, String message_content) {
		ObjectNode ext = factory.objectNode();
		ext.put("user_name", "趣友");
		ext.put("user_avatar", Constants.APP_AVATAR);
		ext.put("xinqing_id", xinqing_id + "");
		sendUserMessage(to_user_id,
				EasemobConstans.XINQING_PRAISE_AND_COMMENT_USER_ID,
				message_content, ext);
	}

	/**
	 * 评论 提醒
	 * 
	 * 
	 */
	public static void sendTextMessageForComment(int picture_id,
			String to_user_chat_id, String message_content) {
		ObjectNode ext = factory.objectNode();
		ext.put("from_user_name", "比颜值");
		ext.put("from_user_avatar", Constants.APP_AVATAR);
		ext.put("picture_id", picture_id);
		ext.put("user_id", -1);
		sendUserMessage(to_user_chat_id, EasemobConstans.COMMENT_USER_ID,
				message_content, ext);
	}

	/**
	 * 拒绝加入圈子通知
	 * 
	 * @param textContent
	 * @param to_user_id
	 */
	public static void sendMessageForFefuseJoinCircle(String textContent,
			String to_user_id) {
		ObjectNode ext = factory.objectNode();
		ext.put("user_name", "趣友");
		ext.put("user_avatar", Constants.APP_AVATAR);
		sendUserMessage(to_user_id, EasemobConstans.REFUSE_JON_CIRCLE_USER_ID,
				textContent, ext);
	}

	/**
	 * 同意加入圈子通知
	 * 
	 * @param textContent
	 * @param to_user_id
	 */
	public static void sendMessageForReceiveJoinCircle(String textContent,
			String to_user_id) {
		ObjectNode ext = factory.objectNode();
		ext.put("user_name", "趣友");
		ext.put("user_avatar", Constants.APP_AVATAR);
		sendUserMessage(to_user_id,
				EasemobConstans.RECEIVE_JOIN_CIRCLE_USER_ID, textContent, ext);
	}

	/**
	 * 发送验证信息
	 * 
	 * @param to_user_chat_id
	 * @param reason
	 * @param user_friend_id
	 * @param user_friend_name
	 * @param user_firend_avatar
	 * @param from_circle
	 */
	public static void addUserFriendInvite(String to_user_chat_id,
			String reason, int user_friend_id, String user_friend_name,
			String user_firend_avatar, String from_circle,
			String user_friend_chat_id) {
		ObjectNode ext = factory.objectNode();
		ext.put("user_name", "趣友");
		ext.put("user_avatar", Constants.APP_AVATAR);
		ext.put("user_friend_id", user_friend_id);
		ext.put("user_friend_chat_id", user_friend_chat_id);
		ext.put("user_friend_name", user_friend_name);
		ext.put("user_firend_avatar", user_firend_avatar);
		ext.put("from_circle", from_circle);
		ext.put("reason", reason);

		sendUserMessage(to_user_chat_id,
				EasemobConstans.ADD_USER_FRIEND_INVITE, "'" + user_friend_name
						+ "'请求加你为好友", ext);
	}

	/**
	 * 像用户发送消息
	 * 
	 * @param targetuser
	 * @param from_user
	 * @param content
	 * @param ext
	 */
	public static void sendUserMessage(String targetuser, String from_user,
			String content, ObjectNode ext) {
		String from = from_user;
		String targetTypeus = "users";
		ArrayNode targetusers = factory.arrayNode();
		targetusers.add(targetuser);
		ObjectNode txtmsg = factory.objectNode();
		txtmsg.put("msg", content);
		txtmsg.put("type", "txt");
		ObjectNode sendTxtMessageusernode = sendMessages(targetTypeus,
				targetusers, txtmsg, from, ext);
		System.out.println("sendMessage:" + sendTxtMessageusernode.toString());
	}

	/**
	 * 发送消息
	 * 
	 * @param targetType
	 *            消息投递者类型：users 用户, chatgroups 群组
	 * @param target
	 *            接收者ID 必须是数组,数组元素为用户ID或者群组ID
	 * @param msg
	 *            消息内容
	 * @param from
	 *            发送者
	 * @param ext
	 *            扩展字段
	 * 
	 * @return 请求响应
	 */
	public static ObjectNode sendMessages(String targetType, ArrayNode target,
			ObjectNode msg, String from, ObjectNode ext) {

		ObjectNode objectNode = factory.objectNode();

		ObjectNode dataNode = factory.objectNode();

		// check appKey format
		if (!HTTPClientUtils
				.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		// check properties that must be provided
		if (!("users".equals(targetType) || "chatgroups".equals(targetType))) {
			LOGGER.error("TargetType must be users or chatgroups .");

			objectNode.put("message",
					"TargetType must be users or chatgroups .");

			return objectNode;
		}

		try {
			// 构造消息体
			dataNode.put("target_type", targetType);
			dataNode.put("target", target);
			dataNode.put("msg", msg);
			dataNode.put("from", from);
			dataNode.put("ext", ext);

			objectNode = HTTPClientUtils.sendHTTPRequest(
					EndPoints.MESSAGES_URL, credential, dataNode,
					HTTPMethod.METHOD_POST);

			objectNode = (ObjectNode) objectNode.get("data");
			for (int i = 0; i < target.size(); i++) {
				String resultStr = objectNode.path(target.path(i).asText())
						.asText();
				if ("success".equals(resultStr)) {
					LOGGER.error(String.format(
							"Message has been send to user[%s] successfully .",
							target.path(i).asText()));
				} else if (!"success".equals(resultStr)) {
					LOGGER.error(String.format(
							"Message has been send to user[%s] failed .",
							target.path(i).asText()));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

}

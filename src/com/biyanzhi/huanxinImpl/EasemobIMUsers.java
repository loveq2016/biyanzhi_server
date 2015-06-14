package com.biyanzhi.huanxinImpl;

import java.net.URL;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * REST API Demo :用户体系集成 REST API HttpClient4.3实现
 * 
 * Doc URL: http://www.easemob.com/docs/rest/userapi
 * 
 * @author Lynch 2014-09-15
 * 
 */
public class EasemobIMUsers {

	private static Logger LOGGER = LoggerFactory
			.getLogger(EasemobIMUsers.class);
	private static JsonNodeFactory factory = new JsonNodeFactory(false);

	// 通过app的client_id和client_secret来获取app管理员token
	private static Credential credential = new ClientSecretCredential(
			EasemobConstants.APP_CLIENT_ID, EasemobConstants.APP_CLIENT_SECRET,
			Roles.USER_ROLE_APPADMIN);

	public static void main(String[] args) {
		createNewUser("song010101011111111111111111", "123");
	}

	public static boolean createNewUser(String username, String password) {
		ObjectNode datanode = JsonNodeFactory.instance.objectNode();
		datanode.put("username", username);
		datanode.put("password", password);
		ObjectNode createNewIMUserSingleNode = createNewIMUserSingle(datanode);
		JSONObject json = JSONObject.fromObject(createNewIMUserSingleNode
				.toString());
		return json.getInt("statusCode") == 200;
	}

	/**
	 * 注册IM用户[单个]
	 * 
	 * 给指定Constants.APPKEY创建�?��新的用户
	 * 
	 * @param dataNode
	 * @return
	 */
	public static ObjectNode createNewIMUserSingle(ObjectNode dataNode) {

		ObjectNode objectNode = factory.objectNode();

		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+",
				EasemobConstants.APPKEY)) {
			LOGGER.error("Bad format of Constants.APPKEY: "
					+ EasemobConstants.APPKEY);

			objectNode.put("message", "Bad format of Constants.APPKEY");

			return objectNode;
		}

		objectNode.removeAll();

		// check properties that must be provided
		if (null != dataNode && !dataNode.has("username")) {
			LOGGER.error("Property that named username must be provided .");

			objectNode.put("message",
					"Property that named username must be provided .");

			return objectNode;
		}
		if (null != dataNode && !dataNode.has("password")) {
			LOGGER.error("Property that named password must be provided .");

			objectNode.put("message",
					"Property that named password must be provided .");

			return objectNode;
		}

		try {

			objectNode = HTTPClientUtils.sendHTTPRequest(EndPoints.USERS_URL,
					credential, dataNode, HTTPMethod.METHOD_POST);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 注册IM用户[批量]
	 * 
	 * 给指定Constants.APPKEY创建�?��用户
	 * 
	 * @param dataArrayNode
	 * @return
	 */
	public static ObjectNode createNewIMUserBatch(ArrayNode dataArrayNode) {

		ObjectNode objectNode = factory.objectNode();

		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+",
				EasemobConstants.APPKEY)) {
			LOGGER.error("Bad format of Constants.APPKEY: "
					+ EasemobConstants.APPKEY);

			objectNode.put("message", "Bad format of Constants.APPKEY");

			return objectNode;
		}

		// check properties that must be provided
		if (dataArrayNode.isArray()) {
			for (JsonNode jsonNode : dataArrayNode) {
				if (null != jsonNode && !jsonNode.has("username")) {
					LOGGER.error("Property that named username must be provided .");

					objectNode.put("message",
							"Property that named username must be provided .");

					return objectNode;
				}
				if (null != jsonNode && !jsonNode.has("password")) {
					LOGGER.error("Property that named password must be provided .");

					objectNode.put("message",
							"Property that named password must be provided .");

					return objectNode;
				}
			}
		}

		try {

			objectNode = HTTPClientUtils.sendHTTPRequest(EndPoints.USERS_URL,
					credential, dataArrayNode, HTTPMethod.METHOD_POST);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 注册IM用户[批量生成用户然后注册]
	 * 
	 * 给指定Constants.APPKEY创建�?��用户
	 * 
	 * @param usernamePrefix
	 *            生成用户名的前缀
	 * @param perNumber
	 *            批量注册时一次注册的数量
	 * @param totalNumber
	 *            生成用户注册的用户�?�?
	 * @return
	 */
	public static ObjectNode createNewIMUserBatchGen(String usernamePrefix,
			Long perNumber, Long totalNumber) {
		ObjectNode objectNode = factory.objectNode();

		if (totalNumber == 0 || perNumber == 0) {
			return objectNode;
		}

		ArrayNode genericArrayNode = EasemobIMUsers.genericArrayNode(
				usernamePrefix, totalNumber);
		if (totalNumber <= perNumber) {
			objectNode = EasemobIMUsers.createNewIMUserBatch(genericArrayNode);
		} else {

			for (int i = 0; i < genericArrayNode.size(); i++) {
				ArrayNode tmpArrayNode = factory.arrayNode();
				tmpArrayNode.add(genericArrayNode.get(i));
				// 300 records on one migration
				if ((i + 1) % perNumber == 0) {
					objectNode = EasemobIMUsers
							.createNewIMUserBatch(genericArrayNode);
					tmpArrayNode.removeAll();
					continue;
				}

				// the rest records that less than the times of 300
				if (i > (genericArrayNode.size() / perNumber * perNumber - 1)) {
					objectNode = EasemobIMUsers
							.createNewIMUserBatch(genericArrayNode);
					tmpArrayNode.removeAll();
				}
			}
		}

		return objectNode;
	}

	/**
	 * 获取IM用户
	 * 
	 * @param userPrimaryKey
	 *            用户主键：username或�?uuid
	 * @return
	 */
	public static ObjectNode getIMUsersByPrimaryKey(String userPrimaryKey) {
		ObjectNode objectNode = factory.objectNode();

		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+",
				EasemobConstants.APPKEY)) {
			LOGGER.error("Bad format of Constants.APPKEY: "
					+ EasemobConstants.APPKEY);

			objectNode.put("message", "Bad format of Constants.APPKEY");

			return objectNode;
		}

		// check properties that must be provided
		if (StringUtils.isEmpty(userPrimaryKey)) {
			LOGGER.error("The primaryKey that will be useed to query must be provided .");

			objectNode
					.put("message",
							"The primaryKey that will be useed to query must be provided .");

			return objectNode;
		}

		try {

			URL userPrimaryUrl = HTTPClientUtils.getURL(EasemobConstants.APPKEY
					.replace("#", "/") + "/users/" + userPrimaryKey);
			objectNode = HTTPClientUtils.sendHTTPRequest(userPrimaryUrl,
					credential, null, HTTPMethod.METHOD_GET);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 删除IM用户[单个]
	 * 
	 * 删除指定Constants.APPKEY下IM单个用户
	 * 
	 * 
	 * @param userPrimaryKey
	 * @return
	 */
	public static ObjectNode deleteIMUserByUserPrimaryKey(String userPrimaryKey) {
		ObjectNode objectNode = factory.objectNode();

		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+",
				EasemobConstants.APPKEY)) {
			LOGGER.error("Bad format of Constants.APPKEY: "
					+ EasemobConstants.APPKEY);

			objectNode.put("message", "Bad format of Constants.APPKEY");

			return objectNode;
		}

		try {

			URL deleteUserPrimaryUrl = HTTPClientUtils
					.getURL(EasemobConstants.APPKEY.replace("#", "/")
							+ "/users/" + userPrimaryKey);
			objectNode = HTTPClientUtils.sendHTTPRequest(deleteUserPrimaryUrl,
					credential, null, HTTPMethod.METHOD_DELETE);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 删除IM用户[批量]
	 * 
	 * 批量指定Constants.APPKEY下删除IM用户
	 * 
	 * @param limit
	 * @param queryStr
	 * @return
	 */
	public static ObjectNode deleteIMUserByUsernameBatch(Long limit,
			String queryStr) {

		ObjectNode objectNode = factory.objectNode();

		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+",
				EasemobConstants.APPKEY)) {
			LOGGER.error("Bad format of Constants.APPKEY: "
					+ EasemobConstants.APPKEY);

			objectNode.put("message", "Bad format of Constants.APPKEY");

			return objectNode;
		}
		if (StringUtils.isEmpty(queryStr)) {
			LOGGER.error("queryStr must be provided .");

			objectNode.put("message", "queryStr must be provided .");

			return objectNode;
		}

		try {

			URL deleteIMUserByUsernameBatchUrl = HTTPClientUtils
					.getURL(EasemobConstants.APPKEY.replace("#", "/")
							+ "/users" + "?ql=" + queryStr + "&limit=" + limit);
			objectNode = HTTPClientUtils.sendHTTPRequest(
					deleteIMUserByUsernameBatchUrl, credential, null,
					HTTPMethod.METHOD_DELETE);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 重置IM用户密码 提供管理员token
	 * 
	 * @param userPrimaryKey
	 * @param dataObjectNode
	 * @return
	 */
	public static ObjectNode modifyIMUserPasswordWithAdminToken(
			String userPrimaryKey, ObjectNode dataObjectNode) {
		ObjectNode objectNode = factory.objectNode();

		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+",
				EasemobConstants.APPKEY)) {
			LOGGER.error("Bad format of Constants.APPKEY: "
					+ EasemobConstants.APPKEY);

			objectNode.put("message", "Bad format of Constants.APPKEY");

			return objectNode;
		}

		if (StringUtils.isEmpty(userPrimaryKey)) {
			LOGGER.error("Property that named userPrimaryKey must be provided，the value is username or uuid of imuser.");

			objectNode
					.put("message",
							"Property that named userPrimaryKey must be provided，the value is username or uuid of imuser.");

			return objectNode;
		}

		if (null != dataObjectNode && !dataObjectNode.has("newpassword")) {
			LOGGER.error("Property that named newpassword must be provided .");

			objectNode.put("message",
					"Property that named newpassword must be provided .");

			return objectNode;
		}

		try {
			URL modifyIMUserPasswordWithAdminTokenUrl = HTTPClientUtils
					.getURL(EasemobConstants.APPKEY.replace("#", "/")
							+ "/users/" + userPrimaryKey + "/password");
			objectNode = HTTPClientUtils.sendHTTPRequest(
					modifyIMUserPasswordWithAdminTokenUrl, credential,
					dataObjectNode, HTTPMethod.METHOD_PUT);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 添加好友[单个]
	 * 
	 * @param ownerUserPrimaryKey
	 * @param friendUserPrimaryKey
	 * 
	 * @return
	 */
	public static ObjectNode addFriendSingle(String ownerUserPrimaryKey,
			String friendUserPrimaryKey) {
		ObjectNode objectNode = factory.objectNode();

		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+",
				EasemobConstants.APPKEY)) {
			LOGGER.error("Bad format of Constants.APPKEY: "
					+ EasemobConstants.APPKEY);

			objectNode.put("message", "Bad format of Constants.APPKEY");

			return objectNode;
		}

		if (StringUtils.isEmpty(ownerUserPrimaryKey)) {
			LOGGER.error("Your userPrimaryKey must be provided，the value is username or uuid of imuser.");

			objectNode
					.put("message",
							"Your userPrimaryKey must be provided，the value is username or uuid of imuser.");

			return objectNode;
		}

		if (StringUtils.isEmpty(friendUserPrimaryKey)) {
			LOGGER.error("The userPrimaryKey of friend must be provided，the value is username or uuid of imuser.");

			objectNode
					.put("message",
							"The userPrimaryKey of friend must be provided，the value is username or uuid of imuser.");

			return objectNode;
		}

		try {

			URL addFriendSingleUrl = HTTPClientUtils
					.getURL(EasemobConstants.APPKEY.replace("#", "/")
							+ "/users/" + ownerUserPrimaryKey
							+ "/contacts/users/" + friendUserPrimaryKey);

			ObjectNode body = factory.objectNode();
			objectNode = HTTPClientUtils.sendHTTPRequest(addFriendSingleUrl,
					credential, body, HTTPMethod.METHOD_POST);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 删除好友
	 * 
	 * @param ownerUserPrimaryKey
	 * @param friendUserPrimaryKey
	 * 
	 * @return
	 */
	public static ObjectNode deleteFriendSingle(String ownerUserPrimaryKey,
			String friendUserPrimaryKey) {
		ObjectNode objectNode = factory.objectNode();

		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+",
				EasemobConstants.APPKEY)) {
			LOGGER.error("Bad format of Constants.APPKEY: "
					+ EasemobConstants.APPKEY);

			objectNode.put("message", "Bad format of Constants.APPKEY");

			return objectNode;
		}

		if (StringUtils.isEmpty(ownerUserPrimaryKey)) {
			LOGGER.error("Your userPrimaryKey must be provided，the value is username or uuid of imuser.");

			objectNode
					.put("message",
							"Your userPrimaryKey must be provided，the value is username or uuid of imuser.");

			return objectNode;
		}

		if (StringUtils.isEmpty(friendUserPrimaryKey)) {
			LOGGER.error("The userPrimaryKey of friend must be provided，the value is username or uuid of imuser.");

			objectNode
					.put("message",
							"The userPrimaryKey of friend must be provided，the value is username or uuid of imuser.");

			return objectNode;
		}

		try {
			URL addFriendSingleUrl = HTTPClientUtils
					.getURL(EasemobConstants.APPKEY.replace("#", "/")
							+ "/users/" + ownerUserPrimaryKey
							+ "/contacts/users/" + friendUserPrimaryKey);

			ObjectNode body = factory.objectNode();
			objectNode = HTTPClientUtils.sendHTTPRequest(addFriendSingleUrl,
					credential, body, HTTPMethod.METHOD_DELETE);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 获取用户�?��好友
	 * 
	 * @param ownerUserPrimaryKey
	 * 
	 * @return
	 */
	public static ObjectNode getFriends(String ownerUserPrimaryKey) {
		ObjectNode objectNode = factory.objectNode();

		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+",
				EasemobConstants.APPKEY)) {
			LOGGER.error("Bad format of Constants.APPKEY: "
					+ EasemobConstants.APPKEY);

			objectNode.put("message", "Bad format of Constants.APPKEY");

			return objectNode;
		}

		if (StringUtils.isEmpty(ownerUserPrimaryKey)) {
			LOGGER.error("Your userPrimaryKey must be provided，the value is username or uuid of imuser.");

			objectNode
					.put("message",
							"Your userPrimaryKey must be provided，the value is username or uuid of imuser.");

			return objectNode;
		}

		try {

			URL addFriendSingleUrl = HTTPClientUtils
					.getURL(EasemobConstants.APPKEY.replace("#", "/")
							+ "/users/" + ownerUserPrimaryKey
							+ "/contacts/users");

			ObjectNode body = factory.objectNode();
			objectNode = HTTPClientUtils.sendHTTPRequest(addFriendSingleUrl,
					credential, body, HTTPMethod.METHOD_GET);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * IM用户登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public static ObjectNode imUserLogin(String username, String password) {

		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+",
				EasemobConstants.APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + EasemobConstants.APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}
		if (StringUtils.isEmpty(username)) {
			LOGGER.error("Your username must be provided，the value is username or uuid of imuser.");

			objectNode
					.put("message",
							"Your username must be provided，the value is username or uuid of imuser.");

			return objectNode;
		}
		if (StringUtils.isEmpty(password)) {
			LOGGER.error("Your password must be provided，the value is username or uuid of imuser.");

			objectNode
					.put("message",
							"Your password must be provided，the value is username or uuid of imuser.");

			return objectNode;
		}

		try {
			ObjectNode dataNode = factory.objectNode();
			dataNode.put("grant_type", "password");
			dataNode.put("username", username);
			dataNode.put("password", password);

			objectNode = HTTPClientUtils.sendHTTPRequest(
					EndPoints.TOKEN_APP_URL, null, dataNode,
					HTTPMethod.METHOD_POST);

		} catch (Exception e) {
			throw new RuntimeException(
					"Some errors ocuured while fetching a token by usename and passowrd .");
		}

		return objectNode;
	}

	/*************************************************************************************************************************/
	/**
	 * 指定前缀和数量生成用户基本数�?
	 * 
	 * @param usernamePrefix
	 * @param number
	 * @return
	 */
	public static ArrayNode genericArrayNode(String usernamePrefix, Long number) {
		ArrayNode arrayNode = factory.arrayNode();
		for (int i = 0; i < number; i++) {
			ObjectNode userNode = factory.objectNode();
			userNode.put("username", usernamePrefix + "_" + i);
			userNode.put("password", EasemobConstants.DEFAULT_PASSWORD);

			arrayNode.add(userNode);
		}

		return arrayNode;
	}

}

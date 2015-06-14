package com.biyanzhi.huanxinImpl;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * REST API Demo : 图片语音文件上传、下�?HttpClient4.3实现
 * 
 * Doc URL: http://www.easemob.com/docs/rest/files/
 * 
 * @author Lynch 2014-09-15
 * 
 */
public class EasemobFiles {
	private static Logger LOGGER = LoggerFactory.getLogger(EasemobFiles.class);
	private static final String APPKEY = EasemobConstants.APPKEY;
	private static JsonNodeFactory factory = new JsonNodeFactory(false);

	// 通过app的client_id和client_secret来获取app管理员token
	private static Credential credential = new ClientSecretCredential(
			EasemobConstants.APP_CLIENT_ID, EasemobConstants.APP_CLIENT_SECRET,
			Roles.USER_ROLE_APPADMIN);

	public static void main(String[] args) {
		/**
		 * 上传图片文件 curl示例 curl --verbose --header "Authorization: Bearer {token}"
		 * --header "restrict-access:true" --form file=@/Users/stliu/a.jpg
		 * https://a1.easemob.com/easemob-playground/test1/chatfiles
		 */
		File uploadImgFile = new File("/home/lynch/Pictures/24849.jpg");
		ObjectNode imgDataNode = mediaUpload(uploadImgFile);
		if (null != imgDataNode) {
			LOGGER.info("上传图片文件: " + imgDataNode.toString());
		}
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * 下载图片文件 curl示例 curl -O -H "share-secret: " --header
		 * "Authorization: Bearer {token}" -H "Accept: application/octet-stream"
		 * http
		 * ://a1.easemob.com/easemob-playground/test1/chatfiles/0c0f5f3a-e66b
		 * -11e3-8863-f1c202c2b3ae
		 */
		String imgFileUUID = imgDataNode.path("entities").get(0).path("uuid")
				.asText();
		String shareSecret = imgDataNode.path("entities").get(0)
				.path("share-secret").asText();
		File downloadedImgFileLocalPath = new File(uploadImgFile.getPath()
				.substring(0, uploadImgFile.getPath().lastIndexOf("."))
				+ "-1.jpg");
		boolean isThumbnail = false;
		ObjectNode downloadImgDataNode = mediaDownload(imgFileUUID,
				shareSecret, downloadedImgFileLocalPath, isThumbnail);
		if (null != downloadImgDataNode) {
			LOGGER.info("下载图片文件: " + downloadImgDataNode.toString());
		}
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * 下载缩略�?curl示例 curl -O -H "thumbnail: true" -H "share-secret: {secret}"
		 * -H "Authorization: Bearer {token}" -H
		 * "Accept: application/octet-stream"
		 * http://a1.easemob.com/easemob-playground
		 * /test1/chatfiles/0c0f5f3a-e66b-11e3-8863-f1c202c2b3ae
		 */
		File downloadedLocalPathThumnailImg = new File(uploadImgFile.getPath()
				.substring(0, uploadImgFile.getPath().lastIndexOf("."))
				+ "-2.jpg");
		isThumbnail = true;
		ObjectNode downloadThumnailImgDataNode = mediaDownload(imgFileUUID,
				shareSecret, downloadedLocalPathThumnailImg, isThumbnail);
		if (null != downloadThumnailImgDataNode) {
			LOGGER.info("下载缩略�? " + downloadThumnailImgDataNode.toString());
		}

		/**
		 * 上传语音文件 curl示例 curl --verbose --header "Authorization: Bearer {token}"
		 * --header "restrict-access:true" --form file=@/Users/stliu/music.MP3
		 * https://a1.easemob.com/easemob-playground/test1/chatfiles
		 */
		File uploadAudioFile = new File("/home/lynch/Music/music.MP3");
		ObjectNode audioDataNode = mediaUpload(uploadAudioFile);
		if (null != audioDataNode) {
			LOGGER.info("上传语音文件: " + audioDataNode.toString());
		}

		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * 下载语音文件 curl示例 curl -O -H "share-secret: {secret}" --header
		 * "Authorization: Bearer {token}" -H "Accept: application/octet-stream"
		 * http
		 * ://a1.easemob.com/easemob-playground/test1/chatfiles/0c0f5f3a-e66b
		 * -11e3-8863-f1c202c2b3ae
		 */
		String audioFileUUID = audioDataNode.path("entities").get(0)
				.path("uuid").asText();
		String audioFileShareSecret = audioDataNode.path("entities").get(0)
				.path("share-secret").asText();
		File audioFileLocalPath = new File(uploadAudioFile.getPath().substring(
				0, uploadAudioFile.getPath().lastIndexOf("."))
				+ "-1.MP3");
		ObjectNode downloadAudioDataNode = mediaDownload(audioFileUUID,
				audioFileShareSecret, audioFileLocalPath, null);
		if (null != downloadAudioDataNode) {
			LOGGER.info("下载语音文件: " + downloadAudioDataNode.toString());
		}
	}

	/**
	 * 图片/语音文件上传
	 * 
	 * @param uploadFile
	 * 
	 */
	public static ObjectNode mediaUpload(File uploadFile) {

		ObjectNode objectNode = factory.objectNode();

		if (!uploadFile.exists()) {

			LOGGER.error("file: " + uploadFile.toString() + " is not exist!");

			objectNode.put("message", "File or directory not found");

			return objectNode;
		}

		if (!HTTPClientUtils
				.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		try {

			List<NameValuePair> headers = new ArrayList<NameValuePair>();
			headers.add(new BasicNameValuePair("restrict-access", "true"));

			objectNode = HTTPClientUtils.uploadFile(EndPoints.CHATFILES_URL,
					uploadFile, credential, headers);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 图片语音文件下载
	 * 
	 * 
	 * @param fileUUID
	 *            文件在DB的UUID
	 * @param shareSecret
	 *            文件在DB中保存的shareSecret
	 * @param localPath
	 *            下载后文件存放地�?
	 * @param isThumbnail
	 *            是否下载缩略�?true:缩略�?false:非缩略图
	 * @return
	 */
	public static ObjectNode mediaDownload(String fileUUID, String shareSecret,
			File localPath, Boolean isThumbnail) {

		ObjectNode objectNode = factory.objectNode();

		File downLoadedFile = null;

		if (!HTTPClientUtils
				.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		try {

			List<NameValuePair> headers = new ArrayList<NameValuePair>();
			if (!StringUtils.isEmpty(shareSecret)) {
				headers.add(new BasicNameValuePair("share-secret", shareSecret));
			}
			headers.add(new BasicNameValuePair("Accept",
					"application/octet-stream"));
			if (isThumbnail != null && isThumbnail) {
				headers.add(new BasicNameValuePair("thumbnail", String
						.valueOf(isThumbnail)));
			}

			URL mediaDownloadUrl = HTTPClientUtils.getURL(EasemobConstants.APPKEY
					.replace("#", "/") + "/chatfiles/" + fileUUID);
			downLoadedFile = HTTPClientUtils.downLoadFile(mediaDownloadUrl,
					credential, headers, localPath);

		} catch (Exception e) {
			e.printStackTrace();
		}

		LOGGER.error("File download successfully，file path : "
				+ downLoadedFile.getAbsolutePath() + ".");

		objectNode.put("message", "File download successfully .");

		return objectNode;
	}

}

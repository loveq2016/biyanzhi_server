package com.biyanzhi.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.biyanzhi.bean.Picture;
import com.biyanzhi.bean.PictureScore;
import com.biyanzhi.dao.CommentDao;
import com.biyanzhi.dao.PictureDao;
import com.biyanzhi.dao.PictureScoreDao;
import com.biyanzhi.dao.UserDao;
import com.biyanzhi.enums.ErrorEnum;
import com.biyanzhi.huanxinImpl.EasemobMessages;
import com.biyanzhi.util.Constants;
import com.biyanzhi.util.DateUtils;

@Controller
public class PictureController {
	@Autowired
	private UserDao uDao;

	public UserDao getuDao() {
		return uDao;
	}

	public void setuDao(UserDao uDao) {
		this.uDao = uDao;
	}

	@Autowired
	private PictureDao pictureDao;

	public PictureDao getPictureDao() {
		return pictureDao;
	}

	public void setPictureDao(PictureDao pictureDao) {
		this.pictureDao = pictureDao;
	}

	@Autowired
	private PictureScoreDao scoreDao;

	public PictureScoreDao getScoreDao() {
		return scoreDao;
	}

	public void setScoreDao(PictureScoreDao scoreDao) {
		this.scoreDao = scoreDao;
	}

	@Autowired
	private CommentDao dao;

	public CommentDao getDao() {
		return dao;
	}

	public void setDao(CommentDao dao) {
		this.dao = dao;
	}

	@ResponseBody
	@RequestMapping(value = "/addpicture.do", method = RequestMethod.POST)
	public String addPicture(HttpServletRequest request) {
		String img_path = request.getSession().getServletContext()
				.getRealPath("picture_image");
		MultipartResolver resolver = new CommonsMultipartResolver(request
				.getSession().getServletContext());
		MultipartHttpServletRequest multipartRequest = resolver
				.resolveMultipart(request);
		int publisher_id = Integer.valueOf(multipartRequest
				.getParameter("publisher_id"));
		String content = multipartRequest.getParameter("content");
		String publisher_name = multipartRequest.getParameter("publisher_name");
		String publisher_avatar = multipartRequest
				.getParameter("publisher_avatar");
		String publicsh_time = DateUtils.getPicturePublishTime();
		Picture pic = new Picture();
		pic.setContent(content);
		pic.setPublish_time(publicsh_time);
		pic.setPublisher_name(publisher_name);
		pic.setPublisher_id(publisher_id);
		pic.setPublisher_avatar(publisher_avatar);
		// pic.setPublish_time_last_update(publicsh_time);
		Map<String, Object> params = new HashMap<String, Object>();
		int picture_id = 0;
		// 保存图片
		MultipartFile file = multipartRequest.getFile("image");
		String serverPath = Constants.SERVER_PATH + "/picture_image/";
		try {
			if (file != null && !file.isEmpty()) {
				String file_name = file.getOriginalFilename();
				String save_filename = DateUtils.getUpLoadFileName()
						+ file_name.substring(file_name.lastIndexOf("."),
								file_name.length());
				File targetFile = new File(img_path, save_filename);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				file.transferTo(targetFile);
				pic.setPicture_image_url(serverPath + save_filename);
				picture_id = pictureDao.insertPicture(pic);
				if (picture_id <= 0) {
					params.put("rt", 0);
					params.put("err", ErrorEnum.INVALID.name());
					JSONObject jsonObjectFromMap = JSONObject
							.fromObject(params);
					return jsonObjectFromMap.toString();
				}
			} else {
				params.put("rt", 0);
				params.put("err", ErrorEnum.INVALID.name());
				JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
				return jsonObjectFromMap.toString();
			}
		} catch (IOException e) {
			params.put("rt", 0);
			params.put("err", ErrorEnum.INVALID.name());
			JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
			return jsonObjectFromMap.toString();
		}
		params.put("rt", 1);
		params.put("picture_id", picture_id);
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/getpicturelists.do", method = RequestMethod.POST)
	public String getPictureList(HttpServletRequest request) {
		String publish_time = request.getParameter("publish_time");
		// int user_id = Integer.valueOf(request.getParameter("user_id"));
		List<Picture> lists = new ArrayList<Picture>();
		lists.addAll(pictureDao.getPictureList(publish_time));
		// for (Picture pic : lists) {
		// pic.setIs_play_score(scoreDao.getPlayScoreByUserID(user_id) > 0);
		// }
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pictures", lists);
		params.put("rt", 1);
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		System.out.println("size:" + jsonObjectFromMap.toString());
		return jsonObjectFromMap.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/getPictureListsByUserID.do", method = RequestMethod.POST)
	public String getPictureListByUserID(HttpServletRequest request) {
		int publisher_user_id = Integer.valueOf(request
				.getParameter("publisher_user_id"));
		List<Picture> lists = new ArrayList<Picture>();
		lists.addAll(pictureDao.getPictureListByUserID(publisher_user_id));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pictures", lists);
		params.put("rt", 1);
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/getPictureListMoreByUserID.do", method = RequestMethod.POST)
	public String getPictureListMoreByUserID(HttpServletRequest request) {
		String publish_time = request.getParameter("publish_time");
		int publisher_user_id = Integer.valueOf(request
				.getParameter("publisher_user_id"));
		List<Picture> lists = new ArrayList<Picture>();
		lists.addAll(pictureDao.getPictureListMoreByUserID(publisher_user_id,
				publish_time));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pictures", lists);
		params.put("rt", 1);
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		System.out.println(jsonObjectFromMap);
		return jsonObjectFromMap.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/loadMorePictureList.do", method = RequestMethod.POST)
	public String loadMorePictureList(HttpServletRequest request) {
		String publish_time = request.getParameter("publish_time");
		// int user_id = Integer.valueOf(request.getParameter("user_id"));
		List<Picture> lists = new ArrayList<Picture>();
		lists.addAll(pictureDao.loadMorePictureList(publish_time));
		// for (Picture pic : lists) {
		// pic.setIs_play_score(scoreDao.getPlayScoreByUserID(user_id) > 0);
		// }
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pictures", lists);
		params.put("rt", 1);
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		System.out.println("size:" + lists.size());
		return jsonObjectFromMap.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/getGirlBangPictureList.do", method = RequestMethod.POST)
	public String getGirlBangPictureList(HttpServletRequest request) {
		// int user_id = Integer.valueOf(request.getParameter("user_id"));
		List<Picture> lists = new ArrayList<Picture>();
		lists.addAll(pictureDao.getGirlBangPictureList());
		// for (Picture pic : lists) {
		// pic.setIs_play_score(scoreDao.getPlayScoreByUserID(user_id) > 0);
		// }
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pictures", lists);
		params.put("rt", 1);
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		System.out.println("size:" + lists.size());
		return jsonObjectFromMap.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/getBoyBangPictureList.do", method = RequestMethod.POST)
	public String getBoyBangPictureList(HttpServletRequest request) {
		// int user_id = Integer.valueOf(request.getParameter("user_id"));
		List<Picture> lists = new ArrayList<Picture>();
		lists.addAll(pictureDao.getBoyBangPictureList());
		// for (Picture pic : lists) {
		// pic.setIs_play_score(scoreDao.getPlayScoreByUserID(user_id) > 0);
		// }
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pictures", lists);
		params.put("rt", 1);
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		System.out.println("size:" + lists.size());
		return jsonObjectFromMap.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/getPictureByID.do", method = RequestMethod.POST)
	public String getPictureByID(HttpServletRequest request) {
		int picture_id = Integer.valueOf(request.getParameter("picture_id"));
		Picture picture = pictureDao.getPictureByPictureID(picture_id);
		Map<String, Object> params = new HashMap<String, Object>();
		if (picture == null) {
			params.put("rt", 0);
			params.put("err", ErrorEnum.INVALID.name());
		} else {
			params.put("rt", 1);
			params.put("picture", picture);
		}
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/addpicturescore.do", method = RequestMethod.POST)
	public String addPictureScore(HttpServletRequest request) {
		int user_id = Integer.valueOf(request.getParameter("user_id"));
		int picture_id = Integer.valueOf(request.getParameter("picture_id"));
		int picture_score = Integer.valueOf(request
				.getParameter("picture_score"));
		int picture_publisher_id = Integer.valueOf(request
				.getParameter("picture_publisher_id"));
		String user_name = request.getParameter("user_name");

		PictureScore score = new PictureScore();
		score.setPicture_id(picture_id);
		score.setPicture_score(picture_score);
		score.setUser_id(user_id);
		score.setPlay_score_time(DateUtils.getPicturePublishTime());
		int result = scoreDao.addPictureScore(score);
		Map<String, Object> params = new HashMap<String, Object>();
		if (result > 0) {
			params.put("rt", 1);
			String user_chat_id = uDao.getUserChatIDByPictureID(picture_id,
					picture_publisher_id);
			EasemobMessages.sendTextMessageForPlayScore(picture_id,
					user_chat_id, "'" + user_name + "‘ 给你的照片打分了快去看看吧");
		} else {
			params.put("rt", 0);
		}
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/upDatePictureTime.do", method = RequestMethod.POST)
	public String upDatePictureTime(HttpServletRequest request) {
		int picture_id = Integer.valueOf(request.getParameter("picture_id"));
		int result = pictureDao.updatePictureUpdateTime(picture_id,
				DateUtils.getPicturePublishTime());
		Map<String, Object> params = new HashMap<String, Object>();
		if (result > 0) {
			params.put("rt", 1);
		} else {
			params.put("rt", 0);
		}
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/delPicture.do", method = RequestMethod.POST)
	public String delPicture(HttpServletRequest request) {
		int picture_id = Integer.valueOf(request.getParameter("picture_id"));
		int result = pictureDao.delPicture(picture_id);
		Map<String, Object> params = new HashMap<String, Object>();
		if (result > 0) {
			params.put("rt", 1);
		} else {
			params.put("rt", 0);
			params.put("err", ErrorEnum.INVALID.name());
		}
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();

	}
}

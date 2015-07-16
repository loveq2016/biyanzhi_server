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

import com.biyanzhi.bean.FeedBack;
import com.biyanzhi.bean.GuanZhu;
import com.biyanzhi.bean.Picture;
import com.biyanzhi.bean.PictureScore;
import com.biyanzhi.bean.SMSCode;
import com.biyanzhi.bean.User;
import com.biyanzhi.dao.CommentDao;
import com.biyanzhi.dao.GuanZhuDao;
import com.biyanzhi.dao.PictureDao;
import com.biyanzhi.dao.PictureScoreDao;
import com.biyanzhi.dao.SMSCodeDao;
import com.biyanzhi.dao.UserDao;
import com.biyanzhi.enums.ErrorEnum;
import com.biyanzhi.huanxinImpl.EasemobIMUsers;
import com.biyanzhi.huanxinImpl.EasemobMessages;
import com.biyanzhi.smscode.RestSMSCode;
import com.biyanzhi.util.Constants;
import com.biyanzhi.util.DateUtils;
import com.biyanzhi.util.MD5;
import com.biyanzhi.util.Utils;

@Controller
public class UserController {
	@Autowired
	private SMSCodeDao dao;

	public SMSCodeDao getDao() {
		return dao;
	}

	public void setDao(SMSCodeDao dao) {
		this.dao = dao;
	}

	@Autowired
	private UserDao uDao;

	public UserDao getuDao() {
		return uDao;
	}

	public void setuDao(UserDao uDao) {
		this.uDao = uDao;
	}

	@Autowired
	private PictureDao pDao;

	public PictureDao getpDao() {
		return pDao;
	}

	public void setpDao(PictureDao pDao) {
		this.pDao = pDao;
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
	private CommentDao commentDdao;

	public CommentDao getCommentDdao() {
		return commentDdao;
	}

	public void setCommentDdao(CommentDao commentDdao) {
		this.commentDdao = commentDdao;
	}

	@Autowired
	private GuanZhuDao guanzhuDao;

	public GuanZhuDao getGuanzhuDao() {
		return guanzhuDao;
	}

	public void setGuanzhuDao(GuanZhuDao guanzhuDao) {
		this.guanzhuDao = guanzhuDao;
	}

	@ResponseBody
	@RequestMapping(value = "/checkVerifyCode.do", method = RequestMethod.POST)
	public String checkVerifyCode(HttpServletRequest request) {
		String cellphone = request.getParameter("user_cellphone");
		String sms_code = request.getParameter("sms_code");
		String code = dao.findCodeByCellphone(cellphone);
		boolean res = false;
		if (null == code) {
			res = false;
		} else {
			res = code.equals(sms_code);

		}

		Map<String, Object> params = new HashMap<String, Object>();
		if (!res) {
			params.put("err", ErrorEnum.SMS_CODE_ERR.name());
			params.put("rt", 0);
		} else {
			params.put("rt", 1);
			dao.delCodeByUserCellPhone(cellphone);
		}
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/getVerifyCode.do", method = RequestMethod.POST)
	public String getVerifyCode(HttpServletRequest request) {
		String cellphone = request.getParameter("user_cellphone");
		String result = uDao.verifyCellphone(cellphone);
		Map<String, Object> params = new HashMap<String, Object>();
		if (null != result) {
			params.put("err", ErrorEnum.USER_ALREADY_EXIST.name());
			params.put("rt", 0);
		} else {
			SMSCode code = new SMSCode();
			String str_code = Utils.getSMSCode();
			code.setSms_code(str_code);
			code.setUser_cellphone(cellphone);
			code.setTime(DateUtils.getUpLoadFileName());
			dao.delCodeByUserCellPhone(cellphone);
			dao.insertToDB(code);
			RestSMSCode.sendCode(str_code, cellphone);
			System.out.println("sms_code:" + str_code);
			params.put("rt", 1);
		}
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/getFindPassWordVerifyCode.do", method = RequestMethod.POST)
	public String getFindPassWordVerifyCode(HttpServletRequest request) {
		String cellphone = request.getParameter("user_cellphone");
		String result = uDao.verifyCellphone(cellphone);
		Map<String, Object> params = new HashMap<String, Object>();
		if (null != result) {
			params.put("rt", 1);
			SMSCode code = new SMSCode();
			String str_code = Utils.getSMSCode();
			code.setSms_code(str_code);
			code.setUser_cellphone(cellphone);
			code.setTime(DateUtils.getUpLoadFileName());
			dao.delCodeByUserCellPhone(cellphone);
			dao.insertToDB(code);
			RestSMSCode.sendCode(str_code, cellphone);
			System.out.println("sms_code:" + str_code);
		} else {
			params.put("err", ErrorEnum.NOT_EXIST_USER.name());
			params.put("rt", 0);
		}
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/userRegister.do", method = RequestMethod.POST)
	public String userRegister(HttpServletRequest request) {
		String img_path = request.getSession().getServletContext()
				.getRealPath("user-avatar");
		MultipartResolver resolver = new CommonsMultipartResolver(request
				.getSession().getServletContext());
		MultipartHttpServletRequest multipartRequest = resolver
				.resolveMultipart(request);
		String user_name = multipartRequest.getParameter("user_name")
				.toString();
		String user_cellphone = multipartRequest.getParameter("user_cellphone")
				.toString();
		String user_password = multipartRequest.getParameter("user_password")
				.toString();
		String user_gender = multipartRequest.getParameter("user_gender")
				.toString();
		String user_birthday = multipartRequest.getParameter("user_birthday")
				.toString();
		String user_address = "";
		user_address = multipartRequest.getParameter("user_address").toString();
		User user = new User();
		user.setUser_address(user_address);
		user.setUser_birthday(user_birthday);
		user.setUser_cellphone(user_cellphone);
		user.setUser_gender(user_gender);
		user.setUser_name(user_name);
		user.setUser_chat_id(MD5.Md5(user_cellphone));
		user.setUser_password(user_password);
		Map<String, Object> params = new HashMap<String, Object>();
		int user_id = 0;
		// ±£´æÍ¼Æ¬
		MultipartFile file = multipartRequest.getFile("image");
		String serverPath = Constants.SERVER_PATH + "/user-avatar/";
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
				user.setUser_avatar(serverPath + save_filename);
				user_id = uDao.insertUserToDB(user);
				if (user_id > 0) {
					params.put("rt", 1);
					JSONObject jsonObjectFromMap = JSONObject
							.fromObject(params);
					EasemobIMUsers.createNewUser(MD5.Md5(user_cellphone),
							MD5.Md5(user_cellphone));
					return jsonObjectFromMap.toString();
				}
			}
		} catch (IOException e) {

		}
		params.put("rt", 0);
		params.put("err", ErrorEnum.INVALID.name());
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/userLogin.do", method = RequestMethod.POST)
	public String userLogin(HttpServletRequest request) {
		String cellphone = request.getParameter("user_cellphone");
		String password = request.getParameter("user_password");
		String result = uDao.verifyCellphone(cellphone);
		Map<String, Object> params = new HashMap<String, Object>();
		if (null == result) {
			params.put("err", ErrorEnum.NOT_EXIST_USER.name());
			params.put("rt", 0);
		} else {
			User user = uDao.findUserByUserCellPhoneAndPassword(cellphone,
					password);
			if (user == null) {
				params.put("err", ErrorEnum.WRONG_PASSWORD.name());
				params.put("rt", 0);
			} else {
				params.put("user", user);
				params.put("rt", 1);
			}

		}
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/getUserInfo.do", method = RequestMethod.POST)
	public String getUserInfo(HttpServletRequest request) {
		int publisher_user_id = Integer.valueOf(request
				.getParameter("publicsher_user_id"));
		int user_id = Integer.valueOf(request.getParameter("user_id"));
		Map<String, Object> params = new HashMap<String, Object>();
		User user = uDao.findUserByUserID(publisher_user_id);
		if (user == null) {
			params.put("err", ErrorEnum.INVALID.name());
			params.put("rt", 0);
		} else {
			List<Picture> lists = new ArrayList<Picture>();
			lists.addAll(pDao.getPictureListByUserID(publisher_user_id));
			GuanZhu guanzhu = new GuanZhu();
			guanzhu.setUser_id(user_id);
			guanzhu.setGuanzhu_user_id(publisher_user_id);
			user.setGuanZhu(guanzhuDao.isGuanZhuByUserIDAndGuanZhuUserID(
					user_id, publisher_user_id) > 0);
			params.put("user", user);
			params.put("pictures", lists);
			params.put("rt", 1);
		}
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		System.out.println("info:" + jsonObjectFromMap.toString());
		return jsonObjectFromMap.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/addGuanZhu.do", method = RequestMethod.POST)
	public String addGuanZhu(HttpServletRequest request) {
		int user_id = Integer.valueOf(request.getParameter("user_id"));
		int guanzhu_user_id = Integer.valueOf(request
				.getParameter("guanzhu_user_id"));
		String user_name = request.getParameter("user_name");
		String guanzhu_user_chat_id = request
				.getParameter("guanzhu_user_chat_id");
		GuanZhu gz = new GuanZhu();
		gz.setGuanzhu_user_id(guanzhu_user_id);
		gz.setUser_id(user_id);
		int result = guanzhuDao.addGuanZhu(gz);
		Map<String, Object> params = new HashMap<String, Object>();
		if (result <= 0) {
			params.put("err", ErrorEnum.INVALID.name());
			params.put("rt", 0);
		} else {
			params.put("rt", 1);
			EasemobMessages.sendTextMessageForGuanzhu(guanzhu_user_chat_id,
					"' " + user_name + " '¹Ø×¢ÁËÄã");
		}
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/upDateUserName.do", method = RequestMethod.POST)
	public String upDateUserName(HttpServletRequest request) {
		int user_id = Integer.valueOf(request.getParameter("user_id"));
		String user_name = request.getParameter("user_name");
		int result = uDao.upDateUserName(user_name, user_id);
		Map<String, Object> params = new HashMap<String, Object>();
		if (result <= 0) {
			params.put("err", ErrorEnum.INVALID.name());
			params.put("rt", 0);
		} else {
			params.put("rt", 1);
		}
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/upDateUserAddress.do", method = RequestMethod.POST)
	public String upDateUserAddress(HttpServletRequest request) {
		int user_id = Integer.valueOf(request.getParameter("user_id"));
		String user_address = request.getParameter("user_address");
		int result = uDao.upDateUserAddress(user_address, user_id);
		Map<String, Object> params = new HashMap<String, Object>();
		if (result <= 0) {
			params.put("err", ErrorEnum.INVALID.name());
			params.put("rt", 0);
		} else {
			params.put("rt", 1);
		}
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/upLoadUserAvatar.do", method = RequestMethod.POST)
	public String upLoadUserAvatar(HttpServletRequest request) {
		String img_path = request.getSession().getServletContext()
				.getRealPath("user-avatar");
		MultipartResolver resolver = new CommonsMultipartResolver(request
				.getSession().getServletContext());
		MultipartHttpServletRequest multipartRequest = resolver
				.resolveMultipart(request);
		Map<String, Object> params = new HashMap<String, Object>();
		int user_id = Integer.valueOf(multipartRequest.getParameter("user_id"));
		// ±£´æÍ¼Æ¬
		MultipartFile file = multipartRequest.getFile("image");
		String serverPath = Constants.SERVER_PATH + "/user-avatar/";
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
				int result = uDao.upDateUserAvatar(serverPath + save_filename,
						user_id);
				if (result > 0) {
					params.put("rt", 1);
					params.put("user_avatar", serverPath + save_filename);
				} else {
					params.put("err", ErrorEnum.INVALID.name());
					params.put("rt", 0);
				}
			}
		} catch (IOException e) {
			params.put("err", ErrorEnum.INVALID.name());
			params.put("rt", 0);
		}

		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/getGuanZhuUserListsByUserID.do", method = RequestMethod.POST)
	public String getGuanZhuUserListsByUserID(HttpServletRequest request) {
		int guanzhu_user_id = Integer.valueOf(request
				.getParameter("guanzhu_user_id"));
		List<User> users = uDao.getGuanZhuUsersByUserID(guanzhu_user_id);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rt", 1);
		params.put("users", users);
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/getVersion.do", method = RequestMethod.POST)
	public String getVersion(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rt", 1);
		params.put("app_version_name", Constants.APP_VSERSION_NAME);
		params.put("app_version_code", Constants.APP_VSERSION_CODE);
		params.put("version_info", Constants.VERSION_INFO);
		params.put("app_link", Constants.APP_LINK);
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		System.out.println("version:" + jsonObjectFromMap.toString());
		return jsonObjectFromMap.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/changePassword.do", method = RequestMethod.POST)
	public String changePassword(HttpServletRequest request) {
		String user_password = request.getParameter("user_password");
		String cell_phone = request.getParameter("cell_phone");
		Map<String, Object> params = new HashMap<String, Object>();
		int res = uDao.changeUserPassword(cell_phone, user_password);
		if (res <= 0) {
			params.put("err", ErrorEnum.INVALID);
			params.put("rt", 0);
		} else {
			params.put("err", 1);
		}
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/upLoadCrash.do", method = RequestMethod.POST)
	public String upLoadCrash(HttpServletRequest request) {
		String img_path = request.getSession().getServletContext()
				.getRealPath("crash");
		MultipartResolver resolver = new CommonsMultipartResolver(request
				.getSession().getServletContext());
		MultipartHttpServletRequest multipartRequest = resolver
				.resolveMultipart(request);
		MultipartFile file = multipartRequest.getFile("image");
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
			}
		} catch (IOException e) {

		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rt", 1);
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/getPlayScoreUserListsByPictureID.do", method = RequestMethod.POST)
	public String getPlayScoreUserListsByPictureID(HttpServletRequest request) {
		int picture_id = Integer.valueOf(request.getParameter("picture_id"));
		List<PictureScore> users = scoreDao
				.getPlayScoreUserListByPictureID(picture_id);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rt", 1);
		params.put("users", users);
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/addFeedBack.do", method = RequestMethod.POST)
	public String addFeedBack(HttpServletRequest request) {
		int user_id = Integer.valueOf(request.getParameter("user_id"));
		String feedback_content = request.getParameter("feedback_content");
		String feedback_time = DateUtils.getPicturePublishTime();
		FeedBack fb = new FeedBack();
		fb.setFeedback_content(feedback_content);
		fb.setFeedback_time(feedback_time);
		fb.setUser_id(user_id);
		int res = uDao.addFeedBack(fb);
		Map<String, Object> params = new HashMap<String, Object>();
		if (res > 0) {
			params.put("rt", 1);
		} else {
			params.put("rt", 0);
			params.put("err", ErrorEnum.INVALID);
		}
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();

	}
}

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

import com.biyanzhi.bean.Comment;
import com.biyanzhi.bean.Picture;
import com.biyanzhi.bean.SMSCode;
import com.biyanzhi.bean.User;
import com.biyanzhi.dao.CommentDao;
import com.biyanzhi.dao.PictureDao;
import com.biyanzhi.dao.PictureScoreDao;
import com.biyanzhi.dao.SMSCodeDao;
import com.biyanzhi.dao.UserDao;
import com.biyanzhi.enums.ErrorEnum;
import com.biyanzhi.smscode.RestSMSCode;
import com.biyanzhi.util.Constants;
import com.biyanzhi.util.DateUtils;
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
		String user_province = "";
		user_address = multipartRequest.getParameter("user_address").toString();
		user_province = multipartRequest.getParameter("user_province")
				.toString();
		User user = new User();
		user.setUser_address(user_address);
		user.setUser_birthday(user_birthday);
		user.setUser_cellphone(user_cellphone);
		user.setUser_gender(user_gender);
		user.setUser_name(user_name);
		user.setUser_province(user_province);
		user.setUser_password(user_password);
		Map<String, Object> params = new HashMap<String, Object>();
		int user_id = 0;
		// ����ͼƬ
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
		int user_id = Integer.valueOf(request.getParameter("user_id"));
		Map<String, Object> params = new HashMap<String, Object>();
		User user = uDao.findUserByUserID(user_id);
		if (user == null) {
			params.put("err", ErrorEnum.INVALID.name());
			params.put("rt", 0);
		} else {
			List<Picture> lists = new ArrayList<Picture>();
			lists.addAll(pDao.getPictureList());
			for (Picture pic : lists) {
				pic.setScore_number(scoreDao.getPictureScores(
						pic.getPicture_id()).size());
				pic.setAverage_score(scoreDao.getPictureAvgScore(pic
						.getPicture_id()));
				List<Comment> comments = new ArrayList<Comment>();
				comments = commentDdao.getCommentByPictureID(pic
						.getPicture_id());
				if (comments != null) {
					pic.setComments(comments);
				}
			}
			params.put("user", user);
			params.put("pictures", lists);
			params.put("rt", 1);
		}
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();

	}
}

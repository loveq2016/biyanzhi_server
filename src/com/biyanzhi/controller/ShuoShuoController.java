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

import com.biyanzhi.bean.ShuoShuo;
import com.biyanzhi.bean.ShuoShuoImage;
import com.biyanzhi.dao.ShuoShuoCommentDao;
import com.biyanzhi.dao.ShuoShuoDao;
import com.biyanzhi.dao.ShuoShuoImageDao;
import com.biyanzhi.dao.ShuoShuoPraiseDao;
import com.biyanzhi.enums.ErrorEnum;
import com.biyanzhi.util.Constants;
import com.biyanzhi.util.DateUtils;

@Controller
public class ShuoShuoController {
	@Autowired
	private ShuoShuoDao dao;

	public ShuoShuoDao getDao() {
		return dao;
	}

	public void setDao(ShuoShuoDao dao) {
		this.dao = dao;
	}

	@Autowired
	private ShuoShuoImageDao imgDao;

	public ShuoShuoImageDao getImgDao() {
		return imgDao;
	}

	public void setImgDao(ShuoShuoImageDao imgDao) {
		this.imgDao = imgDao;
	}

	@Autowired
	private ShuoShuoPraiseDao praiseDao;

	public ShuoShuoPraiseDao getPraiseDao() {
		return praiseDao;
	}

	public void setPraiseDao(ShuoShuoPraiseDao praiseDao) {
		this.praiseDao = praiseDao;
	}

	@Autowired
	private ShuoShuoCommentDao commentDao;

	public ShuoShuoCommentDao getCommentDao() {
		return commentDao;
	}

	public void setCommentDao(ShuoShuoCommentDao commentDao) {
		this.commentDao = commentDao;
	}

	@ResponseBody
	@RequestMapping(value = "/addShuoShuo.do", method = RequestMethod.POST)
	public String addShuoShuo(HttpServletRequest request) {
		String img_path = request.getSession().getServletContext()
				.getRealPath("shuoshuo_images");
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
		ShuoShuo shuo = new ShuoShuo();
		shuo.setContent(content);
		shuo.setTime(publicsh_time);
		shuo.setPublisher_name(publisher_name);
		shuo.setPublisher_id(publisher_id);
		shuo.setPublisher_avatar(publisher_avatar);
		Map<String, Object> params = new HashMap<String, Object>();
		int shuoshuo_id = dao.insert(shuo);
		if (shuoshuo_id <= 0) {
			params.put("rt", 0);
			params.put("err", ErrorEnum.INVALID.name());
			JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
			return jsonObjectFromMap.toString();
		}
		List<ShuoShuoImage> images = new ArrayList<ShuoShuoImage>();
		// ±£´æÍ¼Æ¬
		List<MultipartFile> files = multipartRequest.getFiles("images");
		String serverPath = Constants.SERVER_PATH + "/shuoshuo_images/";
		int index = 1;
		for (MultipartFile file : files) {
			try {
				if (file != null && !file.isEmpty()) {
					String file_name = file.getOriginalFilename();
					String save_filename = DateUtils.getUpLoadFileName()
							+ "_"
							+ index
							+ file_name.substring(file_name.lastIndexOf("."),
									file_name.length());
					index++;
					File targetFile = new File(img_path, save_filename);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					file.transferTo(targetFile);
					ShuoShuoImage image = new ShuoShuoImage();
					image.setShuoshuo_id(shuoshuo_id);
					image.setImg_url(serverPath + save_filename);
					images.add(image);
				}
			} catch (IOException e) {
			}
		}
		imgDao.insert(images);
		params.put("rt", 1);
		params.put("shuoshuo_id", shuoshuo_id);
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();
	}
}

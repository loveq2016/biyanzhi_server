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
import com.biyanzhi.bean.PictureImage;
import com.biyanzhi.dao.PictureDao;
import com.biyanzhi.enums.ErrorEnum;
import com.biyanzhi.util.Constants;
import com.biyanzhi.util.DateUtils;

@Controller
public class PictureController {
	@Autowired
	private PictureDao pictureDao;

	public PictureDao getPictureDao() {
		return pictureDao;
	}

	public void setPictureDao(PictureDao pictureDao) {
		this.pictureDao = pictureDao;
	}

	@ResponseBody
	@RequestMapping(value = "/addpicture.do", method = RequestMethod.GET)
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
		int picture_id = pictureDao.insertPicture(pic);
		Map<String, Object> params = new HashMap<String, Object>();
		if (picture_id <= 0) {
			params.put("rt", 0);
			params.put("err", ErrorEnum.INVALID.name());
			JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
			return jsonObjectFromMap.toString();
		}
		// ±£´æÍ¼Æ¬
		List<MultipartFile> files = multipartRequest.getFiles("image");
		String serverPath = Constants.SERVER_PATH + "/picture_image/";
		List<PictureImage> imageLists = new ArrayList<PictureImage>();
		int imgIndex = 1;
		try {
			for (MultipartFile multipartFile : files) {
				if (!multipartFile.isEmpty()) {
					String file_name = multipartFile.getOriginalFilename();
					String save_filename = DateUtils.getUpLoadFileName()
							+ "_"
							+ imgIndex
							+ file_name.substring(file_name.length() - 4,
									file_name.length());
					File targetFile = new File(img_path, save_filename);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					multipartFile.transferTo(targetFile);
					PictureImage img = new PictureImage();
					img.setPicture_id(picture_id);
					img.setImage_url(serverPath + save_filename);
					imageLists.add(img);
				}
				imgIndex++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		params.put("rt", 1);
		params.put("picture_id", picture_id);
		params.put("images", imageLists);
		params.put("time", publicsh_time);
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();
	}
}

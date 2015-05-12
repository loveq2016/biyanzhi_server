package com.biyanzhi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biyanzhi.bean.Picture;
import com.biyanzhi.dao.PictureDao;

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
		Picture pic = new Picture();
		pic.setContent("aaaaaaa");
		pic.setPublisher_avatar("bbbbb");
		return pictureDao.insertPicture(pic) + "";

	}
}

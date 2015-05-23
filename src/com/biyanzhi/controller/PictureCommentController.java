package com.biyanzhi.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biyanzhi.bean.Comment;
import com.biyanzhi.dao.CommentDao;
import com.biyanzhi.enums.ErrorEnum;
import com.biyanzhi.util.DateUtils;

@Controller
public class PictureCommentController {
	@Autowired
	private CommentDao dao;

	public CommentDao getDao() {
		return dao;
	}

	public void setDao(CommentDao dao) {
		this.dao = dao;
	}

	@ResponseBody
	@RequestMapping(value = "/addcomment.do", method = RequestMethod.POST)
	public String addComment(HttpServletRequest request) {
		String comment_content = request.getParameter("comment_content");
		String reply_someone_name = request.getParameter("reply_someone_name");
		String reply_someone_id = request.getParameter("reply_someone_id");
		int picture_id = Integer.valueOf(request.getParameter("picture_id"));
		String publisher_id = request.getParameter("user_id");
		String publisher_name = request.getParameter("publisher_name");
		String publisher_avatar = request.getParameter("publisher_avatar");
		Comment comment = new Comment();
		comment.setComment_content(comment_content);
		comment.setReply_someone_id(Integer.valueOf(reply_someone_id));
		comment.setReply_someone_name(reply_someone_name);
		comment.setComment_time(DateUtils.getPicturePublishTime());
		comment.setPicture_id(picture_id);
		comment.setPublisher_id(Integer.valueOf(publisher_id));
		comment.setPublisher_avatar(publisher_avatar);
		comment.setPublisher_name(publisher_name);
		int comment_id = dao.insertComment(comment);
		Map<String, Object> params = new HashMap<String, Object>();
		if (comment_id < 0) {
			params.put("err", ErrorEnum.INVALID.name());
			params.put("rt", 0);
		} else {
			params.put("rt", 1);
			params.put("comment_id", comment_id);
		}
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();
	}
}

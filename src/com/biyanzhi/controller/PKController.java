package com.biyanzhi.controller;

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

import com.biyanzhi.bean.PK;
import com.biyanzhi.bean.Picture;
import com.biyanzhi.dao.PKDao;
import com.biyanzhi.enums.ErrorEnum;
import com.biyanzhi.util.DateUtils;

@Controller
public class PKController {
	@Autowired
	private PKDao dao;

	public PKDao getDao() {
		return dao;
	}

	public void setDao(PKDao dao) {
		this.dao = dao;
	}

	@ResponseBody
	@RequestMapping(value = "/addPK.do", method = RequestMethod.POST)
	public String addPK(HttpServletRequest request) {
		int pk1_user_id = Integer.valueOf(request.getParameter("pk1_user_id"));
		String pk1_user_picture = request.getParameter("pk1_user_picture");
		int pk1_ticket_count = Integer.valueOf(request
				.getParameter("pk1_ticket_count"));
		int pk2_user_id = Integer.valueOf(request.getParameter("pk2_user_id"));
		String pk2_user_picture = request.getParameter("pk2_user_picture");
		int pk2_ticket_count = Integer.valueOf(request
				.getParameter("pk2_ticket_count"));
		PK pk = new PK();
		pk.setPk1_ticket_count(pk1_ticket_count);
		pk.setPk1_user_id(pk1_user_id);
		pk.setPk1_user_picture(pk1_user_picture);
		pk.setPk2_ticket_count(pk2_ticket_count);
		pk.setPk2_user_id(pk2_user_id);
		pk.setPk2_user_picture(pk2_user_picture);
		pk.setPk_time(DateUtils.getPicturePublishTime());
		int result = dao.addPK(pk);
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

	@ResponseBody
	@RequestMapping(value = "/getPKList.do", method = RequestMethod.POST)
	public String getPKList(HttpServletRequest request) {
		String pk_time = request.getParameter("pk_time");
		List<PK> lists = new ArrayList<PK>();
		lists.addAll(dao.getPKList(pk_time));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pks", lists);
		params.put("rt", 1);
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		System.out.println("size:" + jsonObjectFromMap.toString());
		return jsonObjectFromMap.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/loadMorePKList.do", method = RequestMethod.POST)
	public String loadMorePKList(HttpServletRequest request) {
		String pk_time = request.getParameter("pk_time");
		List<PK> lists = new ArrayList<PK>();
		lists.addAll(dao.loadMorePKList(pk_time));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pks", lists);
		params.put("rt", 1);
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		System.out.println("size:" + lists.size());
		return jsonObjectFromMap.toString();

	}
}

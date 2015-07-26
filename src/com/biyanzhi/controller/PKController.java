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
import com.biyanzhi.bean.PKVote;
import com.biyanzhi.dao.PKDao;
import com.biyanzhi.dao.PKVoteDao;
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

	@Autowired
	private PKVoteDao voteDao;

	public PKVoteDao getVoteDao() {
		return voteDao;
	}

	public void setVoteDao(PKVoteDao voteDao) {
		this.voteDao = voteDao;
	}

	@ResponseBody
	@RequestMapping(value = "/addPK.do", method = RequestMethod.POST)
	public String addPK(HttpServletRequest request) {
		int pk1_user_id = Integer.valueOf(request.getParameter("pk1_user_id"));
		String pk1_user_gender = request.getParameter("pk1_user_gender");
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
		pk.setPk1_user_gender(pk1_user_gender);
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
		int user_id = Integer.valueOf(request.getParameter("user_id"));
		List<PK> lists = new ArrayList<PK>();
		lists.addAll(dao.getPKList(pk_time));
		for (PK pk : lists) {
			pk.setIs_voted(voteDao.findPKVote(new PKVote(pk.getPk_id(), user_id)) > 0);
		}
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
		int user_id = Integer.valueOf(request.getParameter("user_id"));
		List<PK> lists = new ArrayList<PK>();
		lists.addAll(dao.loadMorePKList(pk_time));
		for (PK pk : lists) {
			pk.setIs_voted(voteDao.findPKVote(new PKVote(pk.getPk_id(), user_id)) > 0);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pks", lists);
		params.put("rt", 1);
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		System.out.println("size:" + lists.size());
		return jsonObjectFromMap.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/upDatePK2.do", method = RequestMethod.POST)
	public String upDatePK2(HttpServletRequest request) {
		int pk2_user_id = Integer.valueOf(request.getParameter("pk2_user_id"));
		int pk_id = Integer.valueOf(request.getParameter("pk_id"));
		String pk2_user_picture = request.getParameter("pk2_user_picture");
		int result = dao.upDatePK2(pk_id, pk2_user_id, pk2_user_picture);
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
	@RequestMapping(value = "/upDatePK2TicketCount.do", method = RequestMethod.POST)
	public String upDatePK2TicketCount(HttpServletRequest request) {
		int pk2_user_id = Integer.valueOf(request.getParameter("pk2_user_id"));
		int pk2_ticket_count = Integer.valueOf(request
				.getParameter("pk2_ticket_count"));
		int pk_id = Integer.valueOf(request.getParameter("pk_id"));
		int user_id = Integer.valueOf(request.getParameter("user_id"));
		int result = dao.upDatePK2TicketCount(pk_id, pk2_ticket_count);
		Map<String, Object> params = new HashMap<String, Object>();
		if (result > 0) {
			params.put("rt", 1);
			if (pk2_ticket_count >= 2) {
				dao.upDatePKState(pk_id, 1, pk2_user_id);
			}
			voteDao.addPKVode(new PKVote(pk_id, user_id));
		} else {
			params.put("rt", 0);
			params.put("err", ErrorEnum.INVALID.name());
		}
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/upDatePK1TicketCount.do", method = RequestMethod.POST)
	public String upDatePK1TicketCount(HttpServletRequest request) {
		int pk1_user_id = Integer.valueOf(request.getParameter("pk1_user_id"));
		int pk1_ticket_count = Integer.valueOf(request
				.getParameter("pk1_ticket_count"));
		int pk_id = Integer.valueOf(request.getParameter("pk_id"));
		int user_id = Integer.valueOf(request.getParameter("user_id"));
		int result = dao.upDatePK1TicketCount(pk_id, pk1_ticket_count);
		Map<String, Object> params = new HashMap<String, Object>();
		if (result > 0) {
			params.put("rt", 1);
			if (pk1_ticket_count >= 2) {
				dao.upDatePKState(pk_id, 1, pk1_user_id);
			}
			voteDao.addPKVode(new PKVote(pk_id, user_id));
		} else {
			params.put("rt", 0);
			params.put("err", ErrorEnum.INVALID.name());
		}
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();

	}
}

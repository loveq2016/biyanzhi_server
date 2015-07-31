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
import com.biyanzhi.bean.User;
import com.biyanzhi.dao.PKDao;
import com.biyanzhi.dao.PKVoteDao;
import com.biyanzhi.dao.UserDao;
import com.biyanzhi.enums.ErrorEnum;
import com.biyanzhi.huanxinImpl.EasemobMessages;
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

	@Autowired
	private UserDao uDao;

	public UserDao getuDao() {
		return uDao;
	}

	public void setuDao(UserDao uDao) {
		this.uDao = uDao;
	}

	@ResponseBody
	@RequestMapping(value = "/tiaoZhanPK.do", method = RequestMethod.POST)
	public String tiaoZhanPK(HttpServletRequest request) {
		int pk1_user_id = Integer.valueOf(request.getParameter("pk1_user_id"));
		String pk1_user_gender = request.getParameter("pk1_user_gender");
		String pk1_user_picture = request.getParameter("pk1_user_picture");
		int pk2_user_id = Integer.valueOf(request.getParameter("pk2_user_id"));
		String pk2_user_picture = request.getParameter("pk2_user_picture");
		User user1 = uDao.findUserByUserID(pk1_user_id);
		User user2 = uDao.findUserByUserID(pk2_user_id);
		if (user2 != null && user1 != null) {
			EasemobMessages.sendTextMessageForTiaoZhan(user2.getUser_chat_id(),
					user1.getUser_name() + " 向你发起了PK挑战", pk1_user_id,
					pk1_user_gender, pk1_user_picture, pk2_user_picture);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rt", 1);
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/receivePK.do", method = RequestMethod.POST)
	public String receivePK(HttpServletRequest request) {
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
			User user1 = uDao.findUserByUserID(pk1_user_id);
			User user2 = uDao.findUserByUserID(pk2_user_id);
			if (user2 != null && user1 != null) {
				EasemobMessages.sendTextMessageForPK(user1.getUser_chat_id(),
						user2.getUser_name() + " 接受了你的PK挑战,快去PK大厅看看吧");
			}
		} else {
			params.put("rt", 0);
			params.put("err", ErrorEnum.INVALID.name());
		}
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/refusePK.do", method = RequestMethod.POST)
	public String refusePK(HttpServletRequest request) {
		int pk1_user_id = Integer.valueOf(request.getParameter("pk1_user_id"));
		int pk2_user_id = Integer.valueOf(request.getParameter("pk2_user_id"));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rt", 1);
		User user1 = uDao.findUserByUserID(pk1_user_id);
		User user2 = uDao.findUserByUserID(pk2_user_id);
		if (user2 != null && user1 != null) {
			EasemobMessages.sendTextMessageForPK(user1.getUser_chat_id(),
					user2.getUser_name() + " 拒绝了你的PK挑战");
		}
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();
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
			List<PKVote> pkVotes = pk.getPkVotes();
			for (PKVote pkVote : pkVotes) {
				if (user_id == pkVote.getVote_user_id()) {
					pk.setIs_voted(true);
				}
			}
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pks", lists);
		params.put("rt", 1);
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
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
			List<PKVote> pkVotes = pk.getPkVotes();
			for (PKVote pkVote : pkVotes) {
				if (user_id == pkVote.getVote_user_id()) {
					pk.setIs_voted(true);
				}
			}
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
		String pk2_user_name = request.getParameter("pk2_user_name");
		int pk1_user_id = Integer.valueOf(request.getParameter("pk1_user_id"));
		int result = dao.upDatePK2(pk_id, pk2_user_id, pk2_user_picture);
		Map<String, Object> params = new HashMap<String, Object>();
		if (result > 0) {
			params.put("rt", 1);
			User user = uDao.findUserByUserID(pk1_user_id);
			if (user != null) {
				String pk1_user_chat_id = user.getUser_chat_id();
				EasemobMessages.sendTextMessageForPK(pk1_user_chat_id,
						pk2_user_name + " 和你PK了,快去PK大厅看看吧");
			}
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
		int pk1_user_id = Integer.valueOf(request.getParameter("pk1_user_id"));
		Map<String, Object> params = new HashMap<String, Object>();
		if (result > 0) {
			params.put("rt", 1);
			User pk2_user = uDao.findUserByUserID(pk2_user_id);
			if (pk2_ticket_count >= 10) {
				dao.upDatePKState(pk_id, 1, pk2_user_id);
				User pk1_user = uDao.findUserByUserID(pk1_user_id);
				if (pk2_user != null && pk2_user != null) {
					PK pk = dao.getPKByPKID(pk_id);
					JSONObject jsonObjectFromMap = JSONObject.fromObject(pk);
					System.out.println(jsonObjectFromMap.toString());
					EasemobMessages
							.sendTextMessageForPKWin(
									pk2_user.getUser_chat_id(), "恭喜你,你在和 "
											+ pk1_user.getUser_name()
											+ " 的PK中取得了胜利",
									jsonObjectFromMap.toString());
					EasemobMessages.sendTextMessageForPK(
							pk1_user.getUser_chat_id(),
							"很遗憾,你在和 " + pk2_user.getUser_name()
									+ " 的PK中失败了,快去打扮一下继续PK TA");
				}
			}
			voteDao.addPKVode(new PKVote(pk_id, user_id));
			if (pk2_user != null) {
				EasemobMessages.sendTextMessageForPK(
						pk2_user.getUser_chat_id(), "有人在你的PK中投票了,快去PK大厅看看吧");
			}
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
		int pk2_user_id = Integer.valueOf(request.getParameter("pk2_user_id"));
		int result = dao.upDatePK1TicketCount(pk_id, pk1_ticket_count);
		Map<String, Object> params = new HashMap<String, Object>();
		if (result > 0) {
			params.put("rt", 1);
			User pk1_user = uDao.findUserByUserID(pk1_user_id);
			if (pk1_ticket_count >= 10) {
				dao.upDatePKState(pk_id, 1, pk1_user_id);
				User pk2_user = uDao.findUserByUserID(pk2_user_id);
				if (pk1_user != null && pk2_user != null) {
					PK pk = dao.getPKByPKID(pk_id);
					JSONObject jsonObjectFromMap = JSONObject.fromObject(pk);
					System.out.println(jsonObjectFromMap.toString());
					EasemobMessages
							.sendTextMessageForPKWin(
									pk1_user.getUser_chat_id(), "恭喜你,你在和 "
											+ pk2_user.getUser_name()
											+ " 的PK中取得了胜利",
									jsonObjectFromMap.toString());
					EasemobMessages.sendTextMessageForPK(
							pk2_user.getUser_chat_id(),
							"很遗憾,你在和 " + pk1_user.getUser_name()
									+ " 的PK中失败了,快去打扮一下继续PK TA");
				}
			}
			voteDao.addPKVode(new PKVote(pk_id, user_id));
			if (pk1_user != null) {
				EasemobMessages.sendTextMessageForPK(
						pk1_user.getUser_chat_id(), "有人在你的PK中投票了,快去PK大厅看看吧");
			}

		} else {
			params.put("rt", 0);
			params.put("err", ErrorEnum.INVALID.name());
		}
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/getPKIngList.do", method = RequestMethod.POST)
	public String getPKIngList(HttpServletRequest request) {
		String pk_time = request.getParameter("pk_time");
		int user_id = Integer.valueOf(request.getParameter("user_id"));
		List<PK> lists = new ArrayList<PK>();
		lists.addAll(dao.getPKIngList(pk_time));
		for (PK pk : lists) {
			List<PKVote> pkVotes = pk.getPkVotes();
			for (PKVote pkVote : pkVotes) {
				if (user_id == pkVote.getVote_user_id()) {
					pk.setIs_voted(true);
				}
			}
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pks", lists);
		params.put("rt", 1);
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		System.out.println("pk_ing:" + jsonObjectFromMap.toString());
		return jsonObjectFromMap.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/loadPKIngMorePKList.do", method = RequestMethod.POST)
	public String loadPKIngMorePKList(HttpServletRequest request) {
		String pk_time = request.getParameter("pk_time");
		int user_id = Integer.valueOf(request.getParameter("user_id"));
		List<PK> lists = new ArrayList<PK>();
		lists.addAll(dao.loadPKIngMorePKList(pk_time));
		for (PK pk : lists) {
			List<PKVote> pkVotes = pk.getPkVotes();
			for (PKVote pkVote : pkVotes) {
				if (user_id == pkVote.getVote_user_id()) {
					pk.setIs_voted(true);
				}
			}
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pks", lists);
		params.put("rt", 1);
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		return jsonObjectFromMap.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/getPKFinishedList.do", method = RequestMethod.POST)
	public String getPKFinishedList(HttpServletRequest request) {
		String pk_time = request.getParameter("pk_time");
		int user_id = Integer.valueOf(request.getParameter("user_id"));
		List<PK> lists = new ArrayList<PK>();
		lists.addAll(dao.getPKFinishedList(pk_time));
		for (PK pk : lists) {
			List<PKVote> pkVotes = pk.getPkVotes();
			for (PKVote pkVote : pkVotes) {
				if (user_id == pkVote.getVote_user_id()) {
					pk.setIs_voted(true);
				}
			}
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pks", lists);
		params.put("rt", 1);
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		System.out.println("size:" + jsonObjectFromMap.toString());
		return jsonObjectFromMap.toString();

	}

	@ResponseBody
	@RequestMapping(value = "/loadPKFinishedMorePKList.do", method = RequestMethod.POST)
	public String loadPKFinishedMorePKList(HttpServletRequest request) {
		String pk_time = request.getParameter("pk_time");
		int user_id = Integer.valueOf(request.getParameter("user_id"));
		List<PK> lists = new ArrayList<PK>();
		lists.addAll(dao.loadPKFinishedMorePKList(pk_time));
		for (PK pk : lists) {
			List<PKVote> pkVotes = pk.getPkVotes();
			for (PKVote pkVote : pkVotes) {
				if (user_id == pkVote.getVote_user_id()) {
					pk.setIs_voted(true);
				}
			}
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pks", lists);
		params.put("rt", 1);
		JSONObject jsonObjectFromMap = JSONObject.fromObject(params);
		System.out.println("size:" + jsonObjectFromMap.toString());
		return jsonObjectFromMap.toString();

	}
}

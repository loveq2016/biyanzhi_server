package com.biyanzhi.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.biyanzhi.bean.User;
import com.biyanzhi.dao.UserDao;
import com.biyanzhi.enums.ErrorEnum;

public class UserLoginFilter implements Filter {

	// @Autowired
	// private UserDao uDao;
	//
	// public UserDao getuDao() {
	// return uDao;
	// }
	//
	// public void setuDao(UserDao uDao) {
	// this.uDao = uDao;
	// }

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(req.getSession()
						.getServletContext());
		UserDao uDao = (UserDao) wac.getBean("userDaoImpl");
		String str_user_id = request.getParameter("user_id");
		if (str_user_id == null) {
			chain.doFilter(request, response);
		} else {
			int user_id = Integer.valueOf(str_user_id);
			User user = uDao.findUserByUserID(user_id);
			if (user != null) {
				if (user.getUser_state() == -1) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("err", ErrorEnum.INVALID.name());
					params.put("rt", 0);
					JSONObject jsonObjectFromMap = JSONObject
							.fromObject(params);
					response.getWriter().print(jsonObjectFromMap.toString());
				} else {
					chain.doFilter(request, response);// ¼ÌÐøÍùºóÖ´ÐÐ
				}
			} else {
				chain.doFilter(request, response);
			}
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}

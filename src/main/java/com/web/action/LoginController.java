package com.web.action;

import com.alibaba.fastjson.JSONObject;
import com.web.core.action.BaseController;
import com.web.entity.User;
import com.web.util.AllResult;
import com.web.util.MD5;
import com.web.util.StringUtil;
import com.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录
 * 
 * @author qgl
 * 
 */
@Controller
public class LoginController extends BaseController {

	/**
	 * web网页登录
	 * 
	 * @param username
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 */
	//TODO  后期将 userName 修改为username
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	@ResponseBody
	public Object login(String userName, String password, HttpServletRequest request, HttpServletResponse response) {
		System.out.println(request.getParameterMap());
		User user = new User();
		if (StringUtil.isEmpty(userName, password)) {
			//request.setAttribute("errorMsg", "用户名或密码不能为空！");
			return AllResult.build(0,"用户名或密码不能为空");
		} else {
			// TODO 后期需要修改
			// User user = userService.getUserByName(username);
			// if (null != user && null != user.getPassword() &&
			// user.getPassword().equals(password)) {
			// request.setAttribute("user", user);
			// return "index.jsp";
			// } else {
			// request.setAttribute("errorMsg", "用户名或密码错误！");
			// }

			user.setUsername(userName);
			user.setPassword(MD5.MD5Encode(password));
			WebUtils.addUser(request, user);
		}

		return AllResult.okJSON(user);
	}

	/**
	 * 退出登录
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/loginOut")
	@ResponseBody
	public Object loginOut(HttpServletRequest request, HttpServletResponse response) {
		WebUtils.removeUser(request);
		return AllResult.build(1,"退出登录成功");
	}

	/**
	 * APP登录
	 * 
	 * @param username
	 * @param password
	 * @param request
	 * @param response
	 */
	@RequestMapping("/app/login")
	public void appLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
		JSONObject obj = new JSONObject();
		if (StringUtil.isEmpty(username, password)) {
			obj.put("msg", "用户名或密码不能为空！");
			obj.put("status", "false");
		} else {
			User user = userService.getUserByName(username);
			if (null != user && null != user.getPassword() && user.getPassword().equals(password)) {
				obj.put("msg", "登录成功！");
				obj.put("status", "true");
				request.setAttribute("user", user);
			} else {
				obj.put("msg", "用户名或密码错误！");
				obj.put("status", "false");

			}
		}
		super.writerResponse(obj.toString(), response);
	}
}

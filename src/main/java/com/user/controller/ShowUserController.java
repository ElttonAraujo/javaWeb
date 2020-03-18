package com.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.UserDao;
import com.user.dao.UserService;
import com.user.model.User;

public class ShowUserController implements Controller{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(req.getParameter("id"));
		User user =  new UserService().findById(id);
		req.setAttribute("user", user);
		return "WEB-INF/jsp/show-user.jsp";
	}

}

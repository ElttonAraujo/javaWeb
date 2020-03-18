package com.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.UserDao;
import com.user.dao.UserService;

public class RemoveUserController implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(req.getParameter("id"));
		UserService userService =  new UserService();
		userService.delete(id);
		return "mvc?controller=ListUserController";
	}
	
	

}

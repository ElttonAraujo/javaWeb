package com.user.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.user.dao.UserDao;
import com.user.dao.UserService;
import com.user.model.User;


public class ListUserController implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		
		UserService userService =  new UserService();
		List<User> users =  userService.findAll();
		req.setAttribute("users", users);
		
		return "/WEB-INF/jsp/list-users.jsp";
	}
	
	

}

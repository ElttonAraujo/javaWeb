package com.user.controller;

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
		System.out.println(users.get(0).getTelephones().get(0).getNumber());
		req.setAttribute("users", users);
		return "/WEB-INF/jsp/list-users.jsp";
	}

}

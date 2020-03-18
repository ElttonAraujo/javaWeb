package com.user.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.user.dao.UserDao;
import com.user.dao.UserService;
import com.user.model.Telephone;
import com.user.model.User;


public class AddUserController implements Controller{
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub

		User user = new User();
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		List<Telephone> telephones = new ArrayList<Telephone>();
		String[] ddd = req.getParameterValues("ddd[]");
		String[] number = req.getParameterValues("number[]");
		String[] type = req.getParameterValues("type[]");
		
		
		for(int i = 0; i<ddd.length;i++) {
			int dddNumber = Integer.parseInt(ddd[i]);
			Telephone telephone = new Telephone(dddNumber, number[i], type[i]);
			telephone.setUser(user);
			telephones.add(telephone);
		}
		
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setTelephones(telephones);
		
		
		new UserService().add(user);
		return "mvc?controller=ListUserController"; 
	}


}

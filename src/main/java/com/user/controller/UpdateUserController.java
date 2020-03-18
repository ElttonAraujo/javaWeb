package com.user.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.UserDao;
import com.user.dao.UserService;
import com.user.model.Telephone;
import com.user.model.User;

public class UpdateUserController implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		User user = new UserService().findById(Integer.parseInt(req.getParameter("id")));
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		

		String[] ddd = req.getParameterValues("ddd[]");
		String[] number = req.getParameterValues("number[]");
		String[] type = req.getParameterValues("type[]");
		
		
		for(int i = 0; i<ddd.length;i++) {
			int dddNumber = Integer.parseInt(ddd[i]);
			if(ddd.length > user.getTelephones().size() && i>0) {
				Telephone telephone = new Telephone(dddNumber, number[i], type[i]);
				telephone.setUser(user);
				user.getTelephones().add(telephone);
			}
			else if(ddd.length < user.getTelephones().size()) {
				user.getTelephones().remove(i+1);
			}
			user.getTelephones().get(i).setDdd(dddNumber);
			user.getTelephones().get(i).setNumber(number[i]);
			user.getTelephones().get(i).setType(type[i]);
		}
		
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		
		new UserService().update(user);
		return "mvc?controller=ListUserController";
	}

}

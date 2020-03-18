package com.user.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.dao.UserService;
import com.user.model.Telephone;
import com.user.model.User;

@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		RequestDispatcher reDispatcher = req.getRequestDispatcher("mvc?controller=AddUserController");
//		reDispatcher.forward(req, resp);
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
		

	}
	
}

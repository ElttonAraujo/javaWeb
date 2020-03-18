package com.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mvc")
public class ControllerServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String parameter = req.getParameter("controller");
		String className = "com.user.controller."+parameter;
		
		try {
			Class classRequest = Class.forName(className);
			Controller controller = (Controller) classRequest.newInstance();
			String page = controller.execute(req, resp);
			req.getRequestDispatcher(page).forward(req, resp);;
		}catch (Exception e) {
			// TODO: handle exception
			throw new ServletException("Controller error", e);
		}
	}

}

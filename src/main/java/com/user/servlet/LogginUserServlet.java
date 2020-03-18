package com.user.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user.dao.UserDao;
import com.user.dao.UserService;
import com.user.model.Telephone;
import com.user.model.User;

@WebServlet("/login")
public class LogginUserServlet extends HttpServlet {
	
	private UserDao userDao;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		
		Telephone telephone = new Telephone(81,"00000000", "cel");
		Telephone telephone2 = new Telephone(81,"11111111", "cel");
		
		List<Telephone> telephones = new ArrayList<Telephone>();
		telephones.add(telephone);
		telephones.add(telephone2);
		
		User user = new User("adminstrator", "admin", "admin", telephones);
		telephone.setUser(user);
		new UserService().add(user);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.sendRedirect("login.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
            authenticate(req, resp);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	 private void authenticate(HttpServletRequest req, HttpServletResponse resp)
			    throws Exception {
		// TODO Auto-generated method stub
		 User user = new User();
		 user.setEmail(req.getParameter("email"));
		 user.setPassword(req.getParameter("password"));
		 
		 if(new UserService().isUser(user)) {
			 HttpSession session = req.getSession();
			 session.setAttribute("logado", user.getEmail());
			 RequestDispatcher requestDispatcher =  req.getRequestDispatcher("mvc?controller=ListUserController");
			 requestDispatcher.forward(req, resp);
		 }else {
			 String error = "User or password wrong";
			 req.setAttribute("error", error);
			 RequestDispatcher requestDispatcher =  req.getRequestDispatcher("login.jsp");
			 requestDispatcher.forward(req, resp);
		 }
		
	}
	

}

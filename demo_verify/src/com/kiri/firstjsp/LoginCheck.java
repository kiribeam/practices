package com.kiri.firstjsp;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kiri.model.SignOperation;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/login")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/ProtectedFile/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if(request.getAttribute("inject")!=null) {
			Object blackCount = session.getAttribute("BlackCount");
			if(blackCount==null){
				session.setAttribute("BlackCount", 1);
			}else{
				session.setAttribute("BlackCount", (int)blackCount + 1);
			}
			session.setAttribute("login", false);
			request.setAttribute("wrongInput", true);
			request.getRequestDispatcher("/WEB-INF/ProtectedFile/Login.jsp").forward(request, response);
		    return;
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//check password flag
		boolean flag;
		try{
			flag = new SignOperation().checkUser(username, password);
		}catch(Exception e){
			throw new ServletException();
		}
		if(flag){
			session.setAttribute("username", username);
			session.setAttribute("login", true);
			request.removeAttribute("wrongInput");
		    Cookie uname= new Cookie("username", username);
		    uname.setMaxAge(60*60*24*3);
		    response.addCookie(uname);
		    response.setContentType("text/html;charset=UTF-8");
		    response.getWriter().write("Log in Success!");
		    response.setHeader("refresh", "2;url=/index.html");
		}else{
			session.setAttribute("login", false);
			request.setAttribute("wrongInput", true);
			request.getRequestDispatcher("/WEB-INF/ProtectedFile/login.jsp").forward(request, response);
		}
	}
}

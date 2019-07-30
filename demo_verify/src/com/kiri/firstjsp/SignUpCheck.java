package com.kiri.firstjsp;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kiri.model.SignOperation;

/**
 * Servlet implementation class SignUpCheck
 */
@WebServlet("/signUp")
public class SignUpCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/ProtectedFile/signUp.jsp").forward(request, response);
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
			request.getRequestDispatcher("/WEB-INF/ProtectedFile/signUp.jsp").forward(request, response);
		    return;
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String checkpass= request.getParameter("checkpass");
		String mail = request.getParameter("mail");
		if(mail == null) mail="null";
		boolean illegalFlag = false; 
		if(!password.equals(checkpass)) illegalFlag = true;
		if(illegalFlag){
		    request.setAttribute("wrongInput", true);
			request.getRequestDispatcher("/WEB-INF/ProtectedFile/signUp.jsp").forward(request, response);
		    return;
		}
		int result = -1;
		try{
			result = new SignOperation().signUp(username, password, mail);
		}catch(Exception e){
			throw new ServletException();
		}
		if(result==-1){
			throw new ServletException();
		}else if(result==0){
		    request.removeAttribute("wrongInput");
		    request.setAttribute("dup", true);
			request.getRequestDispatcher("/WEB-INF/ProtectedFile/signUp.jsp").forward(request, response);
		    return;
		}
	    session.setAttribute("username", username);
	    session.setAttribute("login", false);
	    request.removeAttribute("wrongInput");
	    request.removeAttribute("dup");
	    Cookie cookie = new Cookie("username", username);
	    cookie.setMaxAge(60*60*24*3);
	    response.addCookie(cookie);
	    response.setContentType("text/html;charset=UTF-8");
	    response.getWriter().write("Sign up Success!");
	    response.setHeader("refresh", "2;url=/index.html");
	}

}

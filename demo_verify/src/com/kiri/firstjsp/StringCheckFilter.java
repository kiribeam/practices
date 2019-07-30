package com.kiri.firstjsp;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

/**
 * Servlet Filter implementation class StringCheckFilter
 */
public class StringCheckFilter implements Filter {

    /**
     * Default constructor. 
     */
    public StringCheckFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		if(username==null || password==null
				|| username.equals("") || password.equals("")){
			if(((HttpServletRequest)request).getMethod().equals("POST"))
				request.setAttribute("inject", true);
		}else if(username.length()>20 || password.length()>20){
			request.setAttribute("inject", true);
		}else if(!username.matches("[a-zA-Z0-9]+")){
			request.setAttribute("inject", true);
		}else if(mail != null){
			if(mail.length()>30 || !mail.matches("\\w+(\\.\\w)*@\\w+(\\.\\w+){1,3}")){
				request.setAttribute("inject", true);
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

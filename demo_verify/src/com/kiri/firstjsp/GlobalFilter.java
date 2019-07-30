package com.kiri.firstjsp;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

/**
 * Servlet Filter implementation class GlobalFilter
 */
public class GlobalFilter implements Filter {

    /**
     * Default constructor. 
     */
    public GlobalFilter() {
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
		HttpSession session = ((HttpServletRequest) request).getSession(true);
		if(session.isNew() || session.getAttribute("login") == null || ! (boolean)session.getAttribute("login")){
			session.setAttribute("login", false);
			HttpServletResponse res = (HttpServletResponse) response;
			res.setStatus(res.SC_MOVED_TEMPORARILY);
		    res.setHeader("LOCATION","/login");
			return;
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

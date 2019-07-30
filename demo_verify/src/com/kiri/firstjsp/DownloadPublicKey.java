package com.kiri.firstjsp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DownloadPublicKey
 */
@WebServlet("/function/downloadPublicKey")
public class DownloadPublicKey extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DOWNLOAD_DIR= "download\\pubkey";
	private static final String REAL_PATH = "E:\\code\\eclipse_workspaces\\demo_verify\\WebContent\\WEB-INF\\ProtectedFile";
	private static final String PUB_KEY = "server.pub.asc";
	     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadPublicKey() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = REAL_PATH + File.separator + DOWNLOAD_DIR + File.separator + PUB_KEY;	
		File file = new File(path);
		if(!file.exists()){
		    response.setContentType("text/html;charset=UTF-8");
		    response.getWriter().write("Error!!");
		    response.setHeader("refresh", "2;url=/function");	
		    return ;
		}
		//encrypt it with aes and sign it with pkcs
		//download begin 
		//careful to change here!
		//next use PGP!!!
		FileInputStream fis = new FileInputStream(file);
		byte[] buff = new byte[1024];
		
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=" + PUB_KEY);
		ServletOutputStream out = response.getOutputStream();
		while(fis.read(buff) > 0){
			out.write(buff);
		}
		fis.close();
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

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
 * Servlet implementation class DownLoadFile
 */
@WebServlet("/function/download")
public class DownLoadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String UPLOAD_DIR = "upload\\encrypt_file";
	private static final String REAL_PATH = "E:\\code\\eclipse_workspaces\\demo_verify\\WebContent\\WEB-INF\\ProtectedFile";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownLoadFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//if file not exist
		HttpSession session = request.getSession();
		String username = "";
		username = (String) session.getAttribute("username");
		String path = REAL_PATH + File.separator + UPLOAD_DIR + File.separator +
				username + "." + "hex";
		File file = new File(path);
		if(!file.exists()){
		    response.setContentType("text/html;charset=UTF-8");
		    response.getWriter().write("No file uploaded yet!!");
		    response.setHeader("refresh", "2;url=/function/upload");	
		    return ;
		}
		//encrypt it with aes and sign it with pkcs
		//download begin 
		//careful to change here!
		//next use PGP!!!
		FileInputStream fis = new FileInputStream(file);
		byte[] buff = new byte[1024];
		
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=" +
				username + "." + "encrypt");
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

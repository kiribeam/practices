package com.kiri.firstjsp;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UpLoadFile
 */
@WebServlet("/function/upload")
public class UpLoadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String UPLOAD_DIR = "upload\\encrypt_file";
	private static final String REAL_PATH = "E:\\code\\eclipse_workspaces\\demo_verify\\WebContent\\WEB-INF\\ProtectedFile";
	
	private static final int MEMERY_THRESHOLD = 1024 * 1024 * 3;
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 10;
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 15;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpLoadFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/ProtectedFile/upload.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(!ServletFileUpload.isMultipartContent(request)){
		    response.setContentType("text/html;charset=UTF-8");
		    response.getWriter().write("Error upload");
		    response.setHeader("refresh", "2;url=/index.html");
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(MEMERY_THRESHOLD);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(MAX_FILE_SIZE);
		upload.setSizeMax(MAX_REQUEST_SIZE);
		upload.setHeaderEncoding("UTF-8");
		String uploadPath = REAL_PATH + File.separator + UPLOAD_DIR;
		File uploadDir = new File(uploadPath);
		//System.out.println(uploadPath);
		if(!uploadDir.exists())
			uploadDir.mkdirs();
		
		HttpSession session = request.getSession();
		
		try {
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String fileName = (String) session.getAttribute("username") + ".hex";
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        //System.out.println(filePath);
                        item.write(storeFile);
                        break;
                    }
                }
            }
		    response.setContentType("text/html;charset=UTF-8");
		    response.getWriter().write("Upload Success!");
		    response.setHeader("refresh", "2;url=/function");
        } catch (Exception ex) {
		    response.setContentType("text/html;charset=UTF-8");
		    response.getWriter().write("Upload Failed!!");
		    response.setHeader("refresh", "2;url=/function");
        }
		
	}

}

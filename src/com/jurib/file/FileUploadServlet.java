package com.jurib.file;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.*;
import java.util.*;


@WebServlet("/fileUpload.do")
 public class FileUploadServlet extends javax.servlet.http.HttpServlet {
   static final long serialVersionUID = 1L;
   
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{
			String saveDir = "uploadFileSave";
			String saveFullDir = getServletContext().getRealPath(saveDir);
			int maxFileSize = 5*1024*1024;
			//int maxFileSize = 5*1024;
			String encoding = "utf-8";
			
			MultipartRequest mRequest = null;
		 	//mRequest = new MultipartRequest(request,saveFullDir,maxFileSize,encoding,		new DefaultFileRenamePolicy());
		 	mRequest = new MultipartRequest(request,saveFullDir,maxFileSize,encoding,
		 			new DateTimeFileReNamePolicy());
		 	
		 	String user = mRequest.getParameter("user");
		 	String content = mRequest.getParameter("content"); 
		 	
		 	
		 	System.out.println("작성자 : "+user);
		 	System.out.println("내용 : "+content);

		 	
		 	String contentType = mRequest.getContentType("uploadFile");
		 	String originalFileName = mRequest.getOriginalFileName("uploadFile");
		 	String filesystemName = mRequest.getFilesystemName("uploadFile");

		 	System.out.println("content type : "+contentType);
		 	System.out.println("originalFileName : "+originalFileName);
		 	System.out.println("filesystemName : "+filesystemName);
		 	
	 		request.setAttribute("user",user);
	 		request.setAttribute("content", content);
	 		request.setAttribute("fileName", filesystemName);
	 		request.setAttribute("originalFileName", originalFileName);
	 		request.setAttribute("contentType", contentType);
	 		
	 		RequestDispatcher rd = request.getRequestDispatcher("uploadResult.jsp");
	 		rd.forward(request, response);
	 		
		}catch(Exception e){
			System.out.println(e.getMessage());
			response.sendRedirect("fileSizeError.jsp");
		}		 		
	}   	  	    
}
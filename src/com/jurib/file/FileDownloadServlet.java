package com.jurib.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fileDownload.do")
public class FileDownloadServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fileName = request.getParameter("fileName");
		String originalFileName = request.getParameter("originalFileName");

		String saveFullDir = getServletContext().getRealPath("uploadFileSave");
		String fullFileName = saveFullDir + File.separator + fileName;

		System.out.println(fullFileName + "//" + originalFileName);
		File file = new File(fullFileName);

		response.setHeader("Content-Type", "doesn/mater;charset=utf-8");
//        response.setHeader("Content-Disposition", "attachment;filename="+java.net.URLDecoder.decode(request.getParameter("originalFileName"), "UTF-8")+";");
		response.setHeader("Content-Disposition",
				"attachment;filename=" + getDisposition(originalFileName, getBrowser(request)) + ";");

		FileInputStream in = new FileInputStream(file);
		ServletOutputStream out = response.getOutputStream();

		byte buffer[] = new byte[1024];
		int count = 0;

		while ((count = in.read(buffer, 0, buffer.length)) != -1) {
			out.write(buffer, 0, count);
		}

		in.close();
		out.close();
	}

	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		} else if (header.indexOf("Trident/7.0") > -1) {
			// IE 11 이상 //IE 버전 별 체크 >> Trident/6.0(IE 10) , Trident/5.0(IE 9) ,
			// Trident/4.0(IE 8)
			return "MSIE";
		}
		return "Firefox";
	}

	private String getDisposition(String filename, String browser) throws UnsupportedEncodingException {
		String encodedFilename = null;
		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		}
		return encodedFilename;
	}

}
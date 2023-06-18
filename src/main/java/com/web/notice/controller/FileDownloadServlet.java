package com.web.notice.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fileDownload.do")
public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FileDownloadServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String fileName = request.getParameter("fileName");
		String path = getServletContext().getRealPath("/upload/notice/") + fileName;
		File file = new File(path);
		
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		// 한글 파일명 인코딩
		String fileRename = "";
		
		// 웹 브라우저별로 인코딩 방식이 다르기 때문에 어떤 웹 브라우저인지 알아야 함 (IE와 그 외의 브라우저)
		String header = request.getHeader("user-agent");
		boolean isMSIE = header.indexOf("MSIE") != -1 || header.indexOf("Trident") != -1; // IE에 들어 있는 정보 (MSIE, Trident)
		
		if (isMSIE) {
			fileRename = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20"); // +를 띄어쓰기(%20)로 변환
		} else {
			fileRename = new String(fileName.getBytes("UTF-8"), "ISO-8859-1"); // 바인딩된 파일명을 UTF-8에 맞춰 byte 단위로 바꾼 다음 이를 다시 ISO-8859-1 인코딩 규격에 맞춰서 문자열로 변환
		}
		
		// 응답 헤더의 contentType 설정 (텍스트가 아니라 파일을 응답)
		response.setContentType("application/octet-stream"); // 파일 형식이 지정되지 않은 불특정 파일임을 나타낼 때 사용
		response.setHeader("Content-disposition", "attachment; filename=" + fileRename); // attachment를 사용하면 파일 다운로드 시도할 때 다운로드 창이 나타남
		
		// 클라이언트의 출력스트림을 가져와서 사용자에게 파일 보내기
		ServletOutputStream sos = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(sos);
		
		int read = -1;
		while ((read = bis.read()) != -1) {
			bos.write(read);
		}
		
		bis.close();
		bos.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doGet(request, response);
	}

}

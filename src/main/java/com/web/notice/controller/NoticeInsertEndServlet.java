package com.web.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.web.notice.model.dto.Notice;

@WebServlet("/notice/noticeInsertEnd.do")
public class NoticeInsertEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeInsertEndServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		// 1) 업로드한 파일이 multipart/form-data 형식의 요청인지 확인
		if (!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "잘못된 접근입니다. 관리자에게 문의하세요.");
			request.setAttribute("log", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}
		
		// 2) 업로드한 파일 처리하기
		// 클래스 MultiplartRequest를 생성하면 자동으로 request에 담겨 있는 데이터 (file)을 지정된 위치에 저장함
		// 생성 시 필요한 매개변수
		// - 클래스 HttpServletRequest 객체
		
		// - 파일을 저장할 위치: String 타입으로 절대 경로 반환
		String path = getServletContext().getRealPath("/upload/notice"); // notice 폴더 안 만들면 null 반환
		
		// - 업로드할 파일의 최대 크기: int 타입으로 byte 단위 반환 (1024 * 1024 * 10 => 최대 10MB)
		int maxSize = 1024 * 1024 * 10;
		
		// - 인코딩 방식: String 타입으로 utf-8 입력
		String encode = "UTF-8"; // 대소문자 구분 안 함
		
		// - rename 규칙: cos.jar가 제공하는 클래스 DefaultFileRenamePolicy 이용 (재정의해서 쓸 수 있음)
		DefaultFileRenamePolicy dfrp = new DefaultFileRenamePolicy();
		
		// 객체 생성과 동시에 지정된 위치에 업로드한 파일 저장
		MultipartRequest mr = new MultipartRequest(request, path, maxSize, encode, dfrp);
		
		// 나머지 request에 바인딩된 데이터 처리하기
		// String noticeTitle = mr.getParameter("noticeTitle");
		// String noticeWriter = mr.getParameter("noticeWriter");
		// String noticeContent = mr.getParameter("noticeContent");
		// String OriginalFileName = mr.getOriginalFileName("upFile");
		// String fileRename = mr.getFilesystemName("upFile");
		
		Notice notice = Notice.builder()
								.noticeTitle(mr.getParameter("noticeTitle"))
								.noticeWriter(mr.getParameter("noticeWriter"))
								.noticeContent(mr.getParameter("noticeContent"))
								.filePath(mr.getFilesystemName("upFile"))
								.build();
		
		int result new NoticeServie().insertNotice(notice);
		String msg = "공지사항 등록 완료";
		String loc = "/notice/noticeList.do";

		if (result == 0) {
			msg = "공지사항 등록 실패";
			loc = "/notice/insertForm.do";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doGet(request, response);
	}

}

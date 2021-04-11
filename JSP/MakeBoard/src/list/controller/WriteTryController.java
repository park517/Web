package list.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.model.BoardDAO;
import member.model.Member;
@WebServlet("/list/write/try")
public class WriteTryController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		//파일 업로드 폴더 위치
		
		String directory  =("C:/board/upload/");
		int maxSize = 1024 * 1024 * 100; //파일 최대 크기
		String encoding = "UTF-8";
		MultipartRequest multipartRequest =
				new MultipartRequest(req, directory, maxSize, encoding,
						new DefaultFileRenamePolicy());

		//넣은 변수들 정리하기
		BoardDAO dao = new BoardDAO();
		
		//파일 명
		
		String fileName = multipartRequest.getOriginalFileName("file");
		String fileRealName = multipartRequest.getFilesystemName("file");
		
		// 로그인 세션 불러오기
		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("login");
		String id = member.getMid();
		String writer = member.getMname();
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");
		SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
		Date time = new Date();
		String bdate = format.format(time);
		dao.InsertBoard(id, title, content, writer, bdate,fileName,fileRealName);
		resp.sendRedirect("/list");
	
	}
	
}

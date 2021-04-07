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

import board.model.BoardDAO;
import member.model.Member;
@WebServlet("/list/write/try")
public class WriteTryController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		//넣은 변수들 정리하기
		BoardDAO dao = new BoardDAO();
		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("login");
		String id = member.getMid();
		String writer = member.getMname();
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
		Date time = new Date();
		String bdate = format.format(time);
		dao.InsertBoard(id, title, content, writer, bdate);
		resp.sendRedirect("/list");
	
	}
	
}

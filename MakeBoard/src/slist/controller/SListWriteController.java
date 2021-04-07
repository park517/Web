package slist.controller;

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

import member.model.Member;
import sboard.model.SboardDAO;
@WebServlet("/list/comment/write")
public class SListWriteController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		Member member = (Member)session.getAttribute("login");
		int bid = Integer.parseInt(req.getParameter("bid"));
		String mid = member.getMid();
		String swriter = member.getMname();
		String scontent = req.getParameter("comment");
		SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
		Date time = new Date();
		String sdate = format.format(time);
		SboardDAO sdao = new SboardDAO();
		sdao.InsertComment(bid, mid, scontent, swriter, sdate);
		resp.sendRedirect("/list/detail?bid="+bid);

	}
}

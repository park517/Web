package slist.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sboard.model.SboardDAO;
@WebServlet("/list/comment/update")
public class SListUpdateController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		int bid = Integer.parseInt(req.getParameter("bid"));
		int sid = Integer.parseInt(req.getParameter("sid"));
		String scontent = req.getParameter("scontent");
		SboardDAO sdao = new SboardDAO();
		sdao.UpdateCommnet(scontent, sid);
		resp.sendRedirect("/list/detail?bid="+bid);
		
	}
	
	


	
	
	
}

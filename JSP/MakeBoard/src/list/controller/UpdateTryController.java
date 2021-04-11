package list.controller;

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

import board.model.BoardDAO;
import board.model.BoardVo;
@WebServlet("/list/update/try")
public class UpdateTryController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		BoardDAO dao = new BoardDAO();
		int bid = Integer.parseInt(req.getParameter("bid"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		dao.UpdateBoard(bid, title, content);
		resp.sendRedirect("/list/detail?bid="+bid);
		
	}
}

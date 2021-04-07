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
@WebServlet("/list/update")
public class UpdateController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int bid = Integer.parseInt(req.getParameter("id"));
		BoardDAO dao = new BoardDAO();
		dao.getBoard(bid);
		BoardVo board = dao.getBoard(bid);
		req.setAttribute("list", board);
		req.getRequestDispatcher("/WEB-INF/view/Update.jsp").forward(req, resp);
		
	}
}

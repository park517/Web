package list.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVo;

@WebServlet("/list")
public class ListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		List<BoardVo> list = new ArrayList<>();
		
		BoardDAO dao = new BoardDAO();
		list= dao.getBoardList();
		System.out.println(list);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/WEB-INF/view/List.jsp").forward(req, resp);
		
	}
}

package list.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVo;
import sboard.model.SboardVO;
@WebServlet("/list/detail")
public class ListDetailController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		int bid = Integer.parseInt(req.getParameter("bid"));
		List<SboardVO> slist = new ArrayList<SboardVO>();
		BoardDAO dao = new BoardDAO();
		BoardVo board = dao.getBoard(bid);
		slist = dao.getComment(bid);
		req.setAttribute("list", board);
		req.setAttribute("slist", slist);
		req.getRequestDispatcher("/WEB-INF/view/Detail.jsp").forward(req, resp);
		
		
		
	}
	
}

package login.controller;

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

import member.model.MemberDAO;
@WebServlet("/login")
public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<String>id_list = new ArrayList<String>();
		MemberDAO dao = new MemberDAO();
		
		
		// DAO로 부터 아이디만 불러와서 id_list에 저장하여 JSP로 넘긴다.
		id_list = dao.getmidList();
		req.setAttribute("id_list",id_list);
		req.getRequestDispatcher("/WEB-INF/view/Login.jsp").forward(req, resp);
		

	
	}
}

package register.controller;

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
@WebServlet("/register")
public class RegisterController extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	List<String> id_list = new ArrayList<String>();
	MemberDAO dao = new MemberDAO();
	id_list = dao.getmidList();
	
	req.setAttribute("id_list", id_list);
	req.getRequestDispatcher("/WEB-INF/view/Register.jsp").forward(req, resp);
	
}
}

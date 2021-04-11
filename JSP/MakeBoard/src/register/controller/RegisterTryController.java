package register.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.MemberDAO;

@WebServlet("/register/try")
public class RegisterTryController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			req.setCharacterEncoding("UTF-8");
			String mid = req.getParameter("mid");
			String mpassword = req.getParameter("mpassword");
			String mname =req.getParameter("mname");
			String memail = req.getParameter("memail");
			String mphone = req.getParameter("mphone");
			MemberDAO dao = new MemberDAO();
			dao.Register(mid, mpassword, mname, memail, mphone);
	
			resp.sendRedirect("/login");
		
	}

}

package login.controller;

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
import javax.servlet.http.HttpSession;

import member.model.Member;
import member.model.MemberDAO;
@WebServlet("/login/try")
public class LoginTryController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		MemberDAO dao = new MemberDAO();
		String mid = req.getParameter("mid");
		String mpassword = req.getParameter("mpassword");
		Member member = null;
		member = dao.getMember(mid, mpassword);

		
		// 로그인 성공 시 해당 해당 id password에 해당하는 멤버 정보들을 세션에 저장해 사용자가 이용하는동안 로그인이 유지되게 한다.
		if( member.getMpassword() !=null && member.getMpassword().equals(mpassword)) {
			HttpSession session = req.getSession();
			session.removeAttribute("check");
			session.setAttribute("login", member);
			resp.sendRedirect("/list");
		}
		
		else {
			HttpSession session = req.getSession();
			session.setAttribute("check", 0);
			resp.sendRedirect("/login");
		}
		
		

	}
}

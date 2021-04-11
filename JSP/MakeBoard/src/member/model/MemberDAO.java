package member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class MemberDAO {
	
	// 커넥션 가져오기
	private Connection getConnection() {
		
		String str = "jdbc:oracle:thin:@localhost:1521:orcl"; // 사무실 번지
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(str, "board", "b102030");
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("DB 연결 문제 입니다.");
		}
		return con;
	}
	
	// id 목록 가져오기
	
	public List<String> getmidList() {
		List<String>id_list = new ArrayList<String>();
		Connection con = getConnection();

		ResultSet rs;
		try {
			PreparedStatement pstmt = con.prepareStatement("select mid from member");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				id_list.add(rs.getString("mid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id_list;
	}
	
	// 멤버 목록 가져오기
	
	public Member getMember(String mid , String mpassword) {
		Connection con = getConnection();
		Member member = null;
		try {
			PreparedStatement pstmt =
					con.prepareStatement("select * from member where mid=? ");
			pstmt.setString(1,mid);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				member = new Member(
						rs.getString("mid"),
						rs.getString("mpassword"),
						rs.getString("mname"),
						rs.getString("memail"),
						rs.getString("mphone")
						);
			}
			rs.close();
			pstmt.close();
		    con.close();
		    
		} catch (SQLException e) {
			System.out.println("로그인 sql 오류 입니다.");
			e.printStackTrace();
		}
		
		return member;
	}
	
	
	//회원가입
	
	public void Register(String mid , String mpassword,String mname,
			String memail , String mphone) {
		Connection con = getConnection();
		
		try {
			PreparedStatement pstmt =
					con.prepareStatement("insert into member values(?,?,?,?,?)");
			//id password name email phone
			pstmt.setString(1,mid);
			pstmt.setString(2,mpassword);
			pstmt.setString(3,mname);
			pstmt.setString(4,memail);
			pstmt.setString(5,mphone);
			pstmt.executeUpdate();

			pstmt.close();
		    con.close();
		} catch (SQLException e) {
			System.out.println("회원가입 오류 입니다.");
			e.printStackTrace();
		}
	}
	
}

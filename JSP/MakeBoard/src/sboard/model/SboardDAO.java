package sboard.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SboardDAO {
	
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
	
	public void InsertComment(int bid,String mid, String scontent,
			String swriter , String sdate) {
		Connection con = getConnection();
		try {

			PreparedStatement pstmt =
					con.prepareStatement("insert into sboard values(?,?,sid.nextval,?,?,?)");
			pstmt.setInt(1, bid);
			pstmt.setString(2, mid);
			pstmt.setString(3,scontent);
			pstmt.setString(4, swriter);
			pstmt.setString(5, sdate );
			pstmt.executeUpdate();

			pstmt.close();
		    con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void DeleteComment(int sid) {
		Connection con = getConnection();
		try {
			PreparedStatement pstmt =
					con.prepareStatement("delete from sboard where sid =?");
			pstmt.setInt(1, sid);
			pstmt.executeUpdate();
			
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("댓글 삭제 오류 입니다.");
			e.printStackTrace();
		}
		
	}
	
	public void UpdateCommnet(String scontent , int sid)  {
		Connection con = getConnection();
		try {
			PreparedStatement pstmt =
					con.prepareStatement("update sboard set SCONTEXT =? where sid =?");
			pstmt.setString(1, scontent);
			pstmt.setInt(2,sid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
}

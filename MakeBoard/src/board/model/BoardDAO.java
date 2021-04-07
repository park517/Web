package board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sboard.model.SboardVO;

public class BoardDAO {
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
	

	public List<BoardVo> getBoardList() {
		List<BoardVo> list = new ArrayList<>();
		Connection con = getConnection();
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM board order by bid");
			
			while(rs.next()) {
				int bid =rs.getInt("bid");
				String mid =rs.getString("mid");
				String btitle =rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				String bwriter = rs.getString("bwriter");
				Date bdate = rs.getDate("bdate");
				int hit = rs.getInt("hit");
				System.out.println("조회수 : "+hit);
				list.add(new BoardVo (
						bid,mid,btitle,bcontent,
						bwriter,bdate,hit
				));	
			}
		    stmt.close();
		    con.close();
		    rs.close();
		} catch (SQLException e) {
			System.out.println("select문 오류 입니다.");
		}
	
		return list;
	}
	
	public BoardVo getBoard(int bid) {
		BoardVo board =null;
		Connection con =getConnection();
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement("select * from board where bid=? ");
			pstmt.setInt(1, bid);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int hit = rs.getInt("hit");
			board = new BoardVo ( 
					rs.getInt("BID"),rs.getString("MID"),rs.getString("BTITLE"),
					rs.getString("BCONTENT"),rs.getString("BWRITER"),
					rs.getDate("BDATE"),rs.getInt("hit")
					);
			rs.close();
			pstmt.close();
			con.close();
			plushit(hit,bid);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return board;
	}
	
	public void plushit(int hit,int bid) {
		Connection con =getConnection();
		try {
			PreparedStatement pstmt =
					con.prepareStatement("update board set hit=? where bid=? ");
			pstmt.setInt(1, hit+1);
			pstmt.setInt(2, bid);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void InsertBoard(String id , String title , String content , String writer,String bdate) {
		Connection con =getConnection();
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement("insert into board values(bid.nextval,?,?,?,?,?,?)");
			pstmt.setString(1,id);
			pstmt.setString(2,title);
			pstmt.setString(3,content);
			pstmt.setString(4,writer);
			pstmt.setString(5,bdate);
			pstmt.setInt(6,0);
			pstmt.executeUpdate();
			pstmt.close();
		    con.close();
		} catch (SQLException e) {
			System.out.println("본문 insert 오류 입니다.");
		}


	}
	
	public void DeleteBoard(int bid) {
		
		Connection con = getConnection();
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement("delete from board where bid=?");
			pstmt.setInt(1, bid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("delete문 오류 입니다.");
		}	
	}
	
	
	public void UpdateBoard(int bid, String title , String content) {
		Connection con = getConnection();
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement("update board set BTITLE =?,BCONTENT =? where bid=? ");
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, bid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			
			System.out.println("본문 업데이트 문제 입니다.");
			e.printStackTrace();
		}
		

	}

	public List<SboardVO> getComment(int bid) {
		Connection con = getConnection();
		List<SboardVO> slist = new ArrayList<SboardVO>();
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from sboard where bid=? ");
			pstmt.setInt(1, bid);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				slist.add(new SboardVO ( 
						rs.getInt("BID"),rs.getString("MID"),rs.getInt("SID"),
						rs.getString("SCONTENT"),rs.getString("SWRITER"),
						rs.getDate("SDATE")
						));	
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return slist;
	}
	
}

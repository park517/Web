package board.model;

import java.io.File;
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
	//커넥션 정보 가져오기
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
	
	// 작성된 게시물들을 가져오는 메소드
	
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
				String fileName = rs.getString("filename");
				String fileRealName = rs.getString("filerealname");
				
				System.out.println("조회수 : "+hit);
				list.add(new BoardVo (
						bid,mid,btitle,bcontent,
						bwriter,bdate,hit,fileName,fileRealName
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
	
	// 해당 글을 클릭했을 시 그 글에 맞는 정보를 가져오는 메소드

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
					rs.getDate("BDATE"),rs.getInt("hit"),rs.getString("filename"),
					rs.getString("filerealname")
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
	
	// 검색 시 해당 조건에 맞는 글들을 가져오는 메소드
	
	public ArrayList<BoardVo> searchBoard(String bwriter) {
		ArrayList<BoardVo> boardList = new ArrayList<>();
		Connection con =getConnection();
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement("SELECT * FROM board where bwriter like ?");
			pstmt.setString(1,"%"+bwriter+"%");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVo board = new BoardVo ( 
						rs.getInt("BID"),rs.getString("MID"),rs.getString("BTITLE"),
						rs.getString("BCONTENT"),rs.getString("BWRITER"),
						rs.getDate("BDATE"),rs.getInt("hit"),rs.getString("filename"),
						rs.getString("filerealname")
						);
				boardList.add(board);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return boardList;
	}

	// 글 클릭 시 조회수를 증가 시켜주는 메소드
	
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
	
	// 글쓰기시 입력받은 값을 insert 해주는 메소드
	
	public void InsertBoard(String id , String title , String content , String writer,String bdate
			,String filename , String filerealname) {
		Connection con =getConnection();
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement("insert into board values(bid.nextval,?,?,?,?,?,?,?,?)");
			pstmt.setString(1,id);
			pstmt.setString(2,title);
			pstmt.setString(3,content);
			pstmt.setString(4,writer);
			pstmt.setString(5,bdate);
			pstmt.setInt(6,0);
			pstmt.setString(7,filename);
			pstmt.setString(8,filerealname);
			pstmt.executeUpdate();
			pstmt.close();
		    con.close();
		} catch (SQLException e) {
			System.out.println("본문 insert 오류 입니다.");
		}


	}
	
	// 글 삭제 시 글을 삭제해주는 메소드
	
	public void DeleteBoard(int bid) {
	    // 삭제할 파일의 경로
	  
	    BoardVo board = getBoard(bid);
	    String path = "C:/board/upload/"+board.getFileRealName();
	    File file = new File(path);
	    if(file.exists() == true){
	    			file.delete();
	    		}
		
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
	
	// 글 수정시 업데이트 해주는 메소드
	
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

	// 해당 글에 달린 댓글들을 불러와주는 메소드
	
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
						rs.getString("SCONTEXT"),rs.getString("SWRITER"),
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

package board.model;

import java.util.Date;

public class BoardVo {
	
	private int bid;
	private String mid; //bid.nextval()
	private String btitle;
	private String bcontent;
	private String bwriter;
	private Date bdate;
	private int hit;
	
	
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBcontent() {
		return bcontent;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}
	public String getBwriter() {
		return bwriter;
	}
	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}
	public Date getBdate() {
		return bdate;
	}
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public BoardVo(int bid, String mid, String btitle, String bcontent, String bwriter, Date bdate,int hit) {
		super();
		this.bid = bid;
		this.mid = mid;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bwriter = bwriter;
		this.bdate = bdate;
		this.hit = hit;
	}
	@Override
	public String toString() {
		return "BoardVo [bid=" + bid + ", mid=" + mid + ", btitle=" + btitle + ", bcontent=" + bcontent + ", bwriter="
				+ bwriter + ", bdate=" + bdate + "]";
	}
	
	
	
}

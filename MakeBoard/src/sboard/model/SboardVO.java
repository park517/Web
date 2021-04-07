package sboard.model;

import java.util.Date;

public class SboardVO {
	
	private int bid;
	private String mid;
	private int sid;
	private String scontent;
	private String swriter;
	private Date sdate;
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
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getScontent() {
		return scontent;
	}
	public void setScontent(String scontent) {
		this.scontent = scontent;
	}
	public String getSwriter() {
		return swriter;
	}
	public void setSwriter(String swriter) {
		this.swriter = swriter;
	}
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	
	public SboardVO(int bid, String mid, int sid,String scontent, String swriter, Date sdate) {
		super();
		this.bid = bid;
		this.mid = mid;
		this.sid = sid;
		this.scontent = scontent;
		this.swriter = swriter;
		this.sdate = sdate;
	}
	@Override
	public String toString() {
		return "SboardVO [bid=" + bid + ", mid=" + mid + ", sid=" + sid + ", scontent=" + scontent + ", swriter="
				+ swriter + ", sdate=" + sdate + "]";
	}
	
	
	
	
}

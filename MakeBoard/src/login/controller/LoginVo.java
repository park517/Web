package login.controller;

public class LoginVo {
	private String mid;
	private String mpassword;
	
	
	public LoginVo(String mid, String mpassword) {
		super();
		this.mid = mid;
		this.mpassword = mpassword;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMpassword() {
		return mpassword;
	}
	public void setMpassword(String mpassword) {
		this.mpassword = mpassword;
	}
	
	@Override
	public String toString() {
		return mid + "|"+ mpassword ;
	}
	
	
	
	
}


package com.web.curation.model.user;
//vo
public class User {
	private String user_id;
	private String user_email;
	private String user_password;
	private String user_desc;
	private String user_pic;
	private String user_CBTI;
	private int user_total_score;
	private int user_authStatus;
	private String user_authKey;
	
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_email=" + user_email + ", user_password=" + user_password
				+ ", user_desc=" + user_desc + ", user_pic=" + user_pic + ", user_CBTI=" + user_CBTI
				+ ", user_total_score=" + user_total_score + ", user_authStatus=" + user_authStatus + ", user_authKey="
				+ user_authKey;
	}


	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_desc() {
		return user_desc;
	}

	public void setUser_desc(String user_desc) {
		this.user_desc = user_desc;
	}

	public String getUser_pic() {
		return user_pic;
	}

	public void setUser_pic(String user_pic) {
		this.user_pic = user_pic;
	}

	public String getUser_CBTI() {
		return user_CBTI;
	}

	public void setUser_CBTI(String user_CBTI) {
		this.user_CBTI = user_CBTI;
	}

	public int getUser_total_score() {
		return user_total_score;
	}

	public void setUser_total_score(int user_total_score) {
		this.user_total_score = user_total_score;
	}

	public int getUser_authStatus() {
		return user_authStatus;
	}

	public void setUser_authStatus(int user_authStatus) {
		this.user_authStatus = user_authStatus;
	}

	public String getUser_authKey() {
		return user_authKey;
	}

	public void setUser_authKey(String user_authKey) {
		this.user_authKey = user_authKey;
	}

	public User() {

	}
	
	public User(String user_id, String user_email, String user_password) {
		this.user_id = user_id;
		this.user_email = user_email;
		this.user_password = user_password;
		
	}
	
	public User(String user_id, String user_email, String user_password, String user_desc, String user_pic) {
		this.user_id = user_id;
		this.user_email = user_email;
		this.user_password = user_password;
		this.user_desc = user_desc;
		this.user_pic = user_pic;
	}
	
}

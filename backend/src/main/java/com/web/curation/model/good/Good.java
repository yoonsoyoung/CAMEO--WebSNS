package com.web.curation.model.good;

public class Good {
	private int good_id;
	private String good_my_email;
	private String good_user_email;
	private int good_pheed_id;
	private String good_pheed_title;
	private String good_tmp;
	public int getGood_id() {
		return good_id;
	}
	public void setGood_id(int good_id) {
		this.good_id = good_id;
	}
	public String getGood_my_email() {
		return good_my_email;
	}
	public void setGood_my_email(String good_my_email) {
		this.good_my_email = good_my_email;
	}
	public String getGood_user_email() {
		return good_user_email;
	}
	public void setGood_user_email(String good_user_email) {
		this.good_user_email = good_user_email;
	}
	public int getGood_pheed_id() {
		return good_pheed_id;
	}
	public void setGood_pheed_id(int good_pheed_id) {
		this.good_pheed_id = good_pheed_id;
	}
	public String getGood_pheed_title() {
		return good_pheed_title;
	}
	public void setGood_pheed_title(String good_pheed_title) {
		this.good_pheed_title = good_pheed_title;
	}
	public String getGood_tmp() {
		return good_tmp;
	}
	public void setGood_tmp(String good_tmp) {
		this.good_tmp = good_tmp;
	}
	public Good(int good_id, String good_my_email, String good_user_email, int good_pheed_id, String good_pheed_title,
			String good_tmp) {
		
		this.good_id = good_id;
		this.good_my_email = good_my_email;
		this.good_user_email = good_user_email;
		this.good_pheed_id = good_pheed_id;
		this.good_pheed_title = good_pheed_title;
		this.good_tmp = good_tmp;
	}
	
	
	
	
	
	

}

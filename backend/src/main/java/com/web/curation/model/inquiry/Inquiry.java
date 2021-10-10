package com.web.curation.model.inquiry;

import java.sql.Date;

public class Inquiry {

	private int inquiry_id;
	private String inquiry_user_id;
	private String inquiry_user_email;
	private String inquiry_title;
	private String inquiry_content;
	private String inquiry_pic;
	private Date inquiry_date;

	public int getInquiry_id() {
		return inquiry_id;
	}

	public void setInquiry_id(int inquiry_id) {
		this.inquiry_id = inquiry_id;
	}

	public String getInquiry_user_id() {
		return inquiry_user_id;
	}

	public void setInquiry_user_id(String inquiry_user_id) {
		this.inquiry_user_id = inquiry_user_id;
	}

	public String getInquiry_user_email() {
		return inquiry_user_email;
	}

	public void setInquiry_user_email(String inquiry_user_email) {
		this.inquiry_user_email = inquiry_user_email;
	}

	public String getInquiry_title() {
		return inquiry_title;
	}

	public void setInquiry_title(String inquiry_title) {
		this.inquiry_title = inquiry_title;
	}

	public String getInquiry_content() {
		return inquiry_content;
	}

	public void setInquiry_content(String inquiry_content) {
		this.inquiry_content = inquiry_content;
	}

	public String getInquiry_pic() {
		return inquiry_pic;
	}

	public void setInquiry_pic(String inquiry_pic) {
		this.inquiry_pic = inquiry_pic;
	}

	public Date getInquiry_date() {
		return inquiry_date;
	}

	public void setInquiry_date(Date inquiry_date) {
		this.inquiry_date = inquiry_date;
	}

	// test를 위한 toString
	@Override
	public String toString() {
		return "Inquiry [inquiry_id=" + inquiry_id + ", inquiry_user_id=" + inquiry_user_id + ", inquiry_user_email="
				+ inquiry_user_email + ", inquiry_title=" + inquiry_title + ", inquiry_content=" + inquiry_content
				+ ", inquiry_pic=" + inquiry_pic + ", inquiry_date=" + inquiry_date + "]";
	}

// 프론트 보내는 건 전부 있는 건데(pheed_id, date 포함
// 받는 건 date랑 pheed_id 없어야함
}

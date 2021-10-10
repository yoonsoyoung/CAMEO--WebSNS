package com.web.curation.model.notice;

import java.sql.Date;

public class Notice {

	private int notice_id;
	private String notice_title;
	private String notice_content;
	private String notice_pic;
	private Date notice_date;

	public int getNotice_id() {
		return notice_id;
	}

	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	public String getNotice_pic() {
		return notice_pic;
	}

	public void setNotice_pic(String notice_pic) {
		this.notice_pic = notice_pic;
	}

	public Date getNotice_date() {
		return notice_date;
	}

	public void setNotice_date(Date notice_date) {
		this.notice_date = notice_date;
	}

	// test를 위한 toString
	@Override
	public String toString() {
		return "Notice [notice_id=" + notice_id + ", notice_title=" + notice_title + ", notice_content="
				+ notice_content + ", notice_pic=" + notice_pic + ", notice_date=" + notice_date + "]";
	}

}

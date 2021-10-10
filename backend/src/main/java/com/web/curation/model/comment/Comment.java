package com.web.curation.model.comment;

import java.sql.Date;

public class Comment {
	
	private Long comment_id;
	
	private Long comment_inquiry_id;
	
	private String comment_content;
	
	private String comment_writer;
	
	
	
	private Date comment_date;
	
	private Date comment_update_time;

	

	public Long getComment_id() {
		return comment_id;
	}



	public void setComment_id(Long comment_id) {
		this.comment_id = comment_id;
	}



	public Long getComment_inquiry_id() {
		return comment_inquiry_id;
	}



	public void setComment_inquiry_id(Long comment_inquiry_id) {
		this.comment_inquiry_id = comment_inquiry_id;
	}



	public String getComment_content() {
		return comment_content;
	}



	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}



	public String getComment_writer() {
		return comment_writer;
	}



	public void setComment_writer(String comment_writer) {
		this.comment_writer = comment_writer;
	}



	



	public Date getComment_date() {
		return comment_date;
	}



	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
	}



	public Date getComment_update_time() {
		return comment_update_time;
	}



	public void setComment_update_time(Date comment_update_time) {
		this.comment_update_time = comment_update_time;
	}

	


	public Comment(Long comment_id, Long comment_inquiry_id, String comment_content, String comment_writer,
			 Date comment_date, Date comment_update_time) {
		super();
		this.comment_id = comment_id;
		this.comment_inquiry_id = comment_inquiry_id;
		this.comment_content = comment_content;
		this.comment_writer = comment_writer;
		
		this.comment_date = comment_date;
		this.comment_update_time = comment_update_time;
	}

	
	

	@Override
	public String toString() {
		return "Comment [comment_id=" + comment_id + ", comment_inquiry_id=" + comment_inquiry_id + ", comment_content="
				+ comment_content + ", comment_writer=" + comment_writer + ", comment_delete_yn=" 
				+ ", comment_date=" + comment_date + ", comment_update_time=" + comment_update_time + "]";
	}



	public Comment() {
		// TODO Auto-generated constructor stub
	}

	

	

}

package com.web.curation.model.pheed;

import java.util.List;

public class Pheed {
	private int pheed_id;
	private String pheed_user_id;
	private String pheed_title;
	private String pheed_content;
	private String pheed_pic;
	private String pheed_date;
	private String changeYn;
	private List<Integer> picIds;
	
	public int getPheed_id() {
		return pheed_id;
	}

	public void setPheed_id(int pheed_id) {
		this.pheed_id = pheed_id;
	}

	public String getPheed_date() {
		return pheed_date;
	}

	public void setPheed_date(String pheed_date) {
		this.pheed_date = pheed_date;
	}

	public String getPheed_user_id() {
		return pheed_user_id;
	}

	public void setPheed_user_id(String pheed_user_id) {
		this.pheed_user_id = pheed_user_id;
	}

	public String getPheed_title() {
		return pheed_title;
	}

	public void setPheed_title(String pheed_title) {
		this.pheed_title = pheed_title;
	}

	public String getPheed_content() {
		return pheed_content;
	}

	public void setPheed_content(String pheed_content) {
		this.pheed_content = pheed_content;
	}

	public String getPheed_pic() {
		return pheed_pic;
	}

	public void setPheed_pic(String pheed_pic) {
		this.pheed_pic = pheed_pic;
	}

	public String getChangeYn() {
		return changeYn;
	}

	public void setChangeYn(String changeYn) {
		this.changeYn = changeYn;
	}

	public List<Integer> getPicIds() {
		return picIds;
	}

	public void setPicIds(List<Integer> picIds) {
		this.picIds = picIds;
	}

	public Pheed(String pheed_user_id, String pheed_title, String pheed_content) {
		super();
		this.pheed_user_id = pheed_user_id;
		this.pheed_title = pheed_title;
		this.pheed_content = pheed_content;
	}

	public Pheed(String pheed_user_id, String pheed_title, String pheed_content, String pheed_date) {
		this.pheed_user_id = pheed_user_id;
		this.pheed_title = pheed_title;
		this.pheed_content = pheed_content;
		this.pheed_date = pheed_date;
	}

	public Pheed(String pheed_user_id, String pheed_title, String pheed_content, String pheed_pic, String pheed_date) {
		super();
		this.pheed_user_id = pheed_user_id;
		this.pheed_title = pheed_title;
		this.pheed_content = pheed_content;
		this.pheed_pic = pheed_pic;
		this.pheed_date = pheed_date;
	}
	
	public Pheed(int pheed_id, String pheed_user_id, String pheed_title, String pheed_content, String pheed_pic,
			String pheed_date) {
		super();
		this.pheed_id = pheed_id;
		this.pheed_user_id = pheed_user_id;
		this.pheed_title = pheed_title;
		this.pheed_content = pheed_content;
		this.pheed_pic = pheed_pic;
		this.pheed_date = pheed_date;
	}

	
	public Pheed(int pheed_id, String pheed_user_id, String pheed_title, String pheed_content) {
		super();
		this.pheed_id = pheed_id;
		this.pheed_user_id = pheed_user_id;
		this.pheed_title = pheed_title;
		this.pheed_content = pheed_content;
	}

	public Pheed() {

	}

	@Override
	public String toString() {
		return "Pheed [pheed_user_id=" + pheed_user_id + ", pheed_title=" + pheed_title + ", pheed_content="
				+ pheed_content + ", pheed_pic=" + pheed_pic + ", pheed_date=" + pheed_date + "]";
	}
// 프론트 보내는 건 전부 있는 건데(pheed_id, date 포함
// 받는 건 date랑 pheed_id 없어야함
}

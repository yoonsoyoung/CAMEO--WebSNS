package com.web.curation.model.pheedcomment;

public class PheedComment {
	private int pheedCommentId;
	private int pheedId;
	private String userEmail;
	private String content;
	private String insertTime;
	
	public PheedComment() {}
	
	public PheedComment(int pheedId, String userEmail, String content) {
		this.pheedId = pheedId;
		this.userEmail = userEmail;
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	public int getPheedCommentId() {
		return pheedCommentId;
	}

	public void setPheedCommentId(int pheedCommentId) {
		this.pheedCommentId = pheedCommentId;
	}

	public int getPheedId() {
		return pheedId;
	}

	public void setPheedId(int pheedId) {
		this.pheedId = pheedId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	@Override
	public String toString() {
		return "PheedComment [pheedCommentId=" + pheedCommentId + ", pheedId=" + pheedId + ", userEmail=" + userEmail
				+ ", content=" + content + ", insertTime=" + insertTime + "]";
	}
	
}

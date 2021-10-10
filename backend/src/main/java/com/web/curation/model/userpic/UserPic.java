package com.web.curation.model.userpic;

import java.time.LocalDateTime;

public class UserPic {
	private int userPicId;
	private String userEmail;
	private String originalName;
	private String saveName;
	private Long size;
	private String deleteYn;
	private LocalDateTime insertTime;
	private LocalDateTime deleteTime;
	
	public int getUserPicId() {
		return userPicId;
	}
	public void setUserPicId(int userPicId) {
		this.userPicId = userPicId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public LocalDateTime getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(LocalDateTime insertTime) {
		this.insertTime = insertTime;
	}
	public LocalDateTime getDeleteTime() {
		return deleteTime;
	}
	public void setDeleteTime(LocalDateTime deleteTime) {
		this.deleteTime = deleteTime;
	}
	
	public UserPic(int userPicId, String userEmail, String originalName, String saveName, Long size) {
		this.userPicId = userPicId;
		this.userEmail = userEmail;
		this.originalName = originalName;
		this.saveName = saveName;
		this.size = size;
	}
	
	public UserPic() {}
	
	@Override
	public String toString() {
		return "UserPic [userPicId=" + userPicId + ", userEmail=" + userEmail + ", originalName=" + originalName
				+ ", saveName=" + saveName + ", size=" + size + ", deleteYn=" + deleteYn + ", insertTime=" + insertTime
				+ ", deleteTime=" + deleteTime + "]";
	}
}

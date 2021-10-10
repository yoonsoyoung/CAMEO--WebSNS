package com.web.curation.model.pheedpic;


import java.time.LocalDateTime;

public class PheedPic {
	private int pheedPicId;
	private int pheedId;
	private String originalName;
	private String saveName;
	private Long size;
	private String deleteYn;
	private LocalDateTime insertTime;
	private LocalDateTime deleteTime;

	public int getPheedPicId() {
		return pheedPicId;
	}

	public void setPheedPicId(int pheedPicId) {
		this.pheedPicId = pheedPicId;
	}

	public int getPheedId() {
		return pheedId;
	}

	public void setPheedId(int pheedId) {
		this.pheedId = pheedId;
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

	public PheedPic() {
		
	}

	public PheedPic(int pheedPicId, int pheedId, String originalName, String saveName, Long size) {
		this.pheedPicId = pheedPicId;
		this.pheedId = pheedId;
		this.originalName = originalName;
		this.saveName = saveName;
		this.size = size;
	}


	@Override
	public String toString() {
		return "PheedPic [pheedPicId=" + pheedPicId + ", pheedId=" + pheedId + ", originalName=" + originalName
				+ ", saveName=" + saveName + ", size=" + size + ", deleteYn=" + deleteYn + ", insertTime=" + insertTime
				+ ", deleteTime=" + deleteTime + "]";
	}
}

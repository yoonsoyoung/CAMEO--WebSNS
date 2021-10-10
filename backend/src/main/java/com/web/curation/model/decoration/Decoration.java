package com.web.curation.model.decoration;

import java.time.LocalDateTime;



public class Decoration {
	
	private int decoration_id;
	private String originalName;
	private String saveName;
	private Long size;
	private LocalDateTime insertTime;
	private LocalDateTime deleteTime;
	public int getDecoration_id() {
		return decoration_id;
	}
	
	public Decoration(int decoration_id, String originalName, String saveName, Long size, LocalDateTime insertTime,
			LocalDateTime deleteTime) {
		
		this.decoration_id = decoration_id;
		this.originalName = originalName;
		this.saveName = saveName;
		this.size = size;
		this.insertTime = insertTime;
		this.deleteTime = deleteTime;
	}

	public Decoration() {
		
	}

	public void setDecoration_id(int decoration_id) {
		this.decoration_id = decoration_id;
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
	@Override
	public String toString() {
		return "Decoration [decoration_id=" + decoration_id + ", originalName=" + originalName + ", saveName="
				+ saveName + ", size=" + size + ", insertTime=" + insertTime + ", deleteTime=" + deleteTime + "]";
	}
	
	
	
}

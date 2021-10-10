package com.web.curation.model.scrap;

public class Scrap {
	private int scrap_id;
	private String scrap_user_email;
	private int scrap_pheed_id;
	
	public int getScrap_id() {
		return scrap_id;
	}
	public void setScrap_id(int scrap_id) {
		this.scrap_id = scrap_id;
	}
	
	public String getScrap_user_email() {
		return scrap_user_email;
	}
	public void setScrap_user_email(String scrap_user_email) {
		this.scrap_user_email = scrap_user_email;
	}
	public int getScrap_pheed_id() {
		return scrap_pheed_id;
	}
	public void setScrap_pheed_id(int scrap_pheed_id) {
		this.scrap_pheed_id = scrap_pheed_id;
	}
	public Scrap(int scrap_id, String scrap_user_email, int scrap_pheed_id) {
		super();
		this.scrap_id = scrap_id;
		this.scrap_user_email = scrap_user_email;
		this.scrap_pheed_id = scrap_pheed_id;
	}
	@Override
	public String toString() {
		return "Scrap [scrap_id=" + scrap_id + ", scrap_user_email=" + scrap_user_email + ", scrap_pheed_id="
				+ scrap_pheed_id + "]";
	}
	


}

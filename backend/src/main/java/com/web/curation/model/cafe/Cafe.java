package com.web.curation.model.cafe;

public class Cafe {
	private String cafe_name;
	private String updated_date;
	private String address;
	private String homepage;
	private String telephone;
	private String image;
	private String url;
	public String getCafe_name() {
		return cafe_name;
	}
	public void setCafe_name(String cafe_name) {
		this.cafe_name = cafe_name;
	}
	public String getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(String updated_date) {
		this.updated_date = updated_date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Cafe() {}
	
	public Cafe(String cafe_name, String updated_date, String address, String homepage, String telephone, String image,
			String url) {
		super();
		this.cafe_name = cafe_name;
		this.updated_date = updated_date;
		this.address = address;
		this.homepage = homepage;
		this.telephone = telephone;
		this.image = image;
		this.url = url;
	}
	@Override
	public String toString() {
		return "Cafe [cafe_name=" + cafe_name + ", updated_date=" + updated_date + ", address=" + address
				+ ", homepage=" + homepage + ", telephone=" + telephone + ", image=" + image + ", url=" + url + "]";
	}
	
	
}

package com.web.curation.model.follow;

public class Follow {
	private int follow_id;
	private String following;
	private String followers;
	private String temp_followers;
	private boolean isMute;
	private boolean isFollowingEachOther;

	public Follow() {
	}

	public Follow(int follow_id, String following, String followers, String temp_followers, boolean isMute,
			boolean isFollowingEachOther) {
		super();
		this.follow_id = follow_id;
		this.following = following;
		this.followers = followers;
		this.temp_followers = temp_followers;
		this.isMute = isMute;
		this.isFollowingEachOther = isFollowingEachOther;
	}

	public int getFollow_id() {
		return follow_id;
	}

	public void setFollow_id(int follow_id) {
		this.follow_id = follow_id;
	}

	public String getFollowing() {
		return following;
	}

	public void setFollowing(String following) {
		this.following = following;
	}

	public String getFollowers() {
		return followers;
	}

	public void setFollowers(String followers) {
		this.followers = followers;
	}

	public String getTemp_followers() {
		return temp_followers;
	}

	public void setTemp_followers(String temp_followers) {
		this.temp_followers = temp_followers;
	}

	public boolean isMute() {
		return isMute;
	}

	public void setMute(boolean isMute) {
		this.isMute = isMute;
	}

	public boolean isFollowingEachOther() {
		return isFollowingEachOther;
	}

	public void setFollowingEachOther(boolean isFollowingEachOther) {
		this.isFollowingEachOther = isFollowingEachOther;
	}
}

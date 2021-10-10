package com.web.curation.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.userpic.UserPic;

@Mapper
public interface UserPicDao {
	public int insertUserPic(UserPic userPic) throws SQLException;
	
	public int updateUserPic(UserPic userPic) throws SQLException;
	
	public int deleteUserPic(String userEmail) throws SQLException; 
	
	public UserPic getUserPic(String userEmail) throws SQLException;
}

package com.web.curation.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


import com.web.curation.model.decoration.Decoration;

@Mapper
public interface DecorationDao {
	
	
	public int insertPic(List<Decoration> picList) throws SQLException;
	
	public int selectScore(String user_id) throws Exception;
	
	public Decoration selectPic(int user_total_score) throws Exception;
	
//	public Decoration selectPic1(int user_total_score ) throws Exception;
	
//	public Decoration selectPic2(int res ) throws Exception;
//	
//	public Decoration selectPic3(int res ) throws Exception;
//	
//	public Decoration selectPic4(int res ) throws Exception;
//	
//	public Decoration selectPic5(int res ) throws Exception;

}

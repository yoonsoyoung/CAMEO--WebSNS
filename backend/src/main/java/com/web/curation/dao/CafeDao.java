package com.web.curation.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.cafe.Cafe;
@Mapper
public interface CafeDao {
	
	public List<Cafe> findCafe(String cafe_name) throws SQLException;
	
	public List<Cafe> getAllCafe() throws SQLException;
	
	public List<Cafe> findCafePlace(String place)  throws SQLException;
}

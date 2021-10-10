package com.web.curation.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.pheed.Pheed;
@Mapper
public interface NewsPheedDao {
	int insertPheed(Pheed pheed) throws SQLException;

	List<Pheed> selectPheedList(String following) throws SQLException;

	int deletePheed(int pheed_id) throws SQLException;

	int updatePheed(Pheed pheed) throws SQLException;
	
	Pheed selectPheed(int pheed_id) throws SQLException;
	
	List<Pheed> selectAllPheed() throws SQLException;
	
	List<Pheed> selectPheedByEmail(String pheed_user_id) throws SQLException;
}

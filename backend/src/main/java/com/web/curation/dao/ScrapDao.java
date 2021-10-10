package com.web.curation.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.scrap.Scrap;

@Mapper
public interface ScrapDao {

	// 스크랩 등록
	int insertScrap(Map<String, Object> map) throws SQLException;

	// 스크랩 조회
	public List<Integer> getAllScrap(String scrap_user_email) throws SQLException;

	// 스크랩 취소
	int deleteScrap(int scrap_id) throws SQLException;
	
	// 스크랩아이디 
	int getId(Map<String, Object> map) throws SQLException;
	
	int selectScrapId(Map<String, Object> map) throws SQLException;
}

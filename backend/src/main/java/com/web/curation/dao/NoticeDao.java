package com.web.curation.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.notice.Notice;

@Mapper
public interface NoticeDao {

	int insertNotice(Notice notice) throws SQLException;

	Notice getNotice(int notice_id) throws SQLException;
	
	int getNotice_id(String title) throws SQLException;

	List<Notice> getNoticeList() throws SQLException;

	int updateNotice(Map<String, String> map) throws SQLException;

	int deleteNotice(int notice_id) throws SQLException;
}
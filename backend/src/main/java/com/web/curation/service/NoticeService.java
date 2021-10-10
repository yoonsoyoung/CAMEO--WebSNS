package com.web.curation.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.web.curation.model.notice.Notice;

public interface NoticeService {

	int insertNotice(Notice notice) throws Exception;

	Notice getNotice(int notice_id) throws Exception;
	
	int getNotice_id(String title) throws Exception;

	List<Notice> getNoticeList() throws Exception;

	int deleteNotice(int notice_id) throws Exception;

	int updateNotice(Map<String, String> map) throws Exception;

}

package com.web.curation.service;

import java.util.List;
import java.util.Map;

import com.web.curation.model.pheed.Pheed;
import com.web.curation.model.scrap.Scrap;

public interface ScrapService {

	// 스크랩 등록
	int insertScrap(Map<String, Object> map) throws Exception;

	// 스크랩 조회
	public List<Pheed> getAllScrap(String scrap_user_email) throws Exception;

	// 스크랩 취소
	int deleteScrap(Map<String, Object> map) throws Exception;
}

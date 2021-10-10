package com.web.curation.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.NewsPheedDao;
import com.web.curation.dao.ScrapDao;
import com.web.curation.model.pheed.Pheed;
import com.web.curation.model.scrap.Scrap;

@Service
public class ScrapServiceImpl implements ScrapService {

	@Autowired
	private ScrapDao dao;

	@Autowired
	private NewsPheedDao pheed_dao;
	
	// 스크랩 등록
	@Override
	public int insertScrap(Map<String, Object> map) throws Exception {
		return  dao.insertScrap(map);
	}

	// 스크랩 조회
	@Override
	public List<Pheed> getAllScrap(String scrap_user_email) throws Exception {
		
		List<Integer> list = new ArrayList<>();
		list = dao.getAllScrap(scrap_user_email);
		
		List<Pheed> list2 = new ArrayList<>();
		for (int i : list) {
			Pheed p = pheed_dao.selectPheed(i);
			list2.add(p);
			
		}
		
		return list2;
	}

	// 스크랩 취소
	@Override
	public int deleteScrap(Map<String, Object> map) throws Exception {
		int cnt = dao.getId(map);
		return dao.deleteScrap(cnt);
	}

}

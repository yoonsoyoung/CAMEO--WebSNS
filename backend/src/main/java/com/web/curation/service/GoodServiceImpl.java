package com.web.curation.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.GoodDao;
import com.web.curation.model.good.Good;

@Service
public class GoodServiceImpl implements GoodService {
	
	@Autowired
	private GoodDao dao;

	@Override
	public int insertGood(@Valid Map<String, Object> map) throws SQLException {
		// TODO Auto-generated method stub
		return dao.insertGood(map);
	}

	@Override
	public List<Good> getGoodReqList(String good_my_email) throws SQLException {
		// TODO Auto-generated method stub
		return dao.getGoodReqList(good_my_email);
	}

	@Override
	public int deleteGood(Map<String, String> map) throws SQLException {
		// TODO Auto-generated method stub
		int cnt = dao.ida(map); // good_id 
		return dao.deleteGood(cnt);
	}

	@Override
	public int countGood(String good_pheed_id) throws SQLException {
		// TODO Auto-generated method stub
		return dao.countGood(good_pheed_id);
	}

	@Override
	public String selectPheedtitle(int cnt) throws SQLException {
		// TODO Auto-generated method stub
		return dao.selectPheedtitle(cnt);
	}
	
	

}

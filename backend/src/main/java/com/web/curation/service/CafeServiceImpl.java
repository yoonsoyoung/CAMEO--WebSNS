package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.CafeDao;
import com.web.curation.model.cafe.Cafe;
@Service
public class CafeServiceImpl implements CafeService{

	@Autowired
	private CafeDao dao;
	
	@Override
	public List<Cafe> findCafe(String cafe_name) throws Exception {
		return dao.findCafe(cafe_name);
	}

	@Override
	public List<Cafe> getAllCafe() throws Exception {
		return dao.getAllCafe();
	}

	@Override
	public List<Cafe> findCafePlace(String place) throws Exception {
		return dao.findCafePlace(place);
	}

}

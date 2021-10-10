package com.web.curation.service;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.HelpSelectDao;

@Service
public class HelpSelectServiceImpl implements HelpSelectService {
	
	@Autowired
	private HelpSelectDao dao;

	@Override
	public int updateHelpPlus(Map<String,String> map) throws SQLException {
		
		return dao.updateHelpPlus(map);
	}

	@Override
	public int updateHelpMinus(Map<String,String> map) throws SQLException {
		
		return dao.updateHelpMinus(map);
	}

	


}

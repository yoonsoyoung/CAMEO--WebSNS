package com.web.curation.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.TagDao;
import com.web.curation.model.tag.Tag;


@Service
public class TagServiceImpl implements TagService{
	
	@Autowired
	private TagDao dao;

	@Override
	public int insertTag(Map map) throws SQLException {
		// TODO Auto-generated method stub
		return dao.insertTag(map);
	}

	@Override
	public int deleteTag(int tag_id) throws SQLException {
		// TODO Auto-generated method stub
		return dao.deleteTag(tag_id);
	}

	@Override
	public List<Tag> getTagList() throws SQLException {
		// TODO Auto-generated method stub
		return dao.getTagList();
	}

	@Override
	public List<Tag> tagSelect(String tag_content) throws SQLException {
		// TODO Auto-generated method stub
		return dao.tagSelect(tag_content);
	}

	

}

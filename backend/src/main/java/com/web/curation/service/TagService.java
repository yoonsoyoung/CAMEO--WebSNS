package com.web.curation.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.web.curation.model.tag.Tag;

public interface TagService {
	
public int insertTag(Map map) throws SQLException;
	
	public int deleteTag(int tag_id) throws SQLException;
	
	List<Tag> getTagList() throws SQLException;
	

	List<Tag> tagSelect(String tag_content)throws SQLException;

}

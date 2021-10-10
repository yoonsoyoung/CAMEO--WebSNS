package com.web.curation.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.tag.Tag;

@Mapper
public interface TagDao {
	
	public int insertTag(Map map) throws SQLException;
	
	public int deleteTag(int tag_id) throws SQLException;
	
	List<Tag> getTagList() throws SQLException;
	
	List<Tag> tagSelect(String tag_content)throws SQLException;
	

}

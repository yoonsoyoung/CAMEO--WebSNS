package com.web.curation.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.pheedcomment.PheedComment;
import com.web.curation.model.scrap.Scrap;

@Mapper
public interface PheedCommentDao {
	// 갯수
	public int selectCommentCount(int pheedId) throws SQLException;

	// 작성
	public int insertComment(PheedComment pheedComment) throws SQLException;

	// 수정
	public int updateComment(PheedComment pheedComment) throws SQLException;
	
	// 삭제
	public int deleteComment(int pheedCommentId) throws SQLException;
	
	// 목록
	public List<PheedComment> getAllComment(int pheedId) throws SQLException;
}

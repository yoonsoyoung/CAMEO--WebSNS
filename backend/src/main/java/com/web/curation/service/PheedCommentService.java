package com.web.curation.service;

import java.sql.SQLException;
import java.util.List;

import com.web.curation.model.pheedcomment.PheedComment;

public interface PheedCommentService {
	// 갯수
	public int selectCommentCount(int pheedId) throws Exception;

	// 작성
	public int insertComment(PheedComment pheedComment) throws Exception;

	// 수정
	public int updateComment(PheedComment pheedComment) throws Exception;

	// 삭제
	public int deleteComment(int pheedCommentId) throws Exception;

	// 목록
	public List<PheedComment> getAllComment(int pheedId) throws Exception;
}

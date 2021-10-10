package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.PheedCommentDao;
import com.web.curation.model.pheedcomment.PheedComment;
@Service
public class PheedCommentServiceImpl implements PheedCommentService{

	@Autowired
	private PheedCommentDao dao;
	
	@Override
	public int selectCommentCount(int pheedId) throws Exception {
		return dao.selectCommentCount(pheedId);
	}

	@Override
	public int insertComment(PheedComment pheedComment) throws Exception {
		return dao.insertComment(pheedComment);
	}

	@Override
	public int updateComment(PheedComment pheedComment) throws Exception {
		return dao.updateComment(pheedComment);
	}

	@Override
	public int deleteComment(int pheedCommentId) throws Exception {
		return dao.deleteComment(pheedCommentId);
	}

	@Override
	public List<PheedComment> getAllComment(int pheedId) throws Exception {
		return dao.getAllComment(pheedId);
	}

}

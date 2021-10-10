package com.web.curation.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.CommentDao;
import com.web.curation.model.comment.Comment;
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao dao;
	
	@Override
	public boolean registerComment(Comment comment) {
		
		int queryResult = 0;
		
		if(comment.getComment_id() == null) {
			queryResult = dao.insertComment(comment);
		} else {
			queryResult = dao.updateComment(comment);
		}
		return (queryResult == 1) ?  true : false;
	}

	@Override
	public boolean deleteComment(Long comment_id) {
		
		return dao.deleteComment(comment_id) == 1;
//		int queryResult = 0;
//		
//		Comment comment = dao.selectCommentDetail(comment_id);
//		
//		if(comment != null && "N".equals(comment.getComment_delete_yn())) {
//			queryResult = dao.deleteComment(comment_id);
//		}
//		
//		return (queryResult == 1) ?  true : false;
	}

	@Override
	public List<Comment> getCommentList(Comment comment) {
//		List<Comment> commentList = Collections.emptyList();
//		
//		int commentTotalCount = dao.selectCommentTotalCount(comment);
//		if(commentTotalCount > 0) {
//			commentList = dao.selectCommentList(comment);
//		}
//		return commentList;
		
		return dao.selectCommentList(comment);
	}

}

package com.web.curation.service;

import java.util.List;

import com.web.curation.model.comment.Comment;

public interface CommentService {
	
	
	public boolean registerComment(Comment comment);
	
	public boolean deleteComment(Long comment_id);
	
	public List<Comment> getCommentList(Comment comment);

	

}

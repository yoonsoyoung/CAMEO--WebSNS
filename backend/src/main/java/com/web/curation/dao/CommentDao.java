package com.web.curation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.comment.Comment;

@Mapper
public interface CommentDao {
	
	// comment 작성
	public int insertComment(Comment comment);
	
	// select comment_id 가지고 내용 확인
	public Comment selectCommentDetail(Long comment_id);
	
	// comment 수정
	public int updateComment(Comment comment);
	
	// comment 삭제
	public int deleteComment(Long comment_id);
	
	// comment LIST 뽑기
	
	public List<Comment> selectCommentList(Comment comment);
	
	// comment 댓글 갯수 확인
	public int selectCommentTotalCount(Comment comment);

}

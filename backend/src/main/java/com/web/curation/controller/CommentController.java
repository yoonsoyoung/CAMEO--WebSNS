package com.web.curation.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.comment.Comment;
import com.web.curation.service.CommentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/comments")
public class CommentController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private CommentService commentService;
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	
	// 댓글 등록할 때 , 같은 comment_id가 있다면 , 서비스 단에서 update 수정해줌 
	@ApiOperation(value = "댓글 등록 같은 comment_id가 있다면  update도 같이 진행", response =String.class)
	@GetMapping
	public ResponseEntity<String> registerComment(@RequestBody Comment comment){
		logger.debug("registerComment- 호출");
		
		if(commentService.registerComment(comment)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			
		}else {
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		//굿
		
		
		
	}
	
	//댓글 삭제
	@DeleteMapping("/{comment_id}")
	public ResponseEntity<String> deleteComment(@PathVariable long comment_id, HttpSession session ){
		logger.debug("deleteComment - 호출");
		if(commentService.deleteComment(comment_id)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		
		
	}
	
	//댓글 리스트
	@ApiOperation(value = "comment list 를 출력 ")
	@GetMapping("/getCommentList")
	public ResponseEntity<List<Comment>> getCommentList(Comment comment){
		
		//test
//		List<Comment> list = commentService.getCommentList(comment);
//		for (Comment tmp : list)  { System.out.println(tmp);};
		
		return new ResponseEntity<List<Comment>>(commentService.getCommentList(comment), HttpStatus.OK);
		
	}
	
	
	

		
	


}

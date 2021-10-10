package com.web.curation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.tag.Tag;
import com.web.curation.service.TagServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tag")

public class TagController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	public static final Logger logger = LoggerFactory.getLogger(TagController.class);
	
	@Autowired
	private TagServiceImpl service;
	
	@ApiOperation(value = "tag 삽입" , notes = "SUCCESS")
	@PostMapping("/insert")
	public Object insertTag(@Valid @RequestBody Map<String, String> map) throws Exception{
		
		if(service.insertTag(map) == 1) {
			return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(FAIL, HttpStatus.FORBIDDEN);
		}
		
	}
	
	@DeleteMapping("/delete/{tag_id}")
	@ApiOperation(value = "태그삭제", notes = "해당 tag_id필요 ")
	public ResponseEntity<String> deleteTag(@PathVariable("tag_id") int tag_id) throws Exception{
		
		int res = service.deleteTag(tag_id);
		
		if(res > 0) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	

		@ApiOperation(value = "태그 리스트" , notes = "내가 삽입한 태그 리스트 ")
		@GetMapping("/list")
		public ResponseEntity<List<Tag>> getTagList() throws Exception{
			return new ResponseEntity<List<Tag>>(service.getTagList(), HttpStatus.OK);
		}
	
		@ApiOperation(value = "태그 검색 " , notes = "tag_content에 포함된 태그명 검색 ")
		@GetMapping("/select")
		public ResponseEntity<List<Tag>> tagSelect(@RequestParam String tag_content) throws Exception{
			return new ResponseEntity<List<Tag>>(service.tagSelect(tag_content), HttpStatus.OK);
			
		}
		
		

}

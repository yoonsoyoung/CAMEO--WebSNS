package com.web.curation.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dao.HelpSelectDao;
import com.web.curation.model.BasicResponse;

import com.web.curation.service.HelpSelectServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

		@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/help")

public class HelpSelectController {
	// 도움이 돼요 안돼요 컨트롤러
	public static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Autowired
	HelpSelectDao dao;
	
	@Autowired
	private HelpSelectServiceImpl service;
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	//도움이 돼요
	@ApiOperation(value = "도움이 돼요", notes = " user_id or user_email 이 같은 user를 찾아 훈장점수 올려줌")
	@PutMapping("/plus")
	public ResponseEntity<String> updateHelpPlus(@RequestBody Map<String, String> map) throws Exception{
		int res = service.updateHelpPlus(map);
		
		if(res>0) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	//도움이 안돼요
	@ApiOperation(value = "도움이 안돼요" ,notes =" user_id or user_email 이 같은 user를 찾아 훈장점수 내려줌")
	@PutMapping("/minus")
	public ResponseEntity<String> updateHelpMinus(@RequestBody Map<String, String> map) throws Exception{
		int res = service.updateHelpMinus(map);
		
		if(res>0) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//이미지 가져오기dd (훈장)
	

}

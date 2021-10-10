package com.web.curation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashPrintJobAttributeSet;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dao.NoticeDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.notice.Notice;
import com.web.curation.service.NoticeServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/notice")

public class NoticeController {

	public static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

	@Autowired
	NoticeDao noticeDao;

	@Autowired
	private NoticeServiceImpl service;

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	// 공지사항 입력, insert할때 , notice_id를 함께 반환해주자 gg
	@ApiOperation(value = "공지사항 입력" , notes = "SUCCESS와 notice_id 반환")
	@PostMapping("/insert")
	public Object insertNotice(@Valid @RequestBody Notice notice) throws Exception {
		// 일단 가입하면 기본 정보는 저장함 이때 authStatus = 0
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		//notice_id "1"을 받아오고 
		int res = service.insertNotice(notice);
		
			if(res > 0) {
				
				resultMap.put("message", SUCCESS);
				resultMap.put("notice_id",res);
				status = HttpStatus.OK;
			}else {
				resultMap.put("message", FAIL);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
			return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 공지사항 가져오기
	@ApiOperation(value = "공지사항 반환")
	@GetMapping("/list/{notice_id}")
	public ResponseEntity<Notice> getNotice(@PathVariable int notice_id, HttpSession session) throws Exception {
//			logger.debug("getNoticeList - 호출");
		return new ResponseEntity<Notice>(service.getNotice(notice_id), HttpStatus.OK);
		}

	// 공지사항 리스트
	@ApiOperation(value = "공지사항 리스트를 반환한다.")
	@GetMapping("/list")
	public ResponseEntity<List<Notice>> getNoticeList() throws Exception {
//			logger.debug("getNoticeList - 호출");
		return new ResponseEntity<List<Notice>>(service.getNoticeList(), HttpStatus.OK);
	}

	// 공지사항 수정
	@PutMapping("/update")
	// 내가 리턴해주고 싶은 값의 유형을 정해주는 ResponseEntity
	public ResponseEntity<String> updateNotice(@RequestBody Map<String, String> map) throws Exception {

		int res = service.updateNotice(map);

		if (res > 0) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 공지사항 삭제
	@DeleteMapping("/delete/{notice_id}")
	public ResponseEntity<String> deleteNotice(@PathVariable int notice_id, HttpSession session) throws Exception {
		int res = service.deleteNotice(notice_id);
		if (res > 0) {
			session.invalidate();
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

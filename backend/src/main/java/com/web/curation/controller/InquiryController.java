package com.web.curation.controller;

import java.util.List;
import java.util.Map;

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

import com.web.curation.dao.InquiryDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.inquiry.Inquiry;
import com.web.curation.service.InquiryServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/inquiry")

public class InquiryController {

	public static final Logger logger = LoggerFactory.getLogger(InquiryController.class);

	@Autowired
	InquiryDao inquiryDao;

	@Autowired
	private InquiryServiceImpl service;

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	// 문의사항 생성
	@ApiOperation(value = "문의사항 생성")
	@PostMapping("/insert")
	public Object insertInquiry(@Valid @RequestBody Map<String, String> map) throws Exception {
		if (service.insertInquiry(map) == 1) {
			return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(FAIL, HttpStatus.FORBIDDEN);
		}
	}

	// 문의사항 조회
	@ApiOperation(value = "문의사항 조회")
	@GetMapping("/list/{inquiry_id}")
	public ResponseEntity<Inquiry> getInquiry(@PathVariable int inquiry_id, HttpSession session) throws Exception {
//				logger.debug("getInquiry - 호출");
		return new ResponseEntity<Inquiry>(service.getInquiry(inquiry_id), HttpStatus.OK);
	}

	// 문의사항 리스트 조회
	@ApiOperation(value = "문의사항 리스트")
	@GetMapping("list")
	public ResponseEntity<List<Inquiry>> selectInquiryList() throws Exception {
//			logger.debug("selectInquiryList - 호출");
		return new ResponseEntity<List<Inquiry>>(service.selectInquiryList(), HttpStatus.OK);
	}

	// 문의사항 수정
	@ApiOperation(value = "문의사항 수정")
	@PutMapping("/update")
	public ResponseEntity<String> updateInquiry(@RequestBody Map<String, String> map) throws Exception {

		int res = service.updateInquiry(map);

		if (res > 0) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 문의사항 삭제
	@ApiOperation(value = "문의사항 삭제")
	@DeleteMapping("delete/{inquiry_id}")
	public ResponseEntity<String> deleteInquiry(@PathVariable int inquiry_id, HttpSession session) throws Exception {
		int res = service.deleteInquiry(inquiry_id);
		if (res > 0) {
			session.invalidate();
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

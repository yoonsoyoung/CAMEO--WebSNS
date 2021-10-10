package com.web.curation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.cafe.Cafe;
import com.web.curation.model.pheed.Pheed;
import com.web.curation.service.CafeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = "*")
@RequestMapping("/cafe")
@RestController
public class CafeController {
	
	@Autowired 
	private CafeService ser;
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@ApiOperation(value = "카페 이름으로 찾기", notes = "카페 이름을 검색하면 유사한 이름의 카페 정보가 나온다.", response = Map.class)
	@RequestMapping(value = "/findCafeByname", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findCafeByname(@RequestParam String cafe_name) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			List<Cafe> cafeList = ser.findCafe(cafe_name);
			resultMap.put("cafeList", cafeList);
			resultMap.put("message", SUCCESS);

			status = HttpStatus.ACCEPTED;
			
		} catch (DataAccessException e) {
			resultMap.put("message", "DB 문제");
			status = HttpStatus.FAILED_DEPENDENCY;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "카페 목록", notes = "카페 전체 목록", response = Map.class)
	@RequestMapping(value = "/getAllCafe", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getAllCafe() {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			List<Cafe> cafeList = ser.getAllCafe();
			resultMap.put("cafeList", cafeList);
			resultMap.put("message", SUCCESS);

			status = HttpStatus.ACCEPTED;
		} catch (DataAccessException e) {
			resultMap.put("message", "DB 문제");
			status = HttpStatus.FAILED_DEPENDENCY;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "카페 주소로 찾기", notes = "카페 주소를 검색하면 해당 주소의 모든 카페 정보가 나온다.", response = Map.class)
	@RequestMapping(value = "/findCafeByAddress", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findCafeByAddress(@RequestParam String place) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			List<Cafe> cafeList = ser.findCafePlace(place);
			resultMap.put("cafeList", cafeList);
			resultMap.put("message", SUCCESS);

			status = HttpStatus.ACCEPTED;
		} catch (DataAccessException e) {
			resultMap.put("message", "DB 문제");
			status = HttpStatus.FAILED_DEPENDENCY;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
}

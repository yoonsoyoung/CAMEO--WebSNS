package com.web.curation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.pheed.Pheed;
import com.web.curation.model.scrap.Scrap;
import com.web.curation.service.ScrapServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = "*")
@RequestMapping("/scrap")
@RestController
public class ScrapController {

	@Autowired
	private ScrapServiceImpl ser;

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@ApiOperation(value = "스크랩 등록", notes = "scrap_user_email, scrap_pheed_id 백엔드에 보내주세요.")
	@PostMapping(value = "/insert")
	public ResponseEntity<Map<String, Object>> insertScrap(@RequestBody Map<String, Object> map) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		HttpStatus status = null;
		
		Map<String, Object> resultmap = new HashMap<String, Object>();
		int scrap_pheed_id =  (int) map.get("scrap_pheed_id");
		String scrap_user_email = (String) map.get("scrap_user_email");
		
		resultmap.put("scrap_user_email", scrap_user_email);
		resultmap.put("scrap_pheed_id", scrap_pheed_id);
		int scrap_id = ser.insertScrap(resultmap);
		
		if (scrap_id != 0) {
			status = HttpStatus.ACCEPTED;
			
			result.put("scrap_id", scrap_id);
			result.put("message", SUCCESS);
			
		} else {
			status = HttpStatus.NOT_ACCEPTABLE;
			
			result.put("message", FAIL);
		}
		return new ResponseEntity<Map<String,Object>>(result, status);
	}

	// 스크랩 조회
	@ApiOperation(value = "스크랩 조회", notes = "프론트에서 scrap_user_email을 건네주면 이를 통해 해당 유저가 스크랩한 모든 피드를 반환해준다.")
	@GetMapping("/list/{scrap_user_email}")
	public ResponseEntity<Map<String, Object>> getAllScrap(@PathVariable("scrap_user_email") String scrap_user_email) throws Exception{
	HttpStatus status = null;
	Map<String, Object> resultmap = new HashMap<String, Object>();
	
	List<Pheed> list = ser.getAllScrap(scrap_user_email);
	
	if (list != null) {
		status = HttpStatus.ACCEPTED;
		
		resultmap.put("list", list);
		resultmap.put("message", SUCCESS);
		
	} else {
		status = HttpStatus.NOT_ACCEPTABLE;
		
		resultmap.put("message", FAIL);
	}
		return new ResponseEntity<Map<String, Object>>(resultmap, status);
	}

	// 스크랩 취소
	@DeleteMapping("/delete")
	@ApiOperation(value = "스크랩 취소", notes = "scrap_user_email와 scrap_pheed_id로 스크랩 취소를 할 수 있다.")
	public ResponseEntity<String> deleteScrap(@RequestBody Map<String, Object> map, HttpSession session) throws Exception {
		
		Map<String, Object> resultmap = new HashMap<String, Object>();
		
		String scrap_user_email = (String) map.get("scrap_user_email");
		int scrap_pheed_id =  (int) map.get("scrap_pheed_id");
		
		resultmap.put("scrap_user_email", scrap_user_email);
		resultmap.put("scrap_pheed_id", scrap_pheed_id);
		
		int res = ser.deleteScrap(resultmap);

		if (res > 0) {
			session.invalidate();
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

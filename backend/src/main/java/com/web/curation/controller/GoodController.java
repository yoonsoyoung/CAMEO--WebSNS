package com.web.curation.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import com.web.curation.model.good.Good;
import com.web.curation.service.GoodServiceImpl;
import com.web.curation.service.UserServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })


@CrossOrigin(origins = "*")
@RequestMapping("/good")
@RestController
public class GoodController {
	
	@Autowired
	private GoodServiceImpl gser;
	
	@Autowired
	private UserServiceImpl userser;
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	//삽입 
	
	@ApiOperation(value = "좋아요 등록", notes = "key를 good_my_email(현재 아이디), good_user_email(내가좋아요를누른유저), good_pheed_id(내가 좋아요를 누른 pheed_id)로 보내주세용")
	@PostMapping(value = "/insert")
	public Object insertGood(@Valid @RequestBody Map<String, Object> map ) throws Exception{
		
		int cnt = (int) map.get("good_pheed_id");
		
		String title = gser.selectPheedtitle(cnt);
		
		map.put("good_pheed_title", title);
		
		if(gser.insertGood(map) == 1) {
			return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(FAIL, HttpStatus.FORBIDDEN);
		}
			
	}
	
	// 내가 좋아요한 사람들의 리스트
	@ApiOperation(value = "좋아요 리스트" , notes = "내가 좋아요한 사람들의 리스트 ")
	@GetMapping("/list/{good_my_email}")
	public ResponseEntity<List<Good>> getGoodReqList(@PathVariable("good_my_email") String good_my_email) throws Exception{
		return new ResponseEntity<List<Good>>(gser.getGoodReqList(good_my_email), HttpStatus.OK);
	}
	
	// delete 부분 tmp 확인해서 1이면 지워질 수 있도록 service에서 처리 user_id, pheed_id
	@DeleteMapping("/delete")
	@ApiOperation(value = "좋아요 취소", notes = "good_my_email, good_pheed_id가 필요  ")
	public ResponseEntity<String> deleteGood(@RequestBody Map<String, String> map) throws Exception{
		
		if(gser.deleteGood(map) == 1) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
					
		}else {
			return new ResponseEntity<String>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
					
		}
		
		
	}
	
	@ApiOperation(value = "좋아요 수 카운트" , notes = "pheed_id를 보내주면 해당 게시글의 좋아요 수 카운트 반환 ")
	@GetMapping("/count/{good_pheed_id}")
	public Integer countGood(@PathVariable("good_pheed_id") String good_pheed_id) throws Exception{
		return gser.countGood(good_pheed_id);
	}
	
	
	

}

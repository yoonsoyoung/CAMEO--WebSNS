package com.web.curation.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.net.HttpHeaders;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.decoration.Decoration;
import com.web.curation.service.DecorationService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/decoration")
public class DecorationController {
	
	@Autowired
	private DecorationService service;
	
	public static final Logger logger = LoggerFactory.getLogger(DecorationController.class);
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	// 파일처리 컨트롤러
	@ApiOperation(value = "훈장 사진 등록", notes = "관리자만 새로운 훈장 사진을 등록한다", response = Map.class)
	@RequestMapping(value = "/insert", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Map<String, Object>> insertDecoration(@RequestPart("file") MultipartFile[] files){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
//			Decoration decoration = service.getJson(decoraitonstr);
			int cntPic = 0;
			
			
			cntPic = service.insertDecoration(files);
			System.out.println("cntpic :" + cntPic);
			
			
			if(cntPic != 0) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (DataAccessException e) {
			// TODO: handle exception
			resultMap.put("message", "DB 문제");
			status = HttpStatus.FAILED_DEPENDENCY;
		} catch(Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
		
	}
	
	// 파일불러오기
	// user_id를 가지고 
	// user_total_score을
	// 받아서 user_total_score에 맞는 이미지 뿌려주기

	
//	@ApiOperation(value = "훈장 사진 불러오기", notes = "user_id를 가지고 훈장사진을 불러올 수 있다")
//	@GetMapping(value = "/read",
//			// 출력 데이터 포맷 정의
//			produces = {MediaType.IMAGE_JPEG_VALUE}
//			
//			)
//	public ResponseEntity<byte[]> readDecoration(@RequestParam String user_id){
//		Map<String, Object> resultMap = new HashMap<>();
//		byte[] imageByteArray = null;
//		HttpStatus status = null;
//		
//		try {
//			int score = service.selectScore(user_id);
//			
//			String absolutePath =
//		        	new File("").getAbsolutePath() + File.separator + File.separator;
//			String path;
//			
//			Decoration pic = service.selectPic(score);
//			
//			System.out.println(pic.getSaveName());
//			
//			path = "C:\\decoration\\upload\\" + pic.getSaveName();
//			InputStream imageStream = new FileInputStream(path);
//			imageByteArray = IOUtils.toByteArray(imageStream);
//			imageStream.close();
//			
//			if(score > 0) {
//				resultMap.put("message", SUCCESS);
//				resultMap.put("score", score);
//				resultMap.put("pic", imageByteArray);
//				status = HttpStatus.ACCEPTED;
//			}else {
//				resultMap.put("message", FAIL);
//				status = HttpStatus.ACCEPTED;
//				
//			}
//		} catch (Exception e) {
//			resultMap.put("message",  e.getMessage());
//			status = HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//		
//		return new ResponseEntity<byte[]>(imageByteArray, status);
//		
//	}
	// 훈장점수를 가지고올 컨트롤러점수를 가지고와서 savename을가지고오기
	@ApiOperation(value = "훈장 점수", notes = "user_id를 가지고 savename을 불러올 수 있다")
	@GetMapping("/score/{user_id}")
	public ResponseEntity<Map<String,Object>> decoScore(@PathVariable String user_id) throws Exception{
		Map<String,Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			//스코어불러오고
			int score = service.selectScore(user_id);
			
			Decoration pic = service.selectPic(score);
			
			if(score>=0) {
				resultMap.put("message", SUCCESS);
				resultMap.put("score", score);
				resultMap.put("savename", pic.getSaveName());
				status = HttpStatus.ACCEPTED;
			}else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			// TODO: handle exception
		}
			return new ResponseEntity<Map<String,Object>>(resultMap,status);
	}
	
	
	@GetMapping("/read/{fileName}")
	@ApiOperation(value = "파일이름", notes = "fileName은 savename으로넘겨면됨")
    public Object bFile(@PathVariable String fileName) throws MalformedURLException{
		Resource resource = new FileSystemResource(
				"/home/ubuntu/S05P12C202/frontend/src/assets/images/decoration/" + fileName);

        if(fileName == null) {
            final BasicResponse result = new BasicResponse();
            result.status = true;
            result.data = "success";
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        return ResponseEntity.ok()
        		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename() + "")
				.body(resource);
    }
	
	

	


}

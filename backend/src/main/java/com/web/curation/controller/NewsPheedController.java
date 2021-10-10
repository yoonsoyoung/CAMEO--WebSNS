package com.web.curation.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.pheed.Pheed;
import com.web.curation.model.pheedcomment.PheedComment;
import com.web.curation.model.pheedpic.PheedPic;
import com.web.curation.service.NewsPheedService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/*
 * !!!!!!!!!!!!!!!!!!필수!!!!!!!!!!!!!!!!!!!!!
 *  사진 db에 넣는거 완료하고 나면 꼭꼬꼬꼬꼮 Pheed 생성자 정리하기!!!!!!!!!!!
 * 
 */

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = "*")
@RequestMapping("/newspheed")
@RestController
public class NewsPheedController {
	@Autowired
	private NewsPheedService ser;

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@ApiOperation(value = "뉴스피드 글 작성", notes = "새로운 글을 작성한다.", response = Map.class)
	@RequestMapping(value = "/write", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Map<String, Object>> writePheed(@RequestPart("pheed") String pheedstr,
			@RequestPart("file") MultipartFile[] files) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			Pheed pheed = ser.getJson(pheedstr);
			int pheed_id = pheed.getPheed_id();
			int cntPic = 0;

			cntPic = ser.insertPheed(pheed, files);
			if (cntPic != 0) {
				resultMap.put("message", SUCCESS);
				resultMap.put("pheedId", cntPic);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (DataAccessException e) {
			resultMap.put("message", "DB 문제");
			status = HttpStatus.FAILED_DEPENDENCY;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 본인이 팔로잉한 사람들이 올리는 피드를 검색하고 그리고 각 피드 마다 사진을 검색해서 팔로워 리스트 리턴한 것 처럼 리턴
	@ApiOperation(value = "팔로잉 뉴스피드 목록 보기", notes = "본인이 팔로우한 사람들이 작성한 뉴스피드 목록을 볼 수 있다.", response = Map.class)
	@GetMapping("/{user_email}")
	public ResponseEntity<Map<String, Object>> getNewsPheedList(@PathVariable String user_email) {
		Map<String, Object> resultMap = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		HttpStatus status = null;
		List<Map<String, Object>> list = new ArrayList<>();
		try {
			list = ser.selectPheedList(user_email);
			if (list != null) {
				resultMap.put("pheedList", list);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;

			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "뉴스피드 수정하기", notes = "본인이 작성한 뉴스피드를 수정할 수 있다.", response = Map.class)
	@RequestMapping(value = "/modify", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Map<String, Object>> modifyNewsPheed(@RequestPart("pheed") String pheedstr,
			@RequestPart("file") MultipartFile[] files) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			Pheed pheed = ser.getJson(pheedstr);
			int pheedId = pheed.getPheed_id();
			// db에 저장되어 있는 파일 불러오기
			List<PheedPic> fileList = ser.getPicList(pheedId);

			// 새로 받아온 파일들
			List<MultipartFile> multipartList = new ArrayList<>();
			for (MultipartFile m : files) {
				multipartList.add(m);
			}
			// 저장할 파일들 담는 리스트
			List<MultipartFile> addFileList = new ArrayList<>();

			if (CollectionUtils.isEmpty(fileList)) { // db에 아예 존재 x
				if (!CollectionUtils.isEmpty(multipartList)) { // 새로 받은 파일도 없음
					for (MultipartFile mpf : files) {
						addFileList.add(mpf);
					}
				}
			} else { // db에 존재하긴 함
				if (!CollectionUtils.isEmpty(addFileList)) { // 새로받은 파일 없어
					// 전부 파일 지우기
					for (PheedPic pp : fileList) {
						ser.deletePic(pp.getPheedId());
					}
				} else { // 새로 받아온 파일들 하나 이상 존재
					// db에 저장할 사진 리스트 오리지널 이름 담을 리스트
					List<String> dbOriginNameList = new ArrayList<>();
					// db 원본 파일 명
					for (PheedPic pp : fileList) {
						String originName = pp.getOriginalName();
						if (!multipartList.contains(originName)) // 만약 서버에 저장된 파일 중 전달되어 있는 파일 리스트에
							// 이름이 없다면
							ser.deletePic(pp.getPheedId()); // 제거
						else {
							dbOriginNameList.add(originName);
						}
					}

					for (MultipartFile multi : multipartList) { // 전달되어온 파일 하나씩 검사
						String multipartOrigName = multi.getOriginalFilename(); // 파일 원본 명 얻어오기
						if (!dbOriginNameList.contains(multipartOrigName)) { // db에 없는 파일이면
							addFileList.add(multi); // db에 저장할 파일 목록에 추가
						}
					}
				}
			}

			MultipartFile[] finalFileList = new MultipartFile[addFileList.size()];
			addFileList.toArray(finalFileList);

			int cnt = ser.updatePheed(pheed, finalFileList);
			if (cnt != 0) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "뉴스피드 삭제하기", notes = "본인이 작성한 뉴스피드를 삭제할 수 있다.", response = Map.class)
	@DeleteMapping("/delete/{pheed_id}")
	public ResponseEntity<Map<String, Object>> deleteNewsPheed(@PathVariable int pheed_id) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			int cnt = ser.deletePheed(pheed_id);
			if (cnt != 0) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "피드 조회", notes = "피드의 아이디를 가지고 하나의 피드를 조회할 수 있다.", response = Map.class)
	@GetMapping("/read")
	public ResponseEntity<Map<String, Object>> readNewsPheed(@RequestParam int pheedId) {
		Map<String, Object> resultMap = new HashMap<>();
		byte[] imageByteArray = null;
		HttpStatus status = null;
		try {
			Map<String, Object> map = ser.selectPheed(pheedId);
			if (map != null) {
				resultMap.put("pheed", map);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "뉴스피드 전부 가져오기", notes = "모든 뉴스피드를 볼 수 있다.", response = Map.class)
	@GetMapping("/getAllPheed")
	public ResponseEntity<Map<String, Object>> getAllNewsPheedList() {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			List<Map<String, Object>> list = ser.selectAllPheed();
			if (list != null) {
				resultMap.put("pheedList", list);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "뉴스피드 댓글 등록하기", notes = "피드에 댓글을 등록할 수 있다.", response = Map.class)
	@PostMapping("/writeComment")
	public ResponseEntity<Map<String, Object>> writeComment(@RequestBody PheedComment pheedComment) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			int cnt = ser.insertComment(pheedComment);
			if (cnt != 0) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "뉴스피드 댓글 수정하기", notes = "피드에 댓글을 수정할 수 있다.", response = Map.class)
	@PutMapping("/updateComment")
	public ResponseEntity<Map<String, Object>> updateComment(@RequestBody PheedComment pheedComment) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			int cnt = ser.updateComment(pheedComment);
			if (cnt != 0) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "뉴스피드 댓글 삭제하기", notes = "피드에 댓글을 삭제할 수 있다.", response = Map.class)
	@DeleteMapping("/deleteComment")
	public ResponseEntity<Map<String, Object>> deleteComment(@RequestParam int pheedCommentId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			int cnt = ser.deleteComment(pheedCommentId);
			if (cnt != 0) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	private String rnd(String originalName, byte[] fileData, String path) throws Exception {
		UUID uuid = UUID.randomUUID();
		String savedName = uuid.toString() + "_" + originalName;
		File target = new File(path, savedName);

		FileCopyUtils.copy(fileData, target);
		return savedName;
	}

	// 피드 사진 가져오기
	@ApiOperation(value = "피드 사진 불러오기", notes = "피드의 사진을 반환한다. pheedId는 int, fileName은 string")
	@GetMapping(value = "/getPheedPic/{pheedId}/{fileName}")
	public Object getUserPic(@PathVariable int pheedId,  @PathVariable String fileName) throws MalformedURLException {
		Resource resource = new FileSystemResource(
				"/home/ubuntu/S05P12C202/frontend/src/assets/images/pheed/" + pheedId +"/"+ fileName);

		if (fileName == null) {
			final BasicResponse result = new BasicResponse();
			result.status = true;
			result.data = "success";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename() + "")
				.body(resource);
	}
	
	@ApiOperation(value = "내가 쓴 뉴스피드 보기", notes = "본인이 작성한 뉴스피드의 목록을 볼 수 있다.>_<", response = Map.class)
	@GetMapping("/getMyPheedList/{userEmail}")
	public ResponseEntity<Map<String, Object>> readMyNewsPheed(@PathVariable String userEmail) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			List<Map<String, Object>> list = ser.selectAllPheedByEmail(userEmail);
			if (list != null) {
				resultMap.put("pheedList", list);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

//	public List<String> readNewsPheed4Pic(int pheedId) {
//		// 피드 아이디를 받아서 
//		// 파일 아이디로 savename 찾고
//		List<PheedPic> picList;
//		List<String> pathList= new ArrayList<>();
//		try {
//			picList = ser.getPicList(pheedId);
//			// 여기서 파일 아이디 넘겨주면 경로를 피드 아이디 +
//			for (PheedPic pp : picList) {
//				String str = pp.getSaveName();
//				// 경로 + saveName pathList에 넣기
//				/*
//				 * 
//				 * 이 부분 수정하기 
//				 * 
//				 */
//				String path = "경로" + str;
//				pathList.add(path);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return pathList;
//	}

}

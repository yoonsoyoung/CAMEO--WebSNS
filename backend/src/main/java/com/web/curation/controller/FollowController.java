package com.web.curation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.follow.Follow;
import com.web.curation.model.user.User;
import com.web.curation.service.FollowServiceImpl;
import com.web.curation.service.UserServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = "*")
@RequestMapping("/follow")
@RestController
public class FollowController {
	@Autowired
	private FollowServiceImpl fser;

	@Autowired
	private UserServiceImpl userSer;

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@ApiOperation(value = "팔로워 목록", notes = "유저를 팔로우하고 있는 다른 유저들의 목록을 반환한다. 나를 팔로우 하고 있는 유저들 목록", response = Map.class)
	@GetMapping("/following")
	public ResponseEntity<Map<String, Object>> getFollowersList(@RequestParam String user_email) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			List<Map<String, Object>> followersList = new ArrayList<>();
			List<Follow> list = fser.selectFollowingList(user_email);
			for (Follow f : list) {
				Map<String, Object> m = new HashMap<>();
				String email = f.getFollowing();
				User user = userSer.getUser(email);
				boolean mute = f.isMute();
				boolean isFollowEachOther = f.isFollowingEachOther();
				m.put("userInfo", user);
				m.put("mute", mute);
				m.put("isFollowEachOther", isFollowEachOther);
				followersList.add(m);
			}
			if (user_email != null) {
				resultMap.put("followersList", followersList);
				if(followersList.isEmpty()) {
					resultMap.put("message", "아무도 당신을 팔로우 하고 있지 않습니다.");
					status = HttpStatus.ACCEPTED;
				}else {
					resultMap.put("message", SUCCESS);
					status = HttpStatus.ACCEPTED;
				}
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

	@ApiOperation(value = "팔로잉 목록", notes = "유저가 현재 팔로우 하고 있는 유저들의목록을 반환한다. 내가 팔로우 하고 있는 유저들 목록", response = Map.class)
	@GetMapping("/followers")
	public ResponseEntity<Map<String, Object>> getFollowingList(@RequestParam String user_email) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			List<Map<String, Object>> followingList = new ArrayList<>();
			List<Follow> list = fser.selectFollowersList(user_email);
			for (Follow f : list) {
				Map<String, Object> m = new HashMap<>();
				String email = f.getFollowers();
				User user = userSer.getUser(email);
				boolean mute = f.isMute();
				boolean isFollowEachOther = f.isFollowingEachOther();
				m.put("userInfo", user);
				m.put("mute", mute);
				m.put("isFollowEachOther", isFollowEachOther);
				followingList.add(m);
			}
			
			if (user_email != null) {
				resultMap.put("followingList", followingList);
				if(followingList.isEmpty()) {
					resultMap.put("message", "당신은 현재 팔로잉하고 있는 다른 유저가 없습니다.");
					status = HttpStatus.ACCEPTED;
				}else {
					resultMap.put("message", SUCCESS);
					status = HttpStatus.ACCEPTED;
				}
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

	@ApiOperation(value = "팔로잉 요청 보내기", notes = "다른 유저에게 팔로잉 요청을 보낸다. following: 팔로우 요청 보내는 사람 / temp_followers: 팔로우 요청 받는 사람", response = Map.class)
	@PostMapping("/reqfollow")
	public ResponseEntity<Map<String, Object>> sendFollowingRequest(@RequestBody Map<String, String> map) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		// map에는 following = 팔로잉 요청하는 사람 이메일과
		// temp_followers = 팔로잉 당하는 사람의 이메일이 들어있다.
		try {
			/*
			 * 만약 cnt의 값이 0이라면 중복된 요청은 아니지만 db에 등록이 되지 않았음 
			 * 1이라면 db에 등록되었음 
			 * 2라면 중복된 요청이기 때문에
			 * 등록되지 않았음
			 */
			int cnt = fser.sendFollowReq(map);
			if (cnt == 1) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else if (cnt == 2) {
				resultMap.put("message", "duplicated request");
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

	@ApiOperation(value = "팔로잉 요청 리스트 확인", notes = "팔로잉 요청을 보낸 유저들의 목록을 본다. temp_followers: temp_followers한테 팔로우 요청 보낸 유저들의 목록을 확인할 수 있", response = Map.class)
	@GetMapping("/reqfollowlist")
	public ResponseEntity<Map<String, Object>> FollowingRequestList(@RequestParam String temp_followers) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			List<Map<String, Object>> followingReqList = fser.getFollowReqList(temp_followers);
			// 팔로우 리퀘스트 리스트 잘 받아 왔고 그 리스트가 0보다 클 때
			if (followingReqList.size() > 0) {
				resultMap.put("followerList", followingReqList);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else if (followingReqList.size() == 0) {
				resultMap.put("message", "팔로잉 요청을 보낸 사람이 없다.");
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

	// 2. b는 그 팔로우 요청을 받아들이거나 거절(삭제)할 수 있다.
	// 2-1. b는 그 팔로우 요청을 받아들일 수 있다.
	@ApiOperation(value = "팔로잉 요청 받아들이기", notes = "다른 유저가 보낸 팔로잉 요청을 받아들인다. 팔로잉-팔로워 관계 성립 -> 팔로잉 목록 중 한 관계를 선택하여서 팔로우 승인/거절하는 것. following: 팔로우 요청 보낸 사람 temp_followers: 팔로우 요청 받는 사람", response = Map.class)
	@PostMapping("/acceptfollowreq")
	public ResponseEntity<Map<String, Object>> acceptFollowingRequest(@RequestBody Map<String, String> map) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			int cnt = fser.updateFollowReq(map);
			if (cnt > 0) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 2. b는 그 팔로우 요청을 받아들이거나 거절(삭제)할 수 있다.
	// 2-2. b는 그 팔로우 요청을 거절(삭제)할 수 있다.
	@ApiOperation(value = "팔로잉 요청 거절", notes = "다른 유저가 보낸 팔로잉 요청을 거절한다.팔로잉 목록 중 한 관계를 선택하여서 팔로우 승인/거절하는 것. following: 팔로우 요청 보낸 사람 temp_followers: 팔로우 요청 받는 사람", response = Map.class)
	@PostMapping("/denyfollowreq")
	public ResponseEntity<Map<String, Object>> denyFollowingRequest(@RequestBody Map<String, String> map) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			int cnt = fser.deleteFollowReq(map);

			if (cnt > 0) {
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

	// 언팔로우
	@ApiOperation(value = "언팔로우", notes = "팔로잉 하고 있는 유저를 언팔로우한다. following : 팔로우 하고 있는 사람 followers: 팔로우 당하고 있는 사람", response = Map.class)
	@RequestMapping(method = RequestMethod.DELETE, value = "/unfollow/{following}/{followers}")
	public ResponseEntity<Map<String, Object>> unfollow(@PathVariable String following,
			@PathVariable String followers) {

		// map에는 following = 팔로잉 요청하는 사람 이메일과
		// temp_followers = 팔로잉 당하는 사람의 이메일이 들어있다.

		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		Map<String, String> map = new HashMap<>();
		map.put("following", following);
		map.put("followers", followers);
		try {
			int cnt = fser.unfollow(map);
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
	
	@ApiOperation(value = "팔로잉 뮤트", notes = "팔로잉 하고 있는 유저를 뮤트한다.(팔로잉관계는 유지되지만 그 유저가 올린 피드는 내 뉴스피드에 보이지 않음) following : 팔로우 하고 있는 사람(누군가를 뮤트할 사람) followers: 팔로우 당하고 있는 사람(뮤트 당할 사람)", response = Map.class)
	@PutMapping("/mute")
	public ResponseEntity<Map<String, Object>> muteFollowers(@RequestBody Map<String, String> map) {

		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			int cnt = fser.muteFollowers(map);
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
	
	
	@ApiOperation(value = "팔로잉 언뮤트", notes = "팔로잉 하고 있는 유저를 언뮤트한다. 뮤트 삭제 following : 팔로우 하고 있는 사람(뮤트 한 사람) followers: 팔로우 당하고 있는 사람(뮤트 당한 사람=뮤트 풀어줄 사람)", response = Map.class)
	@PutMapping("/unmute")
	public ResponseEntity<Map<String, Object>> unmuteFollowers(@RequestBody Map<String, String> map) {

		// map에는 following = 팔로잉 요청하는 사람 이메일과
		// temp_followers = 팔로잉 당하는 사람의 이메일이 들어있다.

		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			int cnt = fser.unmuteFollowers(map);
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
	
	@ApiOperation(value = "팔로잉 상태", notes = "following: 지금 로그인한 사용자 followers: 프로필 보고 있는 유저", response = Map.class)
	@GetMapping("/relation/{following}/{followers}")
	public ResponseEntity<Map<String, Object>> showRelation(@PathVariable String following, @PathVariable String followers) {

		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			Map<String, String> map = new HashMap<>();
			map.put("following", following);
			map.put("followers", followers);
			int cnt = fser.showRelation(map);
			if (cnt == 1) {
				resultMap.put("관계", "이 사람에게 나는 이미 팔로우 요청을 보냈다.");
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else if (cnt == 2) {
				resultMap.put("관계", "이미 이 사람 팔로우 중");
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else if (cnt == 3) {
				resultMap.put("관계", "이 사람에게 팔로우 요청을 보내지도, 팔로우 중이지도 않음 아무 관계도 아니다");
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
	
	@ApiOperation(value = "팔로잉 요청 취소", notes = "following: 지금 로그인한 사용자 temp_followers: 팔로잉 요청 보낸 사람", response = Map.class)
	@DeleteMapping("/cancelFollowReq/{following}/{temp_followers}")
	public ResponseEntity<Map<String, Object>> cancelFollowReq(@PathVariable String following, @PathVariable String temp_followers) {

		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			Map<String, String> map = new HashMap<>();
			map.put("following", following);
			map.put("temp_followers", temp_followers);
			int cnt = fser.cancelFollowReq(map);
			if (cnt == 1) {
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
}

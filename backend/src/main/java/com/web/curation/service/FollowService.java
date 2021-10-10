package com.web.curation.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.web.curation.model.follow.Follow;

public interface FollowService {
	// 팔로워 리스트
	List<Follow> selectFollowersList(String following) throws Exception;

	// 팔로잉 리스트
	List<Follow> selectFollowingList(String followers) throws Exception;

	// 팔로우 요청 보내기 temp_follower에 넣기
	int sendFollowReq(Map<String, String> map) throws Exception;

	// 팔로잉 요청 들어온 목록 조회
	List<Map<String, Object>> getFollowReqList(String following) throws Exception;

	// 2. update follow
	int updateFollowReq(Map<String, String> map) throws Exception;

	// 팔로잉 요청 삭제
	// delete
	int deleteFollowReq(Map<String, String> map) throws Exception;

	// 언팔로우
	// delete
	int unfollow(Map<String, String> map) throws Exception;

	// 팔로잉하고 있는 사람 뮤트
	int muteFollowers(Map<String, String> map) throws Exception;

	// 팔로잉하고 있는 사람 뮤트
	int unmuteFollowers(Map<String, String> map) throws Exception;
	
	int chkThisPersonFMe(Map<String, String> map) throws Exception;

	int chkIFThisPerson(Map<String, String> map) throws Exception;

	int showRelation(Map<String, String> map) throws Exception;

	int cancelFollowReq(Map<String, String> map) throws Exception;
}

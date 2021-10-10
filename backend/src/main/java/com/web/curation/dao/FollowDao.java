package com.web.curation.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.follow.Follow;

@Mapper
public interface FollowDao {

	// 팔로워 리스트
	List<Follow> selectFollowersList(String following) throws SQLException;

	// 팔로잉 리스트
	List<Follow> selectFollowingList(String followers) throws SQLException;

	// 팔로우 요청 보내기 temp_follower에 넣기
	// 1. insert temp_follower
	int sendFollowReq(Map<String, String> map) throws SQLException;

	// 팔로잉 요청 들어온 목록 조회
	List<String> getFollowReqList(String following) throws SQLException;

	// 팔로잉 요청 받아들임
	// 1. following이 a이고 temp_follower가 b인거 찾기 id select
	int getFollowReq(Map<String, String> map) throws SQLException;

	// 2. update follow
	int updateFollowReqEO(Map<String, Object> map) throws SQLException;

	int updateFollowReqNEO(Map<String, Object> map) throws SQLException;

	// 팔로잉 요청 삭제
	// delete
	int deleteFollowReq(int follow_id) throws SQLException;

	// 언팔로우
	// delete
	int deleteFollow(Map<String, String> map) throws SQLException;

	// 팔로우 요청 중복 체크
	int chkFollowReq(Map<String, String> map) throws SQLException;

	// 팔로잉하고 있는 사람 뮤트
	int muteFollowers(int follow_id) throws SQLException;

	// 팔로잉하고 있는 사람 뮤트
	int unmuteFollowers(int follow_id) throws SQLException;

	// 나를 팔로우 요청 보낸 사람이 내가 팔로잉 하고 있는 사람인지 확인
	Integer chkFollowEachOther(Map<String, String> map) throws SQLException;

	// null값일수도 있어서 not int, Integer
	Integer unFollowEachOther(Map<String, String> map) throws SQLException;

	int chkThisPersonFMe(Map<String, String> map) throws SQLException;

	int chkIFThisPerson(Map<String, String> map) throws SQLException;
	
	int selectFollowRelation(Map<String, String> map) throws SQLException;
	
	int cancelFollowReq(Map<String, String> map) throws SQLException;
}

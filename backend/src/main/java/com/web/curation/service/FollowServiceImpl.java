package com.web.curation.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.FollowDao;
import com.web.curation.dao.UserDao;
import com.web.curation.model.follow.Follow;
import com.web.curation.model.user.User;

@Service
public class FollowServiceImpl implements FollowService {

	private static final String FollowingEo = null;
	@Autowired
	private FollowDao followdao;
	
	@Autowired
	private UserDao userdao;
	
	private final String path = "/home/ubuntu/S05P12C202/backend/images/userPic/";

	@Override
	public List<Follow> selectFollowersList(String following) throws Exception {
		return followdao.selectFollowersList(following);
	}

	@Override
	public List<Follow> selectFollowingList(String followers) throws Exception {
		return followdao.selectFollowingList(followers);
	}

	@Override
	public int sendFollowReq(Map<String, String> map) throws Exception {
		// 여기서 체크하자
		int cntChkFollowReq = followdao.chkFollowReq(map);
		int cntSendFollowReq = 0; // 뭔가 db든 이런데랑 연결 안되고 그냥 넘어가면 0
		if (cntChkFollowReq > 0) { // 이미 중복된 요청이라면
			cntSendFollowReq = 2; // 2를
		} else { // 만약 정상적으로 followReq 보내주면 1
			cntSendFollowReq = followdao.sendFollowReq(map);
		}
		return cntSendFollowReq;
	}

	@Override
	public List<Map<String, Object>> getFollowReqList(String following) throws Exception {
		List<String> emailList = followdao.getFollowReqList(following);
		List<Map<String, Object>> res = new ArrayList<>();
		for (String s : emailList) {
			Map<String, Object> map = new HashMap<>();
			User user = userdao.getUser(s);
			map.put("user", user);
			String userPicPath = path + s + "/" + user.getUser_pic();
			map.put("userPicPath", userPicPath);
			res.add(map);
		}
		return res; 
	}

	@Override
	public int updateFollowReq(Map<String, String> map) throws SQLException {
		int followId = followdao.getFollowReq(map);
		int res = 0;
		Map<String, Object> acceptFollow = new HashMap<>();
		Map<String, String> chkFollowEachOther = new HashMap<>();

		// 나 팔로잉 요청 보낸 사람을 나는 기존에 팔로잉하였나? 기존 관계 isFollowingEachOther update
		chkFollowEachOther.put("following", map.get("temp_followers"));
		chkFollowEachOther.put("followers", map.get("following"));
		int FollowingEO = followdao.chkFollowEachOther(chkFollowEachOther);
		// 팔로잉 accept 준비
		acceptFollow.put("follow_id", followId);
		acceptFollow.put("followers", map.get("temp_followers"));
		if(FollowingEO == 0) { // 이제 맞팔관계 성립되려고 함
			
			res = followdao.updateFollowReqNEO(acceptFollow);
		}else { // 맞팔관계는 아닌거 나는 이 사람 팔로우 한 적 없음
			res = followdao.updateFollowReqEO(acceptFollow);
		}
		return res;
	}

	@Override
	public int deleteFollowReq(Map<String, String> map) throws SQLException {
		int followId = followdao.getFollowReq(map);
		return followdao.deleteFollowReq(followId);
	}

	@Override
	public int unfollow(Map<String, String> map) throws Exception {
		
		Map<String, String> updateEOStatus = new HashMap<>();
		updateEOStatus.put("following", map.get("followers"));
		updateEOStatus.put("followers", map.get("following"));
		Object updateStatusCnt = followdao.unFollowEachOther(updateEOStatus);
		int deleteCnt = followdao.deleteFollow(map);
		return deleteCnt;
	}

	@Override
	public int muteFollowers(Map<String, String> map) throws Exception {
		int follow_id = followdao.selectFollowRelation(map);
		return followdao.muteFollowers(follow_id);
	}

	@Override
	public int unmuteFollowers(Map<String, String> map) throws Exception {
		int follow_id = followdao.selectFollowRelation(map);
		return followdao.unmuteFollowers(follow_id);
	}

	@Override
	public int chkThisPersonFMe(Map<String, String> map) throws Exception {
		return followdao.chkThisPersonFMe(map);
	}

	// 내가 지금 이 사람을 팔로우 하고 있는지?
	@Override
	public int chkIFThisPerson(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return followdao.chkIFThisPerson(map);
	}

	@Override
	public int showRelation(Map<String, String> map) throws Exception {
		int res = 3;
		Map<String, String> mapForFReq = new HashMap<>();
		mapForFReq.put("following", map.get("following"));
		mapForFReq.put("temp_followers", map.get("followers"));

		int followReq = followdao.chkFollowReq(mapForFReq);
		System.out.println("팔로우 요청 상태 " + followReq);
		if(followReq == 1) { //팔로우 요청 보낸 상태
			res = 1;
			return res;
		}
		int alreadyF = followdao.chkIFThisPerson(map);
		if(alreadyF == 1) {
			res = 2;
			return res;
		}
		System.out.println("팔로우 현황: " + alreadyF);
		return res;
	}

	@Override
	public int cancelFollowReq(Map<String, String> map) throws Exception {
		return followdao.cancelFollowReq(map);
	}
}

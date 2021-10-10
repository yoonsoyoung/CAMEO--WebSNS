package com.web.curation.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.UserDao;

@Service
public class OAuthServiceImpl implements OAuthService{

	@Autowired 
	UserDao userDao;
	
	@Override
	public int saveOrUpdate(Map<String, String> map) throws Exception {
		// 일단 user_email을 찾아서 
		String user_email = map.get("user_email");
		
		System.out.println("여기는 oAuthService에서 부른 user_email: " + user_email);
		
		int isThisUserExisted= userDao.searchUserByEmailBySns(user_email);
		
		System.out.println("여기는 oAuthService에서 부른 isThisUserExisted: " + isThisUserExisted);
		
		int cnt = 0;
		System.out.println("으아아아 " + isThisUserExisted);
		
		if(isThisUserExisted == 0) { // 회원가입
			cnt = userDao.insertUserViaSNS(map);
			System.out.println("수행하고 난 뒤 : " + cnt);
		}else {
			cnt = userDao.updateUserViaSNS(map);
			System.out.println("수행하고 난 뒤 : " + cnt);
		}
		return cnt;
	}
}

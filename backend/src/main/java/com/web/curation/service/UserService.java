package com.web.curation.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.web.curation.model.user.User;

public interface UserService {

   // 로그인
   User login(User user);

   // 회원인증 key
   int updateAuthKey(Map<String, String> map) throws Exception;

   // 회원인증 상태
   int updateAuthStatus(Map<String, String> map) throws Exception;

   // 회원가입
   int insertUser(User user) throws Exception;

   // 회원정보 수정
   int updateUser(User user) throws Exception;

   // 회원탈퇴
   int deleteUser(String user_email) throws Exception;

   // 이메일 중복 체크
   int emailChk(String user_email) throws Exception;

   // 아이디 중복 체크
   int idChk(String user_id) throws Exception;

   // 회원정보 조회
   User getUser(String user_email) throws Exception;

   // 타 유저 검색 by user_id
   public List<User> searchUserById(String user_id) throws Exception;

   // 타 유저 검색 by user_cbti
   public List<User> searchUserByCBTI(String user_cbti) throws Exception;

   // CBTI 저장
   public int saveCBTI(Map<String, String> map) throws Exception;

   // 유저 프로필 사진 가져오기
   public String getUserPic(String userEmail) throws Exception;

   public int insertUserPic(MultipartFile file, String userEmail) throws Exception;

   // 유저 프로필 사진 삭제
   public int deleteUserPic(String userEmail) throws Exception;

   User getJson(String User) throws Exception;

   
   public int updateIDUser(Map<String, String> map) throws Exception;
   
   public int updatePWUser(Map<String, String> map) throws Exception;

   // 모든 회원의 모든 정보 가져오기
   // public List<User> getAllUser() throws Exception;

}
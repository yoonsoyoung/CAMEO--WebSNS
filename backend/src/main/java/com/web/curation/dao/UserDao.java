package com.web.curation.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.web.curation.model.user.User;

@Mapper
public interface UserDao {

   // 로그인
   User login(User user);

   // 회원인증 key
   int updateAuthKey(Map<String, String> map) throws SQLException;

   // 회원인증 상태
   int updateAuthStatus(Map<String, String> map) throws SQLException;

   // 회원가입
   int insertUser(User user) throws SQLException;

   // 회원정보 수정
   int updateUser(User user) throws SQLException;

   // 회원탈퇴
   int deleteUser(String user_email) throws SQLException;

   // 이메일 중복 체크
   int emailChk(String user_email) throws SQLException;

   // 아이디 중복 체크
   int idChk(String user_id) throws SQLException;

   // 회원정보 조회
   User getUser(String user_email) throws SQLException;

   // 타 유저 검색 by user_id
   public List<User> searchUserById(String user_id) throws SQLException;

   // 타 유저 검색 by user_cbti
   public List<User> searchUserByCBTI(String user_cbti) throws SQLException;

   // CBTI 저장
   public int saveCBTI(Map<String, String> map) throws SQLException;

   // 유저 프로필 사진 가져오기
   public String getUserPic(String user_email) throws SQLException;

   // 유저 프로필 사진 등록
   public int registerUserPic(Map<String, String> map) throws SQLException;

   // 유저 프로필 사진 삭제
   public int deleteUserPic(String user_email) throws SQLException;

   // sns 유저 등록
   public int insertUserViaSNS(Map<String, String> map) throws SQLException;
   
   // sns 유저 수정
   public int updateUserViaSNS(Map<String, String> map) throws SQLException;
   
   // sns 유저 수정
   public Integer searchUserByEmail(String user_email) throws SQLException;
   
   public Integer searchUserByEmailBySns(String user_email) throws SQLException;
   public int updateIDUser(Map<String, String> map) throws SQLException;
   
   public int updatePWUser(Map<String, String> map) throws SQLException;

   // 모든 회원의 모든 정보 가져오기
   // public List<User> getAllUser() throws SQLException;
   public String getUserIdByEmail(String user_email) throws SQLException;
   
}
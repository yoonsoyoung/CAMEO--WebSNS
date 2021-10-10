package com.web.curation.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.curation.dao.UserDao;
import com.web.curation.dao.UserPicDao;
import com.web.curation.model.user.User;
import com.web.curation.model.userpic.UserPic;
import com.web.curation.utils.FileUtils;

@Service
public class UserServiceImpl implements UserService {

   @Autowired
   private UserDao dao;

   @Autowired
   private UserPicDao picDao;

   @Autowired
   private FileUtils fileUtils;

   // 로그인
   @Override
   public User login(User user) {

      if (user.getUser_id() == null || user.getUser_password() == null)
         return null;
      return dao.login(user);
   }

   // 회원인증 key
   @Override
   public int updateAuthKey(Map<String, String> map) throws Exception {
      return dao.updateAuthKey(map);
   }

   // 회원인증 상태
   @Override
   public int updateAuthStatus(Map<String, String> map) throws Exception {
      return dao.updateAuthStatus(map);
   }

   // 회원가입
   @Override
   public int insertUser(User user) throws Exception {
      return dao.insertUser(user);
   }

   // 회원정보 수정
   @Override
   public int updateUser(User user) throws Exception {
      return dao.updateUser(user);
   }

   // 회원탈퇴
   @Override
   public int deleteUser(String user_email) throws Exception {
      return dao.deleteUser(user_email);
   }

   // 이메일 중복체크
   @Override
   public int emailChk(String user_email) throws Exception {
      return dao.emailChk(user_email);
   }

   // 아이디 중복체크
   @Override
   public int idChk(String user_id) throws Exception {
      return dao.idChk(user_id);
   }

   // 회원정보 조회
   @Override
   public User getUser(String user_email) throws Exception {
      System.out.println(user_email);
      return dao.getUser(user_email);
   }

   // 타 유저 검색 by user_id
   @Override
   public List<User> searchUserById(String user_id) throws Exception {
      return dao.searchUserById(user_id);
   }

   // 타 유저 검색 by user_cbti
   @Override
   public List<User> searchUserByCBTI(String user_cbti) throws Exception {
      return dao.searchUserByCBTI(user_cbti);
   }

   // CBTI 저장
   @Override
   public int saveCBTI(Map<String, String> map) throws SQLException {
      return dao.saveCBTI(map);
   }

   // 유저 프로필 사진 가져오기
   @Override
   public String getUserPic(String userEmail) throws Exception {
      String saveName = dao.getUserPic(userEmail);
      return saveName;
   }

   // 여기서 사진 테이블에 사진 등록하고
   // 유저 테이블의 user_pic을 null에서 exist? 뭐 이런 걸로 바꿔주기
   @Override
   public int insertUserPic(MultipartFile file, String userEmail) throws Exception {
      // 일단 사진 등록한 적 있는지 찾아
      int res = 0;
      UserPic up = fileUtils.uploadUserFile(file, userEmail);
      String user_pic = up.getSaveName();
      Map<String, String> map = new HashMap<>();
      map.put("user_email", userEmail);
      map.put("user_pic", user_pic);
      res = dao.registerUserPic(map); // 유저 테이블에만 저장하기
      return res;
   }

   // 유저 프로필 사진 삭제
   @Override
   public int deleteUserPic(String userEmail) throws Exception {
      int res = dao.deleteUserPic(userEmail);
      return res;
   }

   @Override
   public User getJson(String User) throws Exception {
      User userJson = new User();

      try {
         ObjectMapper objectMapper = new ObjectMapper();
         userJson = objectMapper.readValue(User, User.class);
      } catch (IOException err) {
         System.out.printf("Error", err.toString());
      }
      return userJson;
   }

   @Override
   public int updateIDUser(Map<String, String> map) throws Exception {
      return dao.updateIDUser(map);
   }

   @Override
   public int updatePWUser(Map<String, String> map) throws Exception {
      return dao.updatePWUser(map);
   }
}
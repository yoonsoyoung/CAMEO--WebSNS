package com.web.curation.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

import com.web.curation.dao.UserDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.user.User;
import com.web.curation.service.EmailService;
import com.web.curation.service.FollowServiceImpl;
import com.web.curation.service.JwtService;
import com.web.curation.service.JwtServiceImpl;
import com.web.curation.service.UserServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
      @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
      @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
      @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {

   public static final Logger logger = LoggerFactory.getLogger(UserController.class);

   @Autowired
   UserDao userDao;

   @Autowired
   private JwtService jwtService;

   @Autowired
   private UserServiceImpl service;

   @Autowired
   private EmailService mss;

   @Autowired
   private JwtServiceImpl jwtServiceImpl;

   @Autowired
   private FollowServiceImpl fser;

   private static final String SUCCESS = "success";
   private static final String FAIL = "fail";
   private static final String path = "";

   // 로그인
   @ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.")
   @PostMapping("/login")
   public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
      Map<String, Object> resultMap = new HashMap<>();
      HttpStatus status = null;

      try {
         User loginUser = service.login(user);
         if (loginUser != null) {
            // user_id였는데 바꿈
            String token = jwtServiceImpl.create("user_email", loginUser.getUser_email(), "access-token");// key,
                                                                                    // data,
            // subject
            // logger.debug("로그인 토큰정보 : {}", token);
            resultMap.put("member", loginUser);
            resultMap.put("access-token", token);
            resultMap.put("message", "success");
            status = HttpStatus.ACCEPTED;
         } else {
            resultMap.put("message", "fail");
            status = HttpStatus.ACCEPTED;
         }
      } catch (Exception e) {
         // logger.error("로그인 실패 : {}", e);
         resultMap.put("message", e.getMessage());
         status = HttpStatus.INTERNAL_SERVER_ERROR;
      }
      return new ResponseEntity<Map<String, Object>>(resultMap, status);
   }

   // 회원가입
   @PostMapping("/signUp")
   @ApiOperation(value = "회원가입")
   public Object insertUser(@Valid @RequestBody User user) throws Exception {
      // 일단 가입하면 기본 정보는 저장함 이때 authStatus = 0
      if (service.insertUser(user) == 1) {
         // 임의의 authKey 생성해서 이메일 보내주기
         String authKey = mss.sendAuthMail(user.getUser_email());
         user.setUser_authKey(authKey);

         Map<String, String> map = new HashMap<String, String>();
         map.put("user_email", user.getUser_email());
         map.put("user_authKey", user.getUser_authKey());
         System.out.println(map);

         // 이 authKey로 user정보 update userService updateAuthKey
         service.updateAuthKey(map);
         return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
      } else {
         return new ResponseEntity<>(FAIL, HttpStatus.FORBIDDEN);
      }
   }

   // 회원가입 확인
   @GetMapping("/signUpConfirm")
   @ApiOperation(value = "회원가입 확인")
   public Object signUpConfirm(@Valid @RequestParam Map<String, String> map) throws Exception {
      // email, authKey 가 일치할경우 authStatus 업데이트
      if (service.updateAuthStatus(map) > 0) {
         return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
      } else {

         return new ResponseEntity<>(FAIL, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
      }
   }

   @ApiOperation(value = "아이디 수정", notes = "아이디 수정 user_id, user_email")
   @PostMapping(value = "/changeID")
   public ResponseEntity<Map<String, Object>> changeId(@RequestBody Map<String, String> map) throws Exception {
      Map<String, Object> resultMap = new HashMap<>();
      HttpStatus status = null;
      int cnt = service.updateIDUser(map);
      
      if (cnt == 1) {
         resultMap.put("message", SUCCESS);
         status = HttpStatus.ACCEPTED;
      } else {
         resultMap.put("message", FAIL);
         status = HttpStatus.ACCEPTED;
      }
      return new ResponseEntity<Map<String, Object>>(resultMap, status);
   }
   
   @ApiOperation(value = "비밀번호 수정", notes = "비밀번호 수정 user_password, user_email")
   @PostMapping(value = "/changePW")
   public ResponseEntity<Map<String, Object>> changePW(@RequestBody Map<String, String> map) throws Exception {
      Map<String, Object> resultMap = new HashMap<>();
      HttpStatus status = null;
      int cnt = service.updatePWUser(map);
      
      if (cnt == 1) {
         resultMap.put("message", SUCCESS);
         status = HttpStatus.ACCEPTED;
      } else {
         resultMap.put("message", FAIL);
         status = HttpStatus.ACCEPTED;
      }
      return new ResponseEntity<Map<String, Object>>(resultMap, status);
   }
   // 회원 탈퇴
   @DeleteMapping("/delete/{user_email}")
   @ApiOperation(value = "회원 탈퇴", notes = "정상적으로 탈퇴 처리가 완료되면 1 그렇지 않다면 0을 반환")
   public ResponseEntity<String> deleteUser(@PathVariable String user_email, HttpSession session) throws Exception {
      int res = service.deleteUser(user_email);
      if (res > 0) {
         session.invalidate();
         return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
      } else {
         return new ResponseEntity<String>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   // 이메일 중복체크
   @PostMapping("/emailChk")
   @ApiOperation(value = "이메일 중복체크")
   public ResponseEntity<String> emailChk(@Valid @RequestParam String user_email) throws Exception {
      String result;
      try {
         if (service.emailChk(user_email) > 0)
            result = "이미 존재하는 이메일입니다.";
         else
            result = "사용 가능한 이메일입니다.";
      } catch (Exception e) {
         result = e.getMessage();
      }
      return new ResponseEntity<String>(result, HttpStatus.OK);
   }

   // 아이디 중복체크
   @PostMapping("/idChk")
   @ApiOperation(value = "아이디 중복체크")
   public Object idChk(@Valid @RequestParam String user_id) throws Exception {
      String result;
      try {
         if (service.idChk(user_id) > 0)
            result = "이미 존재하는 아이디입니다.";
         else
            result = "사용 가능한 아이디입니다.";
      } catch (Exception e) {
         result = e.getMessage();
      }
      return new ResponseEntity<String>(result, HttpStatus.OK);
   }

   // 회원정보 조회
   /**
    * token값을 얻어야하기 때문에 이 기능을 postman으로 test해주기 전에 반드시 로그인 기능을 postman으로 test하여
    * token의 key와 value를 얻은 다음 테스트해주자. Postman의 Header에 key와 value를 입력해주면된다.
    */
   @ApiOperation(value = "회원정보 조회", notes = "회원 정보를 담은 Token을 사용하여 회원인증 후 회원 정보를 반환한다. map: I - 현재 로그인한 사용자의 이메일 , ThisPerson - 프로필 조회하려고 하는 사람의 이메일")
   @PostMapping("/info")
   public ResponseEntity<Map<String, Object>> getUser(@RequestBody Map<String, String> map,
         HttpServletRequest request) {
      // logger.debug("user_email : {} ", user_email);
      Map<String, Object> resultMap = new HashMap<>();
      HttpStatus status = HttpStatus.ACCEPTED;
      if (jwtService.isUsable(request.getHeader("access-token"))) {
         logger.info("사용 가능한 토큰!!!");
         try {
            // 로그인 사용자 정보.
            Map<String, String> flw = new HashMap<>();
            flw.put("following", map.get("I"));
            flw.put("followers", map.get("ThisPerson"));
            User userDto = service.getUser(map.get("ThisPerson"));
            boolean isThisPersonFollowingMe = fser.chkThisPersonFMe(flw) == 1 ? true : false;
            boolean isIFollowingThisPerson = fser.chkIFThisPerson(flw) == 1 ? true : false;
            resultMap.put("userInfo", userDto);
            resultMap.put("isThisPersonFollowingMe", isThisPersonFollowingMe);
            resultMap.put("isIFollowingThisPerson", isIFollowingThisPerson);
            resultMap.put("message", "success");
            status = HttpStatus.ACCEPTED;
         } catch (Exception e) {
            logger.error("정보조회 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
         }
      } else {
         logger.error("사용 불가능 토큰!!!");
         resultMap.put("message", "fail");
         status = HttpStatus.ACCEPTED;
      }
      return new ResponseEntity<Map<String, Object>>(resultMap, status);
   }

   // 타 유저 검색 by user_id
   @ApiOperation(value = "타 유저 검색 by user_id", notes = "user_id기반으로 유저를 검색하여 비슷한 user_id를 가진 사람들의 목록을 리스트로서 반환해준다.")
   @GetMapping("/search/id/{user_id}")
   public ResponseEntity<List<User>> searchUserById(@PathVariable("user_id") String user_id) throws Exception {
      logger.debug("searchUserById - 호출");
      return new ResponseEntity<List<User>>(service.searchUserById(user_id), HttpStatus.OK);
   }

   // 타 유저 검색 by user_cbti
   @ApiOperation(value = "타 유저 검색 by user_cbti", notes = "user_cbti기반으로 유저를 검색하여 비슷한 user_cbti를 가진 사람들의 목록을 리스트로서 반환해준다.")
   @GetMapping("/search/cbti/{user_cbti}")
   public ResponseEntity<List<User>> searchUserByCBTI(@PathVariable("user_cbti") String user_cbti) throws Exception {
      logger.debug("searchUserByCBTI - 호출");
      return new ResponseEntity<List<User>>(service.searchUserByCBTI(user_cbti), HttpStatus.OK);
   }

   // CBTI 저장
   @ApiOperation(value = "CBTI 저장", notes = "검사한 CBTI를 저장한다. map에는  user_email과 user_cbti가 들어있다")
   @PostMapping("/save/cbti")
   public ResponseEntity<String> saveUserCBTI(@RequestBody Map<String, String> map) throws Exception {

      int res = service.saveCBTI(map);
      if (res > 0) {
         return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
      } else {
         return new ResponseEntity<String>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   // 유저 프로필 사진 가져오기
   @ApiOperation(value = "유저 사진 불러오기", notes = "유저의 사진을 반환한다. 유저가 이미지를 등록하지 않은 경우 default 이미지를 반환한다.")
   @GetMapping(value = "/getUserPic/{fileName}")
   public Object getUserPic(@PathVariable String fileName) throws MalformedURLException {
      String finalName = "defaultProfileImage.png";
      Resource resource = null;
      if (fileName == finalName) {
         resource = new FileSystemResource("/home/ubuntu/S05P12C202/frontend/src/assets/images/" + finalName);
      } else {
         resource = new FileSystemResource("/home/ubuntu/S05P12C202/frontend/src/assets/images/userPic/" + fileName);
      }

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

   // 유저 사진 등록하기
   @ApiOperation(value = "유저 사진 등록하기", notes = "유저의 프로필 사진을 등록한다.")
   @RequestMapping(value = "/saveUserPic", method = RequestMethod.POST, consumes = {
         MediaType.MULTIPART_FORM_DATA_VALUE })
   public ResponseEntity<Map<String, Object>> updateUserPic(@RequestParam("file") MultipartFile file,
         @RequestParam("userEmail") String userEmail) {
      Map<String, Object> resultMap = new HashMap<>();
      HttpStatus status = null;
      try {
         int cnt = service.insertUserPic(file, userEmail);
         if (cnt > 0) {
            resultMap.put("message", SUCCESS);
         } else {
            resultMap.put("message", FAIL);
         }
         status = HttpStatus.ACCEPTED;
      } catch (Exception e) {
         // logger.error("로그인 실패 : {}", e);
         resultMap.put("message", e.getMessage());
         status = HttpStatus.INTERNAL_SERVER_ERROR;
      }
      return new ResponseEntity<Map<String, Object>>(resultMap, status);
   }

   // 유저 프로필 사진 삭제
   @ApiOperation(value = "유저 사진 삭제하기", notes = "유저의 사진을 삭제한다. default 이미지를 반환한다.")
   @PostMapping("/deleteUserPic")
   public ResponseEntity<Map<String, Object>> deleteUserPic(@RequestParam String userEmail) {
      Map<String, Object> resultMap = new HashMap<>();
      HttpStatus status = null;

      try {
         // 사진 삭제
         int cnt = service.deleteUserPic(userEmail);
         if (cnt > 0) {
            resultMap.put("message", SUCCESS);
         } else {
            resultMap.put("message", FAIL);
         }
         status = HttpStatus.ACCEPTED;
      } catch (Exception e) {
         // logger.error("로그인 실패 : {}", e);
         resultMap.put("message", e.getMessage());
         status = HttpStatus.INTERNAL_SERVER_ERROR;
      }
      return new ResponseEntity<Map<String, Object>>(resultMap, status);
   }
}
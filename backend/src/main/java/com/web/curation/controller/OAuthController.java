package com.web.curation.controller;

import com.web.curation.config.auth.GoogleOAuthRequest;
import com.web.curation.config.auth.GoogleOAuthResponse;
import com.web.curation.model.BasicResponse;
import com.web.curation.service.OAuthServiceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = "*")
@RequestMapping("/login")
@RestController
public class OAuthController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	final static String GOOGLE_AUTH_BASE_URL = "https://accounts.google.com/o/oauth2/v2/auth";
	final static String GOOGLE_TOKEN_BASE_URL = "https://oauth2.googleapis.com/token";
	final static String GOOGLE_REVOKE_TOKEN_BASE_URL = "https://oauth2.googleapis.com/revoke";
	
	@Autowired
	OAuthServiceImpl OAuthSer;
	
	@ApiOperation(value = "구글 로그인", response = Map.class)
	@GetMapping("google/auth")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> googleAuth(@RequestParam(value = "code") String authCode)
			throws JsonProcessingException {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		// HTTP Request를 위한 RestTemplate
		RestTemplate restTemplate = new RestTemplate();

		// Google OAuth Access Token 요청을 위한 파라미터 세팅
		GoogleOAuthRequest googleOAuthRequestParam = GoogleOAuthRequest.builder()
				.clientId("1086668190258-4p95mu3hjaqq23isll8oac5bpps5hmli.apps.googleusercontent.com")
				.clientSecret("Hch6KiZOsvCdyvyWMC8ruhr9").code(authCode)
				//.redirectUri("https://i5c202.p.ssafy.io/cameo/login/google/auth").grantType("authorization_code").build();
				.redirectUri("https://i5c202.p.ssafy.io/cameo/login/google/auth").grantType("authorization_code").build();

		// JSON 파싱을 위한 기본값 세팅
		// 요청시 파라미터는 스네이크 케이스로 세팅되므로 Object mapper에 미리 설정해준다.
		ObjectMapper mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		mapper.setSerializationInclusion(Include.NON_NULL);

		// AccessToken 발급 요청
		ResponseEntity<String> resultEntity = restTemplate.postForEntity(GOOGLE_TOKEN_BASE_URL, googleOAuthRequestParam,
				String.class);

		// Token Request
		GoogleOAuthResponse result = mapper.readValue(resultEntity.getBody(), new TypeReference<GoogleOAuthResponse>() {
		});
		// ID Token만 추출 (사용자의 정보는 jwt로 인코딩 되어있다)
		String jwtToken = result.getIdToken();
		String requestUrl = UriComponentsBuilder.fromHttpUrl("https://oauth2.googleapis.com/tokeninfo")
				.queryParam("id_token", jwtToken).encode().toUriString();

		String resultJson = restTemplate.getForObject(requestUrl, String.class);

		Map<String, String> userInfo = mapper.readValue(resultJson, new TypeReference<Map<String, String>>() {
		});
		for (String key : userInfo.keySet()) {
			System.out.println(key);
			System.out.println(userInfo.get(key));
		}

		/* userInfo의 키 값들
		 * iss azp aud sub email email_verified at_hash name picture given_name
		 * family_name locale iat exp alg kid typ
		 */
		Map<String, String> user = new HashMap<>();
		user.put("user_email", userInfo.get("email"));
		user.put("user_id", userInfo.get("name"));
		user.put("user_pic", userInfo.get("picture"));
		
		int cnt = 0;
		try {
			cnt = OAuthSer.saveOrUpdate(user);
			System.out.println("여기는 oAuthController에서 부른 oAuthService 수행하고 난 뒤 값(cnt): " + cnt);
			if(cnt != 0) {
				resultMap.put("user", user);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}else {
				resultMap.put("message", FAIL);
				status = HttpStatus.FORBIDDEN;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	// 토큰 무효화
	@GetMapping("google/revoke/token")
	public Map<String, String> revokeToken(@RequestParam(value = "token") String token) throws JsonProcessingException {

		Map<String, String> result = new HashMap<>();
		RestTemplate restTemplate = new RestTemplate();
		final String requestUrl = UriComponentsBuilder.fromHttpUrl(GOOGLE_REVOKE_TOKEN_BASE_URL)
				.queryParam("token", token).encode().toUriString();
		
		String resultJson = restTemplate.postForObject(requestUrl, null, String.class);
		result.put("result", "success");
		result.put("resultJson", resultJson);

		return result;

	}
}

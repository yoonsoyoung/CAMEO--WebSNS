//package com.web.curation.controller;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.web.curation.model.BasicResponse;
//import com.web.curation.model.user.SocketVO;
//import com.web.curation.model.user.User;
//
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
//
//@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
//		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
//		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
//		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })
//@RestController("/alarm")
//@CrossOrigin(origins = "*")
//public class SocketController {
//	
//	/*
//	 * 처음 user의 id 와 
//	 * 보낼 상대의 id 
//	 * 
//	 * 만약 팔로우를 하게 되면 (팔로우를 눌렀을때, user id, 상대 user id 가 있어야함)
//	 * 따로 db에 저장하지 않고 알림은 한번만 보내면 되니까 
//	 * 
//	 * following => 팔로잉 요청하는 사람
//	 * follower => 팔로잉 당하는 사람
//	 * */
//
//	// senderid = userid , receiver = userid 당하는 사람의
//	
//
//	// /receive를 메시지를 받을 endpoint로 설정
//	@MessageMapping("/receive/follow")
//		
//	// /send로 메시지를 반환한다
//	@SendTo("/send")
////	@ApiOperation(value = "팔로우 요청 실시간 알림 " , notes = "다른 유저에게 팔로우 요청을 보냈을 때 실시간으로 알림  following: 팔로우 요청 보내는 사람, temp_followers : 팔로우 요청 받는 사람 ", response = Map.class)
////	public ResponseEntity<Map<String, Object>> AlarmFollow(@RequestBody SocketVO socketVO){
////		Map<String, Object> resultMap = new HashMap<>();
////		HttpStatus status = null;
////		
////		String senderName = socketVO.getSenderNickName();
////		// vo에서 getter로 content를 가져온다.
////		String receiveername = socketVO.getReceiveerNickName();
////		
////		String content =  senderName + "님이" + receiveername + "에게 팔로우 요청을 보냈습니다." ;
////		// map 에 following => 팔로잉 요청하는 사람 이메일
////		// temp_followers = 팔로잉 당하는 사람의 이메일이 들어있다
////		
////		
////		
////		
////		return new ResponseEntity<Map<String,Object>>(resultMap, status);
////		
////		
////	}
//	
//	// sockethandler 는  1)/receive에서 메시지를 받고, /send로 메시지를 보내준다.
//		// 정의한 socketVo를 1) 인자값, 2) 반환값으로 사용
//	
//	public SocketVO Socketfollow(@RequestBody SocketVO socketVO) {
//		// vo에서 getter로 userName 을 가져온다 
//		String senderName = socketVO.getSenderNickName();
//		// vo에서 getter로 content를 가져온다.
//		String receiveername = socketVO.getReceiveerNickName();
//		
//		String content =  socketVO.getReceiveerNickName() + "님이" + socketVO.getSenderNickName() + "에게 팔로우 요청을 보냈습니다." ;
//		
//		
//		//생성자로 반환값을 생성
//		SocketVO result = new SocketVO(senderName, receiveername, content);
//		
//		//반환
//		
//		return result;
//		
//		//완성
//	}
//	
//	@MessageMapping("/receive/scrap")
//	
//	// /send로 메시지를 반환한다
//	@SendTo("/send")
//	public SocketVO SocketScrap(SocketVO socketVO) {
//		
//		String senderName = socketVO.getSenderNickName();
//		String receiveername = socketVO.getReceiveerNickName();
//		String content = socketVO.getReceiveerNickName() + "님이" + socketVO.getSenderNickName() + "게시물을 스크랩 하였습니다";
//		
//		SocketVO result = new SocketVO(senderName, receiveername, content);
//		
//		return result;
//	}
//	
//	@MessageMapping("/receive/good")
//	
//	// /send로 메시지를 반환한다
//	@SendTo("/send")
//	public SocketVO SocketGood(SocketVO socketVO) {
//		String senderName = socketVO.getSenderNickName();
//		String receiveername = socketVO.getReceiveerNickName();
//		String content = socketVO.getReceiveerNickName() + "님이" + socketVO.getSenderNickName() + "게시물에 좋아요를 눌렀습니다";
//		
//		SocketVO result = new SocketVO(senderName, receiveername, content);
//		
//		return result;
//	}
//	
//	
//
//}

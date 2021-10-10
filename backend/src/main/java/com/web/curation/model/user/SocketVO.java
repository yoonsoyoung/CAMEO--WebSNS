package com.web.curation.model.user;

//@Data // getter, setter를 자동 생성한다, data를 쓰면 getter, setter를 자동 생성한다고 했는데 받아오질 못함..?

// 어노테이션은 생성자를 자동 생성

//@AllArgsConstructor
public class SocketVO {
	// 어떤 형태로 받을지 정의 
	
	// 유저의 이름을 저장하기 위한 변수
	
	
	private String senderNickName;
	
	private String receiveerNickName;
	private String content;


	public SocketVO(String senderNickName, String receiveerNickName, String content) {
		
		this.senderNickName = senderNickName;
		this.receiveerNickName = receiveerNickName;
		this.content = content;
	}


	public SocketVO() {
		
	}


	public String getSenderNickName() {
		return senderNickName;
	}


	public void setSenderNickName(String senderNickName) {
		this.senderNickName = senderNickName;
	}


	public String getReceiveerNickName() {
		return receiveerNickName;
	}


	public void setReceiveerNickName(String receiveerNickName) {
		this.receiveerNickName = receiveerNickName;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	


//	public static MessageDto convertMessageToDto(Message message) {
//		
//	}
	
	
//	private String userName;
//	
//	private String youName;
//	
//	// 메시지의 내용을 저장하기 위한 변수
//	private String content;
//	
	
	//생성자 반환값도 제대로 생성이 안됨??
//	public SocketVO(String userName, String content) {
//		
//	}

//	public String getUserName() {
//		return userName;
//	}
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//
//	public String getContent() {
//		return content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}
	
	
	
	// 질문 그럼 user를 관리하는 dto 하나 
	// soket에서 user id 를 받아올 dto 하나 따로 따로 만드는 건가? 
}

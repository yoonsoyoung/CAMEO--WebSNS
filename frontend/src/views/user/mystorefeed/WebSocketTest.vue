<template>
  <div id="app" class="wrap">
    <!-- <label> 유저이름: </label>
    <input
      v-model="userName"
      type="text"
    >
    <label>내용: </label><input
      v-model="message"
      type="text"
      @keyup="sendMessage"
    >
    <div
      v-for="(item, idx) in recvList"
      :key="idx"
    >
      <h3>유저이름: {{ item.userName }}</h3>
      <h3>내용: {{ item.content }}</h3>
    </div> -->
  </div>
</template>

<script>
// import Stomp from 'webstomp-client'
// import SockJS from 'sockjs-client'


export default {
  name: 'WebSocket',
  data() {
    return {
      userName: "",
      message: "",
      recvList: []
    }
  },
  // created() {
  //   // App.vue가 생성되면 소켓 연결을 시도합니다.
  //   this.connect()
    
  // },
  // methods: {
  //   sendMessage (e) {
  //     if(e.keyCode === 13 && this.userName !== '' && this.message !== ''){
  //       this.send()
  //       this.message = ''
  //     }
  //   },    
  //   send() {
  //     console.log("Send message:" + this.message);
  //     if (this.stompClient && this.stompClient.connected) {
  //       const msg = { 
  //         userName: this.userName,
  //         content: this.message 
  //       };
  //       this.stompClient.send("/receive", JSON.stringify(msg), {});
  //     }
  //   },    
  //   connect() {
  //     const serverURL = "http://localhost:8080/cameo"
  //     let socket = new SockJS(serverURL);
  //     this.stompClient = Stomp.over(socket);
  //     console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)
  //     this.stompClient.connect(
  //       {},
  //       frame => {
  //         // 소켓 연결 성공
  //         this.connected = true;
  //         console.log('소켓 연결 성공', frame);
  //         // 서버의 메시지 전송 endpoint를 구독합니다.
  //         // 이런형태를 pub sub 구조라고 합니다.
  //         this.stompClient.subscribe("/send", res => {
  //           console.log('구독으로 받은 메시지 입니다.', res.body);

  //           // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
  //           this.recvList.push(JSON.parse(res.body))
  //         });
  //       },
  //       error => {
  //         // 소켓 연결 실패
  //         console.log('소켓 연결 실패', error);
  //         this.connected = false;
  //       }
  //     );        
  //   }
  // }
}
</script>
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Fredoka+One&family=Lobster&family=Jua&family=Noto+Sans+KR:wght@300;400&display=swap");
* {
    font-family: "Noto Sans KR";
    font-weight: 400;
}
a {
	text-decoration: none;
	color: black;
}
ul {
	padding: 0;
	margin: 0;
}
li {
	list-style: none;
}
input {
    /* border: 0; */
    background: none;
}
input:focus {
    outline: none;
}
button {
    border: none;
    background: none;
}
label {
    font-size: 20px;
}
.wrap {
  position: absolute;
  top: 200px;
}
</style>
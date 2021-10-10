<template>
  <div id="notificationRoot">
    <div class="user-row-list">
      <div class="user-list-info">
        <div class="user-info-profile">
          <img src="../../assets/images/profile_default.png" alt="프로필 사진" class="user-profile-img">
        </div>
        <div class="user-info-desc">
          <div class="user-info-id" v-if="this.type == 'noti'">
            <span id="userId"><b>{{userId}}</b> 님에게 팔로잉 요청을 보냈습니다.</span>
            <button class="noti-cancel-btn" id="cancelBtn" @click="undoNoti">취소</button>
          </div>
          <div class="user-info-id" v-else-if="this.type == 'request'">
            <span id="userId"><b>{{userId}}</b> 님이 팔로우 요청을 했습니다.</span>
            <div class="noti-result-btn-group">
              <button class="noti-result-btn" id="rejectBtn" @click="rejectNoti">거절</button>
              <button class="noti-result-btn" id="approveBtn" @click="approveNoti">승인</button>
            </div>
          </div>
          <div class="user-info-id" v-else-if="this.type == 'result'">
            <span id="userId"><b>{{userId}}</b> 님에게 팔로잉 요청이 승인되었습니다.</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from '../../util/http-common.js'
export default {
name: 'Notification',
props: [
  "userId",
  "type",
],
methods: {
  undoNoti() {
    alert(this.userId + "님 팔로잉 취소");
    // userId 와 일치하는 팔로잉 신청 db 삭제
  },
  approveNoti() {
    alert(this.userId + "님 팔로우 승인");
    // userId 와 일치하는 팔로우 신청 db 추가
  },
  rejectNoti() {
    alert(this.userId + "님 팔로우 거절");
    // userId 와 일치하는 팔로우 신청 db 삭제
  },
  getFollowList() {
    // 팔로잉 요청을 보낸 유저 리스트
    http.get('')
  }
},
data() {
  return {
    
  }
},
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
    border: 0;
    background: none;
}
input:focus {
    outline: none;
}
button {
    border: none;
    background: none;
}
#userRow {
  width: 100%;
  display: block;
  float: left;
}
#userRow:hover {
  background-color: #EAEAEA;
}
.user-row-list {
  width: 100%;
}
.user-list-info {
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    align-content: center;
    justify-content: flex-start;
    align-items: center;
    margin: 20px;
}
.user-info-profile {
  width: 55px;
  height: 55px;
  border-radius: 100%;
  background: #888;
  margin-right: 10px;
}
.user-info-profile > .user-profile-img {
  width: 100%;
  height: 100%;
}
.user-info-desc > div > span{
  font-size: 20px;
  /* font-weight: 500; */
  margin-right: 200px;
}
.user-info-request {
  margin-right: 250px;
}
.user-info-id {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  align-content: center;
  justify-content: flex-start;
  align-items: center;
}
.user-info-id > #userId > b {
  font-weight: 600;
}
.noti-cancel-btn {
  width: 80px;
    border: 1px solid rgb(196, 196, 196);
    border-radius: 5px;
}
.noti-result-btn-group > button:first-child {
  margin-right: 10px;
}
.noti-result-btn {
  width: 50px;
  border: 1px solid rgb(196, 196, 196);
  /* background-color: rgb(196, 196, 196); */
  border-radius: 5px;
}

/* 모바일 환경 */
@media (max-width: 500px) {
   #userRow {
        margin-left: 40px;
    }
    .user-info-desc > div > span {
      margin-right: 0;
      margin-bottom: 5px;
      font-size: 15px;
      text-align: left;
    }
    .user-info-id {
        width: 200px;
    }
}
@media (max-width: 768px) {
  .user-info-desc > div > span {
    margin-right: 0;
    font-size: 18px;
    text-align: left;
  }
  @media (min-width: 1024px) {
  .user-info-desc > div > span {
    margin-right: 100px;
    font-size: 18px;
    text-align: left;
  }
}
}

</style>
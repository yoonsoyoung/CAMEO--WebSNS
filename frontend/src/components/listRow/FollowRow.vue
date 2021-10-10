<template>
  <div id="userRow">
    <div class="user-row-list">
      <div class="user-list-info">
        <div class="user-info-desc">
          <div class="user-info-profile">
            <img src="../../assets/images/profile_default.png" alt="프로필 사진" class="user-profile-img" @click="goUserLink">
          </div>
          <div class="info-group">
              <div class="user-info-id">
                <span id="userId"  @click="goUserLink">{{userId}}</span>
              </div>
              <div class="user-desc-bottom">
                  <div class="user-info-email">
                    <span id="userEmail">{{email}}</span>
                  </div>
                  <div class="user-info-cbti" v-if="cbti!==''">
                    <span id="userCbti">{{cbti}}</span>
                  </div>
              </div>
            </div>
        </div>
          <div class="btn-group">
            <div class="follower-btns" v-if="this.type==='follower'">
              <button v-if="isFollowEachOther" class="follow-btn" @click="removeFollower">언팔로우</button>
              <button v-else class="follow-btn" @click="addFollower">팔로우</button>
              <button class="delete-btn" @click="removeFollower">삭제</button>
            </div>
            <div class="following-btns" v-else>
              <button class="follow-btn" @click="removeFollower">언팔로우</button>
              <button class="alarm-btn" v-if="!this.bellOn" @click="offAlarm"><img src="../../assets/images/alarm_no.png" alt="알림on" class="btn-img"></button>
              <button class="alarm-btn" v-else @click="onAlarm"><img src="../../assets/images/alarm_off.png" alt="알림off" class="btn-img"></button>
            </div>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
import ButtonSmallVue from '../common/ButtonSmall.vue';
export default {
  name: 'FollowRow',
  data() {
    return {
      bellOn : '',
      // cbti : '',
      followToo : '',
      // userId : '',
      userImage : '',
      // email : '',
    }
  },
  props: {
    idx: Number,
    userId : String,
    cbti : String,
    email: String,
    type : String,
    mute : Boolean,
    isFollowEachOther : Boolean,
  },
  created() {
    console.log(this.idx)
    this.bellOn = this.mute
  },
  computed : {
  },
  methods: {
    goUserLink() {
      // alert(this.userId + "님 프로필로 이동");
      this.$router.push('/mypage/home/' + this.email);
    },
    // 팔로워 (남이 나를 팔로우)
    addFollower() { // 나도 이 사람 팔로우하기
      const Form = {      
        "following" : localStorage.getItem("user_email"),
        "temp_followers" : this.email
      }
      http.post('/follow/reqfollow', Form)
      .then((res)=>{
        if(res.data.message==="success"){
          alert(this.userId + "님에게 팔로잉 요청이 갔습니다.");
        }else{
          alert('이미 팔로우 중인 유저 입니다.');
        }
      })
    },
    removeFollower() { // 상대방에게서 나 끊기
      const following = this.propEmail
      const followers = localStorage.getItem("user_email")
      http.delete(`/follow/unfollow/${following}/${followers}`)
      .then((res)=>{
        if(res.data.message==="success"){
          alert(`${followers}님을 언팔로우 했습니다.`)
        }
      })
      .catch((err)=>{
        console.log(err)
      })
    },
    // 팔로잉 (내가 남을 팔로우)
    removeFollowing() { // 팔로잉 끊기
      const following = localStorage.getItem("user_email")
      const followers = this.propEmail
      http.delete(`/follow/unfollow/${following}/${followers}`)
      .then((res)=>{
        if(res.data.message==="success"){
          alert(`${followers}님을 언팔로우 했습니다.`)
        }
      })
      .catch((err)=>{
        console.log(err)
      })
    },
    onAlarm() { // 해당 유저 소식 알림 받기
      const Form = {
        'following' : localStorage.getItem("user_email"),
        'followers' : this.email
      }
      http.put('follow/unmute', Form)
      .then((res)=>{
        alert('알림 차단 해제')
      })
      this.bellOn = !this.bellOn
    },
    offAlarm() { // 해당 유저 소식 알림 끄기
      const Form = {
        'following' : localStorage.getItem("user_email"),
        'followers' : this.email
      }
      http.put('follow/mute', Form)
      .then((res)=>{
        alert('알림 차단')
      })
      this.bellOn = !this.bellOn
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
    flex-wrap: wrap;
    align-content: center;
    justify-content: space-between;
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
.user-info-desc {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  align-content: center;
  justify-content: flex-start;
  align-items: center;
}
.user-info-desc > div > span{
  font-size: 20px;
  /* font-weight: 500; */
}
.user-desc-bottom {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  align-content: center;
  justify-content: flex-start;
  align-items: center;
}
.user-info-email {
   margin-right: 10px;
}
.user-info-email > span{
  font-size: 18px;
  font-weight: 500;
}
.user-info-cbti {
  width: 40px;
  background: #C4C4C4;
  border-radius: 5px;
  text-align: center;
}
.user-info-cbti > span {
  font-size: 13px;
  font-weight: bold;
}
.btn-img {
  width: 20px;
  height: 20px;
}
.following-btns {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  align-content: center;
  justify-content: center;
  align-items: center;
}
.follow-btn {
  width: 80px;
  border: 1px solid rgb(196, 196, 196);
  border-radius: 5px;
  margin-right: 10px;
}
.delete-btn {
  width:50px;
  border: 1px solid rgb(196, 196, 196);
  border-radius: 5px;
}
/* 모바일 환경 */
@media (max-width: 500px) {
   #userRow {
        margin-left: 40px;
    }
}
</style>
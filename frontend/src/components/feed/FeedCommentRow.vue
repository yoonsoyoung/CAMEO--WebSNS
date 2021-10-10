<template>
<div id="commentRowRoot">
  <div class="comm-row-list">
    <div class="user-info">
      <div class="user-info-profile" @click="goUserLink">
      <img :src="`https://i5c202.p.ssafy.io/cameo/user/getUserPic/${this.userPic}`" 
          alt="프로필 사진" class="user-profile-img">
      </div>
      <div class="user-info-item">
        <div class="user-comm-group">
          <div class="comment-item">
            <div class="comment-text">
              <span hidden>{{this.commentid}}</span>
              <span id="userId" @click="goUserLink">{{userid}}</span>
              <span id="commentText"  v-if=!onModify>{{comment}}</span>
            </div>
            <div class="modify-comm-input" v-if=onModify>
              <input type="text" id="commentModify"
                :placeholder="comment"
                v-model="modifyText"
                maxlength="50">
            </div>
            <div  v-if="onChk" class="btn-only-user">
              <div class="btn-group" v-if=!onModify>
                  <button class="delete-btn" @click="deleteComm">삭제</button>
                  <button class="modify-btn" @click="changeModify">수정</button>
              </div>
              <div class="btn-group" v-else>
                <button class="modify-btn" @click="modifyComm">완료</button>
              </div>
            </div>
          </div>
        </div>
        <div class="comm-info-group">
            <div class="write-time">
                <span id="writeTime">{{this.wTime}}</span>
            </div>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import http from '@/util/http-common.js'

export default {
  name: "FeedCommentRow",
  props: {
    feedId : String,
    commentid : Number,
    userid : String,
    comment : String,
    writetime : String
  },
  created() {
    const Form = {
      I : localStorage.getItem("user_email"),
      ThisPerson : this.userid,
    }
    // console.log(this.userid)
    http.post('/user/info',Form)
    .then((res)=>{
      this.userPic = res.data.userInfo.user_pic
    })
    if(this.userid == localStorage.getItem("user_email")){
      this.onChk = true
    }else{this.onChk = false}
    
    this.timeForToday(this.writetime);
  },
  methods: {
    goUserLink() {
      this.$router.push(`/mypage/home/${this.userid}`)
    },
    deleteComm() {
      var deleteChk = confirm(this.commentid + "댓글을 삭제하시겠습니까?");
      if(deleteChk) {
        http.delete(`newspheed/deleteComment?pheedCommentId=${this.commentid}`)
        alert("댓글이 삭제되었습니다.");
      }
      this.$router.go(0)
    },
    changeModify() {
      this.onModify = true;
            
    },
    modifyComm() {
    http.put(`/newspheed/updateComment`)
    const Form ={
      "pheedCommentId" : this.commentid,
      "content" : this.modifyText
    }

    this.moveCheck = true;
    http.put(`/newspheed/updateComment`, Form)

    alert("댓글 수정 완료");
    this.$router.go();
    this.onModify = false;
        // 수정한 댓글 DB 저장
    },
    timeForToday(writetime) {
      const today = new Date();
      const timevalue = new Date(writetime);

      const betweenTime = Math.floor((today.getTime() - timevalue.getTime()) / 1000 / 60);
      if(betweenTime < 1) return this.wTime="방금전";
      if(betweenTime < 60 ) return this.wTime=betweenTime+"분전";
      
      const betweenTimeHour = Math.floor(betweenTime / 60);
      if(betweenTimeHour < 24 ) return this.wTime=betweenTimeHour + "시간전";

      const betweenTimeDay = Math.floor(betweenTimeHour/ 24);
      if(betweenTimeDay < 31) return this.wTime=betweenTimeDay+"일전";
      
      const betweenTimeMonth = Math.floor(betweenTimeDay / 30 );
      if(betweenTimeMonth < 12) return this.wTime=betweenTimeMonth+"달전";
      const betweenTimeYear = Math.floor(betweenTimeDay / 365);
      return this.wTime=betweenTimeYear+"년전";
    },
  },
    data() {
      return {
        onChk : '',
        userPic : '',
        modifyText: "",
        onModify: false,
        wTime: "",
        // 로그인 한 유저 중 본인 댓글 수정/삭제 가능
        // loginedUser: true,
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
#commentRowRoot {
    width: 100%;
    height: 100%;
    margin-bottom: 30px;
}
.comm-row-list {
    width: 100%;
}
.user-info {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    align-content: flex-start;
    justify-content: center;
    align-items: center;
}
.user-comm-group {
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    align-content: center;
    justify-content: flex-start;
    align-items: center;
    width: 100%;
}
.user-info-item {
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    align-content: space-between;
    justify-content: center;
    align-items: flex-start;
    margin-left: 10px;
    width: 75%;
}
.user-info-profile {
    width: 50px;
    height: 50px;
    background-color: #ececec;
    border-radius: 50%;
}
.user-profile-img {
    width: 100%;
    height: 100%;
    border-radius: 50%;
}
.comment-item {
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    align-content: center;
    justify-content: space-between;
    align-items: center;
    width: 100%;
}
.comment-text {
    max-width: 80%;
    line-height: 1.5em;
}
.user-info-id {
    min-width: 80px;
}
#userId {
    font-size: 1.1em;
    font-weight: 600;
    margin-right: 10px;
}
.modify-comm-input {
    width: 75%;
    border: 1px solid rgb(196, 196, 196);
    border-radius: 5px;
    text-align: center;
}
.modify-comm-input > input {
    width: 98%;
    font-size: 1.0em;
}
.btn-group > button {
    width: 100px;
    height: 20px;
    border: 1px solid rgb(196, 196, 196);
    border-radius: 5px;
    line-height: 10px;
}
.btn-group > button:first-child {
    margin-right: 5px;
}
.write-time > span{
    font-size: 0.7em;
    color: #8b8b8b;
}
/* 모바일 환경 */
@media (max-width: 479px) {
   #userRow {
        margin-left: 40px;
    }
    .user-profile-img {
        width: 30px;
        height: 30px;
    }
    #userId {
        font-size: 0.5em;
        font-weight: bold;
    }
    #commentText {
        font-size: 0.5em;
        text-align: left;
    }
    .write-time > span {
        font-size: 0.5em;
    }
    .user-comm-group {
        flex-direction: column;
        align-items: flex-start;
    }
    .btn-group > button {
        width: 35px;
        height: 20px;
        font-size: 0.1em;
        line-height: 0;
    }
    .btn-group > button:first-child {
        margin-right: 2px;
    }
    .modify-comm-input {
        width: 65%;
        height: 20px;
        line-height: 8px;
        text-align: center;
    }
    .modify-comm-input > input {
        width: 95%;
        font-size: 0.3em;
    }
    .comment-text {
        max-width: 70%;
        line-height: 0.7em;
    }
    
}
@media (min-width: 480px) and (max-width: 767px) {
    .comment-text {
        max-width: 70%;
    }
}
@media (min-width: 768px) {
    .comment-text {
        max-width: 75%;
    }
}

</style>
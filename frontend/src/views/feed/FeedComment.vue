<template>
<div id="feedCommentRoot">
  <sub-nav 
    :title="'댓글'"
  />
  <div class="feed-comment-wrap">
    <div class="comment-list">
      <feed-comment-row 
        v-for="(item, index) in commentList"
        :key="index"
        :commentid="item.pheedComment.pheedCommentId"
        :userid="item.pheedComment.userEmail"
        :comment="item.pheedComment.content"
        :writetime="item.pheedComment.insertTime"
      />
    </div>
  </div>
  <div class="write-comment-form">
    <hr class="comment-input-hr">
      <div class="user-comment-input">
        <div class="user-info-profile">
        <img  :src="`https://i5c202.p.ssafy.io/cameo/user/getUserPic/${this.userPic}`" 
            alt="프로필 사진" class="user-profile-img">
        </div>
        <div class="comment-input-box">
          <input type="text" id="writeComment"
            maxlength="50" v-model="mycomment"
            :placeholder="loginedUser.userid+`(으)로 댓글달기...`"
            v-bind="doCommentText"
            @keyup.enter="uploadComment"
          >
          <button class="wirte-btn" 
            @click="uploadComment"
            v-if="isSubmit"
          >게시</button>
        </div>
      </div>
    </div>
</div>
</template>

<script>
import "../../components/css/main-scss.scss";
import SubNav from '../../components/header/SubNav.vue'
import FeedCommentRow from '../../components/feed/FeedCommentRow.vue'
import http from '@/util/http-common.js'

export default {
    components: {
      SubNav,
      FeedCommentRow,
    },
    props: {
      feed_id : String,
    },
    created() {
      http.get(`/newspheed/read?pheedId=${this.feed_id}`)
      .then((res)=>{
        this.commentList = res.data.pheed.pheedCommentList
        console.log(this.commentList[0].pheedComment)
      })
      const Form = {
        "I" : localStorage.getItem("user_email"),
        "ThisPerson" : localStorage.getItem("user_email")
      }
      http.post(`/user/info`, Form)
      .then((res)=>{
        this.userPic=res.data.userInfo.user_pic
      })
    },
    methods: {
      uploadComment() {
        // input에 적은 내용 현재 피드_id에 DB 저장
        const Form ={
          "pheedId" : this.feed_id,
          "userEmail" : localStorage.getItem("user_email"),
          "content" : this.mycomment
        }
        this.moveCheck = true;
        http.post(`/newspheed/writeComment`, Form)

        alert("댓글 등록 완료");
        this.$router.go();
      },
      checkForm() { 
        // 댓글 입력 칸에 입력되었을 때만 '게시'버튼 활성화
        if(this.mycomment.trim().length > 0) {
          this.moveCheck = false; // 페이지 이동 불가
          return this.isSubmit = true;
        }
        else {
          this.moveCheck = true; // 페이지 이동 가능
          return this.isSubmit = false;
        }
      },
      unLoadEvent: function(event) {
        // input 이 변경되지 않은 경우 경고창 뜨지 않음
        if(this.moveCheck) return;
        event.preventDefault();
        event.returnValue = '';
      },
    },
    watch: {
      mycomment: function(v) {
          this.checkForm();
      },
    },
    // 페이지 이동 감지
    mounted() {
      window.addEventListener('beforeunload', this.unLoadEvent);
    },
    beforeUnmount() {
      window.removeEventListener('beforeunload', this.unLoadEvent);
    },
    beforeRouteLeave (to, from, next) {
      // 라이프사이클 메소드 : SPA에서 페이지 이동 시
      if(this.moveCheck) next();
      else if(confirm('현재 페이지에서 이동하시겠습니까? \n 변경사항이 저장되지 않을 수 있습니다.')) {
          next();
      }
    },
    data() {
      return {
        userPic : "",
        mycomment: "",
        isSubmit: false,
        moveCheck: true,
        doCommentText: "",
        loginedUser: {
          userid: localStorage.getItem("user_id"),
        },
        commentList: [],
      }
    },
}
</script>

<style scoped>
.user-info-profile{
  width: 50px;
  aspect-ratio: 1;
  border-radius: 50%;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
}
.user-info-profile >img {
  width:100%;
  height: 100%;
}
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
#feedCommentRoot {
  top: 0;
  left: 0;
    position: absolute;
    width: 100%;
    height: 100%;
}
.feed-comment-wrap {
    overflow: auto;
    min-height: 580px;

    /* 스크롤바 없앰 */
     /* for Firefox */
    scrollbar-width: none;
    /* for IE, Edge */
    -ms-overflow-style: none;
}
/* For Chrome, Safari and Opera */
.feed-comment-wrap::-webkit-scrollbar {
    display: none;
}
.comment-list {
    width: 80%;
    margin: 0 auto;
    position: relative;
    top: 200px;
    min-height: 500px;
    overflow: hidden;
}
.write-comment-form {
    width: 100%;
    position: absolute;
    bottom: 10%;
    left: 0;
    z-index: 30;
    display: flex;
    flex-direction: column;
    flex-wrap: wrap;
    align-content: center;
    align-items: center;
    justify-content: center;
    background-color: white;
}
.comment-input-hr {
    width: 70%;
}
.user-comment-input {
    width: 70%;
    /* border: 1px solid rgb(196, 196, 196); */
    border-radius: 15px;
    padding: 10px;
    display: flex;
    align-content: center;
    align-items: center;
    justify-content: space-evenly;
    flex-direction: row;
    flex-wrap: nowrap;
}
.comment-input-box {
    width: 90%;
    background-color: #EAEAEA;
    border-radius: 100px;
    height: 40px;
    margin-left: 10px;
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    align-content: center;
    justify-content: space-around;
    align-items: center;
}
.comment-input-box > input {
    width: 80%;
    padding: 10px;
    font-size: 1.0em;
}
.wirte-btn {
    color: #a6774e;
}
@media (min-height: 750px) {
    .feed-comment-wrap {
        min-height: 650px;
    }
}
/* 모바일 */
@media (max-width: 479px) {
    .comment-list {
        width: 100%;
    }
    .user-profile-img {
        width: 30px;
        height: 30px;
    }
    .comment-input-box > input {
        width: 65%;
        font-size: 0.5em;
    }
    
}

</style>
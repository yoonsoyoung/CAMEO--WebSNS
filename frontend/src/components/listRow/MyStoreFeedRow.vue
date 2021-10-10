<template>
<div id="userRow">
    <div class="row-list">
      <div class="row-list-info">
        <div class="row-info-profile">
          <img :src="`https://i5c202.p.ssafy.io/cameo/user/getUserPic/${this.user_pic}`" alt="프로필 사진" class="user-profile-img">
        </div>
        <div class="list-info-desc">
          <div class="list-info-title"  @click="moveFeed">
            <span id="feedTitle">{{feedTitle}}</span>
          </div>
          <div class="list-desc-bottom">
            <div class="list-info-id">
              <span id="userId">{{userId}}</span>
            </div>
          </div>
        </div>
        <div class="row-list-btn">
          <div class="row-list-btn-like" v-if="type === 'likeFeed'">
            <button v-if="this.likeCheck" @click="removeLike"><i class="like-btn fas fa-heart"></i></button>
            <!-- <button v-else @click="addLike"><i class="like-btn far fa-heart"></i></button> -->
          </div>
        </div>
        <!-- likeFeedList end -->
        <div class="row-list-btn">
          <div class="row-list-btn-scrap" v-if="type === 'scrapFeed'">
            <button v-if="scrapCheck" id="scrapBtn"  @click="removeScrap"><i class="scrap-btn fas fa-bookmark"></i></button>
            <!-- <button v-else id="unscrapBtn" @click="addScrap"><i class="scrap-btn far fa-bookmark"></i></button> -->
          </div>
        </div>
        <!-- scropFeedList end -->
      </div>
    </div>
  </div>
</template>

<script>
import http from '@/util/http-common.js'

export default {
name: 'MyStoreFeedRow',
props: {
  feedTitle: {feedTitle: String},
  feedId: {feedId: Number},
  userId: {userId: String},
  type: {type: String},
},
data() {
  return {
    user_pic: '',
    word: "",
    title: "좋아요",
    likeCheck: {type: Boolean},
    scrapCheck: true,
    loginUser : localStorage.getItem("user_email"),
  }
},
created() {
  const Form = {
    I : this.userId,
    ThisPerson : this.userId
  }
  http.post('/user/info', Form)
  .then((res)=>{
    this.user_pic = res.data.userInfo.user_pic
  })
  // console.log(this.likeCheck);
},
methods: {
  addLike() {
    // let feed = {
      // 게시글 제목,; 아이디, 게시글 번호 등 정보 들어감
    // };
    // this.$store.dispatch("addLikeFeedList", feed); // 좋아요 한 리스트에 추가
    // this.$store.dispatch("getLikeFeedList", this.userInfo.id); // 해당 id의 유저한테 리스트 추가
    // this.likeCheck = false;
    // alert(this.feedTitle + " 좋아요 취소");
    // console.log("likeCheck : " + this.likeCheck);
  },
  removeLike() {
    // let feed = {};
    // const instance = createInstance();
    // instance.default.headers["access-token"] = window.localStorage.getItem("access-token");
    // instance
    //   .post("/피드 삭제 url" + this.userInfo.id, feed);
    //   .then(()=> {
    //     this.$store.dispatch("getLikeFeedList", this.userInfo.id);
    //     this.likeCheck = true;
    //   })
    //   .catch(() => {
    //     alert("에러발생!");
    //   });
    http.delete("/good/delete",
    { data:{
      good_my_email : this.loginUser,
      good_pheed_id : this.feedId}
    })
    this.likeCheck = false;
    // alert(this.feedTitle + " 좋아요 등록");
    console.log("likeCheck : " + this.likeCheck);

  },
  // addScrap() {
  //   this.scrapCheck = !this.scrapCheck;
  //   const Form = {
  //     "scrap_user_email" : localStorage.getItem("user_email"),
  //     "scrap_pheed_id" : this.feedId,
  //   }
  //   http.post('/scrap/insert', Form)
  // },
  removeScrap() {
    const Form = {
      scrap_user_email : this.loginUser,
      scrap_pheed_id : this.feedId
    }
    http.delete(`/scrap/delete`, {
      data: Form
    })
    this.scrapCheck = !this.scrapCheck;
    document.getElementByClassName("row-list-btn-scrap").style.display='none';
  }, 
  moveFeed(){
    this.$router.push('/feed/' + this.feedId);
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
  width: 70%;
  display: block;
  float: left;
}
#userRow:hover {
  background-color: #EAEAEA;
}
.row-list {
  width: 100%;
}
.row-list-info {
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    align-content: center;
    justify-content: space-around;
    align-items: center;
    margin: 20px;
}
.list-info-desc {
    min-width: 80%;
}
.row-info-profile {
  width: 55px;
  height: 55px;
  border-radius: 100%;
  background: #888;
  margin-right: 10px;
}
.row-info-profile > .user-profile-img {
  width: 100%;
  height: 100%;
}
.row-info-desc > div > span{
  font-size: 15px;
  /* font-weight: 500; */
}
.row-desc-bottom {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  align-content: center;
  justify-content: flex-start;
  align-items: center;
}
.row-info-id {
   margin-right: 20px;
}
.user-info-id > span{
  font-size: 18px;
  font-weight: 500;
}
#feedTitle {
  font-size: 18px;
  cursor: pointer;
}
#userId {
  font-size: 15px;
}
.like-btn {
    font-size: 20px;
    color: rgb(255, 53, 80);
}
.scrap-btn {
  font-size: 20px;
  color: #365959;
}
/* 모바일 환경 */
@media (max-width: 500px) {
   #userRow {
        margin-left: 40px;
    }
    .list-info-desc {

    }
}
</style>
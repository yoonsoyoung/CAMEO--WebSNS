<template>

  <div id="feedItemForm">
    <div class="full" id="feedTitle">
      <div class="flex-col-center">
        <img v-if="showUserImage" class="cursor-pointer" @click="goToProfilePage" :src="`https://i5c202.p.ssafy.io/cameo/user/getUserPic/${this.userImage}`" alt=""  id="profileImg">
        <img v-else class="cursor-pointer" @click="goToProfilePage" src="../../assets/images/profile_default.png" alt=""  id="profileImg">
      </div>
      <div class="flex-end flex-start">
        <div class="cursor-pointer" @click="goToProfilePage">{{feedUserEmail}}</div>
        <div id="feedItemDate">{{feedDate}}</div>
      </div>
      <div class="flex-end">
        <div class="cursor-pointer" id="moreBtn" @click="showMoreModal">:</div>
        <div id="moreModal" v-show="showModal">
          <div class="full cursor-pointer"> <div class="border-bottom flex-center" style="color:red;" @click="ilegalFeed">신고하기</div> </div>
          <div class="full cursor-pointer" @click="copyLink"><div class="border-bottom flex-center">링크복사</div></div>
          <div class="full cursor-pointer" @click="showMoreModal"><div class="border-bottom flex-center">취소</div></div>
        </div>
        <button v-if="loginUser !== feedUserEmail" class="flex-center" id="followBtn" @click="onFollowUser">팔로우</button>
        <button v-else class="flex-center" id="editBtn">수정하기</button>
      </div>
    </div>
    <div class="carousel">
      <div class="margin-left" id="feedItemTitle">제목 : {{feedTitle}}</div>
      <div class="carousel-image-container">
        <button v-show="this.images.length!==1" @click="prevPic" disabled id="prevBtn">prev</button>
        <img class="carousel-image" :src="`https://i5c202.p.ssafy.io/cameo/newspheed/getPheedPic/${this.feedId}/${this.images[picNum]}`" alt="">
        <button v-show="this.images.length!==1" @click="nextPic" id="nextBtn">next</button>
      </div>
      <p class="margin-left">내용 : {{feedContent}}</p>
    </div>
    <!-- <div class="full margin-left" id="feedContent">
    </div> -->
    <div class="full flex-space-between" id="feedBtn">
      <div class="half">
        <button v-if="feedLike" @click="toggleLike"><img class="btn-img" src="@/assets/images/btns/like-btn-on.png" alt=""></button>
        <button v-else @click="toggleLike"><img class="btn-img" src="@/assets/images/btns/like-btn-off.png" alt=""></button>
        <button @click="goToComment"><img class="btn-img" src="@/assets/images/btns/comment-btn.png" alt=""></button>
        <span>{{this.likeNum}}명이 좋아합니다.</span>
      </div>
      <div class="half justify-conetent-end">
        <button v-if="feedPlus"  @click="toggleHelpPlus"><img class="btn-img" src="@/assets/images/btns/good-btn-on.png" alt=""></button>
        <button v-else @click="toggleHelpPlus"><img class="btn-img" src="@/assets/images/btns/good-btn-off.png" alt=""></button>
        <button v-if="feedMinus" @click="toggleHelpMinus"><img class="btn-img" src="@/assets/images/btns/bad-btn-on.png" alt=""></button>
        <button v-else @click="toggleHelpMinus"><img class="btn-img" src="@/assets/images/btns/bad-btn-off.png" alt=""></button>
        <button v-if="scrapFeed" @click="scrapThisFeed" ><img class="btn-img" src="@/assets/images/btns/scrap-btn-on.png" alt=""></button>
        <button v-else @click="scrapThisFeed" ><img class="btn-img" src="@/assets/images/btns/scrap-btn-off.png" alt=""></button>
      </div>
    </div>
    <!-- <div class="full " id="feedComment">
    </div> -->
  </div>

</template>
<script>

import http from '@/util/http-common.js'
export default {
  name : 'FeedItemForm',
  props: {
    feed_id: String,
  },
  data() {
    return {
      loginUserId : localStorage.getItem('user_id'),
      loginUser : localStorage.getItem('user_email'),
      showModal : false,
      images : [],
      picNum : 0,
      likeNum : 0,
      feedId : '',
      userImage : '',
      showUserImage: true,
      feedUserEmail : '',
      feedDate : '',
      feedTitle : '',
      feedContent : '',
      myGoodId : '',
      feedLike: false,
      feedPlus: false,
      feedMinus : false,
      scrapFeed : false,
    }
  },
  created() {
    http.get(`newspheed/read?pheedId=${this.feed_id}`)
    .then((res)=>{
      this.feedId = res.data.pheed.pheed["pheed_id"]
      this.feedDate = res.data.pheed.pheed["pheed_date"]
      this.feedTitle = res.data.pheed.pheed["pheed_title"]
      this.feedContent = res.data.pheed.pheed["pheed_content"]
      this.feedUserEmail = res.data.pheed.pheed["pheed_user_id"]
      this.images = res.data.pheed.picList
      http.get(`good/count/${this.feedId}`)
      .then((res)=>{
        this.likeNum = res.data
      })
      const Form = {
        I : this.userId,
        ThisPerson : this.feedUserEmail
      }
      http.post('user/info/', Form)
      .then((res)=>{
        if(res.data.userInfo.user_pic != null){this.showUserImage = true; this.userImage = res.data.userInfo.user_pic; }
        else{this.showUserImage=false}
      }) 
      http.get(`/good/list/${this.loginUser}`)
      .then((res)=>{
        res.data.forEach((feed)=>{
        if(feed.good_pheed_id==this.feedId){this.feedLike=true;}
      })
      http.get(`scrap/list/${this.loginUser}`)
      .then((res)=>{
        console.log(res.data)
        res.data.list.forEach((feed)=>{
          if(feed.pheed_id==this.feedId){this.scrapFeed = true}
        })
      })
    })
    })
    .catch(()=>{
      this.$router.push('/404')
    })

  },
  mounted() {

  },
  methods : {
    copyLink(){
      let dummy = document.createElement("input") // 임시로 input 태그 형성
      document.body.appendChild(dummy) // document에 추가
      dummy.value = window.document.location.href // input에 현재 url 값을 넣어줌
      dummy.select(); // 해당 값이 선택되도록 select
      document.execCommand("copy")// 현재 클립 보드에 카피
      document.body.removeChild(dummy) // document에서 삭제
    },
    prevPic() { 
      this.picNum -= 1
      const prevBtn = document.getElementById('prevBtn')
      const nextBtn = document.getElementById('nextBtn')
      nextBtn.disabled = false
      if(this.picNum === 0){
        prevBtn.disabled = true
      }
    },
    nextPic() {
      this.picNum += 1
      const prevBtn = document.getElementById('prevBtn')
      const nextBtn = document.getElementById('nextBtn')
      prevBtn.disabled = false
      if(this.picNum == (this.images.length-1)){
        nextBtn.disabled = true
      }
    },
    showMoreModal() {
      this.showModal = !this.showModal
    },
    toggleLike(){
      if (this.feedLike){
        http.delete("/good/delete",
        { data:{
          good_my_email : localStorage.getItem("user_email"),
          good_pheed_id : this.feedId}
        })
        .then((res)=>{
          console.log(res)
          this.likeNum -= 1
        })
        .catch((err)=>{
          console.log(err)
        })
      }else{
        const Form = {
          "good_my_email" : localStorage.getItem("user_email"),
          "good_user_email" : this.feedUserEmail,
          "good_pheed_id" : this.feedId
        }
        http.post('/good/insert', Form)
        .then((res)=>{
          this.likeNum += 1
        })
      }
      this.feedLike = !this.feedLike
    },
    goToComment(){
      this.$router.push(`/feed/comment/${this.feedId}`)
    },
    toggleHelpPlus(){
      const Form = {
        user_id : this.feedId, 
        user_email : this.feedUserEmail
      }
      http.put('help/plus',Form)
      .then((res)=>{})
      this.feedPlus = !this.feedPlus
      if(this.feedMinus===true){this.feedMinus = false}
    },
    toggleHelpMinus(){
      const Form = {
        user_id : this.feedId, 
        user_email : this.feedUserEmail
      }
      http.put('help/minus',Form)
      .then((res)=>{
      })
      this.feedMinus = !this.feedMinus
      if(this.feedPlus===true){this.feedPlus = false}
    },
    scrapThisFeed() {
      if(this.scrapFeed){
        http.delete(`/scrap/delete`, {
          data:{
            scrap_user_email : this.loginUser,
            scrap_pheed_id : this.feedId,
          }
        })
      }else{
        const Form = {
          "scrap_user_email" : localStorage.getItem("user_email"),
          "scrap_pheed_id" : this.feedId,
        }
        http.post('/scrap/insert', Form)
      }
      this.scrapFeed = !this.scrapFeed
    },
    goToProfilePage(){
      this.$router.push(`/mypage/home/${this.feedUserEmail}`)
    },
    onFollowUser() {
      // 내가 이 사람을 팔로잉 요청 보냄
      this.isIFollowingThisPerson = !this.isIFollowingThisPerson
      const Form = {
        following: this.loginUserId ,
        temp_followers: this.feedUserEmail
      }
      http.post('/follow/reqfollow', Form)
      .then(()=>{
        alert(this.profileEmail + "님에게 팔로잉 요청을 보냈습니다. 허가가 날 때 까지 기다려 주세요");
      })
    },
    ilegalFeed(){
      alert('나쁜 게시글로 신고되었습니다.')
    }

  },
}
</script>

<style scoped>

button {
  border: none;
  background: none;
    
}
.btn-img {
  width: 30px;
  height: 30px;
}
span{
  z-index: 10;
  width: 100%;
  height: 100%;
}
#moreModal{
  position: absolute;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  z-index: 100;
  top: 50%;
  width: 15%;
  height: 80%;
  background-color: white;
}
.border-bottom {
  width: 80%;
  border-bottom: 1px solid black;
}
.cursor-pointer{
  cursor:pointer;
}
.margin-left{
  margin-left: 5%;
}
.carousel{
  display: flex;
  justify-content: center;
  position: relative;
  flex-direction: column;
  
}
.carousel-btn{
  z-index: 10;
  position: absolute;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.carousel-image-container{
  display: flex;
  justify-content: space-around;
  align-items: center;
  width: 100%;
  height:100%;
  /* height: 1000px; */
  /* animation: 3s ease-in 1s infinite reverse both running slidein; */

  /* height: 500px; */
}
.carousel-image{
  width: 33%;
  aspect-ratio: 1;
}
#feedItemForm{
  position: relative;
  display: grid;
  width: 80%;
  height: 100%;
  /* grid-row: min-content; */
  /* grid-auto-rows: 1fr; */
  grid-template-rows: 1fr 2fr 1fr;
  

}
.full {
  width: 100%;
  height: 100%;
}
.flex-col-center{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.flex-center{
  display: flex;
  justify-content: center;
  align-items: center;
}
.flex-end{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-end;
}
.flex-start{
  align-items: flex-start;
}
.flex-space-between {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
#feedTitle{
  display: grid;
  grid-template-columns: 1fr 1fr 1.5fr;
  position: relative;
}
#profileImg{
  width: 35%;
  aspect-ratio: 1;
  overflow: hidden;
  border-radius: 50%;
  background-color: white;
}
#feedItemTitle{
  font-size: 1rem;
  font-weight: bold;
}
#feedItemDate{

}
#moreBtn{
  font-size: 2.5rem;
  margin-right: 2%;
  cursor: pointer;
}
#followBtn{
  all: none;
  background-color: green;
  color: white;
  border-radius: 5px;
  width: 100px;
  aspect-ratio: 2;
  /* height: 20%; */
  font-size: 1rem;
  box-shadow: none;
  cursor: pointer;
}
#feedContent{
  padding: 0;
  
}
.half{
  width: 50%;
}
.justify-conetent-end{
  display: flex;
  justify-content: flex-end;
}
svg {
  cursor: pointer;
  font-size: 1.5rem;
  margin: 0 1%;
}
</style> 
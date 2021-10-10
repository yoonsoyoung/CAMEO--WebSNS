<template>
  <div id="pageHeader">
    <img  id="profileImage" :src="`https://i5c202.p.ssafy.io/cameo/user/getUserPic/${this.image}`" alt="">
    <div id="profileId">
      <div class="user-desc-box">
        <h1 class="d-flex">{{profileUserId}}</h1>
        <img id="profileDecoration" :src="`https://i5c202.p.ssafy.io/cameo/decoration/read/${this.decoUrl}`" alt="">
      </div>
      <div class="user-info-box">
        <div class="user-info-cbti" v-if=profileUserCbti>
          <span id="userCbti">{{profileUserCbti}}</span>
        </div>
      </div>
    </div>
    <div id="profileEtc">
      <div id="profileInfo">
        <div class="text-center basic-font-size">{{userPheedList}}<span class="min-info basic-font-size">글</span><br><span class="info basic-font-size">게시물</span></div>
        <div class="text-center basic-font-size">{{profileScore}}<span class="min-info basic-font-size">수</span><br><span class="info basic-font-size">도움</span> </div>
        <div class="text-center basic-font-size"><router-link to="/mypage/follow">{{profileFollower}}<span class="min-info basic-font-size">명</span><br><span class="info basic-font-size">팔로워</span> </router-link></div>
        <div class="text-center basic-font-size"><router-link to="/mypage/follow">{{profileFollowing}}<span class="min-info basic-font-size">명</span><br><span class="info basic-font-size">팔로잉</span></router-link></div>
      </div>
      <div class="center">
        <button v-if="user_email===loginEmail" id="infoBtn"  class="text-center basic-font-size" @click="goToProfileSetting">프로필 수정</button>
        <button v-else-if="isIFollowingThisPerson===false" id="followBtn"  class="text-center basic-font-size" @click="onFollowUser">팔로우 하기</button>
        <button v-else-if="isIFollowingThisPerson===true" id="unfollowBtn"  class="text-center basic-font-size" @click="onUnfollowUser">언팔로우</button>
      </div>
    </div>
  </div>
</template>

<script>
import http from '@/util/http-common.js'
import decoration from '@/assets/images/rank_pot.png'
export default {
  name : 'MyPageHeader',
  props : {
    user_email : String,
  },
  data() {
    return {
      image : '',
      decoration : decoration,
      profileUserId : '',
      profileUserCbti: '',
      profileUser : '',
      profileScore : 0,
      profileEmail : '',
      profileFollower : '',
      profileFollowing : '',
      isIFollowingThisPerson : false,
      userPheedList : 0,
      loginId : localStorage.getItem("user_id"),
      loginEmail : localStorage.getItem("user_email"),
      decoUrl: null,
    }
  },
  created () {
    const Form  = {
      I : localStorage.getItem("user_email"),
      ThisPerson : this.user_email
    }
    http.post('user/info', Form)
    .then((res)=>{
      if(res.data.userInfo.user_pic != null) {
        this.image = res.data.userInfo.user_pic;
      } else {
        this.image = 'defaultProfileImage.png';
      }
      this.isIFollowingThisPerson = res.data.isIFollowingThisPerson
      this.profileUserId =res.data.userInfo["user_id"]
      this.profileScore =  res.data.userInfo["user_total_score"]
      this.profileEmail = res.data.userInfo["user_email"]
      this.profileUserCbti = res.data.userInfo["user_CBTI"]
      http.get(`/decoration/score/${this.profileUserId}`)
      .then((res)=>{
        this.decoUrl = res.data.savename
        console.log(res.data)
      })
    })
    .catch(() => {
      window.location.reload();
    })

    http.get(`follow/followers?user_email=${this.user_email}`)
    .then((res)=>{
      this.profileFollower = res.data.followingList.length  
    })
    http.get(`follow/following?user_email=${this.user_email}`)
    .then((res)=>{
      this.profileFollowing = res.data.followersList.length
    })
    http.get(`/newspheed/{this.user_id}`)
    .then((res)=>{
      console.log(this.res.data.pheedList)
      this.userPheedList= this.res.data.pheedList.length
    })
    // http.get(`decoration/read/?user_id${this.userId}`)
    // .then((res)=>{
    //   console.log(res)
    // })

  },
  methods : {
    goToProfileSetting(){
      this.$router.push('/mypage/settings')
    },
    onFollowUser() {
      // 내가 이 사람을 팔로잉 요청 보냄
      this.isIFollowingThisPerson = !this.isIFollowingThisPerson
      const Form = {
        following: this.loginEmail,
        temp_followers: this.profileEmail
      }
      http.post('/follow/reqfollow', Form)
      .then(()=>{
        alert(this.profileEmail + "님에게 팔로잉 요청을 보냈습니다.");
      })
    },
    onUnfollowUser(){
      this.isIFollowingThisPerson = !this.isIFollowingThisPerson
    }
  }
}
</script>

<style scoped>
a {
	text-decoration: none;
	color: black;
}
  #pageHeader{
    display: grid;
    grid-template-columns: repeat(2, 1fr) 2fr; 
    place-items: center;
    box-sizing: border-box;
    /* background-color: lightcyan; */
    width: 70%;
    padding-bottom: 2%;
    border-bottom: 1px solid gray;
  }
  #profileImage{
    display: flex;
    /* background-image: url("../../assets/images/profile_default.png"); */
    background-size: cover;
    justify-content: center;
    align-items: center;
    background-color: #ececec;
    box-sizing: border-box;
    width: inherit;
    aspect-ratio: 1;
    border-radius: 50%;
    border: none;
  }
  #profileId{
    display: grid;
    grid-template-rows: repeat(2, 1fr);
    box-sizing: border-box;
    width: 100%;
    height: 100%;
  }
  .user-desc-box {
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    align-content: center;
    justify-content: flex-start;
    align-items: center;
  }
  .user-info-cbti {
  width: 40px;
  background: #D9C6B0;
  border-radius: 5px;
  text-align: center;
  line-height: 1.2em;
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  align-content: center;
  justify-content: center;
  align-items: center;
}
.user-info-cbti > span {
  font-size: 13px;
  font-weight: bold;
}
  #profileDecoration{
    width: 3.5vw;
    height: 3.5vw;
  }
  h1{
    display: flex;
    align-items: center;
    font-size: 3vw;
  }
  p{
    display: flex;
    align-items: center;
    font-size: 1vw;
  }
  #profileEtc{
    display: grid;
    grid-template-rows: 1fr 1fr;
    box-sizing: border-box;
    width: 100%;
    height: 100%;
  }
  #profileInfo{
    display: flex;
    justify-content: space-around;
    align-items: center;
  }
  .min-info{
    display: none;
  }
  .text-center { 
    text-align: center;
    
  }
  .basic-font-size{
    font-size: 1.5vw;
  }
  .center{
    display: flex;
    justify-content: center;
    align-items: center;
  }
  #infoBtn{
    display: flex;
    justify-content: center;
    align-items: center;
    width: 80%;
    height: 50%;
    border: 1px solid black;
    cursor: pointer;
    background-color: white;
    border-radius: 7px;
    font-size: 0.8em;
  }
  #followBtn{
    display: flex;
    justify-content: center;
    align-items: center;
    width: 80%;
    height: 50%;
    border: 1px solid black;
    cursor: pointer;
    background-color: white;
    border-radius: 7px;
    font-size: 0.8em;
  }
  #unfollowBtn{
        display: flex;
    justify-content: center;
    align-items: center;
    width: 80%;
    height: 50%;
    border: 2px solid black;
    cursor: pointer;
    background-color: white;
    border-radius: 7px;
    font-size: 1vw;
  }
  @media screen and (max-width: 768px) {
    .info{
      display: none;
    }
    .min-info{
      display: inline;
    }
  }
  @media (max-width: 479px) {
    .user-info-cbti {
      width: 28px;
      line-height: 1.0em;
    }
    .user-info-cbti > span {
      font-size: 0.2em;
    }
  }
</style>
<template>
  <main id="loginForm">
    <div id="loginFormHeader">
      <span id="loginIntroduction">나의 작고 소중한 카페를 함께 공유하는 공간</span>
      <h1 id="loginTitle">CAMEO</h1>
    </div>
    <div
      id="loginFormBody"
    >
      <div class="row">
        <label for="this.user.id">아이디</label>
        <div class="input-background">
          <input v-model="user.id" type="text" class="" placeholder="아이디를 입력해주세요" @keyup.enter="onLogin">
        </div>
      </div>
      <div class="row">
        <div>
          <label for="user_password">비밀번호</label>
        </div>
        <div class="input-background">
          <input v-model="user.pwd" id="password" :type="passwordType" placeholder="비밀번호를 입력하세요." @keyup.enter="onLogin"/>
        </div>
      </div>
      <div id="loginGoToSignup">
        <router-link to="/join">회원가입을 하시겠습니까?</router-link>
      </div>
      <!-- <button id="loginBtn" @click="onLogin">로그인</button> -->
      <button id="loginBtn" @click="onLogin">로그인</button>
      <div id="loginSocialLoginBackground">
        <h6 id="loginSociaLoginText">소셜 로그인</h6>
        <hr>
        <div class="social-images">
          <!-- <div class="social-image"><img :src="KakaotalkLogo" alt="" ></div> -->
          <div class="social-image" @click="onclickGoogleLogin">
            <img :src="GoogleLogo" alt="" >
            <span class="social-login-text">구글로 로그인</span>
          </div>
        </div>
      </div>
    </div>
  </main>
  
</template>

<script>

import KakaotalkLogo from '@/assets/images/KakaotalkLogo.png'
import GoogleLogo from '@/assets/images/GoogleLogo.png'
import NaverLogo from '@/assets/images/NaverLogo.png'
import http from "@/util/http-common.js"
import { login } from "@/api/UserApi.js"
import { mapState } from "vuex"
import { mapGetters } from "vuex"

export default {
  name : 'LoginForm',
  data: () => {
    return {
      KakaotalkLogo : KakaotalkLogo,
      GoogleLogo : GoogleLogo,
      NaverLogo : NaverLogo,
      passwordType: "password",
      user: {
        id: "",
        pwd: "",
      }
    };
  },
  computed: {
    ...mapState(["isLogin"]),
  },
  created() {

  },
  methods : {
    onLogin() {
      const userForm = {
        "user_id": this.user.id,
        "user_password": this.user.pwd,
      }
      http.post('/user/login', userForm)
      .then((res)=>{
        console.log(res)
        if(res.data.message==="fail"){alert('아이디나 비밀번호가 올바르지 않습니다.')}
        else{
          localStorage.setItem("access-token",res.data["access-token"])
          localStorage.setItem("user_email",res.data.member["user_email"])
          localStorage.setItem("user_id",res.data.member["user_id"])
          this.$router.replace(`mypage/home/${localStorage.getItem("user_email")}`)
        }
      })
      
    },
    login() {
      console.log(this.user.id + " , " + this.user.pwd);
      console.log("로그인");
      localStorage.setItem("access-token", "");
      this.user,
        (response) => {
          console.log(response);
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            this.$store.commit("setIsLogined", true);
            localStorage.setItem("access-token", token);
            this.$store.dispatch("GET_MEMBER_INFO", token);
            // this.$router.push("/");
          } else {
            //console.log("에러");
            alert("아이디가 존재하지 않거나 비밀번호가 틀립니다.");
          }
        },
        (error) => {
          console.error(error);
          alert("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
    },
    onclickGoogleLogin(e){
      var tmp = {};
      console.log('1') 
      tmp = window.location.replace("https://accounts.google.com/o/oauth2/v2/auth?client_id=1086668190258-4p95mu3hjaqq23isll8oac5bpps5hmli.apps.googleusercontent.com&redirect_uri=https://i5c202.p.ssafy.io/cameo/login/google/auth&response_type=code&scope=email%20profile%20openid&access_type=offline")
      this.$router.push('/mypage/settings')
      // console.log(document.evaluate("/html/body/pre/text()", document, null XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue)

      // .then((res)=>{console.log(res);});
      // localStorage.setItem("user_email", tmp.user.user_email);
      // localStorage.setItem("user_id", tmp.user.user_id);
      // localStorage.setItem("user_pic", tmp.user.user_pic);
    },
  }
}
</script>

<style scoped>
a {
	text-decoration: none;
	color: black;
}
  #loginForm{
    
  }
  #loginFormHeader{
    text-align: center;
  }
  #loginIntroduction{
    font-size: 1.5rem;
  }
  #loginTitle{
    font-family: 'Fredoka One','Lobster','Jua';
    color: #365959;
    font-size: 3rem;
    font: bold;
    margin-bottom: 10%;
  }
  #loginFormBody{
    /* width: 80%; */
    margin: 0 auto;
  }
  .row{
    justify-content: center;
    width: 100%;
    margin: 0;
    margin-top: 2%;
  }
  label {
    margin-top: 5%;
  }
  .input-background{
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 100%;
    padding: 10px;
    outline: none;
    background-color: rgba(220, 193, 158, 0.8);
    border-radius: 50px;
    border: none;
  }
  input {
    border: none;
    outline: none;
    background: transparent;
    margin-left: 1%;
    width: 85%;
  }
  #loginGoToSignup{
    display: flex;
    justify-content: flex-end;
    margin-top: 2%;
    margin-bottom: 2%;
  }
  .login-btn-background {
    display: flex;
    margin-top: 20%;
    justify-content: center;
    outline: none;
    background-color: rgba(166, 119, 78, 1);
    border-radius: 50px;
    border: none;
  }
  #loginBtn{
    color: white;
    outline: none;
    background-color: rgba(166, 119, 78, 1);
    border-radius: 50px;
    border: none;
    font-size: 1.3rem;
    line-height: 2.0em;
    text-align: center;
    width: stretch;
    margin-top: 15%;
  }
  #loginSocialLoginBackground{
    margin-top: 5%;
  }
  #loginSociaLoginText{
    text-align: center;
    color: #7a7a7a;
  }
  .social-images{
    display: flex;
    justify-content: space-around;
  }
  .social-image{
    width: 200px;
    height: 40px;
    background-color: #ececec;
    border-radius: 5px;
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    align-content: center;
    align-items: center;
    justify-content: center;
  }
  .social-image > img {
    width: 30px;
    height: 30px;
    margin-right: 10px;
  }
  .social-login-text {
    font-size: 1.0em;
  }
  @media (max-width: 479px) {
    #loginIntroduction {
      font-size: 1.2em;
      font-weight: 500;
      text-shadow: 0 0 10px white;
    }
  }
</style>
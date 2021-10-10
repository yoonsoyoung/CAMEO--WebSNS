<template>
  <main id="signupForm">
    <div id="signupFormHeader">
      <h6 id="signupIntroduction">나의 작고 소중한 카페를 함께 공유하는 공간</h6>
      <h1 id="signupTitle">CAMEO</h1>
      <h3 id="signupSubTitle">회원가입 페이지</h3>
    </div>
    <div id="signupFormBody">
      <div class="row">
        <label for="user_id">닉네임</label>
        <div class="input-background">
          <input v-model="user_id" type="text" class="" @keyup.enter="onSignup" placeholder="닉네임를 입력해주세요">
          <button @click="userIdCheck">{{userIdCheckValue}}</button>
        </div>
        <span class="error-text" id="errorId" v-if="error.user_id">{{error.user_id}}</span>
      </div>
      <div class="row">
        <label for="user_email">이메일</label>
        <div class="input-background" >
          <input v-model="user_email" placeholder="이메일을 입력하세요." type="text" @keyup.enter="onSignup" v-bind:class="{error : error.email, complete:!error.email&&user_email.length!==0}"/>
          <button @click="emailCheck">{{emailCheckValue}}</button>
        </div>
        <span class="error-text" v-if="error.email" id="errorEmail">{{error.email}}</span>
      </div>
      <div class="row">
        <div>
          <label for="password">비밀번호</label>
        </div>
        <div class="input-background" >
          <input 
          v-model="password" 
          id="password" 
          :type="passwordType" 
          placeholder="비밀번호를 입력하세요."
          v-bind:class="{error : error.password, complete:!error.password&&password.length!==0}"
          @keyup.enter="onSignup"
        />
        </div>
        <span class="error-text" v-if="error.password" >{{error.password}}</span>
      </div>
      <div class="row">
        <div>
          <label for="password-confirm">비밀번호 확인</label>
        </div>
        <div class="input-background" >            
          <input
            v-model="passwordConfirm"
            :type="passwordConfirmType"
            id="passwordConfirm"
            placeholder="다시 한번 입력하세요."
            @keyup.enter="onSignup"
          />
        </div>
        <span  v-if="error.passwordConfirm" class="d-flex error-text" id="errorPasswordConfirm">{{error.passwordConfirm}}</span>
      </div>
      <div id="signupGoToLogin">
        <router-link to="/">로그인 하시겠습니까?</router-link>
      </div>
      <button @click="onSignup" id="signupBtn">가입하기</button>    
      <div id="signupSocialLoginBackground">
        <p id="signupSociaLoginText">소셜 로그인</p>
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
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import http from "@/util/http-common.js";

export default {
  name : 'Signup',
  data: () => {
    return {
      KakaotalkLogo : KakaotalkLogo,
      GoogleLogo : GoogleLogo,
      NaverLogo : NaverLogo,
      user_email: "",
      password: "",
      passwordConfirm: "",
      user_id: "",
      isTerm: false,
      isLoading: false,
      error: {
        email: false,
        password: false,
        nickName: false,
        passwordConfirm: false,
        term: false,
        user_id: false,
      },
      isSubmit: false,
      passwordType: "password",
      passwordConfirmType: "password",
      passwordSchema: new PV(),
      termPopup: false,
      emailCheckValue : false,
      userIdCheckValue : false,
    }
  },
  watch: {
    user_id : function(v) {
      this.userIdForm();
    },
    password: function(v) {
      this.checkPasswordForm();
    },
    user_email: function(v) {
      this.checkEmailForm();
    },
    passwordConfirm: function(v){
      this.checkPasswordConfirmForm();
    },
  },
  methods : {
    userIdForm() {
      this.userIdCheckValue = false
      if(this.user_id ==='admin'){
        this.error.user_id = 'admin 닉네임은 사용이 불가능합니다.'
      }else{
        this.error.user_id = "";
      }
    },
    checkEmailForm() {
      this.emailCheckValue = false
      if (this.user_email.length >= 0 && !EmailValidator.validate(this.user_email))
        this.error.email = "올바른 이메일 형식이 아닙니다.";
      else this.error.email = false;
    
      if (this.user_email.length===0) {
          this.error.email = "";
      }
    },
    checkPasswordForm() {
      if (
        this.password.length >= 0 &&
        !this.passwordSchema.validate(this.password)
      )
        this.error.password = "비밀번호는 영문,숫자 포함 8 자리이상이어야 합니다.";
      else this.error.password = false;

      if (this.password.length===0) {
          this.error.password = "";
      }
    },
    emailCheck() {
      if(this.error.email === false && this.user_email.length !== 0){
        let emailForm = new FormData();
        emailForm.append("user_email", this.user_email)
        http.post("user/emailChk", emailForm)
        .then((res)=>{
          console.log(res)
          if (res.data ==="사용 가능한 이메일입니다."){
            this.emailCheckValue = true
          }else{
            this.error.email = '이미 존재하는 이메일입니다.'
          }
        })
      }
    },
    userIdCheck() {
      let nameForm = new FormData()
      nameForm.append("user_id", this.user_id)
      http.post("user/idChk", nameForm)
      .then((res)=>{
        console.log
        if(res.data==="사용 가능한 아이디입니다."){
          // window.document.getElementById('errorId').style.color="green"
          this.userIdCheckValue = true
          // this.error.user_id = "사용 가능한 아이디입니다."
        }else{this.error.user_id = '이미 존재하는 아이디입니다.'}
      })
    },
    onSignup() {
      if(this.emailCheckValue && this.userIdCheck && this.error.passwordConfirm ==="비밀번호가 일치합니다."){
          let userForm = {
            "user_email": this.user_email,
            "user_id": this.user_id,
            "user_password": this.password,
          }
          http.post('user/signUp', userForm)
          .then(()=>{
            let userForm = {
              "user_id": this.user_id,
              "user_password": this.password,
            }
            http.post('user/login', userForm)
            .then((res)=>{
              localStorage.setItem("access-token",res.data["access-token"])
              localStorage.setItem("user_id", res.data.member["user_id"])
              localStorage.setItem("user_email",res.data.member["user_email"])
              this.$router.replace(`/mypage/home/${this.user_email}`)
            })
          })
          .catch((err)=>{
            alert('예상치 못한 에러 발생')
          })
        }
      else{
        alert('조건을 확인해 주세요')
      }
    },
    checkPasswordConfirmForm(){
      if(this.password===this.passwordConfirm){
        this.error.passwordConfirm = "비밀번호가 일치합니다."
        window.document.getElementById('errorPasswordConfirm').style.color="green"

      }else{
        this.error.passwordConfirm = "비밀번호가 일치하지 않습니다."
        window.document.getElementById('errorPasswordConfirm').style.color="crimson"
      }
      if (this.password.length===0) {
        this.error.passwordConfirm = "비밀번호가 입력되지 않았습니다."
      }
      if(!this.passwordSchema.validate(this.password)){
        this.error.passwordConfirm = "비밀번호는 영문,숫자 포함 8 자리이상이어야 합니다."
        window.document.getElementById('errorPasswordConfirm').style.color="crimson"
      }
      if (this.passwordConfirm.length===0) {
          this.error.passwordConfirm = "";
      }
    },
    onclickGoogleLogin(e){
        var tmp = {};
        tmp = window.location.replace("https://accounts.google.com/o/oauth2/v2/auth?client_id=1086668190258-4p95mu3hjaqq23isll8oac5bpps5hmli.apps.googleusercontent.com&redirect_uri=https://i5c202.p.ssafy.io/cameo/login/google/auth&response_type=code&scope=email%20profile%20openid&access_type=offline%22")
        .then((res)=>{console.log(res)})
        localStorage.setItem("user_email", tmp.user.user_email)
        localStorage.setItem("user_id", tmp.user.user_id)
        localStorage.setItem("user_pic", tmp.user.user_pic)
    },


  },  

  created() {
    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(100)
      .has()
      .digits()
      .has()
      .letters();
  },
}
</script>
<style scoped>
a {
	text-decoration: none;
	color: black;
}
  #signupForm {

  }
  #signupFormHeader{
    text-align: center;
  }
  #signupIntroduction{
    font-size: 1.5rem;
  }
  #signupTitle{
    font-family: 'Fredoka One','Lobster','Jua';
    color: #365959;
    font-size: 3rem;
    font: bold;
    margin-bottom: 10%;
  }
  #signupSubTitle{
    font-size: 1.5rem;
    margin-bottom: 10%;
  }
  #signupFormBody{
    /* width: 80%; */
    margin: 0 auto;
  }
  label{
    margin-top: 5%;
  }
  .row{
    justify-content: center;
    width: 100%;
    margin: 0;
    margin-top: 2%;
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
  .error-text{
    color: crimson;
  }
  .success-text{
    color:green;
  }
  button {
    font-size: 0.7rem;
    background-color: transparent;
    border: none;
    outline: none;
  }
  #signupGoToLogin{
    display: flex;
    justify-content: flex-end;
    margin-top: 2%;
    margin-bottom: 2%;
  }
  #signupBtn{
    color: white;
    outline: none;
    background-color: rgba(166, 119, 78, 1);
    border-radius: 50px;
    border: none;
    font-size: 1.3rem;
    text-align: center;
    width: stretch;
    margin-top: 15%;
  }
  #signupSocialLoginBackground{
    margin-top: 5%;
  }
  #signupSociaLoginText{
    text-align: center;
  }
  .social-images{
    display: flex;
    justify-content: space-around;
  }
  .social-image{
    cursor:pointer;
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
    #signupIntroduction {
      font-size: 1.2em;
      font-weight: 500;
      text-shadow: 0 0 10px white;
    }
  }
</style>
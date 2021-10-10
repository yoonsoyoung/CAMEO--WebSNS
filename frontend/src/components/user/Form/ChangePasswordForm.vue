<template>
  <div id="changePasswordForm">
    <div class="row">
      <label for="oldPassword">현재 비밀번호</label>
      <input v-model="oldPassword" id="oldPassword" placeholder="현재 비밀번호를 입력하세요." type="password" />
      <div class="error-text" v-if="error.oldPassword">{{error.oldPassword}}</div>
    </div>
    <div class="row">
      <label for="password">비밀번호</label>
      <input v-model="password" id="newPassword" placeholder="변경할 비밀번호를 입력하세요." type="password" />
      <div class="error-text" v-if="error.password">{{error.password}}</div>
    </div>
    <div class="row">
      <label for="passwordConfirm">비밀번호 확인</label>
      <input v-model="passwordConfirm" id="newPasswordConfirm" placeholder="다시 한 번 입력해주세요" type="password" />
      <div class="error-text" v-if="error.passwordConfirm" id="errorPasswordConfirmText">{{error.passwordConfirm}}</div>
    </div>
    <div id="change-password-btn-background">
      <button id="changePasswordBtn" @click="onChangePassword">수정하기</button>
    </div>
  </div>
</template>

<script>
import PV from "password-validator";
import http from '@/util/http-common.js'
export default {
  name : 'ChangePasswordForm',
    data () {
    return {
      title : '비밀번호 변경하기',
      password : '',
      passwordConfirm : '',
      oldPassword : '',
      error : {
        oldPassword : '',
      },
      passwordSchema :new PV(),
    }
  },
  create() {
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
  watch:{
    // oldPassword(v) {
    //   this.onCheckOldPassword()
    // },
    password(v){
      this.onCheckPassword()
    },
    passwordConfirm(v) {
      this.onCheckPasswordConfirm()
    }
  },
  methods : {
    // onCheckOldPassword(){
    //   const access_token = localStorage.getItem("access-token")
    //   const user_email = localStorage.getItem("user_email")
    //   const Form = {
    //     'access-token' : access_token,
    //     'user_email' : user_email
    //   }

    //   http.GET('', Form)
    //   .then((res)=>{
    //     const presentPassword = res.data.password
    //     if (presentPassword === this.oldPassword){
    //       this.error.oldPassword = '비밀번호가 일치합니다.'
    //       document.getElementsByClassName('error-text').style.color="green"
    //     }
    //   })
      
    // },
    onCheckPassword() {
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
    onCheckPasswordConfirm() {
      if(this.password===this.passwordConfirm){
        this.error.passwordConfirm = "비밀번호가 일치합니다."
        window.document.getElementById('errorPasswordConfirmText').style.color="green"
      }else{
        this.error.passwordConfirm = "비밀번호가 일치하지 않습니다."
      }
      if (this.password.length===0) {
        this.error.passwordConfirm = "비밀번호가 입력되지 않았습니다."
      }
      if(!this.passwordSchema.validate(this.password)){
        this.error.passwordConfirm = "비밀번호는 영문,숫자 포함 8 자리이상이어야 합니다."
      }
      if (this.passwordConfirm.length === 0) {
          this.error.passwordConfirm = "";
      }
    },
    onChangePassword() {
      if (this.password === this.passwordConfirm) {
        const user_email = localStorage.getItem("user_email")
        const form = {
          "user_email": user_email,
          "user_password": this.password,
          "user_total_score": 0
        }
        http.post('/user/changePW', form)
        .then(()=>{
          alert('비밀번호가 변경되었습니다.');
          this.$router.push(`/mypage/home/${form.user_email}`);
        })
      }
    }
  }
}
</script>

<style scoped>
  #changePasswordForm { 
    position: relative;
    display: grid;
    place-items: center;
    margin: 20% 0;
  }
  .row{
    display: flex;
    flex-direction: column;
    width: 50vh;
    height: 20vh;
  }
  .row > label{
    font-size: 1rem;
    margin-bottom: 1vh;
  }
  .row > input {
    font-size: 1rem;
    outline: none;
    border: none;
    border-bottom: 1px black solid;
  }
  #changePasswordBtn{
    background-color: rgba(166, 119, 78, 1);
    color: white;
    border: none;
    font-size: 1rem;
    border-radius: 50px;
    width: 23vh;
    height: 8vh;
  }
  .error-text{
    font-size: 0.5rem;
    color: crimson;
  }
</style>
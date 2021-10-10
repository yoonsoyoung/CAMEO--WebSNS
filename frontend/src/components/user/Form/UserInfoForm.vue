<template>
  <div id="userInfoFormRoot">
    <div id="userImageBackground">
      <div id="userProfileImageContainer">
        <img :src="`https://i5c202.p.ssafy.io/cameo/user/getUserPic/${this.user_pic}`" alt="" id="userProfileImage">
      </div>
      <div id="inputBg" class="mt-4">
        <input type="file" class="input-file" @change="uploadImage" accept="image/*" enctype="multipart/form-data">
        <div class="upload-btn">
          <span @click="onChangeProfileImage" class="change-pic-btn"><i class="fas fa-upload"></i></span>
        </div>
      </div>
    </div>
    <div id="infoUserIdBackground">
      <label class="basic-font-size" for="userId">아이디</label>
      <div id="userIdInputBacground">
        <input class="basic-font-size" style="border:none;" v-model="userId"  type="text" id="userIdInput"/>
        <button class="basic-font-size" @click="userIdCheck" id="userIdCheckBtn">{{userIdCheckValue}}</button>
      </div>
    </div>
    <div id="infoEmailBackground" class="mt-3">
      <label class="basic-font-size" for="userEmail">이메일</label>
      <div class="basic-font-size" id="userEmail">{{userEmail}}</div>
    </div>
    <div id="btnGroup" class="mt-3">
      <router-link to="/changepassword"><button class="basic-font-size modify-btn" id="changePasswordBtn">비밀번호 변경하기</button></router-link>
      <button class="basic-font-size modify-btn" id="changeNickNameBtn" @click="onChangeNickName">아이디 수정하기</button>
    </div>
    <div id="removeBackground">
      <hr class="remove-hr">
      <router-link to="/removeuser" id="goToRemovePage">회원탈퇴하기</router-link>
    </div>
    <img :src="imageSrc" alt="" id="profileIm">
  </div>
</template>

<script>
// import image from '@/assets/images/profile_default.png'
import http from "@/util/http-common.js";

export default {
  name : 'UserInfoForm',
  data() {
    return {
      imageSrc: `https://i5c202.p.ssafy.io/cameo/user/getUserPic/`,
      image : null,
      userId : '',
      userEmail : '',
      userIdCheckValue : false,
      changeImage : '',
      user_pic: '',
    }
  },
  created ()  {
    this.userEmail = localStorage.getItem("user_email")
    const user = {
      "I": this.userEmail,
      "ThisPerson": this.userEmail
    };
    http.post(`/user/info`, user)
    .then((res) => {
      this.user_pic = res.data.userInfo.user_pic;
      this.userId = res.data.userInfo.user_id;
    });
    
},
  methods : {
      userIdCheck() {
      if(this.userId.length !== 0){
        let nameForm = new FormData();
        nameForm.append("user_id", this.userId)
        http.post("/user/idChk", nameForm)
        .then((res)=>{
          if (res.data==='이미 존재하는 아이디입니다.'){
            this.userIdCheckValue = false
            alert('이미 존재하는 닉네임입니다.')
          }else{
            alert('사용가능한 닉네임입니다.')
            this.userIdCheckValue = true
          }
        })
      }else{
        alert('바꿀 닉네임을 입력해 주세요')
      }
    },
    onChangeNickName() {
      if(this.userIdCheckValue){
        const Form  = {
          "user_id" : this.userId, 
          "user_email" : this.userEmail
        }
        http.post('/user/changeID',Form)
        .then(
          localStorage.setItem("user_id", Form.user_id),
          this.$router.push(`/mypage/home/${Form.user_email}`)
        )
      }else{
        alert('아이디 중복체크를 진행해 주세요')
      }
    },
    uploadImage(event){
        const file = event.target.files[0]
        this.changeImage = file
    },
    onChangeProfileImage(){
      if (confirm('이미지를 변경하시겠습니까?')){
        const Form = new FormData();
        Form.append('userEmail', this.userEmail)
        Form.append('file', this.changeImage)
        http.post('user/saveUserPic', Form)
        .then((res)=>{
          this.image = this.changeImage
          this.changeImage = null
          this.$router.go(0)
        })
      }
    },
    getUserInfo() {
      
    }
  }
}
</script>

<style scoped>
#userInfoFormRoot {
  width: 100%;
  position: relative;
  top: 150px;
  left: 0;
}
#profileIm{
  width: 1000px;
}
  .basic-font-size {
    font-size: 1rem;
  }
  hr {
    color : rgba(198, 198, 198, 1);
  }
  button {
    border:  none;
  }
  label{
    margin-right: 5%;
  }
  #userImageBackground{
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  #userProfileImageContainer{
    width: 200px;
    height: 200px;
    border-radius: 50%;
    overflow: hidden;
    background-color: #ececec;
  }
  #userProfileImage{
    width: 100%;
    height: 100%;
    margin: auto;
  }
  #infoUserIdBackground{
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    margin-top: 50px;
  }
  #userIdInputBacground{
    display: flex;
    justify-content: space-between;
    width: 50%;
    border: none;
    border-bottom: 1px solid black;
    outline: none;
  }

  #userIdInput{
    width: 80%;
  }
  #userIdInput:focus{
    outline: none;
  }
  #userIdCheckBtn{
    background: none;
  }
  #userEmail{
    width: 50%;
  }
  #changePasswordBtn{
    background-color: rgba(220, 193, 158, 1);
    width: 174px;
    height: 40.57px;
    color: white;
    margin:0px;
    border-radius: 50px;
  }
  #changeNickNameBtn{
    background-color: rgba(166, 119, 78, 1);
    width: 174px;
    height: 40.57px;
    color:white;
    border-radius: 50px;
    background-color: none;
  }
  #infoEmailBackground{
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    
  }
  #btnGroup{
    display:flex;
    flex-direction: row;
    flex-wrap: nowrap;
    justify-content: center;
    align-items: center;
  }
  #removeBackground{
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 80vw;
  }
  #goToRemovePage{
    color: #c4c4c4;
    
  }
#inputBg {
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  align-content: center;
  justify-content: center;
  align-items: center;
}
.upload-btn {
  width: 25px;
  height: 25px;
  border: 1px solid #ececec;
  border-radius: 5px;
  text-align: center;
}
.change-pic-btn {
  color: #365959;
}
.remove-hr {
  width: 70%;
}
.modify-btn {
  margin: 10px;
}
@media (max-width: 479px) {
  .modify-btn {
    width: 100px;
  }
  #btnGroup {
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    align-content: center;
    justify-content: center;
    align-items: center;
  }
  .input-file {
    width: 200px;
  }
}
</style>
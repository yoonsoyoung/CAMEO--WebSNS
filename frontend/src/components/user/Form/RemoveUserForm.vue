<template>
  <div id="removeUserForm">
      <h6 style="margin-bottom:100px; margin-top:100px;">{{userId}}님 함께한 추억이 모두 사라져요<br>정말 탈퇴하실 건가요?</h6>
      <div id="profileImage" style="margin-bottom:200px">
        <img :src="`https://i5c202.p.ssafy.io/cameo/user/getUserPic/${this.userPic}`" alt="" style="width:400px;">
      </div>
      <button id="removeUserBtn" @click="onRemoveUser">탈퇴하기</button>
    </div>
</template>

<script>
import http from "@/util/http-common.js"
import image from '@/assets/images/profile_image.png'

export default {
  name : 'RemoveUserForm',
  data () {
    return {
      image : image,
      userEmail : localStorage.getItem("user_email"),
      userId : localStorage.getItem("user_id"),
      userPic : '',
    }
  },
  created(){
    const Form = {
      "I" : localStorage.getItem("user_email"),
      "ThisPerson" : localStorage.getItem("user_email")
    }
    http.post(`user/info`, Form)
    .then((res)=>{
      this.userPic=res.data.userInfo.user_pic
    })

  },
  methods : {
      onRemoveUser(){
      let url = encodeURI(`user/delete/${this.user_email}`)
      if(confirm('정말 탈퇴하시겠습니까?')){
        http.delete(url)
        .then((res)=>{
          console.log(res)
          localStorage.removeItem("access-token")
          localStorage.removeItem("user_email")
          alert('삭제가 완료 되었습니다.')
          this.$router.push('/')
        })
      }
    }
  }
}
</script>

<style>
  #profileImage{
    display: flex;
    justify-content: center;
  }
  #removeUserBtn{
    background-color: rgba(54, 89, 89, 1);
    width: 174px;
    height: 40.57px;
    border-radius: 50px;
    color: white;
  }
  #removeUserForm{
    display: grid;
    place-items: center;
    margin: 20% 0;
  }
</style>
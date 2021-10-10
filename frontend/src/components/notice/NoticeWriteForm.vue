<template>
  <div id="NoticeWriteForm">
    <div class="row">
      <label for="noticeTitle">제목</label>
      <div>
        <input type="text" v-model="noticeTitle">
      </div>
    </div>
    <div class="row">
      <label for="author">작성자</label>
      <div>
        <span>admin</span>
      </div>
    </div>
    <div class="row">
      <label for="noticeContent">내용</label>
      <div>
        <textarea name="" id="" cols="20" rows="8" v-model="noticeContent"></textarea>
      </div>
    </div>
    <button v-if="this.loginUser==='admin'" id="noticeUploadBtn" @click="onCreateNotice">등록</button>
  </div>
</template>

<script>
import http from "@/util/http-common.js";

export default {
  name : 'NoticeWriteForm',
  data(){
    return {
      loginUser : localStorage.getItem("user_id"),
      noticeTitle : '',
      noticeContent : '',
    }
  },
  methods : {
    onCreateNotice() {
      if (this.loginUser === 'admin'){
        if (this.noticeTitle !== null && this.noticeContent !== null){
          const Form = {
            notice_title : this.noticeTitle,
            notice_content : this.noticeContent
          }
          http.post('notice/insert', Form)
          .then((res)=>{
            const num = res.data.notice_id
            this.$router.push({path: `/notice/read/${num}`} )
            // this.$router.go(0)
          })
        }
      }else{
        alert('관리자만 접근 가능합니다.')
      }
    }
  }

}
</script>

<style scoped>
  .row{
    display: grid;
    grid-template-columns: 1fr 4fr;
    justify-content: center;
    width: 80%;
    margin-bottom: 5%;
  }
  label {
    text-align: center;
    font-size: 1.3rem;
    font-weight: bold;
  }
  input {
    width: 100%;
    border: none;
    border-bottom: 1px solid black;
    font-size: 1rem;
  }
  input:focus{
    outline: none;
  }
  textarea {
    width: 100%;
    font-size: 1rem;
    border: 1px solid gray;
    border-radius: 5px;
  }
  textarea:focus{
    outline: none;
  }
  #noticeUploadBtn{
    width: 11%;
    aspect-ratio: 3.5;
    border: none;
    border-radius: 7px;
    outline: none;
    background: #365959;
    font-size: 1.1rem;
    color: white;
    cursor: pointer;
  }
</style>
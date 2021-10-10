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
    <button id="noticeUploadBtn" @click="onUpdateNotice">등록</button>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
export default {
  name : 'NoticeUpdateForm',
  props : {
    notice_id : String,
  },
  data(){
    return {
      noticeTitle : '',
      noticeContent : '',
    }
  },
  created() {
    http.get(`notice/list/${this.notice_id}`)
    .then((res)=>{
      this.noticeTitle = res.data["notice_title"]
      this.noticeContent = res.data["notice_content"]
    })
  },
  methods : {
    onUpdateNotice(){
      const Form = {
        notice_id : this.notice_id,
        notice_title : this.noticeTitle,
        notice_content : this.noticeContent
      }
      http.put('notice/update', Form)
      .then(()=>{
        this.$router.replace(`/notice/read/${this.notice_id}`)
      })
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
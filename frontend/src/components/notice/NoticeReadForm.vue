<template>
  <div id="noticeReadForm">
    <div class="total-width" id="noticeReadFormBtnGroup" v-if="loginUser==='admin'">
      <button id="noticeEditBtn" @click="onEditNotice">수정</button>
      <button id="noticeDelBtn" @click="onDelNotice">삭제</button>
    </div>
    <div class="total-width" id="noticeTitleBackground">
      <h1 id="noticeTitle">{{title}}</h1>
    </div>
    <div class="total-width" id="noticeInfoBackground">
      <div id="noticeId">No. {{number}}</div> 
      <div id="noticeDate">{{date}}</div>
    </div>
    <div class="total-width" id="noticeContentBackground"><p id="noticeContent">{{content}}</p></div>
    <div class="total-width" id="noticeFileBackground"><span id="noticeFile">첨부파일: {{file}}</span></div>
    <button @click="goToIndex" id="indexBtn">목록</button>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
export default {
  name : 'NoticeReadForm',
  data(){
    return {
      loginUser : localStorage.getItem("user_id"),
      title : '',
      number : '',
      date : '',
      content : '',
      file : '',
    }
  },
  props :{
    notice_id : String,
  },
  created() {
    http.get(`/notice/list/${this.notice_id}`)
    .then((res)=>{
      if (res.data ===""){
        this.$router.push('/404')
      }
      this.title = res.data["notice_title"]
      this.number = res.data["notice_id"]
      this.date = res.data["notice_date"]
      this.content = res.data["notice_content"]
      this.file = res.data["notice_pic"]
    })
  },
  methods : {
    goToIndex() {
      this.$router.push('/notice')
    },
    onEditNotice() {
      this.$router.push(`/notice/update/${this.notice_id}`)
    },
    onDelNotice() {
      http.delete(`/notice/delete/${this.number}`)
      .then(
        this.$router.push('/notice')
      )
    },
  }
}
</script>

<style scoped>
  .total-width{
    width: 80%; 
  }
  #noticeReadFormBtnGroup{
    display: flex;
    justify-content: flex-end;
    align-items: flex-end;
  }
  #noticeEditBtn{
    background-color: rgb(163, 163, 0);
    color: white;
    cursor: pointer;
  }
  #noticeDelBtn{
    background-color: red;
    color: white;
    cursor: pointer;
  }
  #noticeTitleBackground{
    text-align: center;
    border-top: 3px solid black;
    border-bottom: 3px solid black;
  }
  #noticeTitle{
    font-size: 1.5rem;
    margin: 2% 0;
  }
  #noticeInfoBackground{
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  #noticeId{
    margin-left: 2%;
    font-weight: bold;
  }
  #noticeDate{
    margin-right: 2%;
  }
  #noticeContentBackground{
    border-top: 1px solid gray;
    /* border-bottom: 1px solid gray; */
    min-height: 30vh;
  }
  #noticeContent{
    margin: 2%; 
  }
  #noticeFileBackground{
    display: flex;
    align-items: center;
    border-top: 1px solid gray;
    border-bottom: 1px solid gray;
  }
  #noticeFile{
    margin: 2%;
  }
  #indexBtn{
    margin-top: 10%;
    margin-bottom: 10%;
    width: 10%;
    font-size: 1.1rem;
    font-weight: bolder;
    border-radius: 5px;
    aspect-ratio: 3;
    border: none;
    outline: none;
    background-color: #C4C4C4;
    cursor: pointer;
  }
  @media screen and (max-width: 650px) {
    * {
      font-size: 7px;
    }
    #indexBtn{
      width: 30%;
      /* height: 50%; */
      font-size: 1px;
    }
  }
</style>
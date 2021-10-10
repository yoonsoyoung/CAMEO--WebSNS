<template>
    <div id="inquiryReadForm">
    <div class="total-width" id="inquiryReadFormBtnGroup" v-if="this.loginUser===this.inquiryAuthor">
      <button id="inquiryEditBtn" @click="onEditInquiry">수정</button>
      <button id="inquiryDelBtn" @click="onDelInquiry">삭제</button>
    </div>
    <div class="total-width" id="inquiryTitleBackground">
      <h1 id="inquiryTitle">{{title}}</h1>
    </div>
    <div class="total-width" id="inquiryInfoBackground">
      <div id="inquiryId">No. {{number}}</div> 
      <div id="inquiryDate">{{date}}</div>
    </div>
    <div class="total-width" id="inquiryContentBackground"><p id="inquiryContent">{{content}}</p></div>
    <div class="total-width" id="inquiryFileBackground"><span id="inquiryFile">첨부파일: {{file}}</span></div>
    <button @click="goToIndex" id="indexBtn">목록</button>
  </div>
</template>

<script>
import http from "@/util/http-common.js";

export default {
  name : 'InquiryReadForm',
    data(){
    return {
      loginUser : localStorage.getItem("user_id"),
      title : '',
      number : '',
      date : '',
      content : '',
      file : '',
      inquiryAuthor : '',
      checkAdmin : true,
    }
  },
  props :{
    inquiry_id : String,
  },
  created() {
    http.get(`/inquiry/list/${this.inquiry_id}`)
    .then((res)=>{
      console.log(res.data)
      this.title = res.data["inquiry_title"]
      this.number = res.data["inquiry_id"]
      this.date = res.data["inquiry_date"]
      this.content = res.data["inquiry_content"]
      this.file = res.data["inquiry_pic"]
      this.inquiryAuthor = res.data["inquiry_user_id"]
    })
  },
  methods : {
    goToIndex() {
      this.$router.push('/inquiry')
    },
    onEditInquiry() {
      this.$router.push(`/inquiry/update/${this.inquiry_id}`)
    },
    onDelInquiry() {
      http.delete(`/inquiry/delete/${this.number}`)
      .then(
        this.$router.push('/inquiry')
      )
    },
  }
}
</script>

<style scoped>
  .total-width{
    width: 80%; 
  }
  #inquiryReadFormBtnGroup{
    display: flex;
    justify-content: flex-end;
    align-items: flex-end;
  }
  #inquiryEditBtn{
    background-color: rgb(163, 163, 0);
    color: white;
    cursor: pointer;
  }
  #inquiryDelBtn{
    background-color: red;
    color: white;
    cursor: pointer;
  }
  #inquiryTitleBackground{
    text-align: center;
    border-top: 3px solid black;
    border-bottom: 3px solid black;
  }
  #inquiryTitle{
    font-size: 1.5rem;
    margin: 2% 0;
  }
  #inquiryInfoBackground{
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  #inquiryId{
    margin-left: 2%;
    font-weight: bold;
  }
  #inquiryDate{
    margin-right: 2%;
  }
  #inquiryContentBackground{
    border-top: 1px solid gray;
    /* border-bottom: 1px solid gray; */
    min-height: 30vh;
  }
  #inquiryContent{
    margin: 2%; 
  }
  #inquiryFileBackground{
    display: flex;
    align-items: center;
    border-top: 1px solid gray;
    border-bottom: 1px solid gray;
  }
  #inquiryFile{
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
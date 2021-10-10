<template>
    <div id="InquiryWriteForm">
    <div class="row">
      <label for="inquiryTitle">제목</label>
      <div>
        <input type="text" v-model="inquiryTitle" >
      </div>
    </div>
    <div class="row">
      <label for="author">작성자</label>
      <div>
        <span>{{inquiryAuthor}}</span>
      </div>
    </div>
    <div class="row">
      <label for="inquiryContent">내용</label>
      <div>
        <textarea name="" id="" cols="20" rows="8" v-model="inquiryContent"></textarea>
      </div>
    </div>
    <button id="inquiryUpdateBtn" @click="onUpdateInquiry">등록</button>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
export default {
  name : 'InquriyCreateForm',
  data(){
    return {
      inquiryTitle : '',
      inquiryContent : '',
      inquiryAuthor : localStorage.getItem("user_id"),
    }
  },
  props :{
    inquiry_id : String,
  },
  created() {
    http.get(`/inquiry/list/${this.inquiry_id}`)
    .then((res)=>{
      this.inquiryTitle = res.data["inquiry_title"]
      this.number = res.data["inquiry_id"]
      this.date = res.data["inquiry_date"]
      this.inquiryContent = res.data["inquiry_content"]
      this.file = res.data["inquiry_pic"]
    })
  },
  methods : {
    onUpdateInquiry() {
      const Form = {
        inquiry_id : this.number,
        inquiry_user_id : localStorage.getItem("user_id"),
        inquiry_user_email : localStorage.getItem("user_email"),
        inquiry_title : this.inquiryTitle,
        inquiry_content : this.inquiryContent
      }
      http.put(`inquiry/update`, Form)
      .then((res)=>{
        this.$router.push(`/inquiry/list/${this.number}`)
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
  #inquiryUpdateBtn{
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
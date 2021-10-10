<template>
    <div id="InquiryWriteForm">
    <div class="row">
      <label for="inquiryTitle">제목</label>
      <div>
        <input type="text" v-model="inquiryTitle">
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
    <button id="inquiryCreateBtn" @click="onCreateInquiry">등록</button>
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
      inquiryAuthorEmail : localStorage.getItem("user_email"),
    }
  },
  methods : {
    onCreateInquiry() {
      if (this.inquiryTitle.length > 0 && this.inquiryContent.length >0){
        const Form = {
          inquiry_title : this.inquiryTitle,
          inquiry_content : this.inquiryContent,
          inquiry_user_id : this.inquiryAuthor,
          inquiry_user_email : this.inquiryAuthorEmail
        }
        http.post('inquiry/insert', Form)
        .then((res)=>{
          const num = res.data.inquiry_id
          this.$router.push({path: `/inquiry/read/${num}}`} )
        })
      }else{
        alert('모든 정보를 입력해주세요')
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
  #inquiryCreateBtn{
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
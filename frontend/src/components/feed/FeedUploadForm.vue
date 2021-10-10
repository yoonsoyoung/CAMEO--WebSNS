<template>
  <div id="feedUploadFormContainer">
    <div class="row">
      <label>제목</label>
      <div>
        <input type="text" v-model="feedtitle">
      </div>
    </div>
    <div class="row">
      <label>사진</label>
      <div
        class="image-container"
      > 
        <div 
          class="image-box"
          v-for="(image, idx) in images"
          :key="idx"  
        >  
          <div
            v-if="image"
            class="upload-image-container"
          >
            <img :src="image" alt="사용자가 올린 이미지" class="image">
            <div class="edit-image-btn">
              <label  style="color:white; font-size: 0.5rem" id="edit-image-label" for="imageEdit" @click="getIdx(idx)">수정</label>
              <input style=" display:none;" type="file" id="imageEdit" accept="image/*" @change="editImage" enctype="multipart/form-data">
            </div>
            <div class="del-image-btn" @click="delImage(idx)">삭제</div>
          </div>
          <div v-else>
            <label style="cursor:pointer;" for="imageInput">+</label>
            <input style=" display:none;" type="file" id="imageInput" accept="image/*" @change="uploadImage" enctype="multipart/form-data">
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <label>내용</label>
      <textarea name="" id="" cols="20" rows="7" v-model="feedcontent"></textarea>
    </div>
    <!-- <div class="row">
      <label>태그</label>
      <input type="text" v-model="tags">
    </div> -->
    <button id="feedUploadBtn" @click="onCreateFeed">등록</button>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
export default {
    name: "FeedUploadForm",
    data() {
      return {
        feedtitle : "",
        images : [''],
        dataImages : [''],
        feedcontent : "",
        tags : [],
        url : '',
        num : 0,
        idx: '',
      }
    },
    methods : {
      uploadImage(event){
        const file = event.target.files[0]
        this.dataImages[this.num] = file
        this.url = URL.createObjectURL(file)
        this.images[this.num] = this.url
        this.num = this.num + 1
        this.images.push('')
      },
      getIdx(idx){
        this.idx = idx
      },
      editImage(e){
        const file = e.target.files[0]
        this.$set(this.dataImages, this.idx, file)
        this.url = URL.createObjectURL(file)
        this.$set(this.images, this.idx, this.url)
      },
      delImage(idx){
        this.images.splice(idx,1)
        this.dataImages.splice(idx,1)
        for(let i= 0 ; i< this.images.length ;i++) {
          if (this.images[i] === ""){
            this.num = i
            break
          }
        }
      },
      onCreateFeed(){
        let Form = {
          "pheed_user_id": localStorage.getItem("user_email"),
          "pheed_title" : this.feedtitle,
          "pheed_content" : this.feedcontent
        }
        let formData = new FormData()
        // const file = document.getElementById("userImage").files
        // formData.append("files", file)
        for(let i= 0; i < this.dataImages.length; i++){formData.append("file", this.dataImages[i])}
        formData.append("pheed", new Blob([JSON.stringify(Form)]))
        console.log(formData)
        http.post('/newspheed/write', formData, { 
          header : {'Context-type' : 'multipart/form-data',},
          data : formData,
        })
        .then((res)=>{this.$router.push(`/feed/${res.data.pheedId}`)})
      }
    },
    
    
}
</script>

<style scoped>
  #feedUploadFormContainer{
    width: 80%;
  }
  .row{
    display: grid;
    grid-template-columns: 1fr 4fr;
    justify-content: center;
    width: 80%;
    margin-bottom: 5%;
  }
  label {
    text-align: center;
    font-size: 1rem;
    font-weight: bolder;
  }
  input {
    border: none;
    font-size: 1rem;
    border-bottom: 1px solid black;
    width: 100%;
    font: none;
  }
  input:focus{
    outline: none;
  }
  .image-container{
    display: grid;
    grid-auto-flow: column;  
    overflow: auto;
    justify-content: flex-start;
    gap: 5%;
  }
  .image {
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #C4C4C4;
    width: 150px;
    aspect-ratio: 1;
  }
  .edit-image-btn{
    position: absolute;
    top:25%;
    left:50%;
    width: 75%;
    transform: translate( -50%, -50% );
    text-align:center;
    font-size: 0.5rem;
    color: white;
  }
  .edit-image-label:hover{
    color: yellow;
    cursor: pointer;
  }
  .del-image-btn{
    top:75%;
    left:50%;
    width: 75%;
    transform: translate( -50%, -50% );
    position: absolute;
    text-align:center;
    color:white;
    font-size: 0.5rem;
  }
  .del-image-btn:hover{
    color:red;
    cursor: pointer;
  }
  .image-box{
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #C4C4C4;
    width: 150px;
    aspect-ratio: 1;
  }
  .image-box label{
    display: flex;
    justify-content: center;
    align-items: center; 
    color: black; 
    cursor: pointer; 

    font-size: 3rem;
  }
  .image-box input{
    position: relative; 
    padding: 0; 
    margin: -1px; 
    overflow: hidden; 
    clip:rect(0,0,0,0); 
    border: 0;
  }
  #feedUploadBtn{
    width: 10%;
    aspect-ratio: 2;
    border: none;
    border-radius: 4px;
    outline: none;
    background: #365959;
    font-size: 1.1rem;
    color: white;
    margin-bottom: 20%;
  }
</style>
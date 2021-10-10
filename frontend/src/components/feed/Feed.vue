<template>
  <div id="feedContainer">
    <div
      id="feedHeader"
    >
      <div class="feed-profile-image"></div>
      <div class="feed-info">
        <div>아이디: 12321</div>
        <div>게시일: 12.12.12</div>
      </div>
      <div
        class="btn-group"
      >
        <button id="moreInfo">더보기</button>
        <button id="followBtn">팔로우</button>
      </div>
    </div>
    <div
      id="feedBody"
    >
      <div id="photo">
        <div class="photo-1 square"></div>
        <div id="photo2ndarea">
          <div class="photo-2" id="photo21"></div>
          <div class="photo-2" id="photo22"></div>
        </div>
      </div>
      <div id="description"><p>oremque eius incidunt esse perferendis ullam nostrum nihil ab laudantium.</p>
      </div>
      <div id="item-list">
        <div id="btnList">
          <div class="d-flex justify-content-between" style="margin-left:5%">
            <div class="img-container"><img :src="likeBtn" alt="" class="image"></div>
            <div class="img-container"><img :src="commentBtn" alt="" class="image"></div>
          </div>
          <div class="img-container" style="margin-right:5%"><img :src="otherBtn" alt="" class="image" ></div>
        </div>
        <div id="likeComment">
          <p>??명이 좋아합니다.</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import likeBtn from '@/assets/images/좋아요.png'
import commentBtn from '@/assets/images/댓글.png'
import otherBtn from '@/assets/images/하단버튼.png'

export default {
  name : 'Feed',
  data() {
    return {
      likeBtn : likeBtn,
      commentBtn : commentBtn,
      otherBtn : otherBtn,
    }
  }
}
</script>

<style scoped>
  #feedContainer {
    display: grid;
    grid-template-rows: 1fr 3fr;
    gap: 5%;
  }
  #feedHeader{
    display: grid;
    grid-template-columns: 1fr 5fr 1fr;
    /* width: 90%;
    height: 100%; */
  }
  .feed-profile-image {
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #bbb;
    border-radius: 50%;
    /* width : 100%;
    height: 100%; */
    margin-right: 10%;
  }
  .feed-info{
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
    margin-left: 10%;
    font-size: 2rem;
  }
  .btn-group {
    display: flex;
    flex-direction: column;
    align-items: flex-end;
    justify-content: space-evenly;
    font-size: 1rem;
  }
  #followBtn{
    background-color: #365959;
    color: white;
    width: 100%;
    border-radius: 10%;
  }
  #moreInfo{
    background-color: #1761ff;
    color: white;
    width: 100%;
    border-radius: 10%;
  }
  #feedBody{
    display: grid;
    grid-template-rows: 6fr 1fr 1fr;
    gap :3%;
  }
  #photo {
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-template-rows: 1fr;
    gap: 3%;
    margin-bottom: 3%;
  }
  .square {
    aspect-ratio: 1;
    background-color: lightsalmon;
    border-radius: 12%;
  }
  #photo2ndarea{
    display: grid;
    grid-template-rows: 1fr 1fr;
    gap: 3%;
    margin-left: 3%;
  }
  .photo-2 {
    aspect-ratio: auto;
    border-radius: 12%;
  }
  #photo21{
    background-color: yellow;
  }
  #photo22{
    background-color: lightgrey;
  }
  #description {
    background-color: lightpink;
    font-size: 1rem;
  }
  #item-list{
    display: grid;
    grid-template-rows: 1fr 1fr;
    
  }
  #btnList{
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: lightseagreen;
  }
  .btn {
    width: auto;
    height: auto;
  }
  .img-container {
    display: flex;
    justify-content: center;
    align-items: center;
    overflow: hidden;
  }
  .image {
    width: inherit;
    height: inherit;
  }
  #likeComment{
    background-color: blanchedalmond;

  }
</style>
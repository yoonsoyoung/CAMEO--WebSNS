<template>
  <div id="subNav">
    <div class="subNav-box">
      <div class="back-btn">
        <button @click="clickGoBack"><i class="back-icon fas fa-chevron-left"></i></button>
      </div>
      <div class="page-title-bg">
        <span id="pageTitle">{{this.title}}</span>
      </div>
    </div>
    <div class="subNav-bottom">
      <hr class="subNav-bottom-hr">
    </div>
  </div>
</template>

<script>

export default {
  name : 'SubNav',
  props:{
    title : String,
  },
  data() {
    return {
    }
  },
  methods :{
    clickGoBack(){
      window.history.back();
    }
  }
}
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Fredoka+One&family=Lobster&family=Jua&family=Noto+Sans+KR:wght@300;400&display=swap");
* {
  font-family: "Noto Sans KR";
}
a {
	text-decoration: none;
	color: black;
}
ul {
	padding: 0;
	margin: 0;
}
li {
	list-style: none;
}
input {
    border: 0;
    background: none;
}
input:focus {
    outline: none;
}
button {
    border: none;
    background: none;
}
#subNav{
  display: block;
  font-size: 25px;
  z-index: 3;
  position: fixed;
  top: 80px;
  left: 0;
  width: 100%;
  height: 80px;
  margin: 0 auto;
}
.subNav-box {
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  align-content: center;
  justify-content: space-between;
  align-items: center;
  padding: 20px 50px 15px;
  background-color: white;
}
.page-title-bg {
  margin: 0 auto;
}
.subNav-bottom-hr{
  width: 90%;
  border: 1px solid #C6C6C6;
  margin: 0
}
.subNav-bottom {
  display: flex;
  width: 100%;
  align-content: center;
  justify-content: center;
  align-items: center;
}
#pageTitle {
  font-size: 25px;
  font-weight: 600;
}
.back-icon {
  font-size: 20px;
}
/* 모바일 화면에서 */
@media (max-width: 500px) {
  .subNav-box {
    padding: 5px 50px 5px;
  }
}
</style>
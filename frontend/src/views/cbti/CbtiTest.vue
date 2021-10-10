<template>
  <div id="mainRoot">
    <div class="wrap">
      <div class="main-box">
          <div class="main-title">
            <span class="main-title-sub-top">나의 Cafe MBTI는 뭘까?</span>
            <p class="main-title-text">{{this.title}}</p>
          </div>
      </div>
      <cbti-main
        v-if="menu==='main'"
        @menuCheck="getMenu" 
      />
      <cbti-question
        v-else-if="menu==='qna'"
        @resCbti="getResult"
      />
      <cbti-result 
        v-else-if="menu==='result'"
        @menuCheck="getMenu"
        :cbti=this.cbti
      />
    </div>
    <go-topbtn />
  </div>
</template>

<script>
import CbtiMain from '@/components/cbti/CbtiMain.vue'
import CbtiQuestion from '@/components/cbti/CbtiQuestionForm.vue'
import CbtiResult from '@/components/cbti/CbtiResultForm.vue'
import GoTopbtn from '@/components/header/GoTopBtn.vue'

export default {
  components: {
    CbtiMain,
    CbtiQuestion,
    CbtiResult,
    GoTopbtn,
  },
  methods: {
    getMenu(item) {
      this.menu = item;
    },
    getTitle(item) {
      this.title = item;
    },
    // question 에서 넘어올 때
    getResult({menu, res}) {
      this.menu = menu;
      this.cbti = res;
      if(menu === 'result') this.title = 'CBTI 결과';
    }
  },
  data() {
    return {
      menu: 'main',
      title: 'CBTI',
      cbti: '',
    }
  },
}
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Fredoka+One&family=Lobster&family=Poor+Story&family=Jua&family=Noto+Sans+KR:wght@300;400&display=swap");
* {
    font-family: "Noto Sans KR";
    font-weight: 400;
}
a {
	text-decoration: none;
	color: black;
}
#mainRoot {
    width: 100%;
    height: 100%;
    background-color: #365959;
}
.wrap {
    position: relative;
    top: 100px;
    width: 100%;
    display: flex;
    flex-direction: column;
    align-content: center;
    justify-content: center;
    align-items: center;
    background-color: white;
    border-radius: 10%;
}
.main-box {
    width: 100%;
    min-height: 200px;
    text-align: center;
    display: flex;
    flex-direction: row;
    align-content: center;
    justify-content: center;
    align-items: center;
}
.main-title-sub-top {
    font-size: 1.3em;
}
.main-title-text {
    font-family: 'Jua';
    font-size: 3.5em;
    font-weight: 500;
    margin: 0;
    line-height: 1.0em;
}
@media (max-width: 479px) {
  .main-box {
        min-height: 100px;
    }
}
</style>
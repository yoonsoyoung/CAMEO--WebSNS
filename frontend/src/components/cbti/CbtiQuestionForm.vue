<template>
  <div id="qnaForm">
    <div class="list-form" v-for="(question, index) in list"
          :key="index">
      <div class="question-box">
        <div class="question-card col-md-9"
            v-show="index === step"
        >
          <span>{{question.q}}</span>
        </div>
      </div>
      <div class="answer-box" v-show="index === step">
        <div class="answer-list"
          v-for="(item, index) in question.a"
          :key="index"
        >
        <button class="answer-item col-md-6" @click="checkAns(item.type)">{{item.answer}}</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import data from '../../js/CbtiData.js'
export default {
  name: 'CbtiQuestion',
  methods: {
    // 결과 페이지 이동
    goResult() {
      this.$emit('resCbti', {menu: 'result', res: this.resultCbti});
    },
    // 다음 항목이 보이도록
    next() {
      this.step++;
    },
    // 선택한 답변에 대한 타입 카운팅 및 마지막 문항인지 체크
    checkAns(idx) {
      this.select[idx]++;
      if(this.step === 11) {
        this.calResult();
      } else {
        this.next();
      }
    },
    // 두 타입 중 더 많이 택한 쪽 비교
    compResult(a, b) {
      var type = ['C', 'N', 'H', 'I', 'T', 'V', 'S', 'U'];
      if(this.select[a] > this.select[b])
        return type[a];
      else
        return type[b];
    },
    // 최종 선택된 타입
    calResult() {
      var sel = this.select;
      this.resultCbti += this.compResult(0, 1) + this.compResult(2, 3) + this.compResult(4, 5) + this.compResult(6, 7);
      console.log(this.resultCbti);
      this.goResult();
    },
  },
  data() {
    return {
      list: data.qnaList,
      idx: 0,
      result: [''],
      selectAns: Number,
      select: [0, 0, 0, 0, 0, 0, 0, 0],
      step: 0,
      resultCbti: '',
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
#qnaForm {
  width: 100%;
  /* display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  align-content: center;
  justify-content: center;
  align-items: center; */
  text-align: center;
}
.list-form {
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  align-content: center;
  justify-content: center;
  align-items: center;
}
.question-box {
  width: 70%;
}
.question-card {
  border: 1px solid #ececec;
  border-radius: 7px;
  min-height: 250px;
  margin: 0 auto;
  /* width: 90%; */
  font-size: 1.2em;
  font-family: 'Poor Story';
  font-weight: 600;
  display: flex;
  flex-direction: column;
  align-content: center;
  align-items: center;
  justify-content: center;
}
.answer-box {
  width: 100%;
  min-height: 300px;
  margin: 20px 0;
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  align-content: center;
  justify-content: center;
  align-items: center;
}
.answer-list {
  width: 100%;
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  align-content: center;
  justify-content: center;
  align-items: center;
}
.answer-item {
  /* width: 50%; */
  margin: 20px auto;
  min-height: 70px;
  border: 0;
  border-radius: 50px;
  font-size: 1.1em;
  font-family: 'Poor Story';
  background-color: #ececec;
  padding: 10px;
}
.answer-list > .answer-item:hover, .answer-item:focus {
  background-color: #365959;
  color: white;
}

/* 애니메이션 */
@keyframes fadeIn {
  from { opacity: 0; }
  to { opacity: 1; }
}
@keyframes fadeOut {
  from { opacity: 1; }
  to { opacity: 0; }
}
@-webkit-keyframes fadeIn {
  from {opacity: 0;}
  to {opacity: 1;}
}
@-webkit-keyframes fadeOut {
  from {opacity: 1;}
  to {opacity: 0;}
}
.fadeIn {
  animation: fadeIn;
  animation-duration: 0.5s;
}
.fadeOut {
  animation: fadeOut;
  animation-duration: 0.5s;
}
</style>
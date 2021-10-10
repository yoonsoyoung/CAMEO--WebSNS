<template>
<div class="qna-row-container">
  <div class="row row-q">
    <div style="text-align:center;">Q</div>
    <div style="text-align:left;">{{title}}</div>
    <div style="text-align:center;" @click="onShowAnswer">></div>
  </div>
  <div class="row row-a" v-show="showAnswer">
    <div style="text-align:center;">A</div>
    <div style="text-align:left;">{{answer}}</div>
  </div>
</div>
</template>

<script>
export default {
  name : 'QnARow',
  props : {
    title : String,
    answer : String,
  },
  data() {
    return {
      showAnswer : false,

    }
  },
  methods : {
    onShowAnswer() {
      this.showAnswer = !this.showAnswer
      return this.showAnswer
    }
  }
  
}
</script>

<style scoped>
  td {
    font-size: 2rem;
  }
  tr {
    margin-bottom: 10%;
  }
  .qna-row-container{
    position: relative;
  }
  .row {
    display: grid;
    width: 1000px;
    grid-template-columns: 1fr 3fr 1fr;
    gap: 1%;
    height: 75px;
    align-items: center;
    font-size: 1rem;
  }
  .row-q{
    background: lightgoldenrodyellow;
  }
  .row-a {
    background: lightcoral;
  }
</style>
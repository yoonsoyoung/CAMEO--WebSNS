<template>
  <div class="warp">
    <SubNav
      :title="'자주 묻는 질문들'"
    />
    <div id="QnANav">
      <div class="menu" @click="onSelect(login)">login</div>
      <div class="menu" @click="onSelect(signup)">signup</div>
      <div class="menu" @click="onSelect(member)">member</div>
      <div class="menu" @click="onSelect(alarm)">alarm</div>
      <div class="menu" @click="onSelect(cafe)">cafe</div>
    </div>
    <div id="QnATable" class="accordion">
      <div
        v-for="(row, idx) in list"
        :key="idx"
      >
        <div class="contentBx" >
          <div class="label" @click="showAnswer(idx)">{{row.title}}</div>
          <div class="content"><p>{{row.answer}}</p></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>


import SubNav from '@/components/header/SubNav.vue'

export default {
  name : 'QnA',

  data() {
    return{
      list : [{title : '알림 차단 방법은?' , answer : '알림 차단을 눌러주세요'}, {title : '알림 끄는 방법은?', answer : '알림 끄기를 눌러주세요' }],
      login : [{title : '아이디와 비밀번호 수정하는 방법은?' , answer : '정보 수정을 눌러주세요'}, {title : '회원정보를 볼 수 있는 방법은?', answer : '정보 보기를 눌러주세요'}],
      signup : [{title : '아이디의 조건은?', answer : '없습니다.'}, {title : '회원정보 보안 규정은?' , answer : '없습니다.'}],
      member : [{title : '회원 수는?' , answer : '10000명 입니다.'}, {title : '탈퇴회원 규정은?', answer : '없습니다.'}],
      alarm : [{title : '알림 차단 방법은?', answer : '알림 차단을 눌러주세요'}, {title : '알림 끄는 방법은?', answer : '알림 끄기를 눌러주세요'}],
      cafe : [{title : '존재하는 카페의 수는?', answer : '10000개가 있습니다.'}, {title : '카페의 정보 업데이트는?', answer : '유저가 스스로 업데이트 합니다.'}],
      onselect : '',
    }
  },
  components: {
    // QnATable,
    SubNav,
  },
  methods : {
    
    onSelect(name){
      const menus = document.getElementsByClassName("menu")
      for (let i = 0; i< menus.length; i++){

        if (name === menus[i].outerText){
          menus[i].classList.toggle('active')}
      }
      this.list = name
    },
    showAnswer(idx) {
      const accordion = document.getElementsByClassName("contentBx")
      for (let i = 0; i< accordion.length; i++){
        if (i === idx){accordion[i].classList.toggle('active')}
      }
    },

  }
}
</script>

<style scoped>
  .warp{
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
  }
  #QnANav{
    display: flex;
    justify-content: space-around;
    align-items:center;
    margin: 15% 10% 2%;
  }
  .menu{
    background-color: #365959;
    width: 20%;
    font-weight: bold;
    text-align: center;
    height: 100%;
  }
  .menu.active{
    background-color: red;

  }
  #QnATable{
    margin: 0 10% 15%;
  }

  .accordion .contentBx{
    position:relative;
  }
  .accordion .contentBx .label{
    position: relative;
    padding: 2%;
    
    color: #fff;
    cursor:pointer;
  }
  .accordion .contentBx .label::before{
    content: '+';
    position: absolute;
    top: 50%;
    right: 20px;
    transform: translateY(-50%);
    font-size: 2rem;
  }
  .accordion .contentBx.active .label::before{
    content: '-';
  }
  .accordion .contentBx .content {
    position: relative;
    background: #fff;
    text-align: left;
    height: 0;
    overflow: hidden;
    transition: 0.3s;
    overflow-y: hidden;
  }
  .accordion .contentBx.active .content {
      height: 100px;
      padding: 2%;
  }
</style>
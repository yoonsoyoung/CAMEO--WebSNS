<template>
  <div id="infoModalRoot" v-if="this.modalActive" >
    <div class="modal-box p-3 shadow">
      <div class="btn-box">
        <button class="close-btn" @click="closeBtn"><i class="close-icon fas fa-times"></i></button>
      </div>
      <div class="info-box">
        <div class="info-desc-top">
          <span  class="info-desc-name">카페 지점명</span>
        </div>
        <div class="info-desc-middle">
          <span class="info-desc-addr">카페 주소</span>
          <a href="" class="detail-info-btn">상세보기</a>
        </div>
        <hr class="info-desc-hr">
        <div class="info-desc-bottom">
          <span class="info-desc-signif">무료 와이파이 | 콘센트 많음 | 주차장 </span>
          <span class="info-desc-cbti">많이 찾은 유형 | CHTS </span>
        </div>
      </div>
      <div class="pic-box">

      </div>
    </div>
  </div>
</template>

<script>
export default {
    name: 'InfoModal',
    props: {
        mapData : String,
        modalActive: Boolean,
    },
    methods: {
        closeBtn() {
            this.$emit('modalfalse',false)
            // this.modalActive = false;
        },
    },
    data() {
        return {
            // isActive: true,
        }
    },
}
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Fredoka+One&family=Lobster&family=Jua&family=Noto+Sans+KR:wght@300;400&display=swap");
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
#infoModalRoot {
    width: 400px;
    height: 500px;
}
.modal-box {
    width: 100%;
    height: 100%;
    /* background-color: rgba(255, 255, 255, 0.8); */
    background-color: white;
    border-radius: 10px;
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    align-content: center;
    justify-content: flex-start;
}
.btn-box {
    text-align: right;
}
.info-box {
    height: 100%;
}
.info-desc-top {
}
.info-desc-top > .info-desc-name {
    font-size: 1.8em;
    font-weight: 600;
}
.info-desc-middle {
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    align-content: center;
    justify-content: space-between;
    align-items: center;
}
.info-desc-middle > .info-desc-addr{
    font-size: 1.3em;
}
.info-desc-middle > .detail-info-btn{
    border-radius: 5px;
    background-color: #365959;
    width: 70px;
    line-height: 2.0em;
    text-align: center;
    color: white;
    font-size: 0.8em;
    font-weight: 400;
}
.info-desc-hr {
    margin-bottom: 5px;
}
.info-desc-bottom {
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    align-content: flex-start;
    align-items: flex-start;
}
.info-desc-bottom > .info-desc-signif{

}
.info-desc-bottom > .info-desc-cbti{

}
@media (max-width: 479PX) {
    #infoModalRoot {
        width: 100%;
    }
}
</style>
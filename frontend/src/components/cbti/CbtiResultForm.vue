<template>
  <div id="resultForm">
    <div class="title-text mt-3 col-7 mx-auto">
      <p class="result-cbti">[ {{this.resultinfo.cbti}} ]</p>
      <p class="result-title">{{this.resultinfo.title}}</p>
    </div>
    <div class="img-box col-md-5 mt-3 mb-4 mx-auto">
      <div id="resultImg">
        <img :src="require(`@/assets/images/CBTI/${cbti}.png`)" alt="결과 캐릭터" class="cbti-img">
      </div>
    </div>
    <div class="result-desc-box mt-3 col-md-7 mb-4 mx-auto">
      <div class="result-desc">
        <span class="result-desc-text" v-html="resultinfo.desc">{{this.resultinfo.desc}}</span>
      </div>
      <hr>
      <div class="result-recommend mt-3">
        <span class="recommend-title">이런 메뉴는 어때요?</span>
        <span class="recommend-list p-3">{{this.resultinfo.recommend}}</span>
      </div>
      <div class="btn-box my-4">
        <button class="btn share-btn"><i class="fas fa-share-alt"></i></button>
        <button class="btn save-cbti-btn" @click="saveMyCbti">내 CBTI 저장</button>
      </div>
      <div class="confirm-modal"></div>
    </div>
  </div>
</template>

<script>
import data from '../../js/CbtiData.js'
import http from '../../util/http-common'
export default {
  name: 'CbtiResult',
  props: [
    "cbti",
  ],
  methods: {
    resultInfo(cbti) {
      for (let i = 0; i < this.resultList.length; i++) {
        if(this.resultList[i].cbti === cbti) {
          this.resultinfo = this.resultList[i];
          break;
        }
      }
    },
    saveMyCbti() {
      var useremail = localStorage.getItem("user_email");
      var info = {
        "user_cbti" : this.cbti,
        "user_email" : useremail,
      };
      // console.log(info);
      http.post('/user/save/cbti', info)
          .then((res) => {
            alert("저장이 완료되었습니다.");
          })
          .catch(() => {
            alert("저장에 오류가 발생했습니다.");
          });
    }
  },
  mounted() {
    this.resultInfo(this.mycbti);
  },
  created() {
  },
  data() {
    return {
      resultList: data.infoList,
      resultinfo: {
        cbti: {type: String},
        title: {type: String},
        desc: {type: String},
        recommend: {type: String},
      },
      mycbti: this.cbti,
    }
  },
}
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Fredoka+One&family=Lobster&family=Poor+Story&family=Jua&family=Noto+Sans+KR:wght@300;400&display=swap");
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
#resultForm {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  align-content: center;
  justify-content: center;
  align-items: center;
}
.title-text {
  text-align: center;
}
.result-cbti {
  font-size: 1.6em;
  font-family: 'Jua';
  color: #365959;
  margin: 0;
}
.result-title {
  font-size: 1.4em;
  font-weight: 600;
  font-family: 'Poor Story';
}
.img-box {
  /* width: 400px; */
  height: 350px;
  background-color: #f1f1f1;
  border: 1px solid #ececec;
  border-radius: 10px;
  margin: 0 auto;
  text-align: center;
}
#resultImg {
  width: 100%;
  height: 100%;
}
.cbti-img {
  width: 80%;
  height: 100%;
}
.result-desc-box {
  /* width : 70%; */
  /* min-height: 300px; */
}
.result-desc {
  /* width: 100%; */
  min-height: 300px;
  text-align: center;
  margin: 0 auto;
  padding: 20px;
  font-family: 'Poor Story';
  font-size: 1.3em;
}
.desc-keyword {
  text-align: center;
  font-size: 1.1em;
}
.result-recommend {
  text-align: center;
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  align-content: center;
  justify-content: center;
  align-items: center;
}
.recommend-title {
  width: 200px;
  height: 40px;
  background-color: #a6774e;
  border-radius: 50px;
  color: white;
  font-family: 'Poor Story';
  font-size: 1.2em;
  line-height: 2.2em;
}
.recommend-list {
  font-size: 1.1em;
}
.btn-box{
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  align-content: center;
  justify-content: center;
  align-items: center;
}
.save-cbti-btn, .share-btn {
  background-color: #d9c6b0;
  border-radius: 5px;
}
.share-btn {
  margin-right: 15px;
}
/* 모바일 */
@media (max-width: 479px) {
  .img-box {
    width: 300px;
  }
  .cbti-img {
    width: 100%;
  }
}
</style>
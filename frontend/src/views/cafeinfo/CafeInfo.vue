<template>
  <div id="CafeInfoRoot">
    <div class="wrap">
      <div id="mapWrap">
        <map-item 
          :mapData="mapData"

        />
      </div>
      <div id="openSearchWrap" class="open-btn-box hide">
          <button class="open-btn" @click="openSearch"><i class="open-search-icon fas fa-search"></i></button>
      </div>
      <div id="listWrap" class="">
        <div class="search-wrap">
          <div class="search-box">
            <div class="close-btn-box">
                <button class="close-btn" @click="closeSearch"><i class="close-icon fas fa-times"></i></button>
            </div>
            <div class="select-group mt-2 mb-3">
              <select name="city" id="citySelect" v-model="searchLocation.city" @change="selectCity">
                  <option value="" disabled selected hidden class="option-item-title">시</option>
                  <option v-for="(c, index) in cityList" :key="index" v-bind:value="c" class="option-item">{{ c }}</option>
              </select>
              <select name="gugun" id="gugunSelect" v-model="searchLocation.gugun" @change="selectGugun">
                  <option value="" disabled selected hidden>구,군</option>
                  <option v-for="(g, index) in gugunList" :key="index" v-bind:value="g">{{ g }}</option>
              </select>
              <select name="dong" id="dongSelect" v-model="searchLocation.dong" @change="selectDong">
                  <option value="" disabled selected hidden>동</option>
                  <option v-for="(d, index) in dongList" :key="index" v-bind:value="d">{{ d }}</option>
              </select>
            </div>
            <div class="search-bg align-center">
              <i class="search-icon fas fa-search"></i>
              <input type="text" class="search-input" name="word" @input="search" v-model="word">
            </div>
          </div>
        </div>
        <div class="list-box">
          <div class="addr-box" v-show=this.isActive>
            <span class="pl-2">{{ searchLocation.city }} {{ searchLocation.gugun }} {{ searchLocation.dong }} 검색 결과</span>
            <hr>
          </div>
          <div v-if="this.cafeList.length > 0" class="list-item-wrap">
            <list-item
              v-for="(cafe, index) in filtered"
              :key="index"
              :cafeName="cafe.cafe_name"
              :cafeAddr="cafe.address"
              :cafeUrl="cafe.url"
              @giveAddress="moveMap"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from '@/util/http-common.js'

import ListItem from '@/components/cafeinfo/ListItem.vue'
import MapItem from '@/components/cafeinfo/MapItem.vue'
export default {
  name: 'CafeInfo',
  components: {
    ListItem,
    MapItem,
  },

  methods: {
    moveMap(address){
      this.mapData = address

    },
    search(e) {
      this.word=e.target.value
      if (this.word===""){this.showList = this.cafeList}
      else{
        let searchList = this.cafeList.filter((cafe)=>{
          if(cafe.cafe_name.includes(this.word)){
            return true
          }
        })
        this.showList = searchList
      }
      this.$set(this.showList)
      
    },
    selectCity() {
      // 현재 city 에 따라 gugun 데이터 가져와야 함
      this.gugunList = this.gugunData;
    },
    selectGugun() {
      // 현재 gugun에 따라 dong 데이터 가져와야 함
      if(this.searchLocation.gugun === '동구')
          this.dongList = this.dongData;
    },
    
    selectDong() {
      // dong까지 선택되면 현재 searchLocation 정보 넘겨서 리스트 받아옴
      this.cafeList = this.cafeSampleData;
      this.isActive = true;
    },
    closeSearch() {
      var searchWrap = document.querySelector("#listWrap");
      searchWrap.classList.remove("list-wrap-open");
      searchWrap.classList.add("list-wrap-close");
      setTimeout(() => {
          searchWrap.classList.add("hide");
      }, 500);

      var openBtn = document.querySelector("#openSearchWrap");
      openBtn.classList.remove("hide");
    },
    openSearch() {
      var searchWrap = document.querySelector("#listWrap");
      searchWrap.classList.remove("list-wrap-close");
      searchWrap.classList.remove("hide");
      searchWrap.classList.add("list-wrap-open");

      var openBtn = document.querySelector("#openSearchWrap");
      openBtn.classList.add("hide");
    },
  },
  created(){
    http.get('/cafe/getAllCafe')
    .then((res)=>{
      this.cafeList = res.data.cafeList
      this.showList = this.cafeList
    })
  },
  computed: {
    filtered: function () {
      // return this.cafeList.filter((cafe)=>{if(cafe.address.indexOf(searchLocation.gugun)> -1)}).filter((cafe)=>{if (item.cafe_name.indexOf(cname) > -1) {return true})
      // var cname = this.word.trim();
      // return this.cafeList.filter(function (item) {
      //   if (item.cafe_name.indexOf(cname) > -1) {
      //       return true;
      //   }
      // });
      let ans = []
      return this.cafeList.filter((cafe)=>{if(cafe.address.includes(this.searchLocation.gugun)) return true})
    },
  },
  data() {
    return {
      cityList: [
        "서울특별시",
        "부산광역시",
        "대구광역시",
        "인천광역시",
        "광주광역시",
        "대전광역시",
        "울산광역시",
        "세종특별자치시",
        "경기도",
        "강원도",
        "충청북도",
        "충청남도",
        "전라북도",
        "전라남도",
        "경상북도",
        "경상남도",
        "제주특별자치시도",
      ],
      gugunList: [],
      gugunData: [
        "광산구",
        "남구",
        "동구",
        "북구",
        "서구",
      ],
      dongList: [],
      dongData: [
        "동명동",
        "서남동",
        "지산1동"
      ],
      searchLocation : {
        city: "",
        gugun: "",
        dong: "",
      },
      cafeList: [],
      cafeSampleData: [
      ],
      word: "",
      // 시,군구,동 모두 입력 시 전체 검색 주소 보여짐
      isActive: false,
      mapData : '',
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
#CafeInfoRoot {
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
}
.wrap {
    width: 100%;
    min-height: 800px;
    position: relative;
    top: 50px;
    left: 0;
    display: contents;
}
#listWrap {
    z-index: 10;
    width: 400px;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    background-color: rgba(255, 255, 255, 0.8);
    overflow: hidden;
}
.list-wrap-close {
    /* 슬라이드 애니메이션 */
    animation-duration: 0.5s;
    animation-name: slide-close;
    -webkit-animation: 0.5s;
    -webkit-animation-name: slide-close;
}
.list-wrap-open {
    animation-duration: 0.5s;
    animation-name: slide-open;
    -webkit-animation: 0.5s;
    -webkit-animation-name: slide-open;
}
.hide {
    display: none;
}
.search-wrap {
    width: 100%;
    height: 260px;
    background-color: #365959;
}
.close-btn-box {
    text-align: right;
    width: 90%;
}
.close-btn {
    color: white;
}
#openSearchWrap {
    box-shadow: 2px 2px 5px rgb(0 0 0 / 50%);
}
.open-btn-box {
    z-index: 40;
    position: absolute;
    top: 100px;
    left: 20px;
    border-radius: 10px;
    background-color: #365959;
    width: 40px;
    height: 40px;
    text-align: center;
    line-height: 35px;
}
.open-btn {
    color: white;
    font-size: 1.4em;
}
.search-box {
    position: relative;
    top: 90px;
    left: 0;
    margin: 0 auto;
    text-align: center;
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    align-content: center;
    justify-content: center;
    align-items: center;
}
.search-box > .select-group {
    min-height: 70px;
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    align-content: center;
    justify-content: center;
    align-items: center;
}
.select-group > select {
    width: 100px;
    height: 35px;
    margin: 0 4px;
    font-size: 0.8em;
    font-weight: 600;
    color: white;
    border: none;
    border-bottom: 1px solid white;
    background-color: rgba(255, 255, 255, 0);
    /* select 기본 css 제거 */
    /* -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none; */
}
.select-group > select::-ms-expand {
    display: none;
}
.select-group > select:active, select:visited, select::selection {
    outline: none;
}
.select-group > select > option {
    color: black;
    height: 100px;
}
.option-item-title {
    padding: 0 20px;
}
.search-bg {
    width: 250px;
    border-radius: 100px;
    background-color: #ececec;
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    align-content: center;
    justify-content: flex-start;
    align-items: center;
}
.search-bg > .search-input {
    width: 80%;
}
.search-icon, .search-input {
    position: relative;
}
.search-icon {
    left: 15px;
    color: #989898;
    font-size: 15px;
    margin-right: 20px;
}
.addr-box {
    padding: 10px;
}
.addr-box > span {
    font-size: 1.1em;
    margin: 0 20px;
}
.addr-box > hr {
    margin: 5px auto;
}
#mapWrap {
    width: 100%;
    height: 100%;
    z-index: 2;
}
.list-box {
    height: 90%;
}
.list-item-wrap {
    height: 70%;
    overflow: auto;
}
.list-item-wrap::-webkit-scrollbar {
    width: 6px;
    height: 6px;
}
.list-item-wrap::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3); 
    -webkit-border-radius: 6px;
    border-radius: 6px;
}
.list-item-wrap::-webkit-scrollbar-thumb {
    -webkit-border-radius: 6px;
    border-radius: 6px;
    background: rgba(207, 207, 207, 0.8); 
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.5); 
}
.list-item-wrap::-webkit-scrollbar-thumb:window-inactive {
    background: rgba(153, 153, 153, 0.4); 
}
/* 모바일 */
@media (max-width: 479px) {
    #listWrap {
        width: 340px;
    }
}
/* 애니메이션 */
@keyframes slide-open {
    from {
        margin-left: -400px;
    }
    to {
        margin-right: 0;
    }
}
@-webkit-keyframes slide-open {
    from {
        margin-left: -400px;
    }
    to {
        margin-right: 0;
    }
}
@keyframes slide-close {
    from {
        margin-left: 0px;
    }
    to {
        /* margin-right: -400px; */
        margin-left: -400px;
    }
}
@-webkit-keyframes slide-close {
    from {
        margin-left: 0px;
    }
    to {
        margin-left: -400px;
    }
}
</style>
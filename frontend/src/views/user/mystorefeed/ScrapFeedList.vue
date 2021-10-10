<template>
  <div id="scrapFeedRoot">
    <sub-nav 
        :title="'스크랩'"
    />
    <div class="feed-list-box">
      <div class="feed-list">
        <scrap-feed-row
          v-for="(list, index) in scrapList"
          :key="index"
          :feedTitle="list.pheed_title"
          :feedId="list.pheed_id"
          :userId="list.pheed_user_id"
          type="scrapFeed"
          @click="goUserLink"
        ></scrap-feed-row>
      </div>
    </div>
  </div>
</template>

<script>
import SubNav from '@/components/header/SubNav.vue'
import ScrapFeedRow from "@/components/listRow/MyStoreFeedRow.vue"
import http from '@/util/http-common.js'

export default {
    name: "ScrapFeedList",
    components: {
        ScrapFeedRow,
        SubNav,
    },
    data() {
      return {
        userId : localStorage.getItem("user_id"),
        userEmail : localStorage.getItem("user_email"),
        type: "scrapFeed",
        scrapList: [],
      }
    },
    methods: {
      goUserLink(e) {
        alert(e.target.userid + "님 프로필로 이동");
        this.$router.push(`/mypage/home/${this.userEmail}`)
      }
    },
    created() {
      http.get(`scrap/list/${this.userEmail}`)
      .then((res)=>{
        this.scrapList = res.data.list
      })
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
#scrapFeedRoot {
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
}
.feed-list-box {
    width: 100%;
    margin: 0 auto;
    position: relative;
    left: 0;
    top: 200px;
}
.feed-list {
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    align-content: flex-start;
    justify-content: center;
    align-items: center;
}
/* 모바일 환경 */
@media (max-width: 500px) {
    /* 유저 리스트 정렬 */
    .user-search-list {
        align-items: flex-start;
    }
}
</style>
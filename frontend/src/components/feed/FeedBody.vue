<template>
  <div id="feedBodyRoot">
    <div id="feedBodyWrap">
      <div class="feed-img-box">
        <div class="feed-img"
          v-for="(item, index) in feedList"
          :key="index"
        >
          <img @click="goToFeed(item.pheed.pheed_id)" :src="`https://i5c202.p.ssafy.io/cameo/newspheed/getPheedPic/${item.pheed.pheed_id}/${item.pheedPicList[0]}`" alt="피드사진">
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from '@/util/http-common.js'

export default {
  name: 'FeedBody',
  props: {
    user_email : String,
  },
  created(){
    http.get(`/newspheed/getMyPheedList/${this.user_email}`)
    .then((res)=>{
      this.feedList=res.data.pheedList
      console.log(this.feedList)
    })
  },
  data() {
    return {
      feedList: [],
    }
  },
  methods: {
    goToFeed(n){
      this.$router.push(`/feed/${n}`)
    }
  }
}
</script>

<style scoped>
#feedBodyRoot {
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  align-content: center;
  align-items: center;
  justify-content: flex-start;
}
#feedBodyWrap {
  width: 65%;
}
.feed-img-box {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  align-content: center;
  justify-content: center;
  align-items: flex-start;
}
.feed-img {
  width: 200px;
  height: 200px;
  background-color: #ececec;
  float: left;

  margin: 10px;
}

.feed-img >img{
  width:100%;
  height: 100%;
}
@media (max-width: 479px) {
    .feed-img {
        width: 100PX;
        height: 100px;
        float: left;
        margin: 5px;
    }
}
</style>
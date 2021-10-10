<template>
  <div class="warp">
    <SubNav
      :title="'Main Page'"
    />
    <div id="feedBtnContainer">
      <router-link to="/feed/upload" ><button id="feedCreateBtn">+</button></router-link>
    </div>
    <div id="feedContainer">
      <div
        v-for="(feed,idx) in feedList"
        :key="idx"
      > 
        <div v-if="feed.pheedPicList[0]" class="feedImgContainer">
          <div class="feed-img">
            <img  @click="goToFeed(feed.pheed.pheed_id)" onError="this.style.visibility='hidden';" class="feed-img" :src="`https://i5c202.p.ssafy.io/cameo/newspheed/getPheedPic/${feed.pheed.pheed_id}/${feed.pheedPicList[0]}`">
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Feed from '@/components/feed/Feed.vue'
import SubNav from '@/components/header/SubNav.vue'
import http from '@/util/http-common.js'

export default {
  name : 'MainFeed',
  data() {
    return {
      feedList : [],
    }
  },
  components : {
    SubNav,
  },
  created() {
    http.get('/newspheed/getAllPheed')
    .then((res)=>{
      console.log(res.data.pheedList)
      console.log(res.data.pheedList.length)
      this.feedList = res.data.pheedList
    })
  },
  methods: {
    goToFeed(feed_id){
      this.$router.push(`feed/${feed_id}`)
    }
  },
}
</script>

<style scoped>
  .warp{
    position:absolute;
    top: 0;
    left:0;
    width: 100%;
    height: 100%;
  }
  #feedBtnContainer{
    display: flex;
    /* width: 80%; */
    justify-content: flex-end;
    /* margin: 5% 10%; */
    margin-top: 15%;
    padding: 0 5%;

    /* align-items: center; */
  }
  #feedCreateBtn{
    border: none;
    background-color: #055C9D;
    border-radius: 10px;
    width: 100px;
    font-size: 1.5rem;
    color:white;
    font-weight: bolder;

  }
  #feedContainer{
    display: grid;
    grid-template-columns: repeat(4, 250px);
    justify-content:center;
    align-items: center;
    margin-top:3%;
    gap: 5%;
    /* padding: 3% 0 10%; */
    min-height: 0;
    min-width: 0;
    /* margin-bottom: 15%; */
  }
  .feedImgContainer{
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 250px;
    aspect-ratio: 1;
    /* background-color: lightgoldenrodyellow; */
    overflow: hidden;
  }  
  .feedImgContainer:hover{
    border-radius: 50%;
  }  
  .feed-img{
    width: 100%;
    height:100%;
  }
  .feed-img:hover{
    width: 120%;
    height:120%;
    cursor: pointer;
  }
  
</style>
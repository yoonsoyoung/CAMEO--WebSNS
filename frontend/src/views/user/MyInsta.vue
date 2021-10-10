<template>
  <div class="MyInstaRoot">
    <SubNav
      :title="'마이 페이지'"
      v-if=isMypage
    />
    <div class="wrap">
      <div
        id="MyPageHeaderContainer"
      >
        <MyPageHeader
          :user_email="user_email"
        />
      </div>
      <div id="FeedBodyContainer">
        <FeedBody 
          :user_email="user_email"
        />

      </div>
      <!-- <FeedMain
        id="feedMain"
      /> -->
      <!-- <Footer/> -->
    </div>
  </div>
</template>

<script>
// import FeedMain from '@/components/feed/FeedMain.vue'
import MyPageHeader from '@/components/feed/MyPageHeader.vue'
import SubNav from '@/components/header/SubNav.vue'
import FeedBody from '@/components/feed/FeedBody.vue'
// import Footer from '@/components/footer/Footer.vue'

export default {
  name : 'MyInsta',
  props :{
    user_email : String,
  },
  data () {
    return {
      loginUser: {
        email: localStorage.getItem("user_email")
      },
      isMypage: true,
    }
  },
  components : {
    SubNav,
    MyPageHeader,
    FeedBody,
  },
  methods : {
    userCheck() {
      if(this.loginUser.email === this.user_email)
        this.isMypage = true;
      else
        this.isMypage = false;
    },
  },
  created() {
    this.userCheck();
  },
}
</script>

<style scoped>
#MyInstaRoot {
  width: 100%;
  height: 100%;
  position: relative;
  top: 200px;
  left: 0;
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  justify-content: flex-start;
  align-content: center;
  align-items: center;
}
  .wrap{
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    align-content: center;
    justify-content: flex-start;
    align-items: center;
  }
  #MyPageHeaderContainer{
    position: relative;
    top: 200px;
    display: flex;
    justify-content: center;
    align-items: flex-start;
    align-content: center;
    width: inherit;
    height: 45vw;
    max-height: 100px;
  }
  #FeedBodyContainer {
    display: flex;
    flex-direction: column;
    flex-wrap: wrap;
    align-content: center;
    align-items: center;
    position: relative;
    top: 25vw;
  }
  @media (max-width: 479px) {
    #MyPageHeaderContainer {
      top: 150px;
    }
    #FeedBodyContainer {
      top: 40vw;
    }
  }
</style>
<template>
  <div class="warp">
    <router-view :key="$route.fullPath"></router-view>
    <SubNav :title="'회원정보 수정'"/>
    <UserInfoForm id="userInfoForm" />
  </div>
</template>

<script>
import SubNav from '@/components/header/SubNav.vue'
import UserInfoForm from '@/components/user/Form/UserInfoForm.vue'

export default {
  name : 'MyPage',
  components : {
    SubNav,
    UserInfoForm,
  },
  mounted() {
    
  },

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
  #userInfoForm {
   display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    align-content: center;
    justify-content: center;
    align-items: center;
    position: relative;
    top: 200px;
  }
</style>
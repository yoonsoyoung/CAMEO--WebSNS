<template>
  <div class="wrap">
    <div id="leftBg">
      <img :src="coffee_img" alt="coffee 이미지" id="coffeeImg">
      <div id="leftBar"></div>
    </div>
    <div id="rightBg">
      <div id="rightBar1"></div>
      <div id="rightBar2"></div>
    </div>
    <LoginForm id="loginForm"/> 
  </div>
</template>
<script>
import coffee_img from '@/assets/images/Join_Login_BG_coffee.png'
import LoginForm from '@/components/user/Form/LoginForm.vue' 
export default {
  name : 'LoginBg',
  components :{
    LoginForm,
  },
  data () {
    return {
      coffee_img : coffee_img,
    }
  },

}
</script>

<style scoped>
  .wrap{
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
  }
  #leftBg{
    position: absolute;
    width: 50%;
    height: 100%;
    /* overflow: hidden; */
  }
  #coffeeImg{
    position: absolute;
    top: -20%;
    left: -20%;
  }
  #leftBar{
    position: absolute;
    width: 469px;
    height: 63px;
    left: -270.97px;
    top: 357px;
    background: rgba(191, 170, 143, 0.8);
    border-radius: 50px;
    transform: rotate(20.41deg)
  }

  #loginForm{
    position: absolute;
    top: 45%;
    left: 50%;
    transform: translate(-50%, -50%);
    /* width: 35%; */
    margin: 20% 0;
    z-index: 2;
  }
  #rightBg{
    margin-left: 50%;
    position: absolute;
    width: 50%;
    height: 150%;
    overflow: hidden;
  }
  #rightBar1{
    position: absolute;
    margin-left: 75%;
    bottom: 20%;
    width: 469px;
    height: 63px;
    background: rgba(54, 89, 89, 0.8);
    border-radius: 50px;
    transform: rotate(-29.62deg);
  }
  #rightBar2{
    position: absolute;
    margin-left: 75%;
    bottom: 10%;
    width: 469px;
    height: 63px;
    background: rgba(220, 193, 158, 0.8);
    border-radius: 50px;
    transform: rotate(-29.62deg);
  }
  @media (max-width: 479px) {
    .wrap {
      overflow: hidden;
    }
    #leftBg {
      width: 135%;
    }
    #leftBar {
      display: none;
    }
  }
</style>
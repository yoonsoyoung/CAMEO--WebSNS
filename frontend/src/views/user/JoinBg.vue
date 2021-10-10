<template>
  <div class="warp">
    <div id="leftBackground">
      <img :src="coffee_img" alt="coffee 이미지" id="coffeeImg">
      <div id="leftBar"></div>
    </div>
    <div id="rightBackground">
      <div id="rightBar1"></div>
      <div id="rightBar2"></div>
    </div>
    <SignupForm id="signupForm"/>  
  </div>
</template>

<script>
import coffee_img from '@/assets/images/Join_Login_BG_coffee.png'
import SignupForm from '@/components/user/Form/SignupForm.vue' 
export default {
  name : 'JoinBg',
  components :{
    SignupForm,
  },
  data () {
    return {
      coffee_img : coffee_img,
    }
  },


}
</script>

<style scoped>
  .warp {
    position: absolute;
    width: 100%;
    height: 100%;
    top :0;
    left: 0;
  }
  #leftBackground{
    position: absolute;
    width: 50%;
    height: 100%;
    overflow: hidden;
    z-index: 1;
  }
  #coffeeImg{
    position: absolute;
    top: -20%;
    left: -20%;
    z-index: 1;
  }
  #leftBar{
    position: absolute;
    width: 469px;
    height: 63px;
    left: -270.97px;
    top: 357px;
    background: rgba(191, 170, 143, 0.8);
    border-radius: 50px;
    transform: rotate(20.41deg);
    z-index: 1;
  }
  #signupForm{
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 35%;
    margin: 20% 0;
    z-index: 2;
    
}
  #rightBackground{
    margin-left: 50%;
    position: absolute;
    width: 50%;
    height: 150%;
    overflow: hidden;
    z-index: 0;
  }
  #rightBar1{
    position: absolute;
    margin-left: 75%;
    bottom: 20%;
    width: 469px;
    height: 63px;
    /* right: -400px; */
    /* bottom: -800px; */
    background: rgba(54, 89, 89, 0.8);
    border-radius: 50px;
    transform: rotate(-29.62deg);
        z-index: 0;
  }
  #rightBar2{
    position: absolute;
    margin-left: 75%;
    bottom: 10%;
    width: 469px;
    height: 63px;
    /* right: -400px; */
    background: rgba(220, 193, 158, 0.8);
    border-radius: 50px;
    transform: rotate(-29.62deg);
        z-index: 0;
  }

</style>
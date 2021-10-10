<template>
  <div class="warp page-404">
      <div class="info">
        <div>
          <h1 id="title">Page Not Found.</h1>
          <p id="paragraph">We can't seem to find the <br> page you looking for.</p>
        </div>
        <li><router-link to="/">go to login</router-link></li>
        <li><router-link to="/myinsta">go to mypage</router-link></li>
      </div>
      <img :src="image" alt="" class="image">
  </div>
</template>

<script>
import image from '@/assets/images/404_empty_coffee.jpg'

export default {
  name : 'Page500',
  data () {
    return {
      image : image,
    }
  }
}
</script>

<style scoped>
  .warp{
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    width: 100%;
    height: 100%;
  }
    li {
    font-size: 1.5rem;
  }
  .page-404 {
    display: grid;
    grid-template-columns: 1fr 1fr;
    justify-items: center;
    align-items: center;
    margin: 0;
  }
  .info {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    width: 50%;
    height: 50%;
  }
  #title {
    font-size: 3rem;
  }
  #paragraph {
    font-size: 1.5rem;
    margin: 0;
  }
  .image{
    width: 50%;
    height: 50%;
  }
</style>
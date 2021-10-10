<template>
  <footer>
    <div id="footerCameo">
      <h1 class="footer-title" id="CAMEO">CAMEO</h1>
      <p class="footer-list" style="text-align:center;">'CAMEO'는 커피 커뮤니티 사이트로 자신만의 숨겨진 카페를 공유하거나 커피를 사랑하는 사람들이 모여 소통하는 공간입니다.</p>
    </div>
    <div id="footerSite">
      <h1 class="footer-title">Site Map</h1>
      <div>
        <li class="footer-list"><router-link to="/" class="site-list">login</router-link></li>
        <li class="footer-list"><router-link to="/main" class="site-list">Main</router-link></li>
        <li class="footer-list"><router-link to="/myinsta" class="site-list">마이페이지</router-link></li>
        <li class="footer-list"><router-link to="/mypage" class="site-list">내 정보</router-link></li>
        <li class="footer-list"><router-link to="/notice" class="site-list">공지사항</router-link></li>
      </div>
    </div>
    <div id="footerPeople">
      <h1 class="footer-title" id="footerTitle">About us</h1>
      <div class="footer-list">
        <li>BE: 고소영</li>
        <li>팀장: 김태헌</li>
        <li>BE: 박소현</li>
        <li>FE: 박준수</li>
        <li>FE: 윤소영</li>
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  name : 'Footer',
  data() {
    return {
      
    }
  },
  watch: {

  },
  methods : {
  },
}
</script>

<style scoped>
  footer {
    position: absolute;
    left: 0;
    right: 0;
    bottom: 0;
    display: grid;
    width: 100%;
    height: auto;
    background-color: rgba(166, 119, 78, 0.5);
    grid-template-columns: 1fr 1fr 1fr;
    gap: 10%;
  }
  .footer-title {
    font-size: 1rem;
    color: white;
    text-align: center;
  }
  .footer-list {
    font-size: 0.5rem;
    margin-bottom: 2%;
    text-align: center;
  }
  .site-list {
    color: rgb(0, 0, 0);
  }
  li {
    margin-bottom: 2%;
  }
  @media (max-width: 650px){
    footer{
      position: absolute;
      left: 0;
      right: 0;
      bottom: 0;
      width: 100%;
      height: auto;
      background-color: rgba(166, 119, 78, 0.5);
      display: flex;
      justify-content: center;
      gap: 0;
    } 
    .footer-list{
      margin: 0;
      display: none;
      font-size: 0.1rem;
    }
    #footerSite{
      display: none;
    }
    #footerPeople{
      display: none;
    }


  }
</style>
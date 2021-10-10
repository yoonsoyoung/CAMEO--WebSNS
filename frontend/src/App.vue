<template>
  <div id="app">
    <Header v-if="!$route.meta.hideHeader"/>
    <router-view></router-view>
  </div>
</template>
<script>
// import "./components/css/style.scss";
import Header from './components/header/Header.vue'

export default {
  name: "app",
  components: {
    Header,
  },
};
</script>

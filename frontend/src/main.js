import Vue from 'vue'
import BootstrapVue from 'bootstrap-vue'
import App from './App.vue'
// import VueRouter from 'vue-router';
import router from './routes'
import store from './vuex/store'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import '@/assets/css/font.css'

Vue.config.productionTip = false

// Vue.use(VueRouter)
Vue.use(BootstrapVue)

// const router = new VueRouter({
//     routes,
// });

new Vue({
    router,
    store,
    render: h => h(App),
    
}).$mount('#app');



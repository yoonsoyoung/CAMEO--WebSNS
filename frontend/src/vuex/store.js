import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import actions from './actions'
import mutations from './mutations'
import http from "@/util/http-common";
import createPersistedState from "vuex-persistedstate";
import { createInstance } from "@/api/index.js";


Vue.use(Vuex)

const state = {
    isUser: false,
    access_token : '',
    user_email: '',
    
    // 로그인
    isLogin: false,
    userInfo: null,
    store: [],
    
    // 모든 유저 정보
    allUsers: [],
    oneUser: {},
    
    // 유저 검색
    searchUsers: [],
}

export default new Vuex.Store({
    state,
    mutations,
    getters,
    actions,
    plugins: [createPersistedState()],
})
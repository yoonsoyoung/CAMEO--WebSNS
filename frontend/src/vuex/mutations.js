// mutation : state 변경 처리기 -> commit('함수명', '전달인자') 방식 호출, 동기방식
// setter
export default {
    // 로그인
    setIsLogined(state, isLogin) {
        state.isLogin = isLogin;
    },
    setUserInfo(state, userInfo) {
        state.isLogin = true,
            state.userInfo = userInfo;
    },
     // 모든 유저 정보
     setAllUsers(state, payload) {
        state.allUsers = payload;
    },
    setOneUser(state, payload) {
        state.oneUser = payload;
    },
    // 유저 검색
    setSearchUsers(state, payload) {
        state.searchUsers = payload;
    },
    
    setStore(state, payload) {
        state.sotre = payload;
    },
    
}
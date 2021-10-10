// getters : 컴포넌트의 계산된 속성(computed) 의 공통 속성으로 정의.
// this.$store.getters['경로명/함수명']; 사용
export default {
    // 로그인
    login(state) {
        return state.login;
    },
    userInfo(state) {
        return state.userInfo;
    },

    // 모든 유저 정보
    allUsers(state) {
        return state.allUsers;
    },
    oneUser(state) {
        return state.oneUser;
    },

    // 유저 검색
    searchUsers(state) {
        return state.searchUsers;
    },

    store(state) {
        return state.store;
    },
}
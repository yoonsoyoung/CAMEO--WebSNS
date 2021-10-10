// actions : mutation 실행 트리거, 비동기. dispatch('함수명', '전달인자') 호출, 콜백 함수
import jwt_decode from "jwt-decode";
import { findById } from '@/api/UserApi.js';
import http from '../util/http-common';

export default {
    // 로그인
    async GET_MEMBER_INFO({ commit }, token) {
        let decode = jwt_decode(token);
        await findById(
            decode.userid,
            (response) => {
              if (response.data.message === "success") {
                commit("setUserInfo", response.data.userInfo);
              } else {
                //console.log("유저 정보 없음!!");
              }
            },
            (error) => {
              console.log(error);
            }
          );
  },
  
  // 모든 유저 정보
  getAllUsers({ commit }) {
  },
  // 유저 검색
  getSearchUsers({ commit }, user_id) {
    http.get(user_id)
      .then(({ data }) => {
        commit("setSearchUsers", data);
      });
  }

}

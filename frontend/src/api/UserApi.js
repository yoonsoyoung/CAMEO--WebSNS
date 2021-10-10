/*
 User API 예시
 */
import { createInstance } from './index.js';
const instance = createInstance();

// const requestLogin = (data,callback,errorCallback) => {
//     //백앤드와 로그인 통신하는 부분
//     callback();

// }

function login (user, success, fail) {
    instance.defaults.headers["access-token"] = window.localStorage.getItem("access-token ");
    const member = {
        id: user.id,
        pwd: user.pwd
    };
    console.log(member.id); // 현재 액세스 토큰 받은 유저 아이디
    instance
        .post("user/account/login", member)
        .then(success)
        .catch(fail);
}

async function findById(user_email, success, fail) {
    instance.defaults.headers["access-token"] = window.localStorage.getItem(
        "access-token"
    );
    await instance
        .get(`uesr/info/${user_email}`)
        .then(success)
        .catch(fail);
}

// const UserApi = {
//     requestLogin:(data,callback,errorCallback)=>requestLogin(data,callback,errorCallback)
// }

export default { login, findById };


<template>
  <div class="" id="login">
    <div class="">
      <h1>
        나의 작고 소중한 카페를 함께 공유하는 공간
        <br />CAMEO
      </h1>

      <div class="">
        <input
          v-model="email"
          v-bind:class="{error : error.email, complete:!error.email&&email.length!==0}"
          @keyup.enter="Login"
          id="email"
          placeholder="이메일을 입력하세요."
          type="text"
        />
        <label for="email">이메일</label>
        <div class="error-text" v-if="error.email">{{error.email}}</div>
      </div>
      <div class="">
        <input
          v-model="password"
          type="password"
          v-bind:class="{error : error.password, complete:!error.password&&password.length!==0}"
          id="password"
          @keyup.enter="Login"
          placeholder="비밀번호를 입력하세요."
        />
        <label for="password">비밀번호</label>
        <div class="" v-if="error.password">{{error.password}}</div>
      </div>
      <button
        class=""
        @click="onLogin"
        :disabled="!isSubmit"
        :class="{disabled : !isSubmit}"
      >로그인</button>

      <div class="">
        <div class="">
          <p>SNS 간편 로그인</p>
          <div class=""></div>
        </div>

        <kakaoLogin :component="component" />
        <GoogleLogin :component="component" />
      </div>
      <div class="">
        <div class="">
          <p>혹시</p>
          <div class=""></div>
        </div>
        <div class="">
          <p>비밀번호를 잊으셨나요?</p>
        </div>
        <div class="">
          <p>아직 회원이 아니신가요?</p>
          <router-link to="/user/join" class="">가입하기</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "../../components/css/user.scss";
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import KakaoLogin from "../../components/user/snsLogin/Kakao.vue";
import GoogleLogin from "../../components/user/snsLogin/Google.vue";
import UserApi from "../../api/UserApi";

export default {
  components: {
    KakaoLogin,
    GoogleLogin
  },
  created() {
    this.component = this;

    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(100)
      .has()
      .digits()
      .has()
      .letters();
  },
  watch: {
    password: function(v) {
      this.checkForm();
    },
    email: function(v) {
      this.checkForm();
    }
  },
  methods: {
    checkForm() {
      if (this.email.length >= 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다.";
      else this.error.email = false;

      if (
        this.password.length >= 0 &&
        !this.passwordSchema.validate(this.password)
      )
        this.error.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
      else this.error.password = false;

      let isSubmit = true;
      Object.values(this.error).map(v => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },
    onLogin() {
      if (this.isSubmit) {
        let { email, password } = this;
        let data = {
          email,
          password
        };

        //요청 후에는 버튼 비활성화
        this.isSubmit = false;

        UserApi.requestLogin(
          data,
          res => {
            //통신을 통해 전달받은 값 콘솔에 출력
            //console.log(res);

            //요청이 끝나면 버튼 활성화
            this.isSubmit = true;

            this.$router.push("/main");
          },
          error => {
            //요청이 끝나면 버튼 활성화
            this.isSubmit = true;
          }
        );
      }
    }
  },
  data: () => {
    return {
      email: "",
      password: "",
      passwordSchema: new PV(),
      error: {
        email: false,
        passowrd: false
      },
      isSubmit: false,
      component: this
    };
  }
};
</script>



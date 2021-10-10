<template>
  <div id="userRow" @click="goUserLink">
    <div class="user-row-list">
      <div class="user-list-info">
        <div class="user-info-profile">
          <img src="../../assets/images/profile_default.png" alt="프로필 사진" class="user-profile-img">
        </div>
        <div class="user-info-desc">
          <div class="user-info-id">
            <span id="userId">{{userId}}</span>
          </div>
          <div class="user-desc-bottom">
            <div class="user-info-email">
              <span id="userEmail">@{{emailId}}</span>
            </div>
            <div class="user-info-cbti" v-if=cbti>
              <span id="userCbti">{{cbti}}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'UserRow',
  props: [
    "userId",
    "email",
    "cbti",
  ],
  methods: {
    goUserLink() {
      // alert(this.userId + "님 프로필로 이동");
      this.$router.push('/mypage/home/' + this.email);
    },
    
  },
  computed: {
  emailId() {
    // 받아온 이메일 중 @ 앞까지만
      const emailCheck = this.email.split('@');
      // console.log(emailCheck[0]);
      return emailCheck[0];
    }
  },
  data() {
    return {
    }
  },
}
</script>

<style scoped>
#userRow {
  width: 100%;
  display: block;
  float: left;
}
#userRow:hover {
  background-color: #EAEAEA;
}
.user-row-list {
  width: 100%;
}
.user-list-info {
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    align-content: center;
    justify-content: flex-start;
    align-items: center;
    margin: 20px;
}
.user-info-profile {
  width: 55px;
  height: 55px;
  border-radius: 100%;
  background: #888;
  margin-right: 10px;
}
.user-info-profile > .user-profile-img {
  /* width: 100%; */
  height: 100%;
}
.user-info-desc > div > span{
  font-size: 20px;
  /* font-weight: 500; */
}
.user-desc-bottom {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  align-content: center;
  justify-content: flex-start;
  align-items: center;
}
.user-info-email {
   margin-right: 10px;
}
.user-info-email > span{
  font-size: 18px;
  font-weight: 500;
}
.user-info-cbti {
  width: 40px;
  background: #D9C6B0;
  border-radius: 5px;
  text-align: center;
  line-height: 1.2em;
}
.user-info-cbti > span {
  font-size: 13px;
  font-weight: bold;
}
/* 모바일 환경 */
@media (max-width: 500px) {
   #userRow {
        margin-left: 40px;
    }
}
</style>
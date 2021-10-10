<template>
    <div id="userSearchRoot">
        <sub-nav
            :title="'팔로우'"
        />
        <div class="user-search-list">
            <div id="searchWrap">
                <div class="top-search">
                    <div class="search-bg">
                        <div class="search-item">
                            <i class="search-icon fas fa-search"></i>
                                <input type="text"
                                    name="word"
                                    @input="search"
                                    v-model="word"
                                    class="search-input"
                                    id="search"
                                    maxlength="20"
                                />
                        </div>
                    </div>
                </div>
            </div>
            <!-- searchWrap end -->
            <div id="listTabWrap">
                <div class="list-tab-menu">
                    <button class="list-tab-menu-1 on" @click="onTabMenu1">
                        <span id="tabMenu1">{{this.tabMenuTitle1}} {{this.followerList.length}}명</span>
                    </button>
                    <button class="list-tab-menu-2" @click="onTabMenu2">
                        <span id="tabMenu2">{{this.tabMenuTitle2}} {{this.followingList.length}}명</span>
                    </button>
                </div>
            </div>
            <div class="list-arrange-group">
                <div class="dropdown-toggle">
                    <button @click="dropdown" class="arrange-btn" id="arrangeBtn">정렬<i class="fas fa-sort-down"></i></button>
                <div v-show="show" class="dropdown-menu">
                    <a href="#" class="dropdown-item" @click="arrangeId"><span>ID</span></a>
                    <a href="#" class="dropdown-item" @click="arrangeCbti"><span>CBTI</span></a>
                </div>
                </div>
            </div>
            <div class="tab-menu-item-1">
                <follow-row
                  v-for="(user, index) in list"
                  :key="index"
                  :idx="index"
                  :userId="user.userInfo['user_id']"
                  :cbti="user.userInfo['user_CBTI']"
                  :email="user.userInfo['user_email']"
                  :mute="user.mute"
                  :isFollowEachOther="user.isFollowEachOther"
                  :type="(Menu1) ? 'follower' : 'following'"
                />
            </div>
    </div>
    </div>
</template>

<script>
import SubNav from '@/components/header/SubNav.vue'
import FollowRow from '@/components/listRow/FollowRow.vue'
import http from "@/util/http-common.js";

export default {
    name: "FollowList",
    components: {
        SubNav,
        FollowRow,
    },
    data() {
      return {
          word: "",
          followerList: [],
          followingList: [],
          list : '',
          tabMenuTitle1: "팔로워",
          tabMenuTitle2: "팔로잉",
          Menu1: true,
          Menu2: false,
          show: false,
      }
    },
    created(){

        const user_email =  localStorage.getItem("user_email")
        http.get(`follow/followers?user_email=${user_email}`)
        .then((res)=>{
          this.followerList = res.data.followersList
          this.followerList = this.followerList.filter(
            (element) => element.userInfo != null
          )
          this.list = this.followerList
        })
        http.get(`follow/following?user_email=${user_email}`)
        .then((res)=>{
          this.followingList = res.data.followingList
          this.followingList = this.followingList.filter(
            (element) => element.userInfo != null
          )
          console.log(this.followingList)
        })
    },
    methods: {
        search() {
          if (this.Menu1){
            this.list = this.followerList.filter((user)=>{return (user.userInfo["user_id"].includes(this.word))})
          } 
          if(this.Menu2){
            this.list = this.followingList.filter((user)=>{return (user.userInfo["user_id"].includes(this.word))})
          }
        },
        onTabMenu1() {
          this.Menu1 = true;
          this.Menu2 = false;
          var tab = document.getElementsByClassName('list-tab-menu')[0];
          tab.children[0].classList.add('on');
          tab.children[1].classList.remove('on');
          this.list.splice(0)
          this.word === '' ? this.list = this.followerList.slice() : this.search()
        },
        onTabMenu2() {
          this.Menu1 = false;
          this.Menu2 = true;
          var tab = document.getElementsByClassName('list-tab-menu')[0];
          tab.children[1].classList.add('on');
          tab.children[0].classList.remove('on');
          this.word === '' ? this.list = this.followingList.slice() : this.search()
        },
        dropdown() {
            this.show = !this.show;
        },
        arrangeId() {
            function arrangeById(a, b){
              if(a.userInfo["user_id"] == b.userInfo["user_id"]) return 0; 
              return a.userInfo["user_id"] > b.userInfo["user_id"] ? 1 : -1;
            }
            this.list.sort(arrangeById)
            // this.followingList.sort(arrangeById)
            const arrangeBtn = document.getElementById("arrangeBtn")
            arrangeBtn.innerHTML="ID<i class='fas fa-sort-down'></i>"
            this.show = !this.show;
        },
        arrangeCbti() {
            function arrangeByCBTI(a, b){
              if(a.userInfo["user_CBTI"] == b.userInfo["user_CBTI"]) return 0; 
              return a.userInfo["user_CBTI"] > b.userInfo["user_CBTI"] ? 1 : -1;
            }
            this.list.sort(arrangeByCBTI)
            // this.followerList.sort(arrangeByCBTI)
            // this.followingList.sort(arrangeByCBTI)
            const arrangeBtn = document.getElementById("arrangeBtn")
            arrangeBtn.innerHTML="CBTI<i class='fas fa-sort-down'></i>"
            this.show = !this.show;
        },
    },
    computed:  {
        // filtered: function() {
        //     var cname = this.word.trim();
        //     return this.userList.filter(function (item) {
        //         if(item.userid.indexOf(cname) > -1) {
        //             return true;
        //         }
        //     });
        // },
    },
}
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Fredoka+One&family=Lobster&family=Jua&family=Noto+Sans+KR:wght@300;400&display=swap");
* {
    font-family: "Noto Sans KR";
    font-weight: 400;
}
a {
	text-decoration: none;
	color: black;
}
ul {
	padding: 0;
	margin: 0;
}
li {
	list-style: none;
}
input {
    border: 0;
    background: none;
}
input:focus {
    outline: none;
}
button {
    border: none;
    background: none;
}
#userSearchRoot {
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
}
#searchWrap {
    margin: 0 auto;
}
.top-search {
    width: 280px;
    height: 35px;
    margin: 0 auto;
    margin-bottom: 50px;
}
.top-search > .search-bg {
    width: 100%;
    height: 100%;
    border-radius: 100px;
    background-color: #E7E7E7;
    display: flex;
    flex-wrap: nowrap;
    align-content: center;
    align-items: center;
    justify-content: flex-start;
}
.top-search > .search-bg > .search-item {
    width: 260px;
    min-width: 260px;
    height: 30px;
    position: absolute;
    display: flex;
    align-items: center;
    align-content: center;
}
.search-icon, .search-input {
    position: relative;
}
.search-icon {
    left: 15px;
    color: #989898;
    font-size: 15px;
}
.search-input {
    width: 220px;
    font-size: 20px;
    margin-left: 20px;
}
.user-search-list {
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    align-content: flex-start;
    justify-content: center;
    align-items: center;
    position: relative;
    top: 200px;
}
/* arrangeBtn dropdown */
.list-arrange-group {
    width: 60%;
    height: 50px;
}
.dropdown-toggle {
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: flex-end;
    align-items: flex-end;
}
.arrange-btn {
    background-color: #C4C4C4;
    width: 70px;
    height: 30px;
    border-radius: 5px;
    font-size: 18px;
}
.dropdown-menu {
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    align-content: center;
    justify-content: space-evenly;
    align-items: center;
    width: 110px;
    height: 70px;
    border: 1px solid #C4C4C4;
    border-radius: 5px;
    background-color: white;
    z-index: 100;
}
.dropdown-menu > a > span{
    font-size: 15px;
}
/* tabMenu */
#listTabWrap {
    width: 100%;
    height: 80px;
}
.list-tab-menu {
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    justify-content: center;
    align-items: flex-end;
    align-content: center;
}
.list-tab-menu > button > span {
    font-size: 20px;
}
.list-tab-menu-1, .list-tab-menu-2 {
    text-align: center;
    width: 35%;
    min-width: 250px;
    border-bottom: 1px solid rgb(196, 196, 196);
    padding: 10px 0 15px 0;
    min-width: 150px;
}
.list-tab-menu-1.on, .list-tab-menu-2.on {
    border-bottom: 5px solid rgb(32, 32, 32);
}
.tab-menu-item-1, .tab-menu-item-2 {
    width: 70%;
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    align-content: flex-start;
    justify-content: center;
    align-items: flex-start;
}
/* 모바일 환경 */
@media (max-width: 500px) {
    /* 검색창 너비 */
    .top-search {
        width: 220px;
    }
    .search-item, .search-input {
        width: 200px;
        min-width: 200px;
    }
    /* 유저 리스트 정렬 */
    .user-search-list {
        align-items: flex-start;
    }
}
</style>
<template>
    <div id="userSearchRoot">
        <sub-nav
            :title="'유저 검색'"
        />
        <div class="user-search-list">
            <div id="searchWrap">
                <div class="top-search">
                    <div class="search-bg">
                        <div class="search-item">
                            <i class="search-icon fas fa-search"></i>
                            <!-- 유저 아이디로 검색 -->
                                <input type="text"
                                    name="word"
                                    v-if="Menu1"
                                    @input="searchUser(word)"
                                    v-model="word"
                                    class="search-input"
                                    id="searchU"
                                    maxlength="20"
                                    placeholder="아이디로 검색"
                                    @keydown.space.prevent
                                />
                            <!-- CBTI로 검색 -->
                                <input type="text"
                                    name="cbtiInput"
                                    v-if="Menu2"
                                    @input="searchCbti(cbtiInput)"
                                    @focus="showList"
                                    v-model="cbtiInput"
                                    class="search-input"
                                    id="searchC"
                                    maxlength="4"
                                    placeholder="CBTI로 검색"
                                    autocomplete="off"
                                    @keydown.space.prevent
                                />
                        </div>
                        <div class="autocomplete hide" @keyup="nextItem" v-if="Menu2">
                            <ul class="cbti-list">
                                <li v-for="(item, index) in filterCbti"
                                    :key="index"
                                    :class='{"active-item": currentItem === index}'
                                    @click="searchItemAdd(item)"
                                    @keydown.enter="searchCbti(item)"
                                    class="cbti-list-item"
                                >{{item}}</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <!-- searchWrap end -->
            <div id="listTabWrap">
                <div class="list-tab-menu">
                    <button class="list-tab-menu-1 on" @click="onTabMenu1">
                        <span id="tabMenu1">{{this.tabMenuTitle1}}</span>
                    </button>
                    <button class="list-tab-menu-2" @click="onTabMenu2">
                        <span id="tabMenu2">{{this.tabMenuTitle2}}</span>
                    </button>
                </div>
            </div>
            <div class="tab-menu-item-1" v-if="Menu1">
                <div class="result-row">
                    <user-row
                    v-for="(user, index) in userList_id"
                        :key="index"
                        :userId="user.user_id"
                        :email="user.user_email"
                        :cbti="user.user_CBTI"
                    />
                </div>
            </div>
            <div class="tab-menu-item-2" v-if="Menu2">
                <div class="result-row">
                    <user-row
                    v-for="(user, index) in userList_cbti"
                        :key="index"
                        :userId="user.user_id"
                        :email="user.user_email"
                        :cbti="user.user_CBTI"
                    />
                </div>
            </div>
    </div>
    </div>
</template>

<script>
import SubNav from '@/components/header/SubNav.vue'
import UserRow from '@/components/listRow/UserRow.vue'
import AllCbtiList from '../../js/AllCbtiList.js'
import {mapGetters} from "vuex"
import http from "../../util/http-common"


export default {
    name: "UserSearch",
    components: {
        SubNav,
        UserRow,
    },
    data() {
        return {
            word: '',
            cbtiInput: '',
            selectInput: '',
            result: AllCbtiList,
            currentItem: 0,
            tabMenuTitle1: "계정",
            tabMenuTitle2: "CBTI",
            Menu1: true,
            Menu2: false,
            userList_id: [],
            userList_cbti: [],     
        }
    },
    methods: {
        searchUser(e) {
            this.word = e;
            const listCheck = document.querySelector(".result-row");
            // 값이 들어가면 검색 시작
            if(this.word.trim()) {
                // console.log(this.word);
                listCheck.classList.remove("hide");
                http.get('/user/search/id/'+e)
                    .then((res) => {
                        this.userList_id = res.data;
                    })
                    .catch(() => {
                        console.log("에러발생");
                    });
            } else {
                // console.log("숨김" + this.word);
                listCheck.classList.add("hide");
            }
        },
        searchCbti(e) {
            // 입력값에 따라 computed에서 filter
            this.cbtiInput = e;
            
            // 자동완성 리스트 div 찾음
            const autocomp = document.querySelector(".autocomplete");
            const listCheck = document.querySelector(".result-row");
            // console.log(autocomp);
            // 값이 입력되면
            if(this.cbtiInput) {
                // 입력 시작하면 자동완성 리스트가 보임
                autocomp.classList.remove("hide");
                listCheck.classList.remove("hide");
                http.get('/user/search/cbti/' + e.toUpperCase())
                    .then((res) => {
                        // console.log("DB접근");
                        this.userList_cbti = res.data;
                        // console.log(this.userList_cbti);
                    })
                    .catch(() => {
                        console.log("에러발생");
                    });
            } else {
                // 입력값 없으면 숨김
                autocomp.classList.add("hide"); 
                listCheck.classList.add("hide");
            }
            
        },
        searchItemAdd(item) {
            // 리스트 항목을 클릭하면 input에 입력
            this.cbtiInput = item;
            // console.log(this.cbtiInput);
            this.searchCbti(item);
            // 그러고 밑에 뜬 자동완성 리스트는 숨김
            const autocomp = document.querySelector(".autocomplete");
            autocomp.classList.add("hide"); 
            
        },
        nextItem(event) {
            if(event.keyCode === 38) {
                // console.log("위 방향키");
                if(this.currentItem > 0) {
                    this.currentItem--;
                } else {
                    this.currentItem = this.filterCbti.length-1;
                }
            } else if (event.keyCode === 40){
                // console.log("아래 방향키");
                if(this.currentItem < this.filterCbti.length-1) {
                    this.currentItem++;
                } else {
                    this.currentItem = 0;
                }
            } 
            else if (event.keyCode === 13) {
                // console.log("엔터");
                this.searchItemAdd(this.filterCbti[this.currentItem]);
            }
        },
        hideList() {
            const autocomp = document.querySelector(".autocomplete");
            autocomp.classList.add("hide"); 
        },
        showList() {
            const autocomp = document.querySelector(".autocomplete");
            autocomp.classList.remove("hide"); 
        },
        onTabMenu1() {
            this.Menu1 = true;
            this.Menu2 = false;
            var tab = document.getElementsByClassName('list-tab-menu')[0];
            tab.children[0].classList.add('on');
            tab.children[1].classList.remove('on');
        },
        onTabMenu2() {
            this.Menu1 = false;
            this.Menu2 = true;
            var tab = document.getElementsByClassName('list-tab-menu')[0];
            tab.children[1].classList.add('on');
            tab.children[0].classList.remove('on');
        },
        
    },
    mounted() {
        document.addEventListener("keyup", this.nextItem);
    },
    destroyed () {
        document.removeEventListener("keyup", this.nextItem);
    },
    computed: {
        ...mapGetters(["searchUsers"]),
        filtered: function() {
            var cname = this.word.trim();
            return this.userList.filter(function (item) {
                if(item.user_id.indexOf(cname) > -1) {
                    return true;
                }
            });
        },
        filterCbti: function() {
            var cname = this.cbtiInput.trim();
            return this.result.filter(function (item) {
                if(item.indexOf(cname.toUpperCase()) > -1) {
                    return true;
                }
            });
            // return false;
        },
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
    flex-direction: row;
    flex-wrap: nowrap;
    align-content: flex-start;
    align-items: flex-start;
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
    padding-top: 3px;
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
    font-size: 1.0em;
    margin-left: 20px;
}
.autocomplete {
    background-color: white;
    width: 90%;
    height: auto;
    max-height: 160px;
    position: relative;
    top: 35px;
    left: 15px;
    border: 1px solid rgb(196, 196, 196);
    border-radius: 5px;
    font-size: 1.0em;
    line-height: 1.7em;
    overflow: auto;
    scrollbar-width: none;
    -ms-overflow-style: none;
}
.autocomplete::-webkit-scrollbar {
    display: none;
}
.hide {
    display: none;
}
.cbti-list {
    overflow: auto;
    height: 100%;
    /* 스크롤 바 없앰 */
    scrollbar-width: none;
    -ms-overflow-style: none;
}
.cbti-list::-webkit-scrollbar {
    display: none;
}
.cbti-list-item {
    border-bottom: 1px solid rgb(196,196,196);
    padding: 5px;
}
.cbti-list-item:hover {
    background-color: #ececec;
}
.active-item {
    background-color: #ececec;
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
.result-row {
    width: 100%;
}
/* 모바일 환경 */
@media (max-width: 500px) {
    /* 검색창 너비 */
    .top-search {
        width: 220px;
    }
    .search-item, .search-input {
        width: 160px;
        min-width: 150px;
    }
    /* 유저 리스트 정렬 */
    .user-search-list {
        align-items: flex-start;
    }
}
</style>
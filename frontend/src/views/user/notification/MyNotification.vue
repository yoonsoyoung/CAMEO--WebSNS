<template>
    <div id="MyNotificationRoot">
        <sub-nav
            :title="title"
            @goBack="goBack"
        />
        <div class="user-search-list">
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
                <div class="newAct">
                    <span class="act-title">새로운 활동</span>
                    <div class="act-list-box">
                        <notification-row
                        v-for="(user, index) in notiList"
                            :key="index"
                            :userId="user.userid"
                            :type="user.type"
                            @click="goUserLink"
                        />
                    </div>
                </div>
                <div class="oldAct">
                    <span class="act-title">이전 활동</span>
                    <div class="act-list-box">
                        <notification-row
                        v-for="(user, index) in notiList"
                            :key="index"
                            :userId="user.userid"
                            :type="user.type"
                            @click="goUserLink"
                        />
                    </div>
                </div>
            </div>
            <div class="tab-menu-item-2" v-if="Menu2">
                <notification-row
                v-for="(user, index) in reqList"
                    :key="index"
                    :userId="user.userid"
                    :type="user.type"
                    @click="goUserLink"
                />
            </div>
    </div>
    </div>
</template>

<script>
import SubNav from '@/components/header/SubNav.vue'
import NotificationRow from '@/components/listRow/NotificationRow.vue'

export default {
    name: "MyNotification",
    components: {
        SubNav,
        NotificationRow,
    },
    data() {
        return {
            word: "",
            title: "알림",
            tabMenuTitle1: "알림",
            tabMenuTitle2: "요청",
            Menu1: true,
            Menu2: false,
            notiList: [
                {
                    userid:'아이디1',
                    type: 'noti',
                }, 
                {
                    userid:'아이디2',
                    type: 'noti',
                }, 
                {
                    userid:'아이디3',
                    type: 'result',
                }, 
            ],
            reqList: [
                {
                    userid:'아이디12',
                    type: 'request',
                }, 
                {
                    userid:'아이디22',
                    type: 'request',
                }, 
                {
                    userid:'아이디32',
                    type: 'request',
                }, 
            ],
        }
    },
    methods: {
        goBack() {
            window.history.back();
        },
        search(e) {
            this.word = e.target.value;
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
        goUserLink(e) {
            alert(e.target.userid + "님 프로필로 이동");
        }
    },
    computed: {
        filtered: function() {
            var cname = this.word.trim();
            var filterType = this.notiList;
            if(this.type === 'request')
                filterType = this.reqList;
            return filterType.filter(function (item) {
                if(item.userid.indexOf(cname) > -1) {
                    return true;
                }
            });
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
/* Menu in tab */
.act-list-box {
    margin-left: 40px;
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    align-content: flex-start;
    justify-content: center;
    align-items: flex-start;
}
.act-title {
    margin-left: 20px;
}
/* 모바일 환경 */
@media (max-width: 500px) {
    /* 유저 리스트 정렬 */
    .user-search-list {
        align-items: flex-start;
    }
    /* 알림 리스트 */
    .act-list-box {
      margin-left: 20px;
    }
    .user-search-list {
        top: 140px;
    }
}
</style>
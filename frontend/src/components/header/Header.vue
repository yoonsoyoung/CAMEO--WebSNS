<template>
<div id="header">
    <div id="header-root">
        <div id="headerBox" class="bg-main">
            <div class="header-bg">
                <div class="header-inner">
                    <div id="logo">
                        <router-link to="/main">
                            <div class="logo-text">
                                <span>CAMEO</span>
                            </div>
                        </router-link>
                        <div class="header-center">
                            <div id="Menu">
                                <router-link to="/cafeinfo">카페정보</router-link>
                                <router-link to="/notice">공지사항</router-link>
                            </div>
                        </div>
                    </div>
                    <div class="header-right">
                        <div id="userMenu">
                            <div class="user-menu-list">
                                <div class="user-menu-list-item user-notification-btn">
                                    <router-link to="/notification" class="user-menu-btn">
                                        <i class="user-menu-icon far fa-bell"></i>
                                        <div class="notification">
                                            <div class="notification-count">1</div>
                                        </div>
                                    </router-link>
                                </div>
                                <div class="user-menu-list-item">
                                    <a @click="goMyPage"><i class="user-menu-icon fas fa-user-circle"></i></a>
                                </div>
                                <div class="user-menu-list-item">
                                    <button id="sideMenuBtn" @click="showSideMenu"><i class="user-menu-icon fas fa-bars"></i></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
        <!-- headerBox end -->
        <div id="sideNavView"  class="blackBg" v-show="sideNavOn">
            <div id="sideNavRoot">
            <div class="side-nav">
                <div class="side-nav-top">
                    <div class="close-item">
                        <button id="closeBtn" 
                            @click="closeSideNav"
                        >
                            <i class="close-btn fas fa-times"></i></button>
                    </div>
                    <div class="side-nav-search">
                        <div class="search-bg">
                            <router-link to="/usersearch">
                                <div class="search-item">
                                    <i class="search-icon fas fa-search"></i>
                                    <input type="text" id="searchAll" class="search-input" placeholder="유저 검색" disabled>
                                </div>
                            </router-link>
                        </div>
                    </div>
                    <div class="side-nav-userInfo">
                        <div class="user-info">
                            <div class="user-info-group">
                                <div class="user-info-rank">
                                    <img src="../../assets/images/rank_pot.png" alt="뱃지" class="user-info-rank-icon">
                                </div>
                                <div class="user-info-id">
                                    <span>{{this.user.id}}</span>
                                </div>
                            </div>
                            <div class="user-info-edit">
                                <router-link to="/mypage/settings"><i class="far fa-edit"></i></router-link>
                            </div>
                        </div>
                        <div class="user-info-setting">
                            <div class="user-info-myalarm">
                                <router-link :to="`/mypage/like/${this.user.email}`" id="storedLike">내좋아요</router-link>
                                <span> | </span>
                                <router-link :to="`/mypage/scrap/${this.user.email}`" id="storedScrap">내스크랩</router-link>
                                <span> | </span>
                                <span id="logout" @click="logout"><a>로그아웃</a></span>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- SideNav Top End -->
                <hr class="side-nav-hr">
                <div class="side-nav-contents">
                    <div class="side-nav-menu-list">
                        <div class="side-nav-menu-list-item" id="goMypage">
                            <a @click="goMyPage" class="side-nav-menu-list-page">
                                <div class="side-menu-list-icon-bg">
                                    <div class="side-menu-list-icon">
                                        <i class="side-user-menu-icon fas fa-user-circle"></i>
                                    </div>
                                </div>
                                <div class="side-menu-list-pageName">
                                    <span>마이페이지</span>
                                </div>
                            </a>
                        </div>
                        <div class="side-nav-menu-list-item" id="goCafeInfo">
                            <router-link to="/cafeinfo" class="side-nav-menu-list-page">
                                <div class="side-menu-list-icon-bg">
                                    <div class="side-menu-list-icon">
                                        <i class="side-user-menu-icon fas fa-store"></i>
                                    </div>
                                </div>
                                <div class="side-menu-list-pageName">
                                    <span>카페정보</span>
                                </div>
                            </router-link>
                        </div>
                        <div class="side-nav-menu-list-item" id="goNotice">
                            <router-link to="/notice" class="side-nav-menu-list-page">
                                <div class="side-menu-list-icon-bg">
                                    <div class="side-menu-list-icon">
                                        <i class="side-user-menu-icon fas fa-bullhorn"></i>
                                    </div>
                                </div>
                                <div class="side-menu-list-pageName">
                                    <span>공지사항</span>
                                </div>
                            </router-link>
                        </div>
                        <div class="side-nav-menu-list-item" id="goReport">
                            <router-link to="/cbtitest" class="side-nav-menu-list-page">
                                <div class="side-menu-list-icon-bg">
                                    <div class="side-menu-list-icon">
                                        <!-- <i class="side-user-menu-icon fas fa-envelope-open-text"></i> -->
                                        <i class="side-user-menu-icon fas fa-file-alt"></i>
                                    </div>
                                </div>
                                <div class="side-menu-list-pageName">
                                    <span>CBTI 검사</span>
                                </div>
                            </router-link>
                        </div>
                        <div class="side-nav-menu-list-item" id="goQna">
                            <router-link to="/inquiry" class="side-nav-menu-list-page">
                                <div class="side-menu-list-icon-bg">
                                    <div class="side-menu-list-icon">
                                        <i class="side-user-menu-icon fas fa-question"></i>
                                    </div>
                                </div>
                                <div class="side-menu-list-pageName">
                                    <span>문의하기</span>
                                </div>
                            </router-link>
                        </div>
                    </div>
                    <!-- SideNav MenuList End -->
                    <hr class="side-nav-hr">
                    <div class="side-nav-footer">
                        <div class="side-nav-devSNS">
                            <div class="devSNS-item">
                                <a href="#" id="devSNS-insta" target="_blank">
                                    <div class="devSNS-icon-bg">
                                        <i class="devSNS-icon fab fa-instagram"></i>
                                    </div>
                                </a>
                                <a href="https://lab.ssafy.com/s05-webmobile2-sub3/S05P13C202" id="devSNS-git" target="_blank">
                                    <div class="devSNS-icon-bg">
                                        <i class="devSNS-icon fab fa-gitlab"></i>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <!-- sideNav devSNS End -->
                        <hr class="side-nav-hr">
                        <div class="side-nav-foot-menu">
                            <div class="side-nav-foot-menu-list">
                                <router-link to="" id="howto"><span>카메오스토리</span></router-link>
                                <router-link to="" id="policy"><span>이용약관</span></router-link>
                                <router-link to="" id="siteMap"><span>사이트맵</span></router-link>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- SideNav Content End -->
            </div>
        </div>
    </div>
</div>
</template>
<script>
export default {
    name: "HeaderItem",
    components: {
    },
    data() {
        return {
            sideNavOn: false,
            rank_img_src: "../../assets/images/rank_",
            rank: "pot",
            user: {
                id: "",
                email: "",
            }
        }
    },
    created() {
        this.user.id = localStorage.getItem("user_id");
        this.user.email = localStorage.getItem("user_email");
    },
    methods: {
        showSideMenu() {
            this.sideNavOn = true;
        },
        closeSideNav() {
            this.sideNavOn = false;
        },
        logout() {
            // this.$store
            //     .dispatch("LOGOUT")
            //     .then(() => {
            //         if (this.$route.path !== "/") this.$router.replace("/");
            //     })
            //     .catch(() => {
            //         //console.log("로그아웃 문제!!!");
            //     });
            localStorage.removeItem("access-token")
            localStorage.removeItem("user_email")
            localStorage.removeItem("user_id")
            alert("로그아웃");
            if (this.$route.path !== "/") this.$router.replace("/");
            // this.$router.push('/')

        },
        goMyPage() {
            this.$router.push({
                path: "/mypage/home/" + this.user.email,
            })
            window.location.reload();

        },
    },

};
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
    cursor: pointer;
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
.bg-main {
	background:  #D9C6B0 ;
}
#header {
    padding: 0;
	margin: 0;
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
}
#header-root{
    display: flex;
    width: 100%;
    margin: 0;
    padding: 0;
    flex-basis: 55px;
    height: 80px;
    position: fixed;
    z-index: 888;
}
#headerBox {
    width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
}
.header-bg {
    position: fixed;
    top: 0;
    z-index: 3;
    width: 100%;

}
.header-inner {
    display: flex;
    align-items: center;
    flex-direction: row;
    /* width: 100%; */
    height: 80px;
    padding: 0 100px;
    z-index: 10;
    justify-content: center;
}
.header-center {
    min-width: 125px;
    width: 215px;

}
.header-right {
    align-items: center;
    align-content: center;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    -webkit-box-pack: end;
    -webkit-box-orient: horizontal;
    justify-content: flex-end;
}
#logo {
    min-width: 80px;
    display: flex;
    flex: 1 9999 0%;
    position: relative;
    align-items: center;
    flex-direction: column;
    min-width: 100px;
    justify-content: flex-start;
    -webkit-box-pack: start;
    -webkit-box-orient: horizontal;
    flex-direction: row;
}
#logo > a > .logo-text {
    flex: 0 0 auto;
    justify-content: flex-start;

}
#logo > a > .logo-text > span {
    font-family: 'Fredoka One','Lobster','Jua';
    font-size: 32px;
    color: #365959;
}
#Menu {
    font-size: 20px;
    font-weight: 600;
}
#Menu > a{
    margin: 0 10px;
    color: #40291C;
}
#Menu > a:first-child {
    margin-left: 30px;
}
#Menu > a:hover {
    color: #A6774E;
}
.userMenu {
    display: flex;
    flex-direction: row;
    padding-left: 24px;
    white-space: nowrap;
    -webkit-box-orient: horizontal;
}
.user-menu-list {
    display: flex;
    flex-direction: row;
    white-space: nowrap;
    align-items: center;
}
.user-menu-list-item {
       align-items: center;
}
.user-menu-list-item:not(:first-child) {
    margin-left: 30px;
}
.user-notification-btn {
    padding-top: 13px;
}
.userMenu > .user-menu-list > .user-menu-btn {
    position: relative;
}
.user-menu-icon {
    width: 25px;
    height: 25px;
    color: #272727;
}
#sideMenuBtn > .user-menu-icon{
    color: #365959;
}
.notification {
    background-color: #ed4965;
    border-radius: 100%;
   height: 13px;
    min-width: 10px;
    width: 13px;
    position: relative;
    right: -15px;
    top: -34px;
}
.notification > .notification-count {
    color: white;
    min-width: 13px;
    height: 13px;
    font-size: 10px;
    text-align: center;
}
/* 모바일 화면에서 */
@media (max-width: 500px), (max-width: 650px) {
    /* 메뉴 안보임 */
    .header-center {
        display: none;
    }
    /* 헤더 좌우 패딩 축소 */
    .header-inner {
        padding: 0 20px;
    }
    /* 유저 메뉴 버튼 간격 축소 */
    .user-menu-list-item:not(:first-child) {
        margin-left: 15px;
    }
    /* 사이드 nav 넓이 확장 */
    .side-nav {
        width: 50%;
    }
}

/* side Nav */
#sideNavView {
    width: 100%;
    height: 100%;
    z-index: 888;
}
#sideNavRoot {
    width: 100%;
    height: 100%;
}
.blackBg {
    margin: 0;
    padding: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.8);
    z-index: 30;
    position: fixed;
}
.side-nav {
    position: absolute;
    width: 350px;
    height: 100%;
    top: 0;
    right: 0;
    background-color: white;
    text-align: center;
    overflow: auto;
    /* for Firefox */
    scrollbar-width: none;
    /* for IE, Edge */
    -ms-overflow-style: none;
    
    /* 슬라이드 애니메이션 */
    animation-duration: 0.5s;
    animation-name: side-slide;
    -webkit-animation: 0.5s;
    -webkit-animation-name: side-slide;
}
/* For Chrome, Safari and Opera */
.side-nav::-webkit-scrollbar {
    display: none;
}
#closeBtn {
    width: 20px;
    height: 20px;
    position: fixed;
}
.side-nav-top {
    width: 100%;
    height: 300px;
    /* background-color: #999; */
    text-align: center;
    border-bottom-width: 80%;
    border-bottom-color: lightgray;
}
.side-nav-hr {
    margin: 0 30px;
    color: gray;
}
.close-item {
    text-align: left;
    padding: 30px 20px;
}
#closeBtn > .close-btn {
    font-size: 32px;
}
.side-nav-search {
    width: 220px;
    height: 30px;
    margin: 0 auto;
    margin-top: 30px;
}
.side-nav-search > .search-bg {
    width: 100%;
    height: 100%;
    border-radius: 100px;
    background-color: #E7E7E7;
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    align-content: center;
    justify-content: flex-start;
    align-items: center;
}
.side-nav-search > .search-bg > .search-item {
    width: 220px;
    min-width: 200px;
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
    width: 70%;
    font-size: 15px;
    margin-left: 25px;
}

.user-info {
    display: flex;
    align-content: center;
    align-items: baseline;
    justify-content: center;
    margin: 40px 0 10px 0;
}
.user-info-edit {
    font-size: 15px;
}
.user-info-edit > a:hover {
    color: #365959;
}
.user-info-group {
    display: flex;
    align-items: baseline;
}
.user-info-rank, .user-info-id, .user-info-edit {
    display: inline-block;
    margin: 0 5px;
}
.user-info-rank-icon {
    width: 30px;
    height: 30px;
    display: flex;
}
.user-info-id > span {
    font-size: 35px;
    font-weight: 500;
    line-height: 50px;
}
.user-info-myalarm > a, #logout {
    margin: 0 2px;
    font-size: 14px;
    text-decoration: underline;
}
.user-info-myalarm > a:hover {
    color: #40291C;
}
.user-info-myalarm > span {
    font-size: 14px;
}
.side-nav-contents {
    /* overflow: auto; */
    width: 100%;
    height: 500px;
    max-height: 800px;
    float: left;
}
.side-nav-menu-list {
    display: flex;
    flex-direction: column;
    flex-wrap: wrap;
    align-content: flex-start;
    justify-content: center;
    align-items: flex-start;
    padding: 30px 60px;
}
.side-nav-menu-list-page{
    display: flex;
    align-content: center;
    align-items: center;
    flex-direction: row;
    flex-wrap: nowrap;
    padding-bottom: 25px;
}
.side-nav-menu-list-item:last-child > a{
    padding-bottom: 0px;
}
.side-nav-menu-list-page:hover {
    color: #A6774E;
}
.side-menu-list-icon {
    display: flex;
    border-radius: 100%;
    width: 51px;
    height: 51px;
    background-color: #F1F1F1;
    align-content: center;
    justify-content: center;
    align-items: center;
}
.side-user-menu-icon {
    color: #555555;
    font-size: 21px;
}
.side-menu-list-pageName {
    margin-left: 20px;
}
.side-menu-list-pageName > span {
    font-size: 22px;
    font-weight: 600px;
}
.side-menu-list-pageName > span:active {
    color: #A6774E;
}
.side-nav-footer {
    width: 100%;
    height: 200px;
}
.side-nav-devSNS {
    margin: 0 auto;
    padding: 20px 30px;
}
.devSNS-item {
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    align-content: center;
    justify-content: center;
    align-items: center;
}
.devSNS-item > a:hover {
    color: #365959;
}
.devSNS-icon-bg {
    width: 40px;
    height: 40px;
    border-radius: 100%;
    border: 2px solid rgb(22, 22, 22);
    background-color: white;
    display: flex;
    align-content: center;
    align-items: center;
    justify-content: center;
}
.devSNS-icon {
    font-size: 30px;
}
#devSNS-insta {
    margin-right: 40px;
}
.side-nav-foot-menu-list {
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    align-content: center;
    justify-content: center;
    align-items: center;
}
.side-nav-foot-menu-list > a > span {
    font-size: 13px;
    color: #989898;
    line-height: 40px;
    margin: 0 10px;
}
/* 애니메이션 */
@keyframes side-slide {
    from {
        margin-right: -350px;
    }
    to {
        margin-right: 0;
    }
}
@-webkit-keyframes side-slide {
    from {
        margin-right: -350px;
    }
    to {
        margin-right: 0;
    }
}
</style>

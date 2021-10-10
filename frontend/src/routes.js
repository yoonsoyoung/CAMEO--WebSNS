
import Vue from "vue"
import VueRouter from "vue-router"
// import Login from './views/user/Login.vue'
// import Join from './views/user/Join.vue'
import MainFeed from './views/feed/MainFeed.vue'
import Components from './views/Components.vue'
import Header from './components/header/Header.vue'
import JoinBg from './views/user/JoinBg.vue'
import LoginBg from './views/user/LoginBg.vue'
import MyPage from './views/user/MyPage.vue'
import ChangePassword from './views/user/ChangePassword.vue'
import RemoveUser from './views/user/RemoveUser.vue'
import MyInsta from './views/user/MyInsta.vue'
import Page404 from './views/feed/Page404.vue'
import Notice from './views/notice/Notice.vue'
import NoticeWrite from './views/notice/NoticeWrite.vue'
import NoticeRead from './views/notice/NoticeRead.vue'
import NoticeUpdate from './views/notice/NoticeUpdate.vue'

import Inquiry from './views/inquiry/Inquiry.vue'
import InquiryRead from './views/inquiry/InquiryRead.vue'
import InquiryCreate from './views/inquiry/InquiryCreate.vue'
import InquiryUpdate from './views/inquiry/InquiryUpdate.vue'


import UserSearch from './views/search/UserSearch.vue'
import QnA from './views/notice/QnA.vue'
import LikeFeed from './views/user/mystorefeed/LikeFeedList.vue'
import ScrapFeed from './views/user/mystorefeed/ScrapFeedList.vue'
import Notification from './views/user/notification/MyNotification.vue'
import FollowList from './views/user/follow/FollowList.vue'
import WsLive from './views/user/mystorefeed/WebSocketTest.vue'
import WsSend from './views/sendsubscribe/SendSubscribe.vue'
import FeedComment from './views/feed/FeedComment.vue'
import FeedUpload from './views/feed/FeedUpload.vue'
import FeedForm from './views/feed/FeedForm.vue'
import CbtiTest from './views/cbti/CbtiTest.vue'
import CafeInfo from './views/cafeinfo/CafeInfo.vue'

Vue.use(VueRouter);

// 로그인 사용자만 접근하도록
/*
const onlyAuthUser = async (to, from, next) => {
    let token = localStorage.getItem("access-token");
    if (StorageEvent.state.userInfo == null && token) {
        await store.dispatch("GET_MEMBER_INFO", token);
    };
    if (store.state.userInfo === null) {
        alert("로그인이 필요한 페이지 입니다.");
    } else {
        next();
    }
};

{
    path : '/mypage',
    name : 'MyPage',
    component : MyPage,
    beforeEnter : onlyAuthUser, // 로그인 사용자만 접근
},
*/
const routes = [

    {
        // 루트, 로그인
        path: '/',
        name: 'LoginBg',
        component: LoginBg,
        meta: { hideHeader: true }, // 이 페이지에는 헤더가 나타나지 않음
        // beforeEnter :function(to, from ,next){
        //   const user_email=localStorage.getItem("user_email")
        //   if(user_email !== null){
        //     alert('로그 아웃을 먼저 해주세요')
        //     // this.$router.push('/mypage/settings')
        //     next('/mypage/settings')
        //   }else{
        //     next('/')
        //   }
        // }
    },
    {
        // 회원가입
        path: '/join',
        name: 'JoinBg',
        component: JoinBg,
        meta: { hideHeader: true }, // 이 페이지에는 헤더가 나타나지 않음
        // beforeEnter :function(to, from ,next){
        //   const user_email=localStorage.getItem("user_email")
        //   console.log(user_email)
        //   if(user_email !== null){
        //     alert('로그 아웃을 먼저 해주세요')
        //     to('/mypage/settings')
        //   }else{
        //     next()
        //   }
        // }
    },
    {
        // 회원정보 수정
        path: '/mypage/settings',
        name: 'MyPage',
        component: MyPage,
        beforeEnter :function(to, from ,next){
          const user_email=localStorage.getItem("user_email")
          if(user_email === null){
            alert('로그인이 필요합니다')
            next('/')
          }else{
            next()
          }
        }
    },
    {
        // 비밀번호 변경
        path: '/changepassword',
        name: 'ChangePassword',
        component: ChangePassword,
        beforeEnter :function(to, from ,next){
          const user_email=localStorage.getItem("user_email")
          if(user_email === null){
            alert('로그인이 필요합니다')
            next(`/`)
          }else{
            next()
          }
        }
    },
    {
        // 회원탈퇴
        path: '/removeuser',
        name: 'RemoveUser',
        component: RemoveUser,
        beforeEnter :function(to, from ,next){
          const user_email=localStorage.getItem("user_email")
          if(user_email === null){
            alert('로그인이 필요합니다')
            next(`/`)
          }else{
            next()
          }
        }
    },
    {
        // 타 유저 프로필
        path: '/mypage/home/:user_email',
        name: 'MyInsta',
        component: MyInsta,
        props: true,
        beforeEnter :function(to, from ,next){
          const user_email=localStorage.getItem("user_email")
          if(user_email === null){
            alert('로그인이 필요합니다')
            next('/')
          }else{
            next()
          }
        }
    },
    {
      // 내가 좋아요 한 게시글 목록
      path: '/mypage/like/:user_email',
      name: 'LikeFeed',
      component: LikeFeed,
      beforeEnter :function(to, from ,next){
        const user_email=localStorage.getItem("user_email")
        if(user_email === null){
          alert('로그인이 필요합니다')
          next(`/`)
        }else{
          next()
        }
      }
    },
    {
        // 내가 스크랩 한 게시글 목록
        path: '/mypage/scrap/:user_email',
        name: 'ScrapFeed',
        component: ScrapFeed,
        beforeEnter :function(to, from ,next){
          const user_email=localStorage.getItem("user_email")
          if(user_email === null){
            alert('로그인이 필요합니다')
            next(`/`)
          }else{
            next()
          }
        }
    },
    {
        // 내 팔로우 목록(팔로워, 팔로잉)
        path: '/mypage/follow',
        name: 'FollowList',
        component: FollowList,
        beforeEnter :function(to, from ,next){
          const user_email=localStorage.getItem("user_email")
          if(user_email === null){
            alert('로그인이 필요합니다')
            next(`/`)
          }else{
            next()
          }
        }
    },
    {
        // 알림센터
        path: '/notification',
        name: 'Notification',
        component: Notification,
        beforeEnter :function(to, from ,next){
          const user_email=localStorage.getItem("user_email")
          if(user_email === null){
            alert('로그인이 필요합니다')
            next(`/`)
          }else{
            next()
          }
        }
    },

    {
        // 공지사항 전체 리스트
        path: '/notice',
        name: 'Notice',
        component: Notice,
        meta :{
            reload: true,
        },
        beforeEnter :function(to, from ,next){
          const user_email=localStorage.getItem("user_email")
          if(user_email === null){
            alert('로그인이 필요합니다')
            next(`/`)
          }else{
            next()
          }
        }
    },
    {
        // 공지사항 글 수정
        path: '/notice/write',
        name: 'NoticeWrite',
        component: NoticeWrite,
        beforeEnter :function(to, from ,next){
          const user_email=localStorage.getItem("user_email")
          if(user_email === null){
            alert('로그인이 필요합니다')
            next(`/`)
          }else{
            next()
          }
        }

    },
    {
        // 공지사항 글 검색
        path: '/notice/read/:notice_id',
        name: 'NoticeRead',
        props: true,
        component: NoticeRead,
        meta :{
            reload: true,
        },
        beforeEnter :function(to, from ,next){
          const user_email=localStorage.getItem("user_email")
          if(user_email === null){
            alert('로그인이 필요합니다')
            next(`/`)
          }else{
            next()
          }
        }
    },
    {
        // 공지사항 글 수정
        path: '/notice/update/:notice_id',
        name: 'NoticeUpdate',
        props: true,
        component: NoticeUpdate,
        beforeEnter :function(to, from ,next){
          const user_email=localStorage.getItem("user_email")
          if(user_email === null){
            alert('로그인이 필요합니다')
            next(`/`)
          }else{
            next()
          }
        }
    },

    {
        // 문의하기 글 전체리스트
        path: '/inquiry',
        name: 'Inquiry',
        component: Inquiry,
        beforeEnter :function(to, from ,next){
          const user_email=localStorage.getItem("user_email")
          if(user_email === null){
            alert('로그인이 필요합니다')
            next(`/`)
          }else{
            next()
          }
        }
    },
    {
        // 문의하기 글 등록
        path: '/inquiry/create',
        name: 'InquiryCreate',
        component: InquiryCreate,
        beforeEnter :function(to, from ,next){
          const user_email=localStorage.getItem("user_email")
          if(user_email === null){
            alert('로그인이 필요합니다')
            next(`/`)
          }else{
            next()
          }
        }
    },
    {
        // 문의하기 글 검색
        path: '/inquiry/read/:inquiry_id',
        name: 'InquiryeRead',
        props: true,
        component: InquiryRead,
        meta :{
            reload: true,
        },
        beforeEnter :function(to, from ,next){
          const user_email=localStorage.getItem("user_email")
          if(user_email === null){
            alert('로그인이 필요합니다')
            next(`/`)
          }else{
            next()
          }
        }
    },
    {
        // 문의하기 글 수정
        path: '/inquiry/update/:inquiry_id',
        name: 'InquiryeUpdate',
        props: true,
        component: InquiryUpdate,
        meta :{
            reload: true,
        },
        beforeEnter :function(to, from ,next){
          const user_email=localStorage.getItem("user_email")
          if(user_email === null){
            alert('로그인이 필요합니다')
            next(`/`)
          }else{
            next()
          }
        }
    },
    {
        // 유저 검색
        path: '/usersearch',
        name: 'UserSearch',
        component: UserSearch,
        beforeEnter :function(to, from ,next){
          const user_email=localStorage.getItem("user_email")
          if(user_email === null){
            alert('로그인이 필요합니다')
            next(`/`)
          }else{
            next()
          }
        }
    },
    {
        // 피드 전체
        path: '/main',
        name: 'MainFeed',
        component: MainFeed,
        beforeEnter :function(to, from ,next){
          const user_email=localStorage.getItem("user_email")
          if(user_email === null){
            alert('로그인이 필요합니다')
            next(`/`)
          }else{
            next()
          }
        }
    },
    {
        // 해당 피드 댓글
        path: '/feed/comment/:feed_id',
        name: 'FeedComment',
        props: true,
        component: FeedComment,
        beforeEnter :function(to, from ,next){
          const user_email=localStorage.getItem("user_email")
          if(user_email === null){
            alert('로그인이 필요합니다')
            next(`/`)
          }else{
            next()
          }
        }
    },
    {
      // 피드 업로드
      path: '/feed/upload',
      name: 'FeedUpload',
      component: FeedUpload,
      beforeEnter :function(to, from ,next){
        const user_email=localStorage.getItem("user_email")
        if(user_email === null){
          alert('로그인이 필요합니다')
          next(`/`)
        }else{
          next()
        }
      }
    },
    {
        // 해당 피드 게시글로 이동
        path: '/feed/:feed_id',
        name: 'FeedForm',
        props: true,
        component: FeedForm,
        beforeEnter :function(to, from ,next){
          const user_email=localStorage.getItem("user_email")
          if(user_email === null){
            alert('로그인이 필요합니다')
            next(`/`)
          }else{
            next()
          }
        }
    },


    {
        // 문의하기
        path: '/qna',
        name: 'QnA',
        component: QnA,
        beforeEnter :function(to, from ,next){
          const user_email=localStorage.getItem("user_email")
          if(user_email === null){
            alert('로그인이 필요합니다')
            next(`/`)
          }else{
            next()
          }
        }
    },
    {
        // cbti 검사
        path: '/cbtitest',
        name: 'CbtiTest',
        component: CbtiTest,
        beforeEnter :function(to, from ,next){
          const user_email=localStorage.getItem("user_email")
          if(user_email === null){
            alert('로그인이 필요합니다')
            next(`/`)
          }else{
            next()
          }
        }
    },
    {
    // 카페정보-map
    path: '/cafeinfo',
    name: 'CafeInfo',
    component: CafeInfo,
    beforeEnter :function(to, from ,next){
      const user_email=localStorage.getItem("user_email")
      if(user_email === null){
        alert('로그인이 필요합니다')
        
        next(`/`)
      }else{
        next()
      }
    }
    },
    // {
    //     path: '/test/wslive',
    //     name: 'WsLive',
    //     component: WsLive
    // },
    // {
    //     path: '/test/wssend',
    //     name: 'WsSend',
    //     component: WsSend
    // },
    {
        path: '/404',
        name: 'Page404',
        component: Page404,
        meta: { hideHeader: true } // 이 페이지에는 헤더가 나타나지 않음
    },
    {
        path: '*',
        redirect: '/404'

        // 잘못된 경로는 redirect 시켜서 url 노출 시키지 않음
    }
];

// /#/ 없앤 history 모드 사용
const router = new VueRouter({
  routes,
  mode: "history",
  base: process.env.BASE_URL,

});

export default router;
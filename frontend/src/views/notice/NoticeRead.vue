<template>
  <div class="warp">
    <SubNav
      :title="'공지사항'"
    />
    <NoticeReadForm
      id="noticeReadForm"
      :notice_id="notice_id"
    />
  </div>
</template>

<script>
import NoticeReadForm from '@/components/notice/NoticeReadForm.vue'
import SubNav from '@/components/header/SubNav.vue'
export default {
  name : 'NoticeRead',
  props : {
    notice_id : String,
  },
  components : {
    SubNav,
    NoticeReadForm,
  },

}
</script>

<style scoped>
  .warp {
    position:absolute;
    top: 0;
    left:0;
    width: 100%;
    height: 100%;
  }
  #noticeReadForm{
    display: grid;
    justify-items: center;
    margin: 20% 0 0 0;
    gap: 2%;
  }
  @media screen and (max-width: 650px) {
    #noticeReadForm{
      margin: 25% 0;
    }
  }
</style>
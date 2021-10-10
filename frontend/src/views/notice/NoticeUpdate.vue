<template>
  <div class="warp">
    <SubNav
      title="'공지사항 수정'"
    />
    <NoticeUpdateForm
      id="NoticeUpdateForm"
      :notice_id="notice_id"
    />
  </div>
</template>

<script>
import NoticeUpdateForm from '@/components/notice/NoticeUpdateForm.vue'
import SubNav from '@/components/header/SubNav.vue'

export default {
  name : 'NoticeUpdate',
  components : {
    SubNav,
    NoticeUpdateForm,
  },
  props: {
    notice_id : String,
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
  #NoticeUpdateForm{
    display: grid;
    place-items: center;
    margin: 20% 0;
  }
</style>
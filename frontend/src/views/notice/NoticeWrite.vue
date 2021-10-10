<template>
  <div class="warp">
    <SubNav
      title="공지사항 작성"
    />
    <NoticeWriteForm
      id="NoticeWriteForm"
    />
  </div>
</template>

<script>
import NoticeWriteForm from '@/components/notice/NoticeWriteForm.vue'
import SubNav from '@/components/header/SubNav.vue'

export default {
  name : 'NoticeWrite',
  components : {
    SubNav,
    NoticeWriteForm,
  },

}
</script>

<style scoped>
  .warp {
    position:absolute;
    width: 100%;
    height: 100%;
    top :0;
    left: 0;
  }
  #NoticeWriteForm{
    /* position: relative; */
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 100%;
    margin: 10% 0;
    padding: 10% 0;
  }

</style>
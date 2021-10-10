<template>
    <div class="warp">
    <SubNav
      title="문의사항 작성"
    />
    <InquriyCreateForm
      id="InquiryCreateForm"
    />
  </div>
</template>

<script>
import InquriyCreateForm from '@/components/inquiry/InquriyCreateForm.vue'
import SubNav from '@/components/header/SubNav.vue'

export default {
  name : 'InquiryCreate',
  components : {
    SubNav,
    InquriyCreateForm,
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
  #InquiryCreateForm{
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
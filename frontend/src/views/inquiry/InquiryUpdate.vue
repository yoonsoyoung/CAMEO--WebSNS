<template>
  <div class="warp">
    <SubNav
      title="문의사항 수정"
    />
    <InquiryUpdateForm
      id="InquiryUpdateForm"
      :inquiry_id="inquiry_id"
    />
  </div>
</template>

<script>
import InquiryUpdateForm from '@/components/inquiry/InquiryUpdateForm.vue'
import SubNav from '@/components/header/SubNav.vue'

export default {
  name : 'InquiryUpdate',
  components : {
    SubNav,
    InquiryUpdateForm,
  },
  props: {
    inquiry_id : String,
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
  #InquiryUpdateForm{
    display: grid;
    place-items: center;
    margin: 20% 0;
  }
</style>
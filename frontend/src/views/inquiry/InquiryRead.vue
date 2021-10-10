<template>
    <div class="warp">
    <SubNav
      :title="'문의사항'"
    />
    <InquiryReadForm
      id="inquiryReadForm"
      :inquiry_id="inquiry_id"
    />
  </div>
</template>

<script>
import InquiryReadForm from '@/components/inquiry/InquiryReadForm.vue'
import SubNav from '@/components/header/SubNav.vue'
export default {
  name : 'InquiryRead',
  props : {
    inquiry_id : String,
  },
  components : {
    SubNav,
    InquiryReadForm,
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
  #inquiryReadForm{
    display: grid;
    justify-items: center;
    margin: 20% 0 0 0;
    gap: 2%;
  }
  @media screen and (max-width: 650px) {
    #inquiryReadForm{
      margin: 25% 0;
    }
  }
</style>
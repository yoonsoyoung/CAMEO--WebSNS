<template>
    <div class="warp">
    <SubNav
      :title="'문의사항'"
    />
    <div
      id="inquiryTableContainer"
    >
      <InquiryTable
        id="inquiryTable"
      />
    </div>
  </div>
</template>

<script>
import InquiryTable from '@/components/inquiry/InquiryTable.vue'
import SubNav from '@/components/header/SubNav.vue'

export default {
  name: 'Inquiry',
  components : {
    SubNav,
    InquiryTable
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
  #inquiryTableContainer{
    display: grid;
    place-items: center;
    margin: 10% 0;
    padding: 10% 0;
  }
  #inquiryTable {
    width: 80%;
  }
</style>
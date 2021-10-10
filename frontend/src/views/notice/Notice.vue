<template>
  <div class="warp">
    <SubNav
      :title="'공지사항'"
    />
    <div
      id="noticeTableContainer"
    >
      <NoticeTable
        id="noticeTable"
      />
    </div>
  </div>
</template>

<script>
import NoticeTable from '@/components/notice/NoticeTable.vue'
import SubNav from '@/components/header/SubNav.vue'

export default {
  name: 'Notice',
  data () {
    return {

    }
  },
  components : {
    NoticeTable,
    SubNav,
  },
  methods : {

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
  #noticeTableContainer{
    display: grid;
    place-items: center;
    margin: 10% 0;
    padding: 10% 0;
  }
  #noticeTable {
    width: 80%;
  }

</style>
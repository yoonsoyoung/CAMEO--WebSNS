<template>
  <div class="warp">
    <SubNav :title="'Feed'"/>
    <div
      id="FeedItemFormContainer"
    >
      <FeedItemForm
        :feed_id="feed_id"
      />
    </div>
  </div>
</template>

<script>
import SubNav from '@/components/header/SubNav.vue'
import FeedItemForm from '@/components/feed/FeedItemForm.vue'
export default {
  name : 'FeedForm',
  props : {
    feed_id: String,
  },
  components : {
    SubNav,
    FeedItemForm,
  },
  data() {
    return {
    }
  },

}
</script>
<style scoped>
  .warp{
    position:absolute;
    top: 0;
    left:0;
    width: 100%;
    height: 100%;
    box-sizing: border-box;
  }
  #FeedItemFormContainer{
    display: flex;
    width: inherit;
    height: inherit;
    justify-content: center;
    align-items: center;
    margin: 10% 0;
    padding: 5% 0;
  }
</style>
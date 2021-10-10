<template>
  <div class="warp">
    <SubNav
      :title="'글 작성하기'"
    />
    <feed-upload-form id="feedUploadForm"></feed-upload-form>
  </div>
</template>

<script>
  import SubNav from '@/components/header/SubNav.vue'
  import FeedUploadForm from '../../components/feed/FeedUploadForm.vue'
  // import Footer from '@/components/footer/Footer.vue'
  export default {
      components: {
          SubNav,
          FeedUploadForm,
          // Footer,
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
  }
#feedUploadForm{
    position: relative;
    display: grid;
    place-items: center;
    margin: 20% 0;
}
</style>
<template>
  <div class="warp">
    <SubNav
      :title="'회원탈퇴'"
    />
    <RemoveUserForm
     id="removeUserForm"
    />
  </div>
</template>

<script>
import SubNav from '@/components/header/SubNav.vue'
import RemoveUserForm from '@/components/user/Form/RemoveUserForm.vue'


export default {
  name : 'RemoveUser',

  components : {
    SubNav,
    RemoveUserForm,
  },
  methods : {

  }
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
  #removeUserForm{
    margin: 10%;
    padding: 10%;
  }
</style>
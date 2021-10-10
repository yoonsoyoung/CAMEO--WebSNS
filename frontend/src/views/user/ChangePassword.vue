<template>
  <div>
    <Header/>
    <SubNav
      :title="'비밀번호 변경'"
    />
    <ChangePasswordForm
      id="changePasswordForm"
    />
  </div>
</template>

<script>
  import SubNav from '@/components/header/SubNav.vue'
  import Header from '@/components/header/Header.vue'
  // import Footer from '@/components/footer/Footer.vue'
  import ChangePasswordForm from '@/components/user/Form/ChangePasswordForm.vue'

  export default {
    name : 'ChangePassword',
    data (){
      return {

      }
    },
    components : {
      SubNav,
      Header,
      ChangePasswordForm,
    },

  }
</script>

<style scoped>
  #changePasswordForm{
    display: grid;
    place-items: center;
    margin: 20% 0;
  }
</style>
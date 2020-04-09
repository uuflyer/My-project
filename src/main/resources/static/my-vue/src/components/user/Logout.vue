<template>

</template>

<script>
  export default {
    name: "Logout",
    methods: {
      logout() {

        sessionStorage.setItem('user', '');
        sessionStorage.setItem('id', " ");
        this.$axios.get('/auth/logout',)
          .then(res => {
            console.log("正在退出")
            if (res.data.status === 'OK') {
              alert(res.data.msg)
              this.$router.replace({path: "/login"});

              console.log("退出成功，准备跳转到登录")
            } else {

              this.$router.replace({path: "/login"});
            }
          })
      }
    }
    , mounted() {
      this.logout()
    }
  }
</script>


<style scoped>

</style>

<template>
  <div class='container'>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'AppMain',
}
</script>

<style scoped>
.container {
  min-height: calc(100vh - 181px);
  min-width: 1020px;
}
</style>
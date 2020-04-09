<template>

  <el-container>
    <el-aside>

      <UserInfo></UserInfo>
    </el-aside>

    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>

</template>

<script>
  import UserInfo from "./UserSide";
  import BaseInfo from "./BaseInfo";
  import security from "./security";

  export default {
    name: "UserIndex",
    components: {UserInfo, BaseInfo, security}
  }
</script>

<style scoped>

</style>

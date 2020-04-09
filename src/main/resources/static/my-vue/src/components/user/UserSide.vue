<template>


  <el-menu
    class="categories"
    default-active="1"
    active-text-color="blue"
    router
  >
    <span style="position: relative;top: 10px">
      <div>
        <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" :size="70"></el-avatar>
      </div>
      <span slot="title">{{this.username}}</span>
    </span>
    <el-menu-item index="/userInfo/baseInfo" class="item">
      <i class="el-icon-info"></i>
      <span slot="title">基本信息</span>
    </el-menu-item>
    <el-menu-item index="/userInfo/security" class="item">
      <i class="el-icon-lock"></i>
      <span slot="title">安全设置</span>
    </el-menu-item>
  </el-menu>


</template>

<script>
  export default {
    name: "UserSide",
    data() {
      return {
        username: sessionStorage.getItem("username")
      }
    }
  }
</script>

<style scoped>
  .categories {
    position: fixed;
    left: 0%;
    top: 59px;
    width: 20%;
    height: 100%;
    margin-right: 0;
    border-radius: 5px;
    /*background-color: #409EFF;*/
    background-color: white;
  }

  .item {
    position: relative;
    top: 20px;
  }
</style>

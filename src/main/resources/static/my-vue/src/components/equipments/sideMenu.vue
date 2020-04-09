<template>
  <el-menu
    class="categories"
    default-active=""
    active-text-color="blue"
    router
  >
    <span style="position: relative;top: 10px">
      <div>
        <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" :size="70"></el-avatar>
      </div>
      <span slot="title">{{this.username}}</span>
    </span>
    <el-menu-item index="/equipments/guide" class="item">
      <i class="el-icon-guide"></i>
      <span slot="title">开发向导</span>
    </el-menu-item>
    <el-menu-item index="/equipments/equip" class="item">
      <i class="el-icon-cpu"></i>
      <span slot="title">设备中心</span>
    </el-menu-item>

    <el-menu-item index="/equipments/analy" class="item">
      <i class="el-icon-data-analysis"></i>
      <span slot="title">数据分析</span>
    </el-menu-item>
  </el-menu>
</template>

<script>

  console.log(this.username)

  export default {
    name: 'SideMenu',
    data() {
      return {
        isCollapse: true,
        username: sessionStorage.getItem("username")
      }
    },
    methods: {}
  }
</script>

<style scoped>
  .categories {
    position: fixed;
    left: 0%;
    top: 9%;
    width: 20%;
    height: 100%;
    margin-right: 0;
    border-radius: 0px;
    /*background-color: #409EFF;*/
    background-color: white;
  }

  .item {
    position: relative;
    top: 20px;
  }
</style>

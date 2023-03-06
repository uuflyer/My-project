<template>
  <el-menu
    :default-active="'/index'"
    router
    store
    mode="horizontal"
    background-color="Transparent"
    text-color="#000000"
    active-text-color="#409EFF"
    style="position:fixed;width: 100%;left: 0%;top: 0;height: 8% ">


    <el-menu-item index= "/index" style="position: relative;font-size: 20px;left: 15%;width: 10%;height: 100%;margin-left: 10%;">
      <i class="el-icon-house"></i>
      <span>首页</span>
    </el-menu-item>
    <el-menu-item index= "/equipments" style="position: relative;font-size: 20px;left: 15%;width: 10%;height: 100%;margin-left: 10%;">
      <i class="el-icon-video-camera-solid"></i>
      <span>电影大厅</span>
    </el-menu-item>
    <el-menu-item index= "/application" style="position: relative;font-size: 20px;left: 15%;width: 10%;height: 100%;margin-left: 10%;">
      <i class="el-icon-chat-line-square"></i>
      <span>电影评分</span>
    </el-menu-item>


      <el-header style="position: fixed;top: 3%; right:2%;font-size: 15px">
        <el-dropdown>
          <el-button class="el-icon-setting" style="position:fixed;top: 2%;right: 2%">设置</el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <li tabindex="-1" class="">
                <a href="/userInfo">
                  <i class="el-icon-user"></i>
                  个人中心
                </a>
              </li>
            </el-dropdown-item>
            <el-dropdown-item>
              <li>
                <a href="/meeages">
                  <i class="el-icon-chat-dot-round"></i>
                  消息中心
                </a>
              </li>
            </el-dropdown-item>
            <el-dropdown-item>
              <a href="/logout">
                <i class="el-icon-circle-close"></i>
                退出账号
              </a>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>


  </el-menu>
</template>

<script>

  export default {
    name: 'NavMenu',

    data() {
      return {
        navList: [
        
        ]
      }
    }
  }
</script>

<style scoped>
  a {
    text-decoration: none;
  }

  span {
    pointer-events: none;
  }

  .el-dropdown-link {
    cursor: pointer;
    color: whitesmoke;
  }

  .el-icon-arrow-down {
    font-size: 12px;
  }
</style>

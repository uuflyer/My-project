<template>
  <el-menu
    :default-active="'/index'"
    router
    store
    mode="horizontal"
    background-color="#409EFF"
    text-color="whitesmoke"
    active-text-color="white"
    style="position:fixed;width: 100%;left: 0%;top: 0;height: 60px ">
    <el-menu-item style="position: relative;left: 20%;width: 11%" v-for="(item,i) in navList" :key="i"
                  :index="item.name">
      {{ item.navItem }}
    </el-menu-item>


    <span
      style="position: fixed;padding-top: 20px;left: 57%;font-size: 20px;font-weight: bold;color: white">物联网设备管理平台</span>

    <el-container>
      <el-header style="position: fixed;top: 6%; right:5%;font-size: 12px">
        <el-dropdown>
          <el-button class="el-icon-setting" style="position:fixed;top: 2%;right: 5%">设置</el-button>
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

    </el-container>

  </el-menu>
</template>

<script>

  export default {
    name: 'NavMenu',

    data() {
      return {
        navList: [
          {name: '/index', navItem: '首页'},
          {name: '/equipments', navItem: '设备管理'},
          {name: '/application', navItem: '应用开发'},
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

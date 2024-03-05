<template>
  <div>
<template v-if="!isOneself">
  <div class="app-wrapper">
    <div class="sidebar-container">
      <ElMenu />
    </div>
      
      <div class="main-container">
          <HeaderNav class="header-main"/>
          <AppContent class="app-main" />
      </div>
    </div>
</template>
<!-- 如果在空白页打开则不显示框架 -->
    <template v-else>
      <AppContent />
    </template>

  </div>
    
</template>

<script>
import AppContent from "../components/AppContent.vue";
import ElMenu from "../components/ElMenu/index.vue";
import HeaderNav from "./HeaderNav.vue";
export default {
  components: {
    AppContent,
    ElMenu,
    HeaderNav,
  },
  data() {
    return {
      isOneself: false,
    };
  },
  mounted() {
    // 获取当前路由是否是独自打开的
    this.isOneself = this.$route.meta.oneself;
  },
  watch: {
    // 监听路由变化，实时获取路由信息
    $route: function (newVal) {
      this.isOneself = newVal.meta.oneself;
    },
  },

}
</script>

<style lang="less" scoped>
.app-wrapper {
  position: relative;
  height: 100%;
  width: 100%;
  .sidebar-container {
    -webkit-transition: width 0.28s;
    transition: width 0.28s;
    width: 200px !important;
    background-color: #304156;
    height: 100%;
    position: fixed;
    font-size: 0px;
    top: 50px;
    bottom: 0;
    left: 0;
    z-index: 1001;
    overflow: hidden;
    -webkit-box-shadow: 2px 0 6px rgba(0, 21, 41, 0.35);
    box-shadow: 2px 0 6px rgb(0 ,21, 41, 0.35);
    & > div {
      width: 211px !important;
    }
  }
  .main-container {
    min-height: 100%;
    -webkit-transition: margin-left 0.28s;
    transition: margin-left 0.28s;
    margin-left: 0px;
    position: relative;
  }
  .main-container {
    -webkit-transition: margin-left 0.28s;
    transition: margin-left 0.28s;
    position: fixed;
    width: calc(100vw);
    top: 50px;
    right: 0;
    bottom: 0;
    left: 0;
    .header-main {
      position: fixed;
      height: 50px;
      width: calc(100vw);
      right: auto;
      top: 0;
      display: flex;
      align-items: center;
      border-bottom: 1px solid #ddd;
      padding-left: 15px;
      box-sizing: border-box;
      box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    }
    .app-main {
      min-height: 100%;
      width: 100%;
      position: relative;
      overflow: hidden;
    }
  }
}
</style>
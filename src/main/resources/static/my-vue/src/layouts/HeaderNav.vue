<template>
    <div>
      <!-- 后台标题 -->
      <div class="header-title">
        <div class="img">
          <img src="../../static/logo.jpg" alt="" srcset="" />
        </div>
        <div class="title" >音乐推荐系统</div>
      </div>
      <!-- 面包屑展示 -->
      <div>
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item
            v-for="(route, index) in breadcrumbItems"
            :key="index"
          >
            <!-- 判断面包屑是否有path属性，如果有则显示router-link标签 -->
            <router-link v-if="route.path" :to="route.path">
              <el-tag type="success">
                <i :class="route.icon"></i>
                <span>{{ route.title }}</span>
              </el-tag>
            </router-link>
            <!-- 如果没有path属性则不跳转 -->
            <template v-else>
              <el-tag>
                <i :class="route.icon"></i>
                <span>{{ route.title }}</span>
              </el-tag>
            </template>
          </el-breadcrumb-item>
        </el-breadcrumb>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        breadcrumbItems: [],
      }
    },
    mounted() {
      this.geBreadcrumbItems(this.$route)
    },
    methods: {
      geBreadcrumbItems(route) {
        // 获取当前页面的路由组
        this.breadcrumbItems = route.matched
        // 从下标为1的位置开始获取路由，去除了最外层定义的根路由信息，并且获取到的数组里面只有meta数据，方便我们取值
        this.breadcrumbItems = this.breadcrumbItems
          .map((item) => item.meta)
          .splice(1)
  
        // 判断当前页面是否已经是首页
        let nowPath = route.path
        // 如果当前页面不是首页，则在面包屑的首位置添加一个首页链接
        if (nowPath !== '/home') {
          this.breadcrumbItems.unshift({
            title: '首页',
            icon: 'el-icon-s-home',
            path: '/home',
          })
        }
      },
    },
    watch: {
      $route: function (newVal) {
        this.geBreadcrumbItems(newVal)
      },
    },
  }
  </script>
  
  <style lang="less" scoped>
  .header-title {
    display: flex;
    align-items: center;
    gap: 10px;
    color: white;
    font-weight: 700;
    font-size: 17px;
    margin-right: 30px;
    .img {
      width: 40px;
      height: 40px;
      border-radius: 20px;
      overflow: hidden;
      img {
        width: 100%;
        height: 100%;
      }
    }
  }
  .title{
    background: -webkit-linear-gradient(315deg,#42d392 25%,#647eff);//背景
-webkit-background-clip: text;//字体
-webkit-text-fill-color:transparent;
opacity: 0.7;//透明度
background-color:#64696d+7a;//制淡化背景不改变字体透明度
font-size: 18px;

  }

  </style>
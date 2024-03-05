<template>
    <div>
      <!-- 根菜单 -->
      <router-link tag="span" :to="resolvePath()" v-if="!item.children">
        <el-menu-item :index="resolvePath()">
          <i :class="item.meta.icon"></i>
          <span slot="title">{{ item.meta.title }}</span>
        </el-menu-item>
      </router-link>
  
      <!-- 可展开菜单 -->
      <el-submenu :index="resolvePath()" v-else>
        <template slot="title">
          <i :class="item.meta.icon"></i>
          <span slot="title">{{ item.meta.title }}</span>
        </template>
        <!-- 这里递归去展示多级菜单 -->
        <menu-item
          v-for="(route, index) in item.children"
          :key="index"
          :item="route"
          :fatherPath="resolvePath(route.path)"
        >
        </menu-item>
      </el-submenu>
    </div>
  </template>
  
  <script>
  // 引入path用来处理路径
  import path from "path";
  
  export default {
    // 做组件递归时必须定义一个name。然后递归时的组件名就是这里的name值
    name: "MenuItem",
    props: {
      // 上一级的路由信息
      item: {
        type: Object,
        default: null,
      },
      // 上一级的路径
      fatherPath: {
        type: String,
        default: "",
      },
    },
    data() {
      return {};
    },
    methods: {
      resolvePath(routePath = "") {
        return path.resolve(this.fatherPath, routePath);
      },
    },
  };
  </script>
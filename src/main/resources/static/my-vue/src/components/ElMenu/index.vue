<template>
  <div>
    <el-menu
      :default-active="$route.path"
      class="el-menu-vertical-demo"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
    >
      <MenuItem
        v-for="(route, index) in routersList"
        :key="index"
        :item="route"
        :fatherPath="route.path"
      ></MenuItem>
     
      <MenuItem :key="111"
      :item="logout"
      :fatherPath="logout.path"
      ></MenuItem>
    </el-menu>
    
  </div>
</template>

<script>
import routers from "../../router";
import MenuItem from "./MenuItem.vue";
export default {
  components: {
    MenuItem,
  },
  data() {
    return {
      routersList: [],
      logout:{
      path: '/logout',
      name: 'Logout',
      meta: { title: "退出",icon:"el-icon-switch-button" },
    },
    };
  },
  mounted() {
    // 获取所有定义的一级菜单和多级菜单
    
    if(this.$store.state.user.isSuperUser){
      this.routersList = routers.options.routes[0].children;
    }else{
     const menus = routers.options.routes[0].children;
     console.log(menus)
     this.routersList = menus.filter(element => { return element.meta.title != '系统管理' });
     console.log(this.routersList);
    }
    
  }
};
</script>
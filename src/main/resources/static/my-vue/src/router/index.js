import Vue from 'vue'
import Router from 'vue-router'
import Login from "../components/Login";
import Register from "../components/Register";
import Appindex from "../components/home/AppIndex.vue";
import Layout from "../layouts/index.vue";
import movieRate from "../components/common/movieRate";
import Logout from "../components/user/Logout";
import relationShip from "../components/common/relationShip";
import BaseInfo from "../components/user/BaseInfo";
import security from "../components/user/security";
import UserIndex from "../components/user/UserIndex";
import Recommend from "@/components/Recommend/Recommend"
import Home from '../components/common/Home';

Vue.use(Router)

const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '',
      component: Layout,
      redirect: '/home',
      meta: { requireAuth: true },
      children: [
        {
          path: '/home', name: 'home', component: Recommend,
          meta: { title: "首页", icon: "el-icon-s-home", requireAuth: true }
        },
        {
          path: '/system', name: 'movieRate', component: Layout,
          meta: { title: "系统管理", icon: "el-icon-s-home", requireAuth: true },
          children: [
            {
              path: "userManager",
              meta: { title: "用户管理", icon: "el-icon-s-home" },
              component: movieRate,
            },
            {
              path: "item2",
              meta: { title: "歌曲管理", icon: "el-icon-s-home" },
              component: relationShip,
            },
          ]
        },
        {
          path: 'relationShip', name: 'relationShip', component: relationShip,
          meta: { title: "社交关系", icon: "el-icon-s-home", requireAuth: true },
        }, {
          path: 'songsRate', name: 'songsRate', component: relationShip,
          meta: { title: "歌曲评分", icon: "el-icon-s-home", requireAuth: true },
        }


      ]
    },
    {
      path: '/',
      name: 'default',
      redirect: '/login',
    },
    {
      path: '/logout',
      name: 'Logout',
      component: Login
    },

    {
      path: '/login',
      name: 'Login',
      meta: { title: "登录", oneself: true },
      component: Login
    },

    {
      path: '/register',
      name: 'Register.vue',
      component: Register
    }
  ]
});

router.beforeEach((to, from, next) => {
  //设置当前页面名称
  document.title = to.meta.title;
  next();
})
export default router;

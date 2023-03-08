import Vue from 'vue'
import Router from 'vue-router'
import Login from "../components/Login";
import Register from "../components/Register";
import Home from "../components/Home";
import Appindex from "../components/home/AppIndex.vue";
import movieRate from "../components/common/movieRate";
import EquipmentsGuide from "../components/equipments/EquipmentsGuide";
import Equip from "../components/equipments/Equip";
import UserInfo from "../components/user/UserSide";
import Logout from "../components/user/Logout";
import BaseInfo from "../components/user/BaseInfo";
import security from "../components/user/security";
import UserIndex from "../components/user/UserIndex";

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'default',
      redirect: '/home',
    },
    {
      path: '/home',
      name: 'Home',
      component: Home,
      redirect: '/index',
      meta: { requireAuth: true },
      children: [
        { path: '/index', name: 'AppIndex', component: Appindex, meta: { requireAuth: true } },
        {
          path: '/movieRate', name: 'movieRate', component: movieRate, meta: { requireAuth: true },
        },

        {
          path: '/userInfo',
          name: 'userInfo',
          component: UserIndex,
          meta: {
            requireAuth: true
          },
          children: [
            {
              path: '/userInfo/baseInfo',
              name: 'baseIfo',
              component: BaseInfo,
              meta: { requireAuth: true }
            },
            {
              path: '/userInfo/security',
              name: 'security',
              component: security,
              meta: { requireAuth: true }
            }
          ]

        }
      ]
    },

    {
      path: '/guide',
      name: 'Guide',
      component: EquipmentsGuide,
      meta: {
        requireAuth: true
      }
    },
    {
      path: '/logout',
      name: 'Logout',
      component: Logout
    },

    {
      path: '/login',
      name: 'Login',
      component: Login
    },

    {
      path: '/register',
      name: 'Register.vue',
      component: Register
    }
  ]
})

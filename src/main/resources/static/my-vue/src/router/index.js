import Vue from 'vue'
import Router from 'vue-router'
import Appindex from '@/components/home/Appindex'
import Login from "../components/Login";
import Register from "../components/Register";

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/index',
      name: 'AppIndex',
      component: Appindex
    },

    {
      path: '/register',
      name: 'Register.vue',
      component: Register
    }
  ]
})

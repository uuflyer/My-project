import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

export default new Vuex.Store(
  {
    state: {
      user: {
        username: window.localStorage.getItem('user') == null ? '' : JSON.parse(window.localStorage.getItem('user')).username,
        tel: '',
        id: window.localStorage.getItem('id') == null ? '' : JSON.parse(window.localStorage.getItem('id')).id,
      },
      token: ''
    },
    getters: {
      getequips: function (state) {
        return state.equips;
      },
    },
    mutations: {
      login: function f(state, user) {
        state.user = user;
        console.log(user)
        console.log(user.ipAddress)
        console.log(user.userType)
        sessionStorage.setItem('username', user.username);
        sessionStorage.setItem('tel', user.tel);
        sessionStorage.setItem('userId', user.id)
        sessionStorage.setItem('userType', user.userType)
        sessionStorage.setItem('Email', user.email)
        sessionStorage.setItem('IpAddress', user.ipAddress)
        sessionStorage.setItem('HomeAddress', user.homeAddress)
        sessionStorage.setItem('token', user.token)
        window.localStorage.setItem('user', JSON.stringify(user))
        window.localStorage.setItem('id', JSON.stringify(user.id))
      },
      setEquip(state, data) {
        state.equips = data
      }
    }
  }
)

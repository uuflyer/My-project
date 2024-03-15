import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

export default new Vuex.Store(
  {
    state: {
      user: {
        username: '',
        tel: '',
        id: '',
        isSuperUser: false,
      },
      token: ''
    },
    getters: {
      getequips: function (state) {
        return state.equips;
      },
    },
    mutations: {
      login(state, userInfo) {
        console.log('store', userInfo);
        state.user.username = userInfo.userName
        state.user.id = userInfo.userId;
        state.user.isSuperUser = userInfo.isSuperUser;
        console.log('state', state.user.id);
      },
    }
  }
)
